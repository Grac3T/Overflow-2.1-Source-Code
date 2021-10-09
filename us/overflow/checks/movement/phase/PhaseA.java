// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.phase;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.Overflow;
import us.overflow.events.impl.PlayerPhaseEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class PhaseA extends Check
{
    public PhaseA(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
        this.setDefaultBan(false);
        this.setIgnoreLagBacks(true);
    }
    
    @Listen
    public void onPhase(final PlayerPhaseEvent e) {
        final User user = Overflow.getInstance().getUserManager().getUser(e.getPlayer().getUniqueId());
        user.lastPhaseAFlag = System.currentTimeMillis();
        this.flag(user, new String[0]);
    }
}
