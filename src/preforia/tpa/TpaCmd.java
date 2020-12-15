package preforia.tpa;

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

public class TpaCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		if (args.length != 1) {
			sender.sendMessage(Messages.error + "Usage - /tpa <player>");
			return false;
		}
		
		Player player = (Player) sender;
		Player target = Bukkit.getPlayer(args[0]);
		
		if (!Bukkit.getOnlinePlayers().contains(target) || target == null) {
			player.sendMessage(Messages.offlineplayer);
			return false;
		}
		
		Main.tpa.put(target, player);
		
		target.sendMessage(Messages.prefix + "Player " + Messages.color1 + player.getName() + "§7 sent you TPA request.");
		TextComponent messagee = new TextComponent( "§c§l/tpadeny§r " );
		TextComponent message = new TextComponent("§a§l/tpaaccept§r " );
		message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "§a§l/tpaccept" ).create() ) );
		message.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/tpaaccept" ) );
		messagee.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "§c§l/tpadeny" ).create() ) );
		messagee.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/tpadeny" ) );
		messagee.addExtra(message);
		target.spigot().sendMessage(messagee);
		
		player.sendMessage(Messages.prefix + "You sent TPA request to " + Messages.color1 + target.getName() + "§7.");
		
		removeTpaAfter(45, target);
		
		return false;
	}
	
	public void removeTpaAfter(int time, Player target) {
		

		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				if (Main.tpa.containsKey(target)) {
					Main.tpa.remove(target);
				}
			}
		}, 20*time);
		
	}

	
}
