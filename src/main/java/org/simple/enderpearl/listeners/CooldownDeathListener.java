package org.simple.enderpearl.listeners;

import com.google.common.collect.Sets;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.simple.enderpearl.handlers.CooldownHandler;
import org.simple.enderpearl.pojo.Cooldown;

import java.util.Set;

public class CooldownDeathListener implements Listener {
	private final CooldownHandler cooldownHandler;

	public CooldownDeathListener(CooldownHandler cooldownHandler) {
		this.cooldownHandler = cooldownHandler;
	}

	@EventHandler
	public void on(PlayerDeathEvent event) {
		Set<Cooldown> cooldowns = Sets.newHashSet(this.cooldownHandler.getCooldowns(event.getEntity()).values());
		cooldowns.forEach((cooldown) -> {
			if (cooldown.isRemoveOnDeath()) {
				this.cooldownHandler.removeCooldown(event.getEntity(), cooldown.getCooldownId());
			}

		});
	}
}
