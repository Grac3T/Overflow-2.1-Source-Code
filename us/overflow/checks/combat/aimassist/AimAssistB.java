// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aimassist;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.other.TimeUtils;
import us.overflow.tinyprotocol.packet.in.WrappedInUseEntityPacket;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimAssistB extends Check
{
    public AimAssistB(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getType().equalsIgnoreCase("PacketPlayInUseEntity")) {
            final WrappedInUseEntityPacket wrappedInUseEntityPacket = new WrappedInUseEntityPacket(e.getPacket(), e.getPlayer());
            if (wrappedInUseEntityPacket.getEntity() != null && wrappedInUseEntityPacket.getAction() == WrappedInUseEntityPacket.EnumEntityUseAction.ATTACK) {
                final User user = e.getUser();
                if (user.isLagging()) {
                    return;
                }
                final float pitch = user.player.getLocation().getPitch();
                final float yaw = user.player.getLocation().getYaw();
                final float pitchChange = Math.abs(pitch - user.aimAssistsFPitch);
                final float yawChange = Math.abs(yaw - user.aimAssistsFYaw);
                final float pitchDifference = Math.abs(user.lastAimAssistFPrevPitch - pitchChange);
                final float yawDifference = Math.abs(user.lastAimAssistFPrevYaw - yawChange);
                if (yawChange > yawDifference && yawDifference > 0.3 && pitchChange > 0.0f && pitchChange <= pitchDifference && pitchDifference < 0.1) {
                    if (!user.setAimAssistF) {
                        user.setAimAssistF = true;
                        user.lastAimAssistF = System.currentTimeMillis();
                    }
                    if (user.aimAssistFVerbose++ > 1) {
                        this.flag(user, new String[0]);
                    }
                }
                if (user.setAimAssistF && TimeUtils.secondsFromLong(user.lastAimAssistF) > 20L) {
                    user.setAimAssistF = false;
                    user.aimAssistFVerbose = 0;
                }
                user.aimAssistsFPitch = pitch;
                user.aimAssistsFYaw = yaw;
                user.lastAimAssistFPrevPitch = pitchChange;
                user.lastAimAssistFPrevYaw = yawChange;
            }
        }
    }
}
