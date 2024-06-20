package vue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

public class AjoutEntree extends javax.swing.JFrame {

    public static Connection conn = null;
    public static PreparedStatement pst = null;
    public static ResultSet rs = null;
    private JTable TableEntree;
    private JTable TableProduit;
    static String Tab1;
    static String Tab;

    public AjoutEntree() {
        initComponents();
        conn = Connexion.ConnexionDB.conn;
        GetnumProd();
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

    int x, y;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        annuler = new javax.swing.JButton();
        ajouter = new javax.swing.JButton();
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
        jLabel1.setText("JOUTER NOUVEAU ENTREE");

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

        ajouter.setBackground(new java.awt.Color(53, 143, 197));
        ajouter.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ajouter.setForeground(new java.awt.Color(255, 255, 255));
        ajouter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buttonAjouter.png"))); // NOI18N
        ajouter.setBorder(null);
        ajouter.setBorderPainted(false);
        ajouter.setContentAreaFilled(false);
        ajouter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ajouter.setFocusPainted(false);
        ajouter.setFocusable(false);
        ajouter.setRequestFocusEnabled(false);
        ajouter.setRolloverEnabled(false);
        ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterActionPerformed(evt);
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
                .addComponent(ajouter)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(annuler, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(jLabel4))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(stockEntree)
                            .addComponent(numEntree)
                            .addComponent(numProd, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateEntree, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))))
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
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterActionPerformed
        Date actuelle = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(actuelle);
        String dc = date;
        String numEntreeValue = numEntree.getText();
        String stockEntreeValue = stockEntree.getText();

        if (!stockEntreeValue.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Le champ stockEntree ne doit contenir que des chiffres. Veuillez réessayer", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode en cas d'erreur
        }

        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcherNumEntree = pattern.matcher(numEntreeValue);
        Matcher matcherStockEntree = pattern.matcher(stockEntreeValue);

        if (matcherNumEntree.find() || matcherStockEntree.find()) {
            JOptionPane.showMessageDialog(null, "Les caractères spéciaux ne sont pas valides. Veuillez réessayer", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode en cas d'erreur
        }

        if (numEntree.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ numéro");
        } else if (numProd.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez choisir un numéro");
        } else if (stockEntree.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ stock");
        } else if (((JTextField) dateEntree.getDateEditor().getUiComponent()).getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ date");
        } else {
            try {
                String numProdValue = numProd.getSelectedItem().toString();
                String dateEntreeValue = ((JTextField) dateEntree.getDateEditor().getUiComponent()).getText();

                // Vérifier si le numEntree existe déjà
                String requeteNumEntreeExistence = "SELECT COUNT(*) FROM entree WHERE numEntree = ?";
                pst = conn.prepareStatement(requeteNumEntreeExistence);
                pst.setString(1, numEntreeValue);
                ResultSet rsNumEntreeExistence = pst.executeQuery();
                rsNumEntreeExistence.next();
                int countNumEntreeExistence = rsNumEntreeExistence.getInt(1);

                if (countNumEntreeExistence > 0) {
                    JOptionPane.showMessageDialog(null, "Le numéro d'entrée existe déjà");
                } else {
                    // Insérer la nouvelle entrée
                    String requeteInsertion = "INSERT INTO entree (numEntree, numProd, stockEntree, dateEntree) VALUES (?, ?, ?, ?)";
                    pst = conn.prepareStatement(requeteInsertion);
                    pst.setString(1, numEntreeValue);
                    pst.setString(2, numProdValue);
                    pst.setString(3, stockEntreeValue);
                    pst.setString(4, dateEntreeValue);
                    pst.execute();

                    // Mise à jour du stock dans la table PRODUIT
                    String updateRequete = "UPDATE produit SET stock = stock + ? WHERE numProd = ?";
                    pst = conn.prepareStatement(updateRequete);
                    pst.setString(1, stockEntreeValue);
                    pst.setString(2, numProdValue);
                    pst.executeUpdate();

                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "AJOUT AVEC SUCCÈS");

                    AffichageTableEntree();
                    AffichageTableProduit();
                    this.dispose();
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }//GEN-LAST:event_ajouterActionPerformed

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
            String requete = "SELECT numProd, design, stock FROM produit";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) TableProduit.getModel();
            model.setRowCount(0); // Réinitialiser le modèle de la table avant de l'afficher

            while (rs.next()) {
                String numProd = rs.getString("numProd");
                String design = rs.getString("design");
                int stock = rs.getInt("stock");

//             Vérifier si le produit est gasoil, essence ou pétrole et si le stock est inférieur à 10 litres
                if (design.equalsIgnoreCase("gasoil") || design.equalsIgnoreCase("essence") || design.equalsIgnoreCase("petrole")) {
                    if (stock < 10) {
                        JOptionPane.showMessageDialog(null, "Alerte : Le produit " + design + " a moins de 10 litres de stock disponible !");
                    }
                }

                // Ajouter les données du produit à la table
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
//                String B1 = rs.getString("id");
//                System.out.println(B1);
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
            java.util.logging.Logger.getLogger(AjoutEntree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AjoutEntree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AjoutEntree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AjoutEntree.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AjoutEntree().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouter;
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

    void setEntree(JTable TableEntree) {
        this.TableEntree = TableEntree;
    }

    void setProduit(JTable TableProduit) {
        this.TableProduit = TableProduit;
    }
}
