// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.hitbox;

import us.overflow.events.Listen;
import org.bukkit.Location;
import us.overflow.utils.location.CustomLocation;
import java.util.List;
import us.overflow.base.user.User;
import us.overflow.utils.other.RayTrace;
import us.overflow.utils.MathUtil;
import org.bukkit.entity.LivingEntity;
import us.overflow.utils.blockbox.BoundingBox;
import java.util.ArrayList;
import us.overflow.Overflow;
import us.overflow.tinyprotocol.packet.in.WrappedInUseEntityPacket;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class HitBoxA extends Check
{
    public HitBoxA(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        final User user = e.getUser();
        if (user != null) {
            if (user.isAllLagging() || user.mountedTicks > 0 || user.getPlayer().getVehicle() != null) {
                user.hitBoxAVerbose.setVerbose(0);
                return;
            }
            if ((e.getType().equalsIgnoreCase("PacketPlayInPositionLook") || e.getType().equalsIgnoreCase("PacketPlayInPosition") || e.getType().equalsIgnoreCase("PacketPlayInLook") || e.getType().equalsIgnoreCase("PacketPlayInFlying")) && user.lastEntityAttacked != null) {
                user.hitBoxPastLocations.addLocation(user.lastEntityAttacked.getLocation());
            }
            if (e.getType().equalsIgnoreCase("PacketPlayInUseEntity")) {
                final WrappedInUseEntityPacket wrappedInUseEntityPacket = new WrappedInUseEntityPacket(e.getPacket(), e.getPlayer());
                if (wrappedInUseEntityPacket.getAction() == WrappedInUseEntityPacket.EnumEntityUseAction.ATTACK && wrappedInUseEntityPacket.getEntity() != null && user.lastEntityAttacked != null && user.constantEntityTicks > 5) {
                    final User targetUser = Overflow.getInstance().getUserManager().getUser(user.lastEntityAttacked.getUniqueId());
                    if (targetUser != null && (targetUser.nextToWallTicks > 0 || targetUser.nextToWall)) {
                        user.hitBoxAVerbose.setVerbose(0);
                        return;
                    }
                    final List<BoundingBox> boundingBoxList = new ArrayList<BoundingBox>();
                    final List<CustomLocation> pastLocation = (List<CustomLocation>)user.hitBoxPastLocations.getEstimatedLocation(user.calculatedPing, Math.abs(user.calculatedPing - user.lastCalulated) + 200L);
                    if (pastLocation.size() > 0) {
                        if (user.cancelTicks > 3) {
                            user.hitBoxAVerbose.setVerbose(0);
                            return;
                        }
                        final Location loc2 = user.to.clone();
                        final LivingEntity livingEntity = (LivingEntity)wrappedInUseEntityPacket.getEntity();
                        pastLocation.forEach(loc1 -> boundingBoxList.add(MathUtil.getHitbox(livingEntity, loc1, user)));
                        loc2.setY(loc2.getY() + (user.player.isSneaking() ? 1.53 : user.player.getEyeHeight()));
                        final RayTrace trace = new RayTrace(loc2.toVector(), user.player.getEyeLocation().getDirection());
                        if (boundingBoxList.stream().noneMatch(box -> trace.intersects(box, box.getMinimum().distance(loc2.toVector()) + 1.0, 0.2)) && user.hitBoxAVerbose.flag(8, 10000L)) {
                            this.flag(user, new String[0]);
                        }
                    }
                }
            }
        }
    }
}
