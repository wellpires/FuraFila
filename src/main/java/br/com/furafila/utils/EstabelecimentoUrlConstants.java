package br.com.furafila.utils;

public class EstabelecimentoUrlConstants {

	public static final String BASE_URL = FuraFilaPropertiesUtils.getProperty("furafila.api.establishment.base-url");

	public static final String SAVE_ESTABLISHMENT = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.establishment.save"));

}
