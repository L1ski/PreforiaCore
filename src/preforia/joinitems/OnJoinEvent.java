package preforia.joinitems;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OnJoinEvent implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		player.getInventory().clear();
		
		ItemStack compass = new ItemStack(Material.COMPASS, 1);
		ItemMeta compass_meta = compass.getItemMeta();
		compass_meta.setDisplayName("§fServer selector §8[Right-click]");
		compass_meta.setLore(Arrays.asList(new String[] { "§8Select server to join." }));
		compass.setItemMeta(compass_meta);
		
		ItemStack dye = new ItemStack(Material.LIME_DYE, 1);
		ItemMeta dye_meta = dye.getItemMeta();
		dye_meta.setDisplayName("§fOther players: §a§lShown§r §8[Right-click]");
		dye_meta.setLore(Arrays.asList(new String[] { "§8If you dont want to see other players,", "§8you can use this tool." }));
		dye.setItemMeta(dye_meta);
		
		player.getInventory().setItem(3, compass);
		player.getInventory().setItem(7, dye);
	}
}
