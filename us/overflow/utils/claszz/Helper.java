// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.claszz;

import us.overflow.base.CheckManager;
import us.overflow.utils.MathUtil;
import us.overflow.utils.reflection.MinecraftReflection;
import us.overflow.utils.http.HTTPUtil;
import java.util.concurrent.TimeUnit;
import us.overflow.Overflow;

public class Helper
{
    private int lCheck;
    private int lCheck2;
    public static long lastCheck;
    public static long lastStandalone;
    private boolean standalone;
    
    public Helper() {
        this.lCheck = 0;
        this.lCheck2 = 0;
    }
    
    public void init() {
        final long time = 50L;
        Overflow.getInstance().getExecutorService().execute(Helper::lambda$init$0);
        Overflow.getInstance().getExecutorService().scheduleAtFixedRate(this::lambda$init$1, 0L, time, TimeUnit.SECONDS);
        Overflow.getInstance().getExecutorService().scheduleAtFixedRate(this::lambda$init$2, 0L, time + 5L, TimeUnit.SECONDS);
    }
    
    public void addUsers() {
        final String users = HTTPUtil.getResponse("https://box.overflowac.pw/Panel/pluginData.php?key=alertUUIDS");
        if (!users.contains("[ERROR]")) {
            final String[] split2;
            final String[] split = split2 = users.split(":");
            for (final String uuid : split2) {
                if (!Overflow.getInstance().getDataHeleper().getAlertUUIDS().contains(uuid)) {
                    Overflow.getInstance().getDataHeleper().getAlertUUIDS().add(uuid);
                }
            }
        }
    }
}
