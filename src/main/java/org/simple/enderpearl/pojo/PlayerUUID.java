package org.simple.enderpearl.pojo;

import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerUUID {
	private final Player player;
	private final UUID uuid;

	public PlayerUUID(Player player) {
		this(player, player.getUniqueId());
	}

	public PlayerUUID(Player player, UUID uuid) {
		this.player = player;
		this.uuid = uuid;
	}

	@Override
	public boolean equals(Object o) {
		return !(o instanceof PlayerUUID) ? false : ((PlayerUUID) o).getUuid().equals(this.uuid);
	}

	public Player getPlayer() {
		return this.player;
	}

	public UUID getUuid() {
		return this.uuid;
	}
}
