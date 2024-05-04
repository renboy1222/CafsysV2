/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aldrin.cafsysv2.gui.dialog;

import com.aldrin.cafsysv2.dao.InvoiceDAO;
import com.aldrin.cafsysv2.dao.impl.InvoiceDAOImpl;
import com.aldrin.cafsysv2.dao.impl.UserAccountDAOImpl;
import com.aldrin.cafsysv2.model.Invoice;
import com.aldrin.cafsysv2.util.ComboBoxList;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Java Programming with Aldrin
 */
public class JDialogSale extends javax.swing.JDialog implements MouseListener {

    private com.aldrin.cafsysv2.gui.JFrameCafSys mainMenu;
    private Invoice invoice = new Invoice();
    private DecimalFormat df = new DecimalFormat("##,##0.00");
    private static Long startId;
    private static Long endId;
    private static Long userId;

    /**
     * Creates new form JDialogSupplier
     */
    public JDialogSale(com.aldrin.cafsysv2.gui.JFrameCafSys parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.mainMenu = parent;
        setTable();
        calculateUserSales();
        comboBoxUser();
        ComboBoxList userIdl = (ComboBoxList) this.jComboBoxUser.getSelectedItem();
        com.aldrin.cafsysv2.model.UserAccount u = new com.aldrin.cafsysv2.model.UserAccount();

        setUserId(userIdl.getId());
        invoice.setUserAccount(u);
        

        comboBoxDateAndTimeStart(getUserId());
        ComboBoxList startIdl = (ComboBoxList) this.jComboBoxStart.getSelectedItem();
        setStartId(startIdl.getId());

        comboBoxDateAndTimeEnd(getUserId());
        ComboBoxList endIdl = (ComboBoxList) this.jComboBoxEnd.getSelectedItem();
        setEndId(endIdl.getId());
        //table data

        selectUserSalesList(getStartId(), getEndId());
        jTextFieldSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
//        jTextFieldSearch.putClientProperty("JTextField.variant", "search"); // Optional: Set variant to "search" for a search field style
        
        //icon
        jTextFieldSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("svg/search.svg", 24, 24));
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
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxUser = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxStart = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxEnd = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabelTotalSales = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("USER SALES");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(748, 45));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(400, 100));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 10));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Search");
        jPanel5.add(jLabel2);

        jTextFieldSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldSearch.setPreferredSize(new java.awt.Dimension(200, 30));
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });
        jPanel5.add(jTextFieldSearch);

        jPanel4.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel6.setPreferredSize(new java.awt.Dimension(800, 72));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 10));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel9);

        jLabel3.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("User");
        jLabel3.setPreferredSize(new java.awt.Dimension(40, 25));
        jPanel6.add(jLabel3);

        jComboBoxUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxUser.setPreferredSize(new java.awt.Dimension(150, 25));
        jComboBoxUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUserActionPerformed(evt);
            }
        });
        jPanel6.add(jComboBoxUser);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel10);

        jLabel5.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Start(Date/Time)");
        jLabel5.setPreferredSize(new java.awt.Dimension(120, 25));
        jPanel6.add(jLabel5);

        jComboBoxStart.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboBoxStart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxStart.setPreferredSize(new java.awt.Dimension(150, 25));
        jComboBoxStart.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxStartItemStateChanged(evt);
            }
        });
        jComboBoxStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStartActionPerformed(evt);
            }
        });
        jComboBoxStart.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBoxStartPropertyChange(evt);
            }
        });
        jPanel6.add(jComboBoxStart);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel11);

        jLabel6.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("End(Date/Time)");
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel6.add(jLabel6);

        jComboBoxEnd.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jComboBoxEnd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxEnd.setPreferredSize(new java.awt.Dimension(150, 25));
        jComboBoxEnd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxEndItemStateChanged(evt);
            }
        });
        jComboBoxEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEndActionPerformed(evt);
            }
        });
        jComboBoxEnd.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBoxEndPropertyChange(evt);
            }
        });
        jPanel6.add(jComboBoxEnd);

        jPanel4.add(jPanel6, java.awt.BorderLayout.WEST);

        jPanel3.add(jPanel4, java.awt.BorderLayout.NORTH);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Category", "Description"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel8.setPreferredSize(new java.awt.Dimension(1003, 40));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 5));

        jLabel4.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Total Sales:");
        jPanel12.add(jLabel4);

        jLabelTotalSales.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabelTotalSales.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotalSales.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));
        jLabelTotalSales.setPreferredSize(new java.awt.Dimension(139, 29));
        jPanel12.add(jLabelTotalSales);

        jPanel8.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel13.setPreferredSize(new java.awt.Dimension(10, 40));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel13, java.awt.BorderLayout.EAST);

        jPanel3.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1094, 506));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        String text = jTextFieldSearch.getText().trim();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text + ",*"));
        }
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    private void jComboBoxStartPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxStartPropertyChange

    }//GEN-LAST:event_jComboBoxStartPropertyChange

    private void jComboBoxEndPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxEndPropertyChange

    }//GEN-LAST:event_jComboBoxEndPropertyChange

    private void jComboBoxStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxStartActionPerformed
        try {
            com.aldrin.cafsysv2.model.Invoice invoice = new com.aldrin.cafsysv2.model.Invoice();
            ComboBoxList userIdl = (ComboBoxList) this.jComboBoxUser.getSelectedItem();
            com.aldrin.cafsysv2.model.UserAccount user = new com.aldrin.cafsysv2.model.UserAccount();
            user.setId(userIdl.getId());
            invoice.setUserAccount(user);
            ComboBoxList startIdl = (ComboBoxList) this.jComboBoxStart.getSelectedItem();
            setStartId(startIdl.getId());
            ComboBoxList endIdl = (ComboBoxList) this.jComboBoxEnd.getSelectedItem();
            setEndId(endIdl.getId());
            selectUserSalesList(getStartId(), getEndId());
            calculateUserSales();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jComboBoxStartActionPerformed

    private void jComboBoxStartItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxStartItemStateChanged

    }//GEN-LAST:event_jComboBoxStartItemStateChanged

    private void jComboBoxEndItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxEndItemStateChanged

    }//GEN-LAST:event_jComboBoxEndItemStateChanged

    private void jComboBoxEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEndActionPerformed
        try {
            com.aldrin.cafsysv2.model.Invoice invoice = new com.aldrin.cafsysv2.model.Invoice();
            ComboBoxList userIdl = (ComboBoxList) this.jComboBoxUser.getSelectedItem();
            com.aldrin.cafsysv2.model.UserAccount user = new com.aldrin.cafsysv2.model.UserAccount();
            user.setId(userIdl.getId());
            invoice.setUserAccount(user);
//            comboBoxDateAndTimeStart(getUserId());
            ComboBoxList startIdl = (ComboBoxList) this.jComboBoxStart.getSelectedItem();
            setStartId(startIdl.getId());
//            comboBoxDateAndTimeEnd(getUserId());
            ComboBoxList endIdl = (ComboBoxList) this.jComboBoxEnd.getSelectedItem();
            setEndId(endIdl.getId());
            selectUserSalesList(getStartId(), getEndId());
            calculateUserSales();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jComboBoxEndActionPerformed

    private void jComboBoxUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxUserActionPerformed
        try {
            com.aldrin.cafsysv2.model.Invoice invoice = new com.aldrin.cafsysv2.model.Invoice();
            ComboBoxList userIdl = (ComboBoxList) this.jComboBoxUser.getSelectedItem();
            com.aldrin.cafsysv2.model.UserAccount user = new com.aldrin.cafsysv2.model.UserAccount();
            user.setId(userIdl.getId());
            setUserId(userIdl.getId());
            comboBoxDateAndTimeStart(getUserId());
            ComboBoxList startIdl = (ComboBoxList) this.jComboBoxStart.getSelectedItem();
            setStartId(startIdl.getId());
            comboBoxDateAndTimeEnd(getUserId());
            ComboBoxList endIdl = (ComboBoxList) this.jComboBoxEnd.getSelectedItem();
            setEndId(endIdl.getId());
            selectUserSalesList(getStartId(), getEndId());
            calculateUserSales();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_jComboBoxUserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> jComboBoxEnd;
    private javax.swing.JComboBox<Object> jComboBoxStart;
    private javax.swing.JComboBox<Object> jComboBoxUser;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelTotalSales;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables

//
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jTable1) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (e.getClickCount() == 1) {
                    int row = jTable1.getSelectedRow();
                    if (row != -1) {

                        Object idl = jTable1.getValueAt(row, 0);
                        invoice.setId(Long.parseLong(idl.toString()));

                    }
                } else if (e.getClickCount() == 2) {
                    JDialogViewInvoiceDetails u = new JDialogViewInvoiceDetails(mainMenu, true, invoice);
                    u.setVisible(true);

                }
            }

        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    private ArrayList<Invoice> invoiceList;
    public DefaultTableModel tableModel = new DefaultTableModel() {
        public Class getColumnClass(int columnIndex) {
            if (columnIndex == 0) {
                return String.class;
            }
            switch (columnIndex) {
                case 1:
                    return String.class;
                case 2:
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
        try {
            String[] columnNames = {
                "Invoice No",//
                "Date/Time",//1
                "Total",
                "Total UF"
            };//15

            jTable1.setCellSelectionEnabled(true);
            jTable1 = new JTable(tableModel);
            JTableHeader header = jTable1.getTableHeader();
            header.setFont(new Font("Courier New", Font.PLAIN, 14));
            header.setBackground(new java.awt.Color(153, 153, 153));
            jTable1.setRowSorter(sorter);
            jTable1.addMouseListener(this);
            jTable1.setFont(new Font("Courier New", Font.PLAIN, 14));
            jScrollPane1.setViewportView(jTable1);
            for (int i = 0; i < columnNames.length;) {
                tableModel.addColumn(columnNames[i]);
                i++;
            }
            jScrollPane1.setViewportView(jTable1);

            TableColumn[] column = new TableColumn[100];

            column[0] = jTable1.getColumnModel().getColumn(0);
            column[0].setPreferredWidth(60);

            column[1] = jTable1.getColumnModel().getColumn(1);
            column[1].setPreferredWidth(150);

            column[2] = jTable1.getColumnModel().getColumn(2);
            column[2].setPreferredWidth(150);

            TableColumn hidden1 = jTable1.getColumnModel().getColumn(3);
            hidden1.setMinWidth(0);
            hidden1.setPreferredWidth(0);
            hidden1.setMaxWidth(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private InvoiceDAOImpl invoiceDAOImpl = new InvoiceDAOImpl();

    private void selectUserSalesList(Long start, Long end) {
        invoiceList = invoiceDAOImpl.getSalesUser(getUserId(), start, end);
        tableModel.setRowCount(0);
        for (Invoice i : invoiceList) {
            int YY = Integer.parseInt(i.getCreatedAt().toString().substring(0, 4));
            int MM = Integer.parseInt(i.getCreatedAt().toString().substring(5, 7));
            int DD = Integer.parseInt(i.getCreatedAt().toString().substring(8, 10));
            int HH = Integer.parseInt(i.getCreatedAt().toString().substring(11, 13));
            int mm = Integer.parseInt(i.getCreatedAt().toString().substring(14, 16));
            int SS = Integer.parseInt(i.getCreatedAt().toString().substring(17, 19));
//            int AA = Integer.parseInt(i.getCreatedAt().toString().substring(20, 22));
            LocalDateTime dateTimeToFormat = LocalDateTime.of(YY, MM, DD, HH, mm, SS, SS);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM. dd, yyyy hh:mm:ss a");
            String formattedDateTime = dateTimeToFormat.format(dateTimeFormatter);
            tableModel.addRow(new Object[]{i.getId(), formattedDateTime, df.format(i.getTotal()), i.getTotal()});
        }
    }

    private void calculateUserSales() {
        try {
            double totalSales = 0.0;
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                Double total = Double.parseDouble(jTable1.getValueAt(i, 3).toString());
                totalSales = totalSales + total;
            }
            jLabelTotalSales.setText(df.format(totalSales));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private UserAccountDAOImpl userDAOImpl = new UserAccountDAOImpl();

    private void comboBoxUser() {
        this.jComboBoxUser.removeAllItems();
        userDAOImpl.comboBoxUserAccount();
        for (ComboBoxList a : userDAOImpl.getList()) {
            this.jComboBoxUser.addItem(a);
        }
    }

    private void comboBoxDateAndTimeStart(Long userId) {
        this.jComboBoxStart.removeAllItems();
        invoiceDAOImpl.invoiceDateTimeComboBox(userId);
        if(invoiceDAOImpl.getList().isEmpty()){
        }
        for (ComboBoxList a : invoiceDAOImpl.getList()) {
            this.jComboBoxStart.addItem(a);
        }
    }

    private void comboBoxDateAndTimeEnd(Long userId) {
        this.jComboBoxEnd.removeAllItems();
        invoiceDAOImpl.invoiceDateTimeComboBox(userId);
        for (ComboBoxList a : invoiceDAOImpl.getList()) {
            this.jComboBoxEnd.addItem(a);
        }
    }

    /**
     * @return the startId
     */
    public static Long getStartId() {
        return startId;
    }

    /**
     * @param aStartId the startId to set
     */
    public static void setStartId(Long aStartId) {
        startId = aStartId;
    }

    /**
     * @return the endId
     */
    public static Long getEndId() {
        return endId;
    }

    /**
     * @param aEnd the endId to set
     */
    public static void setEndId(Long aEnd) {
        endId = aEnd;
    }

    /**
     * @return the userId
     */
    public static Long getUserId() {
        return userId;
    }

    /**
     * @param aUserId the userId to set
     */
    public static void setUserId(Long aUserId) {
        userId = aUserId;
    }
}
