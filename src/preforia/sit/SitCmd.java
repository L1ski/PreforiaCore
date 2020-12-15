package preforia.sit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import preforia.api.Messages;

public class SitCmd implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.onlyplayerscommand);
			return false;
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("preforia.sit")) {
			player.sendMessage(Messages.nopermission);
			return false;
		}
		if (player.isOnGround()) {
				
			ArmorStand armorstand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().subtract(0.0D, 1.0D, 0), EntityType.ARMOR_STAND);
			
			armorstand.setVisible(false);
			armorstand.setCustomName(player.getUniqueId().toString() + "SIT");
			armorstand.setGravity(false);
			armorstand.setInvulnerable(false);
			armorstand.setCustomNameVisible(false);
			armorstand.addPassenger(player);
			armorstand.setSmall(true);
			player.sendMessage(Messages.prefix + "You are now sitting.");
			return true;
		} else {
			player.sendMessage(Messages.error + "You cant sit here!");
			return false;
		}
	}
}
