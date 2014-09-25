package de.unisaarland.cs.st.pirates.group1.tests.testUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class ReflectionHelper {
	
	public static void setPrivateField(Object o, String s, Object value) {
		try {
			Class<?> c = o.getClass();
			Field f = c.getDeclaredField(s);
			f.setAccessible(true);
			f.set(o, value);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
