package br.com.furafila.utils;

public class EstoqueUrlConstants {

	public static final String BASE_URL = FuraFilaPropertiesUtils.getProperty("furafila.api.stock.base-url");
	public static final String STOCK_INCOMING = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.stock-incoming.save"));
	public static final String STOCK_OUTGOING = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.stock-outgoing.save"));

	public static final String CHECK_ESTABLISHMENT_HAS_STOCK = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.stock.check-establishment-has-stock"));
	
	public static final String CREATE_STOCK = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.stock.create"));

}
