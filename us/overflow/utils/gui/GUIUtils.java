// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.gui;

import us.overflow.utils.MathUtil;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import org.bukkit.inventory.ItemStack;

public class GUIUtils
{
    public static ItemStack generateItem(final ItemStack itemStack, final String itemName, final List<String> meta) {
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore((List)meta);
        itemMeta.setDisplayName(itemName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    
    public static ItemStack generateItem(final ItemStack itemStack, final String itemName) {
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(itemName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    
    public static ItemStack createSpacer() {
        final ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        final ItemMeta im = i.getItemMeta();
        im.setDisplayName(" ");
        i.setItemMeta(im);
        return i;
    }
    
    public static ItemStack createSpacer(final byte color) {
        final ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)color);
        final ItemMeta im = i.getItemMeta();
        im.setDisplayName(" ");
        i.setItemMeta(im);
        return i;
    }
    
    public static ItemStack randomColorSpacer() {
        final ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)(byte)MathUtil.getRandomInteger(0, 20));
        final ItemMeta im = i.getItemMeta();
        im.setDisplayName(" ");
        i.setItemMeta(im);
        return i;
    }
}
