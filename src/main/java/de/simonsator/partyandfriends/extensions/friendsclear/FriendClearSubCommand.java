package de.simonsator.partyandfriends.extensions.friendsclear;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.api.friends.abstractcommands.FriendSubCommand;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FriendClearSubCommand extends FriendSubCommand {
	private final RandomString RANDOM_STRING;
	private Map<UUID, String> confirmationKey = new HashMap<>();
	private ConfigurationCreator MESSAGES;

	public FriendClearSubCommand(List<String> pCommands, int pPriority, String pHelp, String pPermission, int pKeyLength, ConfigurationCreator pMessages, PAFExtension pPlugin) {
		super(pCommands, pPriority, pHelp, pPermission);
		RANDOM_STRING = new RandomString(pKeyLength);
		MESSAGES = pMessages;
		try {
			Class.forName("org.bukkit.Bukkit");
			new FriendClearKeyRemoverBukkit(this, pPlugin);
		} catch (ClassNotFoundException e) {
			new FriendClearKeyRemoverBungee(this, pPlugin);
		}
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

	public void removeKey(UUID pUUID) {
		confirmationKey.remove(pUUID);
	}
}
