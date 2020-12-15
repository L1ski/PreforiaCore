package preforia.tpa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preforia.api.Main;
import preforia.api.Messages;

public class TpaDenyCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		if (args.length != 0) {
			sender.sendMessage(Messages.error + "Usage - /tpadeny");
			return false;
		}
		
		if (!Main.tpa.containsKey(sender)) {
			sender.sendMessage(Messages.error + "You dont have any waiting TPA requests.");
			return false;
		}
		
		Player receiver = Main.tpa.get(sender);//Tpa reuqest sender
		Player player = (Player) sender;//Tpa request target
		
		player.sendMessage(Messages.prefix + "TPA request from " + Messages.color1 + receiver.getName() + "§7 has been denied.");
		receiver.sendMessage(Messages.prefix + "Your TPA request to " + Messages.color1 + player.getName() + "§7 have been denied.");
		Main.tpa.remove(player);
		return true;
	}

}
