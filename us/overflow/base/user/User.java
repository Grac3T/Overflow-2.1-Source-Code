// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.base.user;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import us.overflow.utils.MathUtil;
import org.bukkit.potion.PotionEffectType;
import us.overflow.utils.block.BlockUtil;
import org.bukkit.Material;
import us.overflow.Overflow;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import us.overflow.api.VelocitySnapshot;
import java.util.Deque;
import us.overflow.utils.other.EvictingList;
import us.overflow.base.user.global.GlobalObject;
import us.overflow.base.processors.PredictionProcessor;
import us.overflow.base.processors.CombatProcessor;
import us.overflow.base.processors.LagProcessor;
import us.overflow.base.processors.MovementProcessor;
import java.util.LinkedList;
import us.overflow.base.CheckType;
import us.overflow.utils.minecraft.MCSmoothing;
import java.util.List;
import us.overflow.probability.ProbabilitySystem;
import us.overflow.utils.location.PastLocation;
import org.bukkit.entity.Entity;
import us.overflow.utils.other.Verbose;
import us.overflow.utils.other.Timer;
import us.overflow.utils.MovingStats;
import us.overflow.utils.block.BlockAssesement;
import us.overflow.utils.blockbox.BoundingBox;
import us.overflow.utils.location.CustomLocation;
import org.bukkit.Location;
import java.util.UUID;
import org.bukkit.entity.Player;

public class User
{
    public Player player;
    private UUID uuid;
    public Location to;
    public Location from;
    public Location oldTo;
    public Location oldFrom;
    public CustomLocation lastGroundLocation;
    private BoundingBox boundingBox;
    private BlockAssesement blockAssesement;
    public boolean wasFlying;
    public boolean setClientSens;
    public boolean invalidMotionXZ;
    public boolean sneaking;
    public boolean motionXZReaady;
    public boolean respawn;
    public boolean explode;
    public boolean collidesHorizontally;
    public boolean collidesVertically;
    public boolean autoClickerESent;
    public boolean autoToolSent;
    public boolean movementChecksOK;
    public boolean speedBLastGroundLocation;
    public boolean speedBLastGround;
    public boolean nearBoat;
    public boolean isBreakingAFUCKINGBLOCK;
    public boolean badPacketsDYInvalid;
    public boolean jumpPad;
    public boolean setAimAssistF;
    public boolean isGAY;
    public boolean sprinting;
    public boolean aimAssistAWork;
    public boolean aimOSet;
    public boolean hasSentPacketBadPacketsD;
    public boolean badPacketsDSprinting;
    public boolean isHasBouncedOnSlime;
    public boolean hasAlerts;
    public boolean wasOnGround;
    public boolean lastGroundGround;
    public boolean lastGround2;
    public boolean aimPSet;
    public boolean attacksSet;
    public boolean aimJExpectingNext;
    public boolean nextToWall;
    public boolean didSwitchGamemode;
    public boolean banned;
    public boolean hasSentH4;
    public boolean hasSentH3;
    public boolean hasSentH2;
    public boolean hasSentH;
    public boolean hasSent;
    public boolean breakingBlock;
    public boolean packetClientGround;
    public boolean teleportedNoMove;
    public boolean clientGround;
    public boolean lastClientGround;
    public boolean lastonground;
    public boolean onGroundMixed;
    public boolean wasOnGroundMixed;
    public boolean laggin2;
    public boolean isOnSlime;
    public boolean slime;
    public boolean lagging;
    public boolean onGround;
    public boolean lastGround;
    public int autoClickerCVerbose;
    public int clientSens;
    public int aimYBuffer;
    public int autoClickerGTicks;
    public int timerBVerbose;
    public int stableMotionACount;
    public int motionASamePrediction;
    public int stableMotionADecrease;
    public int tableMotionACount;
    public int soulSandTicks;
    public int autoblockALastTick;
    public int tick;
    public int motionBVerbose;
    public int flightEVerbose;
    public int flightETicks;
    public int aimAssistCVerbose;
    public int autoClickerFCps;
    public int autoClickerFTicks;
    public int cps;
    public int cpsTicks;
    public int autoClickerDVerbose;
    public int autoClickerDTicks;
    public int flightDVerbose;
    public int flightCHoverVerbose;
    public int flightCAccelerationVerbose;
    public int flightCAir;
    public int motionAVerbose;
    public int discordBotVL;
    public int totalBlocksCheck;
    public int badPacketsBStable;
    public int badPacketsBVerbose1;
    public int snowTicks;
    public int speedBVerbose;
    public int speedAVerbose;
    public int boatTicks;
    public int flightBVerbose;
    public int climbableTicks;
    public int flightADistance;
    public int flightAVerbose;
    public int fuckingCPS;
    public int totalGaySlots;
    public int lastGayAssSlot;
    public int aimAssistFVerbose;
    public int noSlowAVerbose;
    public int vioSplit;
    public int packetsSkipped;
    public int fullTickSkipped;
    public int aimAssistHCounter;
    public int aimAssistHVerbose1Last;
    public int aimAssistHPitchSame;
    public int aimAssistHVerbose1;
    public int lastAimWExpect;
    public int aimWVerbose2;
    public int lastAimWVerbose;
    public int aimWVerbose;
    public int lastAimWLen;
    public int kys52;
    public int aimAssistBCount;
    public int kys;
    public int killauraA2Ticks2;
    public int killauraA2Ticks1;
    public int killauraKCount;
    public int aimTVerbose;
    public int totalticks;
    public int voidTicks;
    public int flyingTicks;
    public int blockcancelTicks;
    public int constantEntityTicks;
    public int aimS1Verbose;
    public int aimOPartTick;
    public int aimOVerboseNigger;
    public int aimOYawStable;
    public int respawnTicks;
    public int badPacketsKVerbose;
    public int badPacketsJVerbose;
    public int lastBadPacketsEBlockDigTick;
    public int badPacketsEVerbose;
    public int lastLastBadPacketsEBP;
    public int lastBadPacketsEBlockPlaceTick;
    public int lastLastBadPacketsEBD;
    public int oldTicks;
    public int KBTicks;
    public int yVelocityTicks;
    public int hVelocityTicks;
    public int joinTicks;
    public int teleportTicks;
    public int Deadticks;
    public int waterTicks;
    public int aimNOptifineTicks;
    public int aimMVerboseFuckYou;
    public int slimeReset2;
    public int slimeReset;
    public int slimeTicks;
    public int predictionDVerbose;
    public int aimCVerbose;
    public int gravityVerbose;
    public int jumpPotionTicks;
    public int aimQAttacks;
    public int aimQVerbose;
    public int aimPCounter;
    public int aimPVerbose;
    public int attacks;
    public int aimAssistAVerbose2;
    public int aimAssistAVerbose;
    public int aimOPredictionTicks;
    public int killauraNTicks1;
    public int killauraNTicks2;
    public int aimMVerbose;
    public int aimLVerbose;
    public int badPacketsDPlaceAmount;
    public int violationSplit;
    public int pistionTicks;
    public int railTicks;
    public int badPacketsCVerbose;
    public int badPacketsOptifineTicks;
    public int aimKVerbose;
    public int jumpBoostTicks;
    public int aimJStable;
    public int aimJVerbose;
    public int aimJZeroHits;
    public int aimJGoodHits;
    public int aimJBadHits;
    public int aimIVerbose;
    public int aimHVerbose;
    public int aimHOptifineTicks;
    public int nextToWallTicks;
    public int cancelTicks;
    public int tickSendH4;
    public int velocityEVerbose;
    public int velocityDVerbose;
    public int tickSendH3;
    public int tickSendH2;
    public int velocityCVerbose;
    public int tickSendH;
    public int velocityBVerbose;
    public int velocityVerbose;
    public int tickSend;
    public int timerAVerbose;
    public int timerCVerbose;
    public int badPacksBVerbose;
    public int badPacketsAVerbose;
    public int swings;
    public int aimEVerbose;
    public int aimDVerbose;
    public int fenceTicks;
    public int wallTicks;
    public int webTicks;
    public int groundTicksMixed;
    public int airTicksMixed;
    public int speedPotionTicks;
    public int speedDVerbose;
    public int climableTicks;
    public int reachAVerbose;
    public int aimAOptifineVerbose;
    public int aimAVerbose;
    public int violation;
    public int groundTicks;
    public int airTicks;
    public int ping;
    public int stairTicks;
    public int slabTicks;
    public int blockAboveTicks;
    public int liquidTicks;
    public int iceTicks;
    public int nearIceTicks;
    public int halfBlockTicks;
    public int mountedTicks;
    public long lastMotionASpeedPotion;
    public long lastRespawn;
    public long lastBowDamage;
    public long lastExplode;
    public long lastSlime;
    public long lastTimerAFlying;
    public long autoClickerEFlying;
    public long autoClickerESwing;
    public long autoToolFlying;
    public long autoToolHeld;
    public long lastBlockJump;
    public long lastniggerarm;
    public long badPacketsDDown;
    public long lastPhaseAFlag;
    public long lastJumpPadUpdate;
    public long lastJumpPadSet;
    public long lastAimAssistF;
    public long lastSkippedTicksLag;
    public long lastSkippedFlying;
    public long lastAimAssistHGCDYaw;
    public long lastAimAssistHLook;
    public long lastAimAssistHPostion;
    public long lastTransShit;
    public long lastPlace;
    public long lastAimAssistGCD;
    public long lastKillauraHBad;
    public long lastKillauraEGPitch;
    public long lastAimOSetTime2;
    public long lastAimOSetTime1;
    public long lastBadPacketsKKeepAlive;
    public long lastBadPacketsKTransaction;
    public long lastBadPacketsKeepAlive;
    public long lastBadPacketsITransaction;
    public long lastBadPacketsFBlockPlace;
    public long lastBadPacketsFBlockDig;
    public long lastBadPacketsEBlockDig;
    public long getLastSlimeBounced;
    public long lastCancelPlace;
    public long lastAimPPos;
    public long lastAimPSet;
    public long lastAttacksSet;
    public long lastAimAssistUp;
    public long lastAimAssistDip;
    public long lastYawGCDAimAssistA;
    public long lastAimOYawChange;
    public long lastAimOPitchChange;
    public long lastAimOMove;
    public long lastBadPacketDDig;
    public long lastBadPacketsDBlockPlace;
    public long lastBadPacketsCFlag;
    public long lastBadPacketPredict;
    public long lastBadPacketsBPos;
    public long lastAimJNegro;
    public long lastAimJSomeWhatAgOODDvALUE;
    public long lastAimHPostion;
    public long lastAttackByEntity;
    public long lastGayModeSwitch;
    public long lastDamage;
    public long lastTime;
    public long lastTime2;
    public long lastBadPacketsBBlockDig;
    public long lastHasPos;
    public long timerALastTime;
    public long lastUnknownTeleport;
    public long lastBadPacketsAFlying;
    public long lastFullBlockMove;
    public long lastAimEPositionLook;
    public long lastAimEPosition;
    public long lastBlockBreakCancel;
    public long lastAttackedByPlayer;
    public long lastAlertRec;
    public long lastLag2;
    public long lastTransaction2;
    public long lastServerTransaction2;
    public long lastFlightToggle;
    public long lastGamemodeSwitch;
    public long lastJoin;
    public long lastLag;
    public long transactionPing;
    public long calculatedPing;
    public long lastCalulated;
    public long lastTransUpdate;
    public long lastTransactionReciv;
    public long calculatedRealPing;
    public long lastLastFucker;
    public long transactionLast;
    public long lastClientTransaction;
    public long lastServerTransaction;
    public long lastTransactionPing;
    public long lastTransDiff;
    public long lastAttackPacket;
    public long lastIce;
    public long lastEntityVelocity;
    public long lastTeleport;
    public long lastBukkitMovement;
    public long lastServerKeepAlive;
    public double velocityLength;
    public double aimLThreshold;
    public double autoClickerGCps;
    public double lastMotionAPrediction;
    public double motionAThreshold;
    public double lastMotionAPredictionDiff;
    public double lastPredictedDiff;
    public double autoClickerEVerbose;
    public double lastAutoClickerDSkewness;
    public double lastAutoClickerDKurtosis;
    public double lastAutoClickerDAverage;
    public double lastFlightDGround;
    public double lastFlightCAcceleration;
    public double lastFlightCDelta;
    public double lastMotionAHorizontal;
    public double lastBadPacketsBY;
    public double badPacketsBDiff;
    public double lastSpeedFSpeed;
    public double lastSpeedBDistance;
    public double lastFlighBDiff;
    public double lastAimDPitch;
    public double lastAimAssistHVal1;
    public double lastAimAssistHGCDPitch;
    public double lastAimWPitch;
    public double lastAimWShort;
    public double lastAimWTrim;
    public double lastFixerIDK;
    public double killauraA2LastValue;
    public double aimSLastYaw;
    public double aimOLastValue;
    public double lastAimNValue;
    public double avgPitchSpeed;
    public double avgPitchDevation;
    public double lastYDiff;
    public double lastY;
    public double lastAimQPrediction;
    public double lastAimQPitch;
    public double lastAimQYaw;
    public double lastAimQTurn;
    public double lastAimQGCD;
    public double aimQLastFixed;
    public double aimQPrediction;
    public double lastAimPYawDiff;
    public double lastAimAssistAPitchDiff;
    public double lastAimAssitAYawDiff;
    public double killauraNLast;
    public double lastAimMPitch;
    public double lastAimKDiff;
    public double lastAimKMoveSpeed;
    public double lastAimIPitchDiff;
    public double lastAimIYawDiff;
    public double lastAimHYawDiff;
    public double balance;
    public double timerABal;
    public double aimassistBVerbose;
    public double lastAimAssistBVal;
    public double lastAimEDiff;
    public double lastAimDValue;
    public double predictionDLastY;
    public double lastPredictedSpeed;
    public double lastongroundspeed;
    public double lastSpeedPotionMP;
    public double lastAimCPitchDiff;
    public double lastAimAPitch;
    public double movementSpeed;
    public double deltaXZ;
    public double deltaY;
    public double deltaX;
    public double deltaZ;
    public double lastDeltaXZ;
    public double lastDeltaY;
    public float lastAimAssistFPrevPitch;
    public float lastAimAssistFPrevYaw;
    public float aimAssistsFPitch;
    public float aimAssistsFYaw;
    public float lastAimAssistHYaw;
    public float lastAimAssistHPitch;
    public float lastAimAssistAPitch;
    public float lastAimAssistAYaw;
    public Location flightGGroundLocation;
    public MovingStats movingStats;
    public Location tpLocation;
    public Location CancelLocation;
    public long lastServerVelocity;
    public long lastTypeCSwing;
    public long lastDoubleClick;
    public long LastFlag;
    public Timer cpsTimer;
    private String clientBrand;
    public float lastYawChangeA;
    public float lastPitchChangeA;
    public Verbose noFallAVerbose;
    public Verbose speedFVerbose;
    public Verbose speedCVerbose;
    public Verbose aimD111Verbose;
    public Verbose badPacketsJVerbose1;
    public Verbose aimAssistHVerbose2;
    public Verbose aimWVerbose1;
    public Verbose aimAssistVerbose;
    public Verbose killauraA2Verbose;
    public Verbose killauraKVerboseFix;
    public Verbose aimSVerbose;
    public Verbose badPacketsFVerbose;
    public Verbose aimOVerbose;
    public Verbose aimNVerbose;
    public Verbose predictionEVerbose;
    public Verbose killauraLVerbose;
    public Verbose badPacketsBVerbose;
    public Verbose killauraNVerbose;
    public Verbose hitBoxAVerbose;
    public Verbose badPacketsDVerbose2;
    public Verbose badPacketsDVerbose;
    public Verbose aimassistBVerbose2;
    public Verbose aimIVerbose2;
    public Verbose criticalsAVerbose;
    public Verbose aimDVerbose2;
    public Verbose aimBVerbose;
    public Entity lastEntityAttacked;
    public PastLocation reachAPastLocations;
    public PastLocation reachBPastLocations;
    public PastLocation hitBoxPastLocations;
    public CustomLocation lastFlightDLocation;
    public CustomLocation newTo;
    public CustomLocation newFrom;
    public CustomLocation lastFlightDGroundLocation;
    public CustomLocation lastOnGroundLocation;
    public ProbabilitySystem probabilitySystem;
    public List<Double> aimAssistsAPatterns;
    public List<Double> aimQPatterns;
    public List<Double> aimSSamples;
    public List<Double> aimWSamples;
    public long lastTimerBFlying;
    public long lastOptifine;
    public long lastOptifineREE;
    public long lastRetardOpitfineSpam;
    public long lastAimAssistACE;
    public int optifineSmoothing2;
    public int lastClientSmoothingValue;
    public int optifineSmoothing;
    public int LastSmoothingCounter;
    public int smoothingCounter;
    public int optifineSmoothSens;
    public int optifinePitchSmooth;
    public int optifineSameCount;
    public int optifineConstantVL2;
    public int optifineConstantVL;
    public int optifineSmoothingFix;
    public int killauraAYawReset;
    public int killauraAPitchReset;
    public int aimAssistsACount;
    public int optifineSmoothingTicks;
    public MCSmoothing aimWSmooth;
    public MCSmoothing newPitchSmoothing;
    public MCSmoothing newYawSmoothing;
    public MCSmoothing yaw;
    public MCSmoothing pitch;
    public MCSmoothing smooth;
    public double lastSmoothingRot2;
    public double lastSmoothingRot;
    public double lastPitchDelta;
    public double lastSmoothPitch1;
    public double lastOptifinePitchSmoothidfklol;
    public float lastYawDelta;
    public float lastSmoothYaw;
    public boolean cineCamera;
    private CheckType lastGUICheckType;
    int ticksB;
    int autoClickerBVerbose;
    int ticksC;
    int autoClickerAVerbose;
    int ticksA;
    int ticksD;
    public double avgSpeedB;
    public double avgDevationB;
    public double avgSpeedC;
    public double avgDevationC;
    public double avgSpeedA;
    public double avgDevationA;
    public LinkedList<Integer> recentCountsC;
    public LinkedList<Integer> recentCountsD;
    private MovementProcessor movementProcessor;
    private LagProcessor lagProcessor;
    private CombatProcessor combatProcessor;
    private PredictionProcessor predictionProcessor;
    private GlobalObject globalObject;
    public double genericAttributesSpeed;
    public CustomLocation retardedLocation;
    public int autoClickerBTicks;
    public int autoClickerETicks;
    public int autoClickerHTicks;
    public LinkedList<Integer> autoClickerBCounts;
    public LinkedList<Integer> autoClickerECounts;
    public double lastAutoClickerESTD;
    public double lastAutoClickerHRange;
    public double avgAutoClickerH;
    public List<Double> autoClickerHPattern;
    public EvictingList<Integer> autoClickerDSamples;
    public final Deque<Double> autoClickerGSamples;
    public final Deque<Long> timerADeque;
    public final Deque<Integer> autoClickerFSamples;
    public final Deque<Float> aimAssistCSamples;
    public final Deque<Float> aimYPitchSamples;
    public final Deque<Float> aimYYawSamples;
    public List<VelocitySnapshot> velocityLengthSamples;
    
