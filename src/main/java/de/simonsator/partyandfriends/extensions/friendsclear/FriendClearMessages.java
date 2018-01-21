package de.simonsator.partyandfriends.extensions.friendsclear;

import de.simonsator.partyandfriends.utilities.Language;
import de.simonsator.partyandfriends.utilities.LanguageConfiguration;

import java.io.File;
import java.io.IOException;

public class FriendClearMessages extends LanguageConfiguration {
	protected FriendClearMessages(Language pLanguage, File pFile) throws IOException {
		super(pLanguage, pFile);
		readFile();
		loadDefaults();
		saveFile();
		process(configuration);
	}

	private void loadDefaults() {
		set("CommandUsage", "&8/&5friend clear <confirmation key> &8- &7Removes all your friends");
		set("EnterPreviousGeneratedKey", "");
	}

	@Override
	public void reloadConfiguration() throws IOException {
		throw new UnsupportedOperationException();
	}
}
