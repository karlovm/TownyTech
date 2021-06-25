package ru.etysoft.townytech.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import ru.etysoft.townytech.TownyTech;
import ru.etysoft.townytech.utils.TextManager;

import java.util.HashMap;
import java.util.List;

public class GUITable implements Listener {

    Inventory inventory;
    HashMap<Integer, Slot> matrix;
    int lines;
    int maxSize;

    public GUITable(String title, int lines, HashMap<Integer, Slot> matrix) throws Exception
    {
        if(lines > 6)
        {
            throw new Exception("Lines cannot be more than 6!");
        }
        else
        {
            this.lines = lines;
            maxSize = lines * 9;
            inventory = Bukkit.createInventory(null, lines * 9, title);
            Bukkit.getPluginManager().registerEvents(this, TownyTech.getInstance());
            this.matrix = matrix;
            initializeItems();
        }

    }

    private void initializeItems() throws Exception {
        for(int i = 0; i < maxSize; i++)
        {
            inventory.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }


                for(int index : matrix.keySet())
                {
                    Slot slot = matrix.get(index);
                    inventory.setItem(index - 1, slot.getItem());
                }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() != inventory) return;
        e.setCancelled(true);
        int clickedId = e.getRawSlot();
        if(matrix.containsKey(clickedId + 1)) {
            final Player p = (Player) e.getWhoClicked();
            Slot slot = matrix.get(clickedId + 1);
            SlotRunnable slotRunnable = slot.getOnClick();
            slotRunnable.setSender(p);
            slotRunnable.run();
        }
    }

    @EventHandler
    public void onInvetoryClosed(final InventoryCloseEvent e)
    {
        if (e.getInventory() != inventory) return;
        InventoryCloseEvent.getHandlerList().unregister(this);
        InventoryDragEvent.getHandlerList().unregister(this);
        InventoryClickEvent.getHandlerList().unregister(this);
    }

    @EventHandler
    public void onInventoryDrag(final InventoryDragEvent e) {
        if (e.getInventory() == inventory) {
            e.setCancelled(true);
        }
    }

    public void open(final HumanEntity humanEntity) {
        humanEntity.openInventory(inventory);
    }

    public static void debug(HumanEntity sender)
    {
        HashMap<Integer, Slot> matrix = new HashMap<>();
        ItemStack itemStack = new ItemStack(Material.ACACIA_DOOR, 1);
        ItemStack namedItem = Items.createNamedItem(itemStack, "Fuck it!", "&bor maybe not..");


        SlotRunnable slotRunnable = new SlotRunnable() {
            @Override
            public void run() {
                System.out.println("clicked on fuckit!");
            }
        };

        Slot slot = new Slot(slotRunnable, itemStack);

        ItemStack itemStack2 = new ItemStack(Material.PAPER, 1);
        ItemStack namedItem2 = Items.createNamedItem(itemStack2, "Pop-it", TextManager.toColor("&bor maybe not.."));


        SlotRunnable slotRunnable2 = new SlotRunnable() {
            @Override
            public void run() {
                System.out.println("clicked on popit!");
            }
        };

        Slot slot2 = new Slot(slotRunnable2, itemStack2);

        matrix.put(3, slot);
        matrix.put(15, slot2);

        try {
            GUITable guiTable = new GUITable("Test GUITable", 5, matrix);
            guiTable.open(sender);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



}
