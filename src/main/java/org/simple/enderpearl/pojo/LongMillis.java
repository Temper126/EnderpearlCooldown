package org.simple.enderpearl.pojo;

public class LongMillis {
	private final long millis;
	private final long duration;

	public LongMillis(long millis, long duration) {
		this.millis = millis;
		this.duration = duration;
	}

	public long getMillis() {
		return this.millis;
	}

	public long getDuration() {
		return this.duration;
	}

	@Override
	public String toString() {
		return "LongMillis{millis=" + this.millis + ", duration=" + this.duration + '}';
	}
}
