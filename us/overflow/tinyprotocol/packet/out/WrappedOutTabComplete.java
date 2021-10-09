// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import java.util.List;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedOutTabComplete extends NMSObject
{
    private static String packet;
    private String[] result;
    private static FieldAccessor<String[]> arrayAccessor;
    private static FieldAccessor<Object> suggestsAccessor;
    private static FieldAccessor<List> suggestionListAccessor;
    private static FieldAccessor<String> suggestionStringAccessor;
    
    public WrappedOutTabComplete(final Object object) {
        super(object);
    }
    
    public WrappedOutTabComplete(final Object object, final Player player) {
        super(object, player);
    }
    
    public WrappedOutTabComplete(final String[] result) {
        this.setPacketArg(WrappedOutTabComplete.packet, (Object)result);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_13)) {
            this.result = (String[])this.fetch((FieldAccessor)WrappedOutTabComplete.arrayAccessor);
        }
        else {
            final Object suggestions = this.fetch((FieldAccessor)WrappedOutTabComplete.suggestsAccessor);
            final List<Object> suggestsList = (List<Object>)WrappedOutTabComplete.suggestionListAccessor.get(suggestions);
            final List<String> strings = suggestsList.stream().map(object -> (String)WrappedOutTabComplete.suggestionStringAccessor.get(object)).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
            this.result = new String[strings.size()];
            for (int i = 0; i < strings.size(); ++i) {
                this.result[i] = strings.get(i);
            }
        }
    }
    
    public String[] getResult() {
        return this.result;
    }
    
    static {
        WrappedOutTabComplete.packet = "PacketPlayOutTabComplete";
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_13)) {
            WrappedOutTabComplete.arrayAccessor = (FieldAccessor<String[]>)fetchField(WrappedOutTabComplete.packet, (Class)String[].class, 0);
        }
        else {
            WrappedOutTabComplete.suggestsAccessor = (FieldAccessor<Object>)fetchField(WrappedOutTabComplete.packet, (Class)Object.class, 1);
            WrappedOutTabComplete.suggestionListAccessor = (FieldAccessor<List>)fetchField(WrappedOutTabComplete.packet, (Class)List.class, 0);
            WrappedOutTabComplete.suggestionStringAccessor = (FieldAccessor<String>)fetchField(WrappedOutTabComplete.packet, (Class)String.class, 0);
        }
    }
}
