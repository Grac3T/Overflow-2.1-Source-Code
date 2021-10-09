// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.types;

import us.overflow.utils.blockbox.ReflectionUtil;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedChatMessage extends NMSObject
{
    private static String type;
    private String chatMessage;
    private Object[] objects;
    private static FieldAccessor<String> messageField;
    private static FieldAccessor<Object[]> objectsField;
    
    public WrappedChatMessage(final String chatMessage, final Object... object) {
        this.chatMessage = chatMessage;
        this.objects = object;
    }
    
    public WrappedChatMessage(final String chatMessage) {
        this(chatMessage, new Object[0]);
    }
    
    public void setPacket(final String packet, final Object... args) {
        final Class<?> chatMsgClass = (Class<?>)ReflectionUtil.getClass(WrappedChatMessage.type);
        final Object o = ReflectionUtil.newInstance((Class)chatMsgClass, new Object[] { packet, args });
        this.setObject(o);
    }
    
    public WrappedChatMessage(final Object object) {
        super(object);
        this.chatMessage = (String)this.fetch(WrappedChatMessage.messageField);
        this.objects = (Object[])this.fetch(WrappedChatMessage.objectsField);
    }
    
    public String getChatMessage() {
        return this.chatMessage;
    }
    
    public Object[] getObjects() {
        return this.objects;
    }
    
    static {
        WrappedChatMessage.type = NMSObject.Type.CHATMESSAGE;
        WrappedChatMessage.messageField = fetchField(WrappedChatMessage.type, (Class)String.class, 0);
        WrappedChatMessage.objectsField = fetchField(WrappedChatMessage.type, (Class)Object[].class, 0);
    }
}
