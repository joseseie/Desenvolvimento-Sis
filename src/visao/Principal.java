/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import javax.sound.midi.SysexMessage;
import controle.ConectaBanco;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Seie José
 */
public class Principal extends javax.swing.JFrame {

    ConectaBanco conecta = new ConectaBanco();  //Variavel global
    /**
     * Creates new form Principal
     */
    public Principal(String user) {
        initComponents();
        
        // Comando  para Maximizar a tela!!
        this.setExtendedState(this.getExtendedState()| Principal.MAXIMIZED_BOTH);
        conecta.conexao();
        jLabelUser.setText(user);
        conecta.executaSQL("select * from vencimento");
        try {
            conecta.rs.last();
            char[] data = conecta.rs.getString("datavenc").toCharArray();
            jLabelLicenca.setText(data[0]+""+data[1]+"/"+data[2]+data[3]+"/"+data[4]+data[5]+data[6]+data[7]);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Principal() throws HeadlessException {
    }

  
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu4 = new javax.swing.JMenu();
        jLabelUser = new javax.swing.JLabel();
        jLabelUserTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelLicenca = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItemProvincias = new javax.swing.JMenuItem();
        jMenuItemCidades = new javax.swing.JMenuItem();
        jMenuItemBairro = new javax.swing.JMenuItem();
        jMenuItemTelefones = new javax.swing.JMenuItem();
        jMenuItemClientes = new javax.swing.JMenuItem();
        jMenuItemFornecedores = new javax.swing.JMenuItem();
        jMenuItemProdutos = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItemValidacao = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuLogOut = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Vendas");

        jLabelUser.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabelUserTitulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelUserTitulo.setText("Usuário: ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("A Licença do sistema inspira no dia : ");

        jLabelLicenca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jMenu1.setText("Cadastros");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Usuário");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItemProvincias.setText("Provincias");
        jMenuItemProvincias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProvinciasActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemProvincias);

        jMenuItemCidades.setText("Cidades");
        jMenuItemCidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCidadesActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCidades);

        jMenuItemBairro.setText("Bairro");
        jMenuItemBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBairroActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemBairro);

        jMenuItemTelefones.setText("Telefones");
        jMenuItemTelefones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTelefonesActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemTelefones);

        jMenuItemClientes.setText("Clientes");
        jMenuItemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientesActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemClientes);

        jMenuItemFornecedores.setText("Fornecedores");
        jMenuItemFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFornecedoresActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemFornecedores);

        jMenuItemProdutos.setText("Produtos");
        jMenuItemProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProdutosActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemProdutos);

        jMenuItem5.setText("Validacao do Sistema");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItemValidacao.setText("Liberação do Sistema");
        jMenuItemValidacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemValidacaoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemValidacao);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Venda");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem3.setText("Formulario Venda");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem2.setText("Pagar Parcela");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Relatórios");
        jMenuBar1.add(jMenu5);

        jMenuLogOut.setText("LogOut");
        jMenuLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuLogOutMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuLogOut);

        jMenu6.setText("Sair");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelLicenca, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(411, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jLabelUserTitulo)
                    .addContainerGap(1226, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(644, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelLicenca, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(643, Short.MAX_VALUE)
                    .addComponent(jLabelUserTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(21, 21, 21)))
        );

        setSize(new java.awt.Dimension(1366, 768));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        // TODO add your handling code here:
        conecta.desconecta();
        System.exit(0);
    }//GEN-LAST:event_jMenu6MouseClicked

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        new frmVenda().setVisible(true);
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
       
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenuLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuLogOutMouseClicked
       dispose();
       new frmLogin().setVisible(true);
    }//GEN-LAST:event_jMenuLogOutMouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       new frmBaixaParcela().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new frmVenda().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItemValidacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemValidacaoActionPerformed
        //     JOptionPane.showMessageDialog(null, "Clicou");
        try {
            conecta.executaSQL("select * from login where login='"+jLabelUser.getText()+"'");
            conecta.rs.first();
            if(conecta.rs.getString("permissao").equals("Desenvolvendor")){
                frmCadastroDataVencimento f = new frmCadastroDataVencimento();
                f.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Nao possui Permissao");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        //      conecta.desconecta();
    }//GEN-LAST:event_jMenuItemValidacaoActionPerformed

    private void jMenuItemProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProdutosActionPerformed
        new frmProduto().setVisible(true);
    }//GEN-LAST:event_jMenuItemProdutosActionPerformed

    private void jMenuItemFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFornecedoresActionPerformed
        new frmFornecedores().setVisible(true);
    }//GEN-LAST:event_jMenuItemFornecedoresActionPerformed

    private void jMenuItemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientesActionPerformed
        new frmClientes().setVisible(true);
    }//GEN-LAST:event_jMenuItemClientesActionPerformed

    private void jMenuItemTelefonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTelefonesActionPerformed
        new frmTelefones().setVisible(true);
    }//GEN-LAST:event_jMenuItemTelefonesActionPerformed

    private void jMenuItemBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBairroActionPerformed
        new frmBairro().setVisible(true);
    }//GEN-LAST:event_jMenuItemBairroActionPerformed

    private void jMenuItemCidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCidadesActionPerformed
        // TODO add your handling code here:
        new frmCidades().setVisible(true);
    }//GEN-LAST:event_jMenuItemCidadesActionPerformed

    private void jMenuItemProvinciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProvinciasActionPerformed
        // TODO add your handling code here:
        frmProvincias p = new frmProvincias();
        p.setVisible(true);
    }//GEN-LAST:event_jMenuItemProvinciasActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            conecta.executaSQL("select * from login where login='"+jLabelUser.getText()+"'");
            conecta.rs.first();
            if(conecta.rs.getString("permissao").equals("Administrador")){
                new frmCadLogin().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Você não possui permissao para acessar este módulo");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Você não possui permissao para acessar este módulo");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
       new frmValidaSistema().setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelLicenca;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JLabel jLabelUserTitulo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItemBairro;
    private javax.swing.JMenuItem jMenuItemCidades;
    private javax.swing.JMenuItem jMenuItemClientes;
    private javax.swing.JMenuItem jMenuItemFornecedores;
    private javax.swing.JMenuItem jMenuItemProdutos;
    private javax.swing.JMenuItem jMenuItemProvincias;
    private javax.swing.JMenuItem jMenuItemTelefones;
    private javax.swing.JMenuItem jMenuItemValidacao;
    private javax.swing.JMenu jMenuLogOut;
    // End of variables declaration//GEN-END:variables
}
