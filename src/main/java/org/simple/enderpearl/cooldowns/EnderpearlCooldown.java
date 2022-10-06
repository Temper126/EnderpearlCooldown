package org.simple.enderpearl.cooldowns;

import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.simple.actionbar.ActionBarAPI;
import org.simple.cooldown.PlayerCooldownMap;
import org.simple.enderpearl.EnderpearlPlugin;
import org.simple.utils.SimpleUtil;

public class EnderpearlCooldown implements Listener {
	private final EnderpearlPlugin plugin;
	public static final String COOLDOWN_ID = "enderpearl";
	public PlayerCooldownMap playerCooldownMap = new PlayerCooldownMap("enderpearl");

	public EnderpearlCooldown(EnderpearlPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(
			priority = EventPriority.HIGHEST
	)
	public void on(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getItemInHand().getType() == Material.ENDER_PEARL) {
			if (this.playerCooldownMap.isOnCooldown(player, COOLDOWN_ID)) {
				SimpleUtil.sendMessage(player, EnderpearlPlugin.PEARL_MESSAGE);
				event.setCancelled(true);
			} else {
				ActionBarAPI.sendActionBar(this.plugin, player, "Enderpearl", EnderpearlPlugin.PEARL_COOLDOWN);
				this.playerCooldownMap.add(player, EnderpearlPlugin.PEARL_COOLDOWN);
			}
		}

	}

	@EventHandler(
			ignoreCancelled = true,
			priority = EventPriority.MONITOR
	)
	public void on(ProjectileLaunchEvent event) {
		Projectile projectile = event.getEntity();
		if (projectile instanceof EnderPearl) {
			EnderPearl enderPearl = (EnderPearl) projectile;
			ProjectileSource source = enderPearl.getShooter();
			if (source instanceof Player) {
				Player shooter = (Player) source;
				this.playerCooldownMap.add(shooter, COOLDOWN_ID, EnderpearlPlugin.PEARL_COOLDOWN);
			}
		}

	}
}
