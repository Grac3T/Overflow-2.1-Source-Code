// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.base.processors;

import us.overflow.Overflow;
import us.overflow.utils.block.BlockAssesement;
import us.overflow.utils.blockbox.BoundingBox;
import us.overflow.utils.location.CustomLocation;
import us.overflow.utils.other.TimeUtils;
import us.overflow.tinyprotocol.packet.in.WrappedInFlyingPacket;
import us.overflow.tinyprotocol.packet.in.WrappedInEntityActionPacket;
import us.overflow.tinyprotocol.packet.out.WrappedOutVelocityPacket;
import java.util.Collection;
import us.overflow.utils.MathUtil;
import us.overflow.utils.other.EvictingList;
import us.overflow.utils.TickTimer;
import java.util.List;
import us.overflow.base.user.User;

public class MovementProcessor
{
    private User user;
    private double offset;
    private double pitchDelta;
    private double yawDelta;
    private double lastDeltaYaw;
    private double lastDeltaPitch;
    private double pitchMode;
    private double yawMode;
    private double sensXPercent;
    private double deltaX;
    private double deltaY;
    private double sensYPercent;
    private double sensitivityX;
    private double sensitivityY;
    private double lastDeltaX;
    private double lastDeltaY;
    private long pitchGCD;
    private long yawGCD;
    private long lastPitchGCD;
    private long lastYawGCD;
    private List<Double> pitchGcdList;
    private List<Double> yawGcdList;
    private TickTimer timer;
    
    public MovementProcessor() {
        this.offset = Math.pow(2.0, 24.0);
        this.pitchGcdList = (List<Double>)new EvictingList(40);
        this.yawGcdList = (List<Double>)new EvictingList(40);
        this.timer = new TickTimer(5);
    }
    
    public void updateSensitityPrediction() {
        if (this.user.getNewFrom() == null || this.user.getNewTo() == null) {
            return;
        }
        this.lastDeltaPitch = this.pitchDelta;
        this.lastDeltaYaw = this.yawDelta;
        this.yawDelta = Math.abs(this.user.getNewTo().getYaw() - this.user.getNewFrom().getYaw());
        this.pitchDelta = this.user.getNewTo().getPitch() - this.user.getNewFrom().getPitch();
        this.lastYawGCD = this.yawGCD;
        this.yawGCD = MathUtil.gcd((long)(this.yawDelta * this.offset), (long)(this.lastDeltaYaw * this.offset));
        this.lastPitchGCD = this.pitchGCD;
        this.pitchGCD = MathUtil.gcd((long)(Math.abs(this.pitchDelta) * this.offset), (long)(Math.abs(this.lastDeltaPitch) * this.offset));
        final double yawGcd = this.yawGCD / this.offset;
        final double pitchGcd = this.pitchGCD / this.offset;
        if (this.yawGCD > 90000L && this.yawGCD < 2.0E7 && yawGcd > 0.009999999776482582 && this.yawDelta < 8.0) {
            this.yawGcdList.add(yawGcd);
        }
        if (this.pitchGCD > 90000L && this.pitchGCD < 2.0E7 && Math.abs(this.pitchDelta) < 8.0) {
            this.pitchGcdList.add(pitchGcd);
        }
        if (this.yawGcdList.size() > 3 && this.pitchGcdList.size() > 3) {
            if (this.timer.hasPassed()) {
                this.yawMode = (double)MathUtil.getMode((Collection)this.yawGcdList);
                this.pitchMode = (double)MathUtil.getMode((Collection)this.pitchGcdList);
                this.timer.reset();
                final double sensitivityFromYawGCD = getSensitivityFromYawGCD(this.yawMode);
                this.sensitivityX = sensitivityFromYawGCD;
                this.sensXPercent = sensToPercent(sensitivityFromYawGCD);
                final double sensitivityFromPitchGCD = getSensitivityFromPitchGCD(this.pitchMode);
                this.sensitivityY = sensitivityFromPitchGCD;
                this.sensYPercent = sensToPercent(sensitivityFromPitchGCD);
                this.user.clientSens = (int)this.sensXPercent;
                this.user.setClientSens = true;
            }
            this.lastDeltaX = this.deltaX;
            this.lastDeltaY = this.deltaY;
            this.deltaX = getDeltaX(this.yawDelta, (float)this.yawMode);
            this.deltaY = getDeltaY(this.pitchDelta, (float)this.pitchMode);
        }
    }
    
