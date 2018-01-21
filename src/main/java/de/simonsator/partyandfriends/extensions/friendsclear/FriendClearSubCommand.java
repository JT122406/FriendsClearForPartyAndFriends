package de.simonsator.partyandfriends.extensions.friendsclear;

import de.simonsator.partyandfriends.api.friends.abstractcommands.FriendSubCommand;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.config.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendClearSubCommand extends FriendSubCommand {
	private Map<OnlinePAFPlayer, String> confirmationKey = new HashMap<>();
	private final RandomString RANDOM_STRING;
	private Configuration MESSAGES;

	public FriendClearSubCommand(List<String> pCommands, int pPriority, String pHelp, String pPermission, int pKeyLength, Configuration pMessages) {
		super(pCommands, pPriority, pHelp, pPermission);
		RANDOM_STRING = new RandomString(pKeyLength);
		MESSAGES = pMessages;
	}

	@Override
	public void onCommand(OnlinePAFPlayer pPlayer, String[] args) {
		if (confirmationKey.containsKey(pPlayer)) {
			if (args.length == 1) {
				pPlayer.sendMessage();
				return;
			}
			if (args[1].equals(confirmationKey.get(pPlayer))) {
				for (PAFPlayer friend : pPlayer.getFriends())
					pPlayer.removeFriend(friend);
				pPlayer.sendMessage();
				return;
			}
			pPlayer.sendMessage();
			return;
		}
		if (args.length == 1) {
			String random = RANDOM_STRING.nextString();
			confirmationKey.put(pPlayer, random);
			pPlayer.sendMessage();
			return;
		}
		sendError(pPlayer, new TextComponent(MESSAGES));
	}
}
