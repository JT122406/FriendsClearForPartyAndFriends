package de.simonsator.partyandfriends.extensions.friendsclear;

import de.simonsator.partyandfriends.api.friends.abstractcommands.FriendSubCommand;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FriendClearSubCommand extends FriendSubCommand implements Listener {
	private final RandomString RANDOM_STRING;
	private Map<UUID, String> confirmationKey = new HashMap<>();
	private Configuration MESSAGES;

	public FriendClearSubCommand(List<String> pCommands, int pPriority, String pHelp, String pPermission, int pKeyLength, Configuration pMessages) {
		super(pCommands, pPriority, pHelp, pPermission);
		RANDOM_STRING = new RandomString(pKeyLength);
		MESSAGES = pMessages;
	}

	@Override
	public void onCommand(OnlinePAFPlayer pPlayer, String[] args) {
		if (args.length == 1) {
			String random = RANDOM_STRING.nextString();
			confirmationKey.put(pPlayer.getUniqueId(), random);
			pPlayer.sendMessage(PREFIX + MESSAGES.getString("ANewConfirmationKeyWasGenerated").replace("[CONFIRMATION_KEY]", random));
			return;
		}
		if (confirmationKey.containsKey(pPlayer.getUniqueId())) {
			if (args[1].equals(confirmationKey.get(pPlayer.getUniqueId()))) {
				for (PAFPlayer friend : pPlayer.getFriends())
					pPlayer.removeFriend(friend);
				confirmationKey.remove(pPlayer.getUniqueId());
				pPlayer.sendMessage(PREFIX + MESSAGES.getString("AllFriendsRemoved"));
				return;
			}
			sendError(pPlayer, new TextComponent(PREFIX + MESSAGES.getString("IncorrectConfirmationKey")));
			return;
		}
		sendError(pPlayer, new TextComponent(PREFIX + MESSAGES.getString("NoConfirmationKeyGeneratedYet")));
	}

	@EventHandler
	public void onLeave(PlayerDisconnectEvent pEvent) {
		confirmationKey.remove(pEvent.getPlayer().getUniqueId());
	}
}
