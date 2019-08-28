package de.simonsator.partyandfriends.extensions.friendsclear;


import de.simonsator.partyandfriends.api.PAFExtension;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class FriendClearKeyRemoverBukkit implements Listener {
	private final FriendClearSubCommand FRIEND_CLEAR_SUB_COMMAND;

	public FriendClearKeyRemoverBukkit(FriendClearSubCommand pSubCommand, PAFExtension pafExtension) {
		FRIEND_CLEAR_SUB_COMMAND = pSubCommand;
		Bukkit.getPluginManager().registerEvents(this, (Plugin) pafExtension);
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent pEvent) {
		FRIEND_CLEAR_SUB_COMMAND.removeKey(pEvent.getPlayer().getUniqueId());
	}
}
