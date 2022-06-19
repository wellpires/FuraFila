package br.com.furafila.utils;

public class ProdutoUrlConstants {

	public static final String BASE_URL = FuraFilaPropertiesUtils.getProperty("furafila.api.product.base-url");

	public static final String SAVE_PRODUCT = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.product.save"));

	public static final String EDIT_PRODUCT = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.product.edit"));

	public static final String TOGGLE_PRODUCT_STATUS = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.product.toggle-status"));

	public static final String LIST_ESTABLISHMENT_PRODUCTS = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.product.list-establishment-products"));

	public static final String EDIT_UNIT_PRICE = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.product.edit-unit-price"));

}
