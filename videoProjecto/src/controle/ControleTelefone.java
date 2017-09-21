/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ModeloTelefone;

/**
 *
 * @author Seie José
 */
public class ControleTelefone {
    
    ConectaBanco connex = new ConectaBanco();
    ConectaBanco connexPesq = new ConectaBanco();
    ModeloTelefone modt = new  ModeloTelefone();
    
    
    public void inserir(ModeloTelefone mod ){
        connex.conexao();
        
        try{
            PreparedStatement pst = connex.conn.prepareStatement("insert into telefone(numero_tel)values(?)");
            pst.setString(1, mod.getTel());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Telefone Gravado com sucesso!");
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de gravação de telefone\nErro:  "+ex);
        }
        
    }
    
    public void alterar(ModeloTelefone mod){
        connex.conexao();
        PreparedStatement pst;
        
        try {
            pst = connex.conn.prepareStatement("updade telefone set numero_tel =? where id_telefone?");
            pst.setString(1, modt.getTel());
            pst.setInt(2, mod.getCod());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucessoos!");
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Dados alterados com sucessoos!");
        }
        
        connex.desconecta();     
    }
    
    public void excluir(ModeloTelefone mod ){
        connex.conexao();
        PreparedStatement pst;
        try{
            pst = connex.conn.prepareStatement("delete from telefone where id_telefone=?");
            pst.setInt(1, mod.getCod());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao excluir: \nERRO: "+ex);
            
        }        
        connex.desconecta();
    }
    
    public ModeloTelefone primeiro(){
        connex.conexao();
        connex.executaSQL("select * from telefone");
        try{
            connex.rs.first();
            modt.setCod(connex.rs.getInt("id_telefone"));
            modt.setTel(connex.rs.getString("numero_tel"));
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro "+ex);
        }
        connex.desconecta();
        return modt;
    }
    
        public ModeloTelefone ulltimo(){
        connex.conexao();
        connex.executaSQL("select * from telefone");
        try{
            connex.rs.last();
            modt.setCod(connex.rs.getInt("id_telefone"));
            modt.setTel(connex.rs.getString("numero_tel"));
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro "+ex);
        }
        connex.desconecta();
        return modt;
    }
    
        public ModeloTelefone anterior(){
        ModeloTelefone mod = new ModeloTelefone();
        connex.conexao();
        connexPesq.conexao();
        
  //      connex.executaSQL("select * from telefone ");
        try{
            connex.rs.previous();
            connexPesq.executaSQL("select * from telefone where id_telefone="+ connex.rs.getInt("id_telefone"));
            connexPesq.rs.first();
            
            modt.setCod(connex.rs.getInt("id_telefone"));
            modt.setTel(connex.rs.getString("numero_tel"));
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Nao foi possivel voltar\nErro, "+ex);
        }
        connex.desconecta();
        return modt;
        }
        
               
     public ModeloTelefone proximo(){
        ModeloTelefone mod = new ModeloTelefone();
        connex.conexao();
        connexPesq.conexao();
        
        try{
            connex.rs.next();
            connexPesq.executaSQL("select * from telefone where id_telefone="+ connex.rs.getInt("id_telefone"));
            connexPesq.rs.first();
            modt.setCod(connex.rs.getInt("id_telefone"));
            modt.setTel(connex.rs.getString("numero_tel"));
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro "+ex);
        }
        connex.desconecta();
        return modt;
    }   
    
    
}
