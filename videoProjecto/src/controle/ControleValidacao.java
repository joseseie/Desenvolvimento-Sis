/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Seie José
 */
public class ControleValidacao {
    
    ConectaBanco conn = new ConectaBanco();
    int valida;
    
    public void valida(String senha){
        conn.conexao();
        conn.executaSQL("select * from vencimento");
        try {
            conn.rs.last();
            valida = Integer.parseInt(conn.rs.getString("datavenc"));
            int operacao = (valida+132)/4;
            int senhaValidacao = Integer.parseInt(senha);
            
            if(operacao == senhaValidacao){
                int dia, mes, ano;
                
                
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date hoje = new Date();
                String data = df.format(hoje);
                
                char[] senhaChar = data.toCharArray();
                String AcertaMes, acertaDia , proximaSenha;
                dia = Integer.parseInt(""+senhaChar[0]+senhaChar[1]);
                mes = Integer.parseInt(""+senhaChar[3]+senhaChar[4]);
                ano = Integer.parseInt(""+senhaChar[6]+senhaChar[7]+senhaChar[8]+senhaChar[9]);
                
                if(mes < 12 ){
                    mes++;
                    if(mes < 10){
                        AcertaMes = "0"+mes;
                    } else {
                        AcertaMes = ""+mes;
                    }
                } else {
                    mes = 1;
                    ano++;
                    AcertaMes = "0"+mes;
                }
                if(dia < 10){
                    acertaDia = "0"+dia;
                } else {
                    acertaDia = ""+dia;
                }
                
                proximaSenha = acertaDia+AcertaMes+ano;
                PreparedStatement pst = conn.conn.prepareStatement("insert into vencimento (datavenc) values(?)");
                pst.setString(1, proximaSenha);
                pst.execute();
 //              JOptionPane.showMessageDialog(null, proximaSenha);
            } else {
                JOptionPane.showMessageDialog(null, "Senha Errada");
            }
            
 //           JOptionPane.showMessageDialog(null, valida);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro  ao validar\nErro: "+ex);
        }
        
        conn.desconecta();
    }
}
