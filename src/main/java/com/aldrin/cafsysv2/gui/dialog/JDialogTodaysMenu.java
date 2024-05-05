/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.aldrin.cafsysv2.gui.dialog;

import com.aldrin.cafsysv2.dao.impl.MenuDAOImpl;
import com.aldrin.cafsysv2.dao.impl.TodaysMenuDAOImpl;
import com.aldrin.cafsysv2.gui.JFrameCafSys;
import com.aldrin.cafsysv2.model.Menu;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ALDRIN B. C.
 */
@Setter
@Getter
@ToString
public class JDialogTodaysMenu extends javax.swing.JDialog implements MouseListener {

    /**
     * Creates new form JDialogCourse
     */
    private JFrameCafSys jFrameCafSys;
    private Menu menu = new Menu();
//    private static Integer selectedRo;

    public JDialogTodaysMenu(JFrameCafSys jFrameCafSys, boolean modal) {
        super(jFrameCafSys, modal);
        this.jFrameCafSys = jFrameCafSys;
        initComponents();
        booleanList.clear();
        setTable();
        selectMenus();
        jTextFieldSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        //icon
        jTextFieldSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("svg/search.svg", 24, 24));
        jButtonTodaysMenu.setEnabled(false);
        recordChecker();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButtonTodaysMenu = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TODAY'S MENU");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

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
            .addGap(0, 395, Short.MAX_VALUE)
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
            .addGap(0, 395, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, java.awt.BorderLayout.EAST);

