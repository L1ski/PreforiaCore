package preforia.give;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import preforia.api.Messages;

public class GiveCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("preforia.give")) {
			player.sendMessage(Messages.nopermission);
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[0]);
		if (!Bukkit.getOnlinePlayers().contains(target)) {
			player.sendMessage(Messages.offlineplayer);
			return false;
		}
		int amount = Integer.parseInt(args[1]);
		ItemStack togive = new ItemStack(Material.valueOf(args[2]));
		target.getInventory().setItem(amount, togive);
		target.sendMessage(Messages.prefix + "Sinulle on annettu §a" + amount + "§f §a" + togive);
		
		return false;
	}
}
