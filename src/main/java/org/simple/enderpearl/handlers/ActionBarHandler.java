package org.simple.enderpearl.handlers;

import com.google.common.collect.Sets;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.simple.actionbar.ActionBarAPI;
import org.simple.enderpearl.EnderpearlPlugin;
import org.simple.enderpearl.pojo.IDCooldown;

import java.util.Set;
import java.util.UUID;

public class ActionBarHandler {
	private final EnderpearlPlugin enderpearlPlugin;

	public ActionBarHandler(EnderpearlPlugin enderpearlPlugin) {
		this.enderpearlPlugin = enderpearlPlugin;
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this.enderpearlPlugin, () -> {
			Set<UUID> actioned = Sets.newHashSet();
			this.enderpearlPlugin.getCooldownHandler().getCooldownMap().forEach((uuid, cooldownMap) -> {
				cooldownMap.values().forEach((cooldown) -> {
					if (cooldown.isShowCooldown()) {
						if (!actioned.contains(cooldown.getUuid())) {
							actioned.add(cooldown.getUuid());
							Player player = Bukkit.getPlayer(cooldown.getUuid());
							if (player != null && player.isOnline()) {
								ActionBarAPI.sendActionBar(this.enderpearlPlugin, Bukkit.getPlayer(cooldown.getUuid()), this.enderpearlPlugin.getCooldownHandler().getRemainingTimeBar(new IDCooldown(cooldown.getUuid(), cooldown.getCooldownId())), -1);
							}

						}
					}
				});
			});
		}, 0L, 5L);
	}
}
