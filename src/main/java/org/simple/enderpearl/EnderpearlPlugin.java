package org.simple.enderpearl;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.simple.enderpearl.handlers.ActionBarHandler;
import org.simple.enderpearl.handlers.CooldownHandler;

public class EnderpearlPlugin extends JavaPlugin {
	public static int PEARL_COOLDOWN;
	public static String PEARL_MESSAGE;
	private CooldownHandler cooldownHandler;
	private ActionBarHandler actionBarHandler;

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		FileConfiguration config = this.getConfig();
		this.cooldownHandler = new CooldownHandler(this);
		this.actionBarHandler = new ActionBarHandler(this);
		PEARL_COOLDOWN = config.getInt("pearl.cooldown");
		PEARL_MESSAGE = config.getString("pearl.message");
	}

	@Override
	public void onDisable() {

	}

	public CooldownHandler getCooldownHandler() {
		return this.cooldownHandler;
	}

	public ActionBarHandler getActionBarHandler() {
		return this.actionBarHandler;
	}

}
