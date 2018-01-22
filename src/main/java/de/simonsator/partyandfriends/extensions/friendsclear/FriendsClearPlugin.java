package de.simonsator.partyandfriends.extensions.friendsclear;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.friends.commands.Friends;
import de.simonsator.partyandfriends.main.Main;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;

public class FriendsClearPlugin extends PAFExtension {
	@Override
	public void onEnable() {
		try {
			Configuration config = new FriendClearConfig(new File(getDataFolder(), "config.yml")).getCreatedConfiguration();
			Configuration messages = new FriendClearMessages(Main.getInstance().getLanguage(), new File(getDataFolder(), "messages.yml")).getCreatedConfiguration();
			FriendClearSubCommand friendClearSubCommand = new FriendClearSubCommand(config.getStringList("Names"), config.getInt("Priority"), messages.getString("CommandUsage"), config.getString("Permission"), config.getInt("ConfirmationKeyLength"), messages);
			getProxy().getPluginManager().registerListener(this, friendClearSubCommand);
			Friends.getInstance().addCommand(friendClearSubCommand);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

