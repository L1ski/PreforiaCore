package preforia.tp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preforia.api.Messages;

public class TpCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("preforia.tp")) {
			player.sendMessage(Messages.nopermission);
			return false;
		}
		
		if (args.length == 3) {
			
			String xl = (args[0]);
			String yl = (args[1]);
			String zl = (args[2]);
			int x = Integer.parseInt(xl);
			int y = Integer.parseInt(yl);
			int z = Integer.parseInt(zl);
			
			Location location = new Location(player.getWorld(), x, y, z);
			player.teleport(location);
			player.sendMessage(Messages.prefix  + "You have been teleported to cordinates " + x + " " + y + " " + z);
			return true;
		} else if (args.length == 2) {
			Player target1 = Bukkit.getServer().getPlayer(args[1]);
			Player target2 = Bukkit.getServer().getPlayer(args[0]);
			if (Bukkit.getOnlinePlayers().contains(target1) && Bukkit.getOnlinePlayers().contains(target1) && !(target1 == null) && !(target2 == null)) {
				target2.teleport(target1);
				player.sendMessage(Messages.prefix + "Player " + Messages.color1 + target1.getName() + " §7has been teleported to player " + Messages.color2 + target2.getName());
				return true;
			} else {
				player.sendMessage(Messages.error + "Players was not found!");
				return false;
			}
		} else if (args.length == 1) {
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (Bukkit.getOnlinePlayers().contains(target) || !(target == null)) {
				player.teleport(target);
				player.sendMessage(Messages.prefix + "You have been teleported to player " + Messages.color1 + target.getName());
				return true;
			} else {
				player.sendMessage(Messages.offlineplayer);
				return false;
			}
		} else {
			player.sendMessage(Messages.error + "Usage - /tp <player | cordinates> tai <player - to player>");
			return true;
		}
	}
}