    public User(final Player player) {
        this.boundingBox = new BoundingBox(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.hasAlerts = true;
        this.movingStats = new MovingStats(20);
        this.cpsTimer = new Timer();
        this.noFallAVerbose = new Verbose();
        this.speedFVerbose = new Verbose();
        this.speedCVerbose = new Verbose();
        this.aimD111Verbose = new Verbose();
        this.badPacketsJVerbose1 = new Verbose();
        this.aimAssistHVerbose2 = new Verbose();
        this.aimWVerbose1 = new Verbose();
        this.aimAssistVerbose = new Verbose();
        this.killauraA2Verbose = new Verbose();
        this.killauraKVerboseFix = new Verbose();
        this.aimSVerbose = new Verbose();
        this.badPacketsFVerbose = new Verbose();
        this.aimOVerbose = new Verbose();
        this.aimNVerbose = new Verbose();
        this.predictionEVerbose = new Verbose();
        this.killauraLVerbose = new Verbose();
        this.badPacketsBVerbose = new Verbose();
        this.killauraNVerbose = new Verbose();
        this.hitBoxAVerbose = new Verbose();
        this.badPacketsDVerbose2 = new Verbose();
        this.badPacketsDVerbose = new Verbose();
        this.aimassistBVerbose2 = new Verbose();
        this.aimIVerbose2 = new Verbose();
        this.criticalsAVerbose = new Verbose();
        this.aimDVerbose2 = new Verbose();
        this.aimBVerbose = new Verbose();
        this.reachAPastLocations = new PastLocation();
        this.reachBPastLocations = new PastLocation();
        this.hitBoxPastLocations = new PastLocation();
        this.probabilitySystem = new ProbabilitySystem();
        this.aimAssistsAPatterns = new ArrayList<Double>();
        this.aimQPatterns = new ArrayList<Double>();
        this.aimSSamples = new ArrayList<Double>();
        this.aimWSamples = new ArrayList<Double>();
        this.aimWSmooth = new MCSmoothing();
        this.newPitchSmoothing = new MCSmoothing();
        this.newYawSmoothing = new MCSmoothing();
        this.yaw = new MCSmoothing();
        this.pitch = new MCSmoothing();
        this.smooth = new MCSmoothing();
        this.recentCountsC = new LinkedList<Integer>();
        this.recentCountsD = new LinkedList<Integer>();
        this.retardedLocation = new CustomLocation(0.0, 0.0, 0.0);
        this.autoClickerBCounts = new LinkedList<Integer>();
        this.autoClickerECounts = new LinkedList<Integer>();
        this.autoClickerHPattern = (List<Double>)Lists.newArrayList();
        this.autoClickerDSamples = (EvictingList<Integer>)new EvictingList(20);
        this.autoClickerGSamples = new LinkedList<Double>();
        this.timerADeque = new LinkedList<Long>();
        this.autoClickerFSamples = new LinkedList<Integer>();
        this.aimAssistCSamples = new LinkedList<Float>();
        this.aimYPitchSamples = new LinkedList<Float>();
        this.aimYYawSamples = new LinkedList<Float>();
        this.velocityLengthSamples = new ArrayList<VelocitySnapshot>();
        this.player = player;
        this.uuid = player.getUniqueId();
        this.setUpProcessors();
        this.to = player.getLocation();
        this.from = player.getLocation();
        this.oldFrom = this.from;
        this.oldTo = this.to;
        this.lastJoin = System.currentTimeMillis();
        this.setJoinTicks(0);
        final GlobalObject globalObject;
        Overflow.getInstance().getExecutorService().execute(() -> {
            globalObject = Overflow.getInstance().getGlobalUserManager().getUser(player.getUniqueId().toString());
            if (!Overflow.getInstance().getDataHeleper().getObjectedUUIDs().contains(player.getUniqueId().toString()) && globalObject == null) {
                Overflow.getInstance().getDataHeleper().getObjectedUUIDs().add(player.getUniqueId().toString());
                Overflow.getInstance().getGlobalUserManager().addUser(new GlobalObject(player.getUniqueId().toString()));
            }
            this.globalObject = globalObject;
            return;
        });
        this.flightGGroundLocation = new Location(player.getWorld(), 0.0, 0.0, 0.0);
    }
    
    public void setUpProcessors() {
        (this.movementProcessor = new MovementProcessor()).setUser(this);
        (this.lagProcessor = new LagProcessor()).setUser(this);
        (this.combatProcessor = new CombatProcessor()).setUser(this);
    }
    
    public boolean hasSwordInHand() {
        return this.player.getItemInHand().getType() == Material.DIAMOND_SWORD || this.player.getItemInHand().getType() == Material.IRON_SWORD || this.player.getItemInHand().getType() == Material.GOLD_SWORD || this.player.getItemInHand().getType() == Material.STONE_SWORD || this.player.getItemInHand().getType() == Material.WOOD_SWORD;
    }
    
    public boolean isAllLagging() {
        return System.currentTimeMillis() - this.lastLag < 1000L || this.isLagging();
    }
    
    public void update(final UpdateType updateType) {
        if (updateType == UpdateType.TICKS) {
            if ((this.getPlayer().isFlying() || this.getPlayer().getAllowFlight()) && !this.wasFlying) {
                this.wasFlying = true;
            }
            else if (this.wasFlying && !this.getPlayer().isFlying() && !this.getPlayer().getAllowFlight() && this.onGround) {
                this.wasFlying = false;
            }
            if (this.respawn && System.currentTimeMillis() - this.lastRespawn > 1000L) {
                this.respawn = false;
            }
            if (System.currentTimeMillis() - this.lastExplode > 1000L && this.explode && this.onGround && this.lastGround) {
                this.explode = false;
            }
            if (this.getPlayer().getVehicle() != null) {
                if (this.mountedTicks < 50) {
                    ++this.mountedTicks;
                }
            }
            else if (this.mountedTicks > 0) {
                --this.mountedTicks;
            }
            if (this.getBoundingBox() != null && this.newTo != null) {
                final Block block = BlockUtil.getBlock(this.newTo.toLocation(this.getPlayer().getWorld()).clone().add(0.0, -1.0, 0.0));
                if (block != null && Overflow.getInstance().getVersion() != Overflow.Version.V1_7 && this.isOnGround()) {
                    if (block.getType() == Material.SLIME_BLOCK && !this.slime) {
                        this.slime = true;
                    }
                    else if (this.slime && block.getType() != Material.AIR && block.getType() != Material.SLIME_BLOCK) {
                        this.slime = false;
                    }
                }
            }
            final boolean nearBoat = BlockUtil.isOnFuckingBoat(this.getPlayer());
            this.nearBoat = nearBoat;
            if (this.nearBoat) {
                if (this.boatTicks < 50) {
                    ++this.boatTicks;
                }
            }
            else if (this.boatTicks > 0) {
                --this.boatTicks;
            }
            if (this.onGround) {
                this.lastOnGroundLocation = this.getNewTo();
                this.airTicks = 0;
                if (this.groundTicks < 50) {
                    ++this.groundTicks;
                }
            }
            else {
                this.groundTicks = 0;
                if (this.airTicks < 50) {
                    ++this.airTicks;
                }
            }
            if (this.player.hasPotionEffect(PotionEffectType.JUMP)) {
                if (this.jumpPotionTicks < 50) {
                    ++this.jumpPotionTicks;
                }
            }
            else if (this.jumpPotionTicks > 0) {
                --this.jumpPotionTicks;
            }
            if (this.onGroundMixed) {
                this.airTicksMixed = 0;
                if (this.groundTicksMixed < 20) {
                    ++this.groundTicksMixed;
                }
            }
            else {
                this.groundTicksMixed = 0;
                if (this.airTicksMixed < 20) {
                    ++this.airTicksMixed;
                }
            }
            if (this.blockAssesement != null) {
                this.collidesVertically = this.blockAssesement.isCollidesVertically();
                this.collidesHorizontally = this.blockAssesement.isCollidesHorizontally();
                if (this.blockAssesement.isSoulSand()) {
                    if (this.soulSandTicks < 20) {
                        ++this.soulSandTicks;
                    }
                }
                else if (this.soulSandTicks > 0) {
                    --this.soulSandTicks;
                }
                if (this.blockAssesement.isSnow()) {
                    if (this.snowTicks < 20) {
                        ++this.snowTicks;
                    }
                }
                else if (this.snowTicks > 0) {
                    --this.snowTicks;
                }
                if (this.blockAssesement.isPistion()) {
                    if (this.pistionTicks < 20) {
                        ++this.pistionTicks;
                    }
                }
                else if (this.pistionTicks > 0) {
                    --this.pistionTicks;
                }
                if (this.blockAssesement.isRail()) {
                    if (this.railTicks < 20) {
                        ++this.railTicks;
                    }
                }
                else if (this.railTicks > 0) {
                    --this.railTicks;
                }
                if (this.blockAssesement.isStair()) {
                    if (this.stairTicks < 20) {
                        ++this.stairTicks;
                    }
                }
                else if (this.stairTicks > 0) {
                    --this.stairTicks;
                }
                if (this.blockAssesement.isFence()) {
                    if (this.fenceTicks < 20) {
                        ++this.fenceTicks;
                    }
                }
                else if (this.fenceTicks > 0) {
                    --this.fenceTicks;
                }
                if (this.blockAssesement.isWeb()) {
                    this.setWebTicks(0);
                }
                if (this.blockAssesement.isWall()) {
                    if (this.wallTicks < 20) {
                        ++this.wallTicks;
                    }
                }
                else if (this.wallTicks > 0) {
                    --this.wallTicks;
                }
                if (this.blockAssesement.isSlab()) {
                    if (this.slabTicks < 20) {
                        ++this.slabTicks;
                    }
                }
                else if (this.slabTicks > 0) {
                    --this.slabTicks;
                }
                if (this.blockAssesement.isBlockAbove()) {
                    if (this.blockAboveTicks < 20) {
                        ++this.blockAboveTicks;
                    }
                }
                else if (this.blockAboveTicks > 0) {
                    --this.blockAboveTicks;
                }
                if (this.blockAssesement.isLiquid()) {
                    if (this.liquidTicks < 20) {
                        ++this.liquidTicks;
                    }
                }
                else if (this.liquidTicks > 0) {
                    --this.liquidTicks;
                }
                if (this.blockAssesement.isOnIce()) {
                    this.lastIce = System.currentTimeMillis();
                    if (this.iceTicks < 50) {
                        ++this.iceTicks;
                    }
                }
                else if (this.iceTicks > 0) {
                    --this.iceTicks;
                }
                if (this.blockAssesement.isSlime()) {
                    if (this.slimeTicks < 20) {
                        ++this.slimeTicks;
                    }
                    this.lastSlime = System.currentTimeMillis();
                }
                else if (this.slimeTicks > 0) {
                    --this.slimeTicks;
                }
                if (this.blockAssesement.isOnNearIce()) {
                    if (this.nearIceTicks < 20) {
                        ++this.nearIceTicks;
                    }
                }
                else if (this.nearIceTicks > 0) {
                    --this.nearIceTicks;
                }
                if (this.blockAssesement.isHalfblock()) {
                    if (this.halfBlockTicks < 20) {
                        ++this.halfBlockTicks;
                    }
                }
                else if (this.halfBlockTicks > 0) {
                    --this.halfBlockTicks;
                }
                if (this.blockAssesement.isClimbale()) {
                    if (this.climbableTicks < 20) {
                        ++this.climbableTicks;
                    }
                }
                else if (this.climbableTicks > 0) {
                    --this.climbableTicks;
                }
            }
            if (this.player.hasPotionEffect(PotionEffectType.SPEED)) {
                if (this.speedPotionTicks < 20) {
                    ++this.speedPotionTicks;
                }
                this.lastSpeedPotionMP = MathUtil.getPotionEffectLevel(this.player, PotionEffectType.SPEED);
            }
            else if (this.speedPotionTicks > 0) {
                --this.speedPotionTicks;
            }
            if (this.CancelLocation == null) {
                this.CancelLocation = this.getNewTo().toLocation(this.getPlayer().getWorld());
            }
            if (System.currentTimeMillis() - this.LastFlag > 350L && this.isClientGround() && this.isOnGround() && this.getNewTo().getY() % 0.015625 == 0.0 && this.deltaY <= 0.41999998688697815) {
                this.CancelLocation = this.getNewFrom().toLocation(this.getPlayer().getWorld());
            }
        }
    }
    
    public int getMaxPingTicks(final int value) {
        return (int)Math.ceil(this.ping / 50.0) + value;
    }
    
    public boolean isSurvival() {
        return (this.getPlayer().getGameMode() == GameMode.SURVIVAL || this.getPlayer().getGameMode() == GameMode.ADVENTURE) && !this.generalCancel();
    }
    
    public boolean isUsingOptifine() {
        return this.optifineSmoothing > 0 || this.optifineSmoothingFix > 2 || this.optifineConstantVL2 > 5;
    }
    
    public boolean generalCancel() {
        return this.wasFlying || this.player.isFlying() || this.player.getAllowFlight() || this.player.getGameMode().equals((Object)GameMode.CREATIVE);
    }
    
    public boolean checkNull() {
        return this.to != null && this.from != null && this.newTo != null && this.newFrom != null && this.oldFrom != null && this.oldTo != null;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public UUID getUuid() {
        return this.uuid;
    }
    
    public Location getTo() {
        return this.to;
    }
    
    public Location getFrom() {
        return this.from;
    }
    
    public Location getOldTo() {
        return this.oldTo;
    }
    
    public Location getOldFrom() {
        return this.oldFrom;
    }
    
    public CustomLocation getLastGroundLocation() {
        return this.lastGroundLocation;
    }
    
    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }
    
    public BlockAssesement getBlockAssesement() {
        return this.blockAssesement;
    }
    
    public boolean isWasFlying() {
        return this.wasFlying;
    }
    
    public boolean isSetClientSens() {
        return this.setClientSens;
    }
    
    public boolean isInvalidMotionXZ() {
        return this.invalidMotionXZ;
    }
    
    public boolean isSneaking() {
        return this.sneaking;
    }
    
    public boolean isMotionXZReaady() {
        return this.motionXZReaady;
    }
    
    public boolean isRespawn() {
        return this.respawn;
    }
    
    public boolean isExplode() {
        return this.explode;
    }
    
    public boolean isCollidesHorizontally() {
        return this.collidesHorizontally;
    }
    
    public boolean isCollidesVertically() {
        return this.collidesVertically;
    }
    
    public boolean isAutoClickerESent() {
        return this.autoClickerESent;
    }
    
    public boolean isAutoToolSent() {
        return this.autoToolSent;
    }
    
    public boolean isMovementChecksOK() {
        return this.movementChecksOK;
    }
    
    public boolean isSpeedBLastGroundLocation() {
        return this.speedBLastGroundLocation;
    }
    
    public boolean isSpeedBLastGround() {
        return this.speedBLastGround;
    }
    
    public boolean isNearBoat() {
        return this.nearBoat;
    }
    
    public boolean isBreakingAFUCKINGBLOCK() {
        return this.isBreakingAFUCKINGBLOCK;
    }
    
    public boolean isBadPacketsDYInvalid() {
        return this.badPacketsDYInvalid;
    }
    
    public boolean isJumpPad() {
        return this.jumpPad;
    }
    
    public boolean isSetAimAssistF() {
        return this.setAimAssistF;
    }
    
    public boolean isGAY() {
        return this.isGAY;
    }
    
    public boolean isSprinting() {
        return this.sprinting;
    }
    
    public boolean isAimAssistAWork() {
        return this.aimAssistAWork;
    }
    
    public boolean isAimOSet() {
        return this.aimOSet;
    }
    
    public boolean isHasSentPacketBadPacketsD() {
        return this.hasSentPacketBadPacketsD;
    }
    
    public boolean isBadPacketsDSprinting() {
        return this.badPacketsDSprinting;
    }
    
    public boolean isHasBouncedOnSlime() {
        return this.isHasBouncedOnSlime;
    }
    
    public boolean isHasAlerts() {
        return this.hasAlerts;
    }
    
    public boolean isWasOnGround() {
        return this.wasOnGround;
    }
    
    public boolean isLastGroundGround() {
        return this.lastGroundGround;
    }
    
    public boolean isLastGround2() {
        return this.lastGround2;
    }
    
    public boolean isAimPSet() {
        return this.aimPSet;
    }
    
    public boolean isAttacksSet() {
        return this.attacksSet;
    }
    
    public boolean isAimJExpectingNext() {
        return this.aimJExpectingNext;
    }
    
    public boolean isNextToWall() {
        return this.nextToWall;
    }
    
    public boolean isDidSwitchGamemode() {
        return this.didSwitchGamemode;
    }
    
    public boolean isBanned() {
        return this.banned;
    }
    
    public boolean isHasSentH4() {
        return this.hasSentH4;
    }
    
    public boolean isHasSentH3() {
        return this.hasSentH3;
    }
    
    public boolean isHasSentH2() {
        return this.hasSentH2;
    }
    
    public boolean isHasSentH() {
        return this.hasSentH;
    }
    
    public boolean isHasSent() {
        return this.hasSent;
    }
    
    public boolean isBreakingBlock() {
        return this.breakingBlock;
    }
    
    public boolean isPacketClientGround() {
        return this.packetClientGround;
    }
    
    public boolean isTeleportedNoMove() {
        return this.teleportedNoMove;
    }
    
    public boolean isClientGround() {
        return this.clientGround;
    }
    
    public boolean isLastClientGround() {
        return this.lastClientGround;
    }
    
    public boolean isLastonground() {
        return this.lastonground;
    }
    
    public boolean isOnGroundMixed() {
        return this.onGroundMixed;
    }
    
    public boolean isWasOnGroundMixed() {
        return this.wasOnGroundMixed;
    }
    
    public boolean isLaggin2() {
        return this.laggin2;
    }
    
    public boolean isOnSlime() {
        return this.isOnSlime;
    }
    
    public boolean isSlime() {
        return this.slime;
    }
    
    public boolean isLagging() {
        return this.lagging;
    }
    
    public boolean isOnGround() {
        return this.onGround;
    }
    
    public boolean isLastGround() {
        return this.lastGround;
    }
    
    public int getAutoClickerCVerbose() {
        return this.autoClickerCVerbose;
    }
    
    public int getClientSens() {
        return this.clientSens;
    }
    
    public int getAimYBuffer() {
        return this.aimYBuffer;
    }
    
    public int getAutoClickerGTicks() {
        return this.autoClickerGTicks;
    }
    
    public int getTimerBVerbose() {
        return this.timerBVerbose;
    }
    
    public int getStableMotionACount() {
        return this.stableMotionACount;
    }
    
    public int getMotionASamePrediction() {
        return this.motionASamePrediction;
    }
    
    public int getStableMotionADecrease() {
        return this.stableMotionADecrease;
    }
    
    public int getTableMotionACount() {
        return this.tableMotionACount;
    }
    
    public int getSoulSandTicks() {
        return this.soulSandTicks;
    }
    
    public int getAutoblockALastTick() {
        return this.autoblockALastTick;
    }
    
    public int getTick() {
        return this.tick;
    }
    
    public int getMotionBVerbose() {
        return this.motionBVerbose;
    }
    
    public int getFlightEVerbose() {
        return this.flightEVerbose;
    }
    
    public int getFlightETicks() {
        return this.flightETicks;
    }
    
    public int getAimAssistCVerbose() {
        return this.aimAssistCVerbose;
    }
    
    public int getAutoClickerFCps() {
        return this.autoClickerFCps;
    }
    
    public int getAutoClickerFTicks() {
        return this.autoClickerFTicks;
    }
    
    public int getCps() {
        return this.cps;
    }
    
    public int getCpsTicks() {
        return this.cpsTicks;
    }
    
    public int getAutoClickerDVerbose() {
        return this.autoClickerDVerbose;
    }
    
    public int getAutoClickerDTicks() {
        return this.autoClickerDTicks;
    }
    
    public int getFlightDVerbose() {
        return this.flightDVerbose;
    }
    
    public int getFlightCHoverVerbose() {
        return this.flightCHoverVerbose;
    }
    
    public int getFlightCAccelerationVerbose() {
        return this.flightCAccelerationVerbose;
    }
    
    public int getFlightCAir() {
        return this.flightCAir;
    }
    
    public int getMotionAVerbose() {
        return this.motionAVerbose;
    }
    
    public int getDiscordBotVL() {
        return this.discordBotVL;
    }
    
    public int getTotalBlocksCheck() {
        return this.totalBlocksCheck;
    }
    
    public int getBadPacketsBStable() {
        return this.badPacketsBStable;
    }
    
    public int getBadPacketsBVerbose1() {
        return this.badPacketsBVerbose1;
    }
    
    public int getSnowTicks() {
        return this.snowTicks;
    }
    
    public int getSpeedBVerbose() {
        return this.speedBVerbose;
    }
    
    public int getSpeedAVerbose() {
        return this.speedAVerbose;
    }
    
    public int getBoatTicks() {
        return this.boatTicks;
    }
    
    public int getFlightBVerbose() {
        return this.flightBVerbose;
    }
    
    public int getClimbableTicks() {
        return this.climbableTicks;
    }
    
    public int getFlightADistance() {
        return this.flightADistance;
    }
    
    public int getFlightAVerbose() {
        return this.flightAVerbose;
    }
    
    public int getFuckingCPS() {
        return this.fuckingCPS;
    }
    
    public int getTotalGaySlots() {
        return this.totalGaySlots;
    }
    
    public int getLastGayAssSlot() {
        return this.lastGayAssSlot;
    }
    
    public int getAimAssistFVerbose() {
        return this.aimAssistFVerbose;
    }
    
    public int getNoSlowAVerbose() {
        return this.noSlowAVerbose;
    }
    
    public int getVioSplit() {
        return this.vioSplit;
    }
    
    public int getPacketsSkipped() {
        return this.packetsSkipped;
    }
    
    public int getFullTickSkipped() {
        return this.fullTickSkipped;
    }
    
    public int getAimAssistHCounter() {
        return this.aimAssistHCounter;
    }
    
    public int getAimAssistHVerbose1Last() {
        return this.aimAssistHVerbose1Last;
    }
    
    public int getAimAssistHPitchSame() {
        return this.aimAssistHPitchSame;
    }
    
    public int getAimAssistHVerbose1() {
        return this.aimAssistHVerbose1;
    }
    
    public int getLastAimWExpect() {
        return this.lastAimWExpect;
    }
    
    public int getAimWVerbose2() {
        return this.aimWVerbose2;
    }
    
    public int getLastAimWVerbose() {
        return this.lastAimWVerbose;
    }
    
    public int getAimWVerbose() {
        return this.aimWVerbose;
    }
    
    public int getLastAimWLen() {
        return this.lastAimWLen;
    }
    
    public int getKys52() {
        return this.kys52;
    }
    
    public int getAimAssistBCount() {
        return this.aimAssistBCount;
    }
    
    public int getKys() {
        return this.kys;
    }
    
    public int getKillauraA2Ticks2() {
        return this.killauraA2Ticks2;
    }
    
    public int getKillauraA2Ticks1() {
        return this.killauraA2Ticks1;
    }
    
    public int getKillauraKCount() {
        return this.killauraKCount;
    }
    
    public int getAimTVerbose() {
        return this.aimTVerbose;
    }
    
    public int getTotalticks() {
        return this.totalticks;
    }
    
    public int getVoidTicks() {
        return this.voidTicks;
    }
    
    public int getFlyingTicks() {
        return this.flyingTicks;
    }
    
    public int getBlockcancelTicks() {
        return this.blockcancelTicks;
    }
    
    public int getConstantEntityTicks() {
        return this.constantEntityTicks;
    }
    
    public int getAimS1Verbose() {
        return this.aimS1Verbose;
    }
    
    public int getAimOPartTick() {
        return this.aimOPartTick;
    }
    
    public int getAimOVerboseNigger() {
        return this.aimOVerboseNigger;
    }
    
    public int getAimOYawStable() {
        return this.aimOYawStable;
    }
    
    public int getRespawnTicks() {
        return this.respawnTicks;
    }
    
    public int getBadPacketsKVerbose() {
        return this.badPacketsKVerbose;
    }
    
    public int getBadPacketsJVerbose() {
        return this.badPacketsJVerbose;
    }
    
    public int getLastBadPacketsEBlockDigTick() {
        return this.lastBadPacketsEBlockDigTick;
    }
    
    public int getBadPacketsEVerbose() {
        return this.badPacketsEVerbose;
    }
    
    public int getLastLastBadPacketsEBP() {
        return this.lastLastBadPacketsEBP;
    }
    
    public int getLastBadPacketsEBlockPlaceTick() {
        return this.lastBadPacketsEBlockPlaceTick;
    }
    
    public int getLastLastBadPacketsEBD() {
        return this.lastLastBadPacketsEBD;
    }
    
    public int getOldTicks() {
        return this.oldTicks;
    }
    
    public int getKBTicks() {
        return this.KBTicks;
    }
    
    public int getYVelocityTicks() {
        return this.yVelocityTicks;
    }
    
    public int getHVelocityTicks() {
        return this.hVelocityTicks;
    }
    
    public int getJoinTicks() {
        return this.joinTicks;
    }
    
    public int getTeleportTicks() {
        return this.teleportTicks;
    }
    
    public int getDeadticks() {
        return this.Deadticks;
    }
    
    public int getWaterTicks() {
        return this.waterTicks;
    }
    
    public int getAimNOptifineTicks() {
        return this.aimNOptifineTicks;
    }
    
    public int getAimMVerboseFuckYou() {
        return this.aimMVerboseFuckYou;
    }
    
    public int getSlimeReset2() {
        return this.slimeReset2;
    }
    
    public int getSlimeReset() {
        return this.slimeReset;
    }
    
    public int getSlimeTicks() {
        return this.slimeTicks;
    }
    
    public int getPredictionDVerbose() {
        return this.predictionDVerbose;
    }
    
    public int getAimCVerbose() {
        return this.aimCVerbose;
    }
    
    public int getGravityVerbose() {
        return this.gravityVerbose;
    }
    
    public int getJumpPotionTicks() {
        return this.jumpPotionTicks;
    }
    
    public int getAimQAttacks() {
        return this.aimQAttacks;
    }
    
    public int getAimQVerbose() {
        return this.aimQVerbose;
    }
    
    public int getAimPCounter() {
        return this.aimPCounter;
    }
    
    public int getAimPVerbose() {
        return this.aimPVerbose;
    }
    
    public int getAttacks() {
        return this.attacks;
    }
    
    public int getAimAssistAVerbose2() {
        return this.aimAssistAVerbose2;
    }
    
    public int getAimAssistAVerbose() {
        return this.aimAssistAVerbose;
    }
    
    public int getAimOPredictionTicks() {
        return this.aimOPredictionTicks;
    }
    
    public int getKillauraNTicks1() {
        return this.killauraNTicks1;
    }
    
    public int getKillauraNTicks2() {
        return this.killauraNTicks2;
    }
    
    public int getAimMVerbose() {
        return this.aimMVerbose;
    }
    
    public int getAimLVerbose() {
        return this.aimLVerbose;
    }
    
    public int getBadPacketsDPlaceAmount() {
        return this.badPacketsDPlaceAmount;
    }
    
    public int getViolationSplit() {
        return this.violationSplit;
    }
    
    public int getPistionTicks() {
        return this.pistionTicks;
    }
    
    public int getRailTicks() {
        return this.railTicks;
    }
    
    public int getBadPacketsCVerbose() {
        return this.badPacketsCVerbose;
    }
    
    public int getBadPacketsOptifineTicks() {
        return this.badPacketsOptifineTicks;
    }
    
    public int getAimKVerbose() {
        return this.aimKVerbose;
    }
    
    public int getJumpBoostTicks() {
        return this.jumpBoostTicks;
    }
    
    public int getAimJStable() {
        return this.aimJStable;
    }
    
    public int getAimJVerbose() {
        return this.aimJVerbose;
    }
    
    public int getAimJZeroHits() {
        return this.aimJZeroHits;
    }
    
    public int getAimJGoodHits() {
        return this.aimJGoodHits;
    }
    
    public int getAimJBadHits() {
        return this.aimJBadHits;
    }
    
    public int getAimIVerbose() {
        return this.aimIVerbose;
    }
    
    public int getAimHVerbose() {
        return this.aimHVerbose;
    }
    
    public int getAimHOptifineTicks() {
        return this.aimHOptifineTicks;
    }
    
    public int getNextToWallTicks() {
        return this.nextToWallTicks;
    }
    
    public int getCancelTicks() {
        return this.cancelTicks;
    }
    
    public int getTickSendH4() {
        return this.tickSendH4;
    }
    
    public int getVelocityEVerbose() {
        return this.velocityEVerbose;
    }
    
    public int getVelocityDVerbose() {
        return this.velocityDVerbose;
    }
    
    public int getTickSendH3() {
        return this.tickSendH3;
    }
    
    public int getTickSendH2() {
        return this.tickSendH2;
    }
    
    public int getVelocityCVerbose() {
        return this.velocityCVerbose;
    }
    
    public int getTickSendH() {
        return this.tickSendH;
    }
    
    public int getVelocityBVerbose() {
        return this.velocityBVerbose;
    }
    
    public int getVelocityVerbose() {
        return this.velocityVerbose;
    }
    
    public int getTickSend() {
        return this.tickSend;
    }
    
    public int getTimerAVerbose() {
        return this.timerAVerbose;
    }
    
    public int getTimerCVerbose() {
        return this.timerCVerbose;
    }
    
    public int getBadPacksBVerbose() {
        return this.badPacksBVerbose;
    }
    
    public int getBadPacketsAVerbose() {
        return this.badPacketsAVerbose;
    }
    
    public int getSwings() {
        return this.swings;
    }
    
    public int getAimEVerbose() {
        return this.aimEVerbose;
    }
    
    public int getAimDVerbose() {
        return this.aimDVerbose;
    }
    
    public int getFenceTicks() {
        return this.fenceTicks;
    }
    
    public int getWallTicks() {
        return this.wallTicks;
    }
    
    public int getWebTicks() {
        return this.webTicks;
    }
    
    public int getGroundTicksMixed() {
        return this.groundTicksMixed;
    }
    
    public int getAirTicksMixed() {
        return this.airTicksMixed;
    }
    
    public int getSpeedPotionTicks() {
        return this.speedPotionTicks;
    }
    
    public int getSpeedDVerbose() {
        return this.speedDVerbose;
    }
    
    public int getClimableTicks() {
        return this.climableTicks;
    }
    
    public int getReachAVerbose() {
        return this.reachAVerbose;
    }
    
    public int getAimAOptifineVerbose() {
        return this.aimAOptifineVerbose;
    }
    
    public int getAimAVerbose() {
        return this.aimAVerbose;
    }
    
    public int getViolation() {
        return this.violation;
    }
    
    public int getGroundTicks() {
        return this.groundTicks;
    }
    
    public int getAirTicks() {
        return this.airTicks;
    }
    
    public int getPing() {
        return this.ping;
    }
    
    public int getStairTicks() {
        return this.stairTicks;
    }
    
    public int getSlabTicks() {
        return this.slabTicks;
    }
    
    public int getBlockAboveTicks() {
        return this.blockAboveTicks;
    }
    
    public int getLiquidTicks() {
        return this.liquidTicks;
    }
    
    public int getIceTicks() {
        return this.iceTicks;
    }
    
    public int getNearIceTicks() {
        return this.nearIceTicks;
    }
    
    public int getHalfBlockTicks() {
        return this.halfBlockTicks;
    }
    
    public int getMountedTicks() {
        return this.mountedTicks;
    }
    
    public long getLastMotionASpeedPotion() {
        return this.lastMotionASpeedPotion;
    }
    
    public long getLastRespawn() {
        return this.lastRespawn;
    }
    
    public long getLastBowDamage() {
        return this.lastBowDamage;
    }
    
    public long getLastExplode() {
        return this.lastExplode;
    }
    
    public long getLastSlime() {
        return this.lastSlime;
    }
    
    public long getLastTimerAFlying() {
        return this.lastTimerAFlying;
    }
    
    public long getAutoClickerEFlying() {
        return this.autoClickerEFlying;
    }
    
    public long getAutoClickerESwing() {
        return this.autoClickerESwing;
    }
    
    public long getAutoToolFlying() {
        return this.autoToolFlying;
    }
    
    public long getAutoToolHeld() {
        return this.autoToolHeld;
    }
    
    public long getLastBlockJump() {
        return this.lastBlockJump;
    }
    
    public long getLastniggerarm() {
        return this.lastniggerarm;
    }
    
    public long getBadPacketsDDown() {
        return this.badPacketsDDown;
    }
    
    public long getLastPhaseAFlag() {
        return this.lastPhaseAFlag;
    }
    
    public long getLastJumpPadUpdate() {
        return this.lastJumpPadUpdate;
    }
    
    public long getLastJumpPadSet() {
        return this.lastJumpPadSet;
    }
    
    public long getLastAimAssistF() {
        return this.lastAimAssistF;
    }
    
    public long getLastSkippedTicksLag() {
        return this.lastSkippedTicksLag;
    }
    
    public long getLastSkippedFlying() {
        return this.lastSkippedFlying;
    }
    
    public long getLastAimAssistHGCDYaw() {
        return this.lastAimAssistHGCDYaw;
    }
    
    public long getLastAimAssistHLook() {
        return this.lastAimAssistHLook;
    }
    
    public long getLastAimAssistHPostion() {
        return this.lastAimAssistHPostion;
    }
    
    public long getLastTransShit() {
        return this.lastTransShit;
    }
    
    public long getLastPlace() {
        return this.lastPlace;
    }
    
    public long getLastAimAssistGCD() {
        return this.lastAimAssistGCD;
    }
    
    public long getLastKillauraHBad() {
        return this.lastKillauraHBad;
    }
    
    public long getLastKillauraEGPitch() {
        return this.lastKillauraEGPitch;
    }
    
    public long getLastAimOSetTime2() {
        return this.lastAimOSetTime2;
    }
    
    public long getLastAimOSetTime1() {
        return this.lastAimOSetTime1;
    }
    
    public long getLastBadPacketsKKeepAlive() {
        return this.lastBadPacketsKKeepAlive;
    }
    
    public long getLastBadPacketsKTransaction() {
        return this.lastBadPacketsKTransaction;
    }
    
    public long getLastBadPacketsKeepAlive() {
        return this.lastBadPacketsKeepAlive;
    }
    
    public long getLastBadPacketsITransaction() {
        return this.lastBadPacketsITransaction;
    }
    
    public long getLastBadPacketsFBlockPlace() {
        return this.lastBadPacketsFBlockPlace;
    }
    
    public long getLastBadPacketsFBlockDig() {
        return this.lastBadPacketsFBlockDig;
    }
    
    public long getLastBadPacketsEBlockDig() {
        return this.lastBadPacketsEBlockDig;
    }
    
    public long getGetLastSlimeBounced() {
        return this.getLastSlimeBounced;
    }
    
    public long getLastCancelPlace() {
        return this.lastCancelPlace;
    }
    
    public long getLastAimPPos() {
        return this.lastAimPPos;
    }
    
    public long getLastAimPSet() {
        return this.lastAimPSet;
    }
    
    public long getLastAttacksSet() {
        return this.lastAttacksSet;
    }
    
    public long getLastAimAssistUp() {
        return this.lastAimAssistUp;
    }
    
    public long getLastAimAssistDip() {
        return this.lastAimAssistDip;
    }
    
    public long getLastYawGCDAimAssistA() {
        return this.lastYawGCDAimAssistA;
    }
    
    public long getLastAimOYawChange() {
        return this.lastAimOYawChange;
    }
    
    public long getLastAimOPitchChange() {
        return this.lastAimOPitchChange;
    }
    
    public long getLastAimOMove() {
        return this.lastAimOMove;
    }
    
    public long getLastBadPacketDDig() {
        return this.lastBadPacketDDig;
    }
    
    public long getLastBadPacketsDBlockPlace() {
        return this.lastBadPacketsDBlockPlace;
    }
    
    public long getLastBadPacketsCFlag() {
        return this.lastBadPacketsCFlag;
    }
    
    public long getLastBadPacketPredict() {
        return this.lastBadPacketPredict;
    }
    
    public long getLastBadPacketsBPos() {
        return this.lastBadPacketsBPos;
    }
    
    public long getLastAimJNegro() {
        return this.lastAimJNegro;
    }
    
    public long getLastAimJSomeWhatAgOODDvALUE() {
        return this.lastAimJSomeWhatAgOODDvALUE;
    }
    
    public long getLastAimHPostion() {
        return this.lastAimHPostion;
    }
    
    public long getLastAttackByEntity() {
        return this.lastAttackByEntity;
    }
    
    public long getLastGayModeSwitch() {
        return this.lastGayModeSwitch;
    }
    
    public long getLastDamage() {
        return this.lastDamage;
    }
    
    public long getLastTime() {
        return this.lastTime;
    }
    
    public long getLastTime2() {
        return this.lastTime2;
    }
    
    public long getLastBadPacketsBBlockDig() {
        return this.lastBadPacketsBBlockDig;
    }
    
    public long getLastHasPos() {
        return this.lastHasPos;
    }
    
    public long getTimerALastTime() {
        return this.timerALastTime;
    }
    
    public long getLastUnknownTeleport() {
        return this.lastUnknownTeleport;
    }
    
    public long getLastBadPacketsAFlying() {
        return this.lastBadPacketsAFlying;
    }
    
    public long getLastFullBlockMove() {
        return this.lastFullBlockMove;
    }
    
    public long getLastAimEPositionLook() {
        return this.lastAimEPositionLook;
    }
    
    public long getLastAimEPosition() {
        return this.lastAimEPosition;
    }
    
    public long getLastBlockBreakCancel() {
        return this.lastBlockBreakCancel;
    }
    
    public long getLastAttackedByPlayer() {
        return this.lastAttackedByPlayer;
    }
    
    public long getLastAlertRec() {
        return this.lastAlertRec;
    }
    
    public long getLastLag2() {
        return this.lastLag2;
    }
    
    public long getLastTransaction2() {
        return this.lastTransaction2;
    }
    
    public long getLastServerTransaction2() {
        return this.lastServerTransaction2;
    }
    
    public long getLastFlightToggle() {
        return this.lastFlightToggle;
    }
    
    public long getLastGamemodeSwitch() {
        return this.lastGamemodeSwitch;
    }
    
    public long getLastJoin() {
        return this.lastJoin;
    }
    
    public long getLastLag() {
        return this.lastLag;
    }
    
    public long getTransactionPing() {
        return this.transactionPing;
    }
    
    public long getCalculatedPing() {
        return this.calculatedPing;
    }
    
    public long getLastCalulated() {
        return this.lastCalulated;
    }
    
    public long getLastTransUpdate() {
        return this.lastTransUpdate;
    }
    
    public long getLastTransactionReciv() {
        return this.lastTransactionReciv;
    }
    
    public long getCalculatedRealPing() {
        return this.calculatedRealPing;
    }
    
    public long getLastLastFucker() {
        return this.lastLastFucker;
    }
    
    public long getTransactionLast() {
        return this.transactionLast;
    }
    
    public long getLastClientTransaction() {
        return this.lastClientTransaction;
    }
    
    public long getLastServerTransaction() {
        return this.lastServerTransaction;
    }
    
    public long getLastTransactionPing() {
        return this.lastTransactionPing;
    }
    
    public long getLastTransDiff() {
        return this.lastTransDiff;
    }
    
    public long getLastAttackPacket() {
        return this.lastAttackPacket;
    }
    
    public long getLastIce() {
        return this.lastIce;
    }
    
    public long getLastEntityVelocity() {
        return this.lastEntityVelocity;
    }
    
    public long getLastTeleport() {
        return this.lastTeleport;
    }
    
    public long getLastBukkitMovement() {
        return this.lastBukkitMovement;
    }
    
    public long getLastServerKeepAlive() {
        return this.lastServerKeepAlive;
    }
    
    public double getVelocityLength() {
        return this.velocityLength;
    }
    
    public double getAimLThreshold() {
        return this.aimLThreshold;
    }
    
    public double getAutoClickerGCps() {
        return this.autoClickerGCps;
    }
    
    public double getLastMotionAPrediction() {
        return this.lastMotionAPrediction;
    }
    
    public double getMotionAThreshold() {
        return this.motionAThreshold;
    }
    
    public double getLastMotionAPredictionDiff() {
        return this.lastMotionAPredictionDiff;
    }
    
    public double getLastPredictedDiff() {
        return this.lastPredictedDiff;
    }
    
    public double getAutoClickerEVerbose() {
        return this.autoClickerEVerbose;
    }
    
    public double getLastAutoClickerDSkewness() {
        return this.lastAutoClickerDSkewness;
    }
    
    public double getLastAutoClickerDKurtosis() {
        return this.lastAutoClickerDKurtosis;
    }
    
    public double getLastAutoClickerDAverage() {
        return this.lastAutoClickerDAverage;
    }
    
    public double getLastFlightDGround() {
        return this.lastFlightDGround;
    }
    
    public double getLastFlightCAcceleration() {
        return this.lastFlightCAcceleration;
    }
    
    public double getLastFlightCDelta() {
        return this.lastFlightCDelta;
    }
    
    public double getLastMotionAHorizontal() {
        return this.lastMotionAHorizontal;
    }
    
    public double getLastBadPacketsBY() {
        return this.lastBadPacketsBY;
    }
    
    public double getBadPacketsBDiff() {
        return this.badPacketsBDiff;
    }
    
    public double getLastSpeedFSpeed() {
        return this.lastSpeedFSpeed;
    }
    
    public double getLastSpeedBDistance() {
        return this.lastSpeedBDistance;
    }
    
    public double getLastFlighBDiff() {
        return this.lastFlighBDiff;
    }
    
    public double getLastAimDPitch() {
        return this.lastAimDPitch;
    }
    
    public double getLastAimAssistHVal1() {
        return this.lastAimAssistHVal1;
    }
    
    public double getLastAimAssistHGCDPitch() {
        return this.lastAimAssistHGCDPitch;
    }
    
    public double getLastAimWPitch() {
        return this.lastAimWPitch;
    }
    
    public double getLastAimWShort() {
        return this.lastAimWShort;
    }
    
    public double getLastAimWTrim() {
        return this.lastAimWTrim;
    }
    
    public double getLastFixerIDK() {
        return this.lastFixerIDK;
    }
    
    public double getKillauraA2LastValue() {
        return this.killauraA2LastValue;
    }
    
    public double getAimSLastYaw() {
        return this.aimSLastYaw;
    }
    
    public double getAimOLastValue() {
        return this.aimOLastValue;
    }
    
    public double getLastAimNValue() {
        return this.lastAimNValue;
    }
    
    public double getAvgPitchSpeed() {
        return this.avgPitchSpeed;
    }
    
    public double getAvgPitchDevation() {
        return this.avgPitchDevation;
    }
    
    public double getLastYDiff() {
        return this.lastYDiff;
    }
    
    public double getLastY() {
        return this.lastY;
    }
    
    public double getLastAimQPrediction() {
        return this.lastAimQPrediction;
    }
    
    public double getLastAimQPitch() {
        return this.lastAimQPitch;
    }
    
    public double getLastAimQYaw() {
        return this.lastAimQYaw;
    }
    
    public double getLastAimQTurn() {
        return this.lastAimQTurn;
    }
    
    public double getLastAimQGCD() {
        return this.lastAimQGCD;
    }
    
    public double getAimQLastFixed() {
        return this.aimQLastFixed;
    }
    
    public double getAimQPrediction() {
        return this.aimQPrediction;
    }
    
    public double getLastAimPYawDiff() {
        return this.lastAimPYawDiff;
    }
    
    public double getLastAimAssistAPitchDiff() {
        return this.lastAimAssistAPitchDiff;
    }
    
    public double getLastAimAssitAYawDiff() {
        return this.lastAimAssitAYawDiff;
    }
    
    public double getKillauraNLast() {
        return this.killauraNLast;
    }
    
    public double getLastAimMPitch() {
        return this.lastAimMPitch;
    }
    
    public double getLastAimKDiff() {
        return this.lastAimKDiff;
    }
    
    public double getLastAimKMoveSpeed() {
        return this.lastAimKMoveSpeed;
    }
    
    public double getLastAimIPitchDiff() {
        return this.lastAimIPitchDiff;
    }
    
    public double getLastAimIYawDiff() {
        return this.lastAimIYawDiff;
    }
    
    public double getLastAimHYawDiff() {
        return this.lastAimHYawDiff;
    }
    
    public double getBalance() {
        return this.balance;
    }
    
    public double getTimerABal() {
        return this.timerABal;
    }
    
    public double getAimassistBVerbose() {
        return this.aimassistBVerbose;
    }
    
    public double getLastAimAssistBVal() {
        return this.lastAimAssistBVal;
    }
    
    public double getLastAimEDiff() {
        return this.lastAimEDiff;
    }
    
    public double getLastAimDValue() {
        return this.lastAimDValue;
    }
    
    public double getPredictionDLastY() {
        return this.predictionDLastY;
    }
    
    public double getLastPredictedSpeed() {
        return this.lastPredictedSpeed;
    }
    
    public double getLastongroundspeed() {
        return this.lastongroundspeed;
    }
    
    public double getLastSpeedPotionMP() {
        return this.lastSpeedPotionMP;
    }
    
    public double getLastAimCPitchDiff() {
        return this.lastAimCPitchDiff;
    }
    
    public double getLastAimAPitch() {
        return this.lastAimAPitch;
    }
    
    public double getMovementSpeed() {
        return this.movementSpeed;
    }
    
    public double getDeltaXZ() {
        return this.deltaXZ;
    }
    
    public double getDeltaY() {
        return this.deltaY;
    }
    
    public double getDeltaX() {
        return this.deltaX;
    }
    
    public double getDeltaZ() {
        return this.deltaZ;
    }
    
    public double getLastDeltaXZ() {
        return this.lastDeltaXZ;
    }
    
    public double getLastDeltaY() {
        return this.lastDeltaY;
    }
    
    public float getLastAimAssistFPrevPitch() {
        return this.lastAimAssistFPrevPitch;
    }
    
    public float getLastAimAssistFPrevYaw() {
        return this.lastAimAssistFPrevYaw;
    }
    
    public float getAimAssistsFPitch() {
        return this.aimAssistsFPitch;
    }
    
    public float getAimAssistsFYaw() {
        return this.aimAssistsFYaw;
    }
    
    public float getLastAimAssistHYaw() {
        return this.lastAimAssistHYaw;
    }
    
    public float getLastAimAssistHPitch() {
        return this.lastAimAssistHPitch;
    }
    
    public float getLastAimAssistAPitch() {
        return this.lastAimAssistAPitch;
    }
    
    public float getLastAimAssistAYaw() {
        return this.lastAimAssistAYaw;
    }
    
    public Location getFlightGGroundLocation() {
        return this.flightGGroundLocation;
    }
    
    public MovingStats getMovingStats() {
        return this.movingStats;
    }
    
    public Location getTpLocation() {
        return this.tpLocation;
    }
    
    public Location getCancelLocation() {
        return this.CancelLocation;
    }
    
    public long getLastServerVelocity() {
        return this.lastServerVelocity;
    }
    
    public long getLastTypeCSwing() {
        return this.lastTypeCSwing;
    }
    
    public long getLastDoubleClick() {
        return this.lastDoubleClick;
    }
    
    public long getLastFlag() {
        return this.LastFlag;
    }
    
    public Timer getCpsTimer() {
        return this.cpsTimer;
    }
    
    public String getClientBrand() {
        return this.clientBrand;
    }
    
    public float getLastYawChangeA() {
        return this.lastYawChangeA;
    }
    
    public float getLastPitchChangeA() {
        return this.lastPitchChangeA;
    }
    
    public Verbose getNoFallAVerbose() {
        return this.noFallAVerbose;
    }
    
    public Verbose getSpeedFVerbose() {
        return this.speedFVerbose;
    }
    
    public Verbose getSpeedCVerbose() {
        return this.speedCVerbose;
    }
    
    public Verbose getAimD111Verbose() {
        return this.aimD111Verbose;
    }
    
    public Verbose getBadPacketsJVerbose1() {
        return this.badPacketsJVerbose1;
    }
    
    public Verbose getAimAssistHVerbose2() {
        return this.aimAssistHVerbose2;
    }
    
    public Verbose getAimWVerbose1() {
        return this.aimWVerbose1;
    }
    
    public Verbose getAimAssistVerbose() {
        return this.aimAssistVerbose;
    }
    
    public Verbose getKillauraA2Verbose() {
        return this.killauraA2Verbose;
    }
    
    public Verbose getKillauraKVerboseFix() {
        return this.killauraKVerboseFix;
    }
    
    public Verbose getAimSVerbose() {
        return this.aimSVerbose;
    }
    
    public Verbose getBadPacketsFVerbose() {
        return this.badPacketsFVerbose;
    }
    
    public Verbose getAimOVerbose() {
        return this.aimOVerbose;
    }
    
    public Verbose getAimNVerbose() {
        return this.aimNVerbose;
    }
    
    public Verbose getPredictionEVerbose() {
        return this.predictionEVerbose;
    }
    
    public Verbose getKillauraLVerbose() {
        return this.killauraLVerbose;
    }
    
    public Verbose getBadPacketsBVerbose() {
        return this.badPacketsBVerbose;
    }
    
    public Verbose getKillauraNVerbose() {
        return this.killauraNVerbose;
    }
    
    public Verbose getHitBoxAVerbose() {
        return this.hitBoxAVerbose;
    }
    
    public Verbose getBadPacketsDVerbose2() {
        return this.badPacketsDVerbose2;
    }
    
    public Verbose getBadPacketsDVerbose() {
        return this.badPacketsDVerbose;
    }
    
    public Verbose getAimassistBVerbose2() {
        return this.aimassistBVerbose2;
    }
    
    public Verbose getAimIVerbose2() {
        return this.aimIVerbose2;
    }
    
    public Verbose getCriticalsAVerbose() {
        return this.criticalsAVerbose;
    }
    
    public Verbose getAimDVerbose2() {
        return this.aimDVerbose2;
    }
    
    public Verbose getAimBVerbose() {
        return this.aimBVerbose;
    }
    
    public Entity getLastEntityAttacked() {
        return this.lastEntityAttacked;
    }
    
    public PastLocation getReachAPastLocations() {
        return this.reachAPastLocations;
    }
    
    public PastLocation getReachBPastLocations() {
        return this.reachBPastLocations;
    }
    
    public PastLocation getHitBoxPastLocations() {
        return this.hitBoxPastLocations;
    }
    
    public CustomLocation getLastFlightDLocation() {
        return this.lastFlightDLocation;
    }
    
    public CustomLocation getNewTo() {
        return this.newTo;
    }
    
    public CustomLocation getNewFrom() {
        return this.newFrom;
    }
    
    public CustomLocation getLastFlightDGroundLocation() {
        return this.lastFlightDGroundLocation;
    }
    
    public CustomLocation getLastOnGroundLocation() {
        return this.lastOnGroundLocation;
    }
    
    public ProbabilitySystem getProbabilitySystem() {
        return this.probabilitySystem;
    }
    
    public List<Double> getAimAssistsAPatterns() {
        return this.aimAssistsAPatterns;
    }
    
    public List<Double> getAimQPatterns() {
        return this.aimQPatterns;
    }
    
    public List<Double> getAimSSamples() {
        return this.aimSSamples;
    }
    
    public List<Double> getAimWSamples() {
        return this.aimWSamples;
    }
    
    public long getLastTimerBFlying() {
        return this.lastTimerBFlying;
    }
    
    public long getLastOptifine() {
        return this.lastOptifine;
    }
    
    public long getLastOptifineREE() {
        return this.lastOptifineREE;
    }
    
    public long getLastRetardOpitfineSpam() {
        return this.lastRetardOpitfineSpam;
    }
    
    public long getLastAimAssistACE() {
        return this.lastAimAssistACE;
    }
    
    public int getOptifineSmoothing2() {
        return this.optifineSmoothing2;
    }
    
    public int getLastClientSmoothingValue() {
        return this.lastClientSmoothingValue;
    }
    
    public int getOptifineSmoothing() {
        return this.optifineSmoothing;
    }
    
    public int getLastSmoothingCounter() {
        return this.LastSmoothingCounter;
    }
    
    public int getSmoothingCounter() {
        return this.smoothingCounter;
    }
    
    public int getOptifineSmoothSens() {
        return this.optifineSmoothSens;
    }
    
    public int getOptifinePitchSmooth() {
        return this.optifinePitchSmooth;
    }
    
    public int getOptifineSameCount() {
        return this.optifineSameCount;
    }
    
    public int getOptifineConstantVL2() {
        return this.optifineConstantVL2;
    }
    
    public int getOptifineConstantVL() {
        return this.optifineConstantVL;
    }
    
    public int getOptifineSmoothingFix() {
        return this.optifineSmoothingFix;
    }
    
    public int getKillauraAYawReset() {
        return this.killauraAYawReset;
    }
    
    public int getKillauraAPitchReset() {
        return this.killauraAPitchReset;
    }
    
    public int getAimAssistsACount() {
        return this.aimAssistsACount;
    }
    
    public int getOptifineSmoothingTicks() {
        return this.optifineSmoothingTicks;
    }
    
    public MCSmoothing getAimWSmooth() {
        return this.aimWSmooth;
    }
    
    public MCSmoothing getNewPitchSmoothing() {
        return this.newPitchSmoothing;
    }
    
    public MCSmoothing getNewYawSmoothing() {
        return this.newYawSmoothing;
    }
    
    public MCSmoothing getYaw() {
        return this.yaw;
    }
    
    public MCSmoothing getPitch() {
        return this.pitch;
    }
    
    public MCSmoothing getSmooth() {
        return this.smooth;
    }
    
    public double getLastSmoothingRot2() {
        return this.lastSmoothingRot2;
    }
    
    public double getLastSmoothingRot() {
        return this.lastSmoothingRot;
    }
    
    public double getLastPitchDelta() {
        return this.lastPitchDelta;
    }
    
    public double getLastSmoothPitch1() {
        return this.lastSmoothPitch1;
    }
    
    public double getLastOptifinePitchSmoothidfklol() {
        return this.lastOptifinePitchSmoothidfklol;
    }
    
    public float getLastYawDelta() {
        return this.lastYawDelta;
    }
    
    public float getLastSmoothYaw() {
        return this.lastSmoothYaw;
    }
    
    public boolean isCineCamera() {
        return this.cineCamera;
    }
    
    public CheckType getLastGUICheckType() {
        return this.lastGUICheckType;
    }
    
    public int getTicksB() {
        return this.ticksB;
    }
    
    public int getAutoClickerBVerbose() {
        return this.autoClickerBVerbose;
    }
    
    public int getTicksC() {
        return this.ticksC;
    }
    
    public int getAutoClickerAVerbose() {
        return this.autoClickerAVerbose;
    }
    
    public int getTicksA() {
        return this.ticksA;
    }
    
    public int getTicksD() {
        return this.ticksD;
    }
    
    public double getAvgSpeedB() {
        return this.avgSpeedB;
    }
    
    public double getAvgDevationB() {
        return this.avgDevationB;
    }
    
    public double getAvgSpeedC() {
        return this.avgSpeedC;
    }
    
    public double getAvgDevationC() {
        return this.avgDevationC;
    }
    
    public double getAvgSpeedA() {
        return this.avgSpeedA;
    }
    
    public double getAvgDevationA() {
        return this.avgDevationA;
    }
    
    public LinkedList<Integer> getRecentCountsC() {
        return this.recentCountsC;
    }
    
    public LinkedList<Integer> getRecentCountsD() {
        return this.recentCountsD;
    }
    
    public MovementProcessor getMovementProcessor() {
        return this.movementProcessor;
    }
    
    public LagProcessor getLagProcessor() {
        return this.lagProcessor;
    }
    
    public CombatProcessor getCombatProcessor() {
        return this.combatProcessor;
    }
    
    public PredictionProcessor getPredictionProcessor() {
        return this.predictionProcessor;
    }
    
    public GlobalObject getGlobalObject() {
        return this.globalObject;
    }
    
    public double getGenericAttributesSpeed() {
        return this.genericAttributesSpeed;
    }
    
    public CustomLocation getRetardedLocation() {
        return this.retardedLocation;
    }
    
    public int getAutoClickerBTicks() {
        return this.autoClickerBTicks;
    }
    
    public int getAutoClickerETicks() {
        return this.autoClickerETicks;
    }
    
    public int getAutoClickerHTicks() {
        return this.autoClickerHTicks;
    }
    
    public LinkedList<Integer> getAutoClickerBCounts() {
        return this.autoClickerBCounts;
    }
    
    public LinkedList<Integer> getAutoClickerECounts() {
        return this.autoClickerECounts;
    }
    
    public double getLastAutoClickerESTD() {
        return this.lastAutoClickerESTD;
    }
    
    public double getLastAutoClickerHRange() {
        return this.lastAutoClickerHRange;
    }
    
    public double getAvgAutoClickerH() {
        return this.avgAutoClickerH;
    }
    
    public List<Double> getAutoClickerHPattern() {
        return this.autoClickerHPattern;
    }
    
    public EvictingList<Integer> getAutoClickerDSamples() {
        return this.autoClickerDSamples;
    }
    
    public Deque<Double> getAutoClickerGSamples() {
        return this.autoClickerGSamples;
    }
    
    public Deque<Long> getTimerADeque() {
        return this.timerADeque;
    }
    
    public Deque<Integer> getAutoClickerFSamples() {
        return this.autoClickerFSamples;
    }
    
    public Deque<Float> getAimAssistCSamples() {
        return this.aimAssistCSamples;
    }
    
    public Deque<Float> getAimYPitchSamples() {
        return this.aimYPitchSamples;
    }
    
    public Deque<Float> getAimYYawSamples() {
        return this.aimYYawSamples;
    }
    
    public List<VelocitySnapshot> getVelocityLengthSamples() {
        return this.velocityLengthSamples;
    }
    
    public void setPlayer(final Player player) {
        this.player = player;
    }
    
    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }
    
