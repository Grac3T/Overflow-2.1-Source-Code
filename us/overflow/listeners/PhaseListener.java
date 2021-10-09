// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.listeners;

import org.bukkit.event.EventHandler;
import us.overflow.utils.event.CustomEventTrigger;
import me.jumba.addon.event.PhaseFlagEvent;
import org.bukkit.event.Listener;

public class PhaseListener implements Listener
{
    @EventHandler
    public void onPhase(final PhaseFlagEvent event) {
        CustomEventTrigger.callPhase(event.getPlayer());
    }
}
