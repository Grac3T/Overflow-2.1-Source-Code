// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet;

import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedField;

public class PacketField<T>
{
    private WrappedField field;
    private T value;
    
    public WrappedField getField() {
        return this.field;
    }
    
    public T getValue() {
        return (T)this.value;
    }
    
    public PacketField(final WrappedField field, final T value) {
        this.field = field;
        this.value = value;
    }
}
