package ru.etysoft.townytech.gui;

import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.etysoft.townytech.TownyTech;
import ru.etysoft.townytech.tech.TechTown;
import ru.etysoft.townytech.utils.TextManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TechMainMenu {

    public static void open(Player p, Town town, TechTown techTown)
    {
        try {
            HashMap<Integer, Slot> matrix = new HashMap<>();
            ItemStack itemStack = new ItemStack(Material.STONE_BRICK_WALL, 1);
            ItemStack namedItem = Items.createNamedItem(itemStack, TextManager.getStringFromConfig("town").replace("%s", town.getName()),
                    TextManager.getStringFromConfig("residents").replace("%s", String.valueOf(town.getResidents().size())),
                    TextManager.getStringFromConfig("levels." + techTown.getLevel()));


            SlotRunnable slotRunnable = new SlotRunnable() {
                @Override
                public void run() {
                }
            };

            SlotRunnable mineTech = new SlotRunnable() {
                @Override
                public void run() {
                    TechUpgradeMenu.open(p, town, techTown, "miners", techTown.getMineLevel(), Material.DIAMOND_PICKAXE, Material.STONE_PICKAXE);
                }
            };

            Slot slot = new Slot(slotRunnable, namedItem);


            List<String> finalMeta = new ArrayList<>();
            finalMeta.add(TextManager.getStringFromConfig("level").replace("%s", String.valueOf(techTown.getMineLevel())));
            finalMeta.addAll(TextManager.getStringsFromConfig("miners.levels." + techTown.getMineLevel() + ".lore"));
            String[] arr = finalMeta.toArray(new String[finalMeta.size()]);

            ItemStack itemStack2 = new ItemStack(Material.STONE_PICKAXE, 1);
            ItemStack mineIcon = Items.createNamedItem(itemStack2, TextManager.getStringFromConfig("miners.title"),
                   arr);

            Slot slot2 = new Slot(mineTech, mineIcon);


            List<String> finalMeta2 = new ArrayList<>();
            finalMeta2.add(TextManager.getStringFromConfig("level").replace("%s", String.valueOf(techTown.getHunterLevel())));
            finalMeta2.addAll(TextManager.getStringsFromConfig("hunters.levels." + techTown.getHunterLevel() + ".lore"));
            String[] arr2 = finalMeta2.toArray(new String[finalMeta2.size()]);

            ItemStack huntItem = new ItemStack(Material.STONE_SWORD, 1);
            ItemStack huntIcon = Items.createNamedItem(huntItem, TextManager.getStringFromConfig("hunters.title"),
                    arr2);

            SlotRunnable huntTech = new SlotRunnable() {
                @Override
                public void run() {
                    TechUpgradeMenu.open(p, town, techTown, "hunters", techTown.getHunterLevel(), Material.DIAMOND_SWORD, Material.STONE_SWORD);
                }
            };


            Slot slot3 = new Slot(huntTech, huntIcon);

            List<String> finalMeta3 = new ArrayList<>();
            finalMeta3.add(TextManager.getStringFromConfig("level").replace("%s", String.valueOf(techTown.getFarmLevel())));
            finalMeta3.addAll(TextManager.getStringsFromConfig("farmers.levels." + techTown.getFarmLevel() + ".lore"));
            String[] arr3 = finalMeta3.toArray(new String[finalMeta3.size()]);

            ItemStack farmItem = new ItemStack(Material.WHEAT, 1);
            ItemStack farmIcon = Items.createNamedItem(farmItem, TextManager.getStringFromConfig("farmers.title"),
                    arr3);

            SlotRunnable farmTech = new SlotRunnable() {
                @Override
                public void run() {
                    TechUpgradeMenu.open(p, town, techTown, "farmers", techTown.getFarmLevel(), Material.WHEAT_SEEDS, Material.WHEAT);
                }
            };


            Slot slot4 = new Slot(farmTech, farmIcon);


            matrix.put(14, slot);
            matrix.put(22, slot2);
            matrix.put(23, slot3);
            matrix.put(24, slot4);
            try {
                GUITable guiTable = new GUITable(TextManager.getStringFromConfig("title"), 5, matrix);
                guiTable.open(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            Bukkit.getConsoleSender().getServer().getLogger().warning("TownyTech Main Menu Error! Check the error stack trace(bad config?):");
            e.printStackTrace();
        }
    }

}
