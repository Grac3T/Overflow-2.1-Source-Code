// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.reach;

import us.overflow.events.Listen;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import us.overflow.base.user.User;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import us.overflow.utils.location.CustomLocation;
import java.util.List;
import us.overflow.Overflow;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.packet.in.WrappedInUseEntityPacket;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class ReachA extends Check
{
    public ReachA(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        final User user = e.getUser();
        if (user != null) {
            if (e.getType().equalsIgnoreCase("PacketPlayInUseEntity")) {
                final WrappedInUseEntityPacket useEntityPacket = new WrappedInUseEntityPacket(e.getPacket(), e.getPlayer());
                if (useEntityPacket.getAction() == WrappedInUseEntityPacket.EnumEntityUseAction.ATTACK && useEntityPacket.getEntity() instanceof Player) {
                    if (user.getPlayer().isFlying() || user.getPlayer().getAllowFlight() || user.getPlayer().getGameMode().equals((Object)GameMode.CREATIVE) || (Overflow.getInstance().getVersion() != Overflow.Version.V1_7 && user.getPlayer().getGameMode().equals((Object)GameMode.SPECTATOR))) {
                        user.reachAVerbose = 0;
                        return;
                    }
                    final User targetUser = Overflow.getInstance().getUserManager().getUser(useEntityPacket.getEntity().getUniqueId());
                    final boolean wall = targetUser != null && (targetUser.nextToWall || targetUser.nextToWallTicks > 0);
                    double maxDistance = 3.1500000953674316;
                    if (wall) {
                        maxDistance = 3.200000047683716;
                    }
                    final Location origin = user.player.getLocation();
                    final List<Vector> pastLocation = (List<Vector>)user.reachAPastLocations.getEstimatedLocation(user.calculatedPing, 150L).stream().map(CustomLocation::toVector).collect(Collectors.toList());
                    final float distance = (float)pastLocation.stream().mapToDouble(vec -> vec.clone().setY(0).distance(origin.toVector().clone().setY(0)) - 0.30000001192092896).min().orElse(0.0);
                    if (distance > maxDistance) {
                        if (user.reachAVerbose++ > 6) {
                            this.flag(user, new String[0]);
                        }
                    }
                    else {
                        final User user2 = user;
                        user2.reachAVerbose -= ((user.reachAVerbose > 0) ? 1 : 0);
                    }
                }
            }
            if ((e.getType().equalsIgnoreCase("PacketPlayInPositionLook") || e.getType().equalsIgnoreCase("PacketPlayInPosition") || e.getType().equalsIgnoreCase("PacketPlayInLook") || e.getType().equalsIgnoreCase("PacketPlayInFlying")) && user.lastEntityAttacked != null) {
                user.reachAPastLocations.addLocation(user.lastEntityAttacked.getLocation());
            }
        }
    }
}
