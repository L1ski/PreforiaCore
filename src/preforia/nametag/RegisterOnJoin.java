package preforia.nametag;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Team;

public class RegisterOnJoin implements Listener {
	
	@EventHandler
	public void playerJoinEvent(final PlayerJoinEvent event) {
    	final String rawprefix = preforia.api.Permissions.getPlayerPrefix ((OfflinePlayer) event.getPlayer());
    	
    	String prefix = rawprefix.replaceAll("&", "§");
        String teamname = prefix;
        if (teamname.length() > 16) {
        	teamname = teamname.substring(0, 16);
        }
        Team t = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(teamname);
        if (t != null) {
        	t.addEntry(event.getPlayer().getName());
        	event.getPlayer().setDisplayName(prefix + event.getPlayer().getName());
            return;
        } else {
        	t = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(teamname);
            t.setPrefix(prefix);
            t.addEntry(event.getPlayer().getName());
            event.getPlayer().setDisplayName(prefix + event.getPlayer().getName());
        }
    }
	
}
