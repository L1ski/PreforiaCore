package preforia.joinitems;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ServerSelectorGui {

	public static void serverGui(Player player) {
		
		Inventory gui = Bukkit.createInventory(player, 27, "Server Selector");
		
		ItemStack diamondblock = new ItemStack(Material.DIAMOND_BLOCK, 1);
		ItemMeta diamondblock_meta = diamondblock.getItemMeta();
		diamondblock_meta.setDisplayName("Creative");
		diamondblock_meta.setLore(Arrays.asList(new String[] { "§8Click to join creative server" }));
		diamondblock.setItemMeta(diamondblock_meta);
		
		ItemStack grass = new ItemStack(Material.GRASS_BLOCK, 1);
		ItemMeta grass_meta = grass.getItemMeta();
		grass_meta.setDisplayName("Survival");
		grass_meta.setLore(Arrays.asList(new String[] { "§8Click to join survival server" }));
		grass.setItemMeta(grass_meta);
		
		ItemStack barrier = new ItemStack(Material.BARRIER, 1);
		ItemMeta barrier_meta = barrier.getItemMeta();
		barrier_meta.setDisplayName("§cExit");
		barrier_meta.setLore(Arrays.asList(new String[] { "§8Close menu" }));
		barrier.setItemMeta(barrier_meta);
		
		gui.setItem(15, diamondblock);
		gui.setItem(11, grass);
		gui.setItem(22, barrier);
		player.openInventory(gui);
		
	}
	
	
}
