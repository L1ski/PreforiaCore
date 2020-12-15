package preforia.format;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinAndLeave implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage(null);
		tablist(event.getPlayer());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		event.setQuitMessage(null);
	}
	
	public void tablist(Player player) {
		
		int maxplayers = Bukkit.getServer().getMaxPlayers();
		int players = Bukkit.getServer().getOnlinePlayers().size();
		
		player.setPlayerListHeader(
					"\n  "
+ "§9�?�?�?�?§f�?�?�?�?�?�?   §f§lSuomi§9§lCraft!§r   §9�?�?�?�?�?§f�?�?�?�?�?§r  " + 
					"\n §7" + players + " / " + maxplayers
					+ "\n ");
		player.setPlayerListFooter("\n"
				+ "§9�?�?�?�?§f�?�?�?�?�?§r  §7suomi.net  §9�?�?�?�?�?§f�?�?�?�?§r" + "§r\n  ");
		
	}
}
