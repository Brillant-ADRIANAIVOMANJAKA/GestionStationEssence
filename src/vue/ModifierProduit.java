package vue;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

public class ModifierProduit extends javax.swing.JFrame {

    public static Connection conn = null;
    public static PreparedStatement pst = null;
    public static ResultSet rs = null;
    static String Tab;
    private JTable TableProduit;

    public ModifierProduit() {
        initComponents();
        conn = Connexion.ConnexionDB.conn;
        ReccuperationDonee();
        init();
    }

    private void init() {
        numProd.putClientProperty(FlatClientProperties.STYLE, ""
                 + "showClearButton:true");
        numProd.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "numProd");
        design.putClientProperty(FlatClientProperties.STYLE, ""
                 + "showClearButton:true");
        design.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "design");
        stock.putClientProperty(FlatClientProperties.STYLE, ""
                 + "showClearButton:true");
        stock.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "stock");

    }
    
    public void ReccuperationDonee() {
        AcceuilPrincipale Pr = new AcceuilPrincipale();
        Pr.ReccuperationTableProduit();

        try {
            String reccuperation = Pr.getTable1();
            String requete = "SELECT numProd, design, stock FROM produit WHERE numProd = '" + reccuperation + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String A1 = rs.getString("numProd");
                numProd.setText(A1);
                String A2 = rs.getString("design");
                design.setText(A2);
                String A3 = rs.getString("stock");
                stock.setText(A3);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        annuler = new javax.swing.JButton();
        modifier = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        numProd = new javax.swing.JTextField();
        design = new javax.swing.JTextField();
        stock = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setType(java.awt.Window.Type.POPUP);

        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel7.setPreferredSize(new java.awt.Dimension(632, 521));

        jPanel8.setBackground(new java.awt.Color(255, 153, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(671, 60));
        jPanel8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel8MouseDragged(evt);
            }
        });
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel8MousePressed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 153, 255));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("MODIFIER PRODUIT");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 153, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(0, 20));

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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(227, Short.MAX_VALUE)
                .addComponent(annuler)
                .addGap(12, 12, 12)
                .addComponent(modifier)
                .addGap(20, 20, 20))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modifier, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(annuler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText(" deesign*");

        jLabel7.setText(" stock *");

        jLabel8.setText("numero produit *");

        numProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numProdKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numProd, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(design, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(numProd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(design, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    int x, y;

    private void annulerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_annulerMouseClicked
        this.hide();
        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Modification Annulé");
        AffichageTableProduit();
    }//GEN-LAST:event_annulerMouseClicked

    private void numProdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numProdKeyTyped
        char t = evt.getKeyChar();
        if (!(Character.isDigit(t) || (t == KeyEvent.VK_BACK_SPACE) || (t == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_numProdKeyTyped

    private void jPanel8MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseDragged
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx - x, yy - y);
    }//GEN-LAST:event_jPanel8MouseDragged

    private void jPanel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel8MousePressed

    private void modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierActionPerformed
        
        String numProdValue = numProd.getText();
        String DesignValue = design.getText();
        
        if (!DesignValue.matches("^[a-zA-Z]+$")) {
            JOptionPane.showMessageDialog(null, "Le champ Design ne doit contenir que des lettres. Veuillez réessayer", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode en cas d'erreur
        }

        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matchernumProd = pattern.matcher(numProdValue);
        Matcher matcherDesign = pattern.matcher(DesignValue);

        if (matchernumProd.find() || matcherDesign.find()) {
            JOptionPane.showMessageDialog(null, "Le caractère spécial n'est pas valide. Veuillez réessayer.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode en cas d'erreur
        }

        String R1 = numProd.getText();
        String R2 = design.getText();
        String R3 = stock.getText();

        System.out.println(R1);
        System.out.println(R2);
        System.out.println(R3);

        AcceuilPrincipale Pr = new AcceuilPrincipale();
        Pr.ReccuperationTableProduit();

        String Reccuperation = Pr.getTable1();

// Vérifier si le design existe déjà dans la table
        String designExistant = ""; // Variable pour stocker le design existant, le cas échéant
        String verificationRequete = "SELECT design FROM produit WHERE design = ?";
        try {
            pst = conn.prepareStatement(verificationRequete);
            pst.setString(1, R2);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                designExistant = rs.getString("design");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if (!designExistant.equals("")) {
            JOptionPane.showMessageDialog(null, "Le produit existe déjà dans la base de données. Veuillez choisir un autre Produit.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            return; // Arrête l'exécution de la méthode si le design existe déjà
        }

        String requete = "UPDATE produit SET numProd = '" + R1 + "', design = '" + R2 + "', stock = '" + R3 + "' WHERE numProd = '" + Reccuperation + "' ";
        try {
            pst = conn.prepareStatement(requete);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Modification avec succès");

            numProd.setText(""); // Valeur vide
            AffichageTableProduit();

        } catch (Exception e) {
            System.out.println(e);
        }
        this.hide();


    }//GEN-LAST:event_modifierActionPerformed

    public void ReccuperationTableProduit() {
        try {
            int Row = TableProduit.getSelectedRow();
            this.Tab = (TableProduit.getModel().getValueAt(Row, 0).toString());
            String requete = "SELECT numProd, design, stock FROM produit WHERE numProd = '" + Tab + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String B1 = rs.getString("numProd");
                System.out.println(B1);
                String B2 = rs.getString("design");
                System.out.println(B2);
                String B3 = rs.getString("stock");
                System.out.println(B3);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getTable1() {
        return Tab;
    }

    public void AffichageTableProduit() {
        try {
            String requete = "SELECT numProd, design, stock FROM produit";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            TableModel model = DbUtils.resultSetToTableModel(rs);
            TableProduit.setModel(model);

            // Désactiver l'édition des cellules individuelles
            TableProduit.setDefaultEditor(Object.class, null);

            // Empêcher la sélection de plusieurs lignes
            TableProduit.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            // Personnaliser le rendu des lignes
            TableProduit.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    if (row % 2 == 0) {
                        c.setBackground(new Color(255, 255, 255));
                    } else {
                        c.setBackground(new Color(255, 255, 255));
                    }

                    if (isSelected) {
                        c.setBackground(new Color(146, 185, 255)); // Couleur de fond de la sélection (bleu)
                    }
                    
                    ((DefaultTableCellRenderer) c).setHorizontalAlignment(SwingConstants.CENTER);

                    return c;
                }
            });

            JTableHeader Head = TableProduit.getTableHeader();
            Dimension dimension = Head.getPreferredSize();
            dimension.height = 35;
            Head.setPreferredSize(dimension);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifierProduit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annuler;
    private javax.swing.JTextField design;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton modifier;
    private javax.swing.JTextField numProd;
    private javax.swing.JTextField stock;
    // End of variables declaration//GEN-END:variables

    void setResp(JTable TableProduit) {
        this.TableProduit = TableProduit;
    }
}
