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
import modelo.ModeloBaixaParcela;
import modelo.ModeloParcelamentoVenda;

/**
 *
 * @author Seie José
 */
public class ControleBaixaParcela {
    ConectaBanco conex = new ConectaBanco();
    
    public ModeloBaixaParcela buscaParcela(ModeloBaixaParcela mod){
        try {
        conex.conexao();
        conex.executaSQL("select * from parcela_venda where id_parc_venda="+mod.getCodParc());
        conex.rs.first();
        mod.setCodParc(conex.rs.getInt("id_parc_venda"));
        mod.setCodVenda(conex.rs.getInt("cod_venda"));
        mod.setDataVenc(conex.rs.getString("datavenc"));
        mod.setValor(conex.rs.getFloat("valor_parce"));
        JOptionPane.showMessageDialog(null,"Dados lidos  com sucesso!");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao buscar Parcela\n"+ ex);
        }
        
        
        conex.desconecta();
        return mod;
    }
    
    public void baixarParcela(ModeloBaixaParcela mod){
      
            conex.conexao();
            conex.executaSQL("select * from parcela_venda where id_parc_venda="+mod.getCodParc()+" and estado='PG'");
          try {   
            if(conex.rs.first()){
                JOptionPane.showMessageDialog(null, "esta parcela ja esta paga");
            }  else {
            
            try {
                
                PreparedStatement pst = conex.conn.prepareStatement("update parcela_Venda set estado=? where id_parc_venda=?");
                pst.setString(1, "PG");
                pst.setInt(2, mod.getCodParc());
                pst.execute();
                
                
                JOptionPane.showMessageDialog(null, "Parcela baixada com sucesso!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar");
            }
        }} catch (SQLException ex) {
            Logger.getLogger(ControleBaixaParcela.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
}