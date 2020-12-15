package preforia.tpa;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import preforia.api.Main;

public class OnlineListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		if (Main.tpa.containsKey(event.getPlayer())) {
			Main.tpa.remove(event.getPlayer());
		}
	}
}
