package de.simonsator.partyandfriends.extensions.friendsclear;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

public class FriendClearConfig extends ConfigurationCreator {
	protected FriendClearConfig(File file, PAFExtension pPlugin) throws IOException {
		super(file, pPlugin, true);
		readFile();
		loadDefaults();
		saveFile();
	}

	private void loadDefaults() {
		set("Names", "clear", "clearfriends");
		set("Priority", 100);
		set("Permission", "");
		set("ConfirmationKeyLength", 5);
	}
}
