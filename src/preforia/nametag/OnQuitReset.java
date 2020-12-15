package preforia.nametag;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;

public class OnQuitReset implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		player.setDisplayName(player.getName());
		for (Team t : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
			if (t.getEntries().contains(player.getName())) {
				t.removeEntry(player.getName());
			}
		}
	}
}