    public void setTo(final Location to) {
        this.to = to;
    }
    
    public void setFrom(final Location from) {
        this.from = from;
    }
    
    public void setOldTo(final Location oldTo) {
        this.oldTo = oldTo;
    }
    
    public void setOldFrom(final Location oldFrom) {
        this.oldFrom = oldFrom;
    }
    
    public void setLastGroundLocation(final CustomLocation lastGroundLocation) {
        this.lastGroundLocation = lastGroundLocation;
    }
    
    public void setBoundingBox(final BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }
    
    public void setBlockAssesement(final BlockAssesement blockAssesement) {
        this.blockAssesement = blockAssesement;
    }
    
    public void setWasFlying(final boolean wasFlying) {
        this.wasFlying = wasFlying;
    }
    
    public void setSetClientSens(final boolean setClientSens) {
        this.setClientSens = setClientSens;
    }
    
    public void setInvalidMotionXZ(final boolean invalidMotionXZ) {
        this.invalidMotionXZ = invalidMotionXZ;
    }
    
    public void setSneaking(final boolean sneaking) {
        this.sneaking = sneaking;
    }
    
    public void setMotionXZReaady(final boolean motionXZReaady) {
        this.motionXZReaady = motionXZReaady;
    }
    
    public void setRespawn(final boolean respawn) {
        this.respawn = respawn;
    }
    
