package it.ms.api.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperty {

	private static ApplicationProperty applicationProperty;
	private static String defPath = "/config.properties";

	private ApplicationProperty() {

	}

	private static ApplicationProperty get() {
		if (applicationProperty == null)
			applicationProperty = new ApplicationProperty();

		return applicationProperty;

	}

	public static ApplicationProperty setDefPath(String defPath) {
		ApplicationProperty.get();
		ApplicationProperty.defPath = defPath;
		return ApplicationProperty.applicationProperty;
	}

	public static String getValue(String key) {
		return ApplicationProperty.get().getPropertie(ApplicationProperty.defPath).getProperty(key);
	}

	public static Properties getProperties() {
		return ApplicationProperty.get().getPropertie(ApplicationProperty.defPath);
	}

	public static Properties getProperties(String path) {
		return ApplicationProperty.get().getPropertie(path);
	}

	private Properties getPropertie(String path) {
		String def = path == null ? ApplicationProperty.defPath : path;
		Properties p = null;
		try {
			InputStream in = ApplicationProperty.class.getResourceAsStream(def);
			p = new Properties();
			p.load(in);

		} catch (Exception e) {

		}
		return p;
	}

}
