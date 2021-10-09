// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.base.processors;

import org.bukkit.potion.Potion;
import org.bukkit.inventory.ItemStack;
import us.overflow.tinyprotocol.api.TinyProtocolHandler;
import us.overflow.tinyprotocol.packet.out.WrappedOutKeepAlivePacket;
import java.util.concurrent.ThreadLocalRandom;
import java.math.RoundingMode;
import java.math.BigDecimal;
import org.bukkit.Material;
import org.bukkit.Location;
import us.overflow.tinyprotocol.packet.in.WrappedInBlockDigPacket;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.packet.in.WrappedInBlockPlacePacket;
import us.overflow.tinyprotocol.packet.in.WrappedInKeepAlivePacket;
import us.overflow.tinyprotocol.packet.in.WrappedInUseEntityPacket;
import us.overflow.utils.MathUtil;
import us.overflow.Overflow;
import org.bukkit.GameMode;
import us.overflow.utils.other.TimeUtils;
import us.overflow.events.impl.PacketEvent;
import java.util.ArrayList;
import java.util.List;
import us.overflow.base.user.User;

public class PredictionProcessor
{
    private User user;
    boolean fMath;
    List<Integer> keepAliveList;
    double lmotionX;
    double lmotionY;
    double lmotionZ;
    double rmotionX;
    double rmotionY;
    double rmotionZ;
    boolean fastMath;
    boolean walkSpecial;
    boolean lastVelocity;
    boolean lastSprint;
    boolean useSword;
    boolean useItem;
    boolean hitPlayer;
    boolean dropItem;
    boolean velocity;
    boolean hitall;
    public long lastUseItem;
    public long itemWaitPredict;
    private double lastSpeedMotion;
    public int speedPotionShit;
    public String pressKey;
    private static final float[] SIN_TABLE_FAST;
    private static final float[] SIN_TABLE;
    
    public PredictionProcessor(final User user) {
        this.fMath = false;
        this.keepAliveList = new ArrayList<Integer>();
        this.lmotionZ = 0.0;
        this.rmotionZ = 0.0;
        this.lastSprint = false;
        this.hitall = false;
        this.pressKey = "";
        this.user = user;
    }
    
