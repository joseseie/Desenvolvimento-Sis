/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.ModeloCidade;
import modelo.ModeloCliente;

public class ControleCliente {
    
    ConectaBanco conex = new ConectaBanco();
    ModeloCliente modCli = new ModeloCliente();
    int codBairro, codCidade, codTel; 
    String bairro, cidade, telefone;
    
    public void inserir(ModeloCliente mod){ 
        conex.conexao();
        buscaCod(mod.getBairro(), mod.getCidade(), mod.getTelefone());
        
        try{
            PreparedStatement pst = conex.conn.prepareStatement("insert into clientes (nome_cliente,endereco_cliente,rg_cliente,"
                    + "cpf_cliente,id_bairro)values(?,?,?,?,?)");
            pst.setString(1, mod.getNome());
            pst.setString(2,mod.getEndereco());
            pst.setString(3, mod.getRg());
            pst.setString(4, mod.getCpf());
            buscaCod(mod.getBairro(), mod.getCidade(), mod.getTelefone());
            pst.setInt(5, codBairro);
            pst.execute();
            
            conex.executaSQL("select * from telefone where numero_tel='"+mod.getTelefone()+"'");
            conex.rs.first();
            codTel = conex.rs.getInt("id_telefone");
            
            conex.executaSQL("select * from clientes where nome_cliente='"+ mod.getNome() +"'");
            conex.rs.first();
            int codCli = conex.rs.getInt("id_cliente");
            pst = conex.conn.prepareStatement("insert into itens_cli_tel(id_cliente,id_tel)values(?,?)");
            buscaCod(mod.getBairro(), mod.getCidade(), "");
            
            pst.setInt(1, codCli);
            pst.setInt(2, codTel);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com sucesso!");
            
        }  catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro na insercao de dados \nErro: "+erro);
        }
        
