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
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;


public class AjoutEntretien extends javax.swing.JFrame {
    public static Connection conn = null;
    public static PreparedStatement pst = null;
    public static ResultSet rs = null;
    private JTable TableEntretien;
    static String Tab3;

    public AjoutEntretien() {
        initComponents();
        conn = Connexion.ConnexionDB.conn;
        GetnumServ();
    }

    int x,y;
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
        immatri_voiture = new javax.swing.JTextField();
        numEntr = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        numServ = new javax.swing.JComboBox<>();
        dateEntretien = new com.toedter.calendar.JDateChooser();
        nomClient = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

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
        jLabel1.setText("JOUTER NOUVEAU ENTRETIEN");

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

        jLabel3.setText(" immatri-voiture *");

        jLabel4.setText(" Date entretien *");

        jLabel5.setText(" Numero service *");

        jLabel6.setText(" Numero entretien *");

        numServ.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        numServ.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        numServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numServActionPerformed(evt);
            }
        });

        dateEntretien.setDateFormatString("yyyy-MM-dd");

        jLabel7.setText(" Nom du client *");

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
                            .addComponent(immatri_voiture)
                            .addComponent(numEntr)
                            .addComponent(numServ, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateEntretien, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))))
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
                        .addComponent(numEntr, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numServ, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(immatri_voiture, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(nomClient, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateEntretien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
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

    private void numServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numServActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numServActionPerformed

    private void ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterActionPerformed
        Date actuelle = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(actuelle);
        String dc = date;

        String numEntrValue = numEntr.getText();
        String immatriValue = immatri_voiture.getText();
        String nomClientValue = nomClient.getText();
        
         if (!nomClientValue.matches("^[a-zA-Z]+$")) {
            JOptionPane.showMessageDialog(null, "Le champ Nom ne doit contenir que des lettres. Veuillez réessayer", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode en cas d'erreur
        }

        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcherNumEntr = pattern.matcher(numEntrValue);
        Matcher matcherImmatri = pattern.matcher(immatriValue);
        Matcher matcherNomClient = pattern.matcher(nomClientValue);

        if (matcherNumEntr.find() || matcherImmatri.find() || matcherNomClient.find()) {
            JOptionPane.showMessageDialog(null, "Les caractères spéciaux ne sont pas valides. Veuillez réessayer", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode en cas d'erreur
        }

        if (numEntr.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ numéro");
        } else if (numServ.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez choisir un numéro");
        } else if (immatri_voiture.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ immatriculation voiture");
        } else if (nomClient.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ nom");
        } else if (((JTextField) dateEntretien.getDateEditor().getUiComponent()).getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ date");
        } else {
            try {
//        String numEntrValue = numEntr.getText();
                String numServValue = numServ.getSelectedItem().toString();
//        String immatriValue = immatri_voiture.getText();
//        String nomClientValue = nomClient.getText();
                String dateEntretienValue = ((JTextField) dateEntretien.getDateEditor().getUiComponent()).getText();

                // Vérifier si le numEntr existe déjà
                String requeteNumEntrExistence = "SELECT COUNT(*) FROM entretien WHERE numEntr = ?";
                pst = conn.prepareStatement(requeteNumEntrExistence);
                pst.setString(1, numEntrValue);
                ResultSet rsNumEntrExistence = pst.executeQuery();
                rsNumEntrExistence.next();
                int countNumEntrExistence = rsNumEntrExistence.getInt(1);

                if (countNumEntrExistence > 0) {
                    JOptionPane.showMessageDialog(null, "Le numéro d'entretien existe déjà");
                } else {
                    // Insérer le nouvel entretien
                    String requeteInsertion = "INSERT INTO entretien (numEntr, numServ, Immatriculation_voiture, nomClient, dateEntretien) VALUES (?, ?, ?, ?, ?)";
                    pst = conn.prepareStatement(requeteInsertion);
                    pst.setString(1, numEntrValue);
                    pst.setString(2, numServValue);
                    pst.setString(3, immatriValue);
                    pst.setString(4, nomClientValue);
                    pst.setString(5, dateEntretienValue);
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "AJOUT AVEC SUCCÈS");

                    AffichageTableEntretien();
                    this.dispose();
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }//GEN-LAST:event_ajouterActionPerformed

        private void GetnumServ() {
        try {
            String Query = "SELECT numServ FROM service";
            pst = conn.prepareStatement(Query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String B2 = rs.getString("numServ");
                numServ.addItem(B2);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
        public void AffichageTableEntretien() {
        try {
            String requete = "SELECT numEntr AS \"NUMERO ENTRETIEN\", numServ AS \"NUMERO SERVICE\", Immatriculation_voiture AS \"IMMATRICULATION\", nomClient AS \"NOM CLIENT\", dateEntretien AS \"DATE ENTRETIEN\" FROM entretien";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            TableEntretien.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void ReccuperationTableEntretien() {
        try {
            int Row = TableEntretien.getSelectedRow();
            this.Tab3 = (TableEntretien.getModel().getValueAt(Row, 0).toString());
            String requete = "SELECT numEntr, numServ, Immatriculation_voiture,nomClient, dateEntretien FROM entretien WHERE numEntr = '" + Tab3 + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String C1 = rs.getString("numEntr");
                System.out.println(C1);
                String C2 = rs.getString("numServ");
                System.out.println(C2);
                String C3 = rs.getString("Immatri_voiture");
                System.out.println(C3);
                String C4 = rs.getString("nomClient");
                System.out.println(C4);
                String C5 = rs.getString("dateEntretien");
                System.out.println(C5);
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
            java.util.logging.Logger.getLogger(AjoutEntretien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AjoutEntretien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AjoutEntretien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AjoutEntretien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AjoutEntretien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouter;
    private javax.swing.JButton annuler;
    private com.toedter.calendar.JDateChooser dateEntretien;
    private javax.swing.JTextField immatri_voiture;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField nomClient;
    private javax.swing.JTextField numEntr;
    private javax.swing.JComboBox<String> numServ;
    // End of variables declaration//GEN-END:variables

    void setEntretien(JTable TableEntretien) {
       this.TableEntretien = TableEntretien;
    }
}
