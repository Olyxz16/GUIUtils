package guiutils.gui;

import guiutils.nbt.NBTTagUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public abstract class GUIBase implements Listener {

    protected Inventory inventory;
    protected Map<Integer, Runnable> eventMap;

    public GUIBase(JavaPlugin plugin, int size, String name)
    {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.inventory = Bukkit.createInventory(null, size, name);
        this.eventMap = new HashMap<>();
    }

    public abstract void build(Player player);

    public void open(Player player) {
        build(player);
        player.openInventory(this.inventory);
    }

    public GUIItem setItem(int slot, GUIItem item) {
        this.inventory.setItem(slot, item.asItemStack());
        return item;
    }
    public GUIItem setItem(int slot, GUIItem item, Runnable event)
    {
        this.inventory.setItem(slot, item.asItemStack());
        this.eventMap.put(slot, event);
        return item;
    }
    public void addEvent(int slot, Runnable event) {
        this.eventMap.put(slot, event);
    }
    public void fill(int[] slots, GUIItem item)
    {
        for(int slot : slots)
        {
            this.inventory.setItem(slot, item.asItemStack());
        }
    }
    public void fill(Iterable<Integer> slots, GUIItem item)
    {
        for(int slot : slots)
        {
            this.inventory.setItem(slot, item.asItemStack());
        }
    }


    @EventHandler
    public void onClickInventory(InventoryClickEvent e) {
        e.setCancelled(true);
        if(e.getClickedInventory() != this.inventory)
        {
            return;
        }
        var player = (Player)e.getWhoClicked();
        var slot = e.getSlot();
        if(player == null)
        {
            return;
        }
        if(this.eventMap.containsKey(slot))
        {
            this.eventMap.get(slot).run();
        }
    }

    @EventHandler()
    public void onClose(InventoryCloseEvent event) {
        if(event.getInventory() == this.inventory)
        {
            InventoryClickEvent.getHandlerList().unregister(this);
            InventoryCloseEvent.getHandlerList().unregister(this);
        }
    }

}

