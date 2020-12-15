package preforia.homes;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import preforia.api.Main;
import preforia.api.Messages;

public class HomesCmd implements CommandExecutor {

	private Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("preforia.managehomes")) {
			player.sendMessage(Messages.nopermission);
			return false;
		}
		
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			player.sendMessage(Messages.offlineplayer);
			return false;
		}
		
		if (target.hasPlayedBefore()) {
			if (main.getConfig().get("Preforia." + ".users." + player.getUniqueId() + ".Name." + player.getName() + ".homes") == null) {
				player.sendMessage(Messages.nohomes);
				return false;
			}
			Set<String> homesraw = main.getConfig().getConfigurationSection("Preforia." + ".users." + player.getUniqueId() + ".Name." + player.getName() + ".homes").getKeys(false);
			if (homesraw.isEmpty()) {
				player.sendMessage(Messages.prefix + "Player " + Messages.color1 + target.getName() + "§7 doesnt have any homes.");
				return false;
			}
			player.sendMessage(Messages.prefix + "Heres player " + Messages.color1 + target.getName() + "§7 homes.");
			int count = 1;
			for (String homes : homesraw) {
				TextComponent homemessage = new TextComponent("§8[§7" + count + "§8] §f" + homes);
				homemessage.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "§f/tphome " + target.getName() + " " + homes).create() ) );
				homemessage.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/tphome " + target.getName() + " " + homes ) );
				target.spigot().sendMessage(homemessage);
				count++;
			}
			return true;
		} else {
			player.sendMessage(Messages.offlineplayer);
			return false;
		}
	}
}
