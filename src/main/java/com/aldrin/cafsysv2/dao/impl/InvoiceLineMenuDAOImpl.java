/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.cafsysv2.dao.impl;

import com.aldrin.cafsysv2.dao.InvoiceLineMenuDAO;
import com.aldrin.cafsysv2.model.Invoice;
import com.aldrin.cafsysv2.model.InvoiceLineMenu;
import com.aldrin.cafsysv2.model.Menu;
import com.aldrin.cafsysv2.model.TodaysMenu;
import com.aldrin.cafsysv2.util.ComboBoxList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ALDRIN B. C.
 */
@Setter
@Getter
public class InvoiceLineMenuDAOImpl extends DBConnection implements InvoiceLineMenuDAO {

    @Override
    public void addInvoiceLineMenu(InvoiceLineMenu invoice) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO INVOICE_LINE_MENU (ID,QTY,INVOICE_ID,TODAYS_MENU_ID) \n"
                    + "VALUES  (?,?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setInt(2, invoice.getQty());
            ps.setLong(3, invoice.getInvoice().getId() - 1);
            ps.setLong(4, invoice.getTodaysMenu().getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInvoiceLineMenu(InvoiceLineMenu category) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CATEGORY SET CATEGORY =?, DESCRIPTION =? WHERE CATEGORY.ID = ?");
//            ps.setString(1, category.getCategory());
//            ps.setString(2, category.getDescription());
            ps.setLong(3, category.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInvoiceLineMenu(InvoiceLineMenu category) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CATEGORY SET DELETED =? WHERE CATEGORY.ID = ? ");
            ps.setBoolean(1, true);
            ps.setLong(2, category.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<InvoiceLineMenu> selectInvoiceLineMenu() {
        ArrayList<InvoiceLineMenu> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM CATEGORY WHERE CATEGORY.DELETED =FALSE  ORDER BY CATEGORY ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                InvoiceLineMenu c = new InvoiceLineMenu();
                c.setId(rs.getLong("ID"));
//                ilm.setCategory(rs.getString("CATEGORY"));
//                ilm.setDescription(rs.getString("DESCRIPTION"));
                list.add(c);
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<InvoiceLineMenu> selectInvoiceLineMenuByInvoiceId(Long id) {
        ArrayList<InvoiceLineMenu> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    INVOICE.ID, \n"
                    + "    MENU.RECIPE,\n"
                    + "    INVOICE_LINE_MENU.QTY, \n"
                    + "    TODAYS_MENU.PRICE, \n"
                    + "    (TODAYS_MENU.PRICE *INVOICE_LINE_MENU.QTY)  AS LINE_TOTAL   \n"
                    + "FROM \n"
                    + "    INVOICE_LINE_MENU \n"
                    + "INNER JOIN \n"
                    + "    INVOICE \n"
                    + "ON (INVOICE_LINE_MENU.INVOICE_ID = INVOICE.ID) \n"
                    + "INNER JOIN \n"
                    + "    TODAYS_MENU \n"
                    + "ON (INVOICE_LINE_MENU.TODAYS_MENU_ID = TODAYS_MENU.ID) \n"
                    + "INNER JOIN \n"
                    + "    MENU \n"
                    + "ON (TODAYS_MENU.MENU_ID = MENU.ID) \n"
                    + "INNER JOIN \n"
                    + "    USER_ACCOUNT \n"
                    + "ON (INVOICE.USER_ID = USER_ACCOUNT.ID) \n"
                    + "INNER JOIN \n"
                    + "    ROLE \n"
                    + "ON (USER_ACCOUNT.ROLE_ID = ROLE.ID) \n"
                    + "INNER JOIN \n"
                    + "    CATEGORY \n"
                    + "ON (MENU.CATEGORY_ID =CATEGORY.ID) \n"
                    + "INNER JOIN \n"
                    + "    ORDER_OPTION \n"
                    + "ON (INVOICE.ORDER_OPTION_ID =ORDER_OPTION.ID) where INVOICE.ID ="+id+ " \n"
                    + "GROUP BY INVOICE.ID, \n"
                    + "    MENU.RECIPE,\n"
                    + "    INVOICE_LINE_MENU.QTY, \n"
                    + "    TODAYS_MENU.PRICE";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                InvoiceLineMenu ilm = new InvoiceLineMenu();
                Invoice invoice = new Invoice();
                Menu menu = new Menu();
                TodaysMenu todaysMenu = new TodaysMenu();
                invoice.setId(rs.getLong("ID"));
                menu.setRecipe(rs.getString("RECIPE"));
                todaysMenu.setMenu(menu);
                todaysMenu.setPrice(rs.getFloat("PRICE"));
                ilm.setQty(rs.getInt("QTY"));
                ilm.setTodaysMenu(todaysMenu);
                ilm.setInvoice(invoice);

                list.add(ilm);
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public void comboBoxInvoiceLineMenu() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT * FROM CATEGORY WHERE CATEGORY.DELETED =FALSE  ORDER BY CATEGORY ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String namel = rs.getString("CATEGORY");
                this.getList().add(new ComboBoxList(idl, namel));
            }
            rs.close();
            statement.close();
            closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Long getMaxId() {
        Long maxId = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    MAX(INVOICE_LINE_MENU.ID) AS ID  \n"
                    + "FROM \n"
                    + "    INVOICE_LINE_MENU ");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                if (idl == 0) {
                    maxId = 1L;
                } else {
                    maxId = idl + 1;
                }
            }
            rs.close();
            statement.close();
//            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Opss...", JOptionPane.ERROR_MESSAGE);
        }
        return maxId;
    }

}
