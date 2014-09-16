package de.unisaarland.cs.st.pirates.group1.tests.testUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StreamHelper {
	public static InputStream asIS(String s) {
		return new ByteArrayInputStream(s.getBytes());
	}
}
