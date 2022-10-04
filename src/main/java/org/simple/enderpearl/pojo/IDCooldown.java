package org.simple.enderpearl.pojo;

import java.util.UUID;

public class IDCooldown {
	private final UUID uuid;
	private final String cooldownId;
	private final long millis;
	private final boolean showCooldown;

	public IDCooldown(UUID uuid, String cooldownId) {
		this(uuid, cooldownId, -1L);
	}

	public IDCooldown(UUID uuid, String cooldownId, long millis) {
		this(uuid, cooldownId, millis, true);
	}

	public IDCooldown(UUID uuid, String cooldownId, long millis, boolean showCooldown) {
		this.uuid = uuid;
		this.cooldownId = cooldownId;
		this.millis = millis;
		this.showCooldown = showCooldown;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof IDCooldown)) {
			return false;
		} else {
			IDCooldown idCooldown = (IDCooldown) o;
			return idCooldown.getUuid().equals(this.uuid) && idCooldown.getCooldownId().equalsIgnoreCase(this.cooldownId);
		}
	}

	@Override
	public int hashCode() {
		long hilo = (long) (this.uuid.hashCode() ^ this.cooldownId.toLowerCase().hashCode());
		return (int) (hilo >> 32) ^ (int) hilo;
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

	public boolean isShowCooldown() {
		return this.showCooldown;
	}

	@Override
	public String toString() {
		return "IDCooldown{uuid=" + this.uuid + ", cooldownId='" + this.cooldownId + '\'' + ", millis=" + this.millis + ", showCooldown=" + this.showCooldown + '}';
	}
}
