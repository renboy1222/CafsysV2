/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.cafsysv2.dao.impl;

import com.aldrin.cafsysv2.dao.TodaysMenuDAO;
import static com.aldrin.cafsysv2.dao.impl.DBConnection.closeConnection;
import static com.aldrin.cafsysv2.dao.impl.DBConnection.getCon;
import com.aldrin.cafsysv2.model.Category;
import com.aldrin.cafsysv2.model.TodaysMenu;
import com.aldrin.cafsysv2.util.ComboBoxList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ALDRIN B. C.
 */
@Setter
@Getter
public class TodaysMenuDAOImpl extends DBConnection implements TodaysMenuDAO {

    @Override
    public void addTodaysMenu(TodaysMenu todaysMenu) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO CATEGORY (ID,CATEGORY,DESCRIPTION) VALUES  (?,?,?) ");
            ps.setLong(1, getMaxId());
//            ps.setString(2, category.getCategory());
//            ps.setString(3, category.getDescription());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTodaysMenu(TodaysMenu todaysMenu) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CATEGORY SET CATEGORY =?, DESCRIPTION =? WHERE CATEGORY.ID = ?");
//            ps.setString(1, category.getCategory());
//            ps.setString(2, category.getDescription());
//            ps.setLong(3, category.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTodaysMenu(TodaysMenu todaysMenu) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE CATEGORY SET DELETED =? WHERE CATEGORY.ID = ? ");
            ps.setBoolean(1, true);
//            ps.setLong(2, category.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ComboBoxList> list;

