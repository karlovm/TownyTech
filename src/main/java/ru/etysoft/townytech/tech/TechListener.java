package ru.etysoft.townytech.tech;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.event.PlayerEnterTownEvent;
import com.palmergames.bukkit.towny.event.executors.TownyActionEventExecutor;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.utils.CombatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.etysoft.townytech.TownyTech;

import java.util.ArrayList;
import java.util.Random;

public class TechListener implements Listener {

    private static int getRandom() {
        Random r = new Random();
        return r.nextInt(101);
    }

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent e)
    {

        if(e.isCancelled() | !TownyActionEventExecutor.canDestroy(e.getPlayer(), e.getBlock().getLocation(), e.getBlock().getType()))
        {
            return;
        }
        try
        {
            ArrayList<Material> leaves = new ArrayList<>();
            leaves.add(Material.ACACIA_LEAVES);
            leaves.add(Material.BIRCH_LEAVES);
            leaves.add(Material.DARK_OAK_LEAVES);
            leaves.add(Material.JUNGLE_LEAVES);
            leaves.add(Material.OAK_LEAVES);
            leaves.add(Material.SPRUCE_LEAVES);
            if(TownyUniverse.getInstance().getResident(e.getPlayer().getName()).hasTown()) {
                Town town = TownyUniverse.getInstance().getResident(e.getPlayer().getName()).getTown();
                TechTown techTown;
                Block block = e.getBlock();

                if (TownyTech.hasTechByUUID(town.getUUID().toString())) {
                    techTown  = TownyTech.getTechByUUID(town.getUUID().toString());
                }
                else
                {
                    techTown = TownyTech.createTechTown(town);
                }
                int mineLevel = techTown.getMineLevel();
                int farmLevel = techTown.getFarmLevel();
                    if (e.getBlock().getBlockData().getMaterial() == Material.COAL_ORE) {
                        if (getRandom() < MinePerks.getCoalChance(mineLevel)) {
                            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.COAL, MinePerks.getCoalModifier(mineLevel)));
                        }
                    }
                    else if (e.getBlock().getBlockData().getMaterial() == Material.DIAMOND_ORE) {
                        if (getRandom() < MinePerks.getDiamondChance(mineLevel)) {
                            block.getWorld().dropItem(block.getLocation(),new ItemStack(Material.DIAMOND, MinePerks.getDiamondModifier(mineLevel)));
                        }
                        if (getRandom() < MinePerks.getDiamondGoldBonusChance(mineLevel)) {
                            Material goldMaterial;
                            if(MinePerks.isAutoGold(mineLevel))
                            {
                                goldMaterial = Material.GOLD_INGOT;
                            }
                            else
                            {
                                goldMaterial = Material.GOLD_ORE;
                            }
                            block.getWorld().dropItem(block.getLocation(),new ItemStack(goldMaterial, MinePerks.getDiamondGoldBonusModifier(mineLevel)));
                        }
                    }
                    else if (e.getBlock().getBlockData().getMaterial() == Material.GOLD_ORE) {
                        if (getRandom() < MinePerks.getGoldChance(mineLevel)) {
                            Material goldMaterial;
                            int dropCount =  MinePerks.getGoldModifier(mineLevel);
                            if(MinePerks.isAutoGold(mineLevel))
                            {
                                goldMaterial = Material.GOLD_INGOT;
                                e.setDropItems(false);
                                dropCount++;
                            }
                            else
                            {
                                goldMaterial = Material.GOLD_ORE;
                            }
                            block.getWorld().dropItem(block.getLocation(),new ItemStack(goldMaterial, dropCount));
                        }
                    }
                    else if(e.getBlock().getBlockData().getMaterial() == Material.IRON_ORE) {
                        if (getRandom() < MinePerks.getIronChance(mineLevel)) {
                            Material ironMaterial;
                            int dropCount =  MinePerks.getIronModifier(mineLevel);
                            if(MinePerks.isAutoIron(mineLevel))
                            {
                                ironMaterial = Material.IRON_INGOT;
                                e.setDropItems(false);
                                dropCount++;
                            }
                            else
                            {
                                ironMaterial = Material.IRON_ORE;
                            }
                            block.getWorld().dropItem(block.getLocation(),new ItemStack(ironMaterial, dropCount));
                        }
                    }
                    else if(e.getBlock().getBlockData().getMaterial() == Material.WHEAT) {
                        if (getRandom() < FarmPerks.getWheatChance(farmLevel)) {
                            block.getWorld().dropItem(block.getLocation(),new ItemStack(Material.WHEAT, FarmPerks.getWheatModifier(farmLevel)));
                        }
                    }
                    else if(e.getBlock().getBlockData().getMaterial() == Material.CARROT) {
                        if (getRandom() < FarmPerks.getCarrotChance(farmLevel)) {
                            block.getWorld().dropItem(block.getLocation(),new ItemStack(Material.CARROT, FarmPerks.getCarrotModifier(farmLevel)));
                        }
                    }
                    else if(leaves.contains(e.getBlock().getBlockData().getMaterial())) {
                        if (getRandom() < FarmPerks.getGoldenAppleChance(farmLevel)) {
                            block.getWorld().dropItem(block.getLocation(),new ItemStack(Material.GOLDEN_APPLE, FarmPerks.getGoldenAppleModifier(farmLevel)));
                        }
                    }

                }



        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }


    @EventHandler
    public void onPlayerShearEntity (PlayerShearEntityEvent e) {
          if(e.isCancelled() | !TownyActionEventExecutor.canDestroy(e.getPlayer(), e.getEntity().getLocation(), e.getItem().getType()))
          {
              return;
          }
           try
           {
               if(TownyUniverse.getInstance().getResident(e.getPlayer().getName()).hasTown()) {
                   Town town = TownyUniverse.getInstance().getResident(e.getPlayer().getName()).getTown();
                   TechTown techTown;
                   if (TownyTech.hasTechByUUID(town.getUUID().toString())) {
                       techTown  = TownyTech.getTechByUUID(town.getUUID().toString());
                   }
                   else
                   {
                       techTown = TownyTech.createTechTown(town);
                   }
                   int farmLevel = techTown.getFarmLevel();
                   if(e.getEntity().getType() == EntityType.SHEEP)
                   {
                       if (getRandom() < FarmPerks.getWoolChance(farmLevel)) {
                           e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.WHITE_WOOL, FarmPerks.getWoolModifier(farmLevel)));
                       }
                   }
               }

           }
           catch (Exception ex)
           {

           }

    }


    @EventHandler
    public void onTownEnter(PlayerEnterTownEvent e)
    {

        try {
         if(e.getEnteredtown() == TownyUniverse.getInstance().getResident(e.getPlayer().getName()).getTown())
         {
             Town town = TownyUniverse.getInstance().getResident(e.getPlayer().getName()).getTown();
             TechTown techTown;

             if (TownyTech.hasTechByUUID(town.getUUID().toString())) {
                 techTown  = TownyTech.getTechByUUID(town.getUUID().toString());
             }
             else
             {
                 techTown = TownyTech.createTechTown(town);
             }
             int huntLevel = techTown.getHunterLevel();
             int mineLevel = techTown.getMineLevel();
             if(HunterPerks.hasForceEffect(huntLevel))
             {
                PotionEffect potionEffect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, HunterPerks.getForceEffectDuration(huntLevel), HunterPerks.getForceEffectAmplifier(huntLevel));
                e.getPlayer().addPotionEffect(potionEffect);
             }

             if(HunterPerks.hasRegenEffect(huntLevel))
             {
                 PotionEffect potionEffect = new PotionEffect(PotionEffectType.REGENERATION, HunterPerks.getRegenEffectDuration(huntLevel), HunterPerks.getRegenEffectAmplifier(huntLevel));
                 e.getPlayer().addPotionEffect(potionEffect);
             }

             if(MinePerks.hasFastDiggingEffect(mineLevel))
             {
                 PotionEffect potionEffect = new PotionEffect(PotionEffectType.FAST_DIGGING, MinePerks.getFastDiggingEffectDuration(mineLevel), MinePerks.getFastDiggingEffectAmplifier(mineLevel));
                 e.getPlayer().addPotionEffect(potionEffect);
             }

         }
        }
        catch (Exception ex)
        {

        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onKill(EntityDamageByEntityEvent e)
    {

        if(e.isCancelled() || CombatUtil.preventDamageCall(Towny.getPlugin(), e.getDamager(), e.getEntity(), e.getCause()))
        {
            return;
        }
        try {
            if(((LivingEntity) e.getEntity()).getHealth() - e.getDamage() <= 0)
            {
            if (e.getDamager() instanceof Player) {
                Player player = (Player) e.getDamager();
                if (TownyUniverse.getInstance().getResident(player.getName()).hasTown()) {
                    Town town = TownyUniverse.getInstance().getResident(player.getName()).getTown();
                    TechTown techTown;


                    if (TownyTech.hasTechByUUID(town.getUUID().toString())) {
                        techTown = TownyTech.getTechByUUID(town.getUUID().toString());
                    } else {
                        techTown = TownyTech.createTechTown(town);
                    }
                    int huntlevel = techTown.getHunterLevel();
                    if (e.getEntityType() == EntityType.COW) {
                        if(getRandom() < HunterPerks.getLeatherChance(huntlevel))
                        {
                            e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.LEATHER, HunterPerks.getLeatherModifier(huntlevel)));
                        }
                        if(getRandom() < HunterPerks.getMeatChance(huntlevel))
                        {
                            e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.BEEF, HunterPerks.getMeatModifier(huntlevel)));
                        }

                    }
                }
            }
            }
        }
        catch (Exception ex)
        {
           // ex.printStackTrace();
        }
    }
}
