package vue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

import raven.toast.Notifications;


public class ModifEntree extends javax.swing.JFrame {
    public static Connection conn = null;
    public static PreparedStatement pst = null;
    public static ResultSet rs = null;
    private JTable TableEntree;
    private JTable TableProduit;
    static String Tab1;
    static String Tab;
    private String D3;
 
    public ModifEntree() {
        initComponents();
        conn = Connexion.ConnexionDB.conn;
        GetnumProd();
        ReccuperationDonee();
    }
    
     private void GetnumProd() {
        try {
            String Query = "SELECT numProd FROM produit";
            pst = conn.prepareStatement(Query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String B2 = rs.getString("numProd");
                numProd.addItem(B2);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
     
     
     public void ReccuperationDonee() {
        AcceuilPrincipale Ap = new AcceuilPrincipale();
        Ap.ReccuperationTableEntree();
        try {

            String reccuperation = Ap.getTable2();
            String requete = "SELECT numEntree, numProd, stockEntree, dateEntree FROM entree WHERE numEntree = '" + reccuperation + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String D1 = rs.getString("numEntree");
                numEntree.setText(D1);
                numEntree.setEnabled(false);
                String D2 = rs.getString("numProd");
                numProd.setSelectedItem(D2);
                D3 = rs.getString("stockEntree");
                stockEntree.setText(D3);
                System.out.println(D3);
                Date D5 = rs.getDate("dateEntree");
                dateEntree.setDate(D5);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void AffichageTableEntree() {
        try {
            String requete = "SELECT numEntree AS \"NUMERO ENTREE\", numProd AS \"NUMERO PRODUIT\", stockEntree AS \"STOCK ENTREE\", dateEntree AS \"DATE ENTREE\" FROM entree";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            TableEntree.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ReccuperationTableEntree() {
        try {
            int Row = TableEntree.getSelectedRow();
            this.Tab1 = (TableEntree.getModel().getValueAt(Row, 0).toString());
            String requete = "SELECT numEntree, numProd, stockEntree, dateEntree FROM entree WHERE numEntree = '" + Tab1 + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String C1 = rs.getString("numEntree");
                System.out.println(C1);
                String C2 = rs.getString("numProd");
                System.out.println(C2);
                String C3 = rs.getString("stockEntree");
                System.out.println(C3);
                String C4 = rs.getString("dateEntree");
                System.out.println(C4);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void AffichageTableProduit() {
        try {
            String requete = "SELECT numProd , design, stock FROM produit";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
//            TableuProduit.setModel(DbUtils.resultSetToTableModel(rs));
            TableProduit.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ReccuperationTableProduit() {
        try {
            int Row = TableProduit.getSelectedRow();
            this.Tab = (TableProduit.getModel().getValueAt(Row, 0).toString());
            String requete = "SELECT numProd, design, stock FROM produit WHERE numProd = '" + Tab + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String B2 = rs.getString("numProd");
                System.out.println(B2);
                String B3 = rs.getString("design");
                System.out.println(B3);
                String B4 = rs.getString("stock");
                System.out.println(B4);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

 int x , y;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        annuler = new javax.swing.JButton();
        Modifier = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        stockEntree = new javax.swing.JTextField();
        numEntree = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        numProd = new javax.swing.JComboBox<>();
        dateEntree = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel4.setMinimumSize(new java.awt.Dimension(0, 10));

        jPanel1.setBackground(new java.awt.Color(204, 153, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(671, 60));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MODIFIER NOUVEAU ENTREE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 153, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(0, 20));

        annuler.setBackground(new java.awt.Color(199, 80, 82));
        annuler.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        annuler.setForeground(new java.awt.Color(255, 255, 255));
        annuler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buttonAnnuler.png"))); // NOI18N
        annuler.setBorder(null);
        annuler.setBorderPainted(false);
        annuler.setContentAreaFilled(false);
        annuler.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        annuler.setDefaultCapable(false);
        annuler.setFocusPainted(false);
        annuler.setFocusable(false);
        annuler.setRequestFocusEnabled(false);
        annuler.setRolloverEnabled(false);
        annuler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                annulerMouseClicked(evt);
            }
        });

        Modifier.setBackground(new java.awt.Color(53, 143, 197));
        Modifier.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Modifier.setForeground(new java.awt.Color(255, 255, 255));
        Modifier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buttonModifier.png"))); // NOI18N
        Modifier.setBorder(null);
        Modifier.setBorderPainted(false);
        Modifier.setContentAreaFilled(false);
        Modifier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Modifier.setFocusPainted(false);
        Modifier.setFocusable(false);
        Modifier.setRequestFocusEnabled(false);
        Modifier.setRolloverEnabled(false);
        Modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(annuler)
                .addGap(18, 18, 18)
                .addComponent(Modifier)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(annuler, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jLabel3.setText(" Stock entree *");

        jLabel4.setText(" Date entree *");

        jLabel5.setText(" Numero produit *");

        jLabel6.setText(" Numero entree *");

        numProd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        numProd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        numProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numProdActionPerformed(evt);
            }
        });

        dateEntree.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stockEntree)
                    .addComponent(numEntree)
                    .addComponent(numProd, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateEntree, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(numEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numProd, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(stockEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx - x, yy - y);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void annulerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_annulerMouseClicked
        this.hide();
        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Annulé");
    }//GEN-LAST:event_annulerMouseClicked

    private void numProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numProdActionPerformed

    private void ModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierActionPerformed
        
        String numEntreeValue = numEntree.getText();
        String stockEntreeValue = stockEntree.getText();

        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcherNumEntree = pattern.matcher(numEntreeValue);
        Matcher matcherStockEntree = pattern.matcher(stockEntreeValue);
        
        if (!stockEntreeValue.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Le champ stockEntree ne doit contenir que des chiffres. Veuillez réessayer", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode en cas d'erreur
        }

        if (matcherNumEntree.find() || matcherStockEntree.find()) {
            JOptionPane.showMessageDialog(null, "Le caractères spéciaux n'est pas validée. Veuillez resailler", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode en cas d'erreur
        }

        String R1 = numEntree.getText();
        String R2 = numProd.getSelectedItem().toString();
        String R3 = stockEntree.getText();
//        String R5 = ((JTextField) dateAchat.getDateEditor().getUiComponent()).getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String d = sdf.format(dateEntree.getDate());

        System.out.println(R1);
        System.out.println(R2);
        System.out.println(R3);
        System.out.println(d);

        AcceuilPrincipale Ac = new AcceuilPrincipale();
        Ac.ReccuperationTableEntree();

        String Reccuperations = Ac.getTable2();

        String requete = "UPDATE entree SET numEntree = '" + R1 + "', numProd = '" + R2 + "', stockEntree = '" + R3 + "', dateEntree = '" + d + "' WHERE numEntree = '" + Reccuperations + "' ";
        try {
            pst = conn.prepareStatement(requete);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Modification avec sucess");

            // Mise à jour du stock dans la table PRODUIT
            String updateRequete = "UPDATE produit SET stock = (stock + ?) - ? WHERE numProd = ?";
            pst = conn.prepareStatement(updateRequete);
            pst.setString(1, stockEntree.getText());
            pst.setString(2, D3);
            pst.setString(3, numProd.getSelectedItem().toString());
            pst.executeUpdate();

            AffichageTableEntree();
            AffichageTableProduit();

        } catch (Exception e) {
            System.out.println(e);
        }
        this.hide();
    }//GEN-LAST:event_ModifierActionPerformed

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
            java.util.logging.Logger.getLogger(ModifEntree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifEntree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifEntree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifEntree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifEntree().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Modifier;
    private javax.swing.JButton annuler;
    private com.toedter.calendar.JDateChooser dateEntree;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField numEntree;
    private javax.swing.JComboBox<String> numProd;
    private javax.swing.JTextField stockEntree;
    // End of variables declaration//GEN-END:variables

    void setProd(JTable TableProduit) {
         this.TableProduit = TableProduit;
    }

    void setEntree(JTable TableEntree) {
        this.TableEntree = TableEntree;
    }
}
