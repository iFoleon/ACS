package foleon.acs;

import foleon.acs.Commands.Commands;
import org.bukkit.plugin.java.JavaPlugin;
import foleon.acs.Cheats.Killaura;
import foleon.acs.Data.Config;
import foleon.acs.Events.EventListener;
import org.bukkit.Bukkit;

public final class ACS extends JavaPlugin {
    private static ACS instance;
    private Killaura killaura;
    private Config config;

    @Override
    public void onEnable() {
        instance = this;
        killaura = new Killaura();
        getServer().getPluginManager().registerEvents(killaura, this);
        config = new Config(this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                killaura.resetWarnCount();
                }
        }, 0L, 600L);
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getCommand("acs").setExecutor(new Commands(this));
    }

    @Override
    public void onDisable() {
    }
}