    public void update(final Object packet, final String type) {
        if (type.equalsIgnoreCase("PacketPlayOutEntityVelocity")) {
            final WrappedOutVelocityPacket velocityPacket = new WrappedOutVelocityPacket(packet, this.user.getPlayer());
            if (velocityPacket.getId() == this.user.getPlayer().getEntityId() && !this.user.isJumpPad() && this.user.isOnGround() && System.currentTimeMillis() - this.user.getLastDamage() > 20L) {
                this.user.setJumpPad(true);
                this.user.setLastJumpPadSet(System.currentTimeMillis());
            }
        }
        if (type.equalsIgnoreCase("PacketPlayInEntityAction")) {
            final WrappedInEntityActionPacket wrappedInEntityActionPacket = new WrappedInEntityActionPacket(packet, this.user.getPlayer());
            if (wrappedInEntityActionPacket.getAction() == WrappedInEntityActionPacket.EnumPlayerAction.START_SNEAKING) {
                this.user.sneaking = true;
            }
            else if (wrappedInEntityActionPacket.getAction() == WrappedInEntityActionPacket.EnumPlayerAction.STOP_SNEAKING) {
                this.user.sneaking = false;
            }
            if (wrappedInEntityActionPacket.getAction() == WrappedInEntityActionPacket.EnumPlayerAction.START_SPRINTING) {
                this.user.sprinting = true;
            }
            else if (wrappedInEntityActionPacket.getAction() == WrappedInEntityActionPacket.EnumPlayerAction.STOP_SPRINTING) {
                this.user.sprinting = false;
            }
        }
        if (type.equalsIgnoreCase("PacketPlayInPosition") || type.equalsIgnoreCase("PacketPlayInPositionLook") || type.equalsIgnoreCase("PacketPlayInLook") || type.equalsIgnoreCase("PacketPlayInFlying")) {
            final WrappedInFlyingPacket wrappedInFlyingPacket = new WrappedInFlyingPacket(packet, this.user.getPlayer());
            this.updateSensitityPrediction();
            if (this.user.didSwitchGamemode && System.currentTimeMillis() - this.user.lastGayModeSwitch > 500L && this.user.isOnGround()) {
                this.user.didSwitchGamemode = false;
            }
            final User user = this.user;
            ++user.tick;
            if (!this.user.movementChecksOK && TimeUtils.secondsFromLong(this.user.lastJoin) > 3L) {
                this.user.movementChecksOK = true;
            }
            if (this.user.isOnGround() && this.user.lastGround) {
                if (this.user.newTo.toLocation(this.user.getPlayer().getWorld()).getBlockY() > this.user.newFrom.toLocation(this.user.player.getWorld()).getBlockY()) {
                    this.user.lastBlockJump = System.currentTimeMillis();
                }
                this.user.setLastGroundLocation(this.user.newTo);
            }
            if (this.user.isJumpPad()) {
                if (System.currentTimeMillis() - this.user.getLastJumpPadSet() > 230L && this.user.isOnGround()) {
                    this.user.setJumpPad(false);
                }
                this.user.setLastJumpPadUpdate(System.currentTimeMillis());
            }
            if (this.user.oldTicks > 19) {
                this.user.oldTicks = 0;
            }
            final User user2 = this.user;
            ++user2.oldTicks;
            if (this.user.newFrom == null || this.user.newTo == null) {
                this.user.newFrom = new CustomLocation(0.0, 0.0, 0.0, 0.0f, 0.0f);
                this.user.newTo = new CustomLocation(0.0, 0.0, 0.0, 0.0f, 0.0f);
            }
            this.user.newFrom = this.user.newTo.clone();
            if (wrappedInFlyingPacket.isPos()) {
                this.user.newTo.setX(wrappedInFlyingPacket.getX());
                this.user.newTo.setY(wrappedInFlyingPacket.getY());
                this.user.newTo.setZ(wrappedInFlyingPacket.getZ());
                this.user.newTo.setTimeStamp(System.currentTimeMillis());
            }
            if (wrappedInFlyingPacket.isLook()) {
                this.user.newTo.setPitch(wrappedInFlyingPacket.getPitch());
                this.user.newTo.setYaw(wrappedInFlyingPacket.getYaw());
            }
            this.user.newTo.setTimeStamp(System.currentTimeMillis());
            this.user.setOldTo(this.user.newTo.toLocation(this.user.getPlayer().getWorld()));
            this.user.setOldFrom(this.user.newFrom.toLocation(this.user.getPlayer().getWorld()));
            this.user.setLastBukkitMovement(System.currentTimeMillis());
            this.user.setLastDeltaXZ(this.user.deltaXZ);
            this.user.setDeltaXZ(Math.hypot(this.user.getNewTo().getX() - this.user.getNewFrom().getX(), this.user.getNewTo().getZ() - this.user.getNewFrom().getZ()));
            this.user.setLastDeltaY(this.user.deltaY);
            this.user.setDeltaY(this.user.getNewTo().getY() - this.user.getNewFrom().getY());
            this.user.setLastClientGround(this.user.isClientGround());
            this.user.setClientGround(wrappedInFlyingPacket.isGround());
            if (this.user.oldTo != this.user.oldFrom && this.user.newTo != this.user.newFrom) {
                final double x = Math.floor(this.user.oldFrom.getX());
                final double z = Math.floor(this.user.oldFrom.getZ());
                if (Math.floor(this.user.oldTo.getX()) != x || Math.floor(this.user.oldTo.getZ()) != z) {
                    if (this.user.teleportedNoMove && System.currentTimeMillis() - this.user.lastTeleport > 1000L && System.currentTimeMillis() - this.user.lastHasPos < 1000L && System.currentTimeMillis() - this.user.lastBukkitMovement < 1000L) {
                        this.user.teleportedNoMove = false;
                    }
                    this.user.lastFullBlockMove = System.currentTimeMillis();
                    if (this.user.totalBlocksCheck < 100) {
                        final User user3 = this.user;
                        ++user3.totalBlocksCheck;
                    }
                }
            }
            this.user.setFrom(this.user.getTo());
            this.user.wasOnGround = this.user.onGround;
            this.user.setTo(this.user.newTo.toLocation(this.user.getPlayer().getWorld()));
            if (this.user.getOldTo() != null && this.user.getOldFrom() != null && this.user.newTo != null && this.user.newFrom != null) {
                this.user.deltaX = this.user.getNewTo().getX() - this.user.getNewFrom().getX();
                this.user.deltaZ = this.user.getNewTo().getZ() - this.user.getNewFrom().getZ();
                final double x = Math.abs(Math.abs(this.user.newTo.getX()) - Math.abs(this.user.newFrom.getX()));
                final double z = Math.abs(Math.abs(this.user.newTo.getZ()) - Math.abs(this.user.newFrom.getZ()));
                this.user.movementSpeed = Math.sqrt(x * x + z * z);
            }
            if (this.user.lastEntityAttacked != null) {
                this.user.reachBPastLocations.addLocation(this.user.lastEntityAttacked.getLocation());
            }
            else {
                this.user.reachBPastLocations.clearLocations();
            }
            final boolean goFuckYourSelf = TimeUtils.secondsFromLong(this.user.lastJoin) > 2L;
            if (!this.user.isGAY && goFuckYourSelf) {
                this.user.isGAY = true;
            }
            if (wrappedInFlyingPacket.isPos()) {
                this.user.lastGround = this.user.isOnGround();
                final CustomLocation to = new CustomLocation(wrappedInFlyingPacket.getX(), wrappedInFlyingPacket.getY(), wrappedInFlyingPacket.getZ(), wrappedInFlyingPacket.getYaw(), wrappedInFlyingPacket.getPitch());
                final boolean switchVecotors = Math.abs(to.toVector().length() - this.user.newFrom.toVector().length()) >= 1.0;
                this.user.setBoundingBox(new BoundingBox(switchVecotors ? to.toVector() : this.user.newFrom.toVector(), to.toVector()).grow(0.3f, 0.0f, 0.3f).add(0.0f, 0.0f, 0.0f, 0.0f, 1.84f, 0.0f));
                final BlockAssesement blockAssesement = new BlockAssesement(this.user.getBoundingBox(), this.user);
                if (this.user.isGAY) {
                    Overflow.getInstance().getBlockBoxManager().getBlockBox().getCollidingBoxes(this.user.getPlayer().getWorld(), this.user.getBoundingBox().grow(0.5f, 0.35f, 0.5f).subtract(0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f)).forEach(bb -> blockAssesement.update(bb, this.user.getPlayer().getWorld()));
                    this.user.setBlockAssesement(blockAssesement);
                    this.user.setOnGround(blockAssesement.isOnGround() || MathUtil.onGround(this.user.getPlayer()));
                }
            }
            this.user.update(User.UpdateType.TICKS);
        }
    }
    
