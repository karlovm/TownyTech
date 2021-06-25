package ru.etysoft.townytech.commands;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.etysoft.townytech.TownyTech;
import ru.etysoft.townytech.gui.*;
import ru.etysoft.townytech.tech.TechTown;
import ru.etysoft.townytech.utils.Config;
import ru.etysoft.townytech.utils.TextManager;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.BiConsumer;

public class MainExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0) {
            String subcommand = args[0].toLowerCase();
            if(subcommand.equals("testguitable"))
            {
                if(sender instanceof HumanEntity) {
                    GUITable.debug((HumanEntity) sender);
                }
                else
                {
                    sender.sendMessage(Config.denyConsole());
                }
            }
            else if(subcommand.equals("reload"))
            {
                if(sender.hasPermission("townytech.admin"))
                {
                    TownyTech.getInstance().reloadConfig();
                    sender.sendMessage("Successfully reloaded config!");
                }
                else
                {
                    sender.sendMessage(Config.noPerm());
                }
            }
            else if(subcommand.equals("save"))
            {
                if(sender.hasPermission("townytech.admin"))
                {
                    TownyTech.getInstance().save();
                }
                else
                {
                    sender.sendMessage(Config.noPerm());
                }
            }
            else if(subcommand.equals("list"))
            {
                if(sender.hasPermission("townytech.admin"))
                {
                    TownyTech.getTechList().forEach(new BiConsumer<String, TechTown>() {
                        @Override
                        public void accept(String s, TechTown techTown) {
                            Town town = TownyUniverse.getInstance().getTown(techTown.getTownName());
                            sender.sendMessage(town.getName() + " (" + techTown.getLevel() + "): mine:" + techTown.getMineLevel() + ", hunt: " + techTown.getHunterLevel() + ", farm: " + techTown.getFarmLevel());
                        }
                    });
                }
                else
                {
                    sender.sendMessage(Config.noPerm());
                }
            }
            else if(subcommand.equals("info"))
            {
                sender.sendMessage(TextManager.toColor("&7[&9TownyTech&7] >> Running TownyTech v" + TownyTech.getInstance().getDescription().getVersion() + " &bby karlov_m"));
            }
            else if(subcommand.equals("set"))
            {
                if(sender.hasPermission("townytech.admin"))
                {
                    if(args.length > 3)
                    {
                        try
                        {

                            Town town = TownyUniverse.getInstance().getTown(args[1]);
                            TechTown techTown = TownyTech.getTechByUUID(town.getUUID().toString());
                            if(args[2].equals("farm"))
                            {
                                techTown.setFarmLevel(Integer.parseInt(args[3]));
                                sender.sendMessage("Set farm level " + args[3] + " on " + args[1]);
                            }
                            else if(args[2].equals("mine"))
                            {
                                techTown.setMineLevel(Integer.parseInt(args[3]));
                                sender.sendMessage("Set minte level " + args[3] + " on " + args[1]);
                            }
                            else if(args[2].equals("hunt"))
                            {
                                techTown.setHunterLevel(Integer.parseInt(args[3]));
                                sender.sendMessage("Set hunt level " + args[3] + " on " + args[1]);
                            }
                            else if(args[2].equals("town"))
                            {
                                techTown.setLevel(Integer.parseInt(args[3]));
                                sender.sendMessage("Set town level " + args[3] + " on " + args[1]);
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            sender.sendMessage(Config.args());
                        }

                    }
                    else
                    {
                        sender.sendMessage(Config.args());
                    }
                }
                else
                {
                    sender.sendMessage(Config.noPerm());
                }
            }
        }
        else
        {
            if(TownyUniverse.getInstance().getResident(sender.getName()).hasTown())
            {
                try {
                    Town town = TownyUniverse.getInstance().getResident(sender.getName()).getTown();
                    TechTown techTown;

                    if(!TownyTech.hasTechByUUID(town.getUUID().toString()))
                    {
                        techTown = TownyTech.createTechTown(town);
                    }
                    else
                    {
                        techTown = TownyTech.getTechByUUID(town.getUUID().toString());
                    }
                    TechMainMenu.open((Player) sender, town, techTown);
                } catch (NotRegisteredException e) {
                    e.printStackTrace();
                }

            }
            else
            {
                sender.sendMessage(Config.noTown());
            }
        }
        return true;
    }
}
