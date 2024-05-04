/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aldrin.cafsysv2.gui;

import com.aldrin.cafsysv2.dao.impl.CategoryDAOImpl;
import com.aldrin.cafsysv2.dao.impl.InvoiceDAOImpl;
import com.aldrin.cafsysv2.dao.impl.InvoiceLineMenuDAOImpl;
import com.aldrin.cafsysv2.dao.impl.MenuDAOImpl;
import com.aldrin.cafsysv2.dao.impl.TodaysMenuDAOImpl;
//import com.aldrin.cafsysv2.events.ActionListenerHandler;
import com.aldrin.cafsysv2.gui.dialog.JDialogAbout;
import com.aldrin.cafsysv2.gui.dialog.JDialogCategory;
import com.aldrin.cafsysv2.gui.dialog.JDialogChangePassword;
import com.aldrin.cafsysv2.gui.dialog.JDialogEditPrice;
import com.aldrin.cafsysv2.gui.dialog.JDialogMenu;
import com.aldrin.cafsysv2.gui.dialog.JDialogReport;
import com.aldrin.cafsysv2.gui.dialog.JDialogSale;
import com.aldrin.cafsysv2.gui.dialog.JDialogTodaysMenu;
import com.aldrin.cafsysv2.gui.dialog.JDialogUserAccount;
import com.aldrin.cafsysv2.gui.dialog.JDialogViewInvoice;
import com.aldrin.cafsysv2.model.Category;
import com.aldrin.cafsysv2.model.Invoice;
import com.aldrin.cafsysv2.model.InvoiceLineMenu;
import com.aldrin.cafsysv2.model.Menu;
import com.aldrin.cafsysv2.model.OrderOption;
import com.aldrin.cafsysv2.model.TodaysMenu;
import com.aldrin.cafsysv2.model.UserAccount;
import com.aldrin.cafsysv2.util.LoginUser;
import com.aldrin.cafsysv2.util.OrdersHold;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ALRIN B.C.
 */
@Setter
@Getter
@ToString
public class JFrameCafSys extends javax.swing.JFrame implements MouseListener {

    private DecimalFormat df = new DecimalFormat("##,##0.00");
    private static Float cash = 0.0F;
    private static Float change = 0.0F;
    private static Boolean payment = false;
    private static Float totalAmount = 0.0F;
    private static String takingMeals = "";
    private static Integer tableNo;
    private static Float customerCash = 0.0F;
    private static OrderOption orderOption;
    private static UserAccount userLogin;
    private LoginUser loginUser;

