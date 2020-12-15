package preforia.sit;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

public class SitRemove implements Listener {

	@EventHandler
	public void onSit(EntityDismountEvent event) {
		if (event.getDismounted() instanceof ArmorStand) {
			if (event.getEntity() instanceof Player) {
				ArmorStand arms = (ArmorStand) event.getDismounted();
				Player player = (Player) event.getEntity();
				if (arms.getCustomName().equalsIgnoreCase(player.getUniqueId().toString() + "SIT")) {
					arms.remove();
				}
			}
		}
 	}
}
