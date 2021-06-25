package ru.etysoft.townytech.tech;

import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.etysoft.townytech.utils.Config;
import ru.etysoft.townytech.utils.TextManager;

public class TechTown {

    private int mineLevel = 0;
    private int hunterLevel = 0;
    private int farmLevel = 0;
    private int level = 0;
    private String townName = "no-data";

    public TechTown()
    {

    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public int getHunterLevel() {
        return hunterLevel;
    }

    public void upgradePrefix(String prefix, Town town, CommandSender sender)
    {
        townName = town.getName();
        if(prefix.equals("miners"))
        {
            upgradeMine(town, sender);
        }
        else if(prefix.equals("hunters"))
        {
            upgradeHunter(town, sender);
        }
        else  if(prefix.equals("farmers"))
        {
            upgradeFarmer(town, sender);
        }
    }

    public boolean upgradeFarmer(Town town, CommandSender sender)
    {
        townName = town.getName();
        int maxLevel = TextManager.getIntFromConfig("levels.maxLevel");
        if((farmLevel - level + 1) <= 1 && farmLevel + 1 <= FarmPerks.getMaxLevel() | level == maxLevel)
        {
            try {
                if (town.getAccount().withdraw(TextManager.getIntFromConfig("farmers.levels." + (farmLevel + 1) + ".price"), "TownyTech")){
                    farmLevel++;

                    sender.sendMessage(TextManager.getPrefixedStringFromConfig("upgrade-farm").replace("%s", String.valueOf(farmLevel)));
                    checkLevelUpgrade((Player) sender);
                    return true;
                }
                else
                {
                    sender.sendMessage(Config.noTownMoney());
                    return false;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
        }
        sender.sendMessage(Config.cantUpgrade());
        return false;
    }

    public boolean upgradeHunter(Town town, CommandSender sender)
    {
        townName = town.getName();
        int maxLevel = TextManager.getIntFromConfig("levels.maxLevel");
        if((hunterLevel - level + 1) <= 1 && hunterLevel + 1 <= HunterPerks.getMaxLevel() | level == maxLevel)
        {
            try {
                if (town.getAccount().withdraw(TextManager.getIntFromConfig("hunters.levels." + (hunterLevel + 1) + ".price"), "TownyTech")){
                    hunterLevel++;

                    sender.sendMessage(TextManager.getPrefixedStringFromConfig("upgrade-hunt").replace("%s", String.valueOf(hunterLevel)));
                    checkLevelUpgrade((Player) sender);
                    return true;
                }
                else
                {
                    sender.sendMessage(Config.noTownMoney());
                    return false;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
        }
        sender.sendMessage(Config.cantUpgrade());
        return false;
    }

    public void checkLevelUpgrade(Player p)
    {
        int maxLevel = TextManager.getIntFromConfig("levels.maxLevel");
        if(mineLevel == farmLevel && farmLevel == hunterLevel  && level <= maxLevel)
        {
            level++;
            p.sendMessage(TextManager.getPrefixedStringFromConfig("upgrade").replace("%s",
                    TextManager.getStringFromConfig("levels." + level)));
            p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1f, 1f);
        }

    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMineLevel(int mineLevel) {
        this.mineLevel = mineLevel;
    }

    public void setHunterLevel(int hunterLevel) {
        this.hunterLevel = hunterLevel;
    }

    public void setFarmLevel(int farmLevel) {
        this.farmLevel = farmLevel;
    }

    public int getFarmLevel() {
        return farmLevel;
    }

    public boolean upgradeMine(Town town, CommandSender sender)
    {
        townName = town.getName();
        if((mineLevel - level + 1) <= 1 && mineLevel + 1 <= MinePerks.getMaxLevel())
        {
            try {
                if (town.getAccount().withdraw(TextManager.getIntFromConfig("miners.levels." + (mineLevel + 1) + ".price"), "TownyTech")){
                    mineLevel++;

                    sender.sendMessage(TextManager.getPrefixedStringFromConfig("upgrade-mine").replace("%s", String.valueOf(mineLevel)));
                    checkLevelUpgrade((Player) sender);
                    return true;
                }
                else
                {
                    sender.sendMessage(Config.noTownMoney());
                    return false;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
        }
        sender.sendMessage(Config.cantUpgrade());
        return false;
    }

    public int getMineLevel() {
        return mineLevel;
    }

    public int getLevel() {
        return level;
    }


}
