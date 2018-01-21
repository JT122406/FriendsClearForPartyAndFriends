package de.simonsator.partyandfriends.extensions.friendsclear;

import de.simonsator.partyandfriends.utilities.ConfigurationCreator;

import java.io.File;
import java.io.IOException;

public class FriendClearConfig extends ConfigurationCreator {
	protected FriendClearConfig(File file) throws IOException {
		super(file);
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

	@Override
	public void reloadConfiguration() throws IOException {
		throw new UnsupportedOperationException();
	}
}
