// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.base.processors;

import us.overflow.utils.other.TimeUtils;
import us.overflow.utils.MathUtil;
import us.overflow.base.user.User;
import java.util.concurrent.TimeUnit;
import us.overflow.Overflow;

public class OptifineProcessor
{
    public OptifineProcessor() {
        Overflow.getInstance().getProcessorService().scheduleAtFixedRate(() -> Overflow.getInstance().getUserManager().getUsers().forEach(user -> {
            this.updateOpti(user);
            this.processSmoother(user);
            this.updateOptifine(user);
        }), 50L, 50L, TimeUnit.MILLISECONDS);
    }
    
    public void updateOpti(final User user) {
        if (System.currentTimeMillis() - user.lastOptifine > 2000L) {
            if (user.optifineSmoothing2 > 0) {
                --user.optifineSmoothing2;
            }
            user.optifineSmoothing = 0;
        }
        if (System.currentTimeMillis() - user.lastBukkitMovement >= 1000L) {
            user.optifineSameCount = 0;
            user.optifineSmoothing2 = 0;
            user.optifineSmoothing = 0;
        }
    }
    
    public void processSmoother(final User user) {
        final float yawDelta = MathUtil.getDelta(user.to.getYaw(), user.from.getYaw());
        final float pitchDelta = MathUtil.getDelta(user.to.getPitch(), user.from.getPitch());
        final float smooth = user.newYawSmoothing.smooth(yawDelta, this.convertToMouseDelta(user.lastYawDelta));
        final float smooth2 = user.newPitchSmoothing.smooth(pitchDelta, this.convertToMouseDelta(pitchDelta));
        final double yaw = Math.abs(smooth - user.lastSmoothYaw);
        final double pitch = Math.abs(smooth2 - user.lastSmoothPitch1);
        if (String.valueOf(yawDelta).contains("E")) {
            user.lastRetardOpitfineSpam = System.currentTimeMillis();
        }
        if ((yaw > 0.0 && yaw < 0.09) || (pitch > 0.0 && pitch < 0.12)) {
            if (user.optifineConstantVL < 30) {
                ++user.optifineConstantVL;
            }
            if (user.optifineSmoothingFix < 30) {
                ++user.optifineSmoothingFix;
            }
        }
        else {
            if (user.optifineConstantVL > 0) {
                --user.optifineConstantVL;
            }
            if (user.optifineSmoothingFix > 0) {
                user.optifineSmoothingFix -= 5;
            }
        }
        if (user.optifineConstantVL > 9) {
            if (user.optifineConstantVL2 < 30) {
                ++user.optifineConstantVL2;
            }
        }
        else if (user.optifineConstantVL2 > 0) {
            --user.optifineConstantVL2;
        }
        if (user.optifineConstantVL2 > 5) {
            user.lastOptifineREE = System.currentTimeMillis();
        }
        user.lastOptifinePitchSmoothidfklol = this.convertToMouseDelta(Math.abs(smooth - user.lastSmoothYaw));
        user.lastSmoothPitch1 = smooth2;
        user.lastSmoothYaw = smooth;
        user.lastYawDelta = yawDelta;
        user.lastPitchDelta = pitchDelta;
        user.newPitchSmoothing.reset();
        user.newYawSmoothing.reset();
    }
    
