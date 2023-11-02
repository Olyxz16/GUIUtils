package guiutils;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

    public static JavaPlugin plugin;

    @Override
    public void onEnable()
    {
        plugin = this;
    }

}