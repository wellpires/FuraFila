package br.com.furafila.utils;

public class EstoqueUrlConstants {

	public static final String BASE_URL = FuraFilaPropertiesUtils.getProperty("furafila.api.stock.base-url");
	public static final String STOCK_INCOMING = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.stock-incoming.save"));

}
