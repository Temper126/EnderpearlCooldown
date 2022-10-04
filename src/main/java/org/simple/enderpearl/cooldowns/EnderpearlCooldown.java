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
import org.simple.enderpearl.EnderpearlPlugin;
import org.simple.utils.SimpleUtil;

public class EnderpearlCooldown implements Listener {
	private final EnderpearlPlugin plugin;

	public EnderpearlCooldown(EnderpearlPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(
			priority = EventPriority.HIGHEST
	)
	public void on(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getItemInHand().getType() == Material.ENDER_PEARL && this.plugin.getCooldownHandler().isOnCooldown(event.getPlayer(), "Ender pearl")) {
			SimpleUtil.sendMessage(event.getPlayer(), EnderpearlPlugin.PEARL_MESSAGE);
			event.setCancelled(true);
			player.updateInventory();
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
				this.plugin.getCooldownHandler().addCooldown(shooter, "Ender pearl", EnderpearlPlugin.PEARL_COOLDOWN);
			}
		}

	}
}
