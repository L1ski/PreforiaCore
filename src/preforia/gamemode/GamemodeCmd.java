package preforia.gamemode;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preforia.api.Messages;

public class GamemodeCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		Player player = (Player) sender;
		
		if (args.length == 2) {
			Player target = Bukkit.getPlayer(args[1]);
			if (!player.hasPermission("preforia.gamemode.others")) {
				player.sendMessage(Messages.nopermission);
				return false;
			}
			if (!Bukkit.getOnlinePlayers().contains(target) || target == null) {
				player.sendMessage(Messages.offlineplayer);
				return false;
			}
			gamemode(target, args[0]);
			player.sendMessage(Messages.prefix + "Gamemode " + Messages.color1 + args[0] + "§7 has been set to player " + Messages.color1 + target.getName() + "§7.");
			return true;
		} else if (args.length == 1) {
			gamemode(player, args[0]);
			return true;
		} else {
			if (!player.hasPermission("preforia.gamemode." + args[0].toLowerCase())) {
				player.sendMessage(Messages.nopermission);
				return false;
			}
		}
		return false;
	}
	public boolean gamemode(Player player, String args0) {
		
		if (args0.equalsIgnoreCase("survival") || args0.equalsIgnoreCase("0") || args0.equalsIgnoreCase("s")) {
			if (player.hasPermission("preforia.gamemode.survival")) {
				setGamemodeSurvival(player);
				return true;
			} else {
				noPermission(player);
				return false;
			}
		} else if (args0.equalsIgnoreCase("creative") || args0.equalsIgnoreCase("1") || args0.equalsIgnoreCase("c")) {
			if (player.hasPermission("preforia.gamemode.creative")) {
				setGamemodeCreative(player);
				return true;
			} else {
				noPermission(player);
				return false;
			}
		} else if (args0.equalsIgnoreCase("adventure") || args0.equalsIgnoreCase("2") || args0.equalsIgnoreCase("a")) {
			if (player.hasPermission("preforia.gamemode.adventure")) {
				setGamemodeAdventure(player);
				return true;
			} else {
				noPermission(player);
				return false;
			}
		} else if (args0.equalsIgnoreCase("spectator") || args0.equalsIgnoreCase("3") || args0.equalsIgnoreCase("sp")) {
			if (player.hasPermission("preforia.gamemode.spectator")) {
				setGamemodeSpectator(player);
				return true;
			} else {
				noPermission(player);
				return false;
			}
		} else {
			player.sendMessage(Messages.error + "Usage - /gamemode <gamemode>");
			return false;
		}
		
	}
	
	public void setGamemodeCreative(Player player) {
		player.setGameMode(GameMode.CREATIVE);
		player.sendMessage(Messages.gamemodeset + player.getGameMode().name().toLowerCase());
	}
	public void setGamemodeSurvival(Player player) {
		player.setGameMode(GameMode.SURVIVAL);
		player.sendMessage(Messages.gamemodeset + player.getGameMode().name().toLowerCase());
	}
	public void setGamemodeAdventure(Player player) {
		player.setGameMode(GameMode.ADVENTURE);
		player.sendMessage(Messages.gamemodeset + player.getGameMode().name().toLowerCase());
	}
	public void setGamemodeSpectator(Player player) {
		player.setGameMode(GameMode.SPECTATOR);
		player.sendMessage(Messages.gamemodeset + player.getGameMode().name().toLowerCase());
	}
	public void noPermission(Player player) {
		player.sendMessage(Messages.nopermission);
	}

}
