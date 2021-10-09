// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.file;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.IOException;
import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class KeyFile
{
    static KeyFile instance;
    Plugin p;
    FileConfiguration config;
    File cfile;
    FileConfiguration data;
    File dfile;
    
    private KeyFile() {
    }
    
    public static KeyFile getInstance() {
        return KeyFile.instance;
    }
    
    public void setup(final Plugin p) {
        this.config = p.getConfig();
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }
        this.dfile = new File("Overflow-License.yml");
        if (!this.dfile.exists()) {
            try {
                this.dfile.createNewFile();
            }
            catch (IOException ex) {}
        }
        this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(this.dfile);
    }
    
    public FileConfiguration getData() {
        return this.data;
    }
    
    public void writeDefaults() {
        if (!this.data.contains("LicenseKey")) {
            this.data.set("LicenseKey", (Object)"ENTER_KEY_HERE");
        }
        this.saveData();
    }
    
    public void saveData() {
        try {
            this.data.save(this.dfile);
        }
        catch (IOException ex) {}
    }
    
    public void reloadData() {
        this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(this.dfile);
    }
    
    public FileConfiguration getConfig() {
        return this.config;
    }
    
    public void saveConfig() {
        try {
            this.config.save(this.cfile);
        }
        catch (IOException ex) {}
    }
    
    public void reloadConfig() {
        this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.cfile);
    }
    
    public PluginDescriptionFile getDesc() {
        return this.p.getDescription();
    }
    
    static {
        KeyFile.instance = new KeyFile();
    }
}