    public JFrameCafSys() {
        initComponents();
        FlatSVGIcon icon = new FlatSVGIcon("svg/recipe.svg", 16, 16);
        setIconImage(icon.getImage());
        jPanelCenter.putClientProperty(FlatClientProperties.STYLE,
                "[light]border: 0,0,0,0,shade(@background,30%),,18;" + "[dark]border: 0,0,0,0,tint(@background,30%),,8");
        jToolBar1.putClientProperty(FlatClientProperties.STYLE,
                "[light]border: 0,0,0,0,shade(@background,30%),,18;" + "[dark]border: 0,0,0,0,tint(@background,30%),,8");
        jPanelDispense.putClientProperty(FlatClientProperties.STYLE,
                "[light]border: 0,0,0,0,shade(@background,30%),,18;" + "[dark]border: 0,0,0,0,tint(@background,30%),,8");
        jPanelMenus.putClientProperty(FlatClientProperties.STYLE,
                "[light]border: 0,0,0,0,shade(@background,30%),,18;" + "[dark]border: 0,0,0,0,tint(@background,30%),,8");
        jPanelMenu.validate();
        setTable();
        selectCategory();
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShadowColor(new java.awt.Color(153, 0, 0));
        dropShadowBorder2.setShowRightShadow(false);
        jPanel58.setBorder(dropShadowBorder2);
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder3 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder3.setShadowColor(new java.awt.Color(0, 102, 51));
        dropShadowBorder3.setShowRightShadow(false);
        jPanel59.setBorder(dropShadowBorder3);
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder4 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder4.setShadowColor(new java.awt.Color(255, 153, 0));
        dropShadowBorder4.setShowRightShadow(false);
        jPanel60.setBorder(dropShadowBorder4);
        UIManager.put("Button.arc", 8);//JButton
        jButtonHold.setEnabled(false);
        jButtonPayment.setEnabled(false);
        jButtonNew.setEnabled(false);

        org.jdesktop.swingx.border.DropShadowBorder panel40 = new org.jdesktop.swingx.border.DropShadowBorder();
        panel40.setShadowColor(new java.awt.Color(153, 0, 0));
        panel40.setShowRightShadow(false);
        panel40.setShowTopShadow(true);
        jPanel40.setBorder(panel40);

        jMenuSystem.setText(takingMeals);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                quitApp();
            }
        });
        if (loginUser.getUserAccount() != null) {
            jMenuSystem.setText(loginUser.getUserAccount().getSurname() + ", " + loginUser.getUserAccount().getFirstname());
            loginRole();
        }

        tableModel.setRowCount(0);

        jMenuItemLogin.setVisible(false);

    }

    public JFrameCafSys(Category c, JFrameCafSys jFrameCafSys) {

        selectTodaysMenu(c.getId());

    }

    public JFrameCafSys(TodaysMenu tm) {
        addOrderToTable(tm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanelCenter = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanelDispense = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jButtonMinus = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        jButtonRemove = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        jButtonEdit = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jPanel58 = new javax.swing.JPanel();
        jLabelTotal2 = new javax.swing.JLabel();
        jLabelTotal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel59 = new javax.swing.JPanel();
        jLabelCash2 = new javax.swing.JLabel();
        jLabelCash = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel60 = new javax.swing.JPanel();
        jLabelChange2 = new javax.swing.JLabel();
        jLabelChange = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jButtonNew = new javax.swing.JButton();
        jPanel42 = new javax.swing.JPanel();
        jButtonHold = new javax.swing.JButton();
        jPanel43 = new javax.swing.JPanel();
        jButtonPayment = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanelMenus = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jPanel53 = new javax.swing.JPanel();
        jPanel54 = new javax.swing.JPanel();
        jPanel55 = new javax.swing.JPanel();
        jPanel56 = new javax.swing.JPanel();
        jPanelMenuFrame = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanelMenu = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanelCategory = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonTodaysMenu = new javax.swing.JButton();
        jButtonSale = new javax.swing.JButton();
        jButtonPrint = new javax.swing.JButton();
        jButtonInvoice = new javax.swing.JButton();
        jButtonUpdatePrice = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuSystem = new javax.swing.JMenu();
        jMenuItemChangePassword = new javax.swing.JMenuItem();
        jMenuItemLogout = new javax.swing.JMenuItem();
        jMenuItemLogin = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuSettings = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuManage = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuTheme = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CafSys v2.0");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(5, 100));
        jPanel1.add(jPanel3, java.awt.BorderLayout.WEST);

        jPanelCenter.setLayout(new java.awt.BorderLayout());

        jPanel19.setOpaque(false);
        jPanel19.setPreferredSize(new java.awt.Dimension(650, 10));
        jPanel19.setLayout(new java.awt.BorderLayout());

        jPanel9.setOpaque(false);
        jPanel9.setPreferredSize(new java.awt.Dimension(10, 3));
        jPanel19.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanelDispense.setOpaque(false);
        jPanelDispense.setLayout(new java.awt.BorderLayout());

        jPanel13.setOpaque(false);
        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel16.setOpaque(false);
        jPanel16.setLayout(new java.awt.BorderLayout());

        jPanel24.setOpaque(false);
        jPanel24.setLayout(new java.awt.BorderLayout());

        jPanel26.setOpaque(false);
        jPanel26.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel26.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel24.add(jPanel26, java.awt.BorderLayout.CENTER);

        jPanel27.setOpaque(false);
        jPanel27.setPreferredSize(new java.awt.Dimension(10, 3));
        jPanel24.add(jPanel27, java.awt.BorderLayout.NORTH);

        jPanel28.setOpaque(false);
        jPanel28.setPreferredSize(new java.awt.Dimension(10, 3));
        jPanel24.add(jPanel28, java.awt.BorderLayout.SOUTH);

        jPanel29.setOpaque(false);
        jPanel29.setPreferredSize(new java.awt.Dimension(3, 10));
        jPanel24.add(jPanel29, java.awt.BorderLayout.EAST);

        jPanel30.setOpaque(false);
        jPanel30.setPreferredSize(new java.awt.Dimension(5, 10));
        jPanel24.add(jPanel30, java.awt.BorderLayout.WEST);

        jPanel16.add(jPanel24, java.awt.BorderLayout.CENTER);

        jPanel25.setOpaque(false);
        jPanel25.setPreferredSize(new java.awt.Dimension(60, 10));

        jButtonAdd.setIcon(new FlatSVGIcon("svg/plus.svg",42,42));
        jButtonAdd.setFocusable(false);
        jButtonAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAdd.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonAdd.setMaximumSize(new java.awt.Dimension(48, 48));
        jButtonAdd.setMinimumSize(new java.awt.Dimension(48, 48));
        jButtonAdd.setOpaque(true);
        jButtonAdd.setPreferredSize(new java.awt.Dimension(48, 48));
        jButtonAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jPanel25.add(jButtonAdd);

        jPanel31.setPreferredSize(new java.awt.Dimension(10, 25));
        jPanel25.add(jPanel31);

        jButtonMinus.setIcon(new FlatSVGIcon("svg/minus.svg",42,42));
        jButtonMinus.setFocusable(false);
        jButtonMinus.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonMinus.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonMinus.setMaximumSize(new java.awt.Dimension(48, 48));
        jButtonMinus.setMinimumSize(new java.awt.Dimension(48, 48));
        jButtonMinus.setOpaque(true);
        jButtonMinus.setPreferredSize(new java.awt.Dimension(48, 48));
        jButtonMinus.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinusActionPerformed(evt);
            }
        });
        jPanel25.add(jButtonMinus);

        jPanel32.setPreferredSize(new java.awt.Dimension(10, 25));
        jPanel25.add(jPanel32);

        jButtonRemove.setIcon(new FlatSVGIcon("svg/remove.svg",42,42));
        jButtonRemove.setFocusable(false);
        jButtonRemove.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRemove.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonRemove.setMaximumSize(new java.awt.Dimension(48, 48));
        jButtonRemove.setMinimumSize(new java.awt.Dimension(48, 48));
        jButtonRemove.setOpaque(true);
        jButtonRemove.setPreferredSize(new java.awt.Dimension(48, 48));
        jButtonRemove.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });
        jPanel25.add(jButtonRemove);

        jPanel33.setPreferredSize(new java.awt.Dimension(10, 25));
        jPanel25.add(jPanel33);

        jButtonEdit.setIcon(new FlatSVGIcon("svg/pencil.svg",42,42));
        jButtonEdit.setFocusable(false);
        jButtonEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEdit.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonEdit.setMaximumSize(new java.awt.Dimension(48, 48));
        jButtonEdit.setMinimumSize(new java.awt.Dimension(48, 48));
        jButtonEdit.setOpaque(true);
        jButtonEdit.setPreferredSize(new java.awt.Dimension(48, 48));
        jButtonEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });
        jPanel25.add(jButtonEdit);

        jPanel16.add(jPanel25, java.awt.BorderLayout.EAST);

        jPanel13.add(jPanel16, java.awt.BorderLayout.CENTER);

        jPanel23.setOpaque(false);
        jPanel23.setPreferredSize(new java.awt.Dimension(10, 100));
        jPanel23.setLayout(new java.awt.BorderLayout());

        jPanel34.setOpaque(false);
        jPanel34.setLayout(new java.awt.BorderLayout(10, 0));

        jPanel36.setOpaque(false);
        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel34.add(jPanel36, java.awt.BorderLayout.CENTER);

        jPanel37.setOpaque(false);
        jPanel37.setPreferredSize(new java.awt.Dimension(250, 10));
        jPanel37.setLayout(new java.awt.BorderLayout());

        jPanel39.setPreferredSize(new java.awt.Dimension(150, 10));
        jPanel39.setLayout(new java.awt.GridLayout(0, 1));

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShadowColor(new java.awt.Color(153, 0, 0));
        jPanel58.setBorder(dropShadowBorder1);
        jPanel58.setLayout(new java.awt.BorderLayout());

        jLabelTotal2.setFont(new java.awt.Font("Arial Narrow", 0, 26)); // NOI18N
        jLabelTotal2.setForeground(new java.awt.Color(153, 0, 0));
        jLabelTotal2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotal2.setText(" TOTAL");
        jPanel58.add(jLabelTotal2, java.awt.BorderLayout.WEST);

        jLabelTotal.setFont(new java.awt.Font("Arial Narrow", 0, 34)); // NOI18N
        jLabelTotal.setForeground(new java.awt.Color(153, 0, 0));
        jLabelTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotal.setText("0.00");
        jPanel58.add(jLabelTotal, java.awt.BorderLayout.CENTER);

        jLabel4.setPreferredSize(new java.awt.Dimension(5, 0));
        jPanel58.add(jLabel4, java.awt.BorderLayout.EAST);

        jPanel39.add(jPanel58);

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShadowColor(new java.awt.Color(0, 102, 51));
        jPanel59.setBorder(dropShadowBorder2);
        jPanel59.setLayout(new java.awt.BorderLayout());

        jLabelCash2.setFont(new java.awt.Font("Arial Narrow", 0, 26)); // NOI18N
        jLabelCash2.setForeground(new java.awt.Color(0, 102, 51));
        jLabelCash2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCash2.setText(" CASH");
        jPanel59.add(jLabelCash2, java.awt.BorderLayout.WEST);

        jLabelCash.setFont(new java.awt.Font("Arial Narrow", 0, 34)); // NOI18N
        jLabelCash.setForeground(new java.awt.Color(0, 102, 51));
        jLabelCash.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCash.setText("0.00");
        jPanel59.add(jLabelCash, java.awt.BorderLayout.CENTER);

        jLabel5.setPreferredSize(new java.awt.Dimension(5, 0));
        jPanel59.add(jLabel5, java.awt.BorderLayout.EAST);

        jPanel39.add(jPanel59);

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder3 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder3.setShadowColor(new java.awt.Color(255, 102, 0));
        jPanel60.setBorder(dropShadowBorder3);
        jPanel60.setForeground(new java.awt.Color(204, 204, 204));
        jPanel60.setLayout(new java.awt.BorderLayout());

        jLabelChange2.setFont(new java.awt.Font("Arial Narrow", 0, 26)); // NOI18N
        jLabelChange2.setForeground(new java.awt.Color(255, 153, 0));
        jLabelChange2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelChange2.setText(" CHANGE");
        jPanel60.add(jLabelChange2, java.awt.BorderLayout.WEST);

        jLabelChange.setFont(new java.awt.Font("Arial Narrow", 0, 34)); // NOI18N
        jLabelChange.setForeground(new java.awt.Color(255, 153, 0));
        jLabelChange.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelChange.setText("0.00");
        jPanel60.add(jLabelChange, java.awt.BorderLayout.CENTER);

        jLabel6.setPreferredSize(new java.awt.Dimension(5, 0));
        jPanel60.add(jLabel6, java.awt.BorderLayout.EAST);

        jPanel39.add(jPanel60);

        jPanel37.add(jPanel39, java.awt.BorderLayout.CENTER);

        jPanel46.setPreferredSize(new java.awt.Dimension(0, 10));
        jPanel37.add(jPanel46, java.awt.BorderLayout.EAST);

        jPanel34.add(jPanel37, java.awt.BorderLayout.EAST);

        jPanel23.add(jPanel34, java.awt.BorderLayout.CENTER);

        jPanel35.setOpaque(false);
        jPanel35.setPreferredSize(new java.awt.Dimension(63, 10));
        jPanel23.add(jPanel35, java.awt.BorderLayout.EAST);

        jPanel13.add(jPanel23, java.awt.BorderLayout.SOUTH);

        jPanelDispense.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel14.setMinimumSize(new java.awt.Dimension(10, 3));
        jPanel14.setOpaque(false);
        jPanel14.setPreferredSize(new java.awt.Dimension(10, 3));
        jPanelDispense.add(jPanel14, java.awt.BorderLayout.NORTH);

        jPanel15.setOpaque(false);
        jPanel15.setPreferredSize(new java.awt.Dimension(10, 70));
        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel40.setOpaque(false);
        jPanel40.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonNew.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jButtonNew.setIcon(new FlatSVGIcon("svg/new.svg",42,42));
        jButtonNew.setText("NEW");
        jButtonNew.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonNew.setPreferredSize(new java.awt.Dimension(105, 50));
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });
        jPanel40.add(jButtonNew);

        jPanel42.setPreferredSize(new java.awt.Dimension(20, 25));
        jPanel40.add(jPanel42);

        jButtonHold.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jButtonHold.setIcon(new FlatSVGIcon("svg/hold.svg",42,42));
        jButtonHold.setText("HOLD");
        jButtonHold.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonHold.setPreferredSize(new java.awt.Dimension(105, 50));
        jButtonHold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHoldActionPerformed(evt);
            }
        });
        jPanel40.add(jButtonHold);

        jPanel43.setPreferredSize(new java.awt.Dimension(20, 25));
        jPanel40.add(jPanel43);

        jButtonPayment.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jButtonPayment.setIcon(new FlatSVGIcon("svg/pay.svg",42,42));
        jButtonPayment.setText("PAY");
        jButtonPayment.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonPayment.setPreferredSize(new java.awt.Dimension(105, 50));
        jButtonPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPaymentActionPerformed(evt);
            }
        });
        jPanel40.add(jButtonPayment);

        jPanel15.add(jPanel40, java.awt.BorderLayout.CENTER);

        jPanel41.setOpaque(false);
        jPanel41.setPreferredSize(new java.awt.Dimension(57, 10));
        jPanel15.add(jPanel41, java.awt.BorderLayout.EAST);

        jPanel38.setOpaque(false);
        jPanel38.setPreferredSize(new java.awt.Dimension(3, 10));
        jPanel15.add(jPanel38, java.awt.BorderLayout.WEST);

        jPanelDispense.add(jPanel15, java.awt.BorderLayout.SOUTH);

        jPanel19.add(jPanelDispense, java.awt.BorderLayout.CENTER);

        jPanel10.setOpaque(false);
        jPanel10.setPreferredSize(new java.awt.Dimension(10, 3));
        jPanel19.add(jPanel10, java.awt.BorderLayout.SOUTH);

        jPanel11.setOpaque(false);
        jPanel11.setPreferredSize(new java.awt.Dimension(3, 10));
        jPanel19.add(jPanel11, java.awt.BorderLayout.WEST);

        jPanel12.setOpaque(false);
        jPanel12.setPreferredSize(new java.awt.Dimension(3, 10));
        jPanel19.add(jPanel12, java.awt.BorderLayout.EAST);

        jPanelCenter.add(jPanel19, java.awt.BorderLayout.WEST);

        jPanel20.setOpaque(false);
        jPanel20.setLayout(new java.awt.BorderLayout());

        jPanel17.setOpaque(false);
        jPanel17.setPreferredSize(new java.awt.Dimension(3, 10));
        jPanel20.add(jPanel17, java.awt.BorderLayout.EAST);

        jPanel18.setOpaque(false);
        jPanel18.setPreferredSize(new java.awt.Dimension(3, 10));
        jPanel20.add(jPanel18, java.awt.BorderLayout.WEST);

        jPanel21.setOpaque(false);
        jPanel21.setPreferredSize(new java.awt.Dimension(10, 3));
        jPanel20.add(jPanel21, java.awt.BorderLayout.SOUTH);

        jPanelMenus.setOpaque(false);
        jPanelMenus.setLayout(new java.awt.BorderLayout());

        jPanel44.setOpaque(false);
        jPanel44.setLayout(new java.awt.BorderLayout());

        jPanel52.setOpaque(false);
        jPanel52.setPreferredSize(new java.awt.Dimension(10, 180));
        jPanel52.setLayout(new java.awt.BorderLayout());

        jPanel53.setOpaque(false);
        jPanel53.setPreferredSize(new java.awt.Dimension(10, 2));
        jPanel52.add(jPanel53, java.awt.BorderLayout.PAGE_START);

        jPanel54.setOpaque(false);
        jPanel54.setPreferredSize(new java.awt.Dimension(10, 2));
        jPanel52.add(jPanel54, java.awt.BorderLayout.PAGE_END);

        jPanel55.setOpaque(false);
        jPanel55.setPreferredSize(new java.awt.Dimension(2, 10));
        jPanel52.add(jPanel55, java.awt.BorderLayout.LINE_END);

        jPanel56.setOpaque(false);
        jPanel56.setPreferredSize(new java.awt.Dimension(2, 10));
        jPanel52.add(jPanel56, java.awt.BorderLayout.LINE_START);

        jPanelMenuFrame.setBorder(javax.swing.BorderFactory.createTitledBorder("MENU"));
        jPanelMenuFrame.setOpaque(false);
        jPanelMenuFrame.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setOpaque(false);

        jPanelMenu.setLayout(new java.awt.BorderLayout());
        jScrollPane3.setViewportView(jPanelMenu);

        jPanelMenuFrame.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel52.add(jPanelMenuFrame, java.awt.BorderLayout.CENTER);

        jPanel44.add(jPanel52, java.awt.BorderLayout.CENTER);

        jPanelMenus.add(jPanel44, java.awt.BorderLayout.CENTER);

        jPanel45.setOpaque(false);
        jPanel45.setPreferredSize(new java.awt.Dimension(10, 180));
        jPanel45.setLayout(new java.awt.BorderLayout());

        jPanel47.setOpaque(false);
        jPanel47.setPreferredSize(new java.awt.Dimension(10, 2));
        jPanel45.add(jPanel47, java.awt.BorderLayout.PAGE_START);

        jPanel48.setOpaque(false);
        jPanel48.setPreferredSize(new java.awt.Dimension(10, 2));
        jPanel45.add(jPanel48, java.awt.BorderLayout.PAGE_END);

        jPanel49.setOpaque(false);
        jPanel49.setPreferredSize(new java.awt.Dimension(2, 10));
        jPanel45.add(jPanel49, java.awt.BorderLayout.LINE_END);

        jPanel50.setOpaque(false);
        jPanel50.setPreferredSize(new java.awt.Dimension(2, 10));
        jPanel45.add(jPanel50, java.awt.BorderLayout.LINE_START);

        jPanel51.setBorder(javax.swing.BorderFactory.createTitledBorder("CATEGORY"));
        jPanel51.setOpaque(false);
        jPanel51.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setOpaque(false);

        jPanelCategory.setOpaque(false);
        jPanelCategory.setPreferredSize(new java.awt.Dimension(10, 540));
        jPanelCategory.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        jScrollPane2.setViewportView(jPanelCategory);

        jPanel51.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel45.add(jPanel51, java.awt.BorderLayout.CENTER);

        jPanelMenus.add(jPanel45, java.awt.BorderLayout.NORTH);

        jPanel20.add(jPanelMenus, java.awt.BorderLayout.CENTER);

        jPanel22.setOpaque(false);
        jPanel22.setPreferredSize(new java.awt.Dimension(10, 3));
        jPanel20.add(jPanel22, java.awt.BorderLayout.NORTH);

        jPanelCenter.add(jPanel20, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanelCenter, java.awt.BorderLayout.CENTER);

        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(5, 10));
        jPanel1.add(jPanel5, java.awt.BorderLayout.EAST);

        jPanel6.setOpaque(false);
        jPanel1.add(jPanel6, java.awt.BorderLayout.SOUTH);

        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(10, 5));
        jPanel1.add(jPanel7, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(10, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);

        jButtonTodaysMenu.setIcon(new FlatSVGIcon("svg/recipe.svg",42,42));
        jButtonTodaysMenu.setToolTipText("Today's menu");
        jButtonTodaysMenu.setFocusable(false);
        jButtonTodaysMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonTodaysMenu.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonTodaysMenu.setMaximumSize(new java.awt.Dimension(48, 48));
        jButtonTodaysMenu.setMinimumSize(new java.awt.Dimension(48, 48));
        jButtonTodaysMenu.setPreferredSize(new java.awt.Dimension(48, 48));
        jButtonTodaysMenu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonTodaysMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTodaysMenuActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonTodaysMenu);

        jButtonSale.setIcon(new FlatSVGIcon("svg/sales.svg",42,42));
        jButtonSale.setToolTipText("User's sales");
        jButtonSale.setFocusable(false);
        jButtonSale.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSale.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonSale.setMaximumSize(new java.awt.Dimension(48, 48));
        jButtonSale.setMinimumSize(new java.awt.Dimension(48, 48));
        jButtonSale.setOpaque(true);
        jButtonSale.setPreferredSize(new java.awt.Dimension(48, 48));
        jButtonSale.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaleActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonSale);

        jButtonPrint.setIcon(new FlatSVGIcon("svg/print.svg",42,42));
        jButtonPrint.setToolTipText("User's sales report");
        jButtonPrint.setFocusable(false);
        jButtonPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPrint.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonPrint.setMaximumSize(new java.awt.Dimension(48, 48));
        jButtonPrint.setMinimumSize(new java.awt.Dimension(48, 48));
        jButtonPrint.setOpaque(true);
        jButtonPrint.setPreferredSize(new java.awt.Dimension(48, 48));
        jButtonPrint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonPrint);

        jButtonInvoice.setIcon(new FlatSVGIcon("svg/invoice.svg",42,42));
        jButtonInvoice.setToolTipText("Invoice's list");
        jButtonInvoice.setFocusable(false);
        jButtonInvoice.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonInvoice.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonInvoice.setMaximumSize(new java.awt.Dimension(48, 48));
        jButtonInvoice.setMinimumSize(new java.awt.Dimension(48, 48));
        jButtonInvoice.setOpaque(true);
        jButtonInvoice.setPreferredSize(new java.awt.Dimension(48, 48));
        jButtonInvoice.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInvoiceActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonInvoice);

        jButtonUpdatePrice.setIcon(new FlatSVGIcon("svg/edit-price.svg",42,42));
        jButtonUpdatePrice.setToolTipText("Update price");
        jButtonUpdatePrice.setFocusable(false);
        jButtonUpdatePrice.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonUpdatePrice.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonUpdatePrice.setMaximumSize(new java.awt.Dimension(48, 48));
        jButtonUpdatePrice.setMinimumSize(new java.awt.Dimension(48, 48));
        jButtonUpdatePrice.setOpaque(true);
        jButtonUpdatePrice.setPreferredSize(new java.awt.Dimension(48, 48));
        jButtonUpdatePrice.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonUpdatePrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdatePriceActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonUpdatePrice);

        jPanel2.add(jToolBar1, java.awt.BorderLayout.CENTER);

        jPanel4.setMinimumSize(new java.awt.Dimension(5, 10));
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(5, 10));
        jPanel2.add(jPanel4, java.awt.BorderLayout.WEST);

        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(5, 10));
        jPanel2.add(jPanel8, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        jMenuSystem.setIcon(new FlatSVGIcon("svg/user.svg",18,18));
        jMenuSystem.setText("File");

        jMenuItemChangePassword.setIcon(new FlatSVGIcon("svg/key.svg",18,18));
        jMenuItemChangePassword.setText("Change Password");
        jMenuItemChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemChangePasswordActionPerformed(evt);
            }
        });
        jMenuSystem.add(jMenuItemChangePassword);

        jMenuItemLogout.setIcon(new FlatSVGIcon("svg/logout.svg",18,18));
        jMenuItemLogout.setText("Logout");
        jMenuItemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLogoutActionPerformed(evt);
            }
        });
        jMenuSystem.add(jMenuItemLogout);

        jMenuItemLogin.setIcon(new FlatSVGIcon("svg/login.svg",18,18));
        jMenuItemLogin.setText("Login");
        jMenuItemLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLoginActionPerformed(evt);
            }
        });
        jMenuSystem.add(jMenuItemLogin);
        jMenuSystem.add(jSeparator1);

        jMenuItem11.setIcon(new FlatSVGIcon("svg/exit.svg",18,18));
        jMenuItem11.setText("Exit");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenuSystem.add(jMenuItem11);

        jMenuBar1.add(jMenuSystem);

        jMenuSettings.setIcon(new FlatSVGIcon("svg/settings.svg",18,18));
        jMenuSettings.setText("Settings");

        jMenuItem2.setIcon(new FlatSVGIcon("svg/category.svg",18,18));
        jMenuItem2.setText("Category");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuSettings.add(jMenuItem2);

        jMenuItem7.setIcon(new FlatSVGIcon("svg/menu-board.svg",18,18));
        jMenuItem7.setText("Menu");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenuSettings.add(jMenuItem7);

        jMenuItem8.setIcon(new FlatSVGIcon("svg/dine.svg",18,18));
        jMenuItem8.setText("Today's Menu");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenuSettings.add(jMenuItem8);

        jMenuBar1.add(jMenuSettings);

        jMenuManage.setIcon(new FlatSVGIcon("svg/manage.svg",18,18));
        jMenuManage.setText("Manage");

        jMenuItem3.setIcon(new FlatSVGIcon("svg/user.svg",18,18));
        jMenuItem3.setText("User");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuManage.add(jMenuItem3);

        jMenuBar1.add(jMenuManage);

        jMenuTheme.setIcon(new FlatSVGIcon("svg/paint-pallete.svg",18,18));
        jMenuTheme.setText("Themes");

        jMenuItem5.setIcon(new FlatSVGIcon("svg/sun.svg",18,18));
        jMenuItem5.setText("Light");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenuTheme.add(jMenuItem5);

        jMenuItem6.setIcon(new FlatSVGIcon("svg/moon.svg",18,18));
        jMenuItem6.setText("Dark");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenuTheme.add(jMenuItem6);

        jMenuBar1.add(jMenuTheme);

        jMenuHelp.setIcon(new FlatSVGIcon("svg/help.svg",18,18));
        jMenuHelp.setText("Help");

        jMenuItem1.setText("About");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItem1);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(1279, 677));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JDialogCategory category = new JDialogCategory(this, true);
        category.setVisible(true);
        selectCategory();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JDialogUserAccount userAccount = new JDialogUserAccount(this, true);
        userAccount.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(JFrameCafSys.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(JFrameCafSys.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        JDialogMenu menu = new JDialogMenu(this, true);
        menu.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        JDialogTodaysMenu todaysMenu = new JDialogTodaysMenu(this, true);
        todaysMenu.setVisible(true);
        selectCategory();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jButtonTodaysMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTodaysMenuActionPerformed
        JDialogTodaysMenu todaysMenu = new JDialogTodaysMenu(this, true);
        todaysMenu.setVisible(true);
        selectCategory();

        jPanelMenu.removeAll();
        jPanelMenu.updateUI();


    }//GEN-LAST:event_jButtonTodaysMenuActionPerformed

    private void jMenuItemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLogoutActionPerformed

        logOut();
//        dispose();
//        JFrameLogin login = new JFrameLogin();
//        login.setVisible(true);


    }//GEN-LAST:event_jMenuItemLogoutActionPerformed

    private void jButtonHoldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHoldActionPerformed
        if (jButtonHold.getText().equals("HOLD")) {
            jButtonRemove.setEnabled(false);
            jButtonEdit.setEnabled(false);
            jButtonAdd.setEnabled(false);
            jButtonMinus.setEnabled(false);
            holdOrders();
            jButtonHold.setText("RELEASE");
            jLabelCash.setText("0.00");
            jLabelTotal.setText("0.00");
            jLabelChange.setText("0.00");
            tableModel.setRowCount(0);
            jButtonPayment.setEnabled(false);
        } else {
            releaseOrder();
            jButtonHold.setText("HOLD");
            autoCalulateTable();
        }
    }//GEN-LAST:event_jButtonHoldActionPerformed

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed
        removeMenu();
        autoCalulateTable();
        if (jTable1.getRowCount() > 0) {
            jButtonNew.setEnabled(true);
            jButtonHold.setEnabled(true);
            jButtonPayment.setEnabled(true);
        } else {
            jButtonNew.setEnabled(false);
            jButtonHold.setEnabled(false);
            jButtonPayment.setEnabled(false);
        }
        getButtonHoldStatus();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            jTable1.setValueAt(i + 1, i, 1);
        }


    }//GEN-LAST:event_jButtonRemoveActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        editQty();
        autoCalulateTable();
    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        try {
            Integer cQrty = 0;
            int selected = jTable1.getSelectedRow();
            Object qty = jTable1.getValueAt(selected, 3);
            int pcs = (int) qty;
            Object purchasePrice = jTable1.getValueAt(selected, 7);
            float sum = 0.0f;
            cQrty = pcs + 1;
            float purchaseP = Float.parseFloat(purchasePrice.toString());
            sum = purchaseP * (float) cQrty;

            jTable1.setValueAt(df.format(sum), selected, 5);
            jTable1.setValueAt(sum, selected, 6);
            jTable1.setValueAt(cQrty, selected, 3);
            jButtonEdit.setEnabled(false);
            jButtonRemove.setEnabled(false);
            jButtonAdd.setEnabled(false);
            jButtonMinus.setEnabled(false);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        autoCalulateTable();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMinusActionPerformed
        try {
            Integer cQrty = 0;
            int selected = jTable1.getSelectedRow();
            Object qty = jTable1.getValueAt(selected, 3);
            int pcs = (int) qty;
            Object purchasePrice = jTable1.getValueAt(selected, 7);
            float sum = 0.0f;
            cQrty = pcs - 1;
            float purchaseP = Float.parseFloat(purchasePrice.toString());
            sum = purchaseP * (float) cQrty;
            if (cQrty == 0) {
                jButtonRemove.setEnabled(true);
                JOptionPane.showMessageDialog(this, "Quantity is one, just click remove button if you want to remove item.", "Warning, quantity is one!!", JOptionPane.PLAIN_MESSAGE);
            } else {
                jTable1.setValueAt(df.format(sum), selected, 5);
                jTable1.setValueAt(sum, selected, 6);
                jTable1.setValueAt(cQrty, selected, 3);
                jButtonEdit.setEnabled(false);
                jButtonRemove.setEnabled(false);
                jButtonAdd.setEnabled(false);
                jButtonMinus.setEnabled(false);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        autoCalulateTable();
    }//GEN-LAST:event_jButtonMinusActionPerformed

    private InvoiceDAOImpl invoiceDAOImpl = new InvoiceDAOImpl();
    private InvoiceLineMenuDAOImpl invoiceLineMenuDAOImpl = new InvoiceLineMenuDAOImpl();
    private void jButtonPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPaymentActionPerformed
        JDialogPayment payment = new JDialogPayment(this, true, getTotalAmount());
        payment.setVisible(true);
        if (getPayment() == true) {
            Invoice invoice = new Invoice();
            TodaysMenu todaysMenu = new TodaysMenu();
            UserAccount userAccount = new UserAccount();
//            //Invoice set input
            invoice.setCustomerCash(getCash());
            invoice.setTableNo(getTableNo());
            invoice.setOrderOption(getOrderOption());
            invoice.setTotal(getTotalAmount());
            invoice.setNoOfOrders(jTable1.getRowCount());
            userAccount.setId(loginUser.getUserAccount().getId());
            invoice.setUserAccount(userAccount);
            invoiceDAOImpl.addInvoice(invoice);
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                InvoiceLineMenu invoiceLineMenu = new InvoiceLineMenu();
                Long todaysMenuId = (Long) jTable1.getValueAt(i, 0);
                Integer qty = (Integer) jTable1.getValueAt(i, 3);
                invoiceLineMenu.setQty(qty);
                todaysMenu.setId(todaysMenuId);
                invoiceLineMenu.setTodaysMenu(todaysMenu);
                invoice.setId(invoiceDAOImpl.getMaxId());
                invoiceLineMenu.setInvoice(invoice);
                invoiceLineMenuDAOImpl.addInvoiceLineMenu(invoiceLineMenu);
            }
            //null static invoice instance

            jButtonHold.setEnabled(false);
            jButtonPayment.setEnabled(false);
            jLabelCash.setText(df.format(getCash()));
            jLabelChange.setText(df.format(getChange()));
            jLabelTotal.setText(String.valueOf(df.format(getCash() - getChange())));
            this.setOrderOption(null);
            this.setTableNo(null);
            this.setTotalAmount(null);
            this.setCash(null);

        }
        getButtonHoldStatus();
    }//GEN-LAST:event_jButtonPaymentActionPerformed

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "Are you sure to create new transaction?", "New order confirmation", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            tableModel.setRowCount(0);
            jLabelCash.setText("0.00");
            jLabelChange.setText("0.00");
            jLabelTotal.setText("0.00");
            jButtonRemove.setEnabled(false);
            jButtonEdit.setEnabled(false);
            jButtonAdd.setEnabled(false);
            jButtonMinus.setEnabled(false);
            jButtonPayment.setEnabled(false);
            jButtonNew.setEnabled(false);
            autoCalulateTable();
            getButtonHoldStatus();
            if (jButtonHold.getText().equals("RELEASE")) {
                jButtonHold.setEnabled(true);
            } else {
                jButtonHold.setEnabled(false);
            }
            JOptionPane.showConfirmDialog(this, "Creating new order.", "Message", JOptionPane.PLAIN_MESSAGE);
        }
        getButtonHoldStatus();
    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jButtonInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInvoiceActionPerformed
        JDialogViewInvoice invoice = new JDialogViewInvoice(this, true);
        invoice.setVisible(true);
    }//GEN-LAST:event_jButtonInvoiceActionPerformed

    private void jButtonSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaleActionPerformed
        invoiceDAOImpl.invoiceDateTimeComboBox(loginUser.getUserAccount().getId());
        if (invoiceDAOImpl.getList().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No sales yet.", "Message", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            JDialogSale sale = new JDialogSale(this, true);
            sale.setVisible(true);
        }
    }//GEN-LAST:event_jButtonSaleActionPerformed

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
        invoiceDAOImpl.invoiceDateTimeComboBox(loginUser.getUserAccount().getId());
        if (invoiceDAOImpl.getList().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No sales yet.", "Message", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            JDialogReport report = new JDialogReport(this, true);
            report.setVisible(true);
        }
    }//GEN-LAST:event_jButtonPrintActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JDialogAbout about = new JDialogAbout(this, true);
        about.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        quitApp();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItemLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLoginActionPerformed
        login();
    }//GEN-LAST:event_jMenuItemLoginActionPerformed

    private void jMenuItemChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemChangePasswordActionPerformed
        JDialogChangePassword cPassword = new JDialogChangePassword(this, true);
        cPassword.setVisible(true);
    }//GEN-LAST:event_jMenuItemChangePasswordActionPerformed

    private void jButtonUpdatePriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdatePriceActionPerformed
        JDialogEditPrice editPrice = new JDialogEditPrice(this, true);
        editPrice.setVisible(true);
    }//GEN-LAST:event_jButtonUpdatePriceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton jButtonAdd;
    private static javax.swing.JButton jButtonEdit;
    public javax.swing.JButton jButtonHold;
    private javax.swing.JButton jButtonInvoice;
    private static javax.swing.JButton jButtonMinus;
    public javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonPayment;
    private javax.swing.JButton jButtonPrint;
    private static javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonSale;
    private javax.swing.JButton jButtonTodaysMenu;
    private javax.swing.JButton jButtonUpdatePrice;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelCash;
    private javax.swing.JLabel jLabelCash2;
    private javax.swing.JLabel jLabelChange;
    private javax.swing.JLabel jLabelChange2;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JLabel jLabelTotal2;
    public static javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItemChangePassword;
    private javax.swing.JMenuItem jMenuItemLogin;
    private javax.swing.JMenuItem jMenuItemLogout;
    private javax.swing.JMenu jMenuManage;
    private javax.swing.JMenu jMenuSettings;
    private javax.swing.JMenu jMenuSystem;
    private javax.swing.JMenu jMenuTheme;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelCategory;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelDispense;
    private javax.swing.JPanel jPanelMenu;
    public static javax.swing.JPanel jPanelMenuFrame;
    private javax.swing.JPanel jPanelMenus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
 private static boolean multipleOrder = false;
    public DefaultTableModel tableModel = new DefaultTableModel(new Object[]{
        "ID",
        "#",
        "ITEM",
        "QTY",
        "<html><center>EACH<br>Price</center></html>",
        "<html><center>Line<br>Total</center></html>",
        "Line Total UF",
        "Price UF"}, 0) { //7   ID,#,ITEM,QTY,UNIT PRICE,LINE TOTAL, LINETOTAL UF, PRICE UF
        public Class getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return String.class;
            }
            switch (columnIndex) {
                case 1:
                    return Integer.class;
                case 2:
                    return String.class;
                case 3:
                    return Integer.class;
                case 4:
                    return Integer.class;
                case 5:
                    return Integer.class;
                case 6:
                    return Integer.class;
                case 7:
                    return Integer.class;
                default:
                    return String.class;
            }
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 10) {
                return false;

            } else {
                return true;
            }
        }

    };
    private TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);

    private void setTable() {
        jTable1 = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                if (isRowSelected(row)) {
                    component.setBackground(getSelectionBackground());
                } else {
                    component.setBackground(row % 2 == 0 ? getBackground() : Color.decode("#F5F5F5")); // Alternate row color
                }
                return component;
            }
        };
        JTableHeader header = jTable1.getTableHeader();
        header.setPreferredSize(new Dimension(100, 45));
        header.setFont(new Font("Courier New", Font.PLAIN, 16));
        header.setBackground(new java.awt.Color(204, 204, 204));
        header.setForeground(new java.awt.Color(51, 51, 51));
        jTable1.setRowSorter(sorter);
        jTable1.addMouseListener(this);
        jTable1.setRowHeight(30);
        jTable1.setFont(new Font("Courier New", Font.PLAIN, 18));
        jScrollPane1.setViewportView(jTable1);
        TableColumn hide0 = jTable1.getColumnModel().getColumn(0);
        hide0.setMinWidth(0);
        hide0.setMaxWidth(0);
        hide0.setPreferredWidth(0);
        TableColumn hide5 = jTable1.getColumnModel().getColumn(6);
        hide5.setMinWidth(0);
        hide5.setMaxWidth(0);
        hide5.setPreferredWidth(0);
        TableColumn hide6 = jTable1.getColumnModel().getColumn(7);
        hide6.setMinWidth(0);
        hide6.setMaxWidth(0);
        hide6.setPreferredWidth(0);

