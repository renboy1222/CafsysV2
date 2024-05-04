/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.cafsysv2.dao.impl;

import com.aldrin.cafsysv2.dao.MenuDAO;
import static com.aldrin.cafsysv2.dao.impl.DBConnection.closeConnection;
import static com.aldrin.cafsysv2.dao.impl.DBConnection.getCon;
import com.aldrin.cafsysv2.model.Category;
import com.aldrin.cafsysv2.model.Menu;
import com.aldrin.cafsysv2.model.TodaysMenu;
import com.aldrin.cafsysv2.util.ComboBoxList;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class MenuDAOImpl extends DBConnection implements MenuDAO {

    @Override
    public void addMenu(Menu menu) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO MENU (ID,RECIPE,PRICE,CATEGORY_ID,INGREDIENTS,PHOTO) VALUES  (?,?,?,?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setString(2, menu.getRecipe());
            ps.setFloat(3, menu.getPrice());
            ps.setLong(4, menu.getCategory().getId());
            ps.setString(5, menu.getIngredients());
            ps.setBytes(6, menu.getPhoto());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMenu(Menu menu) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE MENU SET RECIPE =?, PRICE =?,CATEGORY_ID=?,INGREDIENTS =?,PHOTO=? WHERE MENU.ID = ?");
            ps.setString(1, menu.getRecipe());
            ps.setFloat(2, menu.getPrice());
            ps.setLong(3, menu.getCategory().getId());
            ps.setString(4, menu.getIngredients());
            ps.setBytes(5, menu.getPhoto());
            ps.setLong(6, menu.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(Menu menu) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE MENU SET DELETED =? WHERE MENU.ID = ? ");
            ps.setBoolean(1, true);
            ps.setLong(2, menu.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<Menu> selectMenu() {
        ArrayList<Menu> list = new ArrayList<>();
        try {
            String query = """
                           SELECT 
                                                          MENU.ID, 
                                                          MENU.RECIPE, 
                                                          MENU.PRICE, 
                                                          MENU.CATEGORY_ID, 
                                                          CATEGORY.CATEGORY, 
                                                          MENU.INGREDIENTS 
                                                      FROM 
                                                          MENU 
                                                      INNER JOIN 
                                                          CATEGORY 
                                                      ON (MENU.CATEGORY_ID = CATEGORY.ID) WHERE MENU.DELETED =FALSE ORDER BY MENU.RECIPE ASC """;
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Menu m = new Menu();
                Category c = new Category();
                m.setId(rs.getLong("ID"));
                m.setRecipe(rs.getString("RECIPE"));
                m.setPrice(rs.getFloat("PRICE"));
                c.setId(rs.getLong("CATEGORY_ID"));
                c.setCategory(rs.getString("CATEGORY"));
                m.setIngredients(rs.getString("INGREDIENTS"));
                m.setCategory(c);
                list.add(m);
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
    public void comboBoxMenu() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT * FROM MENU WHERE MENU.DELETED =FALSE  ORDER BY RECIPE ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String namel = rs.getString("RECIPE");
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
                    + "    MAX(MENU.ID) AS ID  \n"
                    + "FROM \n"
                    + "    MENU ");
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
    public Menu findPhotoByMenuId(Menu menu) {
        Menu menuPhoto = new Menu();
        Blob photo = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT  PHOTO FROM MENU  WHERE ID  =" + menu.getId() + "");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                Blob picturel = rs.getBlob("PHOTO");
                photo = picturel;
                byte[] bytes = convertBlobToBytes(picturel);
                menuPhoto.setPhoto(bytes);

            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Opss...", JOptionPane.ERROR_MESSAGE);
        }
        return menuPhoto;
    }

    private static byte[] convertBlobToBytes(java.sql.Blob blob) throws IOException, SQLException {
        try (InputStream inputStream = blob.getBinaryStream()) {
            return convertInputStreamToBytes(inputStream);
        }
    }

    private static byte[] convertInputStreamToBytes(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
    }

    private static void writeBytesToFile(byte[] bytes, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(bytes);
        }
    }

    @Override
    public ArrayList<TodaysMenu> selectMenuPrice() {
        ArrayList<TodaysMenu> list = new ArrayList<>();
        try {
            String query = """
                           SELECT 
                               TODAYS_MENU.ID, 
                               TODAYS_MENU.MENU_ID, 
                               TODAYS_MENU.PRICE, 
                               MENU.PRICE, 
                               MENU.RECIPE, 
                               MENU.INGREDIENTS 
                           FROM 
                               TODAYS_MENU 
                           INNER JOIN 
                               MENU 
                           ON (TODAYS_MENU.MENU_ID = MENU.ID) 
                           INNER JOIN 
                               CATEGORY 
                           ON (MENU.CATEGORY_ID = CATEGORY.ID) 
                           WHERE 
                               TODAYS_MENU.DELETED = FALSE 
                           AND TODAYS_MENU.CREATEDAT = CURRENT_DATE 
                           GROUP BY 
                               TODAYS_MENU.ID, 
                               TODAYS_MENU.MENU_ID, 
                               TODAYS_MENU.PRICE, 
                               MENU.PRICE, 
                               MENU.RECIPE, 
                               MENU.INGREDIENTS 
                           ORDER BY 
                               MENU.RECIPE ASC""";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Menu m = new Menu();
                Category c = new Category();
                TodaysMenu tm = new TodaysMenu();
                tm.setId(rs.getLong("ID"));
                m.setId(rs.getLong("MENU_ID"));
                m.setRecipe(rs.getString("RECIPE"));
                m.setIngredients(rs.getString("INGREDIENTS"));
//                tm.setMenu(m);
                tm.setPrice(rs.getFloat("PRICE"));

                tm.setMenu(m);
                list.add(tm);
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
    public void updateMenuPrice(Menu menu) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE MENU SET  PRICE =?  WHERE MENU.ID = ?");
            ps.setFloat(1, menu.getPrice());
            ps.setLong(2, menu.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
