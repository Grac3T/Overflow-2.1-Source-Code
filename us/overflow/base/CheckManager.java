// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.base;

import us.overflow.events.OverflowListener;
import java.util.function.Consumer;
import org.bukkit.plugin.Plugin;
import us.overflow.utils.file.ChecksFile;
import us.overflow.checks.movement.phase.PhaseB;
import us.overflow.checks.movement.phase.PhaseA;
import us.overflow.Overflow;
import us.overflow.checks.combat.autoblock.AutoBlockA;
import us.overflow.checks.movement.noslow.NoSlowA;
import us.overflow.checks.misc.nofall.NoFall;
import us.overflow.checks.misc.badpackets.BadPacketsN;
import us.overflow.checks.misc.badpackets.BadPacketsM;
import us.overflow.checks.misc.badpackets.BadPacketsL;
import us.overflow.checks.misc.badpackets.BadPacketsK;
import us.overflow.checks.misc.badpackets.BadPacketsJ;
import us.overflow.checks.misc.badpackets.BadPacketsI;
import us.overflow.checks.misc.badpackets.BadPacketsH;
import us.overflow.checks.misc.badpackets.BadPacketsG;
import us.overflow.checks.misc.badpackets.BadPacketsF;
import us.overflow.checks.misc.badpackets.BadPacketsE;
import us.overflow.checks.misc.badpackets.BadPacketsD;
import us.overflow.checks.misc.badpackets.BadPacketsC;
import us.overflow.checks.misc.badpackets.BadPacketsB;
import us.overflow.checks.misc.badpackets.BadPacketsA;
import us.overflow.checks.movement.timer.TimerA;
import us.overflow.checks.combat.hitbox.HitBoxA;
import us.overflow.checks.movement.motion.MotionC;
import us.overflow.checks.movement.motion.MotionA;
import us.overflow.checks.movement.speed.SpeedE;
import us.overflow.checks.movement.speed.SpeedD;
import us.overflow.checks.movement.speed.SpeedC;
import us.overflow.checks.movement.speed.SpeedB;
import us.overflow.checks.movement.speed.SpeedA;
import us.overflow.checks.movement.flight.FlightE;
import us.overflow.checks.movement.flight.FlightD;
import us.overflow.checks.movement.flight.FlightC;
import us.overflow.checks.movement.flight.FlightB;
import us.overflow.checks.movement.flight.FlightA;
import us.overflow.checks.misc.autotool.AutoToolA;
import us.overflow.checks.combat.reach.ReachA;
import us.overflow.checks.combat.aimassist.AimAssistC;
import us.overflow.checks.combat.aimassist.AimAssistB;
import us.overflow.checks.combat.aimassist.AimAssistA;
import us.overflow.checks.combat.aim.AimZ;
import us.overflow.checks.combat.aim.AimY;
import us.overflow.checks.combat.aim.AimX;
import us.overflow.checks.combat.aim.AimW;
import us.overflow.checks.combat.aim.AimV;
import us.overflow.checks.combat.aim.AimU;
import us.overflow.checks.combat.aim.AimT;
import us.overflow.checks.combat.aim.AimS;
import us.overflow.checks.combat.aim.AimR;
import us.overflow.checks.combat.aim.AimQ;
import us.overflow.checks.combat.aim.AimP;
import us.overflow.checks.combat.aim.AimO;
import us.overflow.checks.combat.aim.AimN;
import us.overflow.checks.combat.aim.AimM;
import us.overflow.checks.combat.aim.AimL;
import us.overflow.checks.combat.aim.AimK;
import us.overflow.checks.combat.aim.AimJ;
import us.overflow.checks.combat.aim.AimI;
import us.overflow.checks.combat.aim.AimH;
import us.overflow.checks.combat.aim.AimG;
import us.overflow.checks.combat.aim.AimF;
import us.overflow.checks.combat.aim.AimE;
import us.overflow.checks.combat.aim.AimD;
import us.overflow.checks.combat.aim.AimC;
import us.overflow.checks.combat.aim.AimB;
import us.overflow.checks.combat.aim.AimA;
import us.overflow.checks.combat.CPS;
import us.overflow.checks.combat.autoclicker.AutoClickerF;
import us.overflow.checks.combat.autoclicker.AutoClickerE;
import us.overflow.checks.combat.autoclicker.AutoClickerD;
import us.overflow.checks.combat.autoclicker.AutoClickerC;
import us.overflow.checks.combat.autoclicker.AutoClickerB;
import us.overflow.checks.combat.autoclicker.AutoClickerA;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class CheckManager
{
    private List<Check> checkList;
    public static boolean flag;
    
    public CheckManager() {
        this.checkList = Collections.synchronizedList(new ArrayList<Object>());
        this.loadChecks();
    }
    
    public void loadChecks() {
        this.saveAll();
        this.addCheck((Check)new AutoClickerA("AutoClicker", "A", CheckType.COMBAT, true));
        this.addCheck((Check)new AutoClickerB("AutoClicker", "B", CheckType.COMBAT, true));
        this.addCheck((Check)new AutoClickerC("AutoClicker", "C", CheckType.COMBAT, true));
        this.addCheck((Check)new AutoClickerD("AutoClicker", "D", CheckType.COMBAT, true));
        this.addCheck((Check)new AutoClickerE("AutoClicker", "E", CheckType.COMBAT, true));
        this.addCheck((Check)new AutoClickerF("AutoClicker", "F", CheckType.COMBAT, true));
        this.addCheck((Check)new CPS("CPS", "A", CheckType.COMBAT, true));
        this.addCheck((Check)new AimA("Aim", "A", CheckType.COMBAT, true));
        this.addCheck((Check)new AimB("Aim", "B", CheckType.COMBAT, true));
        this.addCheck((Check)new AimC("Aim", "C", CheckType.COMBAT, true));
        this.addCheck((Check)new AimD("Aim", "D", CheckType.COMBAT, true));
        this.addCheck((Check)new AimE("Aim", "E", CheckType.COMBAT, true));
        this.addCheck((Check)new AimF("Aim", "F", CheckType.COMBAT, true));
        this.addCheck((Check)new AimG("Aim", "G", CheckType.COMBAT, true));
        this.addCheck((Check)new AimH("Aim", "H", CheckType.COMBAT, true));
        this.addCheck((Check)new AimI("Aim", "I", CheckType.COMBAT, true));
        this.addCheck((Check)new AimJ("Aim", "J", CheckType.COMBAT, true));
        this.addCheck((Check)new AimK("Aim", "K", CheckType.COMBAT, true));
        this.addCheck((Check)new AimL("Aim", "L", CheckType.COMBAT, true));
        this.addCheck((Check)new AimM("Aim", "M", CheckType.COMBAT, true));
        this.addCheck((Check)new AimN("Aim", "N", CheckType.COMBAT, true));
        this.addCheck((Check)new AimO("Aim", "O", CheckType.COMBAT, true));
        this.addCheck((Check)new AimP("Aim", "P", CheckType.COMBAT, true));
        this.addCheck((Check)new AimQ("Aim", "Q", CheckType.COMBAT, true));
        this.addCheck((Check)new AimR("Aim", "R", CheckType.COMBAT, true));
        this.addCheck((Check)new AimS("Aim", "S", CheckType.COMBAT, true));
        this.addCheck((Check)new AimT("Aim", "T", CheckType.COMBAT, true));
        this.addCheck((Check)new AimU("Aim", "U", CheckType.COMBAT, true));
        this.addCheck((Check)new AimV("Aim", "V", CheckType.COMBAT, true));
        this.addCheck((Check)new AimW("Aim", "W", CheckType.COMBAT, true));
        this.addCheck((Check)new AimX("Aim", "X", CheckType.COMBAT, true));
        this.addCheck((Check)new AimY("Aim", "Y", CheckType.COMBAT, true));
        this.addCheck((Check)new AimZ("Aim", "Z", CheckType.COMBAT, true));
        this.addCheck((Check)new AimAssistA("AimAssist", "A", CheckType.COMBAT, true));
        this.addCheck((Check)new AimAssistB("AimAssist", "B", CheckType.COMBAT, true));
        this.addCheck((Check)new AimAssistC("AimAssist", "C", CheckType.COMBAT, true));
        this.addCheck((Check)new ReachA("Reach", "A", CheckType.COMBAT, true));
        this.addCheck((Check)new AutoToolA("AutoTool", "A", CheckType.OTHER, true));
        this.addCheck((Check)new FlightA("Flight", "A", CheckType.MOVEMENT, true));
        this.addCheck((Check)new FlightB("Flight", "B", CheckType.MOVEMENT, true));
        this.addCheck((Check)new FlightC("Flight", "C", CheckType.MOVEMENT, true));
        this.addCheck((Check)new FlightD("Flight", "D", CheckType.MOVEMENT, true));
        this.addCheck((Check)new FlightE("Flight", "E", CheckType.MOVEMENT, true));
        this.addCheck((Check)new SpeedA("Speed", "A", CheckType.MOVEMENT, true));
        this.addCheck((Check)new SpeedB("Speed", "B", CheckType.MOVEMENT, true));
        this.addCheck((Check)new SpeedC("Speed", "C", CheckType.MOVEMENT, true));
        this.addCheck((Check)new SpeedD("Speed", "D", CheckType.MOVEMENT, true));
        this.addCheck((Check)new SpeedE("Speed", "E", CheckType.MOVEMENT, true));
        this.addCheck((Check)new MotionA("Motion", "A", CheckType.MOVEMENT, true));
        this.addCheck((Check)new MotionC("Motion", "C", CheckType.MOVEMENT, true));
        this.addCheck((Check)new HitBoxA("HitBox", "A", CheckType.COMBAT, true));
        this.addCheck((Check)new TimerA("Timer", "A", CheckType.OTHER, true));
        this.addCheck((Check)new BadPacketsA("BadPackets", "A", CheckType.OTHER, true));
        this.addCheck((Check)new BadPacketsB("BadPackets", "B", CheckType.OTHER, true));
        this.addCheck((Check)new BadPacketsC("BadPackets", "C", CheckType.OTHER, true));
        this.addCheck((Check)new BadPacketsD("BadPackets", "D", CheckType.OTHER, true));
        this.addCheck((Check)new BadPacketsE("BadPackets", "E", CheckType.OTHER, true));
        this.addCheck((Check)new BadPacketsF("BadPackets", "F", CheckType.OTHER, true));
        this.addCheck((Check)new BadPacketsG("BadPackets", "G", CheckType.OTHER, true));
        this.addCheck((Check)new BadPacketsH("BadPackets", "H", CheckType.OTHER, true));
        this.addCheck((Check)new BadPacketsI("BadPackets", "I", CheckType.OTHER, true));
        this.addCheck((Check)new BadPacketsJ("BadPackets", "J", CheckType.OTHER, true));
        this.addCheck((Check)new BadPacketsK("BadPackets", "K", CheckType.OTHER, true));
        this.addCheck((Check)new BadPacketsL("BadPackets", "L", CheckType.OTHER, true));
        this.addCheck((Check)new BadPacketsM("BadPackets", "M", CheckType.OTHER, true));
        this.addCheck((Check)new BadPacketsN("BadPackets", "N", CheckType.OTHER, true));
        this.addCheck((Check)new NoFall("NoFall", "A", CheckType.MOVEMENT, true));
        this.addCheck((Check)new NoSlowA("NoSlow", "A", CheckType.MOVEMENT, true));
        this.addCheck((Check)new AutoBlockA("Autoblock", "A", CheckType.COMBAT, true));
        if (Overflow.getInstance().isAddonHooked()) {
            Overflow.getInstance().getLogger().info("Hooked into Phase Addon");
            this.addCheck((Check)new PhaseA("Phase", "A", CheckType.MOVEMENT, true));
            this.addCheck((Check)new PhaseB("Phase", "B", CheckType.MOVEMENT, true));
        }
        this.registerAll();
    }
    
    private void registerAll() {
        ChecksFile.getInstance().setup((Plugin)Overflow.getInstance());
        this.checkList.forEach(CheckManager::lambda$registerAll$0);
        ChecksFile.getInstance().saveData();
    }
    
    public void saveAll() {
        ChecksFile.getInstance().setup((Plugin)Overflow.getInstance());
        this.checkList.forEach(CheckManager::lambda$saveAll$1);
        ChecksFile.getInstance().saveData();
    }
    
    public void unRegisterAll() {
        this.checkList.parallelStream().forEach(CheckManager::lambda$unRegisterAll$2);
    }
    
    private void addCheck(final Check check) {
        if (check.isEnabled() && !this.checkList.contains(check)) {
            this.checkList.add(check);
        }
    }
    
    public List<Check> getCheckList() {
        return (List<Check>)this.checkList;
    }
    
    static {
        CheckManager.flag = true;
    }
}
