package preforia.joinitems;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import preforia.api.Main;

public class CompassHandler implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (event.getView().getTitle().equalsIgnoreCase("Server Selector")) {
			
			event.setCancelled(true);
			
			switch (event.getCurrentItem().getType()) {
			
			case GRASS_BLOCK:
				Main.sendPlayerToServer(player, "survival");
				break;
			
			case DIAMOND_BLOCK:
				Main.sendPlayerToServer(player, "creative");
				break;
				
			case BARRIER:
				player.closeInventory();
				break;
			default:
				break;
			}
			
		}
		
	}
}
