// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.types;

import us.overflow.tinyprotocol.api.packets.reflections.Reflections;
import java.util.Arrays;

public enum WrappedEnumParticle
{
    public static final WrappedEnumParticle EXPLOSION_NORMAL;
    public static final WrappedEnumParticle EXPLOSION_LARGE;
    public static final WrappedEnumParticle EXPLOSION_HUGE;
    public static final WrappedEnumParticle FIREWORKS_SPARK;
    public static final WrappedEnumParticle WATER_BUBBLE;
    public static final WrappedEnumParticle WATER_SPLASH;
    public static final WrappedEnumParticle WATER_WAKE;
    public static final WrappedEnumParticle SUSPENDED;
    public static final WrappedEnumParticle SUSPENDED_DEPTH;
    public static final WrappedEnumParticle CRIT;
    public static final WrappedEnumParticle CRIT_MAGIC;
    public static final WrappedEnumParticle SMOKE_NORMAL;
    public static final WrappedEnumParticle SMOKE_LARGE;
    public static final WrappedEnumParticle SPELL;
    public static final WrappedEnumParticle SPELL_INSTANT;
    public static final WrappedEnumParticle SPELL_MOB;
    public static final WrappedEnumParticle SPELL_MOB_AMBIENT;
    public static final WrappedEnumParticle SPELL_WITCH;
    public static final WrappedEnumParticle DRIP_WATER;
    public static final WrappedEnumParticle DRIP_LAVA;
    public static final WrappedEnumParticle VILLAGER_ANGRY;
    public static final WrappedEnumParticle VILLAGER_HAPPY;
    public static final WrappedEnumParticle TOWN_AURA;
    public static final WrappedEnumParticle NOTE;
    public static final WrappedEnumParticle PORTAL;
    public static final WrappedEnumParticle ENCHANTMENT_TABLE;
    public static final WrappedEnumParticle FLAME;
    public static final WrappedEnumParticle LAVA;
    public static final WrappedEnumParticle FOOTSTEP;
    public static final WrappedEnumParticle CLOUD;
    public static final WrappedEnumParticle REDSTONE;
    public static final WrappedEnumParticle SNOWBALL;
    public static final WrappedEnumParticle SNOW_SHOVEL;
    public static final WrappedEnumParticle SLIME;
    public static final WrappedEnumParticle HEART;
    public static final WrappedEnumParticle BARRIER;
    public static final WrappedEnumParticle ITEM_CRACK;
    public static final WrappedEnumParticle BLOCK_CRACK;
    public static final WrappedEnumParticle BLOCK_DUST;
    public static final WrappedEnumParticle WATER_DROP;
    public static final WrappedEnumParticle ITEM_TAKE;
    public static final WrappedEnumParticle MOB_APPEARANCE;
    private String name;
    private int value;
    private boolean something;
    private int data;
    
    public static WrappedEnumParticle valueOf(final String name) {
        return Enum.valueOf(WrappedEnumParticle.class, name);
    }
    
    private WrappedEnumParticle(final String name, final int value, final boolean something) {
        this.name = name;
        this.value = value;
        this.something = something;
    }
    
    private WrappedEnumParticle(final String name, final int value, final boolean something, final int data) {
        this.name = name;
        this.value = value;
        this.something = something;
        this.data = data;
    }
    
    public static WrappedEnumParticle getByName(final String name) {
        return Arrays.stream(values()).filter(WrappedEnumParticle::lambda$getByName$0).findFirst().orElse(null);
    }
    
