package preforia.format;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormat implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		String message = event.getMessage().replaceAll("%", "%%");
		event.setFormat("" + event.getPlayer().getDisplayName() + " : " + message);
	}
}
