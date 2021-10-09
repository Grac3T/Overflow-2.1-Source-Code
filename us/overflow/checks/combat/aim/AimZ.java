// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimZ extends Check
{
    public AimZ(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (this.isPacketMovement(e.getType())) {
            final User user = e.getUser();
            if (user != null && user.setClientSens) {
                if (System.currentTimeMillis() - user.lastAttackPacket < 1000L) {
                    final double sensitivity = user.clientSens;
                    final double pitch = e.getTo().getPitch();
                    final double o = this.modulusRotation(sensitivity, pitch);
                    final int l = MathUtil.getLength(o);
                    if (Math.abs(pitch) != 90.0 && ((o > 0.0 && l > 0 && l < 8) || o == 0.0)) {
                        final User user2 = user;
                        user2.aimLThreshold += 20.0;
                    }
                    if (user.aimLThreshold > 0.0) {
                        final User user3 = user;
                        --user3.aimLThreshold;
                        this.flag(user, new String[0]);
                    }
                }
                else {
                    user.aimLThreshold = 0.0;
                }
            }
        }
    }
    
    private double modulusRotation(final double s, final double pitch) {
        final float f = (float)(s * 0.6000000238418579 + 0.20000000298023224);
        final float f2 = f * f * f * 1.2f;
        return pitch % f2;
    }
}
