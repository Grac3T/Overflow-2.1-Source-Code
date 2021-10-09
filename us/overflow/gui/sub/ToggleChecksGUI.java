// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.gui.sub;

import java.util.Iterator;
import org.bukkit.inventory.Inventory;
import java.util.Collections;
import java.util.List;
import us.overflow.utils.gui.GUIUtils;
import java.util.Arrays;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import java.util.function.Consumer;
import us.overflow.Overflow;
import us.overflow.base.Check;
import java.util.ArrayList;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import us.overflow.base.CheckType;
import us.overflow.base.user.User;

public class ToggleChecksGUI
{
    public void openGUI(final User user, final CheckType checkType) {
        user.setLastGUICheckType(checkType);
        final Inventory inventory = Bukkit.getServer().createInventory((InventoryHolder)null, 54, ChatColor.GREEN + "Overflow | Checks | " + checkType.toString().toUpperCase());
        final List<Check> checks = new ArrayList<Check>();
        Overflow.getInstance().getCheckManager().getCheckList().stream().filter(ToggleChecksGUI::lambda$openGUI$0).forEach(checks::add);
        int i = 0;
        for (final Check check : checks) {
            final boolean enabled = check.isEnabled();
            final boolean autobans = check.isAutoBans();
            inventory.setItem(i, GUIUtils.generateItem(new ItemStack(enabled ? (autobans ? Material.EMERALD_BLOCK : Material.GOLD_BLOCK) : Material.REDSTONE_BLOCK, 1), (enabled ? (autobans ? ChatColor.GREEN : ChatColor.GOLD) : ChatColor.RED) + check.getCheckName() + "(" + check.getType() + ")", (List)Arrays.asList(ChatColor.AQUA + "State: " + (enabled ? (ChatColor.GREEN + "Enabled") : (ChatColor.RED + "Disabled")), ChatColor.AQUA + "AutoBans: " + (autobans ? (ChatColor.GREEN + "Enabled") : (ChatColor.RED + "Disabled")), " ", ChatColor.LIGHT_PURPLE + "Left click to toggle this check.", ChatColor.LIGHT_PURPLE + "Right click to toggle autobans for this check.")));
            ++i;
        }
        int currentSlot = 0;
        for (final ItemStack itemStack : inventory.getContents()) {
            if (itemStack == null) {
                inventory.setItem(currentSlot, GUIUtils.createSpacer((byte)11));
            }
            ++currentSlot;
        }
        inventory.setItem(53, GUIUtils.generateItem(new ItemStack(Material.REDSTONE_BLOCK, 1), ChatColor.RED + "Back", (List)Collections.emptyList()));
        user.getPlayer().openInventory(inventory);
    }
}
