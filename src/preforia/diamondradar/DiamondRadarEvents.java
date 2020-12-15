package preforia.diamondradar;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import preforia.api.Main;
import preforia.api.Messages;

public class DiamondRadarEvents implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (event.getBlock().getType().equals(Material.DIAMOND_ORE)) {
			if (Main.DIAMOND_RADAR.isEmpty()) return;
			if (Main.DIAMOND_RADAR_COOLDOWN.contains(player)) return;
			Messages.Diamondcooldown(player);
			for (Player players : Main.DIAMOND_RADAR) {
				players.sendMessage(Messages.alert + "Player §f" + player.getName() + "§7 broke a diamond ore.");
			}
		}
	}
}
