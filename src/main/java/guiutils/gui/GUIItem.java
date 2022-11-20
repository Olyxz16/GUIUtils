package guiutils.gui;

import guiutils.nbt.NBTTagUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUIItem {

    protected ItemStack itemStack;


    public GUIItem(ItemStack stack)
    {
        this.itemStack = stack;
    }
    public GUIItem(Material material)
    {
        this(new ItemStack(material));
    }

    public ItemStack asItemStack()
    {
        return this.itemStack;
    }


    public GUIItem setItemStack(ItemStack stack)
    {
        this.itemStack = stack;
        return this;
    }
    public GUIItem setMaterial(Material material)
    {
        this.itemStack.setType(material);
        return this;
    }
    public GUIItem setCount(int count)
    {
        this.itemStack.setAmount(count);
        return this;
    }

    public GUIItem addEnchantment(Enchantment enchantment, int level)
    {
        this.itemStack.addEnchantment(enchantment, level);
        return this;
    }
    public GUIItem addEnchantment(Enchantment enchantment, int level, boolean hide)
    {
        this.itemStack.addEnchantment(enchantment, level);
        if(hide)
        {
            var meta = this.itemStack.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public GUIItem setItemMeta(ItemMeta meta)
    {
        this.itemStack.setItemMeta(meta);
        return this;
    }
    public GUIItem setDisplayName(String name)
    {
        var meta = this.itemStack.getItemMeta();
        meta.setDisplayName(name);
        this.itemStack.setItemMeta(meta);
        return this;
    }
    public GUIItem setLore(List<String> lore)
    {
        var meta = this.itemStack.getItemMeta();
        meta.setLore(lore);
        this.itemStack.setItemMeta(meta);
        return this;
    }
    public GUIItem addLore(String loreLine)
    {
        var meta = this.itemStack.getItemMeta();
        var lore = meta.getLore();
        if(lore == null)
        {
            lore = new ArrayList<String>();
        }
        lore.add(loreLine);
        meta.setLore(lore);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public GUIItem addFlag(ItemFlag flag)
    {
        var meta = this.itemStack.getItemMeta();
        meta.addItemFlags(flag);
        this.itemStack.setItemMeta(meta);
        return this;
    }


    public GUIItem setNBTTagInt(String tag, int value)
    {
        this.itemStack = NBTTagUtils.setNBTTagInt(this.itemStack, tag, value);
        return this;
    }
    public GUIItem setNBTTagString(String tag, String value)
    {
        this.itemStack = NBTTagUtils.setNBTTagString(this.itemStack, tag, value);
        return this;
    }

    public int getNBTTagInt(String tag)
    {
        return NBTTagUtils.getNBTTagInt(this.itemStack, tag);
    }
    public String getNBTTagString(String tag)
    {
        return NBTTagUtils.getNBTTagString(this.itemStack, tag);
    }

}
