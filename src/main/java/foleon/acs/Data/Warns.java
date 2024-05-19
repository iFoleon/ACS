package foleon.acs.Data;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Warns extends JavaPlugin {

    private Map<UUID, Integer> warnsMap = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (UUID uuid : warnsMap.keySet()) {
                warnsMap.put(uuid, warnsMap.get(uuid) - 1);
                if (warnsMap.get(uuid) <= 0) {
                    warnsMap.remove(uuid);
                }
            }
        }, 0, 20 * 30);
    }

    public void addWarn(Player player) {
        UUID uuid = player.getUniqueId();
        int warnsCount = warnsMap.getOrDefault(uuid, 0) + 1;
        warnsMap.put(uuid, warnsCount);
        sendWarnNotification(player);
    }

    private void sendWarnNotification(Player player) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.hasPermission("ACS.Owner")) {
                onlinePlayer.sendMessage(ChatColor.RED + player.getName() + " получил варн!");
            }
        }
    }
}

