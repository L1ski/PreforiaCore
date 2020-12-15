package preforia.api;

import net.luckperms.api.model.user.User;
import org.bukkit.OfflinePlayer;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.LuckPerms;

public class Permissions
{
    private static LuckPerms api;
    
    static {
        Permissions.api = LuckPermsProvider.get();
    }
    
    public static String getPlayerPrefix(final OfflinePlayer player) {
        final User user = Permissions.api.getUserManager().getUser(player.getUniqueId());
        String prefix = user.getCachedData().getMetaData(Permissions.api.getContextManager().getStaticQueryOptions()).getPrefix();
        if (prefix == null) {
            prefix = "";
        }
        return prefix;
    }
}