        conex.desconecta();
    }
    
    public void alterar(ModeloCliente mod){
        conex.conexao();
        
        try{
            conex.executaSQL("select * from telefone where numero_tel='"+mod.getTelefone()+"'");
            conex.rs.first();
            codTel = conex.rs.getInt("id_telefone");
            PreparedStatement pst = conex.conn.prepareStatement("update itens_cli_tel set id_tel=? where id_cliente=?");
            pst.setInt(1, codTel);
            pst.setInt(2, mod.getId());
            pst.execute();
            //Altera a tabela cliente
            
            pst = conex.conn.prepareStatement("update clientes set nome_cliente=?, endereco_cliente=?, rg_cliente=?,"
                    + "cpf_cliente=?, id_bairro=? where id_cliente=?");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getEndereco());
            pst.setString(3, mod.getRg());
            pst.setString(4, mod.getCpf());
            buscaCod(mod.getBairro(), mod.getCidade(), "");
            pst.setInt(5, codBairro);
            pst.setInt(6, codCidade);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Alteracao realizada com sucesso!");
            
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao alterar \nErro: "+e);
        }
    }
    
    public void excluir(ModeloCliente mod){ //Nao completo
        conex.conexao();
        
        try{
            PreparedStatement  pst = conex.conn.prepareStatement("delete from itens_cli_tel where id_cliente=?");
            pst.setInt(1, mod.getId());
            pst.execute();
            
            pst = conex.conn.prepareStatement("delete from clientes where id_cliente=?");
            pst.setInt(1, mod.getId());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Exclusao realizada com sucesso!");
            
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro na exclusao.\nErro: "+e);
        }
        conex.desconecta();
    }
    
    public ModeloCliente primeiro(){
        conex.conexao();
        try{
            conex.executaSQL("select * from clientes inner join itens_cli_tel on clientes.id_cliente = itens_cli_tel.id_cliente\n" +
"			inner join telefone on itens_cli_tel.id_tel= telefone.id_telefone\n" +
"           inner join bairro on clientes.id_bairro= bairro.id_bairro inner join cidade on bairro.id_cidade=cidade.id_cidade");
            conex.rs.first();
            modCli.setId(conex.rs.getInt("id_cliente"));
            modCli.setNome(conex.rs.getString("nome_cliente"));
            modCli.setEndereco(conex.rs.getString("endereco_cliente"));
            modCli.setRg(conex.rs.getString("rg_cliente"));
            modCli.setCpf(conex.rs.getString("cpf_cliente"));
            modCli.setBairro(conex.rs.getString("nome_bairro"));
            modCli.setCidade(conex.rs.getString("nome_cidades"));
            modCli.setTelefone(conex.rs.getString("numero_tel"));
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao selecionar a primeira acao: \n Erro:"+e);
        }
 //       conex.desconecta();
        return modCli;
    }
    
    public ModeloCliente ultimo(){
        conex.conexao();
        try{
            conex.executaSQL("select * from clientes inner join itens_cli_tel on clientes.id_cliente = itens_cli_tel.id_cliente\n" +
"			inner join telefone on itens_cli_tel.id_tel= telefone.id_telefone\n" +
"           inner join bairro on clientes.id_bairro= bairro.id_bairro inner join cidade on bairro.id_cidade=cidade.id_cidade");
            conex.rs.last();
            modCli.setId(conex.rs.getInt("id_cliente"));
            modCli.setNome(conex.rs.getString("nome_cliente"));
            modCli.setEndereco(conex.rs.getString("endereco_cliente"));
            modCli.setRg(conex.rs.getString("rg_cliente"));
            modCli.setCpf(conex.rs.getString("cpf_cliente"));
            modCli.setBairro(conex.rs.getString("nome_bairro"));
            modCli.setCidade(conex.rs.getString("nome_cidades"));
            modCli.setTelefone(conex.rs.getString("numero_tel"));
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao selecionar a ultima Opcao: \n Erro:"+e);
        }
  //      conex.desconecta();
        return modCli;
    }
    
    public ModeloCliente anterior(){
 //       conex.conexao();
        try{
            conex.rs.previous();
            modCli.setId(conex.rs.getInt("id_cliente"));
            modCli.setNome(conex.rs.getString("nome_cliente"));
            modCli.setEndereco(conex.rs.getString("endereco_cliente"));
            modCli.setRg(conex.rs.getString("rg_cliente"));
            modCli.setCpf(conex.rs.getString("cpf_cliente"));
            modCli.setBairro(conex.rs.getString("nome_bairro"));
            modCli.setCidade(conex.rs.getString("nome_cidades"));
            modCli.setTelefone(conex.rs.getString("numero_tel"));
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao selecionar anterior: \n Erro:"+e);
        }
 //       conex.desconecta();
        return modCli;
    }
    
      public ModeloCliente proximo(){
 //       conex.conexao();
        try{

            conex.rs.next();
            modCli.setId(conex.rs.getInt("id_cliente"));
            modCli.setNome(conex.rs.getString("nome_cliente"));
            modCli.setEndereco(conex.rs.getString("endereco_cliente"));
            modCli.setRg(conex.rs.getString("rg_cliente"));
            modCli.setCpf(conex.rs.getString("cpf_cliente"));
            modCli.setBairro(conex.rs.getString("nome_bairro"));
            modCli.setCidade(conex.rs.getString("nome_cidades"));
            modCli.setTelefone(conex.rs.getString("numero_tel"));
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao selecionar proximo: \n Erro:"+e);
        }
 //       conex.desconecta();
        return modCli;
    }
    
    public void buscaCod(String Bairro, String cidade, String tel){
//        conex.conexao();
        try{
            conex.executaSQL("select * from bairro where nome_bairro='"+Bairro+"'");
            conex.rs.first();
            codBairro = conex.rs.getInt("id_bairro");
            conex.executaSQL("select * from cidade where nome_cidades='"+cidade+"'");
            conex.rs.first();
            codCidade = conex.rs.getInt("id_cidade");
            conex.executaSQL("select * from telefone where numero_tel='"+tel+"'");
            conex.rs.first();
            codTel = conex.rs.getInt("id_telefone");
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro mostrar os codigos");
        }
  //      conex.desconecta();
    }
    
    public void buscaNomes(int codBairro, int codCid){
        try {
            conex.executaSQL("select * from bairro where id_bairro="+ codBairro);
            conex.rs.first();
           
            bairro = conex.rs.getString("nome_bairro");
            conex.executaSQL("select * from cidade where id_cidade="+codCid);
            conex.rs.first();
            cidade = conex.rs.getString("nome_cidades");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao selecionar os nomes: \nErro: "+e);
        }
        
    }
    
   
    
}
