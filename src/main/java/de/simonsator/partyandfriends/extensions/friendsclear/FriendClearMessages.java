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
		set("ANewConfirmationKeyWasGenerated", " &7A new confirmation key was generated. The key is &e[CONFIRMATION_KEY]&7. Please type into the console &6/friend clear [CONFIRMATION_KEY] &7to confirm the removing of all your friends. If a key was generated previously it is now invalid");
		set("IncorrectConfirmationKey", " &7The confirmation key you entered was incorrect. Please try again entering your confirmation key or generate a new one by using &6/friend clear&7.");
		set("AllFriendsRemoved", " &7All your friends were removed from your friend list.");
		set("NoConfirmationKeyGeneratedYet", " &7No confirmation key was created yet. Please use &6/friend clear &7to generate one&7.");
	}

	@Override
	public void reloadConfiguration() throws IOException {
		throw new UnsupportedOperationException();
	}
}
