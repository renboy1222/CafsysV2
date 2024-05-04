/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.cafsysv2.dao.impl;

import com.aldrin.cafsysv2.dao.OrderOptionDAO;
import com.aldrin.cafsysv2.dao.RoleDAO;
import com.aldrin.cafsysv2.model.OrderOption;
import com.aldrin.cafsysv2.model.Role;
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
public class OrderOptionDAOImpl extends DBConnection implements OrderOptionDAO {

    @Override
    public void addOrderOption(OrderOption orderOption) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO ORDER_OPTION (ID,ORDER_OPTION,DESCRIPTION) VALUES  (?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setString(2, orderOption.getOrderOption());
            ps.setString(3, orderOption.getDescription());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrderOption(OrderOption orderOption) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE ORDER_OPTION SET ORDER_OPTION =?, DESCRIPTION =? WHERE ORDER_OPTION.ID = ?");
            ps.setString(1, orderOption.getOrderOption());
            ps.setString(2, orderOption.getDescription());
            ps.setLong(3, orderOption.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderOption(OrderOption orderOption) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE ORDER_OPTION SET DELETED =? WHERE ORDER_OPTION.ID = ? ");
            ps.setBoolean(1, true);
            ps.setLong(2, orderOption.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<OrderOption> selectOrderOption() {
        ArrayList<OrderOption> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM ORDER_OPTION  ORDER BY ORDER_OPTION ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                OrderOption c = new OrderOption();
                c.setId(rs.getLong("ID"));
                c.setOrderOption(rs.getString("ORDER_OPTION"));
                c.setDescription(rs.getString("DESCRIPTION"));
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
    public void comboBoxOrderOption() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT * FROM ORDER_OPTION UE  ORDER BY ORDER_OPTION ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String namel = rs.getString("ORDER_OPTION");
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
                    + "    MAX(ORDER_OPTION.ID) AS ID  \n"
                    + "FROM \n"
                    + "    ORDER_OPTION");
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
