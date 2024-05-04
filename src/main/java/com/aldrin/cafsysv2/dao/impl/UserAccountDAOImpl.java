/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.cafsysv2.dao.impl;


import com.aldrin.cafsysv2.dao.UserAccountDAO;
import com.aldrin.cafsysv2.model.Role;
import com.aldrin.cafsysv2.model.UserAccount;
import com.aldrin.cafsysv2.util.ComboBoxList;
import com.aldrin.cafsysv2.util.LoginUser;
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
public class UserAccountDAOImpl extends DBConnection implements UserAccountDAO {

    @Override
    public void addUserAccount(UserAccount userAccount) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO USER_ACCOUNT (ID,FIRSTNAME,SURNAME,USERNAME,PASSWORD,ROLE_ID,PHOTO,ACTIVE) VALUES  (?,?,?,?,?,?,?,?) ");
            ps.setLong(1, getMaxId());
            ps.setString(2, userAccount.getFirstname());
            ps.setString(3, userAccount.getSurname());
            ps.setString(4, userAccount.getUsername());
            ps.setString(5, userAccount.getPassword());
            ps.setLong(6, userAccount.getRole().getId());
            ps.setBytes(7, userAccount.getPhoto());
            ps.setBoolean(8, userAccount.getActive());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserAccount(UserAccount userAccount) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE USER_ACCOUNT SET FIRSTNAME =?, SURNAME =?, USERNAME=?, PASSWORD=?, ROLE_ID=?, PHOTO=? ,ACTIVE =? WHERE USER_ACCOUNT.ID = ?");
            ps.setString(1, userAccount.getFirstname());
            ps.setString(2, userAccount.getSurname());
            ps.setString(3, userAccount.getUsername());
            ps.setString(4, userAccount.getPassword());
            ps.setLong(5, userAccount.getRole().getId());
            ps.setBytes(6, userAccount.getPhoto());
            ps.setBoolean(7, userAccount.getActive());
            ps.setLong(8, userAccount.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserAccount(UserAccount userAccount) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE USER_ACCOUNT SET ACTIVE =? WHERE USER_ACCOUNT.ID = ? ");
            ps.setBoolean(1, true);
            ps.setLong(2, userAccount.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<UserAccount> selectUserAccount() {
        ArrayList<UserAccount> list = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    USER_ACCOUNT.ID, \n"
                    + "    USER_ACCOUNT.ACTIVE, \n"
                    + "    USER_ACCOUNT.FIRSTNAME, \n"
                    + "    USER_ACCOUNT.PASSWORD, \n"
                    + "    USER_ACCOUNT.SURNAME, \n"
                    + "    USER_ACCOUNT.USERNAME, \n"
                    + "    USER_ACCOUNT.ROLE_ID, \n"
                    + "    ROLE.ROLE \n"
                    + "FROM \n"
                    + "    USER_ACCOUNT \n"
                    + "INNER JOIN \n"
                    + "    ROLE \n"
                    + "ON \n"
                    + "    ( \n"
                    + "        USER_ACCOUNT.ROLE_ID = ROLE.ID)  ORDER BY ROLE.ROLE ASC";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                UserAccount u = new UserAccount();
                Role r = new Role();
                r.setId(rs.getLong("ROLE_ID"));
                r.setRole(rs.getString("ROLE"));
                u.setId(rs.getLong("ID"));
                u.setRole(r);
                u.setFirstname(rs.getString("FIRSTNAME"));
                u.setSurname(rs.getString("SURNAME"));
                u.setUsername(rs.getString("USERNAME"));
                u.setPassword(rs.getString("PASSWORD"));
                u.setActive(rs.getBoolean("ACTIVE"));

                list.add(u);
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
    public UserAccount findPhotoByUserAccountId(UserAccount userAccount) {
        UserAccount userPhoto = new UserAccount();
        Blob photo = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT  PHOTO FROM USER_ACCOUNT  WHERE ID  =" + userAccount.getId() + "");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                Blob picturel = rs.getBlob("PHOTO");
                photo = picturel;
                byte[] bytes = convertBlobToBytes(picturel);
                userPhoto.setPhoto(bytes);
            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Opss...", JOptionPane.ERROR_MESSAGE);
        }
        return userPhoto;
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
    public UserAccount loginUserAccount(UserAccount userAccount) {
        UserAccount userInfo = null;
        LoginUser loginUser = new LoginUser();
        loginUser.setUserAccount(null);
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    USER_ACCOUNT.FIRSTNAME, \n"
                    + "    USER_ACCOUNT.ACTIVE, \n"
                    + "    USER_ACCOUNT.ID, \n"
                    + "    USER_ACCOUNT.ROLE_ID, \n"
                    + "    ROLE.ROLE, \n"
                    + "    USER_ACCOUNT.PASSWORD, \n"
                    + "    USER_ACCOUNT.SURNAME, \n"
                    + "    USER_ACCOUNT.USERNAME \n"
                    + "FROM \n"
                    + "    USER_ACCOUNT \n"
                    + "INNER JOIN \n"
                    + "    ROLE \n"
                    + "ON \n"
                    + "    (USER_ACCOUNT.ROLE_ID = ROLE.ID) WHERE USER_ACCOUNT.USERNAME =BINARY '" + userAccount.getUsername() + "' AND USER_ACCOUNT.PASSWORD =BINARY '" + userAccount.getPassword() + "' AND USER_ACCOUNT.ACTIVE =true");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UserAccount ua = new UserAccount();
                
                Role r = new Role();
                Long userId = rs.getLong("ID");
                Long roleId = rs.getLong("ROLE_ID");
                String firstname = rs.getString("FIRSTNAME");
                String surname = rs.getString("SURNAME");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String role = rs.getString("ROLE");
                boolean active = rs.getBoolean("ACTIVE");
                r.setId(roleId);
                r.setRole(role);
                ua.setRole(r);
                ua.setId(userId);
                ua.setFirstname(firstname);
                ua.setSurname(surname);
                ua.setUsername(username);
                ua.setPassword(password);
                ua.setActive(active);
                userInfo = ua;
                loginUser.setUserAccount(ua);
            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Opss...", JOptionPane.ERROR_MESSAGE);
        }

        return userInfo;
    }

    @Override
    public Boolean changePassword(UserAccount userAccount) {
        Boolean changePassword = false;
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE USER_ACCOUNT SET  PASSWORD=?  WHERE USER_ACCOUNT.ID = ?");
            ps.setString(1, userAccount.getPassword());
            ps.setLong(2, userAccount.getId());
            ps.execute();
            ps.close();
            closeConnection();
            changePassword = true;
            LoginUser loginUser = new LoginUser();
            loginUser.getUserAccount().setPassword(userAccount.getPassword());
           

        } catch (Exception e) {
            e.printStackTrace();
        }
        return changePassword;

    }

    @Override
    public Long getMaxId() {
        Long maxId = null;
        try {
            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("SELECT \n"
                    + "    MAX(USER_ACCOUNT.ID) AS ID  \n"
                    + "FROM \n"
                    + "    USER_ACCOUNT ");
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
    public void comboBoxUserAccount() {
        this.setList(new ArrayList<ComboBoxList>());
        try {
            getDBConn();
            PreparedStatement statement;
            ResultSet rs;
            statement = getCon().prepareStatement("SELECT  \n"
                    + "    USER_ACCOUNT.ID,\n"
                    + "    USER_ACCOUNT.SURNAME, \n"
                    + "    USER_ACCOUNT.FIRSTNAME \n"
                    + "FROM \n"
                    + "    USER_ACCOUNT ORDER BY USER_ACCOUNT.SURNAME ASC ");
            rs = statement.executeQuery();
            while (rs.next()) {
                Long idl = rs.getLong("ID");
                String firstname = rs.getString("FIRSTNAME");
                String surname = rs.getString("SURNAME");

                this.getList().add(new ComboBoxList(idl, surname + ", " + firstname));
            }
            rs.close();
            statement.close();
            closeConnection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
