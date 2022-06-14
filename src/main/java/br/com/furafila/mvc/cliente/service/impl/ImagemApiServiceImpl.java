package br.com.furafila.mvc.cliente.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemNotFoundException;
import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.furafila.mvc.cliente.dto.EditarImagemDTO;
import br.com.furafila.mvc.cliente.exception.ImagemServerApiException;
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

		Response response = client.target(ImagemUrlConstants.SAVE_IMAGE).request(MediaType.MULTIPART_FORM_DATA)
				.header("Accept", MediaType.APPLICATION_JSON)
				.post(Entity.entity(multiPart, javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ImagemServerApiException(response.getStatusInfo());
		}

		return response.readEntity(NovaImagemResponse.class).getId();
	}

	@Override
	public File buscarImagem(Long idImage) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("imageId", idImage);

		String path = UriComponentsBuilder.fromHttpUrl(ImagemUrlConstants.FIND_IMAGE_BY_ID).buildAndExpand(param)
				.toUriString();

		Client client = ClientBuilder.newClient();
		Response response = client.target(path).request(MediaType.APPLICATION_OCTET_STREAM_TYPE).get();
		String fileName = response.getMetadata().get("Content-Disposition").get(0).toString();

		try {
			File tempFile = File.createTempFile("ff_", fileName);
			FileUtils.copyInputStreamToFile(response.readEntity(InputStream.class), tempFile);

			return tempFile;
		} catch (IOException e) {
			throw new FileSystemNotFoundException();
		}

	}

	@Override
	public void alterar(EditarImagemDTO editarImagemDTO, Integer idImagem) {

		HashMap<String, Object> param = new HashMap<>();
		param.put("imageId", idImagem);
		String path = UriComponentsBuilder.fromHttpUrl(ImagemUrlConstants.EDIT_IMAGE).buildAndExpand(param)
				.toUriString();

		FileDataBodyPart dataBody = new FileDataBodyPart("file", editarImagemDTO.getImagem(),
				MediaType.MULTIPART_FORM_DATA_TYPE);

		MultiPart multiPart = new MultiPart();
		multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
		multiPart.bodyPart(dataBody);

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(MultiPartFeature.class);
		Client client = ClientBuilder.newClient(clientConfig);

		Response response = client.target(path).request(MediaType.MULTIPART_FORM_DATA)
				.header("Accept", MediaType.APPLICATION_JSON)
				.put(Entity.entity(multiPart, javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA));

		if (Family.familyOf(response.getStatus()) != Family.SUCCESSFUL) {
			throw new ImagemServerApiException(response.getStatusInfo());
		}

	}

}
