package preforia.homes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import preforia.api.Main;


public class HomeTab implements TabCompleter {
	
	private Main main = Main.getInstance();
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {		
		
		Player player = (Player) sender;
		
		ArrayList<String> tb = new ArrayList<String>();
		
		if (main.getConfig().get("Preforia." + ".users." + player.getUniqueId() + ".Name." + player.getName() + ".homes") != null) {
		
			Set<String> homes = main.getConfig().getConfigurationSection("Preforia." + ".users." + player.getUniqueId() + ".Name." + player.getName() + ".homes").getKeys(false);
			for (String home : homes) {
				if (home.startsWith(args[0].toLowerCase())) {
					tb.add(home);
				}
			}
	}
		return tb;
	}
	
}
