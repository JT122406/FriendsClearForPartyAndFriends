package de.simonsator.partyandfriends.extensions.friendsclear;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.friends.commands.Friends;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

public class FriendsClearPlugin extends PAFExtension {
	@Override
	public void onEnable() {
		try {
			ConfigurationCreator config = new FriendClearConfig(new File(getConfigFolder(), "config.yml"), this);
			ConfigurationCreator messages = new FriendClearMessages(new File(getConfigFolder(), "messages.yml"), this);
			FriendClearSubCommand friendClearSubCommand = new FriendClearSubCommand(config.getStringList("Names"), config.getInt("Priority"),
					messages.getString("CommandUsage"), config.getString("Permission"), config.getInt("ConfirmationKeyLength"), messages, this);
			Friends.getInstance().addCommand(friendClearSubCommand);
			registerAsExtension();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