    @Override
    public ArrayList<TodaysMenu> selectTodaysMenu() {
        ArrayList<com.aldrin.cafsysv2.model.TodaysMenu> list = new ArrayList<>();
        try {
            String query = """
                           SELECT 
                              MENU.ID, 
                              MENU.CATEGORY_ID,    
                              CATEGORY.CATEGORY,   
                              MENU.RECIPE, 
                              MENU.PRICE, 
                              MENU.INGREDIENTS 
                           FROM 
                               MENU 
                           INNER JOIN 
                               CATEGORY 
                           ON (MENU.CATEGORY_ID = CATEGORY.ID)WHERE MENU.DELETED =FALSE ORDER BY MENU.RECIPE, 
                              CATEGORY.CATEGORY ASC 
                           """;
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                com.aldrin.cafsysv2.model.TodaysMenu tm = new TodaysMenu();
                com.aldrin.cafsysv2.model.Category c = new com.aldrin.cafsysv2.model.Category();
                com.aldrin.cafsysv2.model.Menu m = new com.aldrin.cafsysv2.model.Menu();
                m.setId(rs.getLong("ID"));
                c.setId(rs.getLong("CATEGORY_ID"));
                c.setCategory(rs.getString("CATEGORY"));
                m.setRecipe(rs.getString("RECIPE"));
                m.setCategory(c);
                m.setPrice(rs.getFloat("PRICE"));
                m.setIngredients(rs.getString("ingredient"));
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
    public void comboBoxTodaysMenu() {
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
                    + "    MAX(TODAYS_MENU.ID) AS ID  \n"
                    + "FROM \n"
                    + "    TODAYS_MENU ");
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
    public ArrayList<com.aldrin.cafsysv2.model.TodaysMenu> getMenuList() {
        ArrayList<com.aldrin.cafsysv2.model.TodaysMenu> list = new ArrayList<>();
        try {
            String query = """
                           SELECT 
                              MENU.ID, 
                              MENU.CATEGORY_ID,    
                              CATEGORY.CATEGORY,   
                              MENU.RECIPE, 
                              MENU.PRICE, 
                              MENU.INGREDIENTS 
                           FROM 
                               MENU 
                           INNER JOIN 
                               CATEGORY 
                           ON (MENU.CATEGORY_ID = CATEGORY.ID)WHERE MENU.DELETED =FALSE ORDER BY MENU.RECIPE, 
                              CATEGORY.CATEGORY ASC 
                           """;
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                com.aldrin.cafsysv2.model.TodaysMenu tm = new TodaysMenu();
                com.aldrin.cafsysv2.model.Category c = new com.aldrin.cafsysv2.model.Category();
                com.aldrin.cafsysv2.model.Menu m = new com.aldrin.cafsysv2.model.Menu();
                m.setId(rs.getLong("ID"));
                c.setId(rs.getLong("CATEGORY_ID"));
                c.setCategory(rs.getString("CATEGORY"));
                m.setRecipe(rs.getString("RECIPE"));
                m.setCategory(c);
                m.setPrice(rs.getFloat("PRICE"));
                m.setIngredients(rs.getString("ingredient"));
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
    public Boolean selectTodaysMenu(Long id) {
        Boolean motd = false;
        try {

            getDBConn();
            PreparedStatement statement = getCon().prepareStatement("""
                                                                    SELECT 
                                                                        TODAYS_MENU.MENU_ID, 
                                                                        TODAYS_MENU.PRICE, 
                                                                        TODAYS_MENU.DELETED, 
                                                                        TODAYS_MENU.ID,
                                                                        CREATEDAT
                                                                    FROM 
                                                                        TODAYS_MENU WHERE CREATEDAT =CURRENT_DATE  AND MENU_ID =?  and deleted =FALSE  
                                                                    """);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                motd = true;
            }
            rs.close();
            statement.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Opss...", JOptionPane.ERROR_MESSAGE);
        }
        return motd;
    }

    @Override
    public Boolean getTodaysMenuDRemove(Long menuId) {
        Boolean updated = false;
        try {
            String query = "SELECT \n"
                    + "    TODAYS_MENU.MENU_ID, \n"
                    + "    TODAYS_MENU.PRICE, \n"
                    + "    TODAYS_MENU.DELETED, \n"
                    + "    TODAYS_MENU.ID,\n"
                    + "    CREATEDAT\n"
                    + "FROM \n"
                    + "    TODAYS_MENU WHERE CREATEDAT =CURRENT_DATE  AND MENU_ID =" + menuId + "  and deleted =TRUE  ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer n = rs.getInt("id");
                if (n != null) {
                    updated = true;
                } else {
                    updated = false;
                }
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return updated;
    }

    @Override
    public void updateTodaysMenu(Long id) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE \n"
                    + "  TODAYS_MENU \n"
                    + " SET \n"
                    + "  DELETED = false \n"
                    + "WHERE ID = ? ");
            ps.setLong(1, id);
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean getTodaysMenuDeletedRecover(Long menuId) {
        Boolean updated = false;
        try {
            String query = "SELECT \n"
                    + "    TODAYS_MENU.MENU_ID, \n"
                    + "    TODAYS_MENU.PRICE, \n"
                    + "    TODAYS_MENU.DELETED, \n"
                    + "    TODAYS_MENU.ID,\n"
                    + "    CREATEDAT\n"
                    + "FROM \n"
                    + "    TODAYS_MENU WHERE CREATEDAT =CURRENT_DATE  AND MENU_ID =" + menuId + "  and deleted =TRUE   ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer n = rs.getInt("ID");
                updateTodaysMenuUpdateDeleted(n);

                if (n != null) {
                    updated = true;
                } else {
                    updated = false;
                }
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return updated;
    }

    @Override
    public void addMenuOfTheDay(com.aldrin.cafsysv2.model.Menu menu) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("INSERT INTO TODAYS_MENU (\n"
                    + " ID, MENU_ID,\n"
                    + "  PRICE,\n"
                    + "  CREATEDAT) \n"
                    + "VALUES\n"
                    + "  (?,?,?,CURRENT_DATE) ");
            ps.setLong(1, getMaxId());
            ps.setLong(2, menu.getId());
            ps.setDouble(3, menu.getPrice());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTodaysMenuUpdateDeleted(Integer id) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE  TODAYS_MENU \n"
                    + "   SET \n"
                    + "   DELETED = FALSE \n"
                    + "   WHERE ID = ?  AND CREATEDAT =CURRENT_DATE");
            ps.setInt(1, id);
            ps.execute();
//            ps.close();
//            c.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean getTodaysMenuUpdated(Long menuId) {
        Boolean updated = false;
        try {
            String query = "SELECT \n"
                    + "    TODAYS_MENU.MENU_ID, \n"
                    + "    TODAYS_MENU.PRICE, \n"
                    + "    TODAYS_MENU.DELETED, \n"
                    + "    TODAYS_MENU.ID,\n"
                    + "    CREATEDAT\n"
                    + "FROM \n"
                    + "    TODAYS_MENU WHERE CREATEDAT =CURRENT_DATE  AND MENU_ID =" + menuId + " ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer n = rs.getInt("ID");
                if (n != null) {
                    updated = true;
                } else {
                    updated = false;
                }
            }
            rs.close();
            st.close();
            closeConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return updated;
    }

    public void updateTodaysD2(Long id) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE  TODAYS_MENU \n"
                    + "                    SET \n"
                    + "                     DELETED =TRUE \n"
                    + "                    WHERE MENU_ID = ?  AND CREATEDAT =CURRENT_DATE");
            ps.setLong(1, id);
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Category> selectTodaysMenuCategory() {
        ArrayList<Category> list = new ArrayList<>();
        try {
            String query = """
                   SELECT  
                       CATEGORY.ID, 
                       CATEGORY.CATEGORY, CATEGORY.DESCRIPTION 
                   FROM 
                       TODAYS_MENU 
                   INNER JOIN 
                       MENU 
                   ON ( TODAYS_MENU.MENU_ID = MENU.ID) 
                   INNER JOIN 
                       CATEGORY 
                   ON (MENU.CATEGORY_ID = CATEGORY.ID) where TODAYS_MENU.DELETED =FALSE AND TODAYS_MENU.CREATEDAT =CURRENT_DATE 
                             GROUP BY CATEGORY.ID, 
                       CATEGORY.CATEGORY, CATEGORY.DESCRIPTION ORDER BY CATEGORY.CATEGORY ASC                    
                           """;
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

    public LinkedList<com.aldrin.cafsysv2.model.TodaysMenu> getMenuByCategoryIdMenuId(Long id) {
        LinkedList<com.aldrin.cafsysv2.model.TodaysMenu> list = new LinkedList<>();
        try {
            String query = "SELECT \n"
                    + "    TODAYS_MENU.ID,\n"
                    + "    TODAYS_MENU.MENU_ID,MENU.RECIPE,\n"
                    + "    TODAYS_MENU.PRICE\n"
                    + "    \n"
                    + "FROM \n"
                    + "    TODAYS_MENU \n"
                    + "INNER JOIN \n"
                    + "    MENU \n"
                    + "ON ( TODAYS_MENU.MENU_ID = MENU.ID) \n"
                    + "INNER JOIN \n"
                    + "    CATEGORY \n"
                    + "ON (MENU.CATEGORY_ID = CATEGORY.ID) where TODAYS_MENU.DELETED =FALSE AND CATEGORY.DELETED =FALSE AND MENU.CATEGORY_ID =" + id + " AND TODAYS_MENU.CREATEDAT =CURRENT_DATE \n"
                    + "  ORDER BY MENU.RECIPE ASC ";
            getDBConn();
            Statement st = getCon().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                com.aldrin.cafsysv2.model.TodaysMenu tm = new com.aldrin.cafsysv2.model.TodaysMenu();
                com.aldrin.cafsysv2.model.Menu menu = new com.aldrin.cafsysv2.model.Menu();
                tm.setId(rs.getLong("ID"));
                menu.setRecipe(rs.getString("RECIPE"));
                menu.setId(rs.getLong("MENU_ID"));
                tm.setPrice(rs.getFloat("PRICE"));
                tm.setMenu(menu);
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

    public TodaysMenu addOrderToTable(TodaysMenu tm) {
        TodaysMenu t = new TodaysMenu();
        try {
            getDBConn();
//            WHERE `motd`.`id` =?  AND `motd`.`date` =CURDATE()
            PreparedStatement statement = getCon().prepareStatement("SELECT  \n"
                    + "    `motd`.`id`\n"
                    + "    ,`motd`.`menu_id`\n"
                    + "    , `menu`.`menu`\n"
                    + "    , `menu`.`price`\n"
                    + "\n"
                    + "FROM\n"
                    + "    `motd`\n"
                    + "    INNER JOIN `menu` \n"
                    + "        ON (`motd`.`menu_id` = `menu`.`id`)WHERE `motd`.`id` =?  AND `motd`.`date` =CURDATE()");
            statement.setLong(1, tm.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
//                t.setId(rs.getInt("id"));
//                t.setMenuId(rs.getInt("menu_id"));
//                t.setMenu(rs.getString("menu"));
//                t.setPrice(rs.getDouble("price"));
            }
            rs.close();
            statement.close();
            closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void updateMenuPrice(TodaysMenu tm) {
        try {
            getDBConn();
            java.sql.PreparedStatement ps = getCon().prepareStatement("UPDATE TODAYS_MENU SET  PRICE =?  WHERE TODAYS_MENU.ID = ?");
            ps.setFloat(1, tm.getPrice());
            ps.setLong(2, tm.getId());
            ps.execute();
            ps.close();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
