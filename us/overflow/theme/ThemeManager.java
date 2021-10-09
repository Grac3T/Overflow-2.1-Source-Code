// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.theme;

import us.overflow.utils.config.ConfigManager;
import org.bukkit.plugin.Plugin;
import us.overflow.Overflow;
import us.overflow.utils.file.ConfigFile;

public class ThemeManager
{
    public void load(final String theme) {
        this.resolveTheme(this.getThemeFromString(theme));
    }
    
    private void resolveTheme(final Themes theme) {
        ConfigFile.getInstance().setup((Plugin)Overflow.getInstance());
        switch (ThemeManager$1.$SwitchMap$us$overflow$theme$Themes[theme.ordinal()]) {
            case 2: {
                ConfigFile.getInstance().getData().set("Customizer.Prefix", (Object)"&7[&cOverflow&7]&r");
                ConfigFile.getInstance().getData().set("Customizer.Alert", (Object)"&c%PREFIX% &7%PLAYER% flagged &c%CHECK% &7(&ctype %TYPE%&7) VL: &c%VL%");
                break;
            }
            case 3: {
                ConfigFile.getInstance().getData().set("Customizer.Prefix", (Object)"&c&l[&4&lOverFlow&c&l] &8»");
                ConfigFile.getInstance().getData().set("Customizer.Alert", (Object)"%PREFIX% &c%PLAYER% &7has failed &c%CHECK%&8(&c%TYPE%&8) &8(&c%VL%&8)");
                break;
            }
            case 4: {
                ConfigFile.getInstance().getData().set("Customizer.Prefix", (Object)"§8§l[§6§lOverFlow§8§l]");
                ConfigFile.getInstance().getData().set("Customizer.Alert", (Object)"%PREFIX% §r%PLAYER%§7 flagged §r%CHECK%(%TYPE%) §8(§e%VL%§8)");
                break;
            }
            case 5: {
                ConfigFile.getInstance().getData().set("Customizer.Prefix", (Object)"§9§lOverFlow §8§l>");
                ConfigFile.getInstance().getData().set("Customizer.Alert", (Object)"%PREFIX% §r%PLAYER%§7 failed §r%CHECK% §r%TYPE% §7VL[§9%VL%§7]");
                break;
            }
            case 6: {
                ConfigFile.getInstance().getData().set("Customizer.Prefix", (Object)"§8§l[§b§lOverFlow§8§l]");
                ConfigFile.getInstance().getData().set("Customizer.Alert", (Object)"%PREFIX% §r%PLAYER% §7has failed §r%CHECK% §r(Type %TYPE%) §c(x%VL%)");
                break;
            }
            case 7: {
                ConfigFile.getInstance().getData().set("Customizer.Prefix", (Object)"§7[§c§l!§7]");
                ConfigFile.getInstance().getData().set("Customizer.Alert", (Object)"%PREFIX% §7§l[§4%CHECK%) (%TYPE%)§7] §7§lfailed by §c§l%PLAYER% §d§l%PING%ms");
                break;
            }
        }
        if (theme != Themes.OVERFLOW_2_0) {
            ConfigFile.getInstance().saveData();
            new ConfigManager().doAction(ConfigManager.Action.LOAD);
        }
    }
    
    private Themes getThemeFromString(final String s) {
        for (final Themes t : Themes.values()) {
            if (s.toUpperCase().equalsIgnoreCase(t.name())) {
                return t;
            }
        }
        return Themes.OVERFLOW_2_0;
    }
}
