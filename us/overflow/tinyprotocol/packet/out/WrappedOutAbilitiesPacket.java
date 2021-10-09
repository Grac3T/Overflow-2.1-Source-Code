// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import us.overflow.tinyprotocol.api.packets.reflections.Reflections;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedField;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedClass;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedOutAbilitiesPacket extends NMSObject
{
    private static final String packet = "PacketPlayOutAbilities";
    private static FieldAccessor<Boolean> invulnerableField;
    private static FieldAccessor<Boolean> flyingField;
    private static FieldAccessor<Boolean> allowedFlightField;
    private static FieldAccessor<Boolean> creativeModeField;
    private static FieldAccessor<Float> flySpeedField;
    private static FieldAccessor<Float> walkSpeedField;
    private static WrappedClass abilitiesClass;
    private static WrappedField invulnerableAcc;
    private static WrappedField flyingAcc;
    private static WrappedField allowedFlightAcc;
    private static WrappedField creativeModeAcc;
    private static WrappedField flySpeedAcc;
    private static WrappedField walkSpeedAcc;
    private boolean invulnerable;
    private boolean flying;
    private boolean allowedFlight;
    private boolean creativeMode;
    private float flySpeed;
    private float walkSpeed;
    
    public WrappedOutAbilitiesPacket(final Object object, final Player player) {
        super(object, player);
    }
    
    public WrappedOutAbilitiesPacket(final boolean invulernable, final boolean flying, final boolean allowedFlight, final boolean creativeMode, final float flySpeed, final float walkSpeed) {
        final Object abilities = WrappedOutAbilitiesPacket.abilitiesClass.getConstructorAtIndex(0).newInstance();
        WrappedOutAbilitiesPacket.invulnerableAcc.set(abilities, (Object)invulernable);
        WrappedOutAbilitiesPacket.flyingAcc.set(abilities, (Object)flying);
        WrappedOutAbilitiesPacket.allowedFlightAcc.set(abilities, (Object)allowedFlight);
        WrappedOutAbilitiesPacket.creativeModeAcc.set(abilities, (Object)creativeMode);
        WrappedOutAbilitiesPacket.flySpeedAcc.set(abilities, (Object)flySpeed);
        WrappedOutAbilitiesPacket.walkSpeedAcc.set(abilities, (Object)walkSpeed);
        this.setObject(Reflections.getNMSClass("PacketPlayOutAbilities").getConstructor(new Class[] { WrappedOutAbilitiesPacket.abilitiesClass.getParent() }).newInstance(new Object[] { abilities }));
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.invulnerable = (boolean)this.fetch((FieldAccessor)WrappedOutAbilitiesPacket.invulnerableField);
        this.flying = (boolean)this.fetch((FieldAccessor)WrappedOutAbilitiesPacket.flyingField);
        this.allowedFlight = (boolean)this.fetch((FieldAccessor)WrappedOutAbilitiesPacket.allowedFlightField);
        this.creativeMode = (boolean)this.fetch((FieldAccessor)WrappedOutAbilitiesPacket.creativeModeField);
        this.flySpeed = (float)this.fetch((FieldAccessor)WrappedOutAbilitiesPacket.flySpeedField);
        this.walkSpeed = (float)this.fetch((FieldAccessor)WrappedOutAbilitiesPacket.walkSpeedField);
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
        WrappedOutAbilitiesPacket.invulnerableField = (FieldAccessor<Boolean>)fetchField("PacketPlayOutAbilities", (Class)Boolean.TYPE, 0);
        WrappedOutAbilitiesPacket.flyingField = (FieldAccessor<Boolean>)fetchField("PacketPlayOutAbilities", (Class)Boolean.TYPE, 1);
        WrappedOutAbilitiesPacket.allowedFlightField = (FieldAccessor<Boolean>)fetchField("PacketPlayOutAbilities", (Class)Boolean.TYPE, 2);
        WrappedOutAbilitiesPacket.creativeModeField = (FieldAccessor<Boolean>)fetchField("PacketPlayOutAbilities", (Class)Boolean.TYPE, 3);
        WrappedOutAbilitiesPacket.flySpeedField = (FieldAccessor<Float>)fetchField("PacketPlayOutAbilities", (Class)Float.TYPE, 0);
        WrappedOutAbilitiesPacket.walkSpeedField = (FieldAccessor<Float>)fetchField("PacketPlayOutAbilities", (Class)Float.TYPE, 1);
        WrappedOutAbilitiesPacket.abilitiesClass = Reflections.getNMSClass("PlayerAbilities");
        WrappedOutAbilitiesPacket.invulnerableAcc = WrappedOutAbilitiesPacket.abilitiesClass.getFieldByType((Class)Boolean.TYPE, 0);
        WrappedOutAbilitiesPacket.flyingAcc = WrappedOutAbilitiesPacket.abilitiesClass.getFieldByType((Class)Boolean.TYPE, 1);
        WrappedOutAbilitiesPacket.allowedFlightAcc = WrappedOutAbilitiesPacket.abilitiesClass.getFieldByType((Class)Boolean.TYPE, 2);
        WrappedOutAbilitiesPacket.creativeModeAcc = WrappedOutAbilitiesPacket.abilitiesClass.getFieldByType((Class)Boolean.TYPE, 3);
        WrappedOutAbilitiesPacket.flySpeedAcc = WrappedOutAbilitiesPacket.abilitiesClass.getFieldByType((Class)Float.TYPE, 0);
        WrappedOutAbilitiesPacket.walkSpeedAcc = WrappedOutAbilitiesPacket.abilitiesClass.getFieldByType((Class)Float.TYPE, 1);
    }
}
