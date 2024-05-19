package foleon.acs.Cheats;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Speed implements Listener {

    private double maxSpeed = 0.25;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Вы успешно подключились к серверу!");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        double speedX = player.getVelocity().getX();
        double speedZ = player.getVelocity().getZ();
        double speedXYZ = Math.sqrt(speedX * speedX + speedZ * speedZ);

        if (speedXYZ > maxSpeed) {
            player.setVelocity(player.getVelocity().setY(0));
            player.kickPlayer("Ваша скорость слишком высока!");
        }
    }
}


