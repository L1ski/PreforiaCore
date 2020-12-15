package preforia.vanish;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import preforia.api.Main;

public class VanishEvents implements Listener {
	
	 Main plugin;

	 public VanishEvents(Main plugin) {
		 this.plugin = plugin;
	 }
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPermission("preforia.vanish")) {
			for (Player vanishedplayer : Main.vanished) {
				player.hidePlayer(plugin, vanishedplayer);
			}
		}
	}
}
