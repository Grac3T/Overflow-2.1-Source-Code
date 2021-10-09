// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.theme;

import org.bukkit.inventory.Inventory;
import us.overflow.utils.gui.GUIUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ThemeGUI
{
    public void openGUI(final Player player) {
        final Inventory inventory = Bukkit.getServer().createInventory((InventoryHolder)null, 27, ChatColor.GREEN + "Overflow | Themes");
        int slot = 0;
        for (final Themes themes : Themes.values()) {
            inventory.setItem(slot, GUIUtils.generateItem(new ItemStack(Material.WOOL, 1), ChatColor.GREEN + themes.name().toUpperCase().substring(0, 1) + themes.name().toLowerCase().substring(1)));
            ++slot;
        }
        player.openInventory(inventory);
    }
}
