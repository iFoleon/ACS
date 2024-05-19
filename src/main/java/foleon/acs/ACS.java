package foleon.acs;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import foleon.acs.Cheats.Killaura;
import foleon.acs.Data.Warns;
import foleon.acs.Data.Config;
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
    }

    @Override
    public void onDisable() {
    }
}
