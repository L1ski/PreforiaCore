package preforia.homes;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preforia.api.Main;
import preforia.api.Messages;

public class DelHomeCmd implements CommandExecutor {

	private Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		if (args.length != 1) {
			sender.sendMessage(Messages.error + "Usage - /delhome <homename>");
			return false;
		}
		
		Player player = (Player) sender;
		
		String homename = args[0];
		
		Location home = main.getConfig().getLocation("Preforia." + ".users." + player.getUniqueId() + ".Name." + player.getName() + ".homes." + "."+homename);
		
		if (home == null) {
			player.sendMessage(Messages.error + "You dont have home named §f" + homename + "§8.");
			return false;
		} else {
			main.getConfig().set("Preforia." + ".users." + player.getUniqueId() + ".Name." + player.getName() + ".homes." + "."+homename, null);
			main.saveConfig();
			player.sendMessage(Messages.prefix + "Home removed!");
			return true;
		}
	}

}
