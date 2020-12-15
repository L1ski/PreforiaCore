package preforia.gamemode;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class GamemodeTab implements TabCompleter {
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {		
		
		ArrayList<String> tb = new ArrayList<String>();
		
		String[] gamemode = {"survival","creative","adventure","spectator"};
		
		if (sender instanceof Player) {
			if (args.length > 0) {
				for (String gamemodes : gamemode) {
					if (gamemodes.startsWith(args[0].toLowerCase()) && sender.hasPermission("preforia.gamemode." + gamemodes)) {
						tb.add(gamemodes);
					}
				}
			}
		}
		return tb;
	}
	
}
