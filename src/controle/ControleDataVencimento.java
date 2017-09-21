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

/**
 *
 * @author Seie José
 */
public class ControleDataVencimento {
    ConectaBanco con = new ConectaBanco();
    
    public void salvarData(String  data){
        con.conexao();
        
        try {
            PreparedStatement pst = con.conn.prepareStatement("insert into vencimento(datavenc) values(?)");
            pst.setString(1, data);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Cadastrada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro na insercao da data\nErro: "+ex);
        }
    }
}
