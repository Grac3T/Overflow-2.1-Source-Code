// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.command;

import java.util.Iterator;
import java.util.HashMap;
import org.bukkit.command.SimpleCommandMap;
import java.lang.reflect.Field;
import org.bukkit.command.Command;
import us.overflow.commands.OverflowCommand;
import org.bukkit.command.CommandMap;
import org.bukkit.Bukkit;

public class CommandUtils
{
    public static void registerCommand(final String command) {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            final CommandMap commandMap = (CommandMap)bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register(command, (Command)new OverflowCommand(command));
            bukkitCommandMap.setAccessible(false);
        }
        catch (Exception ex) {}
    }
    
    public static void unRegisterBukkitCommand(final String commandName) {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            final CommandMap commandMap1 = (CommandMap)bukkitCommandMap.get(Bukkit.getServer());
            final Command command = commandMap1.getCommand(commandName);
            if (command != null) {
                try {
                    final Object result = getPrivateField(Bukkit.getServer().getPluginManager(), "commandMap");
                    final SimpleCommandMap commandMap2 = (SimpleCommandMap)result;
                    final Object map = getPrivateField(commandMap2, "knownCommands");
                    final HashMap<String, Command> knownCommands = (HashMap<String, Command>)map;
                    knownCommands.remove(command.getName());
                    for (final String alias : command.getAliases()) {
                        knownCommands.remove(alias);
                    }
                }
                catch (Exception ex) {}
            }
        }
        catch (Exception ex2) {}
    }
    
    private static Object getPrivateField(final Object object, final String field) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        final Class<?> clazz = object.getClass();
        final Field objectField = clazz.getDeclaredField(field);
        objectField.setAccessible(true);
        final Object result = objectField.get(object);
        objectField.setAccessible(false);
        return result;
    }
}
