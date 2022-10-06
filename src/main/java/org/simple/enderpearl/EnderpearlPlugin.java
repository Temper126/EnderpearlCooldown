package org.simple.enderpearl;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class EnderpearlPlugin extends JavaPlugin {
	public static int PEARL_COOLDOWN;
	public static String PEARL_MESSAGE;

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		FileConfiguration config = this.getConfig();
		PEARL_COOLDOWN = config.getInt("pearl.cooldown");
		PEARL_MESSAGE = config.getString("pearl.message");
	}

	@Override
	public void onDisable() {

	}


}
