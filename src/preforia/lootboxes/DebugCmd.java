package preforia.lootboxes;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import preforia.api.Main;
import preforia.api.Messages;

public class DebugCmd implements CommandExecutor {

	Material[] unsafe = { 
			Material.WATER,
			Material.LAVA,
			Material.FIRE,
			Material.CAMPFIRE,
			Material.SWEET_BERRIES,
			Material.BEDROCK };
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		Location loc = generateRandomLoc(player.getWorld());
		
		while (loc == null) {
			loc = generateRandomLoc(player.getWorld());
		}
		
		loc.getChunk().load();
		while (loc.getChunk().isLoaded() == false) {
			loc.getChunk().load();
		}
		
		@SuppressWarnings("deprecation")
		FallingBlock block = loc.getWorld().spawnFallingBlock(loc, Material.SAND, (byte) 0x0);

		Main.LOOT_DROP.add(block);
		
		block.setGlowing(true);
		block.setDropItem(false);
		block.setInvulnerable(false);
		block.setHurtEntities(false);
		block.setGravity(true);
		
		Location toploc = getTopLoc(block.getLocation());
		
		makeBlockSlower(block);
		spawnParticles(block, toploc);
		
		for (Player players : Bukkit.getOnlinePlayers()) {
			players.playSound(block.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 200, 200);
		}
		Bukkit.broadcastMessage(Messages.prefix + "Meteorite has appeared to cordinates " + Messages.color1 + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + "§7.");
		
		return false;
    	
	}
	
	public void spawnParticles(FallingBlock block, Location toploc) {

		Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				if (block.isOnGround()) {
					Main.LOOT_DROP.remove(block);
					Location bloc = block.getLocation();
					Bukkit.broadcastMessage(Messages.prefix + "Rare meteorite has landed to cordinates " + Messages.color1 + bloc.getBlockX() + " " + bloc.getBlockY() + " " + bloc.getBlockZ() + "§7.");
					bloc.getWorld().createExplosion(bloc, 11.0F); //4.0 = tnt
					toploc.getWorld().spigot().strikeLightning(toploc, true);
					Location loc = getTopLoc(block.getLocation());
					loc.getBlock().setType(Material.CHEST);
					
				} else {
					
					Firework fw = block.getWorld().spawn(block.getLocation(), Firework.class);
			        FireworkMeta data = fw.getFireworkMeta();
			        data.addEffects(FireworkEffect.builder().withColor(Color.BLACK).with(Type.BALL).build());
					fw.setFireworkMeta(data);
					fw.detonate();
					
					block.getLocation().getChunk().load();
					 
					block.getWorld().spawnParticle(Particle.TOTEM, block.getLocation(), 8, 0.3F, 0.2F, 0.3F);
					block.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, block.getLocation(), 1);
					 
					spawnParticles(block, toploc);
				}	
			}
		}, 10*1);
	}
	
	public void makeBlockSlower(FallingBlock block) {
		
		Bukkit.getServer().getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            public void run() {
               block.setVelocity(new Vector(0, 0.0, 0));
               makeBlockSlower(block);
            }
        }, 10*1);
		
	}
	
    public static Location getTopLoc(Location loc) {
        int topX =loc.getBlockX();
        int topZ = loc.getBlockZ();
        int topY = loc.getWorld().getHighestBlockYAt(topX, topZ);
        Location blockloc = new Location(loc.getWorld(), topX, topY + 1, topZ, loc.getYaw(), loc.getPitch());
        return blockloc;
    }
    
    public void generateRandomItem(Chest chest) {
    	
    	Random rnd = new Random();
		Inventory inv = chest.getInventory();
		
		ItemStack item = new ItemStack(Material.BOW, 1);
		
		ItemStack slotitem = inv.getItem(rnd.nextInt(inv.getSize()));
		if (slotitem == null) {
			
			inv.setItem(rnd.nextInt(inv.getSize()), item);	
		} else {
			generateRandomItem(chest);
		}
    	
    }
    public Location generateRandomLoc(World world) {
    	
    	int range = (int) world.getWorldBorder().getSize();
		
		int x = (int) (world.getWorldBorder().getCenter().getBlockX() - world.getWorldBorder().getSize() / 2);
		int z = (int) (world.getWorldBorder().getCenter().getBlockZ() - world.getWorldBorder().getSize() / 2);
		
		Random rnd = new Random();
		
		Location loc = new Location(world, x + (range - rnd.nextInt(range)), 256, z + (range - rnd.nextInt(range)));
    	
		Location topblockloc = getTopLoc(loc);
		
		Location checkedloc = new Location(topblockloc.getWorld(), topblockloc.getBlockX(), topblockloc.getBlockY() - 1, topblockloc.getBlockZ());
		
		Location location = new Location(loc.getWorld(), checkedloc.getBlockX(), checkedloc.getBlockY() + 150, checkedloc.getBlockZ());
		
		if (checkedloc.getBlockY() > 120) {
			return null;
		}
		
		boolean safeblock = true;
		
		for (Material m : unsafe) {
        	if (checkedloc.getBlock().getType().equals(m)) {
        		safeblock = false;
        		return null;
        	}
		}
		if (safeblock == true) {
			return location;
		} else {
			return null;
		}
    }

}
