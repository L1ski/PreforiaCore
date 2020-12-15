package preforia.lootboxes;

import org.bukkit.Bukkit;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

import preforia.api.Main;

public class LootboxEvents implements Listener {

	@EventHandler
	public void onblockDamage(BlockDamageEvent event) {
		if (event.getBlock() instanceof FallingBlock) {
			if (Main.LOOT_DROP.contains((FallingBlock) event.getBlock())) {
				event.setCancelled(true);
			}
		}
	}
}
