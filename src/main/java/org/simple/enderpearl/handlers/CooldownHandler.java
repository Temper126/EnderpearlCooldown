package org.simple.enderpearl.handlers;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.simple.enderpearl.EnderpearlPlugin;
import org.simple.enderpearl.pojo.Cooldown;
import org.simple.enderpearl.pojo.IDCooldown;
import org.simple.utils.ItemUtil;
import org.simple.utils.TimeUtil;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class CooldownHandler {
	private final EnderpearlPlugin enderpearlcooldown;
	private final Map<UUID, Map<String, Cooldown>> cooldownMap;

	public CooldownHandler(EnderpearlPlugin enderpearlPlugin) {
		this.enderpearlcooldown = enderpearlPlugin;
		this.cooldownMap = Maps.newHashMap();
	}

	public Map<UUID, Map<String, Cooldown>> getCooldownMap() {
		return Collections.unmodifiableMap(this.cooldownMap);
	}

	public void addCooldown(Player player, String cooldownId, long millis) {
		this.addCooldown(player.getUniqueId(), cooldownId, millis);
	}

	public void addCooldown(UUID uuid, String cooldownId, long millis) {
		this.addCooldown(uuid, cooldownId, millis, true);
	}

	public void addCooldown(Player player, String cooldownId, long millis, boolean showCooldown) {
		this.addCooldown(player.getUniqueId(), cooldownId, millis, showCooldown);
	}

	public void addCooldown(UUID uuid, String cooldownId, long millis, boolean showCooldown) {
		this.addCooldown(uuid, cooldownId, millis, showCooldown, false);
	}

	public void addCooldown(UUID uuid, String cooldownId, long millis, boolean showCooldown, boolean removeOnDeath) {
		this.addCooldown(new IDCooldown(uuid, cooldownId, millis, showCooldown), millis, removeOnDeath);
	}

	public void addCooldown(IDCooldown cooldown, long millis, boolean removeOnDeath) {
		if (!Bukkit.getPlayer(cooldown.getUuid()).hasPermission("fantasycooldowns.admin")) {
			Cooldown newCooldown = new Cooldown(cooldown.getUuid(), cooldown.getCooldownId(), System.currentTimeMillis() + millis, millis, cooldown.isShowCooldown(), removeOnDeath);
			if (this.cooldownMap.containsKey(cooldown.getUuid())) {
				((Map) this.cooldownMap.get(cooldown.getUuid())).put(cooldown.getCooldownId(), newCooldown);
			} else {
				Map<String, Cooldown> cooldownMap = Maps.newHashMap();
				cooldownMap.put(cooldown.getCooldownId(), newCooldown);
				this.cooldownMap.put(cooldown.getUuid(), cooldownMap);
			}

		}
	}

	public boolean isOnCooldown(Player player, String cooldownId) {
		return this.isOnCooldown(player.getUniqueId(), cooldownId);
	}

	public boolean isOnCooldown(UUID uuid, String cooldownId) {
		return this.isOnCooldown(new IDCooldown(uuid, cooldownId));
	}

	public boolean isOnCooldown(IDCooldown cooldown) {
		return this.getRemainingMillis(cooldown) > 0L;
	}

	public long getRemainingMillis(Player player, String cooldownId) {
		return this.getRemainingMillis(player.getUniqueId(), cooldownId);
	}

	public long getRemainingMillis(UUID uuid, String cooldownId) {
		return this.getRemainingMillis(new IDCooldown(uuid, cooldownId));
	}

	public long getRemainingMillis(IDCooldown idCooldown) {
		try {
			Cooldown cooldown = (Cooldown) ((Map) this.cooldownMap.get(idCooldown.getUuid())).get(idCooldown.getCooldownId());
			long millis = cooldown.getMillis() - System.currentTimeMillis();
			if (millis <= 0L) {
				Bukkit.getScheduler().runTaskLater(this.enderpearlcooldown, () -> {
					((Map) this.cooldownMap.get(idCooldown.getUuid())).remove(idCooldown.getCooldownId());
				}, 1L);
			}

			return millis <= 0L ? 0L : millis;
		} catch (NullPointerException var5) {
			return 0L;
		}
	}

	public long getDuration(Player player, String cooldownId) {
		return this.getDuration(player.getUniqueId(), cooldownId);
	}

	public long getDuration(UUID uuid, String cooldownId) {
		return this.getDuration(new IDCooldown(uuid, cooldownId));
	}

	public long getDuration(IDCooldown cooldown) {
		return ((Cooldown) ((Map) this.cooldownMap.get(cooldown.getUuid())).get(cooldown.getCooldownId())).getDuration();
	}

	public void removeCooldown(Player player, String cooldownId) {
		((Map) this.cooldownMap.get(player.getUniqueId())).remove(cooldownId);
	}

	public Map<String, Cooldown> getCooldowns(Player player) {
		return this.getCooldowns(player.getUniqueId());
	}

	public Map<String, Cooldown> getCooldowns(UUID uuid) {
		return (Map) this.getCooldownMap().getOrDefault(uuid, Maps.newHashMap());
	}

	public String getRemainingTimeFormatted(Player player, String cooldownId) {
		return this.getRemainingTimeFormatted(player.getUniqueId(), cooldownId);
	}

	public String getRemainingTimeFormatted(UUID uuid, String cooldownId) {
		return this.getRemainingTimeFormatted(new IDCooldown(uuid, cooldownId));
	}

	public String getRemainingTimeFormatted(IDCooldown cooldown) {
		return TimeUtil.formatTime((int) Math.ceil((double) this.getRemainingMillis(cooldown) / 1000.0D));
	}

	public String getRemainingTimeBar(Player player, String cooldownId) {
		return this.getRemainingTimeFormatted(player.getUniqueId(), cooldownId);
	}

	public String getRemainingTimeBar(UUID uuid, String cooldownId) {
		return this.getRemainingTimeFormatted(new IDCooldown(uuid, cooldownId));
	}

	public String getRemainingTimeBar(IDCooldown cooldown) {
		try {
			long millis = this.getRemainingMillis(cooldown);
			return millis <= 0L ? "" : "&e" + cooldown.getCooldownId() + " &r" + ItemUtil.getXPBar((int) Math.abs((long) ((int) millis) - this.getDuration(cooldown)), (int) this.getDuration(cooldown), 15) + " &e" + this.getRemainingTimeFormatted(cooldown);
		} catch (NullPointerException var4) {
			return "";
		}
	}
}