        jPanel8.setPreferredSize(new java.awt.Dimension(886, 10));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1014, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setMinimumSize(new java.awt.Dimension(255, 70));
        jPanel2.setPreferredSize(new java.awt.Dimension(886, 60));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(100, 70));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));

        jButtonTodaysMenu.setIcon(new FlatSVGIcon("svg/save.svg",24,24));
        jButtonTodaysMenu.setText("<html><center>Today's<br><p style=\"font-weight: bold; color:red;padding-top: 0px;\">Menu</p></center></html>");
        jButtonTodaysMenu.setMargin(new java.awt.Insets(2, 2, 3, 2));
        jButtonTodaysMenu.setPreferredSize(new java.awt.Dimension(80, 40));
        jButtonTodaysMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTodaysMenuActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonTodaysMenu);

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

        setSize(new java.awt.Dimension(1030, 473));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTodaysMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTodaysMenuActionPerformed
        saveTodaysMenu();
        selectMenuOfTheDay();
    }//GEN-LAST:event_jButtonTodaysMenuActionPerformed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        String text = jTextFieldSearch.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text + ",*"));
        }
    }//GEN-LAST:event_jTextFieldSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonTodaysMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables

    public DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"MENU ID", "CATEGORY ID", "TODAY'S MENU", "CATEGORY", "MENU", "PRICE", "INGREDIENTS"}, 0) {
        public Class getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return String.class;
            }
            switch (columnIndex) {
                case 1:
                    return String.class;
                case 2:
                    return Boolean.class;
                case 3:
                    return String.class;
                case 4:
                    return String.class;
                case 5:
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
        jTable1.setCellSelectionEnabled(true);
        jTable1 = new JTable(tableModel);
        jScrollPane1.setViewportView(jTable1);
        jTable1.addMouseListener(this);
        jTable1.setRowSorter(sorter);
        TableColumn[] column = new TableColumn[100];

        column[0] = jTable1.getColumnModel().getColumn(0);
        column[0].setPreferredWidth(40);

        column[1] = jTable1.getColumnModel().getColumn(1);
        column[1].setPreferredWidth(250);

        column[2] = jTable1.getColumnModel().getColumn(2);
        column[2].setPreferredWidth(110);

        column[3] = jTable1.getColumnModel().getColumn(3);
        column[3].setPreferredWidth(80);

        column[4] = jTable1.getColumnModel().getColumn(4);
        column[4].setPreferredWidth(230);

        column[5] = jTable1.getColumnModel().getColumn(5);
        column[5].setPreferredWidth(30);

        column[6] = jTable1.getColumnModel().getColumn(6);
        column[6].setPreferredWidth(360);

        TableColumn hide0 = jTable1.getColumnModel().getColumn(0);
        hide0.setMinWidth(0);
        hide0.setMaxWidth(0);
        hide0.setPreferredWidth(0);
        TableColumn hide1 = jTable1.getColumnModel().getColumn(1);
        hide1.setMinWidth(0);
        hide1.setMaxWidth(0);
        hide1.setPreferredWidth(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jTable1) {
            if (e.getButton() == MouseEvent.BUTTON1) {
//                if (e.getClickCount() == 1) {
//                    int row = jTable1.getSelectedRow();
//                    int col = jTable1.getSelectedColumn();
//                    if (row != -1) {
//                        Object idl = jTable1.getValueAt(row, 0);
//                        Object categoryIdl = jTable1.getValueAt(row, 1);
//                        Object categoryl = jTable1.getValueAt(row, 3);
//                        Object menul = jTable1.getValueAt(row, 4);
//                        Object pricel = jTable1.getValueAt(row, 5);
//                        Object ingredientl = jTable1.getValueAt(row, 6);
//                        Category c = new Category();
//                        c.setId(Long.parseLong(categoryIdl.toString()));
//                        menu.setId(Long.parseLong(idl.toString()));
//                        menu.setRecipe(menul.toString());
//                        menu.setPrice(Float.parseFloat(pricel.toString()));
//                        menu.setIngredients(ingredientl.toString());
//                        menu.setCategory(c);
//                        setSelectedRow(jTable1.getSelectedRow());
//                    }
//                }
                if (e.getClickCount() == 1) {
                    int row = jTable1.getSelectedRow();
                    Boolean selectedCol = (Boolean) jTable1.getValueAt(row, 2);
                    if (selectedCol == true) {
                        jTable1.setValueAt(false, row, 2);
                    } else {
                        jTable1.setValueAt(true, row, 2);
                    }
                    for (int i = 0; i < jTable1.getRowCount(); i++) {
                        Boolean r = (Boolean) jTable1.getValueAt(i, 2);
                        if (booleanList.get(i) != r) {
                            jButtonTodaysMenu.setEnabled(true);
                            break;
                        } else {
                            jButtonTodaysMenu.setEnabled(false);
                        }
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
//    "MENU ID", "CATEGORY ID", "TODAY'S MENU", "CATEGORY", "MENU", "PRICE", "INGREDIENTS"

    private void selectMenus() {
        tableModel.setRowCount(0);
        menuList = menuDAOImpl.selectMenu();
        tableModel.setRowCount(0);
        for (Menu menu : menuList) {
            tableModel.addRow(new Object[]{menu.getId(), menu.getCategory().getId(), false, menu.getCategory().getCategory(), menu.getRecipe(), menu.getPrice(), menu.getIngredients()});
        }
        selectMenuOfTheDay();
    }

    private TodaysMenuDAOImpl todaysMenuDAOImpl = new TodaysMenuDAOImpl();

    private void selectMenuOfTheDay() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            Long menuId = (Long) jTable1.getValueAt(i, 0);
            if (todaysMenuDAOImpl.selectTodaysMenu(menuId) == true) {
                jTable1.setValueAt(true, i, 2);
            }
            if (todaysMenuDAOImpl.getTodaysMenuDRemove(menuId) == true) {
                jTable1.setValueAt(false, i, 2);
            }

        }
    }

    private void saveTodaysMenu() {
        int response = JOptionPane.showConfirmDialog(rootPane, "Are you sure to save this following for menu for this day?", "Save today's menu", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                Long menuId = (Long) jTable1.getValueAt(i, 0);
                Boolean isSelected = (Boolean) jTable1.getValueAt(i, 2);
                Object price = jTable1.getValueAt(i, 5);
                Boolean motd = (Boolean) jTable1.getValueAt(i, 2);
                if (todaysMenuDAOImpl.getTodaysMenuUpdated(menuId) == true) {
                    if (motd == true) {
                        todaysMenuDAOImpl.updateTodaysMenu(menuId);
                    }
                } else {
                    if (motd == true) {
                        menu.setId(menuId);
                        menu.setPrice(Float.parseFloat(price.toString()));
                        if (todaysMenuDAOImpl.getTodaysMenuDeletedRecover(menuId)) {

                        } else {
                            todaysMenuDAOImpl.addMenuOfTheDay(menu);
                        }
                    }
                }
                if (todaysMenuDAOImpl.getTodaysMenuUpdated(menuId)) {
                    if (isSelected == false) {
                       todaysMenuDAOImpl.updateTodaysD2(menuId);
                    }
                }
            }
            JOptionPane.showMessageDialog(jFrameCafSys, "Menu of the day is successfully added to menu of the day.", "Message", JOptionPane.PLAIN_MESSAGE);
            this.dispose();
        }
    }

    private void mouseClickedTodaysMenu() {
        try {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                int row = jTable1.getSelectedRow();
                Boolean isSelected = (Boolean) jTable1.getValueAt(row, 2);
                if (isSelected == true) {
//                    jButtonTodaysMenu.setEnabled(true);
                } else {
//                    jButtonTodaysMenu.setEnabled(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    /**
//     * @return the selectedRow
//     */
//    public static Integer getSelectedRow() {
//        return selectedRow;
//    }
//
//    /**
//     * @param aSelectedRow the selectedRow to set
//     */
//    public static void setSelectedRo(Integer aSelectedRow) {
//        selectedRow = aSelectedRow;
//    }
    private static ArrayList<Boolean> booleanList = new ArrayList<>();

    private void recordChecker() {
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            boolean record = Boolean.parseBoolean(jTable1.getValueAt(i, 2).toString());
            booleanList.add(record);
        }
    }

}
