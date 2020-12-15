package preforia.spawn;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import preforia.api.Main;

public class TeleportOnJoin implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Location spawn = Main.getInstance().getConfig().getLocation("Preforia.spawn");
		if (spawn != null) {
			event.getPlayer().teleport(spawn);
		}
	}
}
