package preforia.homes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preforia.api.Main;
import preforia.api.Messages;

public class TpHomeCmd implements CommandExecutor {

private Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	
	if (!(sender instanceof Player)) {
		sender.sendMessage(Messages.nopermission);
		return false;
	}
	
	Player player = (Player) sender;
	
	if (!player.hasPermission("preforia.managehomes")) {
		player.sendMessage(Messages.nopermission);
		return false;
	}
	
	if (args.length != 2) {
		player.sendMessage(Messages.error + "Usage - /tphome <player> <home>");
		return false;
	}
		
		Player target = Bukkit.getPlayer(args[0]);
		String home = args[1];
		if (target.hasPlayedBefore()) {
			String playerhome = main.getConfig().getString("Preforia." + ".users." + player.getUniqueId() + ".Name." + player.getName() + ".homes." + "."+home);
			if (playerhome != null) {
				Location homeloc = main.getConfig().getLocation("Preforia." + ".users." + player.getUniqueId() + ".Name." + player.getName() + ".homes." + "."+home);
				player.teleport(homeloc);
				player.sendMessage(Messages.prefix + "You have been teleported to player " + Messages.color1 + target.getName() + "§7 home named " + Messages.color1 + home);
				return true;
			} else {
				player.sendMessage(Messages.error + "Player §7" + target.getName() + "§8 doesn't have §7" + home + "§8 named home.");
				return false;
			}
		} else {
			player.sendMessage(Messages.offlineplayer);
			return false;
		}
	}
}
