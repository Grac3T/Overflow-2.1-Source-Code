// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.gui;

import org.bukkit.inventory.Inventory;
import java.util.Collections;
import java.util.List;
import us.overflow.utils.gui.GUIUtils;
import java.util.Arrays;
import us.overflow.utils.other.Verbose;
import us.overflow.Overflow;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import us.overflow.base.user.User;

public class OverflowGUI
{
    public void openGUI(final User user) {
        final Inventory inventory = Bukkit.getServer().createInventory((InventoryHolder)null, 27, ChatColor.GREEN + "Overflow");
        inventory.setItem(13, GUIUtils.generateItem(new ItemStack(Material.WATER_BUCKET, 1), ChatColor.AQUA + "Overflow | " + Overflow.getInstance().getDescription().getVersion(), (List)Arrays.asList(ChatColor.AQUA + "Checks: " + ChatColor.RED + Overflow.getInstance().getCheckManager().getCheckList().size(), ChatColor.AQUA + "Registered to: " + ChatColor.RED + ((Verbose.licensedTo != null) ? Verbose.licensedTo : "UnRegistered"))));
        inventory.setItem(10, GUIUtils.generateItem(new ItemStack(Material.DIAMOND_SWORD, 1), ChatColor.AQUA + "Check Management", (List)Collections.singletonList(ChatColor.AQUA + "Click to manage checks.")));
        inventory.setItem(16, GUIUtils.generateItem(new ItemStack(Material.ANVIL, 1), ChatColor.AQUA + "Reload", (List)Collections.singletonList(ChatColor.AQUA + "Click to reload the anticheat.")));
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
