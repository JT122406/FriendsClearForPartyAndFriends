package de.simonsator.partyandfriends.extensions.friendsclear;

import de.simonsator.partyandfriends.api.PAFExtension;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class FriendClearKeyRemoverBungee implements Listener {
	private final FriendClearSubCommand FRIEND_CLEAR_SUB_COMMAND;

	public FriendClearKeyRemoverBungee(FriendClearSubCommand pSubCommand, PAFExtension pafExtension) {
		FRIEND_CLEAR_SUB_COMMAND = pSubCommand;
		ProxyServer.getInstance().getPluginManager().registerListener(pafExtension, this);
	}

	@EventHandler
	public void onLeave(PlayerDisconnectEvent pEvent) {
		FRIEND_CLEAR_SUB_COMMAND.removeKey(pEvent.getPlayer().getUniqueId());
	}
}
