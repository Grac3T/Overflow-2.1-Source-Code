// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet;

import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedField;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedClass;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedConstructor;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Collection;
import java.util.List;
import us.overflow.tinyprotocol.api.NMSObject;

public class GeneralWrapper extends NMSObject
{
    private List<PacketField<Object>> objects;
    private List<PacketField<Integer>> integers;
    private List<PacketField<Long>> longs;
    private List<PacketField<Float>> floats;
    private List<PacketField<Byte>> bytes;
    private List<PacketField<Double>> doubles;
    private List<PacketField<Short>> shorts;
    private List<PacketField<Collection>> collections;
    private List<PacketField<Map>> maps;
    private List<PacketField<Array>> arrays;
    private List<PacketField<Enum>> enums;
    private List<WrappedConstructor> constructors;
    private WrappedClass objectClass;
    
    public GeneralWrapper(final Object object, final Player player) {
        super(object, player);
        this.objects = new ArrayList<PacketField<Object>>();
        this.integers = new ArrayList<PacketField<Integer>>();
        this.longs = new ArrayList<PacketField<Long>>();
        this.floats = new ArrayList<PacketField<Float>>();
        this.bytes = new ArrayList<PacketField<Byte>>();
        this.doubles = new ArrayList<PacketField<Double>>();
        this.shorts = new ArrayList<PacketField<Short>>();
        this.collections = new ArrayList<PacketField<Collection>>();
        this.maps = new ArrayList<PacketField<Map>>();
        this.arrays = new ArrayList<PacketField<Array>>();
        this.enums = new ArrayList<PacketField<Enum>>();
        this.objectClass = new WrappedClass((Class)object.getClass());
        this.constructors = (List<WrappedConstructor>)this.objectClass.getConstructors();
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        us/overflow/tinyprotocol/packet/GeneralWrapper.objectClass:Lus/overflow/tinyprotocol/api/packets/reflections/types/WrappedClass;
        //     4: invokevirtual   us/overflow/tinyprotocol/api/packets/reflections/types/WrappedClass.getFields:()Ljava/util/List;
        //     7: aload_0         /* this */
        //     8: invokedynamic   BootstrapMethod #0, accept:(Lus/overflow/tinyprotocol/packet/GeneralWrapper;)Ljava/util/function/Consumer;
        //    13: invokeinterface java/util/List.forEach:(Ljava/util/function/Consumer;)V
        //    18: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:198)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public List<PacketField<Object>> getObjects() {
        return this.objects;
    }
    
    public List<PacketField<Integer>> getIntegers() {
        return this.integers;
    }
    
    public List<PacketField<Long>> getLongs() {
        return this.longs;
    }
    
    public List<PacketField<Float>> getFloats() {
        return this.floats;
    }
    
    public List<PacketField<Byte>> getBytes() {
        return this.bytes;
    }
    
    public List<PacketField<Double>> getDoubles() {
        return this.doubles;
    }
    
    public List<PacketField<Short>> getShorts() {
        return this.shorts;
    }
    
    public List<PacketField<Collection>> getCollections() {
        return this.collections;
    }
    
    public List<PacketField<Map>> getMaps() {
        return this.maps;
    }
    
    public List<PacketField<Array>> getArrays() {
        return this.arrays;
    }
    
    public List<PacketField<Enum>> getEnums() {
        return this.enums;
    }
    
    public List<WrappedConstructor> getConstructors() {
        return this.constructors;
    }
    
    public WrappedClass getObjectClass() {
        return this.objectClass;
    }
}
