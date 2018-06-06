package com.dhruvitnaik.util;


import java.util.concurrent.atomic.AtomicLong;

public enum IdGenerator {

	INSTANCE;

	private static AtomicLong MAP_ID = new AtomicLong(0L);
	private static AtomicLong FILTER_ID = new AtomicLong(0L);
	private static AtomicLong PIN_ID = new AtomicLong(0L);

	IdGenerator() {}

	public Long getNextMapId() {
		return MAP_ID.incrementAndGet();
	}

	public Long getNextFilterId() {
		return FILTER_ID.incrementAndGet();
	}

	public Long getNextPinId() {
		return PIN_ID.incrementAndGet();
	}

}
