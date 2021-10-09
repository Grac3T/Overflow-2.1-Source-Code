// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.speed;

import us.overflow.events.Listen;
import org.bukkit.block.Block;
import us.overflow.utils.location.CustomLocation;
import us.overflow.base.user.User;
import org.bukkit.Material;
import us.overflow.utils.block.BlockUtil;
import us.overflow.utils.other.TimeUtils;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class SpeedB extends Check
{
    public SpeedB(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (!this.isPacketMovement(e.getType())) {
            return;
        }
        final User user = e.getUser();
        if (user != null) {
            if (user.getBoatTicks() > 0 || user.generalCancel() || TimeUtils.secondsFromLong(user.getLastTeleport()) < 3L || user.getMountedTicks() > 0 || user.didSwitchGamemode || user.stairTicks > 0 || user.slabTicks > 0) {
                user.speedBVerbose = 0;
                return;
            }
            final CustomLocation from = user.newTo;
            final CustomLocation to = user.newFrom;
            final double distX = to.getX() - from.getX();
            final double distZ = to.getZ() - from.getZ();
            final double dist = distX * distX + distZ * distZ;
            final double lastDist = user.lastSpeedBDistance;
            user.lastSpeedBDistance = dist;
            final boolean onGround = user.isOnGround();
            final boolean lastOnGround = user.speedBLastGround;
            final double friction = 0.9100000262260437;
            final double shiftDist = lastDist * friction;
            final double equalness = dist - shiftDist;
            final double fix = equalness * 138.0;
            final Block block = BlockUtil.getBlock(user.newTo.toLocation(user.getPlayer().getWorld()).clone().add(0.0, -0.1, 0.0));
            final boolean ground = block != null && block.getType() != Material.AIR && block.getType().isSolid();
            if (fix >= 1.04 && !onGround && !lastOnGround && e.getTo().getY() != e.getFrom().getY() && !ground && !user.speedBLastGroundLocation) {
                if (user.speedBVerbose++ > 1) {
                    this.flag(user, new String[0]);
                }
            }
            else {
                final User user2 = user;
                user2.speedBVerbose -= ((user.speedBVerbose > 0) ? 1 : 0);
            }
            user.speedBLastGroundLocation = ground;
            user.speedBLastGround = onGround;
        }
    }
}
