package preforia.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import preforia.api.Main;
import preforia.api.Messages;

public class VoidFall implements Listener {

	private Main main = Main.getInstance();
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if (event.getCause() == DamageCause.VOID && event.getEntity() instanceof Player) {
			if (main.getConfig().contains("ProtectedWorlds." + event.getEntity().getWorld().getName())) {
				Location spawn = Main.getInstance().getConfig().getLocation("preforia.spawn");
				if (spawn != null) {
					event.getEntity().teleport(spawn);
				} else {
					String worldname = event.getEntity().getWorld().getName();
					event.getEntity().teleport(Bukkit.getServer().getWorld(worldname).getSpawnLocation());
					if (event.getEntity().isOp()) {
						event.getEntity().sendMessage(Messages.prefix + "You have been teleported to world spawn because spawnpoint was not found.");
					}
				}
			}
		}
	}
}