//"STOCK IN ID", "UNIT", "PRODUCT", "QUANTITY", "PRICE", "LINE TOTAL"
        TableColumn[] column = new TableColumn[100];
        column[1] = jTable1.getColumnModel().getColumn(1);
        column[1].setPreferredWidth(30);

        column[2] = jTable1.getColumnModel().getColumn(2);
        column[2].setPreferredWidth(230);

        column[3] = jTable1.getColumnModel().getColumn(3);
        column[3].setPreferredWidth(80);

        column[4] = jTable1.getColumnModel().getColumn(4);
        column[4].setPreferredWidth(60);

        column[5] = jTable1.getColumnModel().getColumn(5);
        column[5].setPreferredWidth(100);

        column[6] = jTable1.getColumnModel().getColumn(6);
        column[6].setPreferredWidth(100);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jTable1) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (e.getClickCount() == 1) {
                    int row = jTable1.getSelectedRow();
                    if (row != -1) {

                        jButtonRemove.setEnabled(true);
                        jButtonEdit.setEnabled(true);
                        jButtonAdd.setEnabled(true);
                        jButtonMinus.setEnabled(true);
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
    private ArrayList<Category> categoryList;
    private TodaysMenuDAOImpl todaysMenuDAOImpl = new TodaysMenuDAOImpl();
    private LinkedList<TodaysMenu> todaysMenuList;

    public void selectCategory() {
        categoryList = todaysMenuDAOImpl.selectTodaysMenuCategory();
        jPanelCategory.removeAll();
        jPanelCategory.updateUI();
        for (Category c : categoryList) {
            JButton jButtonCategory = new JButton();
            jButtonCategory.setText(String.valueOf(c.getCategory()));

            jButtonCategory.setPreferredSize(new java.awt.Dimension(100, 44));
            jButtonCategory.addActionListener(new ActionListenerHandler(categoryList, this));
            jButtonCategory.setMargin(new java.awt.Insets(2, 2, 2, 2));
            jButtonCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jButtonCategory.setFont(new java.awt.Font("Segoe UI", 0, 10));
            if (displayPictureCategory(c) == null) {
            } else {
                jButtonCategory.setIcon(displayPictureCategory(c));
            }
            jPanelCategory.add(jButtonCategory);
        }
        disableSideButton();
    }

    public void selectTodaysMenu(Long id) {
        todaysMenuList = todaysMenuDAOImpl.getMenuByCategoryIdMenuId(id);
        //able to display the menus when category button is clicked, public not static
        jPanelMenu.removeAll();
        jPanelMenu.updateUI();
        JPanel panelRecipe = new JPanel(new java.awt.GridLayout(0, 4, 10, 5));
        JScrollPane mainScrollPane = new JScrollPane();
        for (TodaysMenu motd : todaysMenuList) {
            JPanel newPanel = new JPanel();

            Menu menu = new Menu();
            menu.setId(motd.getMenu().getId());
            JButton jButtonRecipe = new JButton();
            jButtonRecipe.setText(String.valueOf(motd.getMenu().getRecipe()));
            jButtonRecipe.setPreferredSize(new java.awt.Dimension(120, 120));
            jButtonRecipe.addActionListener(new ActionListenerHandler(categoryList, this));
            if (displayPicture(menu) == null) {
            } else {
                jButtonRecipe.setIcon(displayPicture(menu));
            }
//            jButtonRecipe.setBackground(new Color(192,192,192));
            jButtonRecipe.setName(String.valueOf(motd.getId()));
            jButtonRecipe.setText("<html><body style =\"text-indent: -2em;padding:0;margin:0;\"><p style=\"font-size: 10; color:red;\">" + motd.getMenu().getRecipe() + "</p><p  style=\"font-weight: 50%; color:blue;\">" + df.format(motd.getPrice()) + "</p></body></html>");
            jButtonRecipe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jButtonRecipe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            jButtonRecipe.addActionListener(new ActionListenerHandler(jButtonRecipe, todaysMenuList, this));
            jButtonRecipe.setMargin(new java.awt.Insets(2, 2, 2, 2));
            jButtonRecipe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            panelRecipe.add(jButtonRecipe);

            mainScrollPane.setViewportView(panelRecipe);

            jPanelMenu.add(mainScrollPane, java.awt.BorderLayout.CENTER);
            jPanelMenu.validate();
            jPanelMenuFrame.validate();
        }
        disableSideButton();
    }

//    private CategoryDAOImpl categoryDAOImpl = new CategoryDAOImpl();
    private ImageIcon displayPictureCategory(Category category) {
        ImageIcon img = null;
        int IMG_WIDTH = 40;
        int IMG_HEIGHT = 30;
        try {
            Category m = categoryDAOImpl.findPhotoByCategoryId(category);
            byte[] imageData = m.getPhoto();
            ImageIcon imageIcon = new ImageIcon(imageData);

            Image image = imageIcon.getImage();
            int type = BufferedImage.TYPE_INT_ARGB;

            BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(image, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
            g.dispose();
            g.setComposite(AlphaComposite.Src);

            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            img = new ImageIcon(resizedImage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    private MenuDAOImpl menuDAOImpl = new MenuDAOImpl();

    private ImageIcon displayPicture(Menu menu) {
        ImageIcon img = null;
        int IMG_WIDTH = 110;
        int IMG_HEIGHT = 70;
        try {
            Menu m = menuDAOImpl.findPhotoByMenuId(menu);
            byte[] imageData = m.getPhoto();
            ImageIcon imageIcon = new ImageIcon(imageData);

            Image image = imageIcon.getImage();
            int type = BufferedImage.TYPE_INT_ARGB;

            BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(image, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
            g.dispose();
            g.setComposite(AlphaComposite.Src);

            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            img = new ImageIcon(resizedImage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    public void addOrderToTable(TodaysMenu tm) {
        try {
//            id,#,menu,qty,unit price, line total,price UF,totl UF
            boolean multipleOrder = true;
//            MOTD m = query.addOrderToTable(motd);
            if (tableModel.getRowCount() == 0) {
                tableModel.addRow(new Object[]{tm.getId(), jTable1.getRowCount() + 1, tm.getMenu().getRecipe(), 1, df.format(tm.getPrice()), df.format(tm.getPrice()), tm.getPrice(), tm.getPrice()});
                jButtonNew.setEnabled(true);
                jButtonHold.setEnabled(true);
                jButtonPayment.setEnabled(true);
                autoCalulateTable();
                return;
            } else {
                int sum = 0;
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    Object productIdl = jTable1.getValueAt(i, 0);
                    int producId = Integer.parseInt(productIdl.toString());
                    //  id,#,menu,qty,unit price, line total,price UF,total UF
                    if (producId == tm.getId()) {
                        //edit quantity
                        Object qtyFirstl = jTable1.getValueAt(i, 3);
                        int qty = Integer.parseInt(qtyFirstl.toString());
                        sum = qty + 1;
                        jTable1.setValueAt(sum, i, 3);//qty
                        jTable1.setValueAt(df.format(sum * tm.getPrice()), i, 5);
                        jTable1.setValueAt(sum * tm.getPrice(), i, 6);
                        setMultipleOrder(true);
                    }
                }
                if (isMultipleOrder() == false) {
                    //  id,#,menu,qty,unit price, line total,price UF,totl UF
                    tableModel.addRow(new Object[]{tm.getId(), jTable1.getRowCount() + 1, tm.getMenu().getRecipe(), 1, df.format(tm.getPrice()), df.format(tm.getPrice()), tm.getPrice(), tm.getPrice()});

                }
            }
            setMultipleOrder(false);

            if (jTable1.getRowCount() > 0) {
                jButtonNew.setEnabled(true);
                jButtonHold.setEnabled(true);
                jButtonPayment.setEnabled(true);
            } else {
                jButtonNew.setEnabled(false);
                jButtonHold.setEnabled(false);
                jButtonPayment.setEnabled(false);
            }
            autoCalulateTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
        getButtonHoldStatus();
    }

    /**
     * @return the multipleOrder
     */
    public static boolean isMultipleOrder() {
        return multipleOrder;
    }

    /**
     * @param aMultipleOrder the multipleOrder to set
     */
    public static void setMultipleOrder(boolean aMultipleOrder) {
        multipleOrder = aMultipleOrder;
    }

    public void autoCalulateTable() {
        try {
            float total = 0.0F;
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                float lineTotal = Float.parseFloat(jTable1.getValueAt(i, 6).toString());
                total = total + lineTotal;
                setTotalAmount(total);
                jLabelTotal.setText(String.valueOf(df.format(total)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jTable1.getRowCount() < 1) {
            jLabelTotal.setText(String.valueOf(df.format(0.00F)));
        }
    }

    private void removeMenu() {
        try {
            if (jTable1.getRowCount() == 0) {
                jButtonPayment.setEnabled(false);
                return;
            }
            int numRows = jTable1.getSelectedRows().length;
            if (numRows == 0) {
                return;
            }
            for (int i = 0; i < numRows; i++) {
                tableModel.removeRow(jTable1.getSelectedRow());
            }
            jButtonRemove.setEnabled(false);
            jButtonEdit.setEnabled(false);
            jButtonAdd.setEnabled(false);
            jButtonMinus.setEnabled(false);
            jButtonNew.setEnabled(false);

        } catch (Exception e) {

        }
    }

    private void editQty() {
        try {
            int selected = jTable1.getSelectedRow();
            Object qty = jTable1.getValueAt(selected, 3);
            int pcs = (int) qty;
            String q = JOptionPane.showInputDialog(this, "Change the quantity in text", pcs);
            int qty1 = 0;

            if (q != null) {
                qty1 = Integer.parseInt(q);
            } else {
                qty1 = pcs;
            }
            if (qty1 == 0) {
                JOptionPane.showMessageDialog(this, "Invalid input!!", "Warning message!!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //  id,#,menu,qty,unit price, line total,price UF,total UF
            Object purchasePrice = jTable1.getValueAt(selected, 7);
            float sum = 0.0f;
            float purchaseP = Float.parseFloat(purchasePrice.toString());
            sum = purchaseP * (float) qty1;
            jTable1.setValueAt(df.format(sum), selected, 5);
            jTable1.setValueAt(sum, selected, 6);
            jTable1.setValueAt(qty1, selected, 3);
            if (tableModel.getRowCount() < 1) {
                jButtonEdit.setEnabled(false);
//                jButtonPayment.setEnabled(false);
                jButtonRemove.setEnabled(false);
            }
            jButtonEdit.setEnabled(false);
            jButtonRemove.setEnabled(false);
            jButtonAdd.setEnabled(false);
            jButtonMinus.setEnabled(false);
//            autoCalulateTable();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
    }

    private void disableSideButton() {
        jButtonAdd.setEnabled(false);
        jButtonMinus.setEnabled(false);
        jButtonEdit.setEnabled(false);
        jButtonRemove.setEnabled(false);
    }

    /**
     * @return the cash
     */
    public static Float getCash() {
        return cash;
    }

    /**
     * @param aCash the cash to set
     */
    public static void setCash(Float aCash) {
        cash = aCash;
    }

    /**
     * @return the change
     */
    public static Float getChange() {
        return change;
    }

    /**
     * @param aChange the change to set
     */
    public static void setChange(Float aChange) {
        change = aChange;
    }

    /**
     * @return the payment
     */
    public static Boolean getPayment() {
        return payment;
    }

    /**
     * @param aPayment the payment to set
     */
    public static void setPayment(Boolean aPayment) {
        payment = aPayment;
    }

    /**
     * @return the totalAmount
     */
    public static Float getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param aTotalAmount the totalAmount to set
     */
    public static void setTotalAmount(Float aTotalAmount) {
        totalAmount = aTotalAmount;
    }

    /**
     * @return the takingMeals
     */
    public static String getTakingMeals() {
        return takingMeals;
    }

    /**
     * @param aTakingMeals the takingMeals to set
     */
    public static void setTakingMeals(String aTakingMeals) {
        takingMeals = aTakingMeals;
    }

    /**
     * @return the tableNo
     */
    public static Integer getTableNo() {
        return tableNo;
    }

    /**
     * @param aTableNo the tableNo to set
     */
    public static void setTableNo(Integer aTableNo) {
        tableNo = aTableNo;
    }
    private OrdersHold orderHold = new OrdersHold();

    private void holdOrders() {
//          ID,#,ITEM,QTY,UNIT PRICE,LINE TOTAL, LINETOTAL UF,UNIT PRICE UF
        ArrayList<OrdersHold> ordersHoldList = new ArrayList<>();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            OrdersHold oh = new OrdersHold();
            Integer id = Integer.parseInt(jTable1.getValueAt(i, 0).toString());
            String menu = String.valueOf(jTable1.getValueAt(i, 2).toString());
            Integer qty = Integer.parseInt(jTable1.getValueAt(i, 3).toString());
            String unitPrice = jTable1.getValueAt(i, 4).toString();
            String lineTotal = jTable1.getValueAt(i, 5).toString();
            String lineTotalUF = jTable1.getValueAt(i, 6).toString();
            String unitPriceUF = jTable1.getValueAt(i, 7).toString();
            oh.setId(id);
            oh.setQty(qty);
            oh.setRecipe(menu);
            oh.setUnitPrice(unitPrice);
            oh.setLineTotal(lineTotal);
            oh.setLineTotalUF(lineTotalUF);
            oh.setUnitPriceUF(unitPriceUF);
            ordersHoldList.add(oh);
        }
        orderHold.setOrdersHold(ordersHoldList);
    }

    private void releaseOrder() {
        tableModel.setRowCount(0);
        for (OrdersHold o : orderHold.getOrdersHold()) {
            tableModel.addRow(new Object[]{o.getId(), jTable1.getRowCount() + 1, o.getRecipe(), o.getQty(), o.getUnitPrice(), o.getLineTotal(), o.getLineTotalUF(), o.getUnitPriceUF()});
        }
    }

    private void getButtonHoldStatus() {
        if (jTable1.getRowCount() == 0) {
            jButtonHold.setEnabled(false);
        } else if (jButtonHold.getText().equals("RELEASE")) {
            jButtonHold.setEnabled(true);
        } else {
            jButtonHold.setEnabled(true);
        }
    }

    private void getButtonNewStatus() {
        if (jTable1.getRowCount() == 0) {
            jButtonNew.setEnabled(false);
        } else {
            jButtonHold.setEnabled(true);
        }
    }

    /**
     * @return the orderOption
     */
    public static OrderOption getOrderOption() {
        return orderOption;
    }

    /**
     * @param aOrderOption the orderOption to set
     */
    public static void setOrderOption(OrderOption aOrderOption) {
        orderOption = aOrderOption;
    }

    /**
     * @return the jMenuUser
     */
    public javax.swing.JMenu getjMenuUser() {
        return jMenuSystem;
    }

    /**
     * @param jMenuUser the jMenuUser to set
     */
    public void setjMenuUser(javax.swing.JMenu jMenuUser) {
        this.jMenuSystem = jMenuUser;
    }

    private void quitApp() {
        try {
            int reply = JOptionPane.showConfirmDialog(this,
                    "Are you sure to exit CafSysv2 application?",
                    "AldrinPOS - Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (reply == JOptionPane.YES_OPTION) {
                System.exit(0);        //Close the Application.
            } else if (reply == JOptionPane.NO_OPTION) {
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the userLogin
     */
    public static UserAccount getUserLogin() {
        return userLogin;
    }

    /**
     * @param aUserLogin the userLogin to set
     */
    public static void setUserLogin(UserAccount aUserLogin) {
        userLogin = aUserLogin;
    }

    /**
     * @return the jMenuBar1
     */
    public static javax.swing.JMenuBar getjMenuBar1() {
        return jMenuBar1;
    }

    /**
     * @param ajMenuBar1 the jMenuBar1 to set
     */
    public static void setjMenuBar1(javax.swing.JMenuBar ajMenuBar1) {
        jMenuBar1 = ajMenuBar1;
    }

    private void login() {
        JDialogLogin logIn = new JDialogLogin(this, true);
        logIn.setVisible(true);
        if (loginUser.getUserAccount() != null) {
            System.out.println("loginUser is not null" + loginUser.getUserAccount().getSurname() + "," + loginUser.getUserAccount().getFirstname());
            jMenuItemLogout.setVisible(true);
            jMenuItemLogin.setVisible(false);
            jMenuItemChangePassword.setVisible(true);
            jMenuSystem.setEnabled(true);
            jMenuSystem.setText(loginUser.getUserAccount().getSurname() + ", " + loginUser.getUserAccount().getFirstname());
            jMenuHelp.setEnabled(true);
            jMenuSettings.setEnabled(true);
            jMenuTheme.setEnabled(true);
//            jMenuManage.setEnabled(true);

            jLabelCash.setEnabled(true);
            jLabelChange.setEnabled(true);
            jLabelTotal.setEnabled(true);
            jLabelCash.setText("0.00");
            jLabelChange.setText("0.00");
            jLabelTotal.setText("0.00");
            jLabelCash2.setEnabled(true);
            jLabelChange2.setEnabled(true);
            jLabelTotal2.setEnabled(true);

            jPanel1.setEnabled(true);
            jPanel2.setEnabled(true);
            selectCategory();
            tableModel.setRowCount(0);
            JTableHeader header = jTable1.getTableHeader();
            header.setPreferredSize(new Dimension(100, 45));
            header.setFont(new Font("Courier New", Font.PLAIN, 16));
            header.setBackground(new java.awt.Color(0, 102, 153));
            header.setForeground(Color.white);

            jTable1.setEnabled(true);

//        JButton
            jButtonTodaysMenu.setEnabled(true);
            jButtonUpdatePrice.setEnabled(true);
            jButtonSale.setEnabled(true);
            jButtonInvoice.setEnabled(true);
            jButtonPrint.setEnabled(true);

            jButtonPayment.setEnabled(false);
            jButtonHold.setEnabled(false);
            jButtonNew.setEnabled(false);

            jButtonMinus.setEnabled(false);
            jButtonEdit.setEnabled(false);
            jButtonAdd.setEnabled(false);
            jButtonRemove.setEnabled(false);
            loginRole();
        }

    }

    private void logOut() {
        jMenuItemChangePassword.setVisible(false);
        jMenuItemLogout.setVisible(false);
        jMenuItemLogin.setVisible(true);
        jMenuSystem.setText("CafSysv2");
        jMenuHelp.setEnabled(false);
        jMenuSettings.setEnabled(false);
        jMenuTheme.setEnabled(false);
        jMenuManage.setEnabled(false);

        jLabelCash.setEnabled(false);
        jLabelChange.setEnabled(false);
        jLabelTotal.setEnabled(false);
        jLabelCash.setText("0.00");
        jLabelChange.setText("0.00");
        jLabelTotal.setText("0.00");
        jLabelCash2.setEnabled(false);
        jLabelChange2.setEnabled(false);
        jLabelTotal2.setEnabled(false);

        jPanel1.setEnabled(false);
        jPanel2.setEnabled(false);
        jPanel26.validate();

        jPanelCategory.removeAll();
        jPanelMenu.removeAll();
        tableModel.setRowCount(0);
        JTableHeader header = jTable1.getTableHeader();
        header.setPreferredSize(new Dimension(100, 45));
        header.setFont(new Font("Courier New", Font.PLAIN, 16));
        header.setBackground(new java.awt.Color(187, 187, 187));
        header.setForeground(new java.awt.Color(51, 51, 51));
        jTable1.setEnabled(false);

//        JButton
        jButtonTodaysMenu.setEnabled(false);
        jButtonUpdatePrice.setEnabled(false);
        jButtonSale.setEnabled(false);
        jButtonRemove.setEnabled(false);
        jButtonPrint.setEnabled(false);
        jButtonPayment.setEnabled(false);
        jButtonNew.setEnabled(false);
        jButtonMinus.setEnabled(false);
        jButtonInvoice.setEnabled(false);
        jButtonHold.setEnabled(false);
        jButtonEdit.setEnabled(false);
        jButtonAdd.setEnabled(false);
        loginUser.setUserAccount(null);
        login();

    }

    class ActionListenerHandler implements ActionListener {

        private ArrayList<Category> categoryList;
        private LinkedList<Menu> menuList;
        private LinkedList<TodaysMenu> motdList;
        private JButton jButton;
        private ArrayList<JButton> arrayButtons = new ArrayList<>();
        private JFrameCafSys jFrameCafSys;

        public ActionListenerHandler() {
        }

        public ActionListenerHandler(ArrayList<Category> categoryList, JFrameCafSys jFrameCafSys) {
            this.categoryList = categoryList;
            this.jFrameCafSys = jFrameCafSys;
        }

        public ActionListenerHandler(LinkedList<Menu> menuList, JFrameCafSys jFrameCafSys) {
            this.menuList = menuList;
            this.jFrameCafSys = jFrameCafSys;
        }

        public ActionListenerHandler(JButton jButton, LinkedList<TodaysMenu> motdList, JFrameCafSys jFrameCafSys) {
            this.setjButton(jButton);
            this.motdList = motdList;
            this.jFrameCafSys = jFrameCafSys;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //Category Event
            String command = e.getActionCommand();
            if (getCategoryList() != null) {
                for (Category c : getCategoryList()) {
                    if (command.equals(c.getCategory())) {
//                    JFrameCafSys p = new JFrameCafSys(c,jFrameCafSys);
                        selectTodaysMenu(c.getId());
                        revalidate();
                    }
                }
            }
            //Today Menu Events
            JButton jButton = new JButton();
            if (e.getSource() == getjButton()) {
                for (TodaysMenu motd : getMotdList()) {
                    TodaysMenu m = new TodaysMenu();
                    m.setId(Long.parseLong(getjButton().getName()));
                    if (motd.getId() == Integer.parseInt(getjButton().getName())) {
//                     JFrameCafSys p = new JFrameCafSys(motd);
                        addOrderToTable(motd);

                    }
                }
            }
        }

        /**
         * @return the categoryList
         */
        public ArrayList<Category> getCategoryList() {
            return categoryList;
        }

        /**
         * @param categoryList the categoryList to set
         */
        public void setCategoryList(ArrayList<Category> categoryList) {
            this.categoryList = categoryList;
        }

        /**
         * @return the menuList
         */
        public LinkedList<Menu> getMenuList() {
            return menuList;
        }

        /**
         * @param menuList the menuList to set
         */
        public void setMenuList(LinkedList<Menu> menuList) {
            this.menuList = menuList;
        }

        /**
         * @return the jButton
         */
        public JButton getjButton() {
            return jButton;
        }

        /**
         * @param jButton the jButton to set
         */
        public void setjButton(JButton jButton) {
            this.jButton = jButton;
        }

        /**
         * @return the arrayButtons
         */
        public ArrayList<JButton> getArrayButtons() {
            return arrayButtons;
        }

        /**
         * @param arrayButtons the arrayButtons to set
         */
        public void setArrayButtons(ArrayList<JButton> arrayButtons) {
            this.arrayButtons = arrayButtons;
        }

        /**
         * @return the motdList
         */
        public LinkedList<TodaysMenu> getMotdList() {
            return motdList;
        }

        /**
         * @param motdList the motdList to set
         */
        public void setMotdList(LinkedList<TodaysMenu> motdList) {
            this.motdList = motdList;
        }
    }

    /**
     * @return the loginUser
     */
    public LoginUser getLoginUser() {
        return loginUser;
    }

    /**
     * @param loginUser the loginUser to set
     */
    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    private void loginRole() {
        String role = loginUser.getUserAccount().getRole().getRole();
        if (role.toString().equals("ADMIN")) {
            jMenuManage.setVisible(true);
            jMenuManage.setEnabled(true);
        } else if (role.equals("USER")) {
            jMenuManage.setVisible(false);
        }
    }
}