    private static double yawToF2(final double yawDelta) {
        return yawDelta / 0.15;
    }
    
    private static double pitchToF3(final double pitchDelta) {
        final int b0 = (pitchDelta >= 0.0) ? 1 : -1;
        return pitchDelta / 0.15 / b0;
    }
    
    private static double getF1FromYaw(final double gcd) {
        final double f = getFFromYaw(gcd);
        return Math.pow(f, 3.0) * 8.0;
    }
    
    private static double getSensitivityFromPitchGCD(final double gcd) {
        final double stepOne = pitchToF3(gcd) / 8.0;
        final double stepTwo = Math.cbrt(stepOne);
        final double stepThree = stepTwo - 0.20000000298023224;
        return stepThree / 0.6000000238418579;
    }
    
    private static double getSensitivityFromYawGCD(final double gcd) {
        final double stepOne = yawToF2(gcd) / 8.0;
        final double stepTwo = Math.cbrt(stepOne);
        final double stepThree = stepTwo - 0.20000000298023224;
        return stepThree / 0.6000000238418579;
    }
    
    private static double getFFromYaw(final double gcd) {
        final double sens = getSensitivityFromYawGCD(gcd);
        return sens * 0.6000000238418579 + 0.2;
    }
    
    private static double getFFromPitch(final double gcd) {
        final double sens = getSensitivityFromPitchGCD(gcd);
        return sens * 0.6000000238418579 + 0.2;
    }
    
