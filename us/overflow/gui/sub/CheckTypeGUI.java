// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.gui.sub;

import org.bukkit.inventory.Inventory;
import java.util.List;
import us.overflow.utils.gui.GUIUtils;
import java.util.Collections;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import us.overflow.base.user.User;

public class CheckTypeGUI
{
    public void openGUI(final User user) {
        final Inventory inventory = Bukkit.getServer().createInventory((InventoryHolder)null, 27, ChatColor.GREEN + "Overflow | Checks");
        inventory.setItem(13, GUIUtils.generateItem(new ItemStack(Material.DIAMOND_SWORD, 1), ChatColor.AQUA + "Combat", (List)Collections.emptyList()));
        inventory.setItem(12, GUIUtils.generateItem(new ItemStack(Material.FEATHER, 1), ChatColor.AQUA + "Movement", (List)Collections.emptyList()));
        inventory.setItem(14, GUIUtils.generateItem(new ItemStack(Material.REDSTONE, 1), ChatColor.AQUA + "Other", (List)Collections.emptyList()));
        inventory.setItem(26, GUIUtils.generateItem(new ItemStack(Material.REDSTONE_BLOCK, 1), ChatColor.RED + "Back", (List)Collections.emptyList()));
        int currentSlot = 0;
        for (final ItemStack itemStack : inventory.getContents()) {
            if (itemStack == null) {
                inventory.setItem(currentSlot, GUIUtils.createSpacer((byte)11));
            }
            ++currentSlot;
        }
        user.getPlayer().openInventory(inventory);
    }
}
