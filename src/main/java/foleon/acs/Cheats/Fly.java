package foleon.acs.Cheats;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.entity.*;
import org.bukkit.ChatColor;

public class Fly {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getAllowFlight() && !player.isOp()) {
            player.kickPlayer(ChatColor.RED + "Ты не имеешь права летать! Опку можно купить на сайте brawlstars.net!");
        }
    }
}