    public void update(final PacketEvent event) {
        if (this.user.didSwitchGamemode || this.user.respawn || TimeUtils.secondsFromLong(this.user.lastRespawn) < 3L) {
            return;
        }
        if (this.user.getPlayer().isFlying() || this.user.getPlayer().getAllowFlight() || this.user.getPlayer().getGameMode() == GameMode.CREATIVE || (Overflow.getInstance().getVersion() != Overflow.Version.V1_7 && this.user.getPlayer().getGameMode().equals((Object)GameMode.SPECTATOR))) {
            return;
        }
        if (!this.user.motionXZReaady) {
            this.user.motionXZReaady = (TimeUtils.secondsFromLong(this.user.getLastJoin()) > 5L);
            return;
        }
        final String pt = event.getType();
        if (event.getDirection() == PacketEvent.Direction.CLIENT && (this.user.getPlayer().getItemInHand().getType().isEdible() || this.isPotion(this.user.getPlayer().getItemInHand()))) {
            final boolean attack = System.currentTimeMillis() - this.user.lastAttackByEntity < 1000L;
            this.useItem = (System.currentTimeMillis() - this.lastUseItem < 6000L && !this.user.sprinting && (MathUtil.trim(1, this.user.movementSpeed) < 0.2 || attack));
        }
        if (pt.equals("PacketPlayOutPosition")) {
            this.sendKeepAlive();
        }
        if (pt.equalsIgnoreCase("PacketPlayOutEntityVelocity")) {
            final WrappedInUseEntityPacket wrappedInUseEntityPacket = new WrappedInUseEntityPacket((Object)pt, this.user.getPlayer());
            if (wrappedInUseEntityPacket.getId() == this.user.getPlayer().getEntityId()) {
                this.sendKeepAlive();
            }
        }
        if (pt.equalsIgnoreCase("PacketPlayInKeepAlive")) {
            final WrappedInKeepAlivePacket wrappedInKeepAlivePacket = new WrappedInKeepAlivePacket((Object)pt, this.user.getPlayer());
            final int value = (int)wrappedInKeepAlivePacket.getTime();
            if (this.keepAliveList.contains(value)) {
                this.keepAliveList.remove(value);
                this.velocity = true;
            }
        }
        if (pt.equals("PacketPlayInHeldItemSlot")) {
            this.useSword = false;
            this.useItem = false;
        }
        if (pt.equalsIgnoreCase("PacketPlayInBlockPlace")) {
            final WrappedInBlockPlacePacket wrappedInBlockPlacePacket = new WrappedInBlockPlacePacket(event.getPacket(), this.user.getPlayer());
            if (wrappedInBlockPlacePacket.getPosition().getX() == -1 && wrappedInBlockPlacePacket.getPosition().getY() == -1 && wrappedInBlockPlacePacket.getPosition().getZ() == -1) {
                this.itemWaitPredict = System.currentTimeMillis();
                if (wrappedInBlockPlacePacket.getItemStack().getType().name().toLowerCase().contains("sword") || wrappedInBlockPlacePacket.getItemStack().getType().name().toLowerCase().contains("bow")) {
                    if (!this.hitall) {
                        this.useSword = true;
                    }
                }
                else {
                    this.useItem = true;
                }
            }
        }
        if (pt.equalsIgnoreCase("PacketPlayInUseEntity")) {
            final WrappedInUseEntityPacket wrappedInUseEntityPacket = new WrappedInUseEntityPacket(event.getPacket(), this.user.getPlayer());
            if (wrappedInUseEntityPacket.getAction() == WrappedInUseEntityPacket.EnumEntityUseAction.ATTACK) {
                this.hitall = true;
                if (wrappedInUseEntityPacket.getEntity() != null && wrappedInUseEntityPacket.getEntity() instanceof Player) {
                    this.hitPlayer = true;
                }
            }
        }
        if (pt.equalsIgnoreCase("PacketPlayInBlockDig")) {
            final WrappedInBlockDigPacket wrappedInBlockDigPacket = new WrappedInBlockDigPacket(event.getPacket(), this.user.getPlayer());
            this.useItem = false;
            this.useSword = false;
            if (wrappedInBlockDigPacket.getAction() == WrappedInBlockDigPacket.EnumPlayerDigType.RELEASE_USE_ITEM) {
                this.useItem = false;
                this.useSword = false;
            }
            else if (wrappedInBlockDigPacket.getAction() == WrappedInBlockDigPacket.EnumPlayerDigType.DROP_ITEM) {
                this.dropItem = true;
            }
        }
        if (pt.equals("PacketPlayInPositionLook") || pt.equals("PacketPlayInPosition") || pt.equals("PacketPlayInFlying") || pt.equals("PacketPlayInLook")) {
            this.rmotionX = this.user.newTo.getX() - this.user.newFrom.getX();
            this.rmotionY = this.user.newTo.getY() - this.user.newFrom.getY();
            this.rmotionZ = this.user.newTo.getZ() - this.user.newFrom.getZ();
            this.fMath = this.fastMath;
            try {
                if (!pt.equals("PacketPlayInFlying") && !pt.equals("PacketPlayInLook") && !this.walkSpecial && this.user.newTo.getYaw() != 0.0f && this.checkConditions()) {
                    this.calc(this.hitPlayer);
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
            if (this.dropItem) {
                this.useItem = false;
                this.useSword = false;
            }
            this.dropItem = false;
            double multiplier = 0.9100000262260437;
            this.rmotionX *= multiplier;
            this.rmotionZ *= multiplier;
            if (this.user.lastGround) {
                multiplier = 0.60000005239967;
                this.rmotionX *= multiplier;
                this.rmotionZ *= multiplier;
            }
            if (Math.abs(this.rmotionX) < 0.005) {
                this.rmotionX = 0.0;
            }
            if (Math.abs(this.rmotionY) < 0.005) {
                this.rmotionY = 0.0;
            }
            if (Math.abs(this.rmotionZ) < 0.005) {
                this.rmotionZ = 0.0;
            }
            this.lmotionX = this.rmotionX;
            this.lmotionY = this.rmotionY;
            this.lmotionZ = this.rmotionZ;
            this.hitPlayer = false;
            this.hitall = false;
            this.lastVelocity = this.velocity;
            this.velocity = false;
            this.walkSpecial = this.checkSpecialBlock();
            this.fastMath = this.fMath;
            this.lastSprint = this.user.sprinting;
        }
    }
    
    public boolean checkSpecialBlock() {
        if (!this.user.clientGround) {
            if (!this.user.lastClientGround) {
                return false;
            }
        }
        try {
            final Material lBlock = new Location(this.user.getPlayer().getWorld(), this.user.newFrom.getX(), this.user.newFrom.getY() - 0.43, this.user.newFrom.getZ()).getBlock().getType();
            final Material block = new Location(this.user.getPlayer().getWorld(), this.user.newTo.getX(), this.user.newTo.getY() - 0.1, this.user.newTo.getZ()).getBlock().getType();
            if (block == Material.ICE || block == Material.PACKED_ICE || block == Material.SLIME_BLOCK || lBlock == Material.ICE || lBlock == Material.PACKED_ICE || lBlock == Material.SLIME_BLOCK || this.user.iceTicks > 0 || this.user.soulSandTicks > 0 || this.user.slime || this.user.slimeTicks > 0) {
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    boolean checkConditions() {
        if (this.user.newFrom.getX() == 0.0 && this.user.newFrom.getY() == 0.0 && this.user.newFrom.getZ() == 0.0) {
            return false;
        }
        if (this.user.lastGround && !this.user.clientGround) {
            return false;
        }
        if (this.rmotionX == 0.0 && this.rmotionZ == 0.0 && this.user.clientGround) {
            return false;
        }
        if (Math.hypot(this.lmotionX, this.lmotionZ) > 11.0) {
            return false;
        }
        if (Math.hypot(this.user.newTo.getX() - this.user.newFrom.getX(), this.user.newTo.getZ() - this.user.newFrom.getZ()) > 10.0) {
            return false;
        }
        if (this.user.getPlayer().getGameMode() == GameMode.SPECTATOR) {
            return false;
        }
        if (this.user.getPlayer().isFlying()) {
            return false;
        }
        final Material m = new Location(this.user.getPlayer().getWorld(), this.user.newTo.getX(), this.user.newTo.getY(), this.user.newTo.getZ()).getBlock().getType();
        return m != Material.LADDER && m != Material.VINE && m != Material.STATIONARY_WATER && m != Material.WATER && m != Material.LAVA && m != Material.STATIONARY_LAVA;
    }
    
    void calc(final boolean hit) {
        boolean flag = true;
        int precision = String.valueOf((int)Math.abs(Math.hypot(this.user.newTo.getX(), this.user.newTo.getZ()))).length();
        precision = 15 - precision;
        final double preD = Double.valueOf("0.5E-" + precision);
        if (this.user.sprinting && hit) {
            this.lmotionX *= 0.6;
            this.lmotionZ *= 0.6;
        }
        final double mx = this.rmotionX - this.lmotionX;
        final double mz = this.rmotionZ - this.lmotionZ;
        float motionYaw = (float)(Math.atan2(mz, mx) * 180.0 / 3.141592653589793) - 90.0f;
        int direction = 6;
        for (motionYaw -= this.user.newTo.getYaw(); motionYaw > 360.0f; motionYaw -= 360.0f) {}
        while (motionYaw < 0.0f) {
            motionYaw += 360.0f;
        }
        motionYaw /= 45.0f;
        float moveS = 0.0f;
        float moveF = 0.0f;
        String key = "-";
        if (Math.abs(Math.abs(mx) + Math.abs(mz)) > preD) {
            direction = (int)new BigDecimal(motionYaw).setScale(1, RoundingMode.HALF_UP).doubleValue();
            if (direction == 1) {
                moveF = 1.0f;
                moveS = -1.0f;
                key = "W + D";
            }
            else if (direction == 2) {
                moveS = -1.0f;
                key = "D";
            }
            else if (direction == 3) {
                moveF = -1.0f;
                moveS = -1.0f;
                key = "S + D";
            }
            else if (direction == 4) {
                moveF = -1.0f;
                key = "S";
            }
            else if (direction == 5) {
                moveF = -1.0f;
                moveS = 1.0f;
                key = "S + A";
            }
            else if (direction == 6) {
                moveS = 1.0f;
                key = "A";
            }
            else if (direction == 7) {
                moveF = 1.0f;
                moveS = 1.0f;
                key = "W + A";
            }
            else if (direction == 8) {
                moveF = 1.0f;
                key = "W";
            }
            else if (direction == 0) {
                moveF = 1.0f;
                key = "W";
            }
            this.pressKey = key;
        }
        moveF *= 0.98f;
        moveS *= 0.98f;
        final String diffString = "";
        double diff = -1337.0;
        double closestdiff = 1337.0;
        int loops = 0;
        for (int fastLoop = 2; fastLoop > 0; --fastLoop) {
            this.fastMath = ((fastLoop == 2) ? this.fMath : (!this.fMath));
            boolean isBlocking = this.useSword;
            if (this.useItem) {
                isBlocking = true;
            }
            ++loops;
            float moveStrafing = moveS;
            float moveForward = moveF;
            if (isBlocking) {
                moveForward *= 0.2f;
                moveStrafing *= 0.2f;
            }
            if (this.user.sneaking) {
                if (this.user.sprinting) {
                    return;
                }
                moveForward *= 0.3f;
                moveStrafing *= 0.3f;
            }
            float jumpMovementFactor = 0.02f;
            if (this.lastSprint) {
                jumpMovementFactor = 0.025999999f;
            }
            final float var3 = 0.54600006f;
            float getAIMoveSpeed = 0.1f;
            if (this.user.sprinting) {
                getAIMoveSpeed = 0.13000001f;
            }
            final float var4 = 0.16277136f / (var3 * var3 * var3);
            float var5;
            if (this.user.lastGround) {
                var5 = getAIMoveSpeed * var4;
            }
            else {
                var5 = jumpMovementFactor;
            }
            double motionX = this.lmotionX;
            double motionZ = this.lmotionZ;
            float var6 = moveStrafing * moveStrafing + moveForward * moveForward;
            if (var6 >= 1.0E-4f) {
                var6 = sqrt_float(var6);
                if (var6 < 1.0f) {
                    var6 = 1.0f;
                }
                var6 = var5 / var6;
                moveStrafing *= var6;
                moveForward *= var6;
                final float var7 = this.sin(this.user.newTo.getYaw() * 3.1415927f / 180.0f);
                final float var8 = this.cos(this.user.newTo.getYaw() * 3.1415927f / 180.0f);
                motionX += moveStrafing * var8 - moveForward * var7;
                motionZ += moveForward * var8 + moveStrafing * var7;
            }
            final double diffX = this.rmotionX - motionX;
            final double diffZ = this.rmotionZ - motionZ;
            diff = Math.hypot(diffX, diffZ);
            diff = this.roundToDouble(diff, precision + 2);
            final double parse = Double.parseDouble(this.roundToString(closestdiff, precision + 2));
            this.user.lastPredictedDiff = diff;
            final boolean bad = this.user.soulSandTicks > 0 || this.user.liquidTicks > 0 || System.currentTimeMillis() - this.user.lastFullBlockMove > 500L || this.user.blockAboveTicks > 0 || this.user.webTicks > 0 || this.user.halfBlockTicks > 0 || System.currentTimeMillis() - this.user.lastJumpPadUpdate < 1000L || this.user.jumpPad || this.user.collidesHorizontally || this.user.stairTicks > 0 || this.user.slabTicks > 0 || !this.user.motionXZReaady || this.user.fenceTicks > 0;
            final double gay = MathUtil.trim(3, diff);
            if (this.user.clientGround && this.user.lastGround && this.user.airTicks < 1) {
                if (this.user.speedPotionTicks > 0) {
                    if (gay > 0.0) {
                        if (this.lastSpeedMotion == gay) {
                            if (this.lastSpeedMotion == gay && this.speedPotionShit < 20) {
                                ++this.speedPotionShit;
                            }
                        }
                        else if (this.speedPotionShit > 0) {
                            this.speedPotionShit -= 5;
                        }
                        this.lastSpeedMotion = gay;
                    }
                }
                else {
                    this.speedPotionShit = 0;
                }
            }
            else {
                this.speedPotionShit = 0;
            }
            if (diff < preD || bad) {
                flag = false;
                this.fMath = this.fastMath;
                break;
            }
            if (diff < closestdiff) {
                closestdiff = diff;
            }
        }
        this.user.invalidMotionXZ = flag;
        if (System.currentTimeMillis() - this.user.lastFullBlockMove < 320L) {
            this.user.lastMotionAPredictionDiff = Math.abs(preD - diff);
        }
        else {
            this.user.lastMotionAPredictionDiff = 1.0E-5;
        }
    }
    
    public boolean isStrafing() {
        return this.pressKey.equalsIgnoreCase("A") || this.pressKey.equalsIgnoreCase("D") || this.pressKey.equalsIgnoreCase("S");
    }
    
    public BigDecimal round(final double value, final int i) {
        return new BigDecimal(value).setScale(i, RoundingMode.HALF_UP);
    }
    
    public double roundToDouble(final double value, final int i) {
        return this.round(value, i).doubleValue();
    }
    
    public String roundToString(final double value, final int i) {
        return this.round(value, i).toPlainString();
    }
    
    public float sin(final float p_76126_0_) {
        return this.fastMath ? PredictionProcessor.SIN_TABLE_FAST[(int)(p_76126_0_ * 651.8986f) & 0xFFF] : PredictionProcessor.SIN_TABLE[(int)(p_76126_0_ * 10430.378f) & 0xFFFF];
    }
    
    public float cos(final float p_76134_0_) {
        return this.fastMath ? PredictionProcessor.SIN_TABLE_FAST[(int)((p_76134_0_ + 1.5707964f) * 651.8986f) & 0xFFF] : PredictionProcessor.SIN_TABLE[(int)(p_76134_0_ * 10430.378f + 16384.0f) & 0xFFFF];
    }
    
    public static float sqrt_float(final float p_76129_0_) {
        return (float)Math.sqrt(p_76129_0_);
    }
    
    public static float sqrt_double(final double p_76133_0_) {
        return (float)Math.sqrt(p_76133_0_);
    }
    
    public void sendKeepAlive() {
        final int id = 233 + this.user.getPlayer().getEntityId() + ThreadLocalRandom.current().nextInt(999);
        TinyProtocolHandler.sendPacket(this.user.getPlayer(), new WrappedOutKeepAlivePacket((long)id).getObject());
    }
    
    private boolean isPotion(final ItemStack item) {
        try {
            Potion.fromItemStack(item);
        }
        catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
    
    static {
        SIN_TABLE_FAST = new float[4096];
        SIN_TABLE = new float[65536];
        for (int i = 0; i < 65536; ++i) {
            PredictionProcessor.SIN_TABLE[i] = (float)Math.sin(i * 3.141592653589793 * 2.0 / 65536.0);
        }
        for (int i = 0; i < 4096; ++i) {
            PredictionProcessor.SIN_TABLE_FAST[i] = (float)Math.sin((i + 0.5f) / 4096.0f * 6.2831855f);
        }
        for (int i = 0; i < 360; i += 90) {
            PredictionProcessor.SIN_TABLE_FAST[(int)(i * 11.377778f) & 0xFFF] = (float)Math.sin(i * 0.017453292f);
        }
    }
}
