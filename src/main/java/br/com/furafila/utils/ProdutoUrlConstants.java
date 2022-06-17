package br.com.furafila.utils;

public class ProdutoUrlConstants {

	public static final String BASE_URL = FuraFilaPropertiesUtils.getProperty("furafila.api.product.base-url");

	public static final String SAVE_PRODUCT = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.product.save"));

}
