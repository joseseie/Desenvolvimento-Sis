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
import modelo.ModeloProduto;

public class ControleProduto {
    
    ModeloProduto mod = new ModeloProduto();
    ConectaBanco conexao = new ConectaBanco();
    ConectaBanco conexaoFornecedor  = new ConectaBanco();
    String nomeFornecedor;
    int codFornecedor;
    
    public void  inserirProduto(ModeloProduto mod){
        buscaCodigo(mod.getFornecedorProduto());
   //     conexao.conexao();
        try {
            PreparedStatement pst = conexao.conn.prepareStatement("insert into produto(nome_produto,preco_venda,preco_compra,quantidade,"
                    + "id_fornecedor)values(?,?,?,?,?)");
            pst.setString(1, mod.getNomeProduto());
            pst.setFloat(2, mod.getPrecoVenda());
            pst.setFloat(3, mod.getPrecoCompra());
            pst.setInt(4, mod.getQtdProduto());
            pst.setInt(5, codFornecedor);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o produto \nErro: "+ex);
        }
       conexao.desconecta();
    }
    
    public void editaProduto(ModeloProduto mod){
        buscaCodigo(mod.getFornecedorProduto());
        conexao.conexao();
        try {
            PreparedStatement pst = conexao.conn.prepareStatement("update produto set nome_produto=?,preco_venda=?,"
                    + "preco_compra=?,quantidade=?,id_fornecedor=? where id_produto=?");
            pst.setString(1, mod.getNomeProduto());
            pst.setFloat(2, mod.getPrecoVenda());
            pst.setFloat(3, mod.getPrecoCompra());
            pst.setInt(4, mod.getQtdProduto());
            pst.setInt(5, codFornecedor);
            pst.setInt(6, mod.getIdProduto());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Produto editado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao editar o produto \nErro: "+ex);
        }
    }
    
    public void excluirProduto(ModeloProduto mod){
        conexao.conexao();
        try {
            PreparedStatement pst = conexao.conn.prepareStatement("delete from produto where id_produto=?");
            pst.setInt(1, mod.getIdProduto());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o Produto!\nErro: "+e);
        }
    }
    
    public ModeloProduto buscaProduto(ModeloProduto modelo){
        conexao.conexao();
        conexao.executaSQL("select * from produto where nome_produto like '%"+modelo.getPesquisa()+"%'");
        try {
            conexao.rs.first();
            buscaNomeFornecedor(conexao.rs.getInt("id_fornecedor"));
            mod.setIdProduto(conexao.rs.getInt("id_produto"));
            mod.setNomeProduto(conexao.rs.getString("nome_produto"));
            mod.setPrecoCompra(conexao.rs.getFloat("preco_compra"));
            mod.setPrecoVenda(conexao.rs.getFloat("preco_venda"));
            mod.setQtdProduto(conexao.rs.getInt("quantidade"));
            mod.setFornecedorProduto(nomeFornecedor);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar registro! \nErro"+ex);
        }
        conexao.desconecta();
        return mod;
    }
    
    public void buscaNomeFornecedor(int cod){
        conexaoFornecedor.conexao();
        conexaoFornecedor.executaSQL("select * from fornecedores where id_fornecedor=" + cod +"");
        
         try {
            conexaoFornecedor.rs.first();
            nomeFornecedor = conexaoFornecedor.rs.getString("nome_fornecedor");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro ao buscar o codigo!n" + e);
        }
 //     conexaoFornecedor.desconecta();
    }
    
    public void buscaCodigo(String nome){
        conexao.conexao();
        conexao.executaSQL("select * from fornecedores where nome_fornecedor='"+nome+"'");
        try {
            conexao.rs.first();
            codFornecedor = conexao.rs.getInt("id_fornecedor");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o codigo\nErro: "+ex);
        }
        
    }
   
}
