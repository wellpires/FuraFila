package br.com.furafila.utils;

public class EstabelecimentoUrlConstants {

	public static final String BASE_URL = FuraFilaPropertiesUtils.getProperty("furafila.api.establishment.base-url");

	public static final String SAVE_ESTABLISHMENT = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.establishment.save"));

	public static final String FIND_ESTABLISHMENT_INITIAL_INFO = BASE_URL
			.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.establishment.find-initial-info"));

}