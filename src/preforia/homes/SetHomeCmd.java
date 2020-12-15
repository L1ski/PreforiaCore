package preforia.homes;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preforia.api.Main;
import preforia.api.Messages;

public class SetHomeCmd implements CommandExecutor {

	private Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.nopermission);
			return false;
		}
		
		if (args.length != 1) {
			sender.sendMessage(Messages.error + "Usage - /sethome <homename>");
			return false;
		}
		
		Player player = (Player) sender;
		
		if (main.getConfig().get("Preforia." + ".users." + player.getUniqueId() + ".Name." + player.getName() + ".homes") != null) {
			Set<String> homes = main.getConfig().getConfigurationSection("Preforia." + ".users." + player.getUniqueId() + ".Name." + player.getName() + ".homes").getKeys(false);
			if (homes.size() == 5) {
				player.sendMessage(Messages.error + "You can have maxium 5 homes.");
				return false;
			}
		}
		Location newhomeloc = player.getLocation();
		String homename = args[0];
		
		Location home = main.getConfig().getLocation("Preforia." + ".users." + player.getUniqueId() + ".Name." + player.getName() + ".homes." + "."+homename);
		
		if (home != null) {
			player.sendMessage(Messages.error + "You already have home named §7" + homename + "§8!");
			return false;
		} else {
			main.getConfig().set("Preforia." + ".users." + player.getUniqueId() + ".Name." + player.getName() + ".homes." + "."+homename, newhomeloc);
			main.saveConfig();
			player.sendMessage(Messages.prefix + "Home has been set!");
			return true;
		}
	}

}
