/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.cafsysv2.events;

import com.aldrin.cafsysv2.gui.JFrameCafSys;
import com.aldrin.cafsysv2.model.Category;
import com.aldrin.cafsysv2.model.Menu;
import com.aldrin.cafsysv2.model.TodaysMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JButton;

/**
 *
 * @author Java Programming with Aldrin
 */
public class ActionListenerHandler implements ActionListener {

    private ArrayList<Category> categoryList;
    private LinkedList<Menu> menuList;
    private LinkedList<TodaysMenu> motdList;
    private JButton jButton;
    private ArrayList<JButton> arrayButtons = new ArrayList<>();
    private JFrameCafSys jFrameCafSys;

    public ActionListenerHandler() {
    }

    public ActionListenerHandler(ArrayList<Category> categoryList,JFrameCafSys jFrameCafSys) {
        this.categoryList = categoryList;
        this.jFrameCafSys =jFrameCafSys;
    }

    public ActionListenerHandler(LinkedList<Menu> menuList,JFrameCafSys jFrameCafSys) {
        this.menuList = menuList;
        this.jFrameCafSys =jFrameCafSys;
    }

    public ActionListenerHandler(JButton jButton, LinkedList<TodaysMenu> motdList,JFrameCafSys jFrameCafSys) {
        this.setjButton(jButton);
        this.motdList = motdList;
        this.jFrameCafSys =jFrameCafSys;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Category Event
        String command = e.getActionCommand();
        if (getCategoryList() != null) {
            for (Category c : getCategoryList()) {
                if (command.equals(c.getCategory())) {
//                    JFrameCafSys p = new JFrameCafSys(c,jFrameCafSys);
                    jFrameCafSys.selectTodaysMenu(c.getId());
                    jFrameCafSys.revalidate();
                }
            }
        }
        //Today Menu Events
        JButton jButton = new JButton();
        if (e.getSource() == getjButton()) {
            for (TodaysMenu motd : getMotdList()) {
                TodaysMenu m = new TodaysMenu();
                m.setId(Long.parseLong(getjButton().getName())); 
                if (motd.getId() == Integer.parseInt(getjButton().getName())) {
                     JFrameCafSys p = new JFrameCafSys(motd);
                     
                }
            }
        }
    }

    /**
     * @return the categoryList
     */
    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    /**
     * @param categoryList the categoryList to set
     */
    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    /**
     * @return the menuList
     */
    public LinkedList<Menu> getMenuList() {
        return menuList;
    }

    /**
     * @param menuList the menuList to set
     */
    public void setMenuList(LinkedList<Menu> menuList) {
        this.menuList = menuList;
    }

    /**
     * @return the jButton
     */
    public JButton getjButton() {
        return jButton;
    }

    /**
     * @param jButton the jButton to set
     */
    public void setjButton(JButton jButton) {
        this.jButton = jButton;
    }

    /**
     * @return the arrayButtons
     */
    public ArrayList<JButton> getArrayButtons() {
        return arrayButtons;
    }

    /**
     * @param arrayButtons the arrayButtons to set
     */
    public void setArrayButtons(ArrayList<JButton> arrayButtons) {
        this.arrayButtons = arrayButtons;
    }

    /**
     * @return the motdList
     */
    public LinkedList<TodaysMenu> getMotdList() {
        return motdList;
    }

    /**
     * @param motdList the motdList to set
     */
    public void setMotdList(LinkedList<TodaysMenu> motdList) {
        this.motdList = motdList;
    }
}
