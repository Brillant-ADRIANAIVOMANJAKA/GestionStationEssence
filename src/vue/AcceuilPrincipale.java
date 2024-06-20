package vue;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import raven.toast.Notifications;

public class AcceuilPrincipale extends javax.swing.JFrame {

    public static Connection conn = null;
    public static PreparedStatement pst = null;
    public static ResultSet rs = null;
    boolean a = true;
    static String Tab;
    static String Tab1;
    static String Tab2;
    static String Tab3;
    static String Tab5;
    static String B5;
    static String B3;
    public String type;
    public int count;

    public AcceuilPrincipale() {
        conn = Connexion.ConnexionDB.conn;
        initComponents();

        UIManager.put("OptionPane.yesButtonText", "Oui");
        UIManager.put("OptionPane.noButtonText", "Non");

        FlatAnimatedLafChange.showSnapshot();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
        FlatMacLightLaf.setup();

        this.setExtendedState(AcceuilPrincipale.MAXIMIZED_BOTH);
        AffichageTableProduit();
        AffichageTableEntretien();
        AffichageTableEntree();
        AffichageTableAchat();
        AffichageTableService();
        client_partitifs();
        Histogramme();
        Compt();
        init();
        applyTableStyle(TableEntretien);

        TableEntretien.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 16));
        TableEntretien.getTableHeader().setOpaque(false);
        TableEntretien.getTableHeader().setBackground(new Color(255,255,255));
        TableEntretien.getTableHeader().setForeground(new Color(0,0,0));

        TableEntree.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 16));
        TableEntree.getTableHeader().setOpaque(false);
        TableEntree.getTableHeader().setBackground(new Color(255,255,255));
        TableEntree.getTableHeader().setForeground(new Color(0,0,0));

        TableAchat.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 16));
        TableAchat.getTableHeader().setOpaque(false);
        TableAchat.getTableHeader().setBackground(new Color(255,255,255));
        TableAchat.getTableHeader().setForeground(new Color(0,0,0));

        TableProduit.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 16));
        TableProduit.getTableHeader().setOpaque(false);
        TableProduit.getTableHeader().setBackground(new Color(255,255,255));
        TableProduit.getTableHeader().setForeground(new Color(0,0,0));

        TableService.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 16));
        TableService.getTableHeader().setOpaque(false);
        TableService.getTableHeader().setBackground(new Color(255,255,255));
        TableService.getTableHeader().setForeground(new Color(0,0,0));


        setColor(JBoard);
        resetColor(JEntrant);
        resetColor(JSortant);
        resetColor(JResponsable);
        resetColor(JTraitement);
        resetColor(JArch);
        Jp1.setVisible(true);
        Jp2.setVisible(false);
        Jp3.setVisible(false);
        Jp4.setVisible(false);
        Jp5.setVisible(false);
        Jp7.setVisible(false);

    }

    private void init() {
        txtrecherche.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Recherche");
    }

    private void applyTableStyle(JTable TableCourrierEntrant) {

        TableCourrierEntrant.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        TableCourrierEntrant.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
    }

    void setColor(JPanel panel) {     
        panel.setBackground(new Color(255,153,255));
    }

    void resetColor(JPanel panel) {
        panel.setBackground(new Color(204,102,255));
    }

    public class ButtonDisabler {

        public static void disableAllButtons(Container container) {
            Component[] components = container.getComponents();
            for (Component component : components) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    button.setEnabled(false);
                } else if (component instanceof Container) {
                    // Récursion pour les conteneurs imbriqués
                    disableAllButtons((Container) component);
                }
            }
        }
    }

    //GlassPane
    public void setGlasspaness() {
        getRootPane().setGlassPane(new JComponent() {
            public void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        Header = new javax.swing.JPanel();
        iconminmaxclose = new javax.swing.JPanel();
        Buttonclose = new javax.swing.JPanel();
        CloseButton = new javax.swing.JLabel();
        Buttonmax = new javax.swing.JPanel();
        MaxButton = new javax.swing.JLabel();
        ButtonReduire = new javax.swing.JPanel();
        Reduire = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        Menuhide = new javax.swing.JPanel();
        ButtonHideMenu = new javax.swing.JLabel();
        JTraitement = new javax.swing.JPanel();
        Traite = new javax.swing.JLabel();
        JEntrant = new javax.swing.JPanel();
        Entrant = new javax.swing.JLabel();
        JBoard = new javax.swing.JPanel();
        Dashboar = new javax.swing.JLabel();
        JSortant = new javax.swing.JPanel();
        Sortant = new javax.swing.JLabel();
        JResponsable = new javax.swing.JPanel();
        Responsab = new javax.swing.JLabel();
        JArch = new javax.swing.JPanel();
        Archive = new javax.swing.JLabel();
        Dashboard = new javax.swing.JPanel();
        Jp1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        recette = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        Partititif = new javax.swing.JPanel();
        Panel1 = new javax.swing.JPanel();
        Jp2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        TableProduit = new javax.swing.JTable();
        AjoutProd = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        ModifProd = new javax.swing.JButton();
        SupProd = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Jp3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        scroll1 = new javax.swing.JScrollPane();
        TableEntretien = new javax.swing.JTable();
        AjoutEntretien = new javax.swing.JButton();
        btnrecherche = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        txtrecherche = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        ModifEntretien = new javax.swing.JButton();
        SupEntretien = new javax.swing.JButton();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel6 = new javax.swing.JLabel();
        Jp4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        scroll2 = new javax.swing.JScrollPane();
        TableEntree = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        modifEntree = new javax.swing.JButton();
        SupEntree = new javax.swing.JButton();
        AjoutEntree = new javax.swing.JButton();
        Jp5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        scroll3 = new javax.swing.JScrollPane();
        TableAchat = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        Modifachat = new javax.swing.JButton();
        SupAchat = new javax.swing.JButton();
        ajouterAchat = new javax.swing.JButton();
        Jp7 = new javax.swing.JPanel();
        Jp10 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        scroll5 = new javax.swing.JScrollPane();
        TableService = new javax.swing.JTable();
        jPanel26 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        ModifService = new javax.swing.JButton();
        SupService = new javax.swing.JButton();
        AjoutService = new javax.swing.JButton();
        hidemenu = new javax.swing.JPanel();
        setting = new javax.swing.JPanel();

        jRadioButton1.setText("jRadioButton1");

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        Header.setBackground(new java.awt.Color(234, 236, 239));
        Header.setPreferredSize(new java.awt.Dimension(800, 35));
        Header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                HeaderMouseDragged(evt);
            }
        });
        Header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HeaderMousePressed(evt);
            }
        });

        iconminmaxclose.setBackground(new java.awt.Color(234, 236, 239));
        iconminmaxclose.setPreferredSize(new java.awt.Dimension(150, 50));

        Buttonclose.setBackground(new java.awt.Color(234, 236, 239));
        Buttonclose.setPreferredSize(new java.awt.Dimension(50, 35));

        CloseButton.setBackground(new java.awt.Color(255, 255, 255));
        CloseButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CloseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-multiplier-50.png"))); // NOI18N
        CloseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CloseButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CloseButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ButtoncloseLayout = new javax.swing.GroupLayout(Buttonclose);
        Buttonclose.setLayout(ButtoncloseLayout);
        ButtoncloseLayout.setHorizontalGroup(
            ButtoncloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CloseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );
        ButtoncloseLayout.setVerticalGroup(
            ButtoncloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CloseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        Buttonmax.setBackground(new java.awt.Color(234, 236, 239));

        MaxButton.setBackground(new java.awt.Color(255, 255, 255));
        MaxButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-agrandir-30.png"))); // NOI18N
        MaxButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MaxButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MaxButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MaxButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ButtonmaxLayout = new javax.swing.GroupLayout(Buttonmax);
        Buttonmax.setLayout(ButtonmaxLayout);
        ButtonmaxLayout.setHorizontalGroup(
            ButtonmaxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ButtonmaxLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(MaxButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        ButtonmaxLayout.setVerticalGroup(
            ButtonmaxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MaxButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ButtonReduire.setBackground(new java.awt.Color(234, 236, 239));

        Reduire.setBackground(new java.awt.Color(255, 255, 255));
        Reduire.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reduire.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-moins-24.png"))); // NOI18N
        Reduire.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReduireMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ReduireMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ReduireMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ButtonReduireLayout = new javax.swing.GroupLayout(ButtonReduire);
        ButtonReduire.setLayout(ButtonReduireLayout);
        ButtonReduireLayout.setHorizontalGroup(
            ButtonReduireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonReduireLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Reduire, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ButtonReduireLayout.setVerticalGroup(
            ButtonReduireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Reduire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout iconminmaxcloseLayout = new javax.swing.GroupLayout(iconminmaxclose);
        iconminmaxclose.setLayout(iconminmaxcloseLayout);
        iconminmaxcloseLayout.setHorizontalGroup(
            iconminmaxcloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iconminmaxcloseLayout.createSequentialGroup()
                .addComponent(ButtonReduire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Buttonmax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Buttonclose, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        iconminmaxcloseLayout.setVerticalGroup(
            iconminmaxcloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iconminmaxcloseLayout.createSequentialGroup()
                .addGroup(iconminmaxcloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ButtonReduire, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Buttonmax, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Buttonclose, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("GESTION STATION ESSENCE");

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 614, Short.MAX_VALUE)
                .addComponent(iconminmaxclose, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addComponent(iconminmaxclose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(Header, java.awt.BorderLayout.PAGE_START);

        Menu.setPreferredSize(new java.awt.Dimension(270, 450));
        Menu.setLayout(new java.awt.BorderLayout());

        Menuhide.setBackground(new java.awt.Color(204, 102, 255));

        ButtonHideMenu.setBackground(new java.awt.Color(0, 51, 51));
        ButtonHideMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ButtonHideMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ButtonHideMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonHideMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButtonHideMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButtonHideMenuMouseExited(evt);
            }
        });

        JTraitement.setBackground(new java.awt.Color(204, 102, 255));

        Traite.setBackground(new java.awt.Color(204, 102, 255));
        Traite.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Traite.setForeground(new java.awt.Color(255, 255, 255));
        Traite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-achat-50.png"))); // NOI18N
        Traite.setText("     Achat");
        Traite.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Traite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TraiteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JTraitementLayout = new javax.swing.GroupLayout(JTraitement);
        JTraitement.setLayout(JTraitementLayout);
        JTraitementLayout.setHorizontalGroup(
            JTraitementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JTraitementLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Traite, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JTraitementLayout.setVerticalGroup(
            JTraitementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Traite, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JEntrant.setBackground(new java.awt.Color(204, 102, 255));

        Entrant.setBackground(new java.awt.Color(204, 102, 255));
        Entrant.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Entrant.setForeground(new java.awt.Color(255, 255, 255));
        Entrant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-entretien-30.png"))); // NOI18N
        Entrant.setText("     Entretien");
        Entrant.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Entrant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EntrantMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JEntrantLayout = new javax.swing.GroupLayout(JEntrant);
        JEntrant.setLayout(JEntrantLayout);
        JEntrantLayout.setHorizontalGroup(
            JEntrantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JEntrantLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Entrant, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JEntrantLayout.setVerticalGroup(
            JEntrantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Entrant, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        );

        JBoard.setBackground(new java.awt.Color(255, 153, 255));

        Dashboar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Dashboar.setForeground(new java.awt.Color(255, 255, 255));
        Dashboar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-tableau-de-bord.png"))); // NOI18N
        Dashboar.setText("      Tableau de bord");
        Dashboar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Dashboar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DashboarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DashboarMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DashboarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout JBoardLayout = new javax.swing.GroupLayout(JBoard);
        JBoard.setLayout(JBoardLayout);
        JBoardLayout.setHorizontalGroup(
            JBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Dashboar, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        JBoardLayout.setVerticalGroup(
            JBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Dashboar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JSortant.setBackground(new java.awt.Color(204, 102, 255));

        Sortant.setBackground(new java.awt.Color(204, 102, 255));
        Sortant.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Sortant.setForeground(new java.awt.Color(255, 255, 255));
        Sortant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-entrer-50.png"))); // NOI18N
        Sortant.setText("     Entree");
        Sortant.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Sortant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SortantMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JSortantLayout = new javax.swing.GroupLayout(JSortant);
        JSortant.setLayout(JSortantLayout);
        JSortantLayout.setHorizontalGroup(
            JSortantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JSortantLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Sortant, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JSortantLayout.setVerticalGroup(
            JSortantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Sortant, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JResponsable.setBackground(new java.awt.Color(204, 102, 255));

        Responsab.setBackground(new java.awt.Color(204, 102, 255));
        Responsab.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Responsab.setForeground(new java.awt.Color(255, 255, 255));
        Responsab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-kerosene-30.png"))); // NOI18N
        Responsab.setText("     Produits");
        Responsab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Responsab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResponsabMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JResponsableLayout = new javax.swing.GroupLayout(JResponsable);
        JResponsable.setLayout(JResponsableLayout);
        JResponsableLayout.setHorizontalGroup(
            JResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JResponsableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Responsab, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JResponsableLayout.setVerticalGroup(
            JResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Responsab, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JArch.setBackground(new java.awt.Color(204, 102, 255));

        Archive.setBackground(new java.awt.Color(204, 102, 255));
        Archive.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Archive.setForeground(new java.awt.Color(255, 255, 255));
        Archive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-service-30.png"))); // NOI18N
        Archive.setText("     Service");
        Archive.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Archive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ArchiveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JArchLayout = new javax.swing.GroupLayout(JArch);
        JArch.setLayout(JArchLayout);
        JArchLayout.setHorizontalGroup(
            JArchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JArchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Archive, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JArchLayout.setVerticalGroup(
            JArchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JArchLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Archive, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout MenuhideLayout = new javax.swing.GroupLayout(Menuhide);
        Menuhide.setLayout(MenuhideLayout);
        MenuhideLayout.setHorizontalGroup(
            MenuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTraitement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JEntrant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JSortant, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MenuhideLayout.createSequentialGroup()
                .addComponent(ButtonHideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(JArch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JResponsable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuhideLayout.setVerticalGroup(
            MenuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuhideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonHideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(JResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JSortant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JTraitement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JArch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JEntrant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(274, Short.MAX_VALUE))
        );

        Menu.add(Menuhide, java.awt.BorderLayout.CENTER);

        getContentPane().add(Menu, java.awt.BorderLayout.LINE_START);

        Dashboard.setBackground(new java.awt.Color(255, 255, 255));

        Jp1.setBackground(new java.awt.Color(235, 243, 232));
        Jp1.setPreferredSize(new java.awt.Dimension(646, 645));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(646, 61));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Tableau de bord");
        jPanel9.add(jLabel15, new java.awt.GridBagConstraints());

        jPanel20.setBackground(new java.awt.Color(235, 243, 232));
        jPanel20.setLayout(new java.awt.GridLayout(1, 4, 10, 0));

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        kGradientPanel3.setkEndColor(new java.awt.Color(204, 102, 255));
        kGradientPanel3.setkStartColor(new java.awt.Color(255, 153, 255));

        recette.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        recette.setForeground(new java.awt.Color(255, 255, 255));
        recette.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Resette total");

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap(244, Short.MAX_VALUE)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recette, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(244, Short.MAX_VALUE))
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(recette, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel20.add(jPanel29);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel21Layout = new java.awt.GridBagLayout();
        jPanel21Layout.columnWidths = new int[] {0, 15, 0, 15, 0, 15, 0};
        jPanel21Layout.rowHeights = new int[] {0};
        jPanel21.setLayout(jPanel21Layout);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 102, 255));
        jLabel10.setText("Statistique regroupée");
        jPanel21.add(jLabel10, new java.awt.GridBagConstraints());

        jPanel22.setBackground(new java.awt.Color(234, 236, 239));
        jPanel22.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        Partititif.setBackground(new java.awt.Color(255, 255, 255));
        Partititif.setLayout(new java.awt.BorderLayout());
        jPanel22.add(Partititif);

        Panel1.setBackground(new java.awt.Color(204, 51, 0));
        Panel1.setLayout(new java.awt.BorderLayout());
        jPanel22.add(Panel1);

        javax.swing.GroupLayout Jp1Layout = new javax.swing.GroupLayout(Jp1);
        Jp1.setLayout(Jp1Layout);
        Jp1Layout.setHorizontalGroup(
            Jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        Jp1Layout.setVerticalGroup(
            Jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jp1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        Jp2.setBackground(new java.awt.Color(234, 236, 239));

        jPanel1.setBackground(new java.awt.Color(234, 236, 239));

        scroll.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 236, 239), 1, true));
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        TableProduit.setBackground(new java.awt.Color(234, 236, 239));
        TableProduit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TableProduit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "numProd", "design", "stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableProduit.setFocusable(false);
        TableProduit.setGridColor(new java.awt.Color(204, 204, 204));
        TableProduit.setRowHeight(30);
        TableProduit.setShowGrid(false);
        TableProduit.setShowHorizontalLines(true);
        TableProduit.setSurrendersFocusOnKeystroke(true);
        TableProduit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProduitMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableProduitMouseReleased(evt);
            }
        });
        scroll.setViewportView(TableProduit);

        AjoutProd.setBackground(new java.awt.Color(51, 51, 51));
        AjoutProd.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        AjoutProd.setForeground(new java.awt.Color(255, 255, 255));
        AjoutProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-ajouter-20.png"))); // NOI18N
        AjoutProd.setText("ajouter");
        AjoutProd.setBorder(null);
        AjoutProd.setBorderPainted(false);
        AjoutProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AjoutProd.setFocusPainted(false);
        AjoutProd.setFocusable(false);
        AjoutProd.setRequestFocusEnabled(false);
        AjoutProd.setRolloverEnabled(false);
        AjoutProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(AjoutProd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 557, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(AjoutProd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(scroll)
                .addGap(45, 45, 45))
        );

        jPanel2.setBackground(new java.awt.Color(234, 236, 239));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        ModifProd.setBackground(new java.awt.Color(73, 154, 83));
        ModifProd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ModifProd.setForeground(new java.awt.Color(255, 255, 255));
        ModifProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Editer.png"))); // NOI18N
        ModifProd.setBorder(null);
        ModifProd.setBorderPainted(false);
        ModifProd.setContentAreaFilled(false);
        ModifProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ModifProd.setFocusPainted(false);
        ModifProd.setFocusable(false);
        ModifProd.setRequestFocusEnabled(false);
        ModifProd.setRolloverEnabled(false);
        ModifProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifProdActionPerformed(evt);
            }
        });
        jPanel2.add(ModifProd);

        SupProd.setBackground(new java.awt.Color(199, 85, 80));
        SupProd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SupProd.setForeground(new java.awt.Color(255, 255, 255));
        SupProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/supprimer.png"))); // NOI18N
        SupProd.setBorder(null);
        SupProd.setBorderPainted(false);
        SupProd.setContentAreaFilled(false);
        SupProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SupProd.setFocusPainted(false);
        SupProd.setFocusable(false);
        SupProd.setRequestFocusEnabled(false);
        SupProd.setRolloverEnabled(false);
        SupProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupProdActionPerformed(evt);
            }
        });
        jPanel2.add(SupProd);

        jPanel13.setPreferredSize(new java.awt.Dimension(0, 100));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setPreferredSize(new java.awt.Dimension(646, 61));
        jPanel14.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Liste des Produits");
        jPanel14.add(jLabel2, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout Jp2Layout = new javax.swing.GroupLayout(Jp2);
        Jp2.setLayout(Jp2Layout);
        Jp2Layout.setHorizontalGroup(
            Jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jp2Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(Jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                .addContainerGap())
        );
        Jp2Layout.setVerticalGroup(
            Jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp2Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(Jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jp2Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        Jp3.setBackground(new java.awt.Color(234, 236, 239));
        Jp3.setPreferredSize(new java.awt.Dimension(646, 580));

        jPanel4.setBackground(new java.awt.Color(234, 236, 239));

        scroll1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 236, 239), 1, true));

        TableEntretien.setBackground(new java.awt.Color(234, 236, 239));
        TableEntretien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TableEntretien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Numero entretien", "Numero service", "Immatriculation-voiture", "Nom du client", "Date entretien"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableEntretien.setFocusable(false);
        TableEntretien.setGridColor(new java.awt.Color(204, 204, 204));
        TableEntretien.setRowHeight(30);
        TableEntretien.setShowGrid(false);
        TableEntretien.setShowHorizontalLines(true);
        TableEntretien.setSurrendersFocusOnKeystroke(true);
        TableEntretien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableEntretienMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableEntretienMouseReleased(evt);
            }
        });
        scroll1.setViewportView(TableEntretien);

        AjoutEntretien.setBackground(new java.awt.Color(51, 51, 51));
        AjoutEntretien.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        AjoutEntretien.setForeground(new java.awt.Color(255, 255, 255));
        AjoutEntretien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-ajouter-20.png"))); // NOI18N
        AjoutEntretien.setText("ajouter");
        AjoutEntretien.setBorder(null);
        AjoutEntretien.setBorderPainted(false);
        AjoutEntretien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AjoutEntretien.setFocusPainted(false);
        AjoutEntretien.setFocusable(false);
        AjoutEntretien.setRequestFocusEnabled(false);
        AjoutEntretien.setRolloverEnabled(false);
        AjoutEntretien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AjoutEntretienMouseClicked(evt);
            }
        });

        btnrecherche.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        btnrecherche.setBorder(null);
        btnrecherche.setBorderPainted(false);
        btnrecherche.setContentAreaFilled(false);
        btnrecherche.setFocusPainted(false);
        btnrecherche.setFocusable(false);
        btnrecherche.setRequestFocusEnabled(false);
        btnrecherche.setRolloverEnabled(false);
        btnrecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrechercheActionPerformed(evt);
            }
        });

        txtrecherche.setBackground(new java.awt.Color(234, 236, 239));
        txtrecherche.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtrecherche.setForeground(new java.awt.Color(0, 0, 0));
        txtrecherche.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 236, 239), 1, true));
        txtrecherche.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtrecherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrechercheKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(AjoutEntretien, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnrecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtrecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AjoutEntretien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnrecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtrecherche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addComponent(scroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(234, 236, 239));
        jPanel5.setPreferredSize(new java.awt.Dimension(561, 36));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buttonDownload.png"))); // NOI18N
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1);

        ModifEntretien.setBackground(new java.awt.Color(73, 154, 83));
        ModifEntretien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ModifEntretien.setForeground(new java.awt.Color(255, 255, 255));
        ModifEntretien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Editer.png"))); // NOI18N
        ModifEntretien.setBorder(null);
        ModifEntretien.setBorderPainted(false);
        ModifEntretien.setContentAreaFilled(false);
        ModifEntretien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ModifEntretien.setFocusPainted(false);
        ModifEntretien.setFocusable(false);
        ModifEntretien.setRequestFocusEnabled(false);
        ModifEntretien.setRolloverEnabled(false);
        ModifEntretien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifEntretienActionPerformed(evt);
            }
        });
        jPanel5.add(ModifEntretien);

        SupEntretien.setBackground(new java.awt.Color(199, 85, 80));
        SupEntretien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SupEntretien.setForeground(new java.awt.Color(255, 255, 255));
        SupEntretien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/supprimer.png"))); // NOI18N
        SupEntretien.setBorder(null);
        SupEntretien.setBorderPainted(false);
        SupEntretien.setContentAreaFilled(false);
        SupEntretien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SupEntretien.setFocusPainted(false);
        SupEntretien.setFocusable(false);
        SupEntretien.setRequestFocusEnabled(false);
        SupEntretien.setRolloverEnabled(false);
        SupEntretien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupEntretienActionPerformed(evt);
            }
        });
        jPanel5.add(SupEntretien);

        kGradientPanel1.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Liste des entretien");
        kGradientPanel1.add(jLabel6, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout Jp3Layout = new javax.swing.GroupLayout(Jp3);
        Jp3.setLayout(Jp3Layout);
        Jp3Layout.setHorizontalGroup(
            Jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
            .addGroup(Jp3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Jp3Layout.setVerticalGroup(
            Jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp3Layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(45, 45, 45)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        Jp4.setBackground(new java.awt.Color(234, 236, 239));
        Jp4.setForeground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(234, 236, 239));

        scroll2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 236, 239), 1, true));

        TableEntree.setBackground(new java.awt.Color(234, 236, 239));
        TableEntree.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TableEntree.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Numero entree", "Numero produit", "Stock entree", "Date entree"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableEntree.setFocusable(false);
        TableEntree.setGridColor(new java.awt.Color(204, 204, 204));
        TableEntree.setRowHeight(30);
        TableEntree.setShowGrid(false);
        TableEntree.setShowHorizontalLines(true);
        TableEntree.setSurrendersFocusOnKeystroke(true);
        TableEntree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableEntreeMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableEntreeMouseReleased(evt);
            }
        });
        scroll2.setViewportView(TableEntree);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll2)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(scroll2, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setPreferredSize(new java.awt.Dimension(0, 61));
        jPanel19.setLayout(new java.awt.GridBagLayout());

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Liste des entrees");
        jPanel19.add(jLabel8, new java.awt.GridBagConstraints());

        jPanel8.setBackground(new java.awt.Color(234, 236, 239));
        jPanel8.setPreferredSize(new java.awt.Dimension(647, 36));
        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        modifEntree.setBackground(new java.awt.Color(73, 154, 83));
        modifEntree.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        modifEntree.setForeground(new java.awt.Color(0, 0, 0));
        modifEntree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Editer.png"))); // NOI18N
        modifEntree.setBorder(null);
        modifEntree.setBorderPainted(false);
        modifEntree.setContentAreaFilled(false);
        modifEntree.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modifEntree.setFocusPainted(false);
        modifEntree.setPreferredSize(new java.awt.Dimension(88, 32));
        modifEntree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifEntreeActionPerformed(evt);
            }
        });
        jPanel8.add(modifEntree);

        SupEntree.setBackground(new java.awt.Color(199, 85, 80));
        SupEntree.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SupEntree.setForeground(new java.awt.Color(255, 255, 255));
        SupEntree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/supprimer.png"))); // NOI18N
        SupEntree.setBorder(null);
        SupEntree.setBorderPainted(false);
        SupEntree.setContentAreaFilled(false);
        SupEntree.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SupEntree.setFocusPainted(false);
        SupEntree.setFocusable(false);
        SupEntree.setPreferredSize(new java.awt.Dimension(88, 32));
        SupEntree.setRequestFocusEnabled(false);
        SupEntree.setRolloverEnabled(false);
        SupEntree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupEntreeActionPerformed(evt);
            }
        });
        jPanel8.add(SupEntree);

        AjoutEntree.setBackground(new java.awt.Color(51, 51, 51));
        AjoutEntree.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        AjoutEntree.setForeground(new java.awt.Color(255, 255, 255));
        AjoutEntree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-ajouter-20.png"))); // NOI18N
        AjoutEntree.setText("ajouter");
        AjoutEntree.setBorder(null);
        AjoutEntree.setBorderPainted(false);
        AjoutEntree.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AjoutEntree.setFocusPainted(false);
        AjoutEntree.setFocusable(false);
        AjoutEntree.setRequestFocusEnabled(false);
        AjoutEntree.setRolloverEnabled(false);
        AjoutEntree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AjoutEntreeMouseClicked(evt);
            }
        });
        AjoutEntree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutEntreeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Jp4Layout = new javax.swing.GroupLayout(Jp4);
        Jp4.setLayout(Jp4Layout);
        Jp4Layout.setHorizontalGroup(
            Jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Jp4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Jp4Layout.createSequentialGroup()
                        .addComponent(AjoutEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Jp4Layout.setVerticalGroup(
            Jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jp4Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(AjoutEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        Jp5.setBackground(new java.awt.Color(234, 236, 239));

        jPanel10.setBackground(new java.awt.Color(234, 236, 239));

        scroll3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 236, 239), 1, true));

        TableAchat.setBackground(new java.awt.Color(234, 236, 239));
        TableAchat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TableAchat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Numero achat", "Numero produit", "Nom du client", "Nombre de litre", "Date achat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableAchat.setFocusable(false);
        TableAchat.setGridColor(new java.awt.Color(204, 204, 204));
        TableAchat.setRowHeight(30);
        TableAchat.setShowGrid(false);
        TableAchat.setShowHorizontalLines(true);
        TableAchat.setSurrendersFocusOnKeystroke(true);
        TableAchat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableAchatMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableAchatMouseReleased(evt);
            }
        });
        scroll3.setViewportView(TableAchat);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll3, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(scroll3, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setPreferredSize(new java.awt.Dimension(463, 61));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Liste des achats");
        jPanel12.add(jLabel11, new java.awt.GridBagConstraints());

        jPanel11.setBackground(new java.awt.Color(234, 236, 239));
        jPanel11.setPreferredSize(new java.awt.Dimension(494, 36));
        jPanel11.setLayout(new java.awt.GridLayout(1, 0));

        Modifachat.setBackground(new java.awt.Color(73, 154, 83));
        Modifachat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Modifachat.setForeground(new java.awt.Color(255, 255, 255));
        Modifachat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Editer.png"))); // NOI18N
        Modifachat.setBorder(null);
        Modifachat.setBorderPainted(false);
        Modifachat.setContentAreaFilled(false);
        Modifachat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Modifachat.setFocusPainted(false);
        Modifachat.setFocusable(false);
        Modifachat.setPreferredSize(new java.awt.Dimension(88, 32));
        Modifachat.setRequestFocusEnabled(false);
        Modifachat.setRolloverEnabled(false);
        Modifachat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifachatActionPerformed(evt);
            }
        });
        jPanel11.add(Modifachat);

        SupAchat.setBackground(new java.awt.Color(199, 85, 80));
        SupAchat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SupAchat.setForeground(new java.awt.Color(255, 255, 255));
        SupAchat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/supprimer.png"))); // NOI18N
        SupAchat.setBorder(null);
        SupAchat.setBorderPainted(false);
        SupAchat.setContentAreaFilled(false);
        SupAchat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SupAchat.setFocusPainted(false);
        SupAchat.setFocusable(false);
        SupAchat.setPreferredSize(new java.awt.Dimension(88, 32));
        SupAchat.setRequestFocusEnabled(false);
        SupAchat.setRolloverEnabled(false);
        SupAchat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupAchatActionPerformed(evt);
            }
        });
        jPanel11.add(SupAchat);

        ajouterAchat.setBackground(new java.awt.Color(51, 51, 51));
        ajouterAchat.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        ajouterAchat.setForeground(new java.awt.Color(255, 255, 255));
        ajouterAchat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-ajouter-20.png"))); // NOI18N
        ajouterAchat.setText("ajouter");
        ajouterAchat.setBorder(null);
        ajouterAchat.setBorderPainted(false);
        ajouterAchat.setFocusPainted(false);
        ajouterAchat.setFocusable(false);
        ajouterAchat.setRequestFocusEnabled(false);
        ajouterAchat.setRolloverEnabled(false);
        ajouterAchat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterAchatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Jp5Layout = new javax.swing.GroupLayout(Jp5);
        Jp5.setLayout(Jp5Layout);
        Jp5Layout.setHorizontalGroup(
            Jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Jp5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Jp5Layout.createSequentialGroup()
                        .addComponent(ajouterAchat, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Jp5Layout.setVerticalGroup(
            Jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp5Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(ajouterAchat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        Jp7.setBackground(new java.awt.Color(255, 255, 255));

        Jp10.setBackground(new java.awt.Color(234, 236, 239));

        jPanel25.setBackground(new java.awt.Color(234, 236, 239));

        scroll5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 236, 239), 1, true));

        TableService.setBackground(new java.awt.Color(234, 236, 239));
        TableService.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TableService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Numero Service", "Service", "Prix"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableService.setFocusable(false);
        TableService.setGridColor(new java.awt.Color(204, 204, 204));
        TableService.setRowHeight(30);
        TableService.setShowGrid(false);
        TableService.setShowHorizontalLines(true);
        TableService.setSurrendersFocusOnKeystroke(true);
        TableService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableServiceMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableServiceMouseReleased(evt);
            }
        });
        scroll5.setViewportView(TableService);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll5, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(scroll5)
                .addGap(45, 45, 45))
        );

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setPreferredSize(new java.awt.Dimension(396, 61));
        jPanel26.setLayout(new java.awt.GridBagLayout());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Liste des Services");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 161, 16, 218);
        jPanel26.add(jLabel16, gridBagConstraints);

        jPanel27.setBackground(new java.awt.Color(234, 236, 239));
        jPanel27.setPreferredSize(new java.awt.Dimension(504, 36));
        jPanel27.setLayout(new java.awt.GridLayout(1, 0));

        ModifService.setBackground(new java.awt.Color(240, 163, 51));
        ModifService.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ModifService.setForeground(new java.awt.Color(255, 255, 255));
        ModifService.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Editer.png"))); // NOI18N
        ModifService.setBorder(null);
        ModifService.setBorderPainted(false);
        ModifService.setContentAreaFilled(false);
        ModifService.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ModifService.setFocusPainted(false);
        ModifService.setFocusable(false);
        ModifService.setRequestFocusEnabled(false);
        ModifService.setRolloverEnabled(false);
        ModifService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifServiceActionPerformed(evt);
            }
        });
        jPanel27.add(ModifService);

        SupService.setBackground(new java.awt.Color(199, 85, 80));
        SupService.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SupService.setForeground(new java.awt.Color(255, 255, 255));
        SupService.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/supprimer.png"))); // NOI18N
        SupService.setBorder(null);
        SupService.setBorderPainted(false);
        SupService.setContentAreaFilled(false);
        SupService.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SupService.setFocusPainted(false);
        SupService.setFocusable(false);
        SupService.setRequestFocusEnabled(false);
        SupService.setRolloverEnabled(false);
        SupService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupServiceActionPerformed(evt);
            }
        });
        jPanel27.add(SupService);

        AjoutService.setBackground(new java.awt.Color(51, 51, 51));
        AjoutService.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        AjoutService.setForeground(new java.awt.Color(255, 255, 255));
        AjoutService.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-ajouter-20.png"))); // NOI18N
        AjoutService.setText("ajouter");
        AjoutService.setBorder(null);
        AjoutService.setBorderPainted(false);
        AjoutService.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AjoutService.setFocusPainted(false);
        AjoutService.setFocusable(false);
        AjoutService.setRequestFocusEnabled(false);
        AjoutService.setRolloverEnabled(false);
        AjoutService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutServiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Jp10Layout = new javax.swing.GroupLayout(Jp10);
        Jp10.setLayout(Jp10Layout);
        Jp10Layout.setHorizontalGroup(
            Jp10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
            .addGroup(Jp10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jp10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jp10Layout.createSequentialGroup()
                        .addComponent(AjoutService, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        Jp10Layout.setVerticalGroup(
            Jp10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp10Layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(AjoutService, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout Jp7Layout = new javax.swing.GroupLayout(Jp7);
        Jp7.setLayout(Jp7Layout);
        Jp7Layout.setHorizontalGroup(
            Jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
            .addGroup(Jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Jp7Layout.setVerticalGroup(
            Jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 651, Short.MAX_VALUE)
            .addGroup(Jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DashboardLayout = new javax.swing.GroupLayout(Dashboard);
        Dashboard.setLayout(DashboardLayout);
        DashboardLayout.setHorizontalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Jp1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp3, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DashboardLayout.setVerticalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Jp1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(Dashboard, java.awt.BorderLayout.CENTER);

        hidemenu.setBackground(new java.awt.Color(0, 51, 51));
        hidemenu.setLayout(new java.awt.BorderLayout());

        setting.setBackground(new java.awt.Color(0, 51, 51));
        setting.setLayout(new java.awt.BorderLayout());
        hidemenu.add(setting, java.awt.BorderLayout.CENTER);

        getContentPane().add(hidemenu, java.awt.BorderLayout.PAGE_END);

        setSize(new java.awt.Dimension(923, 678));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    int x, y;

    public void changecolor(JPanel hover, Color rand) {
        hover.setBackground(rand);
    }

    public void clickmenu(JPanel h1, JPanel h2, int numberbool) {
        if (numberbool == 1) {
            h1.setBackground(new Color(25, 29, 74));
            h2.setBackground(new Color(102, 153, 255));
        } else {
            h1.setBackground(new Color(102, 153, 255));
            h2.setBackground(new Color(25, 29, 74));
        }
    }

    public void changeimage(JLabel button, String resourcheimag) {
        ImageIcon aimg = new ImageIcon(getClass().getResource(resourcheimag));
        button.setIcon(aimg);

    }

    public void hideshow(JPanel menushowhide, boolean dashboard, JLabel button) {
        if (dashboard == true) {
            menushowhide.setPreferredSize(new Dimension(50, menushowhide.getHeight()));
            changeimage(button, "/Icons/icons8-menu.png");
            menushowhide.revalidate();
            menushowhide.repaint();
        } else {
            menushowhide.setPreferredSize(new Dimension(270, menushowhide.getHeight()));
            changeimage(button, "/Icons/icons8-flèche-30.png");
            menushowhide.revalidate();
            menushowhide.repaint();
        }
    }

    private void CloseButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseButtonMouseEntered
        changecolor(Buttonclose, new Color(240, 50, 50));
    }//GEN-LAST:event_CloseButtonMouseEntered

    private void CloseButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseButtonMouseExited
        changecolor(Buttonclose, new Color(234, 236, 239));
    }//GEN-LAST:event_CloseButtonMouseExited

    private void CloseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseButtonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_CloseButtonMouseClicked

    private void MaxButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxButtonMouseEntered
        changecolor(Buttonmax, new Color(140, 130, 130));
    }//GEN-LAST:event_MaxButtonMouseEntered

    private void MaxButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxButtonMouseExited
        changecolor(Buttonmax, new Color(234, 236, 239));
    }//GEN-LAST:event_MaxButtonMouseExited

    private void MaxButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxButtonMouseClicked
        if (this.getExtendedState() != AcceuilPrincipale.MAXIMIZED_BOTH) {
            this.setExtendedState(AcceuilPrincipale.MAXIMIZED_BOTH);
        } else {
            this.setExtendedState(AcceuilPrincipale.NORMAL);
        }
    }//GEN-LAST:event_MaxButtonMouseClicked

    private void ReduireMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReduireMouseClicked
        this.setState(AcceuilPrincipale.ICONIFIED);
    }//GEN-LAST:event_ReduireMouseClicked

    private void ReduireMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReduireMouseEntered
        changecolor(ButtonReduire, new Color(140, 130, 130));
    }//GEN-LAST:event_ReduireMouseEntered

    private void ReduireMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReduireMouseExited
        changecolor(ButtonReduire, new Color(234, 236, 239));
    }//GEN-LAST:event_ReduireMouseExited

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Modifachat.setEnabled(false);
        ModifProd.setEnabled(false);
        modifEntree.setEnabled(false);
        ModifEntretien.setEnabled(false);
        ModifService.setEnabled(false);
        SupAchat.setEnabled(false);
        SupEntretien.setEnabled(false);
        SupEntree.setEnabled(false);
        SupService.setEnabled(false);
        SupProd.setEnabled(false);
        jButton1.setEnabled(false);

    }//GEN-LAST:event_formWindowActivated

    private void HeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeaderMouseDragged
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx - x, yy - y);
    }//GEN-LAST:event_HeaderMouseDragged

    private void HeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeaderMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_HeaderMousePressed

    private void TableProduitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProduitMouseClicked
        ReccuperationTableProduit();
    }//GEN-LAST:event_TableProduitMouseClicked

    private void TableProduitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProduitMouseReleased
        ModifProd.setEnabled(true);
        SupProd.setEnabled(true);
    }//GEN-LAST:event_TableProduitMouseReleased

    private void AjoutProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjoutProdActionPerformed
        AjoutProduit Ar = new AjoutProduit();
        Ar.setResp(TableProduit);
        Ar.setVisible(true);
    }//GEN-LAST:event_AjoutProdActionPerformed

    private void txtrechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercheKeyReleased
        btnrechercheActionPerformed(null);
    }//GEN-LAST:event_txtrechercheKeyReleased

    private void btnrechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrechercheActionPerformed
        String requete = "SELECT * FROM entretien WHERE nomClient LIKE ? ";
        try {
            pst = conn.prepareStatement(requete);
            pst.setString(1, "%" + txtrecherche.getText() + "%");
            rs = pst.executeQuery();
            TableEntretien.setModel(DbUtils.resultSetToTableModel(rs));

            if (txtrecherche.getText().isEmpty()) {
                AffichageTableEntretien();;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnrechercheActionPerformed

    private void ModifProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifProdActionPerformed
        ModifierProduit Mr = new ModifierProduit();
        Mr.setResp(TableProduit);
        Mr.setVisible(true);
    }//GEN-LAST:event_ModifProdActionPerformed

    private void SupProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupProdActionPerformed
        try {
            try {
                String C0 = rs.getString("numProd");
                if (JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cette Produit?",
                        "Supprimer Produit", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    if (C0.length() != 0) {
                        String requete = "DELETE FROM produit WHERE numProd = ? ";
                        pst = conn.prepareStatement(requete);
                        pst.setString(1, C0);
                        pst.execute();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Suppression avec success");

                    } else {
                        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Suppression echoué");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            SupProd.setEnabled(false);
            AffichageTableProduit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_SupProdActionPerformed

    private static PdfPCell createCell(String content, boolean isHeader) {
        PdfPCell cell;
        if (isHeader) {
            cell = new PdfPCell(new Phrase(content));
        } else {
            cell = new PdfPCell(new Phrase(content));
        }
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5);
        cell.setUseBorderPadding(true);

        cell.setBorderColor(new BaseColor(0, 0, 0));
        return cell;
    }


    private void TableAchatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableAchatMouseClicked
        ReccuperationTableAchat();
    }//GEN-LAST:event_TableAchatMouseClicked

    private void TableAchatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableAchatMouseReleased
        Modifachat.setEnabled(true);
        SupAchat.setEnabled(true);
        ajouterAchat.setEnabled(true);
    }//GEN-LAST:event_TableAchatMouseReleased

    private void ModifachatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifachatActionPerformed
        ModifAchat modifAchat = new ModifAchat();
        modifAchat.setAchat(TableAchat);
        modifAchat.setProduit(TableProduit);
        modifAchat.setVisible(true);
    }//GEN-LAST:event_ModifachatActionPerformed

    private void SupAchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupAchatActionPerformed
        try {
            try {
                String C0 = rs.getString("numAchat");
                if (JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cette achat?",
                        "Supprimer achat", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    if (C0.length() != 0) {
                        String requete = "DELETE FROM achat WHERE numAchat = ? ";
                        pst = conn.prepareStatement(requete);
                        pst.setString(1, C0);
                        pst.execute();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Suppression avec success");

                    } else {
                        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Suppression echoué");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            SupAchat.setEnabled(false);
            AffichageTableAchat();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_SupAchatActionPerformed

    private void ajouterAchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterAchatActionPerformed
        AjoutAchat Achat = new AjoutAchat();
        Achat.setAchat(TableAchat);
        Achat.setProduit(TableProduit);
        Achat.setVisible(true);
    }//GEN-LAST:event_ajouterAchatActionPerformed

    private void NotificationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotificationMouseEntered

    }//GEN-LAST:event_NotificationMouseEntered

    private void NotificationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotificationMouseExited

    }//GEN-LAST:event_NotificationMouseExited

    private void NotificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotificationActionPerformed
   
    }//GEN-LAST:event_NotificationActionPerformed


    private void TableServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableServiceMouseClicked
        ReccuperationTableService();
    }//GEN-LAST:event_TableServiceMouseClicked

    private void TableServiceMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableServiceMouseReleased
        SupService.setEnabled(true);
        ModifService.setEnabled(true);
    }//GEN-LAST:event_TableServiceMouseReleased

    private void AjoutServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjoutServiceActionPerformed
        AjoutService As = new AjoutService();
        As.setService(TableService);
        As.setVisible(true);
    }//GEN-LAST:event_AjoutServiceActionPerformed

    private void SupServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupServiceActionPerformed

        try {

            String C0 = rs.getString("numServ");
            int choice = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cette Service?", "Supprimer Service", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                if (C0.length() != 0) {
                    String requete = "DELETE FROM service WHERE numServ = ? ";
                    pst = conn.prepareStatement(requete);
                    pst.setString(1, C0);
                    pst.execute();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Suppression avec success");
                } else {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Suppression echoué");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        SupService.setEnabled(false);
        AffichageTableService();
    }//GEN-LAST:event_SupServiceActionPerformed

    private void ModifServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifServiceActionPerformed
        ModifService mdserv = new ModifService();
        mdserv.setService(TableService);
        mdserv.setVisible(true);
    }//GEN-LAST:event_ModifServiceActionPerformed

    private void SupEntretienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupEntretienActionPerformed
        try {
            try {
                String C0 = rs.getString("numEntr");
                if (JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cette entretien?",
                        "Supprimer entretien", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    if (C0.length() != 0) {
                        String requete = "DELETE FROM entretien WHERE numEntr = ? ";
                        pst = conn.prepareStatement(requete);
                        pst.setString(1, C0);
                        pst.execute();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Suppression avec success");

                    } else {
                        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Suppression echoué");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            SupEntretien.setEnabled(false);
            AffichageTableEntretien();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_SupEntretienActionPerformed

    private void ModifEntretienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifEntretienActionPerformed
        ModifEntretien mdfEntre = new ModifEntretien();
        mdfEntre.setEntretien(TableEntretien);
        mdfEntre.setVisible(true);
    }//GEN-LAST:event_ModifEntretienActionPerformed

    private void AjoutEntretienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjoutEntretienMouseClicked
        AjoutEntretien AjouEntr = new AjoutEntretien();
        AjouEntr.setEntretien(TableEntretien);
        AjouEntr.setVisible(true);
    }//GEN-LAST:event_AjoutEntretienMouseClicked

    private void TableEntretienMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEntretienMouseReleased
        ModifEntretien.setEnabled(true);
        SupEntretien.setEnabled(true);
        jButton1.setEnabled(true);
    }//GEN-LAST:event_TableEntretienMouseReleased

    private void TableEntretienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEntretienMouseClicked
        ReccuperationTableEntretien();
    }//GEN-LAST:event_TableEntretienMouseClicked

    private void NotificationMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotificationMousePressed
       
    }//GEN-LAST:event_NotificationMousePressed

    private void ArchiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ArchiveMouseClicked
        resetColor(JBoard);
        resetColor(JEntrant);
        resetColor(JSortant);
        resetColor(JResponsable);
        resetColor(JTraitement);
        setColor(JArch);
        Jp1.setVisible(false);
        Jp2.setVisible(false);
        Jp3.setVisible(false);
        Jp4.setVisible(false);
        Jp5.setVisible(false);
        Jp7.setVisible(true);
    }//GEN-LAST:event_ArchiveMouseClicked

    private void ResponsabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResponsabMouseClicked
        resetColor(JBoard);
        resetColor(JEntrant);
        resetColor(JSortant);
        setColor(JResponsable);
        resetColor(JTraitement);
        resetColor(JArch);
        Jp1.setVisible(false);
        Jp2.setVisible(true);
        Jp3.setVisible(false);
        Jp4.setVisible(false);
        Jp5.setVisible(false);
        Jp7.setVisible(false);
    }//GEN-LAST:event_ResponsabMouseClicked

    private void SortantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SortantMouseClicked
        resetColor(JBoard);
        resetColor(JEntrant);
        setColor(JSortant);
        resetColor(JResponsable);
        resetColor(JTraitement);
        resetColor(JArch);
        Jp1.setVisible(false);
        Jp2.setVisible(false);
        Jp3.setVisible(false);
        Jp4.setVisible(true);
        Jp5.setVisible(false);
        Jp7.setVisible(false);
    }//GEN-LAST:event_SortantMouseClicked

    private void DashboarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DashboarMousePressed

    private void DashboarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_DashboarMouseEntered

    private void DashboarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboarMouseClicked
        setColor(JBoard);
        resetColor(JEntrant);
        resetColor(JSortant);
        resetColor(JResponsable);
        resetColor(JTraitement);
        resetColor(JArch);
        Jp1.setVisible(true);
        Jp2.setVisible(false);
        Jp3.setVisible(false);
        Jp4.setVisible(false);
        Jp5.setVisible(false);
        Jp7.setVisible(false);
        Histogramme();
        Compt();
        client_partitifs();
    }//GEN-LAST:event_DashboarMouseClicked

    private void EntrantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntrantMouseClicked
        resetColor(JBoard);
        setColor(JEntrant);
        resetColor(JSortant);
        resetColor(JResponsable);
        resetColor(JTraitement);
        resetColor(JArch);
        Jp1.setVisible(false);
        Jp2.setVisible(false);
        Jp3.setVisible(true);
        Jp4.setVisible(false);
        Jp5.setVisible(false);
        Jp7.setVisible(false);
    }//GEN-LAST:event_EntrantMouseClicked

    private void TraiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TraiteMouseClicked
        resetColor(JBoard);
        resetColor(JEntrant);
        resetColor(JSortant);
        resetColor(JResponsable);
        setColor(JTraitement);
        resetColor(JArch);
        Jp1.setVisible(false);
        Jp2.setVisible(false);
        Jp3.setVisible(false);
        Jp4.setVisible(false);
        Jp5.setVisible(true);
        Jp7.setVisible(false);
    }//GEN-LAST:event_TraiteMouseClicked

    private void ButtonHideMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonHideMenuMouseExited

    }//GEN-LAST:event_ButtonHideMenuMouseExited

    private void ButtonHideMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonHideMenuMouseEntered

    }//GEN-LAST:event_ButtonHideMenuMouseEntered

    private void ButtonHideMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonHideMenuMouseClicked

    }//GEN-LAST:event_ButtonHideMenuMouseClicked

    private void AjoutEntreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjoutEntreeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AjoutEntreeActionPerformed

    private void AjoutEntreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjoutEntreeMouseClicked
        AjoutEntree As = new AjoutEntree();
        As.setEntree(TableEntree);
        As.setProduit(TableProduit);
        As.setVisible(true);
    }//GEN-LAST:event_AjoutEntreeMouseClicked

    private void SupEntreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupEntreeActionPerformed
        try {
            try {
                String C0 = rs.getString("numEntree");
                if (JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cette entree?",
                    "Supprimer Entree", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                if (C0.length() != 0) {
                    String requete = "DELETE FROM entree WHERE numEntree = ? ";
                    pst = conn.prepareStatement(requete);
                    pst.setString(1, C0);
                    pst.execute();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Suppression avec success");

                } else {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Suppression echoué");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        SupEntree.setEnabled(false);
        AffichageTableEntree();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_SupEntreeActionPerformed

    private void modifEntreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifEntreeActionPerformed
        ModifEntree MdEntree = new ModifEntree();
        MdEntree.setEntree(TableEntree);
        MdEntree.setProd(TableProduit);
        MdEntree.setVisible(true);

    }//GEN-LAST:event_modifEntreeActionPerformed

    private void TableEntreeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEntreeMouseReleased
        modifEntree.setEnabled(true);
        SupEntree.setEnabled(true);
    }//GEN-LAST:event_TableEntreeMouseReleased

    private void TableEntreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableEntreeMouseClicked
        ReccuperationTableEntree();
    }//GEN-LAST:event_TableEntreeMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         try {
            String C4 = rs.getString("nomClient");
            Date D1 = rs.getDate("dateEntretien");
            Document document = new Document();
            try {
                String query = "SELECT entretien.dateEntretien, entretien.nomClient, entretien.Immatriculation_voiture, service.service, service.prix FROM entretien JOIN service ON entretien.numServ = service.numServ WHERE entretien.nomClient = ? AND entretien.dateEntretien = ?";
                pst = conn.prepareStatement(query);
                pst.setString(1, C4); // Nom du client
                pst.setDate(2, (java.sql.Date) D1); // Date d'entretien
                rs = pst.executeQuery();

                ResultSet resultSet = pst.executeQuery();

                // Variables for total amount calculation
                int totalAmount = 0;

                boolean clientDetailsAdded = false; // Variable to track if client details paragraph is added

                // Demande à l'utilisateur de sélectionner l'emplacement du fichier PDF
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choisir l'emplacement du fichier PDF");
                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    // L'utilisateur a sélectionné un emplacement valide
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                    if (!filePath.toLowerCase().endsWith(".pdf")) {
                        filePath += ".pdf";
                    }

                    // Génération du reçu d'entretien au format PDF avec l'emplacement sélectionné
                    PdfWriter.getInstance(document, new FileOutputStream(filePath));
                    document.open();

                    Rectangle pageSize = document.getPageSize();
                    float pageWidth = pageSize.getWidth();

                    float padding = pageWidth / 40;

                    PdfPCell titreCell = new PdfPCell(new Paragraph("REÇU D'ENTRETIEN"));
                    titreCell.setBackgroundColor(new BaseColor(255, 153, 255));
                    titreCell.setPadding(padding);
                    titreCell.setBorder(Rectangle.NO_BORDER);
                    titreCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

                    PdfPTable TableEntretien = new PdfPTable(1);
                    TableEntretien.setWidthPercentage(100);
                    TableEntretien.addCell(titreCell);
                    TableEntretien.setSpacingAfter(15);
                    document.add(TableEntretien);

                    PdfPTable infoEntretien = new PdfPTable(2);
                    infoEntretien.setWidthPercentage(100);
                    infoEntretien.addCell(createCell("Service", true));
                    infoEntretien.addCell(createCell("Montant", true));

                    while (resultSet.next()) {
                        String service = resultSet.getString("service");
                        int prix = resultSet.getInt("prix");

                        // Add the current service price to the total amount
                        totalAmount += prix;

                        if (!clientDetailsAdded) {
                            String nomClient = resultSet.getString("nomClient");
                            java.util.Date dateEntretien = resultSet.getDate("dateEntretien");
                            String Immatriculation_voiture = resultSet.getString("Immatriculation_voiture");

                            Paragraph p1 = new Paragraph();
                            p1.add("Date : " + dateEntretien.toString());
                            p1.setSpacingAfter(10);
                            document.add(p1);

                            Paragraph p2 = new Paragraph();
                            p2.add("Nom du Client : " + nomClient);
                            p2.setSpacingAfter(10);
                            document.add(p2);

                            Paragraph p3 = new Paragraph();
                            p3.add("Voiture : " + Immatriculation_voiture);
                            p3.setSpacingAfter(15);
                            document.add(p3);

                            clientDetailsAdded = true; // Set the flag to true once client details are added
                        }

                        infoEntretien.addCell(createCell(service, false));
                        infoEntretien.addCell(createCell(Integer.toString(prix), false));
                    }

                    infoEntretien.setSpacingAfter(15);
                    document.add(infoEntretien);

                    // Display the total amount
                    Paragraph p4 = new Paragraph();
                    p4.add("Total: " + totalAmount + " Ar");
                    p4.setSpacingAfter(15);
                    document.add(p4);

                    PdfPTable footerTable = new PdfPTable(1);
                    footerTable.setWidthPercentage(100);
                    footerTable.addCell(createCell("Merci pour votre confiance !", false));
                    document.add(footerTable);

                    document.close();
                    JOptionPane.showMessageDialog(null, "Téléchargement terminé avec succès");

                    // Ouvrir l'emplacement du fichier PDF
                    Desktop desktop = Desktop.getDesktop();
                    File pdfFile = new File(filePath);
                    if (pdfFile.exists()) {
                        desktop.open(pdfFile);
                    } else {
                        JOptionPane.showMessageDialog(null, "Le fichier PDF n'existe pas à l'emplacement spécifié");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Téléchargement échoué");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AcceuilPrincipale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    //-------------------------------RECCUPERATION ET AFFICHAGE TABLE PRODUIT---------------
    public void AffichageTableProduit() {
        try {
            String requete = "SELECT numProd AS \"NUMERO PRODUIT\", design AS \"DESIGN\", stock AS \"STOCK\" FROM produit";
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

    public void ReccuperationTableProduit() {
        try {
            int Row = TableProduit.getSelectedRow();
            this.Tab = (TableProduit.getModel().getValueAt(Row, 0).toString());
            String requete = "SELECT numProd, design, stock  FROM produit WHERE numProd = '" + Tab + "' ";
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

    //-----------------------------------------------FIN------------------------------------------------
    //-------------------------------RECCUPERATION ET AFFICHAGE TABLE ENTREE---------------
    public void AffichageTableEntree() {
        try {
            String requete = "SELECT numEntree AS \"NUMERO ENTREE\", numProd AS \"NUMERO PRODUIT\", stockEntree AS \"STOCK ENTREE\", dateEntree AS \"DATE ENTREE\" FROM entree";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            TableModel model = DbUtils.resultSetToTableModel(rs);
            TableEntree.setModel(model);

            // Désactiver l'édition des cellules individuelles
            TableEntree.setDefaultEditor(Object.class, null);

            // Empêcher la sélection de plusieurs lignes
            TableEntree.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Personnaliser le rendu des lignes
            TableEntree.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

            JTableHeader Head = TableEntree.getTableHeader();
            Dimension dimension = Head.getPreferredSize();
            dimension.height = 35;
            Head.setPreferredSize(dimension);
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

    public String getTable2() {
        return Tab1;
    }

    //-----------------------------------------------FIN------------------------------------------------
    //-------------------------------RECCUPERATION ET AFFICHAGE TABLE ENTRANT---------------
    public void AffichageTableEntretien() {
        try {
           String requete = "SELECT numEntr AS \"NUMERO ENTRETIEN\", numServ AS \"NUMERO SERVICE\", Immatriculation_voiture AS \"IMMATRICULATION\", nomClient AS \"NOM CLIENT\", dateEntretien AS \"DATE ENTRETIEN\" FROM entretien";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            TableModel model = DbUtils.resultSetToTableModel(rs);
            TableEntretien.setModel(model);

            // Désactiver l'édition des cellules individuelles
            TableEntretien.setDefaultEditor(Object.class, null);

            // Empêcher la sélection de plusieurs lignes
            TableEntretien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Personnaliser le rendu des lignes
            TableEntretien.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

            JTableHeader Head = TableEntretien.getTableHeader();
            Dimension dimension = Head.getPreferredSize();
            dimension.height = 35;
            Head.setPreferredSize(dimension);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void ReccuperationTableEntretien() {
        try {
            int Row = TableEntretien.getSelectedRow();
            this.Tab2 = (TableEntretien.getModel().getValueAt(Row, 0).toString());
           String requete = "SELECT numEntr, numServ, Immatriculation_voiture, nomClient, dateEntretien FROM entretien WHERE numEntr = '" + Tab2 + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
               String C1 = rs.getString("numEntr");
                System.out.println(C1);
                String C2 = rs.getString("numServ");
                System.out.println(C2);
                String C3 = rs.getString("Immatriculation_voiture");
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

    public String getTable7() {
        return Tab3;
    }

    //-----------------------------------------------FIN------------------------------------------------
    //-------------------------------RECCUPERATION ET AFFICHAGE TABLE ACHAT---------------
    public void AffichageTableAchat() {
        try {
            String requete = "SELECT numAchat AS \"NUMERO ACHAT\", numProd AS \"NUMERO PRODUIT\", nomClient AS \"NOM CLIENT\", nbrLitre AS \"NOMBRE DE LITRE\", dateAchat AS \"DATE\" FROM achat";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            TableModel model = DbUtils.resultSetToTableModel(rs);
            TableAchat.setModel(model);

            // Désactiver l'édition des cellules individuelles
            TableAchat.setDefaultEditor(Object.class, null);

            // Empêcher la sélection de plusieurs lignes
            TableAchat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Personnaliser le rendu des lignes
            TableAchat.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

            JTableHeader Head = TableAchat.getTableHeader();
            Dimension dimension = Head.getPreferredSize();
            dimension.height = 35;
            Head.setPreferredSize(dimension);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void searchtypes() {
        try {
            String Requete = "SELECT numAchat, numProd, nomClient, nbrLitre, dateAchat FROM achat WHERE nomClient = '" + type + "'";
            pst = conn.prepareStatement(Requete);
            rs = pst.executeQuery();
            TableModel model = DbUtils.resultSetToTableModel(rs);
            TableAchat.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ReccuperationTableAchat(){
        try {
            int Row = TableAchat.getSelectedRow();
            this.Tab2 = (TableAchat.getModel().getValueAt(Row, 0).toString());
            String requete = "SELECT numAchat, numProd, nomClient, nbrLitre, dateAchat FROM achat WHERE numAchat = '" + Tab2 + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String B1 = rs.getString("numAchat");
                System.out.println(B1);
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

    public String getTable4() {
        return Tab2;
    }

    //-----------------------------------------------FIN------------------------------------------------
    //-------------------------------RECCUPERATION ET AFFICHAGE TABLE SERVICE---------------
    public void AffichageTableService() {
        try {
            String requete = "SELECT numServ AS \"NUMERO SERVICE\", service AS \"SERVICE\", prix AS \"PRIX\" FROM service";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            TableModel model = DbUtils.resultSetToTableModel(rs);
            TableService.setModel(model);

            // Désactiver l'édition des cellules individuelles
            TableService.setDefaultEditor(Object.class, null);

            // Empêcher la sélection de plusieurs lignes
            TableService.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Personnaliser le rendu des lignes
            TableService.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

            JTableHeader Head = TableService.getTableHeader();
            Dimension dimension = Head.getPreferredSize();
            dimension.height = 35;
            Head.setPreferredSize(dimension);
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

    //------------------------------------fin--------------
   
    //---------------------HISTOGRAMME-----------------------------
     public void Histogramme() {
        try {
            // Obtention de la date d'il y a 5 mois
            LocalDate today = LocalDate.now();
            LocalDate fiveMonthsAgo = today.minusMonths(5);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

            String requete = "SELECT DATE_FORMAT(dateEntretien, '%Y-%m') AS mois, SUM(s.prix) AS recetteTotale FROM entretien e JOIN service s ON e.numServ = s.numServ WHERE dateEntretien >= ? GROUP BY mois";
            pst = conn.prepareStatement(requete);
            pst.setString(1, fiveMonthsAgo.format(formatter));
            rs = pst.executeQuery();

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            while (rs.next()) {
                String mois = rs.getString("mois");
                int recetteTotale = rs.getInt("recetteTotale");
                dataset.addValue(recetteTotale, "Recettes", mois);
            }

            JFreeChart chart = ChartFactory.createBarChart3D("Recettes pendant 5 derniers mois", "Mois", "Recette", dataset, PlotOrientation.VERTICAL, true, true, false);

            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            chart.setBackgroundPaint(new Color(255, 255, 255));
            chart.getTitle().setPaint(new Color(0, 0, 0));
            CategoryPlot catPlot = chart.getCategoryPlot();
            CategoryItemRenderer renderer = catPlot.getRenderer();
            plot.setOutlineVisible(false);
            plot.setBackgroundPaint(Color.WHITE);
            renderer.setSeriesPaint(0, new Color(255,153,255));
            ChartPanel ChartPanel = new ChartPanel(chart);
            Panel1.removeAll();
            Panel1.add(ChartPanel, BorderLayout.CENTER);
            Panel1.validate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //-----------------------FIN----------------------------------
 
 public void Compt() {
        try {
            String requete = "SELECT e.numServ, SUM(s.prix) AS recetteTotale FROM entretien e JOIN service s ON e.numServ = s.numServ GROUP BY e.numServ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();

            int sommeTotale = 0; // Variable de totalisation

            while (rs.next()) {
                int total = rs.getInt("recetteTotale");
                //  System.out.println(total);

                sommeTotale += total; // Ajouter le total à la somme totale
            }

            // System.out.println("Somme totale : " + sommeTotale); // Afficher la somme totale
            recette.setText("" + sommeTotale + " Ar");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //----------------------FIN-------------------------------
    //------------------affichage client  plus partitifs----------------
    public void client_partitifs() {
        try {
            String requete = "SELECT nomClient, COUNT(*) AS total_operations FROM (SELECT nomClient FROM entretien UNION ALL SELECT nomClient FROM achat) AS combined GROUP BY nomClient ORDER BY total_operations DESC LIMIT 5";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();

            // Create a dataset for the pie chart
            DefaultPieDataset dataset = new DefaultPieDataset();
            while (rs.next()) {
                String nomClient = rs.getString("nomClient");
                int totalOperations = rs.getInt("total_operations");
                dataset.setValue(nomClient, totalOperations);
            }

            // Create the pie chart
            JFreeChart chart = ChartFactory.createPieChart("Liste des 5 Client plus partitifs", dataset, true, true, false);
            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setOutlineVisible(false);
            plot.setBackgroundPaint(Color.WHITE);
            chart.setBackgroundPaint(Color.WHITE);
            // Create a chart panel and add it to your panel (Panel1)
            ChartPanel chartPanel = new ChartPanel(chart);
            Partititif.setLayout(new BorderLayout()); // Set the layout manager of Panel1

            // Refresh the panel to reflect the changes
            Partititif.removeAll();
            Partititif.add(chartPanel, BorderLayout.CENTER);
            Partititif.validate();
            // Centrer le contenu des cellules du tableau
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            // TablePartitifs.setDefaultRenderer(Object.class, centerRenderer);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //----------------------FIN------------------------------------
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AcceuilPrincipale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AjoutEntree;
    private javax.swing.JButton AjoutEntretien;
    private javax.swing.JButton AjoutProd;
    private javax.swing.JButton AjoutService;
    private javax.swing.JLabel Archive;
    private javax.swing.JLabel ButtonHideMenu;
    private javax.swing.JPanel ButtonReduire;
    private javax.swing.JPanel Buttonclose;
    private javax.swing.JPanel Buttonmax;
    private javax.swing.JLabel CloseButton;
    private javax.swing.JLabel Dashboar;
    private javax.swing.JPanel Dashboard;
    private javax.swing.JLabel Entrant;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel JArch;
    private javax.swing.JPanel JBoard;
    private javax.swing.JPanel JEntrant;
    private javax.swing.JPanel JResponsable;
    private javax.swing.JPanel JSortant;
    private javax.swing.JPanel JTraitement;
    private javax.swing.JPanel Jp1;
    private javax.swing.JPanel Jp10;
    private javax.swing.JPanel Jp2;
    private javax.swing.JPanel Jp3;
    private javax.swing.JPanel Jp4;
    private javax.swing.JPanel Jp5;
    private javax.swing.JPanel Jp7;
    private javax.swing.JLabel MaxButton;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Menuhide;
    private javax.swing.JButton ModifEntretien;
    private javax.swing.JButton ModifProd;
    private javax.swing.JButton ModifService;
    private javax.swing.JButton Modifachat;
    private javax.swing.JPanel Panel1;
    private javax.swing.JPanel Partititif;
    private javax.swing.JLabel Reduire;
    private javax.swing.JLabel Responsab;
    private javax.swing.JLabel Sortant;
    private javax.swing.JButton SupAchat;
    private javax.swing.JButton SupEntree;
    private javax.swing.JButton SupEntretien;
    private javax.swing.JButton SupProd;
    private javax.swing.JButton SupService;
    private javax.swing.JTable TableAchat;
    private javax.swing.JTable TableEntree;
    private javax.swing.JTable TableEntretien;
    private javax.swing.JTable TableProduit;
    private javax.swing.JTable TableService;
    private javax.swing.JLabel Traite;
    private javax.swing.JButton ajouterAchat;
    private javax.swing.JButton btnrecherche;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel hidemenu;
    private javax.swing.JPanel iconminmaxclose;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JSeparator jSeparator1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel3;
    private javax.swing.JButton modifEntree;
    private javax.swing.JLabel recette;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JScrollPane scroll1;
    private javax.swing.JScrollPane scroll2;
    private javax.swing.JScrollPane scroll3;
    private javax.swing.JScrollPane scroll5;
    private javax.swing.JPanel setting;
    private javax.swing.JTextField txtrecherche;
    // End of variables declaration//GEN-END:variables
}
