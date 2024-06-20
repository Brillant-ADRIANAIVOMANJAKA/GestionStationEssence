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
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;


public class ModifAchat extends javax.swing.JFrame {
    public static Connection conn = null;
    public static PreparedStatement pst = null;
    public static ResultSet rs = null;
    static String Tab;
    static String Tab2;
    private JTable TableAchat;
    private JTable TableProduit;
    private String D4;
   
    public ModifAchat() {
        initComponents();
        conn = Connexion.ConnexionDB.conn;
        GetnumProd();
        ReccuperationDoneee();
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

        public void ReccuperationDoneee() {
        AcceuilPrincipale Ac = new AcceuilPrincipale();
        Ac.ReccuperationTableAchat();
        try {
            String reccuperation = Ac.getTable4();
            String requete = "SELECT numAchat, numProd, nomClient, nbrLitre, dateAchat FROM achat WHERE numAchat = '" + reccuperation + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String D1 = rs.getString("numAchat");
                numAchat.setText(D1);
                numAchat.setEnabled(false);
                String D2 = rs.getString("numProd");
                numProd.setSelectedItem(D2);
                String D3 = rs.getString("nomClient");
                nomClient.setText(D3);
                D4 = rs.getString("nbrLitre");
                nbrLitre.setText(D4);
                Date D5 = rs.getDate("dateAchat");
                dateAchat.setDate(D5);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ReccuperationTableAchat() {
        try {
            int Row = TableAchat.getSelectedRow();
            this.Tab2 = (TableAchat.getModel().getValueAt(Row, 0).toString());
            String requete = "SELECT numAchat, numProd, nomClient, nbrLitre, dateAchat FROM achat WHERE numAchat = '" + Tab2 + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String B1 = rs.getString("numAchat");
                System.out.println(B1);
                String B2 = rs.getString("numProd");
                System.out.println(B2);
                String B3 = rs.getString("nomClient");
                System.out.println(B3);
                String B4 = rs.getString("nbrLitre");
                System.out.println(B4);
                String B5 = rs.getString("dateAchat");
                System.out.println(B5);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void AffichageTableAchat() {
        try {
            String requete = "SELECT  numAchat AS \"NUMERO ACHAT\", numProd AS \"NUMERO PRODUIT\", nomClient AS \"NOM CLIENT\", nbrLitre AS \"NOMBRE DE LITRE\", dateAchat AS \"DATE ACHAT\" FROM achat";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
//            TableuProduit.setModel(DbUtils.resultSetToTableModel(rs));
            TableAchat.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void AffichageTableProduit() {
        try {
            String requete = "SELECT numProd, design, stock FROM produit";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) TableProduit.getModel();
            model.setRowCount(0); // Réinitialiser le modèle de la table avant de l'afficher

            while (rs.next()) {
                String numProd = rs.getString("numProd");
                String design = rs.getString("design");
                int stock = rs.getInt("stock");
                model.addRow(new Object[]{numProd, design, stock});
            }
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
        
    int x, y;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        annuler = new javax.swing.JButton();
        modifier = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nomClient = new javax.swing.JTextField();
        numAchat = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        numProd = new javax.swing.JComboBox<>();
        dateAchat = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        nbrLitre = new javax.swing.JTextField();

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
        jLabel1.setText("MODIFIER ACHAT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
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

        modifier.setBackground(new java.awt.Color(53, 143, 197));
        modifier.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        modifier.setForeground(new java.awt.Color(255, 255, 255));
        modifier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buttonModifier.png"))); // NOI18N
        modifier.setBorder(null);
        modifier.setBorderPainted(false);
        modifier.setContentAreaFilled(false);
        modifier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modifier.setFocusPainted(false);
        modifier.setFocusable(false);
        modifier.setRequestFocusEnabled(false);
        modifier.setRolloverEnabled(false);
        modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierActionPerformed(evt);
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
                .addComponent(modifier)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(annuler, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jLabel3.setText(" Nom du client *");

        jLabel4.setText(" Date achat *");

        jLabel5.setText(" Numero produit *");

        jLabel6.setText(" Numero achat *");

        numProd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        numProd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        numProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numProdActionPerformed(evt);
            }
        });

        dateAchat.setDateFormatString("yyyy-MM-dd");

        jLabel7.setText(" Nombre du litre *");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nomClient)
                            .addComponent(numAchat)
                            .addComponent(numProd, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateAchat, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(nbrLitre, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(0, 0, Short.MAX_VALUE))
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
                        .addComponent(numAchat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numProd, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nomClient, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(nbrLitre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateAchat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(30, 30, 30)
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

    private void modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierActionPerformed
                String numAchatValue = numAchat.getText();
        String nomClientValue = nomClient.getText();
        String nbrLitreValue = nbrLitre.getText();
        
         if (!nomClientValue.matches("^[a-zA-Z]+$")) {
            JOptionPane.showMessageDialog(null, "Le champ Nom ne doit contenir que des lettres. Veuillez réessayer", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode en cas d'erreur
        }

        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcherNumAchat = pattern.matcher(numAchatValue);
        Matcher matcherNomClient = pattern.matcher(nomClientValue);
        Matcher matcherNbrLitre = pattern.matcher(nbrLitreValue);
        
        if (!nbrLitreValue.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Le champ nbrLitre ne doit contenir que des chiffres. Veuillez réessayer", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode en cas d'erreur
        }

        if (matcherNumAchat.find() || matcherNomClient.find() || matcherNbrLitre.find()) {
            JOptionPane.showMessageDialog(null, "Le caractères spéciaux n'est pas validée. Veuillez resailler", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode en cas d'erreur
        }

        String R1 = numAchat.getText();
        String R2 = numProd.getSelectedItem().toString();
        String R3 = nomClient.getText();
        String R4 = nbrLitre.getText();
//        String R5 = ((JTextField) dateAchat.getDateEditor().getUiComponent()).getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String d = sdf.format(dateAchat.getDate());

        System.out.println(R1);
        System.out.println(R2);
        System.out.println(R3);
        System.out.println(R4);
        System.out.println(d);

        AcceuilPrincipale Ac = new AcceuilPrincipale();
        Ac.ReccuperationTableAchat();

        String Reccuperations = Ac.getTable4();

        String requete = "UPDATE achat SET numAchat = '" + R1 + "', numProd = '" + R2 + "', nomClient = '" + R3 + "', nbrLitre = '" + R4 + "', dateAchat = '" + d + "' WHERE numAchat = '" + Reccuperations + "' ";
        try {
            pst = conn.prepareStatement(requete);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Modification avec sucess");

            // Mise à jour du stock dans la table PRODUIT
            String updateRequete = "UPDATE produit SET stock = (stock + ?) - ? WHERE numProd = ?";
            pst = conn.prepareStatement(updateRequete);
            pst.setString(1, D4);
            pst.setString(2, nbrLitre.getText());
            pst.setString(3, numProd.getSelectedItem().toString());
            pst.executeUpdate();

            AffichageTableAchat();
            AffichageTableProduit();

        } catch (Exception e) {
            System.out.println(e);
        }
        this.hide();

    }//GEN-LAST:event_modifierActionPerformed

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
            java.util.logging.Logger.getLogger(ModifAchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifAchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifAchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifAchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifAchat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annuler;
    private com.toedter.calendar.JDateChooser dateAchat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton modifier;
    private javax.swing.JTextField nbrLitre;
    private javax.swing.JTextField nomClient;
    private javax.swing.JTextField numAchat;
    private javax.swing.JComboBox<String> numProd;
    // End of variables declaration//GEN-END:variables

    void setAchat(JTable TableAchat) {
       this.TableAchat = TableAchat;
    }

    void setProduit(JTable TableProduit) {
       this.TableProduit = TableProduit;
    }
}
