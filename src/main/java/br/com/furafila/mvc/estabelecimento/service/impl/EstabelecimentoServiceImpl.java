package br.com.furafila.mvc.estabelecimento.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.furafila.mvc.cliente.service.ImagemService;
import br.com.furafila.mvc.cliente.service.impl.ImagemServiceImpl;
import br.com.furafila.mvc.estabelecimento.builder.NovoEstabelecimentoDTOBuilder;
import br.com.furafila.mvc.estabelecimento.business.EstabelecimentoBusiness;
import br.com.furafila.mvc.estabelecimento.dto.EditarEstabelecimentoDTO;
import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoDTO;
import br.com.furafila.mvc.estabelecimento.dto.EstabelecimentoInformacoesIniciaisDTO;
import br.com.furafila.mvc.estabelecimento.dto.EstablishmentStatusDTO;
import br.com.furafila.mvc.estabelecimento.dto.NovoEstabelecimentoDTO;
import br.com.furafila.mvc.estabelecimento.dto.NovoUsuarioEstabelecimentoDTO;
import br.com.furafila.mvc.estabelecimento.function.EstabelecimentoDTO2EstabelecimentoFuncion;
import br.com.furafila.mvc.estabelecimento.model.Estabelecimento;
import br.com.furafila.mvc.estabelecimento.service.EstabelecimentoApiService;
import br.com.furafila.mvc.estabelecimento.service.EstabelecimentoService;
import br.com.furafila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;
import br.com.furafila.mvc.pedidos.model.Pedidos;
import br.com.furafila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstabelecimentoServiceImpl implements EstabelecimentoService {

	private EstabelecimentoBusiness estabelecimentoBusiness = new EstabelecimentoBusiness();

	private EstabelecimentoApiService estabelecimentoApiService = new EstabelecimentoApiServiceImpl();
	private ImagemService imagemService = new ImagemServiceImpl();

	@Override
	public List<Estabelecimento> listarEstabelecimentos() throws Exception {

		List<EstabelecimentoDTO> estabelecimentos = estabelecimentoApiService.listarEstabelecimento();

		return estabelecimentos.stream().map(new EstabelecimentoDTO2EstabelecimentoFuncion())
				.collect(Collectors.toList());

	}

	@Override
	public Estabelecimento buscarInformacaoEstabelecimento(Integer idEstabelecimento) throws Exception {

		EstabelecimentoDTO estabelecimentoDTO = estabelecimentoApiService.buscarEstabelecimento(idEstabelecimento);

		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setIdEstabelecimento(estabelecimentoDTO.getId().intValue());
		estabelecimento.setRazaoSocial(estabelecimentoDTO.getCorporateName());
		estabelecimento.setEmail(estabelecimentoDTO.getEmail());
		estabelecimento.setCnpj(Long.parseLong(estabelecimentoDTO.getCnpj()));
		estabelecimento.setInscricaoEstadual(Long.parseLong(estabelecimentoDTO.getStateRegistration()));
		estabelecimento.setStatus(estabelecimentoDTO.getStatus());
		estabelecimento.getImagem().setIdImagem(estabelecimentoDTO.getImageId().intValue());

		return estabelecimento;

	}

	@Override
	public List<Pedidos> listarEstabelecimentoMaisVendem() throws Exception {

		List<List<String>> lstDados = this.estabelecimentoBusiness.listarEstabelecimentoMaisVendem();
		List<Pedidos> lstEstabelecimentos = new ArrayList<>();

		if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
			for (List<String> lstValores : lstDados) {

				int index = 0;
				Pedidos pedido = new Pedidos();

				pedido.getComanda().getEstabelecimento().setRazaoSocial(lstValores.get(index++));
				pedido.setQtd(Integer.parseInt(lstValores.get(index++)));

				lstEstabelecimentos.add(pedido);
			}
		}

		return lstEstabelecimentos;

	}

	@Override
	public void gravar(Estabelecimento estabelecimento) {

		NovoEstabelecimentoDTO novoEstabelecimentoDTO = new NovoEstabelecimentoDTOBuilder()
				.corporateName(estabelecimento.getRazaoSocial()).email(estabelecimento.getEmail())
				.cnpj(estabelecimento.getCnpj()).stateRegistration(estabelecimento.getInscricaoEstadual())
				.idLogin(estabelecimento.getLoginId()).idImagem(estabelecimento.getImagem().getIdImagem()).build();

		this.estabelecimentoApiService.gravar(novoEstabelecimentoDTO);
	}

	@Override
	public Estabelecimento buscarInformacoesIniciaisEstabelecimento(EstabelecimentoLogin estabelecimentoLogin) {

		EstabelecimentoInformacoesIniciaisDTO estabelecimentoInformacoesIniciaisDTO = this.estabelecimentoApiService
				.buscarInformacoesIniciaisEstabelecimento(estabelecimentoLogin.getLogin().getIdLogin());

		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setIdEstabelecimento(estabelecimentoInformacoesIniciaisDTO.getIdEstablishment().intValue());
		estabelecimento.setRazaoSocial(estabelecimentoInformacoesIniciaisDTO.getCorporateName());
		estabelecimento.setStatus(estabelecimentoInformacoesIniciaisDTO.getStatus());
		estabelecimento.getImagem().setIdImagem(estabelecimentoInformacoesIniciaisDTO.getIdImage().intValue());
		estabelecimento.setEstoqueId(estabelecimentoInformacoesIniciaisDTO.getStockId());

		File imagem = imagemService.buscarImagem(estabelecimentoInformacoesIniciaisDTO.getIdImage());
		estabelecimento.getImagem().setImagem(imagem.getAbsolutePath());

		return estabelecimento;
	}

	@Override
	public void alterar(Estabelecimento estabelecimento) {

		EditarEstabelecimentoDTO editarEstabelecimentoDTO = new EditarEstabelecimentoDTO();
		editarEstabelecimentoDTO.setCorporateName(estabelecimento.getRazaoSocial());
		editarEstabelecimentoDTO.setEmail(estabelecimento.getEmail());

		this.estabelecimentoApiService.editarEstabelecimento(editarEstabelecimentoDTO,
				estabelecimento.getIdEstabelecimento());

	}

	@Override
	public void adicionarUsuarioEstabelecimento(EstabelecimentoLogin estabelecimentoLogin) {

		NovoUsuarioEstabelecimentoDTO novoUsuarioEstabelecimentoDTO = new NovoUsuarioEstabelecimentoDTO();
		novoUsuarioEstabelecimentoDTO
				.setEstablishmentId(Long.valueOf(estabelecimentoLogin.getEstabelecimento().getIdEstabelecimento()));
		novoUsuarioEstabelecimentoDTO.setLoginId(Long.valueOf(estabelecimentoLogin.getLogin().getIdLogin()));

		this.estabelecimentoApiService.adicionarUsuarioEstabelecimento(novoUsuarioEstabelecimentoDTO);

	}

	@Override
	public int verificarDuplicidadeRazaoSocial(Estabelecimento estabelecimento) throws Exception {
		return this.estabelecimentoBusiness.pegarRazaoSocial(estabelecimento).size();
	}

	@Override
	public int verificarDuplicidadeEmail(Estabelecimento estabelecimento) throws Exception {
		return this.estabelecimentoBusiness.pegarEmail(estabelecimento).size();
	}

	@Override
	public int verificarDuplicidadeCnpj(Estabelecimento estabelecimento) throws Exception {
		return this.estabelecimentoBusiness.pegarCnpj(estabelecimento).size();
	}

	@Override
	public int verificarDuplicidadeInscricaoEstadual(Estabelecimento estabelecimento) throws Exception {
		return this.estabelecimentoBusiness.pegarInscricaoEstadual(estabelecimento).size();
	}

	@Override
	public void alterarStatus(Estabelecimento estabelecimento) {

		EstablishmentStatusDTO establishmentStatusDTO = new EstablishmentStatusDTO();
		establishmentStatusDTO.setStatus(estabelecimento.getStatus());

		estabelecimentoApiService.alterarStatus(establishmentStatusDTO,
				estabelecimento.getIdEstabelecimento().longValue());

	}

}