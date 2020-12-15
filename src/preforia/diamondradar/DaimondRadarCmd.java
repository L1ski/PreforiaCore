package preforia.diamondradar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preforia.api.Main;
import preforia.api.Messages;

public class DaimondRadarCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("preforia.diamondradar")) {
			player.sendMessage(Messages.nopermission);
			return false;
		}
		
		if (Main.DIAMOND_RADAR.contains(player)) {
			Main.DIAMOND_RADAR.remove(player);
			player.sendMessage(Messages.prefix + "Diamond radar has been disabled.");
			return true;
		} else {
			Main.DIAMOND_RADAR.add(player);
			player.sendMessage(Messages.prefix + "Diamond radar has been activated.");
			return true;
		}
	}

}
