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

public class DirFile
{
    static DirFile instance;
    Plugin p;
    FileConfiguration config;
    File cfile;
    FileConfiguration data;
    File dfile;
    
    private DirFile() {
    }
    
    public static DirFile getInstance() {
        return DirFile.instance;
    }
    
    public void setup(final Plugin p) {
        this.config = p.getConfig();
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }
        this.dfile = new File("OverflowFileSystem.yml");
        if (!this.dfile.exists()) {
            try {
                this.dfile.createNewFile();
            }
            catch (IOException ex) {}
        }
        this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(this.dfile);
        if (!this.data.contains("config")) {
            this.data.set("config", (Object)"plugins/Overflow/config.yml");
        }
        if (!this.data.contains("checks")) {
            this.data.set("checks", (Object)"plugins/Overflow/checks.yml");
        }
        this.saveData();
    }
    
    public FileConfiguration getData() {
        return this.data;
    }
    
    public void writeDefaults() {
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
        DirFile.instance = new DirFile();
    }
}
