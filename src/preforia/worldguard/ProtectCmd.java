package preforia.worldguard;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preforia.api.Main;
import preforia.api.Messages;

public class ProtectCmd implements CommandExecutor {

	private Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player player = (Player) sender;
			if (!player.hasPermission("preforia.admin")) {
				player.sendMessage(Messages.nopermission);
				return false;
			} else {
				sender.sendMessage(Messages.error + "Vain console voi suorittaa t‰m‰n komennon");
			}
			return false;
			
		} else {
			
			if (main.getConfig().get("ProtectedWorlds.") == null) {
				for (World world : Bukkit.getServer().getWorlds()) {
					main.getConfig().set("ProtectedWorlds." +world.getName(), false);
					Bukkit.broadcastMessage("homo");
				}
				main.saveConfig();
			}
			
			String world = Bukkit.getServer().getWorld(args[0]).getName();
			World rawworld = Bukkit.getServer().getWorld(args[0]);
			
			if (!Bukkit.getServer().getWorlds().contains(rawworld)) {
				sender.sendMessage(Messages.error + "World named ß7" + world + "ß8 was not found.");
				return false;
			}
			
			if (main.getConfig().getBoolean("ProtectedWorlds." + "."+world) == true) {
				main.getConfig().set("ProtectedWorlds." + "."+world, false);
				sender.sendMessage(Messages.prefix + "Mailma ßa" + world + "ß7 ei ole en‰‰n suojattu.");
				main.saveConfig();
				return true;
			} else {
				main.getConfig().set("ProtectedWorlds." + "."+world, true);
				sender.sendMessage(Messages.prefix + "Mailma ßa" + world + "ß7 on nyt suojattu.");
				main.saveConfig();
				return true;
			}
			
		}
	}

}
