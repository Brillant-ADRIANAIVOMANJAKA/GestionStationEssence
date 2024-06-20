package vue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

public class ModifService extends javax.swing.JFrame {

    public static Connection conn = null;
    public static PreparedStatement pst = null;
    public static ResultSet rs = null;
    static String Tab5;
    private JTable TableService;

    public ModifService() {
        initComponents();
        conn = Connexion.ConnexionDB.conn;
        ReccuperationDonee();
    }

    public void ReccuperationDonee() {
        AcceuilPrincipale Pr = new AcceuilPrincipale();
        Pr.ReccuperationTableService();

        try {
            String reccuperation = Pr.getTable6();
            String requete = "SELECT numServ, service, prix FROM service WHERE numServ = '" + reccuperation + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String A1 = rs.getString("numServ");
                numServ.setText(A1);
                numServ.setEnabled(false);
                String A2 = rs.getString("service");
                Service.setText(A2);
                String A3 = rs.getString("prix");
                Prix.setText(A3);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void AffichageTableService() {
        try {
            String requete = "SELECT numServ AS \"NUMERO SERVICE\", service AS \"SERVICE\", prix AS \"PRIX\" FROM service";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            TableService.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ReccuperationTableService() {
        try {
            int Row = TableService.getSelectedRow();
            this.Tab5 = (TableService.getModel().getValueAt(Row, 0).toString());
            String requete = "SELECT numServ, service, prix FROM service WHERE numServ = '" + Tab5 + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
//                String B1 = rs.getString("id");
//                System.out.println(B1);
                String B2 = rs.getString("numServ");
                System.out.println(B2);
                String B3 = rs.getString("Service");
                System.out.println(B3);
                String B4 = rs.getString("Prix");
                System.out.println(B4);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getTable6() {
        return Tab5;
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
        jLabel5 = new javax.swing.JLabel();
        Prix = new javax.swing.JTextField();
        numServ = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Service = new javax.swing.JTextField();

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
        jLabel1.setText("JOUTER NOUVEAU SERVICE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
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
        ajouter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buttonModifier.png"))); // NOI18N
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

        jLabel3.setText(" Prix *");

        jLabel5.setText(" Service *");

        jLabel6.setText(" Numero service *");

        Service.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServiceActionPerformed(evt);
            }
        });

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
                            .addComponent(jLabel5))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Prix, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addComponent(numServ)
                            .addComponent(Service))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel6))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(numServ, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Service, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 6, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Prix, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void ServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ServiceActionPerformed

    private void ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterActionPerformed

        String numServValue = numServ.getText();
        String ServiceValue = Service.getText();
        String PrixValue = Prix.getText();

        if (!ServiceValue.matches("^[a-zA-Z]+$")) {
            JOptionPane.showMessageDialog(null, "Le champ Service ne doit contenir que des lettres. Veuillez réessayer", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode en cas d'erreur
        }

        if (!PrixValue.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Le champ Prix ne doit contenir que des chiffres. Veuillez réessayer", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode en cas d'erreur
        }

        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matchernumServ = pattern.matcher(numServValue);
        Matcher matcherService = pattern.matcher(ServiceValue);
        Matcher matcherPrix = pattern.matcher(PrixValue);

        if (matchernumServ.find() || matcherService.find() || matcherPrix.find()) {
            JOptionPane.showMessageDialog(null, "Les caractères spéciaux ne sont pas valides. Veuillez réessayer.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode en cas d'erreur
        }

        String R1 = numServ.getText();
        String R2 = Service.getText();
        String R3 = Prix.getText();

        System.out.println(R1);
        System.out.println(R2);
        System.out.println(R3);

        AcceuilPrincipale Pr = new AcceuilPrincipale();
        Pr.ReccuperationTableService();

        String Reccuperation = Pr.getTable4();

// Vérifier si le service existe déjà dans la table
        String serviceExistant = ""; // Variable pour stocker le service existant, le cas échéant
        String verificationRequete = "SELECT service FROM service WHERE service = ?";
        try {
            pst = conn.prepareStatement(verificationRequete);
            pst.setString(1, R2);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                serviceExistant = rs.getString("service");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if (!serviceExistant.equals("")) {
            int choix = JOptionPane.showConfirmDialog(null, "Le service existe déjà dans la base de données. Voulez-vous continuer et modifier le prix ?", "Service existant", JOptionPane.YES_NO_OPTION);
            if (choix == JOptionPane.YES_OPTION) { 
                String requete = "UPDATE service SET prix = ? WHERE service = ?";

                try {
                    pst = conn.prepareStatement(requete);
                    pst.setString(1, R3); 
                    pst.setString(2, R2); 
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Prix modifié avec succès");
                    AffichageTableService(); 
                    this.dispose();
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                return;
            }
        }


    }//GEN-LAST:event_ajouterActionPerformed

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
            java.util.logging.Logger.getLogger(ModifService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifService().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Prix;
    private javax.swing.JTextField Service;
    private javax.swing.JButton ajouter;
    private javax.swing.JButton annuler;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField numServ;
    // End of variables declaration//GEN-END:variables

    void setService(JTable TableService) {
        this.TableService = TableService;
    }
}