    public void setExplode(final boolean explode) {
        this.explode = explode;
    }
    
    public void setCollidesHorizontally(final boolean collidesHorizontally) {
        this.collidesHorizontally = collidesHorizontally;
    }
    
    public void setCollidesVertically(final boolean collidesVertically) {
        this.collidesVertically = collidesVertically;
    }
    
    public void setAutoClickerESent(final boolean autoClickerESent) {
        this.autoClickerESent = autoClickerESent;
    }
    
    public void setAutoToolSent(final boolean autoToolSent) {
        this.autoToolSent = autoToolSent;
    }
    
    public void setMovementChecksOK(final boolean movementChecksOK) {
        this.movementChecksOK = movementChecksOK;
    }
    
    public void setSpeedBLastGroundLocation(final boolean speedBLastGroundLocation) {
        this.speedBLastGroundLocation = speedBLastGroundLocation;
    }
    
    public void setSpeedBLastGround(final boolean speedBLastGround) {
        this.speedBLastGround = speedBLastGround;
    }
    
    public void setNearBoat(final boolean nearBoat) {
        this.nearBoat = nearBoat;
    }
    
    public void setBreakingAFUCKINGBLOCK(final boolean isBreakingAFUCKINGBLOCK) {
        this.isBreakingAFUCKINGBLOCK = isBreakingAFUCKINGBLOCK;
    }
    
