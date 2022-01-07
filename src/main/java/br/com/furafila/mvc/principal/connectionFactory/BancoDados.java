package br.com.furafila.mvc.principal.connectionFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.furafila.utils.StringConexao;

public class BancoDados {

	private static final Logger logger = LogManager.getLogger(BancoDados.class);
	
    private static final String REGEX = "AS\\s\\[[A-Z\\s_]*\\]";
	private static Statement sta;
    private static ResultSet rs;
    private static Connection con = null;
    private static final String DRIVER = "org.postgresql.Driver";
    private static String caminho; //Outro computador: jdbc:sqlserver://localhost:8089;databaseName=GSFIEO
    private static String usuario;
    private static String senha;

    /**
     * @throws ClassNotFoundException, SQLException, Exception, FileNotFoundException e IOException 
     * 
     *
     */
    private static void conexao() throws ClassNotFoundException, SQLException, Exception, FileNotFoundException, IOException {

        StringConexao conexao = new StringConexao();
        caminho = conexao.getCaminho();
        usuario = conexao.getUsuario();
        senha = conexao.getSenha();

        Class.forName(DRIVER);

        con = DriverManager.getConnection(caminho, usuario, senha);
        sta = con.createStatement();

    }

    public static void executaComando(String sql) throws ClassNotFoundException, SQLException, Exception {

        /* EXECUTA COMANDOS SEM RETORNO DE INFORMAÇÕES
         * EXEMPLOS: DELETE, INSERT, UPDATE E ETC...
         */
        try {
            conexao();

            sta.executeUpdate(sql.replaceAll(REGEX, ""));
        } catch (ClassNotFoundException | SQLException ex) {
        	logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            fecharConexoes();
        }

    }

    public static Integer inserirRetornaID(String sql) throws ClassNotFoundException, SQLException, Exception {
        /*
         *EFETUA A INSERÇÃO E RETORNA O CÓDIGO QUE FOI INSERIDO
         */
        Integer ID = 0;

        try {

            conexao();

            rs = sta.executeQuery(sql);

            while (rs.next()) {
                ID = Integer.parseInt(rs.getObject(1).toString());
            }

        } catch (ClassNotFoundException | SQLException | NumberFormatException ex) {
        	logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            fecharConexoes();

        }

        return ID;
    }

    public static Integer inserirImagem(String img) throws SQLException, FileNotFoundException, Exception, ClassNotFoundException {

        Integer imagemId = 0;
        try {

            conexao();

            
            if(StringUtils.isBlank(img)) {
            	return inserirRetornaID("INSERT INTO IMAGEM (imagem) VALUES(null) RETURNING id_imagem;");
            }
            
            File file = new File(img);
            FileInputStream fis = new FileInputStream(file);
            int len = (int) file.length();
            String query = "INSERT INTO IMAGEM (imagem) VALUES(?) RETURNING id_imagem;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setBinaryStream(1, fis, len);
            ResultSet rs = pstmt.executeQuery();

            if (rs != null && rs.next()) {
            	imagemId = rs.getInt(1);
            }

        } catch (ClassNotFoundException | SQLException | FileNotFoundException ex) {
        	logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            fecharConexoes();
        }

        return imagemId;

    }

    
	public static byte[] retornaImagem(String sql) throws SQLException, Exception {
		
        //RETORNA UM REGISTRO
		byte[] imagem = null;
        try {

            conexao();

            rs = sta.executeQuery(sql.replaceAll(REGEX, ""));

            int coluna = rs.getMetaData().getColumnCount();

            while (rs.next()) {

                for (int i = 0; i < coluna; i++) {
                	imagem = rs.getBytes((i + 1));

                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
        	logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            fecharConexoes();
        }

        return imagem;

	}
    
    public static void alterarImagem(String img, Integer codigo) throws SQLException, FileNotFoundException, Exception, ClassNotFoundException {

        try {

            conexao();

            PreparedStatement pstmt;
            File file = new File(img);
            FileInputStream fis = new FileInputStream(file);
            int len = (int) file.length();
            String query = "UPDATE IMAGEM SET imagem = ? WHERE id_imagem = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setBinaryStream(1, fis, len);
            pstmt.setInt(2, codigo);
            pstmt.executeUpdate();

        } catch (ClassNotFoundException | SQLException | FileNotFoundException ex) {
        	logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            fecharConexoes();
        }

    }

    public static List<List<String>> retorna_N_Registros(String sql) throws ClassNotFoundException, SQLException, Exception {

        // RETORNA VÁRIOS REGISTROS
        List<List<String>> lstRegistros = new ArrayList<>();
        try {
            List<String> lstValores = new ArrayList<>();

            conexao();

            rs = sta.executeQuery(sql.replaceAll(REGEX, ""));

            int coluna = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= coluna; i++) {

                    lstValores.add(rs.getString(i));
                }

                lstRegistros.add(lstValores);
                lstValores = new ArrayList<>();
            }

        } catch (ClassNotFoundException | SQLException ex) {
        	logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            fecharConexoes();
        }
        return lstRegistros;

    }

    
    public static List<String> retornaRegistro(String sql) throws ClassNotFoundException, SQLException, Exception {

        //RETORNA UM REGISTRO
        List<String> lstRegistros = new ArrayList<>();
        try {

            conexao();

            rs = sta.executeQuery(sql.replaceAll(REGEX, ""));

            int coluna = rs.getMetaData().getColumnCount();

            while (rs.next()) {

                for (int i = 0; i < coluna; i++) {
                    lstRegistros.add(rs.getString(i + 1));

                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
        	logger.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            fecharConexoes();
        }

        return lstRegistros;

    }

    
    private static void fecharConexoes() throws SQLException, Exception {

        fecharResultSet();
        fecharStatement();
        fecharConexaoBanco();

    }

    private static void fecharResultSet() throws SQLException, Exception {

        if (rs != null && rs.isClosed() == false) {
            rs.close();
        }

    }

    private static void fecharStatement() throws SQLException, Exception {

        if (sta.isClosed() == false) {
            sta.close();
        }

    }

    private static void fecharConexaoBanco() throws SQLException, Exception {

        if (con.isClosed() == false) {
            con.close();
        }

    }


}
