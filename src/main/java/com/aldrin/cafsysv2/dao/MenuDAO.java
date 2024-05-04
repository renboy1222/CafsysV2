/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.cafsysv2.dao;

import com.aldrin.cafsysv2.model.Menu;
import com.aldrin.cafsysv2.model.TodaysMenu;
import java.util.ArrayList;

/**
 *
 * @author ALDRIN B. C.
 */
public interface MenuDAO {

    public Long getMaxId();

//    add Menu
    public void addMenu(Menu menu);

//    update Menu
    public void updateMenu(Menu menu);

//    delete Menu
    public void deleteMenu(Menu menu);

//    list of Menu
    public ArrayList<Menu> selectMenu();

    public void comboBoxMenu();

    public Menu findPhotoByMenuId(Menu menu);

    public ArrayList<TodaysMenu> selectMenuPrice();

    public void updateMenuPrice(Menu menu);

}
