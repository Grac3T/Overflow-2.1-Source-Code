// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInWindowClickPacket extends NMSObject
{
    private static final String packet = "PacketPlayInWindowClick";
    private static FieldAccessor<Integer> fieldId;
    private static FieldAccessor<Integer> fieldSlot;
    private static FieldAccessor<Integer> fieldButton;
    private static FieldAccessor<Short> fieldAction;
    private static FieldAccessor<Object> fieldItemStack;
    private int id;
    private short slot;
    private byte button;
    private short counter;
    private ClickType action;
    private ItemStack item;
    private byte mode;
    
    public WrappedInWindowClickPacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.id = (int)this.fetch(WrappedInWindowClickPacket.fieldId);
        this.slot = ((Integer)this.fetch(WrappedInWindowClickPacket.fieldSlot)).shortValue();
        final byte button = ((Integer)this.fetch(WrappedInWindowClickPacket.fieldButton)).byteValue();
        this.counter = (short)this.fetch(WrappedInWindowClickPacket.fieldAction);
        this.item = toBukkitStack(this.fetch(WrappedInWindowClickPacket.fieldItemStack));
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_9)) {
            final FieldAccessor<Integer> fieldShift = (FieldAccessor<Integer>)fetchField("PacketPlayInWindowClick", (Class)Integer.TYPE, 3);
            this.mode = ((Integer)this.fetch((FieldAccessor)fieldShift)).byteValue();
        }
        else {
            final FieldAccessor<Enum> fieldShift2 = (FieldAccessor<Enum>)fetchField("PacketPlayInWindowClick", (Class)Enum.class, 0);
            this.mode = (byte)((Enum)this.fetch((FieldAccessor)fieldShift2)).ordinal();
        }
        if (this.slot == -1) {
            this.action = ((button == 0) ? ClickType.WINDOW_BORDER_LEFT : ClickType.WINDOW_BORDER_RIGHT);
        }
        else if (this.mode == 0) {
            if (button == 0) {
                this.action = ClickType.LEFT;
            }
            else if (button == 1) {
                this.action = ClickType.RIGHT;
            }
        }
        else if (this.mode == 1) {
            if (button == 0) {
                this.action = ClickType.SHIFT_LEFT;
            }
            else if (button == 1) {
                this.action = ClickType.SHIFT_RIGHT;
            }
        }
        else if (this.mode == 2) {
            if (button >= 0 && button < 9) {
                this.action = ClickType.NUMBER_KEY;
            }
        }
        else if (this.mode == 3) {
            if (button == 2) {
                this.action = ClickType.MIDDLE;
            }
            else {
                this.action = ClickType.UNKNOWN;
            }
        }
        else if (this.mode == 4) {
            if (this.slot >= 0) {
                if (button == 0) {
                    this.action = ClickType.DROP;
                }
                else if (button == 1) {
                    this.action = ClickType.CONTROL_DROP;
                }
            }
            else {
                this.action = ClickType.LEFT;
                if (button == 1) {
                    this.action = ClickType.RIGHT;
                }
            }
        }
        else if (this.mode == 5) {
            this.action = ClickType.DRAG;
        }
        else if (this.mode == 6) {
            this.action = ClickType.DOUBLE_CLICK;
        }
    }
    
    public int getId() {
        return this.id;
    }
    
    public short getSlot() {
        return this.slot;
    }
    
    public byte getButton() {
        return this.button;
    }
    
    public short getCounter() {
        return this.counter;
    }
    
    public ClickType getAction() {
        return this.action;
    }
    
    public ItemStack getItem() {
        return this.item;
    }
    
    public byte getMode() {
        return this.mode;
    }
    
    static {
        WrappedInWindowClickPacket.fieldId = fetchField("PacketPlayInWindowClick", (Class)Integer.TYPE, 0);
        WrappedInWindowClickPacket.fieldSlot = fetchField("PacketPlayInWindowClick", (Class)Integer.TYPE, 1);
        WrappedInWindowClickPacket.fieldButton = fetchField("PacketPlayInWindowClick", (Class)Integer.TYPE, 2);
        WrappedInWindowClickPacket.fieldAction = fetchField("PacketPlayInWindowClick", (Class)Short.TYPE, 0);
        WrappedInWindowClickPacket.fieldItemStack = fetchField("PacketPlayInWindowClick", NMSObject.Type.ITEMSTACK, 0);
    }
    
    public enum ClickType
    {
        public static final ClickType LEFT;
        public static final ClickType SHIFT_LEFT;
        public static final ClickType RIGHT;
        public static final ClickType SHIFT_RIGHT;
        public static final ClickType WINDOW_BORDER_LEFT;
        public static final ClickType WINDOW_BORDER_RIGHT;
        public static final ClickType MIDDLE;
        public static final ClickType NUMBER_KEY;
        public static final ClickType DOUBLE_CLICK;
        public static final ClickType DROP;
        public static final ClickType CONTROL_DROP;
        public static final ClickType CREATIVE;
        public static final ClickType DRAG;
        public static final ClickType UNKNOWN;
        
        public static ClickType valueOf(final String name) {
            return Enum.valueOf(ClickType.class, name);
        }
        
        public boolean isKeyboardClick() {
            return this == ClickType.NUMBER_KEY || this == ClickType.DROP || this == ClickType.CONTROL_DROP;
        }
        
        public boolean isCreativeAction() {
            return this == ClickType.MIDDLE || this == ClickType.CREATIVE;
        }
        
        public boolean isRightClick() {
            return this == ClickType.RIGHT || this == ClickType.SHIFT_RIGHT;
        }
        
        public boolean isLeftClick() {
            return this == ClickType.LEFT || this == ClickType.SHIFT_LEFT || this == ClickType.DOUBLE_CLICK || this == ClickType.CREATIVE;
        }
        
        public boolean isShiftClick() {
            return this == ClickType.SHIFT_LEFT || this == ClickType.SHIFT_RIGHT || this == ClickType.CONTROL_DROP;
        }
        
        static {
            ClickType.LEFT = new ClickType("LEFT", 0);
            ClickType.SHIFT_LEFT = new ClickType("SHIFT_LEFT", 1);
            ClickType.RIGHT = new ClickType("RIGHT", 2);
            ClickType.SHIFT_RIGHT = new ClickType("SHIFT_RIGHT", 3);
            ClickType.WINDOW_BORDER_LEFT = new ClickType("WINDOW_BORDER_LEFT", 4);
            ClickType.WINDOW_BORDER_RIGHT = new ClickType("WINDOW_BORDER_RIGHT", 5);
            ClickType.MIDDLE = new ClickType("MIDDLE", 6);
            ClickType.NUMBER_KEY = new ClickType("NUMBER_KEY", 7);
            ClickType.DOUBLE_CLICK = new ClickType("DOUBLE_CLICK", 8);
            ClickType.DROP = new ClickType("DROP", 9);
            ClickType.CONTROL_DROP = new ClickType("CONTROL_DROP", 10);
            ClickType.CREATIVE = new ClickType("CREATIVE", 11);
            ClickType.DRAG = new ClickType("DRAG", 12);
            ClickType.UNKNOWN = new ClickType("UNKNOWN", 13);
            ClickType.$VALUES = new ClickType[] { ClickType.LEFT, ClickType.SHIFT_LEFT, ClickType.RIGHT, ClickType.SHIFT_RIGHT, ClickType.WINDOW_BORDER_LEFT, ClickType.WINDOW_BORDER_RIGHT, ClickType.MIDDLE, ClickType.NUMBER_KEY, ClickType.DOUBLE_CLICK, ClickType.DROP, ClickType.CONTROL_DROP, ClickType.CREATIVE, ClickType.DRAG, ClickType.UNKNOWN };
        }
    }
}
