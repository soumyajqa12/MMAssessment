package sj.cucumber.po.config;

import java.util.Properties;

public class AppConfig {
	
	private static AppConfig thisApp;
	private static final String FILE_NAME = "app.properties";
	private Properties props;
	
	private AppConfig() throws Exception {
		initProps();
	}
	
	private void initProps() throws Exception {
		props = new Properties();
		props.load(AppConfig.class.getClassLoader().getResourceAsStream((FILE_NAME)));
	
	}

	public static AppConfig getInstance() throws Exception {
		if(thisApp == null) {
			thisApp = new AppConfig();
		}
		return thisApp;
	}
	
	public String getProperty(String key) {
		return props.getProperty(key);
	}
	
	public String getProperty(String key, String defaultValue) {
		String value = props.getProperty(key);
		if(value == null) {
			return defaultValue;
		}
		return value;
	}

}