    public void updateOptifine(final User user) {
        final int ace = (user.killauraAYawReset > 2) ? (user.killauraAPitchReset * user.killauraAYawReset) : user.killauraAYawReset;
        if (ace > 3) {
            user.lastAimAssistACE = System.currentTimeMillis();
        }
        if (TimeUtils.elapsed(user.lastAimAssistACE) <= 100L && user.aimAssistsACount > 0) {
            --user.aimAssistsACount;
        }
        final float yawDelta = MathUtil.getDelta(user.to.getYaw(), user.from.getYaw());
        final float pitchDelta = MathUtil.getDelta(user.to.getPitch(), user.from.getPitch());
        final float yawShit = this.convertToMouseDelta(yawDelta);
        final float pitchShit = this.convertToMouseDelta(pitchDelta);
        final float smooth = user.yaw.smooth(yawShit, yawShit * 0.05f);
        final float smooth2 = user.pitch.smooth(pitchShit, pitchShit * 0.05f);
        user.cineCamera = (MathUtil.getDelta(smooth, yawShit) < 0.08f || MathUtil.getDelta(smooth2, pitchShit) < 0.04f);
        if (user.cineCamera) {
            ++user.optifineSmoothingTicks;
        }
        else if (user.optifineSmoothingTicks > 0) {
            --user.optifineSmoothingTicks;
        }
        if (user.to.getPitch() == user.from.getPitch()) {
            ++user.killauraAPitchReset;
        }
        else {
            user.killauraAPitchReset = 0;
        }
        if (user.to.getYaw() == user.from.getYaw() || Math.abs(user.to.getYaw() - user.from.getYaw()) == 0.0) {
            if (user.killauraAYawReset < 20) {
                ++user.killauraAYawReset;
            }
        }
        else if (user.killauraAYawReset > 0) {
            --user.killauraAYawReset;
        }
        final float yawDelta2 = Math.abs(user.from.getYaw() - user.to.getYaw());
        final float pitchDelta2 = Math.abs(user.from.getYaw() - user.to.getYaw());
        final float yaw = this.convertToMouseDelta(yawDelta2);
        final float pitch = this.convertToMouseDelta(pitchDelta2);
        final float smoothing = ((float)Math.cbrt(yawDelta2 / 0.15f / 8.0f) - 0.2f) / 0.6f;
        final float smoothingpitchDelta = ((float)Math.cbrt(yawDelta2 / 0.15f / 8.0f) - 0.2f) / 0.6f;
        final float smooth3 = user.smooth.smooth(yaw, pitch * 0.05f);
        if (Math.abs(user.lastSmoothingRot - smoothingpitchDelta) < 1.0) {
            if (user.optifinePitchSmooth < 20) {
                ++user.optifinePitchSmooth;
            }
        }
        else {
            user.optifinePitchSmooth = 0;
        }
        final boolean smoothing2 = Math.abs(user.lastSmoothingRot - smoothing) < 0.1;
        final boolean smoothing3 = Math.abs(smooth3 - smoothing) > 0.2 && smoothing > 1.2;
        if ((smoothing2 || smoothing3) && MathUtil.looked(user.from, user.to)) {
            if (MathUtil.looked(user.from, user.to) && user.from.getYaw() != user.to.getYaw() && user.from.getPitch() != user.to.getPitch()) {
                if (user.optifineSmoothSens < 20) {
                    ++user.optifineSmoothSens;
                }
            }
            else if (user.optifineSmoothSens > 0) {
                --user.optifineSmoothSens;
            }
        }
        else if (user.optifineSmoothSens > 0) {
            --user.optifineSmoothSens;
        }
        if (Math.abs(user.smoothingCounter - user.LastSmoothingCounter) > 0) {
            user.smoothingCounter = 0;
        }
        if (user.optifineSameCount > 2) {
            user.optifineSmoothing = 0;
        }
        if (user.smoothingCounter > 4) {
            if (user.lastClientSmoothingValue == user.smoothingCounter) {
                user.optifineSameCount += 2;
            }
            else if (user.optifineSameCount > 0) {
                --user.optifineSameCount;
            }
            user.lastClientSmoothingValue = user.smoothingCounter;
            user.lastOptifine = System.currentTimeMillis();
            if (user.optifineSmoothing < 30) {
                ++user.optifineSmoothing;
            }
        }
        else if (user.optifineSmoothing > 0) {
            user.optifineSmoothing -= 2;
        }
        if (smoothing2 || smoothing3) {
            if (MathUtil.looked(user.from, user.to) && user.from.getYaw() != user.to.getYaw() && user.from.getPitch() != user.to.getPitch()) {
                ++user.smoothingCounter;
            }
            else if (user.smoothingCounter > 0) {
                --user.smoothingCounter;
            }
            user.LastSmoothingCounter = user.smoothingCounter;
        }
        else {
            user.smoothingCounter = 0;
        }
        user.lastSmoothingRot = smoothing;
        user.lastSmoothingRot2 = smoothingpitchDelta;
    }
    
    private float convertToMouseDelta(final float value) {
        return ((float)Math.cbrt(value / 0.15f / 8.0f) - 0.2f) / 0.6f;
    }
}
