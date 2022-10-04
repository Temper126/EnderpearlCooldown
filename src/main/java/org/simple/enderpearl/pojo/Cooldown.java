package org.simple.enderpearl.pojo;

import java.util.UUID;

public class Cooldown {
	private final UUID uuid;
	private final String cooldownId;
	private final long millis;
	private final long duration;
	private final boolean showCooldown;
	private final boolean removeOnDeath;

	public Cooldown(UUID uuid, String cooldownId, long millis, long duration) {
		this(uuid, cooldownId, millis, duration, true);
	}

	public Cooldown(UUID uuid, String cooldownId, long millis, long duration, boolean showCooldown) {
		this(uuid, cooldownId, millis, duration, true, false);
	}

	public Cooldown(UUID uuid, String cooldownId, long millis, long duration, boolean showCooldown, boolean removeOnDeath) {
		this.uuid = uuid;
		this.cooldownId = cooldownId;
		this.millis = millis;
		this.duration = duration;
		this.showCooldown = showCooldown;
		this.removeOnDeath = removeOnDeath;
	}

	public UUID getUuid() {
		return this.uuid;
	}

	public String getCooldownId() {
		return this.cooldownId;
	}

	public long getMillis() {
		return this.millis;
	}

	public long getDuration() {
		return this.duration;
	}

	public boolean isShowCooldown() {
		return this.showCooldown;
	}

	public boolean isRemoveOnDeath() {
		return this.removeOnDeath;
	}
}