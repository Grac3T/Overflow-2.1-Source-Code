// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.event;

import us.overflow.events.OverflowEvent;
import us.overflow.events.impl.PlayerPhaseEvent;
import us.overflow.Overflow;
import org.bukkit.entity.Player;

public class CustomEventTrigger
{
    public static void callPhase(final Player player) {
        Overflow.getInstance().getEventManager().callEvent(new PlayerPhaseEvent(player));
    }
}
