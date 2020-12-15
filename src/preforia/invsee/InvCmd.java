package preforia.invsee;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preforia.api.Messages;

public class InvCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("preforia.invsee")) {
			player.sendMessage(Messages.nopermission);
			return false;
		}
		
		if (args.length != 1) {
			player.sendMessage(Messages.error + "Usage - /inv <player>");
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[0]);
		if (target.hasPlayedBefore()) {
			if (player == target) {
				player.sendMessage(Messages.error + "You cant open your own inventory.");
				return false;
			}
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 60, 60);
			player.sendMessage(Messages.prefix + "You opened player " + Messages.color1 + player.getName() + "§r inventory.");
			player.openInventory(target.getInventory());
			return true;
		} else {
			player.sendMessage(Messages.offlineplayer);
			return false;
		}
	}
}
