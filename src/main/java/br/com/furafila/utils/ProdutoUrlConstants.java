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

	public static final String CHECK_PRODUCT_TYPE_NAME_DUPLICITY = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.product.product-type.check-name-duplicity"));

	public static final String LIST_PRODUCT_TYPES = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.product.product-type.list-product-types"));

	public static final String SAVE_PRODUCT_TYPE = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.product.product-type.save"));

	public static final String EDIT_PRODUCT_TYPE = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.product.product-type.edit"));

	public static final String TOGGLE_PRODUCT_TYPE_STATUS = BASE_URL.concat(
			FuraFilaPropertiesUtils.getProperty("furafila.api.product.product-type.toggle-product-type-status"));

}
