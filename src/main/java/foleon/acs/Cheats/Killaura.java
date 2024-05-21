package foleon.acs.Cheats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import foleon.acs.Data.Warns;
import foleon.acs.Data.Config;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Killaura implements Listener {
    private static int distances = 5;
    private static Map<UUID, Integer> warnCountMap = new HashMap<>();

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            if (player.getTargetBlock((Set<org.bukkit.Material>) null, 0) != null && event.getEntity() instanceof Entity) {
                double distance = player.getLocation().distance(event.getEntity().getLocation());
                if (distance > distances) {
                    int warnCount = warnCountMap.getOrDefault(player.getUniqueId(), 0);
                    warnCount++;
                    event.setCancelled(true);
                    if (warnCount >= 3) {
                        event.setCancelled(true);
                        player.kickPlayer(ChatColor.RED + "[ACS] " + ChatColor.WHITE + "вы были исключены из-за подозрения в читерстве (Killaura)");
                        warnCountMap.remove(player.getUniqueId());
                        Bukkit.broadcastMessage(ChatColor.RED + "[ACS]" + ChatColor.YELLOW + "Игрок " + player.getName() + " был исключен с сервера из-за подозрения в использовании Killaura.");
                    } else {
                        int warnMessage = 3 - warnCount;
                        warnCountMap.put(player.getUniqueId(), warnCount);
                        Set<Player> owners = (Set<Player>) Bukkit.getOnlinePlayers();
                        for (Player owner : owners) {
                            if (owner.hasPermission("acs.owner")) {
                                owner.sendMessage(ChatColor.YELLOW + "Игрок " + player.getName() + " получил предупреждение за использование Killaura.");
                            }
                        }
                    }
                }
            }
        }
    }

    public void resetWarnCount() {
        warnCountMap.clear();
    }
}










