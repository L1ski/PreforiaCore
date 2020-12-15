package preforia.doublejump;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class JumpEvents implements Listener {
	
	private static boolean ready = false;
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		if (player.isOnGround() && ready != true) {
			ready = true;
		}
		
		if ((player.getGameMode() != GameMode.CREATIVE) 
		&& (player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR)
		&& (!player.isFlying()) && (ready == true)) {
			player.setAllowFlight(true);
		}
		
	}
	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
		if (ready == true) {
			ready = false;
			Player player = event.getPlayer();
			if (player.getGameMode() == GameMode.CREATIVE) return;
			event.setCancelled(true);
			player.setAllowFlight(false);
			player.setFlying(false);
			player.setVelocity(player.getLocation().getDirection().multiply(0.8).setY(0.7));
			player.playSound(player.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 100, 100);
		}
	}
}
