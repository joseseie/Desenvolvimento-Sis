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
import modelo.ModeloParcelamentoVenda;

/**
 *
 * @author Seie José
 */
public class ControleParcelamentoVenda {
    
    ConectaBanco conex = new ConectaBanco();
    
    public void salvar(ModeloParcelamentoVenda mod){
        conex.conexao();
              
            try {
                PreparedStatement pst = conex.conn.prepareStatement("insert into parcela_venda (cod_venda,valor_venda,valor_total,valor_parce,"
                        + "numero_parcela,datavenc) values(?,?,?,?,?,?)");
                pst.setInt(1, mod.getCodVenda());
                pst.setFloat(2, mod.getValor_venda());
                pst.setFloat(3, mod.getValor_total());
                pst.setFloat(4, mod.getValorParcela());
                pst.setInt(5, mod.getNumeroParc());
                pst.setString(6, mod.getDataVenc());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Salvado com sucesso!");
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar as Parcelas!\nErro: "+ex);
            }
        conex.desconecta();
    }
        
    
}
