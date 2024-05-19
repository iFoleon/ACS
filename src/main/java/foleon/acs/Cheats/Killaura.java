package foleon.acs.Cheats;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import foleon.acs.Data.Warns;

import java.util.Set;
import java.util.UUID;

public class Killaura implements Listener {
    private static int warnCount = 0;
    int distances = 5;

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            if (player.getTargetBlock((Set<org.bukkit.Material>) null, 0) != null && event.getEntity() instanceof Entity) {
                double distance = player.getLocation().distance(event.getEntity().getLocation());
                if (distance > distances) {
                    warnCount++;
                    event.setCancelled(true);
                    if (warnCount >= 3) {
                        event.setCancelled(true);
                        player.kickPlayer("Вы нарушили правила сервера (Killaura)");
                        warnCount = 0;
                    } else {
                        int warnMessage = 3 - warnCount;
                        player.sendMessage(ChatColor.RED + "Внимание! Вы были предупреждены за использование Killaura. Если вы получите еще " + warnMessage + " предупреждения, вы будете исключены с сервера.");
                    }
                }
            }
        }
    }
    public void resetWarnCount() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            warnCount = 0;
        }
    }
}








