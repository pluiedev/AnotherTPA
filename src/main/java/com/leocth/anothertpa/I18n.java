package com.leocth.anothertpa;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;

public final class I18n {
	public static ResourceBundle localeBundle;
	public static ResourceBundle defBundle; //Default Bundle
	public static Locale locale;
	private static I18n instance;

	public I18n(String localeName) {
	    init(localeName);
    }
	public void init(String localeName) {
	    instance = this;
		switch (localeName.toLowerCase()) {
		case "en_us": locale = new Locale("en"); break;
		case "zh_cn": locale = new Locale("zh"); break;
		}
		defBundle = ResourceBundle.getBundle("i18n", new Locale("en"));
		try {
			localeBundle = ResourceBundle.getBundle("i18n", locale);
		} catch (MissingResourceException e) {
			AnotherTPA.logger.log(Level.WARNING, "Missing locale - Check and add locale file and run /reload");
			localeBundle = defBundle;
		}
		
	}
	
	private String g0(String key, String... objects) {
	    String localeData = MessageFormat.format(localeBundle.getString(key), objects);
		if (localeData != null) {
		    return localeData;
        } else {
		    return MessageFormat.format(defBundle.getString(key), objects);
        }
	}
	public static String g(String key, String... objects) {
	    return instance.g0(key, objects);
    }
}
