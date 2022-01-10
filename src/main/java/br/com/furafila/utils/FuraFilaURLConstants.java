package br.com.furafila.utils;

public class FuraFilaURLConstants {

	private static String baseUrl = FuraFilaPropertiesUtils.getProperty("furafila.api.base-url");

	
	public static final String VALIDATE_CREDENTIALS = baseUrl.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.auth.validate-credentials"));
	public static final String CHECK_CREDENTIAL_DUPLICITY = baseUrl.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.credential.check-duplicity"));
	public static final String LIST_COURIERS = baseUrl.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.credential.list-couriers"));
	public static final String SAVE_CREDENTIAL = baseUrl.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.credential.save"));
	
	public static final String SEARCH_CEP = FuraFilaPropertiesUtils.getProperty("furafila.api.cep");


	public static final String GEO_CODE = FuraFilaPropertiesUtils.getProperty("furafila.api.google.geo-code");
	public static final String DISTANCE_MATRIX = FuraFilaPropertiesUtils.getProperty("furafila.api.google.distance-matrix");


	
	
}
