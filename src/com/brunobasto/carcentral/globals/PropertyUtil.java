package com.brunobasto.carcentral.globals;

import java.util.HashMap;
import java.util.Map;

public class PropertyUtil {

	public static synchronized void setProperty(String key, Object value) {
		_properties.put(key, value);
	}

	public static Object getProperty(String key) {
		return _properties.get(key);
	}

	private static Map<String, Object> _properties = new HashMap<String, Object>();

}
