package preforia.rtp;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class Rtp {

	private Material[] unsafeBlocks = {
			Material.WATER,
			Material.LAVA,
			Material.CACTUS,
			Material.MAGMA_BLOCK,
			Material.SWEET_BERRY_BUSH,
			Material.CAMPFIRE
		};
		
		public Location getRandomLocation(World world) {
			
			int range = (int) world.getWorldBorder().getSize();
			
			int x = (int) (world.getWorldBorder().getCenter().getBlockX() - world.getWorldBorder().getSize() / 2);
			int z = (int) (world.getWorldBorder().getCenter().getBlockZ() - world.getWorldBorder().getSize() / 2);
			
			Random rnd = new Random();
			
			for(int i = 0; i < 10; i++) {
				Location loc = new Location(world, x + (range - rnd.nextInt(range)), 256, z + (range - rnd.nextInt(range)));
				loc.setY(world.getHighestBlockYAt(loc.getBlockX(), loc.getBlockZ()));
				
				boolean safe = true;
				for(Material material : unsafeBlocks) {
					if(material.equals(loc.getBlock().getType())) {
						safe = false;
						break;
					}
				}
				if(safe) return loc;
			}
			return null;
		}
	
}
