package preforia.rtp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preforia.api.Main;
import preforia.api.Messages;

public class RtpCmd implements CommandExecutor {

	private static int times = 0;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		Player player = (Player) sender;
		
		Location loc = new Rtp().getRandomLocation(player.getWorld());
		
		int y = loc.getBlockY();
		int x = loc.getBlockX();
		int z = loc.getBlockZ();
		World world = loc.getWorld();
		
		Location randomloc = new Location(world, x, z, y);
		player.teleport(randomloc);
		
		return true;
	}
	
	public void loop(Player player, Location loc) {
		
		if (!(times > 4)) {
			Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
				@Override
				public void run() {
					player.teleport(loc);
					player.sendMessage("tp");
					times++;
					loop(player, loc);
				}
			}, 5);
		} else {
			times = 0;
		}
		
	}

}
