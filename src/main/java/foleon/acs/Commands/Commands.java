package foleon.acs.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import foleon.acs.ACS;

public class Commands implements CommandExecutor {

    private ACS plugin;

    public Commands(ACS plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Эта команда доступна только для игроков.");
            return true;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("acs")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.GOLD + "Использование: /acs reload или /acs info");
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                if (!player.hasPermission("acs.reload")) {
                    player.sendMessage(ChatColor.RED + "У вас недостаточно прав для выполнения этой команды.");
                    return true;
                }
                plugin.reloadConfig();
                player.sendMessage(ChatColor.GREEN + "Конфигурация плагина успешно перезагружена.");
                return true;
            } else if (args[0].equalsIgnoreCase("info")) {
                player.sendMessage(ChatColor.GOLD + "Информация о плагине ACS:");
                player.sendMessage(ChatColor.YELLOW + "- Версия плагина: " + plugin.getDescription().getVersion());
                player.sendMessage(ChatColor.YELLOW + "- Автор: " + plugin.getDescription().getAuthors().toString());
                player.sendMessage(ChatColor.YELLOW + "- Описание: " + plugin.getDescription().getDescription());
                return true;
            }
        }

        return false;
    }
}


