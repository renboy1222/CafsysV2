/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.cafsysv2.dao.impl;

import com.aldrin.cafsysv2.dao.InvoiceDAO;
import com.aldrin.cafsysv2.model.Invoice;
import com.aldrin.cafsysv2.model.OrderOption;
import com.aldrin.cafsysv2.model.UserAccount;
import com.aldrin.cafsysv2.util.ComboBoxList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @authorB. C.
 */
@Setter
@Getter
public class InvoiceDAOImpl extends DBConnection implements InvoiceDAO {

    @Override
    public void addInvoice(Invoice invoice) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO INVOICE (ID,NOOFORDERS,CUSTOMERCASH,ORDER_OPTION_ID,TABLENO,TOTAL,USER_ID) \n"
                    + "VALUES  (?,?,?,?,?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setInt(2, invoice.getNoOfOrders());
            ps.setFloat(3, invoice.getCustomerCash());
            ps.setLong(4, invoice.getOrderOption().getId());
            ps.setInt(5, invoice.getTableNo());
            ps.setFloat(6, invoice.getTotal());
            ps.setLong(7, invoice.getUserAccount().getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInvoice(Invoice category) {
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
    public void deleteInvoice(Invoice category) {
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
    public ArrayList<Invoice> selectInvoice() {
//         "INOVICE #", "USER","CREATED AT","# OF ORDERS","AMOUNT DUE","ORDER OPTION"
        ArrayList<Invoice> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "   INVOICE.ID, \n"
                    + "   USER_ACCOUNT.FIRSTNAME, \n"
                    + "   USER_ACCOUNT.SURNAME, \n"
                    + "   INVOICE.TABLENO, \n"
                    + "   INVOICE.CREATEDAT, \n"
                    + "   INVOICE.CUSTOMERCASH, \n"
                    + "   INVOICE.NOOFORDERS,\n"
                    + "   INVOICE.TOTAL,  \n"
                    + "   ORDER_OPTION.ORDER_OPTION \n"
                    + "FROM \n"
                    + "   INVOICE_LINE_MENU \n"
                    + "INNER JOIN \n"
                    + "   INVOICE \n"
                    + "ON (INVOICE_LINE_MENU.INVOICE_ID =INVOICE.ID) \n"
                    + "INNER JOIN \n"
                    + "   TODAYS_MENU \n"
                    + "ON (INVOICE_LINE_MENU.TODAYS_MENU_ID =TODAYS_MENU.ID) \n"
                    + "INNER JOIN \n"
                    + "   MENU \n"
                    + "ON (TODAYS_MENU.MENU_ID =MENU.ID) \n"
                    + "INNER JOIN \n"
                    + "   USER_ACCOUNT \n"
                    + "ON (INVOICE.USER_ID =USER_ACCOUNT.ID) \n"
                    + "INNER JOIN \n"
                    + "   ROLE \n"
                    + "ON (USER_ACCOUNT.ROLE_ID =ROLE.ID) \n"
                    + "INNER JOIN \n"
                    + "   CATEGORY \n"
                    + "ON (MENU.CATEGORY_ID =CATEGORY.ID) \n"
                    + "INNER JOIN \n"
                    + "   ORDER_OPTION \n"
                    + "ON (INVOICE.ORDER_OPTION_ID =ORDER_OPTION.ID) GROUP BY  \n"
                    + "   INVOICE.ID, \n"
                    + "   USER_ACCOUNT.FIRSTNAME, \n"
                    + "   USER_ACCOUNT.SURNAME, \n"
                    + "   INVOICE.TABLENO, \n"
                    + "   INVOICE.CREATEDAT, \n"
                    + "   INVOICE.CUSTOMERCASH, \n"
                    + "   INVOICE.NOOFORDERS,\n"
                    + "   INVOICE.TOTAL,  \n"
                    + "   ORDER_OPTION.ORDER_OPTION  ORDER BY INVOICE.ID DESC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Invoice invoice = new Invoice();
                UserAccount userAccount = new UserAccount();
                OrderOption orderOption = new OrderOption();
                invoice.setId(rs.getLong("ID"));
                userAccount.setFirstname(rs.getString("FIRSTNAME"));
                userAccount.setSurname(rs.getString("SURNAME"));
                invoice.setTableNo(rs.getInt("TABLENO"));
                invoice.setCreatedAt(rs.getTimestamp("CREATEDAT"));
                invoice.setCustomerCash(rs.getFloat("CUSTOMERCASH"));
                invoice.setNoOfOrders(rs.getInt("NOOFORDERS"));
                invoice.setTotal(rs.getFloat("TOTAL"));
                orderOption.setOrderOption(rs.getString("ORDER_OPTION"));
                invoice.setUserAccount(userAccount);
                invoice.setOrderOption(orderOption);

                list.add(invoice);
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
    public void comboBoxInvoice() {
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
                    + "    MAX(INVOICE.ID) AS ID  \n"
                    + "FROM \n"
                    + "    INVOICE ");
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

    @Override
    public ArrayList<com.aldrin.cafsysv2.model.Invoice> getSalesUser(Long userId, Long startId, Long endId) {
        ArrayList<com.aldrin.cafsysv2.model.Invoice> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    USER_ACCOUNT.SURNAME, \n"
                    + "    USER_ACCOUNT.FIRSTNAME, \n"
                    + "    INVOICE.CREATEDAT, \n"
                    + "    INVOICE.TOTAL, \n"
                    + "    INVOICE.ID \n"
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
                    + "ON (MENU.CATEGORY_ID = CATEGORY.ID) \n"
                    + "INNER JOIN \n"
                    + "    ORDER_OPTION \n"
                    + "ON (INVOICE.ORDER_OPTION_ID = ORDER_OPTION.ID) \n"
                    + "WHERE \n"
                    + " INVOICE.USER_ID = " + userId + "  AND  ((INVOICE.ID >=" + startId + " ) AND ( INVOICE.ID <=" + endId + "))   GROUP BY  USER_ACCOUNT.SURNAME, \n"
                    + "    USER_ACCOUNT.FIRSTNAME, \n"
                    + "    INVOICE.CREATEDAT, \n"
                    + "    INVOICE.TOTAL, \n"
                    + "    INVOICE.ID ORDER BY INVOICE.ID DESC  ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                com.aldrin.cafsysv2.model.Invoice i = new com.aldrin.cafsysv2.model.Invoice();
                i.setId(rs.getLong("ID"));
                i.setTotal(rs.getFloat("TOTAL"));
                i.setCreatedAt(rs.getTimestamp("CREATEDAT"));
                list.add(i);
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (Exception ex) {
            String err = ex.getMessage();
            System.out.println(err);
        }
        return list;
    }

    @Override
    public void invoiceDateTimeComboBox() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    INVOICE.ID, \n"
                    + "    INVOICE.CREATEDAT  \n"
                    + "     \n"
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
                    + "ON (MENU.CATEGORY_ID = CATEGORY.ID) \n"
                    + "INNER JOIN \n"
                    + "    ORDER_OPTION \n"
                    + "ON (INVOICE.ORDER_OPTION_ID = ORDER_OPTION.ID) \n"
                    + "  GROUP BY  INVOICE.ID, \n"
                    + "    INVOICE.CREATEDAT  ORDER BY INVOICE.ID DESC");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                Date dt = rs.getTimestamp("CREATEDAT");
                int YY = Integer.parseInt(rs.getTimestamp("CREATEDAT").toString().substring(0, 4));
                int MM = Integer.parseInt(rs.getTimestamp("CREATEDAT").toString().substring(5, 7));
                int DD = Integer.parseInt(rs.getTimestamp("CREATEDAT").toString().substring(8, 10));
                int HH = Integer.parseInt(rs.getTimestamp("CREATEDAT").toString().substring(11, 13));
                int mm = Integer.parseInt(rs.getTimestamp("CREATEDAT").toString().substring(14, 16));
                int SS = Integer.parseInt(rs.getTimestamp("CREATEDAT").toString().substring(17, 19));
                int AA = Integer.parseInt(rs.getTimestamp("CREATEDAT").toString().substring(20, 22));
                LocalDateTime dateTimeToFormat = LocalDateTime.of(YY, MM, DD, HH, mm, SS, AA);

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM. dd, yyyy hh:mm:ss a");
                String formattedDateTime = dateTimeToFormat.format(dateTimeFormatter);

                // Process data here
                this.getList().add(new ComboBoxList(idl, formattedDateTime));
            }
            rs.close();
            statement.close();
            closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void invoiceDateTimeComboBox(Long userId) {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    INVOICE.ID, \n"
                    + "    INVOICE.CREATEDAT  \n"
                    + "     \n"
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
                    + "ON (MENU.CATEGORY_ID = CATEGORY.ID) \n"
                    + "INNER JOIN \n"
                    + "    ORDER_OPTION \n"
                    + "ON (INVOICE.ORDER_OPTION_ID = ORDER_OPTION.ID) where INVOICE.USER_ID ="+userId +" \n"
                    + "  GROUP BY  INVOICE.ID, \n"
                    + "    INVOICE.CREATEDAT  ORDER BY INVOICE.ID DESC");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                Date dt = rs.getTimestamp("CREATEDAT");
                int YY = Integer.parseInt(rs.getTimestamp("CREATEDAT").toString().substring(0, 4));
                int MM = Integer.parseInt(rs.getTimestamp("CREATEDAT").toString().substring(5, 7));
                int DD = Integer.parseInt(rs.getTimestamp("CREATEDAT").toString().substring(8, 10));
                int HH = Integer.parseInt(rs.getTimestamp("CREATEDAT").toString().substring(11, 13));
                int mm = Integer.parseInt(rs.getTimestamp("CREATEDAT").toString().substring(14, 16));
                int SS = Integer.parseInt(rs.getTimestamp("CREATEDAT").toString().substring(17, 19));
//                int AA = Integer.parseInt(rs.getTimestamp("CREATEDAT").toString().substring(20, 22));
                LocalDateTime dateTimeToFormat = LocalDateTime.of(YY, MM, DD, HH, mm, SS, SS);

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM. dd, yyyy hh:mm:ss a");
                String formattedDateTime = dateTimeToFormat.format(dateTimeFormatter);

                // Process data here
                this.getList().add(new ComboBoxList(idl, formattedDateTime));
            }
            rs.close();
            statement.close();
            closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
