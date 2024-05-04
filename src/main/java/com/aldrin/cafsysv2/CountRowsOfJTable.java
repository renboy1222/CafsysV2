/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.cafsysv2;

/**
 *
 * @author Java Programming with Aldrin
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CountRowsOfJTable extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JButton countRowsButton;

    public CountRowsOfJTable() {
        setTitle("Count Rows of JTable");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create table model
        model = new DefaultTableModel();
        model.addColumn("Column 1");
        model.addColumn("Column 2");

        // Create table
        table = new JTable(model);

        // Add some sample data to the table
        model.addRow(new Object[]{"Data 1", "Data A"});
//        model.addRow(new Object[]{"Data 2", "Data B"});
//        model.addRow(new Object[]{"Data 3", "Data C"});

        // Create button to count rows
        countRowsButton = new JButton("Count Rows");
        countRowsButton.addActionListener(e -> countRows());

        // Add table and button to frame
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(countRowsButton, BorderLayout.SOUTH);

        // Set frame size and visibility
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void countRows() {
        int rowCount = table.getRowCount();
        JOptionPane.showMessageDialog(this, "Row count: " + rowCount);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CountRowsOfJTable::new);
    }
}

