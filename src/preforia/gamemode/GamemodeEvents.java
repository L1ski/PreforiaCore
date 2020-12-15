package preforia.gamemode;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class GamemodeEvents implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (event.getPlayer().hasPermission("preforia.gamemode." + event.getPlayer().getGameMode().name().toLowerCase())) {
			event.getPlayer().setGameMode(Bukkit.getServer().getDefaultGameMode());
		}
	}
}
