package br.com.furafila.mvc.cliente.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import br.com.furafila.mvc.cliente.service.ImagemApiService;
import br.com.furafila.mvc.imagem.dto.NovaImagemDTO;
import br.com.furafila.mvc.imagem.response.NovaImagemResponse;
import br.com.furafila.mvc.imagem.util.ImagemUrlConstants;

public class ImagemApiServiceImpl implements ImagemApiService {

	@Override
	public Long gravar(NovaImagemDTO novaImagemDTO) {

		FileDataBodyPart dataBody = new FileDataBodyPart("file", novaImagemDTO.getImagem(),
				MediaType.MULTIPART_FORM_DATA_TYPE);

		MultiPart multiPart = new MultiPart();
		multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
		multiPart.bodyPart(dataBody);

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(MultiPartFeature.class);
		Client client = ClientBuilder.newClient(clientConfig);

		NovaImagemResponse novaImagemResponse = client.target(ImagemUrlConstants.SAVE_IMAGE)
				.request(MediaType.MULTIPART_FORM_DATA).header("Accept", MediaType.APPLICATION_JSON)
				.post(Entity.entity(multiPart, javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA),
						NovaImagemResponse.class);

		return novaImagemResponse.getId();
	}

}
