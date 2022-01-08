package br.com.furafila.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FuraFilaPropertiesUtils {

	private static final Logger logger = LogManager.getLogger(FuraFilaPropertiesUtils.class);

	private final static Properties properties;

	static {
		properties = new Properties();

		try {
			URL resource = Thread.currentThread().getContextClassLoader().getResource(".");

			File file;
			file = new File(resource.toURI());
			File[] listFiles = file.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".properties");
				}
			});

			Arrays.asList(listFiles).stream().forEach(item -> {
				try {
					properties.load(new FileInputStream(item));
				} catch (IOException e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
			});
		} catch (URISyntaxException uriEx) {
			logger.error(uriEx.getMessage(), uriEx);
		}

	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

}
