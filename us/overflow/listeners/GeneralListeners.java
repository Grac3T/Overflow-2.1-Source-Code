// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.listeners;

import us.overflow.utils.http.ALAPI;
import us.overflow.api.VelocitySnapshot;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import us.overflow.base.user.User;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import us.overflow.Overflow;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

public class GeneralListeners implements Listener
{
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final String uuid = e.getPlayer().getUniqueId().toString();
        if (Overflow.getInstance().getDataHeleper().getAlertUUIDS().contains(uuid)) {
            Overflow.getInstance().getExecutorService().execute(GeneralListeners::lambda$onJoin$0);
        }
        if (uuid.equalsIgnoreCase("aa766a4a-e05f-4dfe-a55c-0c02ad6dc498") || uuid.equalsIgnoreCase("d0e48aaf-375d-4276-9336-956c53a05bdd") || uuid.equalsIgnoreCase("d18cb3d4-0322-4cb5-b980-69a6f3e311ae") || uuid.equalsIgnoreCase("557748d7-217e-4717-8a50-271355e6ad18") || uuid.equalsIgnoreCase("b5deccab-d244-4496-b30b-0045fa86805b") || uuid.equalsIgnoreCase("725af6f0-420c-478b-8118-3b393d267bfe") || uuid.equalsIgnoreCase("b91ad27b-fec9-47c8-870c-7a1b77534a0d")) {
            new GeneralListeners$1(this, e).runTaskLater((Plugin)Overflow.getInstance(), 70L);
        }
    }
    
    @EventHandler
    public void onBlockBreak(final BlockBreakEvent e) {
        if (e.getBlock().getType().isSolid() && e.isCancelled()) {
            final User user = Overflow.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId());
            if (user != null) {
                user.lastBlockBreakCancel = System.currentTimeMillis();
            }
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlace(final BlockPlaceEvent e) {
        final User user = Overflow.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId());
        if (user != null) {
            user.lastPlace = System.currentTimeMillis();
            if (e.isCancelled()) {
                user.lastCancelPlace = System.currentTimeMillis();
                user.setBlockcancelTicks(0);
            }
        }
    }
    
    @EventHandler
    public void onTeleport(final PlayerTeleportEvent e) {
        final User user = Overflow.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId());
        if (user != null) {
            user.CancelLocation = e.getTo();
            user.setTpLocation(e.getTo());
            if (System.currentTimeMillis() - user.getLastFlag() > 1000L) {
                if (e.getCause() != PlayerTeleportEvent.TeleportCause.UNKNOWN) {
                    user.teleportedNoMove = true;
                    user.setLastTeleport(System.currentTimeMillis());
                }
                else {
                    user.lastUnknownTeleport = System.currentTimeMillis();
                }
            }
        }
    }
    
    @EventHandler
    public void onGamemodeSwitch(final PlayerGameModeChangeEvent e) {
        final User user = Overflow.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId());
        if (user != null) {
            user.setLastGamemodeSwitch(System.currentTimeMillis());
        }
    }
    
    @EventHandler
    public void onFlyToggle(final PlayerToggleFlightEvent e) {
        final User user = Overflow.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId());
        if (user != null) {
            user.setLastFlightToggle(System.currentTimeMillis());
        }
    }
    
    @EventHandler
    public void onDamage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            final User user = Overflow.getInstance().getUserManager().getUser(e.getEntity().getUniqueId());
            if (user != null) {
                boolean set = false;
                switch (GeneralListeners$2.$SwitchMap$org$bukkit$event$entity$EntityDamageEvent$DamageCause[e.getCause().ordinal()]) {
                    case 1: {
                        set = true;
                        break;
                    }
                    case 2: {
                        set = true;
                        user.lastAttackByEntity = System.currentTimeMillis();
                        break;
                    }
                    case 3: {
                        user.lastExplode = System.currentTimeMillis();
                        if (!user.explode) {
                            user.explode = true;
                            break;
                        }
                        break;
                    }
                    case 4: {
                        user.lastBowDamage = System.currentTimeMillis();
                        break;
                    }
                }
                if (set) {
                    user.lastDamage = System.currentTimeMillis();
                }
            }
        }
    }
    
    @EventHandler
    public void onEntityDamage(final EntityDamageEvent e) {
        final User user = Overflow.getInstance().getUserManager().getUser(e.getEntity().getUniqueId());
        if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
            user.setVoidTicks(0);
        }
    }
    
    @EventHandler
    public void onEntityDamage(final PlayerRespawnEvent e) {
        final User user = Overflow.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId());
        user.setRespawnTicks(0);
    }
    
    @EventHandler
    public void onEntityDamage(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            final User user = Overflow.getInstance().getUserManager().getUser(e.getDamager().getUniqueId());
            if (user != null && e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                if (e.isCancelled()) {
                    final User user2 = user;
                    user2.cancelTicks += ((user.cancelTicks < 20) ? 1 : 0);
                }
                else {
                    final User user3 = user;
                    user3.cancelTicks -= ((user.cancelTicks > 0) ? 5 : 0);
                }
            }
        }
    }
    
    @EventHandler
    public void onRespawn(final PlayerRespawnEvent e) {
        final User user = Overflow.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId());
        if (user != null) {
            user.lastRespawn = System.currentTimeMillis();
            user.respawn = true;
        }
    }
    
    @EventHandler
    public void onGamemodeChange(final PlayerGameModeChangeEvent e) {
        final User user = Overflow.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId());
        if (user != null && !user.didSwitchGamemode) {
            user.didSwitchGamemode = true;
            user.lastGamemodeSwitch = System.currentTimeMillis();
        }
    }
    
    @EventHandler
    public void onPreCommand(final PlayerCommandPreprocessEvent e) {
        if (Overflow.getInstance().getConfigValues().isHider()) {
            if (e.getMessage().toLowerCase().startsWith("/ver") || e.getMessage().toLowerCase().startsWith("/version") || e.getMessage().toLowerCase().startsWith("/about")) {
                final String replace = e.getMessage().replace("/ver", "").replace("/version", "").replace("/about", "").replaceFirst(" ", "");
                if (replace.length() > 0 && !e.getPlayer().hasPermission("bukkit.command.version") && !e.getPlayer().isOp()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
                }
            }
            if (e.getMessage().toLowerCase().startsWith("/help") && Overflow.getInstance().getConfigValues().isBlockHelpCommand()) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(Overflow.getInstance().getConfigValues().getNoPermissionMessage());
            }
            if ((e.getMessage().toLowerCase().startsWith("/pl") || e.getMessage().toLowerCase().startsWith("/plugins")) && !e.getMessage().toLowerCase().startsWith("/plugman")) {
                if (e.getPlayer().hasPermission("bukkit.command.plugins")) {
                    boolean notFlase = false;
                    for (final String gay : e.getMessage().split(" ")) {
                        if (gay.toLowerCase().contains("plugins") || gay.toLowerCase().contains("/pl")) {
                            notFlase = true;
                        }
                    }
                    if (!notFlase) {
                        return;
                    }
                    e.setCancelled(true);
                    final String pluginMessage = "Plugins (%s)";
                    String append = "";
                    int plugins = 0;
                    for (final Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                        if (!plugin.getName().equalsIgnoreCase("OverflowLoader")) {
                            if (!plugin.getName().equalsIgnoreCase("OverflowDebugger")) {
                                String name = plugin.getName();
                                if (name.equalsIgnoreCase("Overflow")) {
                                    name = Overflow.getInstance().getConfigValues().getCustomAnticheatName();
                                }
                                append += ((plugins < 1) ? (ChatColor.GREEN + name + ChatColor.WHITE + ", ") : (ChatColor.WHITE + ((plugins > 1) ? ", " : "") + ChatColor.GREEN + name + ChatColor.WHITE));
                                ++plugins;
                            }
                        }
                    }
                    e.getPlayer().sendMessage(String.format(pluginMessage, String.valueOf(plugins)) + ": " + append);
                }
                else {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
                }
            }
        }
    }
    
    @EventHandler
    public void onVelocity(final PlayerVelocityEvent e) {
        final User user = Overflow.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId());
        final long now = System.currentTimeMillis();
        final double length = e.getVelocity().length();
        if (user != null) {
            user.lastServerVelocity = System.currentTimeMillis();
            try {
                user.velocityLengthSamples.removeIf(GeneralListeners::lambda$onVelocity$1);
                user.velocityLengthSamples.add(new VelocitySnapshot(length));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
