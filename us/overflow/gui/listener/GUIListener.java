// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.gui.listener;

import org.bukkit.event.EventHandler;
import java.util.Iterator;
import us.overflow.base.user.User;
import us.overflow.utils.file.ConfigFile;
import us.overflow.theme.Themes;
import org.bukkit.event.inventory.ClickType;
import us.overflow.base.Check;
import us.overflow.base.CheckType;
import us.overflow.events.OverflowListener;
import us.overflow.utils.config.ConfigManager;
import org.bukkit.ChatColor;
import us.overflow.Overflow;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;

public class GUIListener implements Listener
{
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        try {
            final User user = Overflow.getInstance().getUserManager().getUser(e.getWhoClicked().getUniqueId());
            if (user != null && (user.getPlayer().isOp() || user.getPlayer().hasPermission(Overflow.getInstance().getPermissionUtil().getCommandPermission()))) {
                final String name = ChatColor.stripColor(e.getInventory().getName());
                final String itemName = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
                if (name != null) {
                    final int slot = e.getSlot();
                    if (itemName.equalsIgnoreCase("Back")) {
                        if (name.equalsIgnoreCase("Overflow | Checks")) {
                            Overflow.getInstance().getGuiHelper().getOverflowGUI().openGUI(user);
                        }
                        else if (name.contains("Overflow | Checks |")) {
                            Overflow.getInstance().getGuiHelper().getCheckTypeGUI().openGUI(user);
                        }
                    }
                    if (name.equalsIgnoreCase("Overflow")) {
                        e.setCancelled(true);
                        if (slot == 16) {
                            e.getWhoClicked().sendMessage(ChatColor.GREEN + "Reloading config...");
                            new ConfigManager().doAction(ConfigManager.Action.SAVE);
                            new ConfigManager().doAction(ConfigManager.Action.LOAD);
                            Overflow.getInstance().getCheckManager().getCheckList().forEach(check -> Overflow.getInstance().getEventManager().unregisterListener(check));
                            Overflow.getInstance().getCheckManager().getCheckList().clear();
                            Overflow.getInstance().getCheckManager().unRegisterAll();
                            Overflow.getInstance().getCheckManager().loadChecks();
                            Overflow.getInstance().getUserManager().getUsers().forEach(users -> {
                                users.setViolation(0);
                                users.setVioSplit(0);
                                users.setViolationSplit(0);
                                return;
                            });
                        }
                        if (slot == 10) {
                            user.getPlayer().closeInventory();
                            Overflow.getInstance().getGuiHelper().getCheckTypeGUI().openGUI(user);
                        }
                    }
                    else if (name.equalsIgnoreCase("Overflow | Checks")) {
                        e.setCancelled(true);
                        if (slot == 13) {
                            Overflow.getInstance().getGuiHelper().getToggleChecksGUI().openGUI(user, CheckType.COMBAT);
                        }
                        else if (slot == 12) {
                            Overflow.getInstance().getGuiHelper().getToggleChecksGUI().openGUI(user, CheckType.MOVEMENT);
                        }
                        else if (slot == 14) {
                            Overflow.getInstance().getGuiHelper().getToggleChecksGUI().openGUI(user, CheckType.OTHER);
                        }
                    }
                    else if (name.contains("Overflow | Checks |")) {
                        e.setCancelled(true);
                        try {
                            if (itemName != null) {
                                Check foundCheck = null;
                                for (final Check checks : Overflow.getInstance().getCheckManager().getCheckList()) {
                                    final String format = checks.getCheckName() + "(" + checks.getType() + ")";
                                    if (itemName.equalsIgnoreCase(format)) {
                                        foundCheck = checks;
                                        break;
                                    }
                                }
                                if (foundCheck != null && user.getLastGUICheckType() != null) {
                                    if (e.getClick() == ClickType.LEFT) {
                                        foundCheck.registerCheck(!foundCheck.isEnabled());
                                    }
                                    else if (e.getClick() == ClickType.RIGHT) {
                                        foundCheck.setAutoBans(!foundCheck.isAutoBans());
                                    }
                                    user.getPlayer().closeInventory();
                                    Overflow.getInstance().getGuiHelper().getToggleChecksGUI().openGUI(user, user.getLastGUICheckType());
                                    Overflow.getInstance().getCheckManager().saveAll();
                                }
                            }
                        }
                        catch (Exception ex) {}
                    }
                    else if (name.contains("Overflow | Theme")) {
                        e.setCancelled(true);
                        try {
                            if (itemName != null) {
                                Themes themes = null;
                                for (final Themes theme : Themes.values()) {
                                    if (theme.name().equalsIgnoreCase(itemName)) {
                                        themes = theme;
                                    }
                                }
                                if (themes != null) {
                                    if (themes == Themes.OVERFLOW_2_0) {
                                        ConfigFile.getInstance().getData().set("Customizer.Prefix", (Object)"&7[&cOverflow&7]&r");
                                        ConfigFile.getInstance().getData().set("Customizer.Alert", (Object)"&c%PREFIX% &7%PLAYER% flagged &c%CHECK% &7(&ctype %TYPE%&7) VL: &c%VL%");
                                        ConfigFile.getInstance().saveData();
                                        Overflow.getInstance().getConfigValues().setPrefix(ConfigManager.fixedColor(ConfigFile.getInstance().getData().getString("Customizer.Prefix")));
                                        Overflow.getInstance().getConfigValues().setAlertMessage(ConfigManager.fixedColor(ConfigFile.getInstance().getData().getString("Customizer.Alert")));
                                    }
                                    else {
                                        Overflow.getInstance().getThemeManager().load(themes.name());
                                    }
                                    Overflow.getInstance().getConfig().set("Theme", (Object)themes.name());
                                    e.getWhoClicked().sendMessage(ChatColor.GREEN + "Selected " + ChatColor.GRAY + themes.name() + ChatColor.GREEN + " theme!");
                                }
                            }
                        }
                        catch (Exception ex2) {}
                    }
                }
            }
        }
        catch (Exception ex3) {}
    }
}
