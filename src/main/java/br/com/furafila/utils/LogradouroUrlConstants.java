package br.com.furafila.utils;

public class LogradouroUrlConstants {

	public static final String BASE_URL = FuraFilaPropertiesUtils.getProperty("furafila.api.address.base-url");

	public static final String SAVE_ADDRESS = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.address.save"));

	public static final String FIND_FULL_ADDRESS = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.address.find-full-address"));

}
