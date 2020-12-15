package preforia.homes;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preforia.api.Main;
import preforia.api.Messages;

public class HomeCmd implements CommandExecutor {

	private Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		if (args.length != 1) {
			sender.sendMessage(Messages.error + "Usage - /home <homename>");
			return false;
		}
		
		Player player = (Player) sender;
		
		String homename = args[0];
		
		Location home = main.getConfig().getLocation("Preforia." + ".users." + player.getUniqueId() + ".Name." + player.getName() + ".homes." + "."+homename);
		
		if (home != null) {
			Location homeloc = main.getConfig().getLocation("Preforia." + ".users." + player.getUniqueId() + ".Name." + player.getName() + ".homes." + "."+homename);
			player.teleport(homeloc);
			player.sendMessage(Messages.prefix + "You have been teleported to home " + Messages.color1 + homename);
			return true;
		} else {
			player.sendMessage(Messages.error + "Home not found.");
			return false;
		}
	}

}
