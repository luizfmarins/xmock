package br.xmock.util;

import java.util.List;

public final class Assert {
	
	private Assert() {}
	
	public static <T> T assertOnlyOne(List<T> list) {
		org.junit.Assert.assertEquals(1, list.size());
		return list.get(0);
	}
}
