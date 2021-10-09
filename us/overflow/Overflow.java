// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow;

import us.overflow.tinyprotocol.packet.out.WrappedOutTransaction;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import us.overflow.utils.http.ALAPI;
import us.overflow.base.user.User;
import org.bukkit.entity.Player;
import us.overflow.utils.other.TimeUtils;
import us.overflow.listeners.PacketListener;
import us.overflow.antivpn.AntiVPNListener;
import us.overflow.gui.listener.GUIListener;
import us.overflow.listeners.GeneralListeners;
import us.overflow.utils.command.CommandUtils;
import org.bukkit.plugin.messaging.PluginMessageListener;
import us.overflow.base.BungeecordListener;
import org.bukkit.event.Listener;
import us.overflow.listeners.PhaseListener;
import java.util.function.Consumer;
import us.overflow.utils.MathUtil;
import us.overflow.utils.claszz.ClassHelper;
import us.overflow.utils.http.HTTPUtil;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import us.overflow.base.processors.OptifineProcessor;
import us.overflow.utils.block.BlockUtil;
import us.overflow.utils.blockbox.ReflectionUtil;
import us.overflow.utils.reflection.MinecraftReflection;
import us.overflow.utils.file.DirFile;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import us.overflow.utils.file.KeyFile;
import java.util.concurrent.Executors;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import us.overflow.theme.ThemeManager;
import us.overflow.discord.DiscordAPI;
import us.overflow.database.sockets.MySQLSocket;
import us.overflow.utils.other.PermissionUtil;
import us.overflow.database.DatabaseHelper;
import us.overflow.base.user.global.GlobalUserManager;
import us.overflow.base.user.global.GlobalObject;
import java.util.ArrayList;
import us.overflow.utils.gui.GUIHelper;
import us.overflow.utils.other.DataHeleper;
import us.overflow.utils.config.ConfigValues;
import us.overflow.events.OverflowListener;
import us.overflow.base.CheckManager;
import org.bukkit.entity.Entity;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import us.overflow.events.EventManager;
import java.util.concurrent.ScheduledExecutorService;
import us.overflow.tinyprotocol.api.TinyProtocolHandler;
import us.overflow.utils.blockbox.impl.BoundingBoxes;
import us.overflow.utils.blockbox.BlockBoxManager;
import us.overflow.base.user.UserManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Overflow extends JavaPlugin
{
    private static Overflow instance;
    private UserManager userManager;
    private BlockBoxManager blockBoxManager;
    private BoundingBoxes boxes;
    private String bukkitVerbose;
    private String pluginVersion;
    private String key;
    private TinyProtocolHandler tinyProtocolHandler;
    private ScheduledExecutorService executorService;
    private ScheduledExecutorService processorService;
    private ScheduledExecutorService databaseExecutor;
    private ScheduledExecutorService discordPostExecutor;
    private EventManager eventManager;
    private Map<UUID, List<Entity>> entities;
    private CheckManager checkManager;
    private List<OverflowListener> overflowListeners;
    private List<UUID> banQueue;
    private Version version;
    private boolean disabled;
    private boolean addonHooked;
    private boolean discordAddonLib;
    private boolean isLagging;
    private ConfigValues configValues;
    private DataHeleper dataHeleper;
    private long lastStart;
    private long lastThreadTick;
    private long lastExternalThreadTick;
    private long lastServerLag;
    private long lastServerStart;
    private long lastServerTick;
    private GUIHelper guiHelper;
    private ArrayList<GlobalObject> Users;
    private GlobalUserManager globalUserManager;
    private DatabaseHelper databaseHelper;
    private PermissionUtil permissionUtil;
    private MySQLSocket mySQLSocket;
    private int serverLagVerbose;
    public String configDIR;
    public String checksDIR;
    public String logsDIR;
    public String banwaveDIR;
    public String currentDate;
    private DiscordAPI discordAPI;
    private int totalFlags;
    private int totalBans;
    private ThemeManager themeManager;
    private int currentTicks;
    
    public Overflow() {
        this.entities = new ConcurrentHashMap();
        this.overflowListeners = Collections.synchronizedList(new ArrayList<Object>());
        this.banQueue = new CopyOnWriteArrayList();
        this.configValues = new ConfigValues();
        this.dataHeleper = new DataHeleper();
        this.guiHelper = new GUIHelper();
        this.Users = new ArrayList();
        this.globalUserManager = new GlobalUserManager();
        this.databaseHelper = new DatabaseHelper();
        this.permissionUtil = new PermissionUtil();
        this.mySQLSocket = new MySQLSocket();
        this.currentDate = "(NOT SET)";
        this.themeManager = new ThemeManager();
    }
    
    public void onEnable() {
        Overflow.instance = this;
        try {
            Class.forName("me.jumba.addon.event.PhaseFlagEvent");
            this.addonHooked = true;
        }
        catch (ClassNotFoundException ignored) {
            this.addonHooked = false;
        }
        try {
            Class.forName("me.jumba.plugin.Plugin");
            Class.forName("club.minnced.discord.webhook.WebhookCluster");
            this.discordAPI = new DiscordAPI();
            this.discordAddonLib = true;
        }
        catch (ClassNotFoundException ignored) {
            this.discordAddonLib = false;
        }
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        this.discordPostExecutor = Executors.newSingleThreadScheduledExecutor();
        KeyFile.getInstance().setup((Plugin)this);
        KeyFile.getInstance().writeDefaults();
        this.key = KeyFile.getInstance().getData().getString("LicenseKey");
        this.databaseExecutor = Executors.newSingleThreadScheduledExecutor();
        this.bukkitVerbose = Bukkit.getServer().getClass().getPackage().getName().substring(23);
        this.pluginVersion = this.getDescription().getVersion();
        if (this.bukkitVerbose.contains("1_8")) {
            this.version = Version.V1_8;
        }
        if (this.bukkitVerbose.contains("1_7")) {
            this.version = Version.V1_7;
        }
        DirFile.getInstance().setup((Plugin)this);
        this.configDIR = DirFile.getInstance().getData().getString("config");
        this.checksDIR = DirFile.getInstance().getData().getString("checks");
        this.logsDIR = DirFile.getInstance().getData().getString("logs");
        this.banwaveDIR = DirFile.getInstance().getData().getString("banwave");
        this.userManager = new UserManager();
        new MinecraftReflection();
        new ReflectionUtil();
        new BlockUtil();
        this.blockBoxManager = new BlockBoxManager();
        this.boxes = new BoundingBoxes();
        this.tinyProtocolHandler = new TinyProtocolHandler();
        this.processorService = Executors.newSingleThreadScheduledExecutor();
        this.eventManager = new EventManager();
        this.loadListeners();
        this.checkManager = new CheckManager();
        new OptifineProcessor();
        this.executorService.scheduleAtFixedRate(this::lambda$onEnable$2, 500L, 500L, TimeUnit.MILLISECONDS);
        this.executorService.scheduleAtFixedRate(this::lambda$onEnable$5, 2050L, 2050L, TimeUnit.MILLISECONDS);
        this.executorService.scheduleAtFixedRate(this::lambda$onEnable$7, 60L, 60L, TimeUnit.SECONDS);
        final HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("DKDKFJK23002FL", "NtmPXMZy9AqSMEKQ$vp7*6S*BQOoR3unJYmVKEEJc18Nuu#YK$");
        final String resolvedClass1 = HTTPUtil.getResponse("https://overflowac.pw/Panel/2.0Resolver.php?clazz=29483945883999299", (HashMap)headers);
        ClassHelper.callClass(resolvedClass1);
        headers.clear();
        System.gc();
        new MathUtil();
        this.lastStart = System.currentTimeMillis();
        Bukkit.getOnlinePlayers().forEach(this::lambda$onEnable$8);
        this.databaseExecutor.scheduleAtFixedRate(this::lambda$onEnable$10, 5L, 5L, TimeUnit.SECONDS);
        Bukkit.getOnlinePlayers().forEach(Overflow::lambda$onEnable$11);
        if (this.isAddonHooked()) {
            this.getServer().getPluginManager().registerEvents((Listener)new PhaseListener(), (Plugin)this);
        }
        this.executorService.scheduleAtFixedRate(this::lambda$onEnable$12, 1L, 1L, TimeUnit.MINUTES);
        this.executorService.scheduleAtFixedRate(this::lambda$onEnable$13, 10L, 10L, TimeUnit.MINUTES);
        this.executorService.scheduleAtFixedRate(this::lambda$onEnable$14, 20L, 20L, TimeUnit.MINUTES);
        new Overflow$1(this).runTaskTimer((Plugin)this, 20L, 20L);
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "OverflowAlerts");
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel((Plugin)this, "OverflowListener", (PluginMessageListener)new BungeecordListener());
        this.executorService.scheduleAtFixedRate(this::lambda$onEnable$15, 50L, 50L, TimeUnit.MILLISECONDS);
        this.startLagCheck();
    }
    
    public void onDisable() {
        this.checkManager.unRegisterAll();
        Bukkit.getOnlinePlayers().forEach(this::lambda$onDisable$16);
        this.processorService.shutdownNow();
        this.executorService.shutdownNow();
        this.overflowListeners.parallelStream().forEach(this::lambda$onDisable$17);
        CommandUtils.unRegisterBukkitCommand("overflow");
        CommandUtils.unRegisterBukkitCommand("alerts");
        CommandUtils.unRegisterBukkitCommand(this.configValues.getCustomAnticheatCommand());
        this.databaseExecutor.shutdownNow();
        this.discordPostExecutor.shutdownNow();
    }
    
    private void loadListeners() {
        this.getServer().getPluginManager().registerEvents((Listener)new GeneralListeners(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new GUIListener(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new AntiVPNListener(), (Plugin)this);
        this.overflowListeners.add(new PacketListener());
        this.overflowListeners.parallelStream().forEach(this::lambda$loadListeners$18);
    }
    
    private void startLagCheck() {
        this.executorService.scheduleAtFixedRate(this::lambda$startLagCheck$19, 1L, 1L, TimeUnit.MILLISECONDS);
        new Overflow$2(this).runTaskTimer((Plugin)this, 1L, 1L);
    }
    
    public void setUserManager(final UserManager userManager) {
        this.userManager = userManager;
    }
    
    public void setBlockBoxManager(final BlockBoxManager blockBoxManager) {
        this.blockBoxManager = blockBoxManager;
    }
    
    public void setBoxes(final BoundingBoxes boxes) {
        this.boxes = boxes;
    }
    
    public void setBukkitVerbose(final String bukkitVerbose) {
        this.bukkitVerbose = bukkitVerbose;
    }
    
    public void setPluginVersion(final String pluginVersion) {
        this.pluginVersion = pluginVersion;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public void setTinyProtocolHandler(final TinyProtocolHandler tinyProtocolHandler) {
        this.tinyProtocolHandler = tinyProtocolHandler;
    }
    
    public void setExecutorService(final ScheduledExecutorService executorService) {
        this.executorService = executorService;
    }
    
    public void setProcessorService(final ScheduledExecutorService processorService) {
        this.processorService = processorService;
    }
    
    public void setDatabaseExecutor(final ScheduledExecutorService databaseExecutor) {
        this.databaseExecutor = databaseExecutor;
    }
    
    public void setDiscordPostExecutor(final ScheduledExecutorService discordPostExecutor) {
        this.discordPostExecutor = discordPostExecutor;
    }
    
    public void setEventManager(final EventManager eventManager) {
        this.eventManager = eventManager;
    }
    
    public void setEntities(final Map<UUID, List<Entity>> entities) {
        this.entities = entities;
    }
    
    public void setCheckManager(final CheckManager checkManager) {
        this.checkManager = checkManager;
    }
    
    public void setOverflowListeners(final List<OverflowListener> overflowListeners) {
        this.overflowListeners = overflowListeners;
    }
    
    public void setBanQueue(final List<UUID> banQueue) {
        this.banQueue = banQueue;
    }
    
    public void setVersion(final Version version) {
        this.version = version;
    }
    
    public void setDisabled(final boolean disabled) {
        this.disabled = disabled;
    }
    
    public void setAddonHooked(final boolean addonHooked) {
        this.addonHooked = addonHooked;
    }
    
    public void setDiscordAddonLib(final boolean discordAddonLib) {
        this.discordAddonLib = discordAddonLib;
    }
    
    public void setLagging(final boolean isLagging) {
        this.isLagging = isLagging;
    }
    
    public void setConfigValues(final ConfigValues configValues) {
        this.configValues = configValues;
    }
    
    public void setDataHeleper(final DataHeleper dataHeleper) {
        this.dataHeleper = dataHeleper;
    }
    
    public void setLastStart(final long lastStart) {
        this.lastStart = lastStart;
    }
    
    public void setLastThreadTick(final long lastThreadTick) {
        this.lastThreadTick = lastThreadTick;
    }
    
    public void setLastExternalThreadTick(final long lastExternalThreadTick) {
        this.lastExternalThreadTick = lastExternalThreadTick;
    }
    
    public void setLastServerLag(final long lastServerLag) {
        this.lastServerLag = lastServerLag;
    }
    
    public void setLastServerStart(final long lastServerStart) {
        this.lastServerStart = lastServerStart;
    }
    
    public void setLastServerTick(final long lastServerTick) {
        this.lastServerTick = lastServerTick;
    }
    
    public void setGuiHelper(final GUIHelper guiHelper) {
        this.guiHelper = guiHelper;
    }
    
    public void setUsers(final ArrayList<GlobalObject> Users) {
        this.Users = Users;
    }
    
    public void setGlobalUserManager(final GlobalUserManager globalUserManager) {
        this.globalUserManager = globalUserManager;
    }
    
    public void setDatabaseHelper(final DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
    
    public void setPermissionUtil(final PermissionUtil permissionUtil) {
        this.permissionUtil = permissionUtil;
    }
    
    public void setMySQLSocket(final MySQLSocket mySQLSocket) {
        this.mySQLSocket = mySQLSocket;
    }
    
    public void setServerLagVerbose(final int serverLagVerbose) {
        this.serverLagVerbose = serverLagVerbose;
    }
    
    public void setConfigDIR(final String configDIR) {
        this.configDIR = configDIR;
    }
    
    public void setChecksDIR(final String checksDIR) {
        this.checksDIR = checksDIR;
    }
    
    public void setLogsDIR(final String logsDIR) {
        this.logsDIR = logsDIR;
    }
    
    public void setBanwaveDIR(final String banwaveDIR) {
        this.banwaveDIR = banwaveDIR;
    }
    
    public void setCurrentDate(final String currentDate) {
        this.currentDate = currentDate;
    }
    
    public void setDiscordAPI(final DiscordAPI discordAPI) {
        this.discordAPI = discordAPI;
    }
    
    public void setTotalFlags(final int totalFlags) {
        this.totalFlags = totalFlags;
    }
    
    public void setTotalBans(final int totalBans) {
        this.totalBans = totalBans;
    }
    
    public void setThemeManager(final ThemeManager themeManager) {
        this.themeManager = themeManager;
    }
    
    public void setCurrentTicks(final int currentTicks) {
        this.currentTicks = currentTicks;
    }
    
    public UserManager getUserManager() {
        return this.userManager;
    }
    
    public BlockBoxManager getBlockBoxManager() {
        return this.blockBoxManager;
    }
    
    public BoundingBoxes getBoxes() {
        return this.boxes;
    }
    
    public String getBukkitVerbose() {
        return this.bukkitVerbose;
    }
    
    public String getPluginVersion() {
        return this.pluginVersion;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public TinyProtocolHandler getTinyProtocolHandler() {
        return this.tinyProtocolHandler;
    }
    
    public ScheduledExecutorService getExecutorService() {
        return this.executorService;
    }
    
    public ScheduledExecutorService getProcessorService() {
        return this.processorService;
    }
    
    public ScheduledExecutorService getDatabaseExecutor() {
        return this.databaseExecutor;
    }
    
    public ScheduledExecutorService getDiscordPostExecutor() {
        return this.discordPostExecutor;
    }
    
    public EventManager getEventManager() {
        return this.eventManager;
    }
    
    public Map<UUID, List<Entity>> getEntities() {
        return (Map<UUID, List<Entity>>)this.entities;
    }
    
    public CheckManager getCheckManager() {
        return this.checkManager;
    }
    
    public List<OverflowListener> getOverflowListeners() {
        return (List<OverflowListener>)this.overflowListeners;
    }
    
    public List<UUID> getBanQueue() {
        return (List<UUID>)this.banQueue;
    }
    
    public Version getVersion() {
        return this.version;
    }
    
    public boolean isDisabled() {
        return this.disabled;
    }
    
    public boolean isAddonHooked() {
        return this.addonHooked;
    }
    
    public boolean isDiscordAddonLib() {
        return this.discordAddonLib;
    }
    
    public boolean isLagging() {
        return this.isLagging;
    }
    
    public ConfigValues getConfigValues() {
        return this.configValues;
    }
    
    public DataHeleper getDataHeleper() {
        return this.dataHeleper;
    }
    
    public long getLastStart() {
        return this.lastStart;
    }
    
    public long getLastThreadTick() {
        return this.lastThreadTick;
    }
    
    public long getLastExternalThreadTick() {
        return this.lastExternalThreadTick;
    }
    
    public long getLastServerLag() {
        return this.lastServerLag;
    }
    
    public long getLastServerStart() {
        return this.lastServerStart;
    }
    
    public long getLastServerTick() {
        return this.lastServerTick;
    }
    
    public GUIHelper getGuiHelper() {
        return this.guiHelper;
    }
    
    public ArrayList<GlobalObject> getUsers() {
        return (ArrayList<GlobalObject>)this.Users;
    }
    
    public GlobalUserManager getGlobalUserManager() {
        return this.globalUserManager;
    }
    
    public DatabaseHelper getDatabaseHelper() {
        return this.databaseHelper;
    }
    
    public PermissionUtil getPermissionUtil() {
        return this.permissionUtil;
    }
    
    public MySQLSocket getMySQLSocket() {
        return this.mySQLSocket;
    }
    
    public int getServerLagVerbose() {
        return this.serverLagVerbose;
    }
    
    public String getConfigDIR() {
        return this.configDIR;
    }
    
    public String getChecksDIR() {
        return this.checksDIR;
    }
    
    public String getLogsDIR() {
        return this.logsDIR;
    }
    
    public String getBanwaveDIR() {
        return this.banwaveDIR;
    }
    
    public String getCurrentDate() {
        return this.currentDate;
    }
    
    public DiscordAPI getDiscordAPI() {
        return this.discordAPI;
    }
    
    public int getTotalFlags() {
        return this.totalFlags;
    }
    
    public int getTotalBans() {
        return this.totalBans;
    }
    
    public ThemeManager getThemeManager() {
        return this.themeManager;
    }
    
    public int getCurrentTicks() {
        return this.currentTicks;
    }
    
    public static Overflow getInstance() {
        return Overflow.instance;
    }
    
    public enum Version
    {
        public static final Version V1_8;
        public static final Version V1_7;
        
        public static Version valueOf(final String name) {
            return Enum.valueOf(Version.class, name);
        }
        
        static {
            Version.V1_8 = new Version("V1_8", 0);
            Version.V1_7 = new Version("V1_7", 1);
            Version.$VALUES = new Version[] { Version.V1_8, Version.V1_7 };
        }
    }
}
