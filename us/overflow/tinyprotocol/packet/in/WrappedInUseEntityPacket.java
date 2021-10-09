// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInUseEntityPacket extends NMSObject
{
    private static String packet;
    private static FieldAccessor<Integer> fieldId;
    private static FieldAccessor<Enum> fieldAction;
    private int id;
    private EnumEntityUseAction action;
    private Entity entity;
    
    public WrappedInUseEntityPacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.id = Objects.requireNonNull(this.fetch(WrappedInUseEntityPacket.fieldId));
        final Enum fieldAct = Objects.nonNull(this.fetch(WrappedInUseEntityPacket.fieldAction)) ? ((Enum)this.fetch(WrappedInUseEntityPacket.fieldAction)) : null;
        this.action = ((fieldAct == null) ? EnumEntityUseAction.INTERACT_AT : EnumEntityUseAction.valueOf(fieldAct.name()));
        final List<Entity> entities = (List<Entity>)player.getWorld().getEntities();
        for (final Entity ent : entities) {
            if (this.id == ent.getEntityId()) {
                this.entity = ent;
                break;
            }
        }
    }
    
    public int getId() {
        return this.id;
    }
    
    public EnumEntityUseAction getAction() {
        return this.action;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    static {
        WrappedInUseEntityPacket.packet = "PacketPlayInUseEntity";
        WrappedInUseEntityPacket.fieldId = fetchField(WrappedInUseEntityPacket.packet, (Class)Integer.TYPE, 0);
        WrappedInUseEntityPacket.fieldAction = fetchField(WrappedInUseEntityPacket.packet, (Class)Enum.class, 0);
    }
    
    public enum EnumEntityUseAction
    {
        public static final EnumEntityUseAction INTERACT;
        public static final EnumEntityUseAction ATTACK;
        public static final EnumEntityUseAction INTERACT_AT;
        private String name;
        
        public static EnumEntityUseAction valueOf(final String name) {
            return Enum.valueOf(EnumEntityUseAction.class, name);
        }
        
        private EnumEntityUseAction(final String name) {
            this.name = name;
        }
        
        public String getName() {
            return this.name;
        }
        
        static {
            EnumEntityUseAction.INTERACT = new EnumEntityUseAction("INTERACT", 0, "INTERACT");
            EnumEntityUseAction.ATTACK = new EnumEntityUseAction("ATTACK", 1, "ATTACK");
            EnumEntityUseAction.INTERACT_AT = new EnumEntityUseAction("INTERACT_AT", 2, "INTERACT_AT");
            EnumEntityUseAction.$VALUES = new EnumEntityUseAction[] { EnumEntityUseAction.INTERACT, EnumEntityUseAction.ATTACK, EnumEntityUseAction.INTERACT_AT };
        }
    }
}
