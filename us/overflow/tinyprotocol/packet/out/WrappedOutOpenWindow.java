// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.packet.types.WrappedChatMessage;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedOutOpenWindow extends NMSObject
{
    private static String packet;
    private static FieldAccessor<Integer> idField;
    private static FieldAccessor<String> nameField;
    private static FieldAccessor<Object> chatCompField;
    private static FieldAccessor<Integer> inventorySize;
    private int id;
    private String name;
    private WrappedChatMessage chatComponent;
    private int size;
    
    public WrappedOutOpenWindow(final Object object, final Player player) {
        super(object, player);
    }
    
    public WrappedOutOpenWindow(final int id, final String name, final WrappedChatMessage msg, final int size) {
        this.setPacket(WrappedOutOpenWindow.packet, new Object[] { id, name, msg.getObject(), size });
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.id = (int)this.fetch((FieldAccessor)WrappedOutOpenWindow.idField);
        this.name = (String)this.fetch((FieldAccessor)WrappedOutOpenWindow.nameField);
        this.chatComponent = new WrappedChatMessage(this.fetch((FieldAccessor)WrappedOutOpenWindow.chatCompField));
        this.size = (int)this.fetch((FieldAccessor)WrappedOutOpenWindow.inventorySize);
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public WrappedChatMessage getChatComponent() {
        return this.chatComponent;
    }
    
    public int getSize() {
        return this.size;
    }
    
    static {
        WrappedOutOpenWindow.packet = "PacketPlayOutOpenWindow";
        WrappedOutOpenWindow.idField = (FieldAccessor<Integer>)fetchField(WrappedOutOpenWindow.packet, (Class)Integer.TYPE, 0);
        WrappedOutOpenWindow.nameField = (FieldAccessor<String>)fetchField(WrappedOutOpenWindow.packet, (Class)String.class, 0);
        WrappedOutOpenWindow.chatCompField = (FieldAccessor<Object>)fetchField(WrappedOutOpenWindow.packet, (Class)Object.class, 2);
        WrappedOutOpenWindow.inventorySize = (FieldAccessor<Integer>)fetchField(WrappedOutOpenWindow.packet, (Class)Integer.TYPE, 1);
    }
}