    public void setBadPacketsDYInvalid(final boolean badPacketsDYInvalid) {
        this.badPacketsDYInvalid = badPacketsDYInvalid;
    }
    
    public void setJumpPad(final boolean jumpPad) {
        this.jumpPad = jumpPad;
    }
    
    public void setSetAimAssistF(final boolean setAimAssistF) {
        this.setAimAssistF = setAimAssistF;
    }
    
    public void setGAY(final boolean isGAY) {
        this.isGAY = isGAY;
    }
    
    public void setSprinting(final boolean sprinting) {
        this.sprinting = sprinting;
    }
    
    public void setAimAssistAWork(final boolean aimAssistAWork) {
        this.aimAssistAWork = aimAssistAWork;
    }
    
    public void setAimOSet(final boolean aimOSet) {
        this.aimOSet = aimOSet;
    }
    
    public void setHasSentPacketBadPacketsD(final boolean hasSentPacketBadPacketsD) {
        this.hasSentPacketBadPacketsD = hasSentPacketBadPacketsD;
    }
    
    public void setBadPacketsDSprinting(final boolean badPacketsDSprinting) {
        this.badPacketsDSprinting = badPacketsDSprinting;
    }
    
    public void setHasBouncedOnSlime(final boolean isHasBouncedOnSlime) {
        this.isHasBouncedOnSlime = isHasBouncedOnSlime;
    }
    
    public void setHasAlerts(final boolean hasAlerts) {
        this.hasAlerts = hasAlerts;
    }
    
    public void setWasOnGround(final boolean wasOnGround) {
        this.wasOnGround = wasOnGround;
    }
    
    public void setLastGroundGround(final boolean lastGroundGround) {
        this.lastGroundGround = lastGroundGround;
    }
    
    public void setLastGround2(final boolean lastGround2) {
        this.lastGround2 = lastGround2;
    }
    
    public void setAimPSet(final boolean aimPSet) {
        this.aimPSet = aimPSet;
    }
    
    public void setAttacksSet(final boolean attacksSet) {
        this.attacksSet = attacksSet;
    }
    
    public void setAimJExpectingNext(final boolean aimJExpectingNext) {
        this.aimJExpectingNext = aimJExpectingNext;
    }
    
    public void setNextToWall(final boolean nextToWall) {
        this.nextToWall = nextToWall;
    }
    
    public void setDidSwitchGamemode(final boolean didSwitchGamemode) {
        this.didSwitchGamemode = didSwitchGamemode;
    }
    
    public void setBanned(final boolean banned) {
        this.banned = banned;
    }
    
    public void setHasSentH4(final boolean hasSentH4) {
        this.hasSentH4 = hasSentH4;
    }
    
    public void setHasSentH3(final boolean hasSentH3) {
        this.hasSentH3 = hasSentH3;
    }
    
    public void setHasSentH2(final boolean hasSentH2) {
        this.hasSentH2 = hasSentH2;
    }
    
    public void setHasSentH(final boolean hasSentH) {
        this.hasSentH = hasSentH;
    }
    
    public void setHasSent(final boolean hasSent) {
        this.hasSent = hasSent;
    }
    
    public void setBreakingBlock(final boolean breakingBlock) {
        this.breakingBlock = breakingBlock;
    }
    
    public void setPacketClientGround(final boolean packetClientGround) {
        this.packetClientGround = packetClientGround;
    }
    
    public void setTeleportedNoMove(final boolean teleportedNoMove) {
        this.teleportedNoMove = teleportedNoMove;
    }
    
    public void setClientGround(final boolean clientGround) {
        this.clientGround = clientGround;
    }
    
    public void setLastClientGround(final boolean lastClientGround) {
        this.lastClientGround = lastClientGround;
    }
    
    public void setLastonground(final boolean lastonground) {
        this.lastonground = lastonground;
    }
    
    public void setOnGroundMixed(final boolean onGroundMixed) {
        this.onGroundMixed = onGroundMixed;
    }
    
    public void setWasOnGroundMixed(final boolean wasOnGroundMixed) {
        this.wasOnGroundMixed = wasOnGroundMixed;
    }
    
    public void setLaggin2(final boolean laggin2) {
        this.laggin2 = laggin2;
    }
    
    public void setOnSlime(final boolean isOnSlime) {
        this.isOnSlime = isOnSlime;
    }
    
    public void setSlime(final boolean slime) {
        this.slime = slime;
    }
    
