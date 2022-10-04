package org.simple.enderpearl.pojo;

import org.bukkit.Material;

public class ConfiguredCooldown {
	private final String cooldownId;
	private final Material type;
	private final short damage;
	private final long cooldown;

	public ConfiguredCooldown(String cooldownId, Material type, short damage, long cooldown) {
		this.cooldownId = cooldownId;
		this.type = type;
		this.damage = damage;
		this.cooldown = cooldown;
	}

	public String getCooldownId() {
		return this.cooldownId;
	}

	public Material getType() {
		return this.type;
	}

	public short getDamage() {
		return this.damage;
	}

	public long getCooldown() {
		return this.cooldown;
	}
}
