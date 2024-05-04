/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.cafsysv2.dao.impl;

import com.aldrin.cafsysv2.dao.CategoryDAO;
import static com.aldrin.cafsysv2.dao.impl.DBConnection.closeConnection;
import static com.aldrin.cafsysv2.dao.impl.DBConnection.getCon;
import com.aldrin.cafsysv2.model.Category;
import com.aldrin.cafsysv2.model.Menu;
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
public class CategoryDAOImpl extends DBConnection implements CategoryDAO {

    @Override
    public void addCategory(Category category) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO CATEGORY (ID,CATEGORY,DESCRIPTION,PHOTO) VALUES  (?,?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setString(2, category.getCategory());
            ps.setString(3, category.getDescription());
            ps.setBytes(4, category.getPhoto());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategory(Category category) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CATEGORY SET CATEGORY =?, DESCRIPTION =?,PHOTO=? WHERE CATEGORY.ID = ?");
            ps.setString(1, category.getCategory());
            ps.setString(2, category.getDescription());
            ps.setBytes(3, category.getPhoto());
            ps.setLong(4, category.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(Category category) {
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
    public ArrayList<Category> selectCategory() {
        ArrayList<Category> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM CATEGORY WHERE CATEGORY.DELETED =FALSE  ORDER BY CATEGORY ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getLong("ID"));
                c.setCategory(rs.getString("CATEGORY"));
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
    public void comboBoxCategory() {
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
                    + "    MAX(CATEGORY.ID) AS ID  \n"
                    + "FROM \n"
                    + "    CATEGORY ");
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
    public Category findPhotoByCategoryId(Category category) {
        Category menuPhoto = new Category();
        Blob photo = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT  PHOTO FROM CATEGORY  WHERE ID  =" + category.getId() + "");
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

}