    private static double getF1FromPitch(final double gcd) {
        final double f = getFFromPitch(gcd);
        return (float)Math.pow(f, 3.0) * 8.0f;
    }
    
    private static int getDeltaX(final double yawDelta, final double gcd) {
        final double f2 = yawToF2(yawDelta);
        return MathUtil.floor(f2 / getF1FromYaw(gcd));
    }
    
    private static int getDeltaY(final double pitchDelta, final double gcd) {
        final double f3 = pitchToF3(pitchDelta);
        return MathUtil.floor(f3 / getF1FromPitch(gcd));
    }
    
    public static int sensToPercent(final double sensitivity) {
        return (int)MathUtil.round(sensitivity / 0.5 * 100.0, 0);
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
    
    public void setOffset(final double offset) {
        this.offset = offset;
    }
    
    public void setPitchDelta(final double pitchDelta) {
        this.pitchDelta = pitchDelta;
    }
    
    public void setYawDelta(final double yawDelta) {
        this.yawDelta = yawDelta;
    }
    
    public void setLastDeltaYaw(final double lastDeltaYaw) {
        this.lastDeltaYaw = lastDeltaYaw;
    }
    
    public void setLastDeltaPitch(final double lastDeltaPitch) {
        this.lastDeltaPitch = lastDeltaPitch;
    }
    
    public void setPitchMode(final double pitchMode) {
        this.pitchMode = pitchMode;
    }
    
    public void setYawMode(final double yawMode) {
        this.yawMode = yawMode;
    }
    
    public void setSensXPercent(final double sensXPercent) {
        this.sensXPercent = sensXPercent;
    }
    
    public void setDeltaX(final double deltaX) {
        this.deltaX = deltaX;
    }
    
    public void setDeltaY(final double deltaY) {
        this.deltaY = deltaY;
    }
    
    public void setSensYPercent(final double sensYPercent) {
        this.sensYPercent = sensYPercent;
    }
    
    public void setSensitivityX(final double sensitivityX) {
        this.sensitivityX = sensitivityX;
    }
    
    public void setSensitivityY(final double sensitivityY) {
        this.sensitivityY = sensitivityY;
    }
    
    public void setLastDeltaX(final double lastDeltaX) {
        this.lastDeltaX = lastDeltaX;
    }
    
    public void setLastDeltaY(final double lastDeltaY) {
        this.lastDeltaY = lastDeltaY;
    }
    
    public void setPitchGCD(final long pitchGCD) {
        this.pitchGCD = pitchGCD;
    }
    
    public void setYawGCD(final long yawGCD) {
        this.yawGCD = yawGCD;
    }
    
    public void setLastPitchGCD(final long lastPitchGCD) {
        this.lastPitchGCD = lastPitchGCD;
    }
    
    public void setLastYawGCD(final long lastYawGCD) {
        this.lastYawGCD = lastYawGCD;
    }
    
    public void setPitchGcdList(final List<Double> pitchGcdList) {
        this.pitchGcdList = pitchGcdList;
    }
    
    public void setYawGcdList(final List<Double> yawGcdList) {
        this.yawGcdList = yawGcdList;
    }
    
    public void setTimer(final TickTimer timer) {
        this.timer = timer;
    }
}
