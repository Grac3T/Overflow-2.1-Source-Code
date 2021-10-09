// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInAbilitiesPacket extends NMSObject
{
    private static final String packet = "PacketPlayInAbilities";
    private static FieldAccessor<Boolean> invulnerableField;
    private static FieldAccessor<Boolean> flyingField;
    private static FieldAccessor<Boolean> allowedFlightField;
    private static FieldAccessor<Boolean> creativeModeField;
    private static FieldAccessor<Float> flySpeedField;
    private static FieldAccessor<Float> walkSpeedField;
    private boolean invulnerable;
    private boolean flying;
    private boolean allowedFlight;
    private boolean creativeMode;
    private float flySpeed;
    private float walkSpeed;
    
    public WrappedInAbilitiesPacket(final Object object, final Player player) {
        super(object, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.invulnerable = (boolean)this.fetch((FieldAccessor)WrappedInAbilitiesPacket.invulnerableField);
        this.flying = (boolean)this.fetch((FieldAccessor)WrappedInAbilitiesPacket.flyingField);
        this.allowedFlight = (boolean)this.fetch((FieldAccessor)WrappedInAbilitiesPacket.allowedFlightField);
        this.creativeMode = (boolean)this.fetch((FieldAccessor)WrappedInAbilitiesPacket.creativeModeField);
        this.flySpeed = (float)this.fetch((FieldAccessor)WrappedInAbilitiesPacket.flySpeedField);
        this.walkSpeed = (float)this.fetch((FieldAccessor)WrappedInAbilitiesPacket.walkSpeedField);
    }
    
    public boolean isInvulnerable() {
        return this.invulnerable;
    }
    
    public boolean isFlying() {
        return this.flying;
    }
    
    public boolean isAllowedFlight() {
        return this.allowedFlight;
    }
    
    public boolean isCreativeMode() {
        return this.creativeMode;
    }
    
    public float getFlySpeed() {
        return this.flySpeed;
    }
    
    public float getWalkSpeed() {
        return this.walkSpeed;
    }
    
    static {
        WrappedInAbilitiesPacket.invulnerableField = (FieldAccessor<Boolean>)fetchField("PacketPlayInAbilities", (Class)Boolean.TYPE, 0);
        WrappedInAbilitiesPacket.flyingField = (FieldAccessor<Boolean>)fetchField("PacketPlayInAbilities", (Class)Boolean.TYPE, 1);
        WrappedInAbilitiesPacket.allowedFlightField = (FieldAccessor<Boolean>)fetchField("PacketPlayInAbilities", (Class)Boolean.TYPE, 2);
        WrappedInAbilitiesPacket.creativeModeField = (FieldAccessor<Boolean>)fetchField("PacketPlayInAbilities", (Class)Boolean.TYPE, 3);
        WrappedInAbilitiesPacket.flySpeedField = (FieldAccessor<Float>)fetchField("PacketPlayInAbilities", (Class)Float.TYPE, 0);
        WrappedInAbilitiesPacket.walkSpeedField = (FieldAccessor<Float>)fetchField("PacketPlayInAbilities", (Class)Float.TYPE, 1);
    }
}
