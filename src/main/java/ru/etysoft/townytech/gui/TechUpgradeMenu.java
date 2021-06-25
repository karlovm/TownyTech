package ru.etysoft.townytech.gui;

import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.etysoft.townytech.tech.TechTown;
import ru.etysoft.townytech.utils.Config;
import ru.etysoft.townytech.utils.TextManager;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class TechUpgradeMenu {

    public static void open(Player p, Town town, TechTown techTown, String prefix, int currentLevel, Material upgraded, Material unupgraded)
    {
        if(p.hasPermission("townytech.upgrade")) {
            try {
                HashMap<Integer, Slot> matrix = new HashMap<>();


                for (int i = 0; i <= TextManager.getIntFromConfig(prefix + ".maxLevel"); i++) {
                    ItemStack itemStack = new ItemStack(unupgraded, 1);
                    ItemStack itemStackNot = new ItemStack(upgraded, 1);

                    ItemStack preview;
                    boolean canUpgrade = false;
                    if (i <= currentLevel) {
                        preview = itemStack;
                    } else {
                        preview = itemStackNot;
                        if (i - currentLevel == 1) {
                            canUpgrade = true;
                        }
                    }

                    List<String> finalMeta = TextManager.getStringsFromConfig(prefix + ".levels." + i + ".lore");


                    finalMeta.add(TextManager.getStringFromConfig("price").replace("%s", TextManager.getStringFromConfig(prefix + ".levels." + i + ".price")));
                    String[] arr = finalMeta.toArray(new String[finalMeta.size()]);


                    ItemStack level = Items.createNamedItem(preview, TextManager.getStringFromConfig(prefix + ".levels." + i + ".title"),
                            arr);

                    boolean finalCanUpgrade = canUpgrade;
                    SlotRunnable slotRunnable = new SlotRunnable() {
                        @Override
                        public void run() {
                            if (finalCanUpgrade) {
                                Bukkit.getConsoleSender().sendMessage("Upgrading " + town.getName() + " on " + prefix);
                                techTown.upgradePrefix(prefix, town, p);
                                p.closeInventory();
                            }
                        }
                    };

                    Slot slot = new Slot(slotRunnable, level);

                    matrix.put(i + 1, slot);


                }


                try {
                    GUITable guiTable = new GUITable(TextManager.getStringFromConfig("title-upgrade"), 1, matrix);
                    guiTable.open(p);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                Bukkit.getConsoleSender().getServer().getLogger().warning("TownyTech Upgrade Menu Error! Check the error stack trace(bad config?):");
                e.printStackTrace();
            }
        }
        else
        {
            p.sendMessage(Config.noPerm());
        }
    }
}
