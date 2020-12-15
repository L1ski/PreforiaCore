package preforia.tpa;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preforia.api.Main;
import preforia.api.Messages;

public class TpaAcceptCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		if (args.length != 0) {
			sender.sendMessage(Messages.error + "Usage - /tpaaccept");
			return false;
		}
		
		if (!Main.tpa.containsKey(sender)) {
			sender.sendMessage(Messages.error + "You dont have any waiting TPA requests.");
			return false;
		}
		
		Player receiver = Main.tpa.get(sender);//Tpa reuqest sender
		Player player = (Player) sender;//Tpa request target
		
		player.sendMessage(Messages.prefix + "TPA request from " + Messages.color1 + receiver.getName() + "§7 has been accepted.");
		receiver.sendMessage(Messages.prefix + "You will be teleported to " + Messages.color1 + player.getName() + "§7 after 3 seconds.");
		
		Main.tpa.remove(player);
		TpaCooldown(receiver, player, 3);
		
		return false;
	}
	
	public void TpaCooldown(Player target, Player player, int cooldown) {

			Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
				@Override
				public void run() {
					target.teleport(player);
					player.sendMessage(Messages.prefix + "Player " + Messages.color1 + target.getName() + "§7 has been teleported to your location.");
					target.sendMessage(Messages.prefix + "You have been teleported to " + Messages.color1 + player.getName() + ".");
					
				}
			}, 20*cooldown);
	}
		
}
