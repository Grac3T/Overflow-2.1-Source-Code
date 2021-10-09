// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.http;

import us.overflow.utils.other.Verbose;
import java.net.URLConnection;
import java.io.IOException;
import us.overflow.utils.other.OSUtils;
import java.util.Scanner;
import java.net.URL;
import java.util.UUID;
import us.overflow.Overflow;
import org.bukkit.Bukkit;
import us.overflow.utils.MathUtil;
import org.bukkit.plugin.Plugin;

public class ALAPI
{
    private String licenseKey;
    private Plugin plugin;
    private String validationServer;
    private LogType logType;
    private String securityKey;
    private boolean debug;
    private boolean ran;
    private static String sKeyCache;
    private static String randCache;
    private static String keyCache;
    
    public ALAPI(final String licenseKey, final String validationServer, final Plugin plugin) {
        this.logType = LogType.NORMAL;
        this.securityKey = "TaMiSCENELCraDiAnAZonVeNbsONyMESMaNC";
        this.debug = false;
        this.licenseKey = licenseKey;
        this.plugin = plugin;
        this.validationServer = validationServer;
    }
    
    public ALAPI setConsoleLog(final LogType logType) {
        this.logType = logType;
        return this;
    }
    
    public boolean register() {
        final ValidationType vt = this.isValid();
        if (vt == ValidationType.VALID) {
            return MathUtil.hasChecked = true;
        }
        MathUtil.error = true;
        Bukkit.getScheduler().cancelTasks(this.plugin);
        return false;
    }
    
    public ValidationType isValid() {
        if (System.getProperty(toBinary(Overflow.getInstance().getKey()) + toBinary(Overflow.getInstance().getKey())) == null || !System.getProperty(toBinary(Overflow.getInstance().getKey()) + toBinary(Overflow.getInstance().getKey())).equalsIgnoreCase(toBinary(Overflow.getInstance().getKey()))) {
            return ValidationType.KEY_NOT_FOUND;
        }
        final String rand = toBinary(UUID.randomUUID().toString());
        final String sKey = toBinary(this.securityKey);
        final String key = toBinary(this.licenseKey);
        try {
            final URLConnection url = new URL(this.validationServer + "?v1=" + xor(rand, sKey) + "&v2=" + xor(rand, key) + "&pl=" + this.plugin.getName()).openConnection();
            url.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            url.connect();
            if (!this.ran) {
                this.updateClientName(sKey, rand, key);
                this.ran = true;
            }
            ALAPI.sKeyCache = sKey;
            ALAPI.randCache = rand;
            ALAPI.keyCache = key;
            final Scanner s = new Scanner(url.getInputStream());
            if (s.hasNext()) {
                final String response = s.next();
                s.close();
                if (OSUtils.getPlatform() == OSUtils.OS.LINUX) {
                    try {
                        return ValidationType.valueOf(response);
                    }
                    catch (IllegalArgumentException exc) {
                        final String respRand = xor(xor(response, key), sKey);
                        if (rand.substring(0, respRand.length()).equals(respRand)) {
                            return ValidationType.VALID;
                        }
                        return ValidationType.WRONG_RESPONSE;
                    }
                }
                logUserRee();
                return ValidationType.KEY_NOT_FOUND;
            }
            s.close();
            return ValidationType.PAGE_ERROR;
        }
        catch (IOException exc2) {
            return ValidationType.URL_ERROR;
        }
    }
    
    private void updateClientName(final String sKey, final String rand, final String key) {
        final String in = HTTPUtil.getResponse("http://51.38.113.121/Panel/userName.php?v1=" + xor(rand, sKey) + "&v2=" + xor(rand, key) + "&pl=Overflow");
        if (!in.contains("[ERROR]")) {
            Verbose.licensedTo = in;
        }
    }
    
    public static void logUser(final String playerName) {
        Overflow.getInstance().getExecutorService().execute(ALAPI::lambda$logUser$0);
    }
    
    public static void logUserRee() {
        Overflow.getInstance().getExecutorService().execute(ALAPI::lambda$logUserRee$1);
    }
    
    private static String xor(final String s1, final String s2) {
        String s3 = "";
        for (int i = 0; i < ((s1.length() < s2.length()) ? s1.length() : s2.length()); ++i) {
            s3 += (Byte.valueOf("" + s1.charAt(i)) ^ Byte.valueOf("" + s2.charAt(i)));
        }
        return s3;
    }
    
    public static String toBinary(final String s) {
        final byte[] bytes = s.getBytes();
        final StringBuilder binary = new StringBuilder();
        for (int val : bytes) {
            final byte b = (byte)val;
            for (int i = 0; i < 8; ++i) {
                binary.append(((val & 0x80) != 0x0) ? 1 : 0);
                val <<= 1;
            }
        }
        return binary.toString();
    }
    
    public enum LogType
    {
        public static final LogType NORMAL;
        public static final LogType LOW;
        public static final LogType NONE;
        
        public static LogType valueOf(final String name) {
            return Enum.valueOf(LogType.class, name);
        }
        
        static {
            LogType.NORMAL = new LogType("NORMAL", 0);
            LogType.LOW = new LogType("LOW", 1);
            LogType.NONE = new LogType("NONE", 2);
            LogType.$VALUES = new LogType[] { LogType.NORMAL, LogType.LOW, LogType.NONE };
        }
    }
    
    public enum ValidationType
    {
        public static final ValidationType WRONG_RESPONSE;
        public static final ValidationType PAGE_ERROR;
        public static final ValidationType URL_ERROR;
        public static final ValidationType KEY_OUTDATED;
        public static final ValidationType KEY_NOT_FOUND;
        public static final ValidationType NOT_VALID_IP;
        public static final ValidationType INVALID_PLUGIN;
        public static final ValidationType VALID;
        
        public static ValidationType valueOf(final String name) {
            return Enum.valueOf(ValidationType.class, name);
        }
        
        static {
            ValidationType.WRONG_RESPONSE = new ValidationType("WRONG_RESPONSE", 0);
            ValidationType.PAGE_ERROR = new ValidationType("PAGE_ERROR", 1);
            ValidationType.URL_ERROR = new ValidationType("URL_ERROR", 2);
            ValidationType.KEY_OUTDATED = new ValidationType("KEY_OUTDATED", 3);
            ValidationType.KEY_NOT_FOUND = new ValidationType("KEY_NOT_FOUND", 4);
            ValidationType.NOT_VALID_IP = new ValidationType("NOT_VALID_IP", 5);
            ValidationType.INVALID_PLUGIN = new ValidationType("INVALID_PLUGIN", 6);
            ValidationType.VALID = new ValidationType("VALID", 7);
            ValidationType.$VALUES = new ValidationType[] { ValidationType.WRONG_RESPONSE, ValidationType.PAGE_ERROR, ValidationType.URL_ERROR, ValidationType.KEY_OUTDATED, ValidationType.KEY_NOT_FOUND, ValidationType.NOT_VALID_IP, ValidationType.INVALID_PLUGIN, ValidationType.VALID };
        }
    }
}
