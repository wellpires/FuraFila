package br.com.furafila.utils;

public class PedidoUrlConstants {

	public static final String BASE_URL = FuraFilaPropertiesUtils.getProperty("furafila.api.order.base-url");
	public static final String LIST_APPROVED_ORDERS = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.order.list-approved-orders"));

}