    public Object toNMS() {
        return Reflections.getNMSClass("EnumParticle").getEnum(getByName(this.name).name());
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public boolean isSomething() {
        return this.something;
    }
    
    public int getData() {
        return this.data;
    }
    
    static {
        WrappedEnumParticle.EXPLOSION_NORMAL = new WrappedEnumParticle("EXPLOSION_NORMAL", 0, "explode", 0, true);
        WrappedEnumParticle.EXPLOSION_LARGE = new WrappedEnumParticle("EXPLOSION_LARGE", 1, "largeexplode", 1, true);
        WrappedEnumParticle.EXPLOSION_HUGE = new WrappedEnumParticle("EXPLOSION_HUGE", 2, "hugeexplosion", 2, true);
        WrappedEnumParticle.FIREWORKS_SPARK = new WrappedEnumParticle("FIREWORKS_SPARK", 3, "fireworksSpark", 3, false);
        WrappedEnumParticle.WATER_BUBBLE = new WrappedEnumParticle("WATER_BUBBLE", 4, "bubble", 4, false);
        WrappedEnumParticle.WATER_SPLASH = new WrappedEnumParticle("WATER_SPLASH", 5, "splash", 5, false);
        WrappedEnumParticle.WATER_WAKE = new WrappedEnumParticle("WATER_WAKE", 6, "wake", 6, false);
        WrappedEnumParticle.SUSPENDED = new WrappedEnumParticle("SUSPENDED", 7, "suspended", 7, false);
        WrappedEnumParticle.SUSPENDED_DEPTH = new WrappedEnumParticle("SUSPENDED_DEPTH", 8, "depthsuspend", 8, false);
        WrappedEnumParticle.CRIT = new WrappedEnumParticle("CRIT", 9, "crit", 9, false);
        WrappedEnumParticle.CRIT_MAGIC = new WrappedEnumParticle("CRIT_MAGIC", 10, "magicCrit", 10, false);
        WrappedEnumParticle.SMOKE_NORMAL = new WrappedEnumParticle("SMOKE_NORMAL", 11, "smoke", 11, false);
        WrappedEnumParticle.SMOKE_LARGE = new WrappedEnumParticle("SMOKE_LARGE", 12, "largesmoke", 12, false);
        WrappedEnumParticle.SPELL = new WrappedEnumParticle("SPELL", 13, "spell", 13, false);
        WrappedEnumParticle.SPELL_INSTANT = new WrappedEnumParticle("SPELL_INSTANT", 14, "instantSpell", 14, false);
        WrappedEnumParticle.SPELL_MOB = new WrappedEnumParticle("SPELL_MOB", 15, "mobSpell", 15, false);
        WrappedEnumParticle.SPELL_MOB_AMBIENT = new WrappedEnumParticle("SPELL_MOB_AMBIENT", 16, "mobSpellAmbient", 16, false);
        WrappedEnumParticle.SPELL_WITCH = new WrappedEnumParticle("SPELL_WITCH", 17, "witchMagic", 17, false);
        WrappedEnumParticle.DRIP_WATER = new WrappedEnumParticle("DRIP_WATER", 18, "dripWater", 18, false);
        WrappedEnumParticle.DRIP_LAVA = new WrappedEnumParticle("DRIP_LAVA", 19, "dripLava", 19, false);
        WrappedEnumParticle.VILLAGER_ANGRY = new WrappedEnumParticle("VILLAGER_ANGRY", 20, "angryVillager", 20, false);
        WrappedEnumParticle.VILLAGER_HAPPY = new WrappedEnumParticle("VILLAGER_HAPPY", 21, "happyVillager", 21, false);
        WrappedEnumParticle.TOWN_AURA = new WrappedEnumParticle("TOWN_AURA", 22, "townaura", 22, false);
        WrappedEnumParticle.NOTE = new WrappedEnumParticle("NOTE", 23, "note", 23, false);
        WrappedEnumParticle.PORTAL = new WrappedEnumParticle("PORTAL", 24, "portal", 24, false);
        WrappedEnumParticle.ENCHANTMENT_TABLE = new WrappedEnumParticle("ENCHANTMENT_TABLE", 25, "enchantmenttable", 25, false);
        WrappedEnumParticle.FLAME = new WrappedEnumParticle("FLAME", 26, "flame", 26, false);
        WrappedEnumParticle.LAVA = new WrappedEnumParticle("LAVA", 27, "lava", 27, false);
        WrappedEnumParticle.FOOTSTEP = new WrappedEnumParticle("FOOTSTEP", 28, "footstep", 28, false);
        WrappedEnumParticle.CLOUD = new WrappedEnumParticle("CLOUD", 29, "cloud", 29, false);
        WrappedEnumParticle.REDSTONE = new WrappedEnumParticle("REDSTONE", 30, "reddust", 30, false);
        WrappedEnumParticle.SNOWBALL = new WrappedEnumParticle("SNOWBALL", 31, "snowballpoof", 31, false);
        WrappedEnumParticle.SNOW_SHOVEL = new WrappedEnumParticle("SNOW_SHOVEL", 32, "snowshovel", 32, false);
        WrappedEnumParticle.SLIME = new WrappedEnumParticle("SLIME", 33, "slime", 33, false);
        WrappedEnumParticle.HEART = new WrappedEnumParticle("HEART", 34, "heart", 34, false);
        WrappedEnumParticle.BARRIER = new WrappedEnumParticle("BARRIER", 35, "barrier", 35, false);
        WrappedEnumParticle.ITEM_CRACK = new WrappedEnumParticle("ITEM_CRACK", 36, "iconcrack_", 36, false, 2);
        WrappedEnumParticle.BLOCK_CRACK = new WrappedEnumParticle("BLOCK_CRACK", 37, "blockcrack_", 37, false, 1);
        WrappedEnumParticle.BLOCK_DUST = new WrappedEnumParticle("BLOCK_DUST", 38, "blockdust_", 38, false, 1);
        WrappedEnumParticle.WATER_DROP = new WrappedEnumParticle("WATER_DROP", 39, "droplet", 39, false);
        WrappedEnumParticle.ITEM_TAKE = new WrappedEnumParticle("ITEM_TAKE", 40, "take", 40, false);
        WrappedEnumParticle.MOB_APPEARANCE = new WrappedEnumParticle("MOB_APPEARANCE", 41, "mobappearance", 41, true);
        WrappedEnumParticle.$VALUES = new WrappedEnumParticle[] { WrappedEnumParticle.EXPLOSION_NORMAL, WrappedEnumParticle.EXPLOSION_LARGE, WrappedEnumParticle.EXPLOSION_HUGE, WrappedEnumParticle.FIREWORKS_SPARK, WrappedEnumParticle.WATER_BUBBLE, WrappedEnumParticle.WATER_SPLASH, WrappedEnumParticle.WATER_WAKE, WrappedEnumParticle.SUSPENDED, WrappedEnumParticle.SUSPENDED_DEPTH, WrappedEnumParticle.CRIT, WrappedEnumParticle.CRIT_MAGIC, WrappedEnumParticle.SMOKE_NORMAL, WrappedEnumParticle.SMOKE_LARGE, WrappedEnumParticle.SPELL, WrappedEnumParticle.SPELL_INSTANT, WrappedEnumParticle.SPELL_MOB, WrappedEnumParticle.SPELL_MOB_AMBIENT, WrappedEnumParticle.SPELL_WITCH, WrappedEnumParticle.DRIP_WATER, WrappedEnumParticle.DRIP_LAVA, WrappedEnumParticle.VILLAGER_ANGRY, WrappedEnumParticle.VILLAGER_HAPPY, WrappedEnumParticle.TOWN_AURA, WrappedEnumParticle.NOTE, WrappedEnumParticle.PORTAL, WrappedEnumParticle.ENCHANTMENT_TABLE, WrappedEnumParticle.FLAME, WrappedEnumParticle.LAVA, WrappedEnumParticle.FOOTSTEP, WrappedEnumParticle.CLOUD, WrappedEnumParticle.REDSTONE, WrappedEnumParticle.SNOWBALL, WrappedEnumParticle.SNOW_SHOVEL, WrappedEnumParticle.SLIME, WrappedEnumParticle.HEART, WrappedEnumParticle.BARRIER, WrappedEnumParticle.ITEM_CRACK, WrappedEnumParticle.BLOCK_CRACK, WrappedEnumParticle.BLOCK_DUST, WrappedEnumParticle.WATER_DROP, WrappedEnumParticle.ITEM_TAKE, WrappedEnumParticle.MOB_APPEARANCE };
    }
}
