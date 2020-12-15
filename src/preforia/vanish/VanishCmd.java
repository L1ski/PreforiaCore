package preforia.vanish;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preforia.api.Main;
import preforia.api.Messages;

public class VanishCmd implements CommandExecutor {

	 Main plugin;

	 public VanishCmd(Main plugin) {
		 this.plugin = plugin;
	 }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("preforia.vanish")) {
			player.sendMessage(Messages.nopermission);
			return false;
		}
		
		if (!Main.vanished.contains(player)) {
			//Laita vanish
			Main.vanished.add(player);
			player.sendMessage(Messages.prefix + "You are now invisible!");
			for (Player players : Bukkit.getOnlinePlayers()) {
				if (!players.hasPermission("suomi.vanish")) {
					players.hidePlayer(plugin, player);
				}
			}
		} else {
			//Poista vanish
			Main.vanished.remove(player);
			player.sendMessage(Messages.prefix + "You are now visible.");
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.showPlayer(plugin, player);
			}
		}
		
		return true;
	}
}
