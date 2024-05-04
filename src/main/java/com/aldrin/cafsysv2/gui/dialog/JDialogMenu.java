/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.aldrin.cafsysv2.gui.dialog;

import com.aldrin.cafsysv2.dao.impl.MenuDAOImpl;
import com.aldrin.cafsysv2.gui.JFrameCafSys;
import com.aldrin.cafsysv2.gui.dialog.au.JDialogMenuAU;
import com.aldrin.cafsysv2.model.Category;
import com.aldrin.cafsysv2.model.Menu;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ALDRIN B. C.
 */
public class JDialogMenu extends javax.swing.JDialog implements MouseListener {

    /**
     * Creates new form JDialogCourse
     */
    private JFrameCafSys jFrameCafSys;
    private Menu menu;

    public JDialogMenu(JFrameCafSys jFrameCafSys, boolean modal) {
        super(jFrameCafSys, modal);
        this.jFrameCafSys = jFrameCafSys;
        initComponents();
        setTable();
        selectMenus();
        jTextFieldSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        //icon
        jTextFieldSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("svg/search.svg", 24, 24));
        jButtonDelete.setEnabled(false);
        jButtonUpdate.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUser = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButtonNew = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MENU");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jTableUser.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableUser);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel6.setPreferredSize(new java.awt.Dimension(10, 425));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, java.awt.BorderLayout.WEST);

        jPanel7.setPreferredSize(new java.awt.Dimension(10, 425));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, java.awt.BorderLayout.EAST);

        jPanel8.setPreferredSize(new java.awt.Dimension(886, 10));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 898, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(886, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));

        jButtonNew.setIcon(new FlatSVGIcon("svg/add.svg",24,24));
        jButtonNew.setText("New");
        jButtonNew.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonNew.setPreferredSize(new java.awt.Dimension(80, 32));
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonNew);

        jButtonUpdate.setIcon(new FlatSVGIcon("svg/edit.svg",24,24));
        jButtonUpdate.setText("Update");
        jButtonUpdate.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonUpdate.setPreferredSize(new java.awt.Dimension(80, 32));
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonUpdate);

        jButtonDelete.setIcon(new FlatSVGIcon("svg/delete.svg",24,24));
        jButtonDelete.setText("Delete");
        jButtonDelete.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(80, 32));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonDelete);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 10));

        jTextFieldSearch.setPreferredSize(new java.awt.Dimension(250, 32));
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });
        jPanel4.add(jTextFieldSearch);

        jPanel2.add(jPanel4, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        setSize(new java.awt.Dimension(914, 440));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        JDialogMenuAU roleAU = new JDialogMenuAU(jFrameCafSys, true);
        roleAU.setVisible(true);
        selectMenus();
    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        JDialogMenuAU roleAU = new JDialogMenuAU(jFrameCafSys, true, menu, "Update");
        roleAU.setVisible(true);
        selectMenus();
        jButtonDelete.setEnabled(false);
        jButtonUpdate.setEnabled(false);

    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        JDialogMenuAU roleAU = new JDialogMenuAU(jFrameCafSys, true, "Delete", menu);
        roleAU.setVisible(true);
        selectMenus();
        jButtonDelete.setEnabled(false);
        jButtonUpdate.setEnabled(false);
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        String text = jTextFieldSearch.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text + ",*"));
        }
    }//GEN-LAST:event_jTextFieldSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUser;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables

    public DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "CATEGORY ID", "RECIPE", "PRICE", "CATEGORY", "INGREDIENTS"}, 0) {
        public Class getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return String.class;
            }
            switch (columnIndex) {
                case 1:
                    return String.class;
                case 2:
                    return String.class;
                case 4:
                    return String.class;
                case 5:
                    return String.class;
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
        jTableUser.setCellSelectionEnabled(true);
        jTableUser = new JTable(tableModel);
        jScrollPane1.setViewportView(jTableUser);
        jTableUser.addMouseListener(this);
        jTableUser.setRowSorter(sorter);
        TableColumn hide0 = jTableUser.getColumnModel().getColumn(0);
        hide0.setMinWidth(0);
        hide0.setMaxWidth(0);
        hide0.setPreferredWidth(0);
        TableColumn hide1 = jTableUser.getColumnModel().getColumn(1);
        hide1.setMinWidth(0);
        hide1.setMaxWidth(0);
        hide1.setPreferredWidth(0);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jTableUser) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (e.getClickCount() == 1) {
                    int row = jTableUser.getSelectedRow();
                    if (row != -1) {
//                        "ID", "CATEGORY ID", "RECIPE", "PRICE", "CATEGORY", "INGREDIENTS"
                        Long id = Long.parseLong(jTableUser.getValueAt(row, 0).toString());
                        Long categoryId = Long.parseLong(jTableUser.getValueAt(row, 1).toString());
                        String recipe = jTableUser.getValueAt(row, 2).toString();
                        Float price = Float.parseFloat(jTableUser.getValueAt(row, 3).toString());
                        String category = jTableUser.getValueAt(row, 4).toString();
                        String ingredients = jTableUser.getValueAt(row, 5).toString();

                        Menu mnu = new Menu();
                        Category c = new Category();
                        c.setId(categoryId);
                        c.setCategory(category);
                        mnu.setId(id);
                        mnu.setCategory(c);
                        mnu.setRecipe(recipe);
                        mnu.setPrice(price);
                        mnu.setIngredients(ingredients);

                        Menu uat = menuDAOImpl.findPhotoByMenuId(mnu);
                        mnu.setPhoto(uat.getPhoto());
                        this.menu = mnu;
                        jButtonDelete.setEnabled(true);
                        jButtonUpdate.setEnabled(true);
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

    private MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
    private ArrayList<Menu> menuList;
    String pattern = "MMM. dd, yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//  "ID", "CATEGORY ID", "RECIPE", "PRICE", "CATEGORY", "INGREDIENTS"
    private void selectMenus() {
        tableModel.setRowCount(0);
        menuList = menuDAOImpl.selectMenu();
        tableModel.setRowCount(0);
        for (Menu menu : menuList) {
            tableModel.addRow(new Object[]{menu.getId(), menu.getCategory().getId(), menu.getRecipe(), menu.getPrice(), menu.getCategory().getCategory(), menu.getIngredients()});
        }
    }

}
