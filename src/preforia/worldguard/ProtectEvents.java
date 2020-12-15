package preforia.worldguard;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import preforia.api.Main;

public class ProtectEvents implements Listener {

	private Main main = Main.getInstance();
	
	@EventHandler (ignoreCancelled = true)
	public void onBreak(BlockBreakEvent event) {
		if (main.getConfig().getBoolean("ProtectedWorlds." + event.getPlayer().getWorld().getName()) && !event.getPlayer().hasPermission("preforia.worldguard")) {
			event.setCancelled(true);
		}
	}
	@EventHandler (ignoreCancelled = true)
	public void onBuild(BlockPlaceEvent event) {
		if (main.getConfig().getBoolean("ProtectedWorlds." + event.getPlayer().getWorld().getName()) && !event.getPlayer().hasPermission("preforia.worldguard")) {
			event.setCancelled(true);
		}
	}
	@EventHandler (ignoreCancelled = true)
	public void onEntityDamage(EntityDamageEvent event) {
		if (main.getConfig().getBoolean("ProtectedWorlds." + event.getEntity().getWorld().getName())) {
			event.setCancelled(true);
		}
	}
	@EventHandler (ignoreCancelled = true)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player && main.getConfig().getBoolean("ProtectedWorlds." + event.getDamager().getWorld().getName())) {
			event.setCancelled(true);
		}
	}
	@EventHandler (ignoreCancelled = true)
	public void onInteract(PlayerInteractEvent event) {
		if (main.getConfig().getBoolean("ProtectedWorlds." + event.getPlayer().getWorld().getName()) && !event.getPlayer().hasPermission("preforia.worldguard")) {
			event.setCancelled(true);
		}
	}
	@EventHandler (ignoreCancelled = true)
	public void onBlockFade(BlockFadeEvent event) {
		if (main.getConfig().getBoolean("ProtectedWorlds." + event.getBlock().getWorld().getName())) {
			event.setCancelled(true);
		}
	}
	@EventHandler (ignoreCancelled = true)
	public void onHunger(FoodLevelChangeEvent event) {
		if (main.getConfig().getBoolean("ProtectedWorlds." + event.getEntity().getWorld().getName())) {
			event.setCancelled(true);
			event.setFoodLevel(20);
		}
	}
}
