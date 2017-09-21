    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.Statement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Seie José
 */
public class ConectaBanco {
    
    public Statement stm; //responsavel por preparar e realizar pesquisas no banco de dados
    public PreparedStatement pstm;
    public ResultSet rs; // responsável por armazenar um resultado de uma pesquisa por statement
    private String driver = "org.postgresql.Driver"; //Responsável por identificar o serviço de banco de dados
    private String caminho = "jdbc:postgresql://localhost:5432/sistemavideoaula"; // responsavel setar o local de banco de dados
    private String usuario = "postgres";
    private String senha = "jo110893";
    public Connection conn; // responsável por realizar a conexão com o banco de dados

    public void conexao(){ // método responsavel por realizar a conexão com o banco
        try { //Tentativa inicial
            System.setProperty("jdbc.Drivers", driver); // seta a propriedade do driver de conexão
            conn = DriverManager.getConnection(caminho, usuario, senha); //realiza a conexão com o banco
  //          JOptionPane.showMessageDialog(null, "Conectado com sucesso!");  
        } catch (SQLException ex) { //Excessão
            Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro de conexão\nErro: "+ex.getMessage());
        }
    }
    public void executaSQL(String sql ){
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);

          
        } catch(SQLException ex ){
            JOptionPane.showMessageDialog(null, "Erro ao executar o metodo executaSQL: "+ex);
        }
    }
   
    
    public void desconecta(){ // Método para fechar a conexão com o banco de dados
       
        try {
            conn.close(); //Fecha a conexão
 //            JOptionPane.showMessageDialog(null, "Desconectado com sucesso!"); 
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão\nErro: "+ex.getMessage());
        }
        
        
    }


}
