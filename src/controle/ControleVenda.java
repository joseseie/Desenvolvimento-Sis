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
import modelo.ModeloVenda;

/**
 *
 * @author Seie José
 */
public class ControleVenda {
    
    ConectaBanco conexao = new ConectaBanco();
    int codProd, codCliente;
    String nomeCliente;
    
    
    public void adicionaItem(ModeloVenda mod){
       
             achaCodProduto(mod.getNomeProduto());
             conexao.conexao();
         try {
            PreparedStatement pst = conexao.conn.prepareStatement("insert into itens_venda__produto(id_venda,"
            + "id_produto,quantidade_produto)values(?,?,?)");
            pst.setInt(1, mod.getIdVenda());
            pst.setInt(2, codProd);
            pst.setInt(3, mod.getQtdItem());
            pst.execute();
//           Baixa no estoque (segundo Java Plugado!) 
            int quantidade = 0, resul = 0;
            conexao.executaSQL("select * from produto where nome_produto='"+mod.getNomeProduto()+"'");
            conexao.rs.first();
            quantidade = conexao.rs.getInt("quantidade");
            resul = quantidade - mod.getQtdItem();
            pst = conexao.conn.prepareStatement("update produto set quantidade=? where nome_produto=?");
            pst.setInt(1, resul);
            pst.setString(2, mod.getNomeProduto());
            pst.execute();
            
            JOptionPane.showMessageDialog(null,"Produto adicionado!");
            conexao.desconecta();
        } catch (SQLException ex) {
            conexao.desconecta();
            JOptionPane.showMessageDialog(null, "Erro ao Adicionar Item\nErro: "+ex);
        }
    }
    
    public void  achaCodProduto(String nome){
        try {
            conexao.conexao();
            conexao.executaSQL("select * from produto where nome_produto='"+nome+"'");
        
            conexao.rs.first();
            codProd =  conexao.rs.getInt("id_produto");
            conexao.desconecta();
        } catch (SQLException ex) {
            conexao.desconecta();
            JOptionPane.showMessageDialog(null,"Erro ao buscar o codigo: \n "+ex);
        }
    }
    
    public void fechaVenda(ModeloVenda mod){
        achaCliente(mod.getNomeCliente());
        conexao.conexao();
        try {
            PreparedStatement pst = conexao.conn.prepareStatement("update venda set data_venda=?, valor_venda=?, id_cliente=?,tipo_pagamento=? where id_venda=?");
            pst.setString(1, mod.getData());
            pst.setFloat(2, mod.getValorVenda());
            pst.setInt(3,codCliente);
            pst.setString(4, mod.getPagamento());
            pst.setInt(5, mod.getIdVenda());
           
            pst.execute();
            JOptionPane.showMessageDialog(null, "Venda Finalizada com sucesso!");
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a  venda\nErro: "+ex);
        }
        conexao.desconecta();
    }
    
    public void achaCliente(String nome){
        conexao.conexao();
        try {
            conexao.executaSQL("select * from clientes where nome_cliente='"+nome+"'");
            conexao.rs.first();
            codCliente = conexao.rs.getInt("id_cliente");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao achar o Cliente\nErro: "+ex);
        }
        conexao.desconecta();
            
    }
    
    public void cancelaVenda(){
        PreparedStatement pst;
        conexao.conexao();
        conexao.executaSQL("select * from venda inner join itens_venda__produto on venda.id_venda=itens_venda__produto.id_venda inner\n" +
"                join produto on itens_venda__produto.id_produto=produto.id_produto where valor_venda=0");
        
        try {
            conexao.rs.first();
            do{
               int qtdProduto = conexao.rs.getInt("quantidade");
               int qtdVenda = conexao.rs.getInt("quantidade_produto");
               int soma = qtdProduto + qtdVenda;  
               pst = conexao.conn.prepareStatement("update produto set quantidade=? where id_produto=?");
               pst.setInt(1, soma);
               pst.setInt(2, conexao.rs.getInt("id_produto"));
               pst.execute();
               pst = conexao.conn.prepareStatement("delete from itens_venda__produto where id_venda=?");
               pst.setInt(1, conexao.rs.getInt("id_venda"));
               pst.execute();
            }while(conexao.rs.next());
            pst = conexao.conn.prepareStatement("delete from venda where valor_venda=?");
            pst.setInt(1, 0);
            pst.execute();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"erro no cancelamento de Venda \nErro:" +ex);
        }
       
        conexao.desconecta();
    }
    
    
}
