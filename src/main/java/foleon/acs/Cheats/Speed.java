package foleon.acs.Cheats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.ChatColor;

public class Speed implements Listener {

    private double maxSpeed = 0.3;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        double speedX = player.getVelocity().getX();
        double speedZ = player.getVelocity().getZ();
        double speedXYZ = Math.sqrt(speedX * speedX + speedZ * speedZ);

        if (speedXYZ > maxSpeed) {
            player.setVelocity(player.getVelocity().setY(0));
            player.kickPlayer(ChatColor.RED + "[ACS]" + ChatColor.WHITE + "вы были исключены из-за подозрения в читерстве (Speed)");
            Bukkit.broadcastMessage(ChatColor.RED + "[ACS]" + ChatColor.YELLOW + "Игрок " + player.getName() + " был исключен с сервера из-за подозрения в использовании Speed.");
        }
    }
}


