/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.aldrin.cafsysv2;

import com.aldrin.cafsysv2.gui.JFrameCafSys;
import com.aldrin.cafsysv2.gui.JFrameLogin;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

/**
 *
 * @author ALRIN B.C.
 */
public class CafSysv2 {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        UIManager.put("Button.arc", 8);//JButton
        UIManager.put("ProgressBar.arc", 999);//JProgressBar
        UIManager.put("TextComponent.arc", 8);//JTextField,JPasswordField,JFormattedTextField
        UIManager.put("CheckBox", 999);//JCheckBox
        UIManager.put("Component.arc", 8);//JComboBox,JSpinner

        UIManager.put("Component.innerFocusWidth", 2);//JComboBox, JTextField,JPasswordField,JFormattedTextField,JSpinner
        UIManager.put("Button.innerFocusWidth", 2);//JButton

        System.setProperty("flatlaf.menuBarEmbedded", "false");
//        JFrameCafSys cafSys = new JFrameCafSys();
//        cafSys.setVisible(true);
        JFrameLogin login =new JFrameLogin();
        login.setVisible(true);

    }
}
