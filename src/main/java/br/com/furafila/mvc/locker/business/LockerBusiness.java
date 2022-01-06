package br.com.furafila.mvc.locker.business;

import java.util.List;

import br.com.furafila.mvc.conjuntoLocker.model.ConjuntoLocker;
import br.com.furafila.mvc.locker.model.Locker;
import br.com.furafila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class LockerBusiness {

    public void gravar(Locker locker) throws Exception {

        String strQuery = "INSERT INTO LOCKER ("
                + "locker_desc,"
                + "id_conjunto_locker_FK,"
                + "id_dimensao_FK,"
                + "id_status_FK"
                + ") VALUES ("
                + "'" + locker.getLockerDesc() + "'"
                + "," + locker.getConjuntoLocker().getIdConjuntoLocker()
                + "," + locker.getDimensao().getIdDimensao()
                + "," + locker.getStatus().getIdStatus()
                + ")";

        BancoDados.executaComando(strQuery);
    }

    public void alterar(Locker locker) throws Exception {
        
        String strQuery = "UPDATE "
                + "LOCKER "
                + "SET "
                + "status = " + locker.getBolStatusSQL()
                + ",id_dimensao_FK = " + locker.getDimensao().getIdDimensao()
                + " WHERE id_locker = " + locker.getIdLocker();
        
        BancoDados.executaComando(strQuery);
        
    }
    
    public void alterarStatus(Locker locker) throws Exception {

        String strQuery = "UPDATE LOCKER SET id_status_FK = " + locker.getStatus().getIdStatus() + " WHERE id_locker = " + locker.getIdLocker();

        BancoDados.executaComando(strQuery);

    }

    public List<List<String>> buscarLockersPorVolume(Integer volumeTotal,ConjuntoLocker conjuntoLocker) throws Exception {
        String strQuery = "SELECT id_locker AS [COD],"
                + " locker_desc AS [DESC],"
                + " id_conjunto_locker_FK AS [CONJUNTO],"
                + " (SELECT D.id_dimensao FROM DIMENSAO D WHERE D.id_dimensao = L.id_dimensao_FK) AS [CD_DIMENSAO],"
                + " (SELECT D.altura FROM DIMENSAO D WHERE D.id_dimensao = L.id_dimensao_FK) AS [ALTURA],"
                + " (SELECT D.largura FROM DIMENSAO D WHERE D.id_dimensao = L.id_dimensao_FK) AS [LARGURA],"
                + " (SELECT D.profundidade FROM DIMENSAO D WHERE D.id_dimensao = L.id_dimensao_FK) AS [PROFUNDIDADE],"
                + " id_status_FK AS [STATUS]"
                + " FROM LOCKER L WHERE L.id_dimensao_FK IN"
                + " (SELECT D.id_dimensao FROM DIMENSAO D WHERE (D.altura * D.largura * D.profundidade) > " + volumeTotal.toString() + ")  "
                + "AND L.id_conjunto_locker_FK  = " + conjuntoLocker.getIdConjuntoLocker();

        return BancoDados.retorna_N_Registros(strQuery);
    }

}
