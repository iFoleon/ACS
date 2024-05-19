package foleon.acs;

import org.bukkit.plugin.java.JavaPlugin;
import foleon.acs.Cheats.Killaura;
import foleon.acs.Data.Warns;
import org.bukkit.Bukkit;

public final class ACS extends JavaPlugin {
    private static ACS instance;
    private Killaura killaura;


    @Override
    public void onEnable() {
        instance = this;
        killaura = new Killaura();
        getServer().getPluginManager().registerEvents(killaura, this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                killaura.resetWarnCount();
            }
        }, 0L, 600L);
    }

        @Override
        public void onDisable () {
        }
    }