    public void setLagging(final boolean lagging) {
        this.lagging = lagging;
    }
    
    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }
    
    public void setLastGround(final boolean lastGround) {
        this.lastGround = lastGround;
    }
    
    public void setAutoClickerCVerbose(final int autoClickerCVerbose) {
        this.autoClickerCVerbose = autoClickerCVerbose;
    }
    
    public void setClientSens(final int clientSens) {
        this.clientSens = clientSens;
    }
    
    public void setAimYBuffer(final int aimYBuffer) {
        this.aimYBuffer = aimYBuffer;
    }
    
    public void setAutoClickerGTicks(final int autoClickerGTicks) {
        this.autoClickerGTicks = autoClickerGTicks;
    }
    
    public void setTimerBVerbose(final int timerBVerbose) {
        this.timerBVerbose = timerBVerbose;
    }
    
    public void setStableMotionACount(final int stableMotionACount) {
        this.stableMotionACount = stableMotionACount;
    }
    
    public void setMotionASamePrediction(final int motionASamePrediction) {
        this.motionASamePrediction = motionASamePrediction;
    }
    
    public void setStableMotionADecrease(final int stableMotionADecrease) {
        this.stableMotionADecrease = stableMotionADecrease;
    }
    
    public void setTableMotionACount(final int tableMotionACount) {
        this.tableMotionACount = tableMotionACount;
    }
    
    public void setSoulSandTicks(final int soulSandTicks) {
        this.soulSandTicks = soulSandTicks;
    }
    
    public void setAutoblockALastTick(final int autoblockALastTick) {
        this.autoblockALastTick = autoblockALastTick;
    }
    
    public void setTick(final int tick) {
        this.tick = tick;
    }
    
    public void setMotionBVerbose(final int motionBVerbose) {
        this.motionBVerbose = motionBVerbose;
    }
    
    public void setFlightEVerbose(final int flightEVerbose) {
        this.flightEVerbose = flightEVerbose;
    }
    
    public void setFlightETicks(final int flightETicks) {
        this.flightETicks = flightETicks;
    }
    
    public void setAimAssistCVerbose(final int aimAssistCVerbose) {
        this.aimAssistCVerbose = aimAssistCVerbose;
    }
    
    public void setAutoClickerFCps(final int autoClickerFCps) {
        this.autoClickerFCps = autoClickerFCps;
    }
    
    public void setAutoClickerFTicks(final int autoClickerFTicks) {
        this.autoClickerFTicks = autoClickerFTicks;
    }
    
    public void setCps(final int cps) {
        this.cps = cps;
    }
    
    public void setCpsTicks(final int cpsTicks) {
        this.cpsTicks = cpsTicks;
    }
    
    public void setAutoClickerDVerbose(final int autoClickerDVerbose) {
        this.autoClickerDVerbose = autoClickerDVerbose;
    }
    
    public void setAutoClickerDTicks(final int autoClickerDTicks) {
        this.autoClickerDTicks = autoClickerDTicks;
    }
    
    public void setFlightDVerbose(final int flightDVerbose) {
        this.flightDVerbose = flightDVerbose;
    }
    
    public void setFlightCHoverVerbose(final int flightCHoverVerbose) {
        this.flightCHoverVerbose = flightCHoverVerbose;
    }
    
    public void setFlightCAccelerationVerbose(final int flightCAccelerationVerbose) {
        this.flightCAccelerationVerbose = flightCAccelerationVerbose;
    }
    
    public void setFlightCAir(final int flightCAir) {
        this.flightCAir = flightCAir;
    }
    
    public void setMotionAVerbose(final int motionAVerbose) {
        this.motionAVerbose = motionAVerbose;
    }
    
    public void setDiscordBotVL(final int discordBotVL) {
        this.discordBotVL = discordBotVL;
    }
    
    public void setTotalBlocksCheck(final int totalBlocksCheck) {
        this.totalBlocksCheck = totalBlocksCheck;
    }
    
    public void setBadPacketsBStable(final int badPacketsBStable) {
        this.badPacketsBStable = badPacketsBStable;
    }
    
    public void setBadPacketsBVerbose1(final int badPacketsBVerbose1) {
        this.badPacketsBVerbose1 = badPacketsBVerbose1;
    }
    
    public void setSnowTicks(final int snowTicks) {
        this.snowTicks = snowTicks;
    }
    
    public void setSpeedBVerbose(final int speedBVerbose) {
        this.speedBVerbose = speedBVerbose;
    }
    
    public void setSpeedAVerbose(final int speedAVerbose) {
        this.speedAVerbose = speedAVerbose;
    }
    
    public void setBoatTicks(final int boatTicks) {
        this.boatTicks = boatTicks;
    }
    
    public void setFlightBVerbose(final int flightBVerbose) {
        this.flightBVerbose = flightBVerbose;
    }
    
    public void setClimbableTicks(final int climbableTicks) {
        this.climbableTicks = climbableTicks;
    }
    
    public void setFlightADistance(final int flightADistance) {
        this.flightADistance = flightADistance;
    }
    
    public void setFlightAVerbose(final int flightAVerbose) {
        this.flightAVerbose = flightAVerbose;
    }
    
    public void setFuckingCPS(final int fuckingCPS) {
        this.fuckingCPS = fuckingCPS;
    }
    
    public void setTotalGaySlots(final int totalGaySlots) {
        this.totalGaySlots = totalGaySlots;
    }
    
    public void setLastGayAssSlot(final int lastGayAssSlot) {
        this.lastGayAssSlot = lastGayAssSlot;
    }
    
    public void setAimAssistFVerbose(final int aimAssistFVerbose) {
        this.aimAssistFVerbose = aimAssistFVerbose;
    }
    
    public void setNoSlowAVerbose(final int noSlowAVerbose) {
        this.noSlowAVerbose = noSlowAVerbose;
    }
    
    public void setVioSplit(final int vioSplit) {
        this.vioSplit = vioSplit;
    }
    
    public void setPacketsSkipped(final int packetsSkipped) {
        this.packetsSkipped = packetsSkipped;
    }
    
    public void setFullTickSkipped(final int fullTickSkipped) {
        this.fullTickSkipped = fullTickSkipped;
    }
    
    public void setAimAssistHCounter(final int aimAssistHCounter) {
        this.aimAssistHCounter = aimAssistHCounter;
    }
    
    public void setAimAssistHVerbose1Last(final int aimAssistHVerbose1Last) {
        this.aimAssistHVerbose1Last = aimAssistHVerbose1Last;
    }
    
    public void setAimAssistHPitchSame(final int aimAssistHPitchSame) {
        this.aimAssistHPitchSame = aimAssistHPitchSame;
    }
    
    public void setAimAssistHVerbose1(final int aimAssistHVerbose1) {
        this.aimAssistHVerbose1 = aimAssistHVerbose1;
    }
    
    public void setLastAimWExpect(final int lastAimWExpect) {
        this.lastAimWExpect = lastAimWExpect;
    }
    
    public void setAimWVerbose2(final int aimWVerbose2) {
        this.aimWVerbose2 = aimWVerbose2;
    }
    
    public void setLastAimWVerbose(final int lastAimWVerbose) {
        this.lastAimWVerbose = lastAimWVerbose;
    }
    
    public void setAimWVerbose(final int aimWVerbose) {
        this.aimWVerbose = aimWVerbose;
    }
    
    public void setLastAimWLen(final int lastAimWLen) {
        this.lastAimWLen = lastAimWLen;
    }
    
    public void setKys52(final int kys52) {
        this.kys52 = kys52;
    }
    
    public void setAimAssistBCount(final int aimAssistBCount) {
        this.aimAssistBCount = aimAssistBCount;
    }
    
    public void setKys(final int kys) {
        this.kys = kys;
    }
    
    public void setKillauraA2Ticks2(final int killauraA2Ticks2) {
        this.killauraA2Ticks2 = killauraA2Ticks2;
    }
    
    public void setKillauraA2Ticks1(final int killauraA2Ticks1) {
        this.killauraA2Ticks1 = killauraA2Ticks1;
    }
    
    public void setKillauraKCount(final int killauraKCount) {
        this.killauraKCount = killauraKCount;
    }
    
    public void setAimTVerbose(final int aimTVerbose) {
        this.aimTVerbose = aimTVerbose;
    }
    
    public void setTotalticks(final int totalticks) {
        this.totalticks = totalticks;
    }
    
    public void setVoidTicks(final int voidTicks) {
        this.voidTicks = voidTicks;
    }
    
    public void setFlyingTicks(final int flyingTicks) {
        this.flyingTicks = flyingTicks;
    }
    
    public void setBlockcancelTicks(final int blockcancelTicks) {
        this.blockcancelTicks = blockcancelTicks;
    }
    
    public void setConstantEntityTicks(final int constantEntityTicks) {
        this.constantEntityTicks = constantEntityTicks;
    }
    
    public void setAimS1Verbose(final int aimS1Verbose) {
        this.aimS1Verbose = aimS1Verbose;
    }
    
    public void setAimOPartTick(final int aimOPartTick) {
        this.aimOPartTick = aimOPartTick;
    }
    
    public void setAimOVerboseNigger(final int aimOVerboseNigger) {
        this.aimOVerboseNigger = aimOVerboseNigger;
    }
    
    public void setAimOYawStable(final int aimOYawStable) {
        this.aimOYawStable = aimOYawStable;
    }
    
    public void setRespawnTicks(final int respawnTicks) {
        this.respawnTicks = respawnTicks;
    }
    
    public void setBadPacketsKVerbose(final int badPacketsKVerbose) {
        this.badPacketsKVerbose = badPacketsKVerbose;
    }
    
    public void setBadPacketsJVerbose(final int badPacketsJVerbose) {
        this.badPacketsJVerbose = badPacketsJVerbose;
    }
    
    public void setLastBadPacketsEBlockDigTick(final int lastBadPacketsEBlockDigTick) {
        this.lastBadPacketsEBlockDigTick = lastBadPacketsEBlockDigTick;
    }
    
    public void setBadPacketsEVerbose(final int badPacketsEVerbose) {
        this.badPacketsEVerbose = badPacketsEVerbose;
    }
    
    public void setLastLastBadPacketsEBP(final int lastLastBadPacketsEBP) {
        this.lastLastBadPacketsEBP = lastLastBadPacketsEBP;
    }
    
    public void setLastBadPacketsEBlockPlaceTick(final int lastBadPacketsEBlockPlaceTick) {
        this.lastBadPacketsEBlockPlaceTick = lastBadPacketsEBlockPlaceTick;
    }
    
    public void setLastLastBadPacketsEBD(final int lastLastBadPacketsEBD) {
        this.lastLastBadPacketsEBD = lastLastBadPacketsEBD;
    }
    
    public void setOldTicks(final int oldTicks) {
        this.oldTicks = oldTicks;
    }
    
    public void setKBTicks(final int KBTicks) {
        this.KBTicks = KBTicks;
    }
    
    public void setYVelocityTicks(final int yVelocityTicks) {
        this.yVelocityTicks = yVelocityTicks;
    }
    
    public void setHVelocityTicks(final int hVelocityTicks) {
        this.hVelocityTicks = hVelocityTicks;
    }
    
    public void setJoinTicks(final int joinTicks) {
        this.joinTicks = joinTicks;
    }
    
    public void setTeleportTicks(final int teleportTicks) {
        this.teleportTicks = teleportTicks;
    }
    
    public void setDeadticks(final int Deadticks) {
        this.Deadticks = Deadticks;
    }
    
    public void setWaterTicks(final int waterTicks) {
        this.waterTicks = waterTicks;
    }
    
    public void setAimNOptifineTicks(final int aimNOptifineTicks) {
        this.aimNOptifineTicks = aimNOptifineTicks;
    }
    
    public void setAimMVerboseFuckYou(final int aimMVerboseFuckYou) {
        this.aimMVerboseFuckYou = aimMVerboseFuckYou;
    }
    
    public void setSlimeReset2(final int slimeReset2) {
        this.slimeReset2 = slimeReset2;
    }
    
    public void setSlimeReset(final int slimeReset) {
        this.slimeReset = slimeReset;
    }
    
    public void setSlimeTicks(final int slimeTicks) {
        this.slimeTicks = slimeTicks;
    }
    
    public void setPredictionDVerbose(final int predictionDVerbose) {
        this.predictionDVerbose = predictionDVerbose;
    }
    
    public void setAimCVerbose(final int aimCVerbose) {
        this.aimCVerbose = aimCVerbose;
    }
    
    public void setGravityVerbose(final int gravityVerbose) {
        this.gravityVerbose = gravityVerbose;
    }
    
    public void setJumpPotionTicks(final int jumpPotionTicks) {
        this.jumpPotionTicks = jumpPotionTicks;
    }
    
    public void setAimQAttacks(final int aimQAttacks) {
        this.aimQAttacks = aimQAttacks;
    }
    
    public void setAimQVerbose(final int aimQVerbose) {
        this.aimQVerbose = aimQVerbose;
    }
    
    public void setAimPCounter(final int aimPCounter) {
        this.aimPCounter = aimPCounter;
    }
    
    public void setAimPVerbose(final int aimPVerbose) {
        this.aimPVerbose = aimPVerbose;
    }
    
    public void setAttacks(final int attacks) {
        this.attacks = attacks;
    }
    
    public void setAimAssistAVerbose2(final int aimAssistAVerbose2) {
        this.aimAssistAVerbose2 = aimAssistAVerbose2;
    }
    
    public void setAimAssistAVerbose(final int aimAssistAVerbose) {
        this.aimAssistAVerbose = aimAssistAVerbose;
    }
    
    public void setAimOPredictionTicks(final int aimOPredictionTicks) {
        this.aimOPredictionTicks = aimOPredictionTicks;
    }
    
    public void setKillauraNTicks1(final int killauraNTicks1) {
        this.killauraNTicks1 = killauraNTicks1;
    }
    
    public void setKillauraNTicks2(final int killauraNTicks2) {
        this.killauraNTicks2 = killauraNTicks2;
    }
    
    public void setAimMVerbose(final int aimMVerbose) {
        this.aimMVerbose = aimMVerbose;
    }
    
    public void setAimLVerbose(final int aimLVerbose) {
        this.aimLVerbose = aimLVerbose;
    }
    
    public void setBadPacketsDPlaceAmount(final int badPacketsDPlaceAmount) {
        this.badPacketsDPlaceAmount = badPacketsDPlaceAmount;
    }
    
    public void setViolationSplit(final int violationSplit) {
        this.violationSplit = violationSplit;
    }
    
    public void setPistionTicks(final int pistionTicks) {
        this.pistionTicks = pistionTicks;
    }
    
    public void setRailTicks(final int railTicks) {
        this.railTicks = railTicks;
    }
    
    public void setBadPacketsCVerbose(final int badPacketsCVerbose) {
        this.badPacketsCVerbose = badPacketsCVerbose;
    }
    
    public void setBadPacketsOptifineTicks(final int badPacketsOptifineTicks) {
        this.badPacketsOptifineTicks = badPacketsOptifineTicks;
    }
    
    public void setAimKVerbose(final int aimKVerbose) {
        this.aimKVerbose = aimKVerbose;
    }
    
    public void setJumpBoostTicks(final int jumpBoostTicks) {
        this.jumpBoostTicks = jumpBoostTicks;
    }
    
    public void setAimJStable(final int aimJStable) {
        this.aimJStable = aimJStable;
    }
    
    public void setAimJVerbose(final int aimJVerbose) {
        this.aimJVerbose = aimJVerbose;
    }
    
    public void setAimJZeroHits(final int aimJZeroHits) {
        this.aimJZeroHits = aimJZeroHits;
    }
    
    public void setAimJGoodHits(final int aimJGoodHits) {
        this.aimJGoodHits = aimJGoodHits;
    }
    
    public void setAimJBadHits(final int aimJBadHits) {
        this.aimJBadHits = aimJBadHits;
    }
    
    public void setAimIVerbose(final int aimIVerbose) {
        this.aimIVerbose = aimIVerbose;
    }
    
    public void setAimHVerbose(final int aimHVerbose) {
        this.aimHVerbose = aimHVerbose;
    }
    
    public void setAimHOptifineTicks(final int aimHOptifineTicks) {
        this.aimHOptifineTicks = aimHOptifineTicks;
    }
    
    public void setNextToWallTicks(final int nextToWallTicks) {
        this.nextToWallTicks = nextToWallTicks;
    }
    
    public void setCancelTicks(final int cancelTicks) {
        this.cancelTicks = cancelTicks;
    }
    
    public void setTickSendH4(final int tickSendH4) {
        this.tickSendH4 = tickSendH4;
    }
    
    public void setVelocityEVerbose(final int velocityEVerbose) {
        this.velocityEVerbose = velocityEVerbose;
    }
    
    public void setVelocityDVerbose(final int velocityDVerbose) {
        this.velocityDVerbose = velocityDVerbose;
    }
    
    public void setTickSendH3(final int tickSendH3) {
        this.tickSendH3 = tickSendH3;
    }
    
    public void setTickSendH2(final int tickSendH2) {
        this.tickSendH2 = tickSendH2;
    }
    
    public void setVelocityCVerbose(final int velocityCVerbose) {
        this.velocityCVerbose = velocityCVerbose;
    }
    
    public void setTickSendH(final int tickSendH) {
        this.tickSendH = tickSendH;
    }
    
    public void setVelocityBVerbose(final int velocityBVerbose) {
        this.velocityBVerbose = velocityBVerbose;
    }
    
    public void setVelocityVerbose(final int velocityVerbose) {
        this.velocityVerbose = velocityVerbose;
    }
    
    public void setTickSend(final int tickSend) {
        this.tickSend = tickSend;
    }
    
    public void setTimerAVerbose(final int timerAVerbose) {
        this.timerAVerbose = timerAVerbose;
    }
    
    public void setTimerCVerbose(final int timerCVerbose) {
        this.timerCVerbose = timerCVerbose;
    }
    
    public void setBadPacksBVerbose(final int badPacksBVerbose) {
        this.badPacksBVerbose = badPacksBVerbose;
    }
    
    public void setBadPacketsAVerbose(final int badPacketsAVerbose) {
        this.badPacketsAVerbose = badPacketsAVerbose;
    }
    
    public void setSwings(final int swings) {
        this.swings = swings;
    }
    
    public void setAimEVerbose(final int aimEVerbose) {
        this.aimEVerbose = aimEVerbose;
    }
    
    public void setAimDVerbose(final int aimDVerbose) {
        this.aimDVerbose = aimDVerbose;
    }
    
    public void setFenceTicks(final int fenceTicks) {
        this.fenceTicks = fenceTicks;
    }
    
    public void setWallTicks(final int wallTicks) {
        this.wallTicks = wallTicks;
    }
    
    public void setWebTicks(final int webTicks) {
        this.webTicks = webTicks;
    }
    
    public void setGroundTicksMixed(final int groundTicksMixed) {
        this.groundTicksMixed = groundTicksMixed;
    }
    
    public void setAirTicksMixed(final int airTicksMixed) {
        this.airTicksMixed = airTicksMixed;
    }
    
    public void setSpeedPotionTicks(final int speedPotionTicks) {
        this.speedPotionTicks = speedPotionTicks;
    }
    
    public void setSpeedDVerbose(final int speedDVerbose) {
        this.speedDVerbose = speedDVerbose;
    }
    
    public void setClimableTicks(final int climableTicks) {
        this.climableTicks = climableTicks;
    }
    
    public void setReachAVerbose(final int reachAVerbose) {
        this.reachAVerbose = reachAVerbose;
    }
    
    public void setAimAOptifineVerbose(final int aimAOptifineVerbose) {
        this.aimAOptifineVerbose = aimAOptifineVerbose;
    }
    
    public void setAimAVerbose(final int aimAVerbose) {
        this.aimAVerbose = aimAVerbose;
    }
    
    public void setViolation(final int violation) {
        this.violation = violation;
    }
    
    public void setGroundTicks(final int groundTicks) {
        this.groundTicks = groundTicks;
    }
    
    public void setAirTicks(final int airTicks) {
        this.airTicks = airTicks;
    }
    
    public void setPing(final int ping) {
        this.ping = ping;
    }
    
    public void setStairTicks(final int stairTicks) {
        this.stairTicks = stairTicks;
    }
    
    public void setSlabTicks(final int slabTicks) {
        this.slabTicks = slabTicks;
    }
    
    public void setBlockAboveTicks(final int blockAboveTicks) {
        this.blockAboveTicks = blockAboveTicks;
    }
    
    public void setLiquidTicks(final int liquidTicks) {
        this.liquidTicks = liquidTicks;
    }
    
    public void setIceTicks(final int iceTicks) {
        this.iceTicks = iceTicks;
    }
    
    public void setNearIceTicks(final int nearIceTicks) {
        this.nearIceTicks = nearIceTicks;
    }
    
    public void setHalfBlockTicks(final int halfBlockTicks) {
        this.halfBlockTicks = halfBlockTicks;
    }
    
    public void setMountedTicks(final int mountedTicks) {
        this.mountedTicks = mountedTicks;
    }
    
    public void setLastMotionASpeedPotion(final long lastMotionASpeedPotion) {
        this.lastMotionASpeedPotion = lastMotionASpeedPotion;
    }
    
    public void setLastRespawn(final long lastRespawn) {
        this.lastRespawn = lastRespawn;
    }
    
    public void setLastBowDamage(final long lastBowDamage) {
        this.lastBowDamage = lastBowDamage;
    }
    
    public void setLastExplode(final long lastExplode) {
        this.lastExplode = lastExplode;
    }
    
    public void setLastSlime(final long lastSlime) {
        this.lastSlime = lastSlime;
    }
    
    public void setLastTimerAFlying(final long lastTimerAFlying) {
        this.lastTimerAFlying = lastTimerAFlying;
    }
    
    public void setAutoClickerEFlying(final long autoClickerEFlying) {
        this.autoClickerEFlying = autoClickerEFlying;
    }
    
    public void setAutoClickerESwing(final long autoClickerESwing) {
        this.autoClickerESwing = autoClickerESwing;
    }
    
    public void setAutoToolFlying(final long autoToolFlying) {
        this.autoToolFlying = autoToolFlying;
    }
    
    public void setAutoToolHeld(final long autoToolHeld) {
        this.autoToolHeld = autoToolHeld;
    }
    
    public void setLastBlockJump(final long lastBlockJump) {
        this.lastBlockJump = lastBlockJump;
    }
    
    public void setLastniggerarm(final long lastniggerarm) {
        this.lastniggerarm = lastniggerarm;
    }
    
    public void setBadPacketsDDown(final long badPacketsDDown) {
        this.badPacketsDDown = badPacketsDDown;
    }
    
    public void setLastPhaseAFlag(final long lastPhaseAFlag) {
        this.lastPhaseAFlag = lastPhaseAFlag;
    }
    
    public void setLastJumpPadUpdate(final long lastJumpPadUpdate) {
        this.lastJumpPadUpdate = lastJumpPadUpdate;
    }
    
    public void setLastJumpPadSet(final long lastJumpPadSet) {
        this.lastJumpPadSet = lastJumpPadSet;
    }
    
    public void setLastAimAssistF(final long lastAimAssistF) {
        this.lastAimAssistF = lastAimAssistF;
    }
    
    public void setLastSkippedTicksLag(final long lastSkippedTicksLag) {
        this.lastSkippedTicksLag = lastSkippedTicksLag;
    }
    
    public void setLastSkippedFlying(final long lastSkippedFlying) {
        this.lastSkippedFlying = lastSkippedFlying;
    }
    
    public void setLastAimAssistHGCDYaw(final long lastAimAssistHGCDYaw) {
        this.lastAimAssistHGCDYaw = lastAimAssistHGCDYaw;
    }
    
    public void setLastAimAssistHLook(final long lastAimAssistHLook) {
        this.lastAimAssistHLook = lastAimAssistHLook;
    }
    
    public void setLastAimAssistHPostion(final long lastAimAssistHPostion) {
        this.lastAimAssistHPostion = lastAimAssistHPostion;
    }
    
    public void setLastTransShit(final long lastTransShit) {
        this.lastTransShit = lastTransShit;
    }
    
    public void setLastPlace(final long lastPlace) {
        this.lastPlace = lastPlace;
    }
    
    public void setLastAimAssistGCD(final long lastAimAssistGCD) {
        this.lastAimAssistGCD = lastAimAssistGCD;
    }
    
    public void setLastKillauraHBad(final long lastKillauraHBad) {
        this.lastKillauraHBad = lastKillauraHBad;
    }
    
    public void setLastKillauraEGPitch(final long lastKillauraEGPitch) {
        this.lastKillauraEGPitch = lastKillauraEGPitch;
    }
    
    public void setLastAimOSetTime2(final long lastAimOSetTime2) {
        this.lastAimOSetTime2 = lastAimOSetTime2;
    }
    
    public void setLastAimOSetTime1(final long lastAimOSetTime1) {
        this.lastAimOSetTime1 = lastAimOSetTime1;
    }
    
    public void setLastBadPacketsKKeepAlive(final long lastBadPacketsKKeepAlive) {
        this.lastBadPacketsKKeepAlive = lastBadPacketsKKeepAlive;
    }
    
    public void setLastBadPacketsKTransaction(final long lastBadPacketsKTransaction) {
        this.lastBadPacketsKTransaction = lastBadPacketsKTransaction;
    }
    
    public void setLastBadPacketsKeepAlive(final long lastBadPacketsKeepAlive) {
        this.lastBadPacketsKeepAlive = lastBadPacketsKeepAlive;
    }
    
    public void setLastBadPacketsITransaction(final long lastBadPacketsITransaction) {
        this.lastBadPacketsITransaction = lastBadPacketsITransaction;
    }
    
    public void setLastBadPacketsFBlockPlace(final long lastBadPacketsFBlockPlace) {
        this.lastBadPacketsFBlockPlace = lastBadPacketsFBlockPlace;
    }
    
    public void setLastBadPacketsFBlockDig(final long lastBadPacketsFBlockDig) {
        this.lastBadPacketsFBlockDig = lastBadPacketsFBlockDig;
    }
    
    public void setLastBadPacketsEBlockDig(final long lastBadPacketsEBlockDig) {
        this.lastBadPacketsEBlockDig = lastBadPacketsEBlockDig;
    }
    
    public void setGetLastSlimeBounced(final long getLastSlimeBounced) {
        this.getLastSlimeBounced = getLastSlimeBounced;
    }
    
    public void setLastCancelPlace(final long lastCancelPlace) {
        this.lastCancelPlace = lastCancelPlace;
    }
    
    public void setLastAimPPos(final long lastAimPPos) {
        this.lastAimPPos = lastAimPPos;
    }
    
    public void setLastAimPSet(final long lastAimPSet) {
        this.lastAimPSet = lastAimPSet;
    }
    
    public void setLastAttacksSet(final long lastAttacksSet) {
        this.lastAttacksSet = lastAttacksSet;
    }
    
    public void setLastAimAssistUp(final long lastAimAssistUp) {
        this.lastAimAssistUp = lastAimAssistUp;
    }
    
    public void setLastAimAssistDip(final long lastAimAssistDip) {
        this.lastAimAssistDip = lastAimAssistDip;
    }
    
    public void setLastYawGCDAimAssistA(final long lastYawGCDAimAssistA) {
        this.lastYawGCDAimAssistA = lastYawGCDAimAssistA;
    }
    
    public void setLastAimOYawChange(final long lastAimOYawChange) {
        this.lastAimOYawChange = lastAimOYawChange;
    }
    
    public void setLastAimOPitchChange(final long lastAimOPitchChange) {
        this.lastAimOPitchChange = lastAimOPitchChange;
    }
    
    public void setLastAimOMove(final long lastAimOMove) {
        this.lastAimOMove = lastAimOMove;
    }
    
    public void setLastBadPacketDDig(final long lastBadPacketDDig) {
        this.lastBadPacketDDig = lastBadPacketDDig;
    }
    
    public void setLastBadPacketsDBlockPlace(final long lastBadPacketsDBlockPlace) {
        this.lastBadPacketsDBlockPlace = lastBadPacketsDBlockPlace;
    }
    
    public void setLastBadPacketsCFlag(final long lastBadPacketsCFlag) {
        this.lastBadPacketsCFlag = lastBadPacketsCFlag;
    }
    
    public void setLastBadPacketPredict(final long lastBadPacketPredict) {
        this.lastBadPacketPredict = lastBadPacketPredict;
    }
    
    public void setLastBadPacketsBPos(final long lastBadPacketsBPos) {
        this.lastBadPacketsBPos = lastBadPacketsBPos;
    }
    
    public void setLastAimJNegro(final long lastAimJNegro) {
        this.lastAimJNegro = lastAimJNegro;
    }
    
    public void setLastAimJSomeWhatAgOODDvALUE(final long lastAimJSomeWhatAgOODDvALUE) {
        this.lastAimJSomeWhatAgOODDvALUE = lastAimJSomeWhatAgOODDvALUE;
    }
    
    public void setLastAimHPostion(final long lastAimHPostion) {
        this.lastAimHPostion = lastAimHPostion;
    }
    
    public void setLastAttackByEntity(final long lastAttackByEntity) {
        this.lastAttackByEntity = lastAttackByEntity;
    }
    
    public void setLastGayModeSwitch(final long lastGayModeSwitch) {
        this.lastGayModeSwitch = lastGayModeSwitch;
    }
    
    public void setLastDamage(final long lastDamage) {
        this.lastDamage = lastDamage;
    }
    
    public void setLastTime(final long lastTime) {
        this.lastTime = lastTime;
    }
    
    public void setLastTime2(final long lastTime2) {
        this.lastTime2 = lastTime2;
    }
    
    public void setLastBadPacketsBBlockDig(final long lastBadPacketsBBlockDig) {
        this.lastBadPacketsBBlockDig = lastBadPacketsBBlockDig;
    }
    
    public void setLastHasPos(final long lastHasPos) {
        this.lastHasPos = lastHasPos;
    }
    
    public void setTimerALastTime(final long timerALastTime) {
        this.timerALastTime = timerALastTime;
    }
    
    public void setLastUnknownTeleport(final long lastUnknownTeleport) {
        this.lastUnknownTeleport = lastUnknownTeleport;
    }
    
    public void setLastBadPacketsAFlying(final long lastBadPacketsAFlying) {
        this.lastBadPacketsAFlying = lastBadPacketsAFlying;
    }
    
    public void setLastFullBlockMove(final long lastFullBlockMove) {
        this.lastFullBlockMove = lastFullBlockMove;
    }
    
    public void setLastAimEPositionLook(final long lastAimEPositionLook) {
        this.lastAimEPositionLook = lastAimEPositionLook;
    }
    
    public void setLastAimEPosition(final long lastAimEPosition) {
        this.lastAimEPosition = lastAimEPosition;
    }
    
    public void setLastBlockBreakCancel(final long lastBlockBreakCancel) {
        this.lastBlockBreakCancel = lastBlockBreakCancel;
    }
    
    public void setLastAttackedByPlayer(final long lastAttackedByPlayer) {
        this.lastAttackedByPlayer = lastAttackedByPlayer;
    }
    
    public void setLastAlertRec(final long lastAlertRec) {
        this.lastAlertRec = lastAlertRec;
    }
    
    public void setLastLag2(final long lastLag2) {
        this.lastLag2 = lastLag2;
    }
    
    public void setLastTransaction2(final long lastTransaction2) {
        this.lastTransaction2 = lastTransaction2;
    }
    
    public void setLastServerTransaction2(final long lastServerTransaction2) {
        this.lastServerTransaction2 = lastServerTransaction2;
    }
    
    public void setLastFlightToggle(final long lastFlightToggle) {
        this.lastFlightToggle = lastFlightToggle;
    }
    
    public void setLastGamemodeSwitch(final long lastGamemodeSwitch) {
        this.lastGamemodeSwitch = lastGamemodeSwitch;
    }
    
    public void setLastJoin(final long lastJoin) {
        this.lastJoin = lastJoin;
    }
    
    public void setLastLag(final long lastLag) {
        this.lastLag = lastLag;
    }
    
    public void setTransactionPing(final long transactionPing) {
        this.transactionPing = transactionPing;
    }
    
    public void setCalculatedPing(final long calculatedPing) {
        this.calculatedPing = calculatedPing;
    }
    
    public void setLastCalulated(final long lastCalulated) {
        this.lastCalulated = lastCalulated;
    }
    
    public void setLastTransUpdate(final long lastTransUpdate) {
        this.lastTransUpdate = lastTransUpdate;
    }
    
    public void setLastTransactionReciv(final long lastTransactionReciv) {
        this.lastTransactionReciv = lastTransactionReciv;
    }
    
    public void setCalculatedRealPing(final long calculatedRealPing) {
        this.calculatedRealPing = calculatedRealPing;
    }
    
    public void setLastLastFucker(final long lastLastFucker) {
        this.lastLastFucker = lastLastFucker;
    }
    
    public void setTransactionLast(final long transactionLast) {
        this.transactionLast = transactionLast;
    }
    
    public void setLastClientTransaction(final long lastClientTransaction) {
        this.lastClientTransaction = lastClientTransaction;
    }
    
    public void setLastServerTransaction(final long lastServerTransaction) {
        this.lastServerTransaction = lastServerTransaction;
    }
    
    public void setLastTransactionPing(final long lastTransactionPing) {
        this.lastTransactionPing = lastTransactionPing;
    }
    
    public void setLastTransDiff(final long lastTransDiff) {
        this.lastTransDiff = lastTransDiff;
    }
    
    public void setLastAttackPacket(final long lastAttackPacket) {
        this.lastAttackPacket = lastAttackPacket;
    }
    
    public void setLastIce(final long lastIce) {
        this.lastIce = lastIce;
    }
    
    public void setLastEntityVelocity(final long lastEntityVelocity) {
        this.lastEntityVelocity = lastEntityVelocity;
    }
    
    public void setLastTeleport(final long lastTeleport) {
        this.lastTeleport = lastTeleport;
    }
    
    public void setLastBukkitMovement(final long lastBukkitMovement) {
        this.lastBukkitMovement = lastBukkitMovement;
    }
    
    public void setLastServerKeepAlive(final long lastServerKeepAlive) {
        this.lastServerKeepAlive = lastServerKeepAlive;
    }
    
    public void setVelocityLength(final double velocityLength) {
        this.velocityLength = velocityLength;
    }
    
    public void setAimLThreshold(final double aimLThreshold) {
        this.aimLThreshold = aimLThreshold;
    }
    
    public void setAutoClickerGCps(final double autoClickerGCps) {
        this.autoClickerGCps = autoClickerGCps;
    }
    
    public void setLastMotionAPrediction(final double lastMotionAPrediction) {
        this.lastMotionAPrediction = lastMotionAPrediction;
    }
    
    public void setMotionAThreshold(final double motionAThreshold) {
        this.motionAThreshold = motionAThreshold;
    }
    
    public void setLastMotionAPredictionDiff(final double lastMotionAPredictionDiff) {
        this.lastMotionAPredictionDiff = lastMotionAPredictionDiff;
    }
    
    public void setLastPredictedDiff(final double lastPredictedDiff) {
        this.lastPredictedDiff = lastPredictedDiff;
    }
    
    public void setAutoClickerEVerbose(final double autoClickerEVerbose) {
        this.autoClickerEVerbose = autoClickerEVerbose;
    }
    
    public void setLastAutoClickerDSkewness(final double lastAutoClickerDSkewness) {
        this.lastAutoClickerDSkewness = lastAutoClickerDSkewness;
    }
    
    public void setLastAutoClickerDKurtosis(final double lastAutoClickerDKurtosis) {
        this.lastAutoClickerDKurtosis = lastAutoClickerDKurtosis;
    }
    
    public void setLastAutoClickerDAverage(final double lastAutoClickerDAverage) {
        this.lastAutoClickerDAverage = lastAutoClickerDAverage;
    }
    
    public void setLastFlightDGround(final double lastFlightDGround) {
        this.lastFlightDGround = lastFlightDGround;
    }
    
    public void setLastFlightCAcceleration(final double lastFlightCAcceleration) {
        this.lastFlightCAcceleration = lastFlightCAcceleration;
    }
    
    public void setLastFlightCDelta(final double lastFlightCDelta) {
        this.lastFlightCDelta = lastFlightCDelta;
    }
    
    public void setLastMotionAHorizontal(final double lastMotionAHorizontal) {
        this.lastMotionAHorizontal = lastMotionAHorizontal;
    }
    
    public void setLastBadPacketsBY(final double lastBadPacketsBY) {
        this.lastBadPacketsBY = lastBadPacketsBY;
    }
    
    public void setBadPacketsBDiff(final double badPacketsBDiff) {
        this.badPacketsBDiff = badPacketsBDiff;
    }
    
    public void setLastSpeedFSpeed(final double lastSpeedFSpeed) {
        this.lastSpeedFSpeed = lastSpeedFSpeed;
    }
    
    public void setLastSpeedBDistance(final double lastSpeedBDistance) {
        this.lastSpeedBDistance = lastSpeedBDistance;
    }
    
    public void setLastFlighBDiff(final double lastFlighBDiff) {
        this.lastFlighBDiff = lastFlighBDiff;
    }
    
    public void setLastAimDPitch(final double lastAimDPitch) {
        this.lastAimDPitch = lastAimDPitch;
    }
    
    public void setLastAimAssistHVal1(final double lastAimAssistHVal1) {
        this.lastAimAssistHVal1 = lastAimAssistHVal1;
    }
    
    public void setLastAimAssistHGCDPitch(final double lastAimAssistHGCDPitch) {
        this.lastAimAssistHGCDPitch = lastAimAssistHGCDPitch;
    }
    
    public void setLastAimWPitch(final double lastAimWPitch) {
        this.lastAimWPitch = lastAimWPitch;
    }
    
    public void setLastAimWShort(final double lastAimWShort) {
        this.lastAimWShort = lastAimWShort;
    }
    
    public void setLastAimWTrim(final double lastAimWTrim) {
        this.lastAimWTrim = lastAimWTrim;
    }
    
    public void setLastFixerIDK(final double lastFixerIDK) {
        this.lastFixerIDK = lastFixerIDK;
    }
    
    public void setKillauraA2LastValue(final double killauraA2LastValue) {
        this.killauraA2LastValue = killauraA2LastValue;
    }
    
    public void setAimSLastYaw(final double aimSLastYaw) {
        this.aimSLastYaw = aimSLastYaw;
    }
    
    public void setAimOLastValue(final double aimOLastValue) {
        this.aimOLastValue = aimOLastValue;
    }
    
    public void setLastAimNValue(final double lastAimNValue) {
        this.lastAimNValue = lastAimNValue;
    }
    
    public void setAvgPitchSpeed(final double avgPitchSpeed) {
        this.avgPitchSpeed = avgPitchSpeed;
    }
    
    public void setAvgPitchDevation(final double avgPitchDevation) {
        this.avgPitchDevation = avgPitchDevation;
    }
    
    public void setLastYDiff(final double lastYDiff) {
        this.lastYDiff = lastYDiff;
    }
    
    public void setLastY(final double lastY) {
        this.lastY = lastY;
    }
    
    public void setLastAimQPrediction(final double lastAimQPrediction) {
        this.lastAimQPrediction = lastAimQPrediction;
    }
    
    public void setLastAimQPitch(final double lastAimQPitch) {
        this.lastAimQPitch = lastAimQPitch;
    }
    
    public void setLastAimQYaw(final double lastAimQYaw) {
        this.lastAimQYaw = lastAimQYaw;
    }
    
    public void setLastAimQTurn(final double lastAimQTurn) {
        this.lastAimQTurn = lastAimQTurn;
    }
    
    public void setLastAimQGCD(final double lastAimQGCD) {
        this.lastAimQGCD = lastAimQGCD;
    }
    
    public void setAimQLastFixed(final double aimQLastFixed) {
        this.aimQLastFixed = aimQLastFixed;
    }
    
    public void setAimQPrediction(final double aimQPrediction) {
        this.aimQPrediction = aimQPrediction;
    }
    
    public void setLastAimPYawDiff(final double lastAimPYawDiff) {
        this.lastAimPYawDiff = lastAimPYawDiff;
    }
    
    public void setLastAimAssistAPitchDiff(final double lastAimAssistAPitchDiff) {
        this.lastAimAssistAPitchDiff = lastAimAssistAPitchDiff;
    }
    
    public void setLastAimAssitAYawDiff(final double lastAimAssitAYawDiff) {
        this.lastAimAssitAYawDiff = lastAimAssitAYawDiff;
    }
    
    public void setKillauraNLast(final double killauraNLast) {
        this.killauraNLast = killauraNLast;
    }
    
    public void setLastAimMPitch(final double lastAimMPitch) {
        this.lastAimMPitch = lastAimMPitch;
    }
    
    public void setLastAimKDiff(final double lastAimKDiff) {
        this.lastAimKDiff = lastAimKDiff;
    }
    
    public void setLastAimKMoveSpeed(final double lastAimKMoveSpeed) {
        this.lastAimKMoveSpeed = lastAimKMoveSpeed;
    }
    
    public void setLastAimIPitchDiff(final double lastAimIPitchDiff) {
        this.lastAimIPitchDiff = lastAimIPitchDiff;
    }
    
    public void setLastAimIYawDiff(final double lastAimIYawDiff) {
        this.lastAimIYawDiff = lastAimIYawDiff;
    }
    
    public void setLastAimHYawDiff(final double lastAimHYawDiff) {
        this.lastAimHYawDiff = lastAimHYawDiff;
    }
    
    public void setBalance(final double balance) {
        this.balance = balance;
    }
    
    public void setTimerABal(final double timerABal) {
        this.timerABal = timerABal;
    }
    
    public void setAimassistBVerbose(final double aimassistBVerbose) {
        this.aimassistBVerbose = aimassistBVerbose;
    }
    
    public void setLastAimAssistBVal(final double lastAimAssistBVal) {
        this.lastAimAssistBVal = lastAimAssistBVal;
    }
    
    public void setLastAimEDiff(final double lastAimEDiff) {
        this.lastAimEDiff = lastAimEDiff;
    }
    
    public void setLastAimDValue(final double lastAimDValue) {
        this.lastAimDValue = lastAimDValue;
    }
    
    public void setPredictionDLastY(final double predictionDLastY) {
        this.predictionDLastY = predictionDLastY;
    }
    
    public void setLastPredictedSpeed(final double lastPredictedSpeed) {
        this.lastPredictedSpeed = lastPredictedSpeed;
    }
    
    public void setLastongroundspeed(final double lastongroundspeed) {
        this.lastongroundspeed = lastongroundspeed;
    }
    
    public void setLastSpeedPotionMP(final double lastSpeedPotionMP) {
        this.lastSpeedPotionMP = lastSpeedPotionMP;
    }
    
    public void setLastAimCPitchDiff(final double lastAimCPitchDiff) {
        this.lastAimCPitchDiff = lastAimCPitchDiff;
    }
    
    public void setLastAimAPitch(final double lastAimAPitch) {
        this.lastAimAPitch = lastAimAPitch;
    }
    
    public void setMovementSpeed(final double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
    
    public void setDeltaXZ(final double deltaXZ) {
        this.deltaXZ = deltaXZ;
    }
    
    public void setDeltaY(final double deltaY) {
        this.deltaY = deltaY;
    }
    
    public void setDeltaX(final double deltaX) {
        this.deltaX = deltaX;
    }
    
    public void setDeltaZ(final double deltaZ) {
        this.deltaZ = deltaZ;
    }
    
    public void setLastDeltaXZ(final double lastDeltaXZ) {
        this.lastDeltaXZ = lastDeltaXZ;
    }
    
    public void setLastDeltaY(final double lastDeltaY) {
        this.lastDeltaY = lastDeltaY;
    }
    
    public void setLastAimAssistFPrevPitch(final float lastAimAssistFPrevPitch) {
        this.lastAimAssistFPrevPitch = lastAimAssistFPrevPitch;
    }
    
    public void setLastAimAssistFPrevYaw(final float lastAimAssistFPrevYaw) {
        this.lastAimAssistFPrevYaw = lastAimAssistFPrevYaw;
    }
    
    public void setAimAssistsFPitch(final float aimAssistsFPitch) {
        this.aimAssistsFPitch = aimAssistsFPitch;
    }
    
    public void setAimAssistsFYaw(final float aimAssistsFYaw) {
        this.aimAssistsFYaw = aimAssistsFYaw;
    }
    
    public void setLastAimAssistHYaw(final float lastAimAssistHYaw) {
        this.lastAimAssistHYaw = lastAimAssistHYaw;
    }
    
    public void setLastAimAssistHPitch(final float lastAimAssistHPitch) {
        this.lastAimAssistHPitch = lastAimAssistHPitch;
    }
    
    public void setLastAimAssistAPitch(final float lastAimAssistAPitch) {
        this.lastAimAssistAPitch = lastAimAssistAPitch;
    }
    
    public void setLastAimAssistAYaw(final float lastAimAssistAYaw) {
        this.lastAimAssistAYaw = lastAimAssistAYaw;
    }
    
    public void setFlightGGroundLocation(final Location flightGGroundLocation) {
        this.flightGGroundLocation = flightGGroundLocation;
    }
    
    public void setMovingStats(final MovingStats movingStats) {
        this.movingStats = movingStats;
    }
    
    public void setTpLocation(final Location tpLocation) {
        this.tpLocation = tpLocation;
    }
    
    public void setCancelLocation(final Location CancelLocation) {
        this.CancelLocation = CancelLocation;
    }
    
    public void setLastServerVelocity(final long lastServerVelocity) {
        this.lastServerVelocity = lastServerVelocity;
    }
    
    public void setLastTypeCSwing(final long lastTypeCSwing) {
        this.lastTypeCSwing = lastTypeCSwing;
    }
    
    public void setLastDoubleClick(final long lastDoubleClick) {
        this.lastDoubleClick = lastDoubleClick;
    }
    
    public void setLastFlag(final long LastFlag) {
        this.LastFlag = LastFlag;
    }
    
    public void setCpsTimer(final Timer cpsTimer) {
        this.cpsTimer = cpsTimer;
    }
    
    public void setClientBrand(final String clientBrand) {
        this.clientBrand = clientBrand;
    }
    
    public void setLastYawChangeA(final float lastYawChangeA) {
        this.lastYawChangeA = lastYawChangeA;
    }
    
    public void setLastPitchChangeA(final float lastPitchChangeA) {
        this.lastPitchChangeA = lastPitchChangeA;
    }
    
    public void setNoFallAVerbose(final Verbose noFallAVerbose) {
        this.noFallAVerbose = noFallAVerbose;
    }
    
    public void setSpeedFVerbose(final Verbose speedFVerbose) {
        this.speedFVerbose = speedFVerbose;
    }
    
    public void setSpeedCVerbose(final Verbose speedCVerbose) {
        this.speedCVerbose = speedCVerbose;
    }
    
    public void setAimD111Verbose(final Verbose aimD111Verbose) {
        this.aimD111Verbose = aimD111Verbose;
    }
    
    public void setBadPacketsJVerbose1(final Verbose badPacketsJVerbose1) {
        this.badPacketsJVerbose1 = badPacketsJVerbose1;
    }
    
    public void setAimAssistHVerbose2(final Verbose aimAssistHVerbose2) {
        this.aimAssistHVerbose2 = aimAssistHVerbose2;
    }
    
    public void setAimWVerbose1(final Verbose aimWVerbose1) {
        this.aimWVerbose1 = aimWVerbose1;
    }
    
    public void setAimAssistVerbose(final Verbose aimAssistVerbose) {
        this.aimAssistVerbose = aimAssistVerbose;
    }
    
    public void setKillauraA2Verbose(final Verbose killauraA2Verbose) {
        this.killauraA2Verbose = killauraA2Verbose;
    }
    
    public void setKillauraKVerboseFix(final Verbose killauraKVerboseFix) {
        this.killauraKVerboseFix = killauraKVerboseFix;
    }
    
    public void setAimSVerbose(final Verbose aimSVerbose) {
        this.aimSVerbose = aimSVerbose;
    }
    
    public void setBadPacketsFVerbose(final Verbose badPacketsFVerbose) {
        this.badPacketsFVerbose = badPacketsFVerbose;
    }
    
    public void setAimOVerbose(final Verbose aimOVerbose) {
        this.aimOVerbose = aimOVerbose;
    }
    
    public void setAimNVerbose(final Verbose aimNVerbose) {
        this.aimNVerbose = aimNVerbose;
    }
    
    public void setPredictionEVerbose(final Verbose predictionEVerbose) {
        this.predictionEVerbose = predictionEVerbose;
    }
    
    public void setKillauraLVerbose(final Verbose killauraLVerbose) {
        this.killauraLVerbose = killauraLVerbose;
    }
    
    public void setBadPacketsBVerbose(final Verbose badPacketsBVerbose) {
        this.badPacketsBVerbose = badPacketsBVerbose;
    }
    
    public void setKillauraNVerbose(final Verbose killauraNVerbose) {
        this.killauraNVerbose = killauraNVerbose;
    }
    
    public void setHitBoxAVerbose(final Verbose hitBoxAVerbose) {
        this.hitBoxAVerbose = hitBoxAVerbose;
    }
    
    public void setBadPacketsDVerbose2(final Verbose badPacketsDVerbose2) {
        this.badPacketsDVerbose2 = badPacketsDVerbose2;
    }
    
    public void setBadPacketsDVerbose(final Verbose badPacketsDVerbose) {
        this.badPacketsDVerbose = badPacketsDVerbose;
    }
    
    public void setAimassistBVerbose2(final Verbose aimassistBVerbose2) {
        this.aimassistBVerbose2 = aimassistBVerbose2;
    }
    
    public void setAimIVerbose2(final Verbose aimIVerbose2) {
        this.aimIVerbose2 = aimIVerbose2;
    }
    
    public void setCriticalsAVerbose(final Verbose criticalsAVerbose) {
        this.criticalsAVerbose = criticalsAVerbose;
    }
    
    public void setAimDVerbose2(final Verbose aimDVerbose2) {
        this.aimDVerbose2 = aimDVerbose2;
    }
    
    public void setAimBVerbose(final Verbose aimBVerbose) {
        this.aimBVerbose = aimBVerbose;
    }
    
    public void setLastEntityAttacked(final Entity lastEntityAttacked) {
        this.lastEntityAttacked = lastEntityAttacked;
    }
    
    public void setReachAPastLocations(final PastLocation reachAPastLocations) {
        this.reachAPastLocations = reachAPastLocations;
    }
    
    public void setReachBPastLocations(final PastLocation reachBPastLocations) {
        this.reachBPastLocations = reachBPastLocations;
    }
    
    public void setHitBoxPastLocations(final PastLocation hitBoxPastLocations) {
        this.hitBoxPastLocations = hitBoxPastLocations;
    }
    
    public void setLastFlightDLocation(final CustomLocation lastFlightDLocation) {
        this.lastFlightDLocation = lastFlightDLocation;
    }
    
    public void setNewTo(final CustomLocation newTo) {
        this.newTo = newTo;
    }
    
    public void setNewFrom(final CustomLocation newFrom) {
        this.newFrom = newFrom;
    }
    
    public void setLastFlightDGroundLocation(final CustomLocation lastFlightDGroundLocation) {
        this.lastFlightDGroundLocation = lastFlightDGroundLocation;
    }
    
    public void setLastOnGroundLocation(final CustomLocation lastOnGroundLocation) {
        this.lastOnGroundLocation = lastOnGroundLocation;
    }
    
    public void setProbabilitySystem(final ProbabilitySystem probabilitySystem) {
        this.probabilitySystem = probabilitySystem;
    }
    
    public void setAimAssistsAPatterns(final List<Double> aimAssistsAPatterns) {
        this.aimAssistsAPatterns = aimAssistsAPatterns;
    }
    
    public void setAimQPatterns(final List<Double> aimQPatterns) {
        this.aimQPatterns = aimQPatterns;
    }
    
    public void setAimSSamples(final List<Double> aimSSamples) {
        this.aimSSamples = aimSSamples;
    }
    
    public void setAimWSamples(final List<Double> aimWSamples) {
        this.aimWSamples = aimWSamples;
    }
    
    public void setLastTimerBFlying(final long lastTimerBFlying) {
        this.lastTimerBFlying = lastTimerBFlying;
    }
    
    public void setLastOptifine(final long lastOptifine) {
        this.lastOptifine = lastOptifine;
    }
    
    public void setLastOptifineREE(final long lastOptifineREE) {
        this.lastOptifineREE = lastOptifineREE;
    }
    
    public void setLastRetardOpitfineSpam(final long lastRetardOpitfineSpam) {
        this.lastRetardOpitfineSpam = lastRetardOpitfineSpam;
    }
    
    public void setLastAimAssistACE(final long lastAimAssistACE) {
        this.lastAimAssistACE = lastAimAssistACE;
    }
    
    public void setOptifineSmoothing2(final int optifineSmoothing2) {
        this.optifineSmoothing2 = optifineSmoothing2;
    }
    
    public void setLastClientSmoothingValue(final int lastClientSmoothingValue) {
        this.lastClientSmoothingValue = lastClientSmoothingValue;
    }
    
    public void setOptifineSmoothing(final int optifineSmoothing) {
        this.optifineSmoothing = optifineSmoothing;
    }
    
    public void setLastSmoothingCounter(final int LastSmoothingCounter) {
        this.LastSmoothingCounter = LastSmoothingCounter;
    }
    
    public void setSmoothingCounter(final int smoothingCounter) {
        this.smoothingCounter = smoothingCounter;
    }
    
    public void setOptifineSmoothSens(final int optifineSmoothSens) {
        this.optifineSmoothSens = optifineSmoothSens;
    }
    
    public void setOptifinePitchSmooth(final int optifinePitchSmooth) {
        this.optifinePitchSmooth = optifinePitchSmooth;
    }
    
    public void setOptifineSameCount(final int optifineSameCount) {
        this.optifineSameCount = optifineSameCount;
    }
    
    public void setOptifineConstantVL2(final int optifineConstantVL2) {
        this.optifineConstantVL2 = optifineConstantVL2;
    }
    
    public void setOptifineConstantVL(final int optifineConstantVL) {
        this.optifineConstantVL = optifineConstantVL;
    }
    
    public void setOptifineSmoothingFix(final int optifineSmoothingFix) {
        this.optifineSmoothingFix = optifineSmoothingFix;
    }
    
    public void setKillauraAYawReset(final int killauraAYawReset) {
        this.killauraAYawReset = killauraAYawReset;
    }
    
    public void setKillauraAPitchReset(final int killauraAPitchReset) {
        this.killauraAPitchReset = killauraAPitchReset;
    }
    
    public void setAimAssistsACount(final int aimAssistsACount) {
        this.aimAssistsACount = aimAssistsACount;
    }
    
    public void setOptifineSmoothingTicks(final int optifineSmoothingTicks) {
        this.optifineSmoothingTicks = optifineSmoothingTicks;
    }
    
    public void setAimWSmooth(final MCSmoothing aimWSmooth) {
        this.aimWSmooth = aimWSmooth;
    }
    
    public void setNewPitchSmoothing(final MCSmoothing newPitchSmoothing) {
        this.newPitchSmoothing = newPitchSmoothing;
    }
    
    public void setNewYawSmoothing(final MCSmoothing newYawSmoothing) {
        this.newYawSmoothing = newYawSmoothing;
    }
    
    public void setYaw(final MCSmoothing yaw) {
        this.yaw = yaw;
    }
    
    public void setPitch(final MCSmoothing pitch) {
        this.pitch = pitch;
    }
    
    public void setSmooth(final MCSmoothing smooth) {
        this.smooth = smooth;
    }
    
    public void setLastSmoothingRot2(final double lastSmoothingRot2) {
        this.lastSmoothingRot2 = lastSmoothingRot2;
    }
    
    public void setLastSmoothingRot(final double lastSmoothingRot) {
        this.lastSmoothingRot = lastSmoothingRot;
    }
    
    public void setLastPitchDelta(final double lastPitchDelta) {
        this.lastPitchDelta = lastPitchDelta;
    }
    
    public void setLastSmoothPitch1(final double lastSmoothPitch1) {
        this.lastSmoothPitch1 = lastSmoothPitch1;
    }
    
    public void setLastOptifinePitchSmoothidfklol(final double lastOptifinePitchSmoothidfklol) {
        this.lastOptifinePitchSmoothidfklol = lastOptifinePitchSmoothidfklol;
    }
    
    public void setLastYawDelta(final float lastYawDelta) {
        this.lastYawDelta = lastYawDelta;
    }
    
    public void setLastSmoothYaw(final float lastSmoothYaw) {
        this.lastSmoothYaw = lastSmoothYaw;
    }
    
    public void setCineCamera(final boolean cineCamera) {
        this.cineCamera = cineCamera;
    }
    
    public void setLastGUICheckType(final CheckType lastGUICheckType) {
        this.lastGUICheckType = lastGUICheckType;
    }
    
    public void setTicksB(final int ticksB) {
        this.ticksB = ticksB;
    }
    
    public void setAutoClickerBVerbose(final int autoClickerBVerbose) {
        this.autoClickerBVerbose = autoClickerBVerbose;
    }
    
    public void setTicksC(final int ticksC) {
        this.ticksC = ticksC;
    }
    
    public void setAutoClickerAVerbose(final int autoClickerAVerbose) {
        this.autoClickerAVerbose = autoClickerAVerbose;
    }
    
    public void setTicksA(final int ticksA) {
        this.ticksA = ticksA;
    }
    
    public void setTicksD(final int ticksD) {
        this.ticksD = ticksD;
    }
    
    public void setAvgSpeedB(final double avgSpeedB) {
        this.avgSpeedB = avgSpeedB;
    }
    
    public void setAvgDevationB(final double avgDevationB) {
        this.avgDevationB = avgDevationB;
    }
    
    public void setAvgSpeedC(final double avgSpeedC) {
        this.avgSpeedC = avgSpeedC;
    }
    
    public void setAvgDevationC(final double avgDevationC) {
        this.avgDevationC = avgDevationC;
    }
    
    public void setAvgSpeedA(final double avgSpeedA) {
        this.avgSpeedA = avgSpeedA;
    }
    
    public void setAvgDevationA(final double avgDevationA) {
        this.avgDevationA = avgDevationA;
    }
    
    public void setRecentCountsC(final LinkedList<Integer> recentCountsC) {
        this.recentCountsC = recentCountsC;
    }
    
    public void setRecentCountsD(final LinkedList<Integer> recentCountsD) {
        this.recentCountsD = recentCountsD;
    }
    
    public void setMovementProcessor(final MovementProcessor movementProcessor) {
        this.movementProcessor = movementProcessor;
    }
    
    public void setLagProcessor(final LagProcessor lagProcessor) {
        this.lagProcessor = lagProcessor;
    }
    
    public void setCombatProcessor(final CombatProcessor combatProcessor) {
        this.combatProcessor = combatProcessor;
    }
    
    public void setPredictionProcessor(final PredictionProcessor predictionProcessor) {
        this.predictionProcessor = predictionProcessor;
    }
    
    public void setGlobalObject(final GlobalObject globalObject) {
        this.globalObject = globalObject;
    }
    
    public void setGenericAttributesSpeed(final double genericAttributesSpeed) {
        this.genericAttributesSpeed = genericAttributesSpeed;
    }
    
    public void setRetardedLocation(final CustomLocation retardedLocation) {
        this.retardedLocation = retardedLocation;
    }
    
    public void setAutoClickerBTicks(final int autoClickerBTicks) {
        this.autoClickerBTicks = autoClickerBTicks;
    }
    
    public void setAutoClickerETicks(final int autoClickerETicks) {
        this.autoClickerETicks = autoClickerETicks;
    }
    
    public void setAutoClickerHTicks(final int autoClickerHTicks) {
        this.autoClickerHTicks = autoClickerHTicks;
    }
    
    public void setAutoClickerBCounts(final LinkedList<Integer> autoClickerBCounts) {
        this.autoClickerBCounts = autoClickerBCounts;
    }
    
    public void setAutoClickerECounts(final LinkedList<Integer> autoClickerECounts) {
        this.autoClickerECounts = autoClickerECounts;
    }
    
    public void setLastAutoClickerESTD(final double lastAutoClickerESTD) {
        this.lastAutoClickerESTD = lastAutoClickerESTD;
    }
    
    public void setLastAutoClickerHRange(final double lastAutoClickerHRange) {
        this.lastAutoClickerHRange = lastAutoClickerHRange;
    }
    
    public void setAvgAutoClickerH(final double avgAutoClickerH) {
        this.avgAutoClickerH = avgAutoClickerH;
    }
    
    public void setAutoClickerHPattern(final List<Double> autoClickerHPattern) {
        this.autoClickerHPattern = autoClickerHPattern;
    }
    
    public void setAutoClickerDSamples(final EvictingList<Integer> autoClickerDSamples) {
        this.autoClickerDSamples = autoClickerDSamples;
    }
    
    public void setVelocityLengthSamples(final List<VelocitySnapshot> velocityLengthSamples) {
        this.velocityLengthSamples = velocityLengthSamples;
    }
    
    public enum UpdateType
    {
        TICKS;
    }
}
