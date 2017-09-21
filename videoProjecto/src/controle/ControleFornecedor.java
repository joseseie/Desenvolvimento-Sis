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
import modelo.ModeloForncedor;

/**
 *
 * @author Seie José
 */
public class ControleFornecedor {
    
    ConectaBanco conn = new ConectaBanco();
    ModeloForncedor mod = new ModeloForncedor();
    int codBairro;
    
    
    public void salvar(ModeloForncedor mod){
 //       conn.conexao();
        try {
            achaBairro(mod.getBairro());
            PreparedStatement pst = conn.conn.prepareStatement("insert into fornecedores(nome_fornecedor,endereco,id_bairro,cnpj_fornecedor)values(?,?,?,?)");
            pst.setString(1,  mod.getNome());
            pst.setString(2, mod.getEndereco());
            pst.setInt(3, codBairro);
            pst.setString(4, mod.getCNPJ());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na insercao do forncedor \nErro: "+ex);
        }
        conn.desconecta();
    }
   
    public void excluir(ModeloForncedor mod){
        conn.conexao();
        try {
            PreparedStatement pst = conn.conn.prepareStatement("delete from fornecedores where id_fornecedor=?");
            pst.setInt(1, mod.getId());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de exclusão! \nErro: "+ex);
        }
        
        conn.desconecta();
    }
    
    public void alterar(ModeloForncedor mod){
        achaBairro(mod.getBairro());
        conn.conexao();
        
        try {
            PreparedStatement pst = conn.conn.prepareStatement("update fornecedores set nome_fornecedor=?, endereco=?, id_bairro=?,cnpj_fornecedor=? where id_fornecedor=?");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getEndereco());
            pst.setInt(3, codBairro);
            pst.setString(4, mod.getCNPJ());
            pst.setInt(5, mod.getId());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Alteracao \nErro: "+ex);
        }
        
        
    }
    
    public void achaBairro(String bairro){
        conn.conexao();
        try {
           
           conn.executaSQL("select * from bairro where nome_bairro='"+bairro+"'");
           conn.rs.first();
           codBairro = conn.rs.getInt("id_bairro");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar codigo do bairro \nErro: "+ex);
        }
        
    }
    
    public ModeloForncedor primeiro(){
        conn.conexao();
        
        try{
            conn.executaSQL("select * from fornecedores inner join bairro on fornecedores.id_bairro=bairro.id_bairro inner join cidade on bairro.id_cidade= cidade.id_cidade inner join provincias on cidade.id_provincia=provincias.id_provincia");
            conn.rs.first();
            mod.setId(conn.rs.getInt("id_fornecedor"));
            mod.setNome(conn.rs.getString("nome_fornecedor"));
            mod.setEndereco(conn.rs.getString("endereco"));
            mod.setBairro(conn.rs.getString("nome_bairro"));
            mod.setCNPJ(conn.rs.getString("cnpj_fornecedor"));
            mod.setNomeCidade(conn.rs.getString("nome_cidades"));
            mod.setSigla_provincia(conn.rs.getString("sigla_provincia"));
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao buscar primeiro\nErro: "+e);
        }
 //       conn.desconecta();
        return mod;
    }
    
    public ModeloForncedor ultimo(){
        conn.conexao();
        
        try{
            conn.executaSQL("select * from fornecedores inner join bairro on fornecedores.id_bairro=bairro.id_bairro inner join cidade on bairro.id_cidade= cidade.id_cidade inner join provincias on cidade.id_provincia=provincias.id_provincia");
            conn.rs.last();
            mod.setId(conn.rs.getInt("id_fornecedor"));
            mod.setNome(conn.rs.getString("nome_fornecedor"));
            mod.setEndereco(conn.rs.getString("endereco"));
            mod.setBairro(conn.rs.getString("nome_bairro"));
            mod.setCNPJ(conn.rs.getString("cnpj_fornecedor"));
            mod.setNomeCidade(conn.rs.getString("nome_cidades"));
            mod.setSigla_provincia(conn.rs.getString("sigla_provincia"));
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao buscar primeiro\nErro: "+e);
        }
  //      conn.desconecta();
        return mod;
    }
    
     public ModeloForncedor anterior(){
  //      conn.conexao();
        
        try{
 //           conn.executaSQL("select * from fornecedores inner join bairro on fornecedores.id_bairro=bairro.id_bairro inner join cidade on bairro.id_cidade= cidade.id_cidade inner join provincias on cidade.id_provincia=provincias.id_provincia");
            conn.rs.previous();
            mod.setId(conn.rs.getInt("id_fornecedor"));
            mod.setNome(conn.rs.getString("nome_fornecedor"));
            mod.setEndereco(conn.rs.getString("endereco"));
            mod.setBairro(conn.rs.getString("nome_bairro"));
            mod.setCNPJ(conn.rs.getString("cnpj_fornecedor"));
            mod.setNomeCidade(conn.rs.getString("nome_cidades"));
            mod.setSigla_provincia(conn.rs.getString("sigla_provincia"));
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao buscar primeiro\nErro: "+e);
        }
 //       conn.desconecta();
        return mod;
    }
     
    public ModeloForncedor proximo(){
 //       conn.conexao();
        
        try{
 //           conn.executaSQL("select * from fornecedores inner join bairro on fornecedores.id_bairro=bairro.id_bairro inner join cidade on bairro.id_cidade= cidade.id_cidade inner join provincias on cidade.id_provincia=provincias.id_provincia");
            conn.rs.next();
            mod.setId(conn.rs.getInt("id_fornecedor"));
            mod.setNome(conn.rs.getString("nome_fornecedor"));
            mod.setEndereco(conn.rs.getString("endereco"));
            mod.setBairro(conn.rs.getString("nome_bairro"));
            mod.setCNPJ(conn.rs.getString("cnpj_fornecedor"));
            mod.setNomeCidade(conn.rs.getString("nome_cidades"));
            mod.setSigla_provincia(conn.rs.getString("sigla_provincia"));
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao buscar primeiro\nErro: "+e);
        }
   //     conn.desconecta();
        return mod;
    } 
    
}
