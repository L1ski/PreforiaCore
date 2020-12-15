package preforia.spawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preforia.api.Main;
import preforia.api.Messages;

public class DelSpawnCmd implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		Player player = (Player) sender;
		
		if (!sender.hasPermission("preforia.setspawn")) {
			player.sendMessage(Messages.nopermission);
			return false;
		}
		
		Main.getInstance().getConfig().set("Preforia.spawn", null);
		Main.getInstance().saveConfig();
		player.sendMessage(Messages.prefix + "Spawn has been removed!");
		
		return false;
	}
}
