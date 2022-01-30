package br.com.furafila.utils;

public class ClienteUrlConstants {

	public static final String BASE_URL = FuraFilaPropertiesUtils.getProperty("furafila.api.customer.base-url");

	public static final String SAVE_CUSTOMER = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.customer.save"));

}
