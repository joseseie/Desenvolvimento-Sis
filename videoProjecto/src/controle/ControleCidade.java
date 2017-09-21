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
import modelo.ModeloCidade;

/**
 *
 * @author Seie José
 */
public class ControleCidade {
     ConectaBanco connCidade = new ConectaBanco();
     
    public void inserirCidade(ModeloCidade mod){
       
        connCidade.conexao();
        try {
            PreparedStatement pst = connCidade.conn.prepareStatement("insert into cidade(nome_cidades,id_provincia)values(?,?)");
            pst.setString(1, mod.getNome());
            pst.setInt(2,mod.getCod_provincia());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados armazenados com sucesso!");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro na inserção dos dados: "+ex);
        }
     //   connCidade.desconecta();
    }
    
    public void excluirCidade(ModeloCidade mod){
        connCidade.conexao();
         try {
             PreparedStatement pst = connCidade.conn.prepareStatement("delete from cidade where id_cidade=?");
             pst.setInt(1, mod.getCod());
             pst.execute();
             JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro na exclusão dos dados! Erro:\n"+ex);
         }
    }
    
    public void aterarCidade(ModeloCidade mod){
        connCidade.conexao();
  
         try {
             PreparedStatement pst = connCidade.conn.prepareStatement("update cidade set nome_cidades= ?, id_provincia=? where id_cidade= ?");
             pst.setString(1, mod.getNome());
             pst.setInt(2, mod.getCod_provincia());
             pst.setInt(3, mod.getCod());
             pst.execute();
             JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro na Alteracao");
         }
    }
    
}
