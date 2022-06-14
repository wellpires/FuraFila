package br.com.furafila.mvc.imagem.util;

import br.com.furafila.utils.FuraFilaPropertiesUtils;

public class ImagemUrlConstants {

	private static String baseUrl = FuraFilaPropertiesUtils.getProperty("furafila.api.image.base-url");
	
	public static final String SAVE_IMAGE = baseUrl.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.image.save"));
	public static final String FIND_IMAGE_BY_ID = baseUrl.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.image.find-by-id"));
	public static final String EDIT_IMAGE = baseUrl.concat(FuraFilaPropertiesUtils.getProperty("furafila.api.image.edit"));

}
