package br.com.furafila.mvc.conjuntoLocker.business;

import java.util.List;

import br.com.furafila.mvc.cliente.model.Cliente;
import br.com.furafila.mvc.conjuntoLocker.model.ConjuntoLocker;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ConjuntoLockerBusiness {

	public int gravar(ConjuntoLocker conjuntoLocker) throws Exception {

		String strQuery = "INSERT INTO CONJUNTO_LOCKER(" + "conjunto_locker_desc," + "nro_localizacao," + "nroCep_FK"
				+ ") VALUES (" + "'" + conjuntoLocker.getConjuntoLockerDesc() + "'" + ","
				+ conjuntoLocker.getNroLocalizacao() + "," + conjuntoLocker.getLogradouro().getNroCep() + ")";

		BancoDados.executaComando(strQuery);

		List<String> conjuntoLockerId = BancoDados.retornaRegistro(String.format(
				"select * from conjunto_locker cl where  cl.conjunto_locker_desc = '%s' and cl.nro_localizacao = %d and nroCep_FK = %d;",
				conjuntoLocker.getConjuntoLockerDesc(), conjuntoLocker.getNroLocalizacao(),
				conjuntoLocker.getLogradouro().getNroCep()));

		return Integer.parseInt(conjuntoLockerId.get(0));

	}

	public void alterar(ConjuntoLocker conjuntoLocker) throws Exception {

		String strQuery = "UPDATE " + "CONJUNTO_LOCKER " + "SET " + "conjunto_locker_desc = '"
				+ conjuntoLocker.getConjuntoLockerDesc() + "'" + ",nroCep_FK = "
				+ conjuntoLocker.getLogradouro().getNroCep() + ",nro_localizacao = "
				+ conjuntoLocker.getNroLocalizacao() + " WHERE id_conjunto_locker = "
				+ conjuntoLocker.getIdConjuntoLocker();

		BancoDados.executaComando(strQuery);

	}

	public void alterarStatus(ConjuntoLocker conjuntoLocker) throws Exception {

		String strQuery = "UPDATE " + "CONJUNTO_LOCKER " + "SET " + "status = '" + conjuntoLocker.getStatusSQL() + "' "
				+ "WHERE id_conjunto_locker = " + conjuntoLocker.getIdConjuntoLocker();

		BancoDados.executaComando(strQuery);

	}

	public List<List<String>> listarConjuntoLockers() throws Exception {

		String strQuery = "select "
				+ "CL.id_conjunto_locker, "
				+ "CL.conjunto_locker_desc, "
				+ "CL.nro_localizacao, "
				+ "CL.nroCep_FK, "
				+ "l.latitude, "
				+ "l.longitude, "
				+ "tl.desc_tipo_logradouro, "
				+ "l.logradouro, "
				+ "b.desc_bairro, "
				+ "c.desc_cidade, "
				+ "u.sigla_uf,cl.status, "
				+ "(SELECT COUNT(L.id_locker) FROM LOCKER L WHERE L.id_conjunto_locker_FK = CL.id_conjunto_locker) "
				+ "from conjunto_locker cl "
				+ "inner join logradouro l on l.nrocep = cl.nrocep_fk  "
				+ "inner join tipo_logradouro tl on tl.id_tipo_logradouro = l.id_tipo_logradouro_fk  "
				+ "inner join bairro b on b.id_bairro = l.id_bairro_fk "
				+ "inner join cidade c on c.id_cidade =b.id_cidade_fk "
				+ "inner join uf u on u.id_uf = c.id_uf_fk  where cl.status = 1;";

		return BancoDados.retorna_N_Registros(strQuery);
	}

	public List<List<String>> listarLockers(ConjuntoLocker conjuntoLocker) throws Exception {

		String strQuery = "SELECT L.id_locker AS [COD_LOCKER]," + "L.locker_desc AS [DESC],"
				+ "(SELECT DL.id_dimensao_locker FROM DIMENSOES_LOCKER DL WHERE DL.id_dimensao_locker = L.id_dimensao_locker_FK) AS [CODIGO],"
				+ "(SELECT DL.dimensao_desc FROM DIMENSOES_LOCKER DL WHERE DL.id_dimensao_locker = L.id_dimensao_locker_FK) AS [TIPO],"
				+ "(SELECT S.id_status FROM STATUS S WHERE S.id_status = L.id_status_FK) AS [CD_STATUS],"
				+ "(SELECT S.status FROM STATUS S WHERE S.id_status = L.id_status_FK) AS [STATUS] FROM LOCKER L WHERE L.id_conjunto_locker_FK = "
				+ conjuntoLocker.getIdConjuntoLocker();

		return BancoDados.retorna_N_Registros(strQuery);

	}

	public List<List<String>> listarLockersPorConjunto(ConjuntoLocker conjuntoLocker) throws Exception {

		String strQuery = "SELECT L.id_locker AS [CODIGO]," + "L.locker_desc AS [DESC]," + "L.status AS [STATUS],"
				+ "(SELECT S.id_status FROM STATUS S WHERE S.id_status = L.id_status_FK) AS [CD_STATUS],"
				+ "(SELECT S.status FROM STATUS S WHERE S.id_status = L.id_status_FK) AS [DISPONIBILIZACAO],"
				+ "(SELECT D.id_dimensao FROM DIMENSAO D WHERE D.id_dimensao = L.id_dimensao_FK) AS [CD_DIMENSAO],"
				+ "(SELECT D.dimensao_desc FROM DIMENSAO D WHERE D.id_dimensao = L.id_dimensao_FK) AS [DIMENSAO_DESC],"
				+ "(SELECT D.altura FROM DIMENSAO D WHERE D.id_dimensao = L.id_dimensao_FK) AS [ALTURA],"
				+ "(SELECT D.largura FROM DIMENSAO D WHERE D.id_dimensao = L.id_dimensao_FK) AS [LARGURA],"
				+ "(SELECT D.profundidade FROM DIMENSAO D WHERE D.id_dimensao = L.id_dimensao_FK) AS [PROFUNDIDADE]"
				+ " FROM LOCKER L WHERE (L.id_status_FK = 1 OR L.id_status_FK = 2) AND L.id_conjunto_locker_FK = "
				+ conjuntoLocker.getIdConjuntoLocker();

		return BancoDados.retorna_N_Registros(strQuery);

	}

	public List<List<String>> listarLockersProximos(Cliente cliente) throws Exception {

		String strQuery = "SELECT " + "C.id_conjunto_locker," + " C.conjunto_locker_desc," + "P.id_locker,"
				+ "P.locker_desc," + "D.dimensao_desc," + "C.nroCep_FK " + "FROM CONJUNTO_LOCKER C"
				+ " INNER JOIN LOCKER P ON (P.id_conjunto_locker_FK = C.id_conjunto_locker) INNER JOIN"
				+ " DIMENSAO D ON (P.id_dimensao_FK = D.id_dimensao) "
				+ "WHERE P.id_status_FK = 2 ORDER BY ABS(C.nroCep_FK - " + cliente.getLogradouro().getNroCep()
				+ ") ASC";

		return BancoDados.retorna_N_Registros(strQuery);

	}

}
