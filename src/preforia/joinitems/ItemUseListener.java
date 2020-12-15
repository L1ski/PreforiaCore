package preforia.joinitems;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import preforia.api.Main;
import preforia.api.Messages;

public class ItemUseListener implements Listener {

	 Main plugin;

	 public ItemUseListener(Main plugin) {
		 this.plugin = plugin;
	 }
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if ((event.getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_AIR) || (event.getAction() == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK)) {
			switch (player.getInventory().getItemInMainHand().getType()) {
			
			case COMPASS:
				ServerSelectorGui.serverGui(player);
				break;
				
			case LIME_DYE:
				hidePlayers(player);
				break;
				
			case GRAY_DYE:
				showPlayers(player);
				break;
				
			default:
				break;
			}
		}
	}
	public void openServers(Player player) {
		
		
	}
	public void hidePlayers(Player player) {
		
		if (!Messages.itemUseCooldown(player) == true) { return; }
		
		Main.IGNORE_PLAYERS.add(player);
		for (Player users : Bukkit.getOnlinePlayers()) {
			player.hidePlayer(plugin, users);
		}
		ItemStack dye = new ItemStack(Material.GRAY_DYE, 1);
		ItemMeta dye_meta = dye.getItemMeta();
		dye_meta.setDisplayName("§fOther players: §c§lInvisible§r §8[Right-click]");
		dye_meta.setLore(Arrays.asList(new String[] { "§8If you dont want to see other players,", "§8you can use this tool." }));
		dye.setItemMeta(dye_meta);
		
		player.getInventory().setItem(7, dye);
		
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 60, 60);
	}
	
	public void showPlayers(Player player) {
		
		if (!Messages.itemUseCooldown(player) == true) { return; }
		
		Main.IGNORE_PLAYERS.remove(player);
		for (Player users : Bukkit.getOnlinePlayers()) {
			player.showPlayer(plugin, users);
		}
		ItemStack dye = new ItemStack(Material.LIME_DYE, 1);
		ItemMeta dye_meta = dye.getItemMeta();
		dye_meta.setDisplayName("§fOther players: §a§lVisible§r §8[Right-click]");
		dye_meta.setLore(Arrays.asList(new String[] { "§8If you dont want to see other players,", "§8you can use this tool." }));
		dye.setItemMeta(dye_meta);
		
		player.getInventory().setItem(7, dye);
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 60, 60);
	}
}
