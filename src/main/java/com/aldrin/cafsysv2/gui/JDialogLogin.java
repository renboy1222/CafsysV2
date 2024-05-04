/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.aldrin.cafsysv2.gui;

import com.aldrin.cafsysv2.dao.impl.UserAccountDAOImpl;
import com.aldrin.cafsysv2.model.UserAccount;
import com.aldrin.cafsysv2.util.LoginUser;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Java Programming with Aldrin
 */
@Setter
@Getter
@ToString
public class JDialogLogin extends javax.swing.JDialog {

    /**
     * Creates new form JDialogLogin
     */
    private JFrameCafSys jFrameCafSys = new JFrameCafSys();

    public JDialogLogin(JFrameCafSys jFrameCafSys, boolean modal) {
        super(jFrameCafSys, modal);
        this.jFrameCafSys = jFrameCafSys;
        initComponents();
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent we) {
//                quitApp();
//            }
//        });
        jTextFieldUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Username");
        jPasswordFieldPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");

        //clear button
        jTextFieldUsername.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        jTextFieldUsername.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("svg/user.svg", 24, 24));

        //reveal button
        jPasswordFieldPassword.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true;" + "showCapsLock:true");
        jPasswordFieldPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("svg/key.svg", 24, 24));
    }

 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButtonLogin = new javax.swing.JButton();
        jTextFieldUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jLabelErrorMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Password");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 150, 93, 20));

        jButtonLogin.setBackground(new java.awt.Color(4, 170, 109));
        jButtonLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonLogin.setIcon(new FlatSVGIcon("svg/login.svg",24,24));
        jButtonLogin.setText("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 230, 409, 35));
        getContentPane().add(jTextFieldUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 110, 410, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Username");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 90, 93, 20));

        jPasswordFieldPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordFieldPasswordKeyPressed(evt);
            }
        });
        getContentPane().add(jPasswordFieldPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 170, 410, 30));

        jLabelErrorMessage.setForeground(new java.awt.Color(153, 0, 0));
        jLabelErrorMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabelErrorMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 50, 410, 30));

        setSize(new java.awt.Dimension(440, 314));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        login();
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jPasswordFieldPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }//GEN-LAST:event_jPasswordFieldPasswordKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelErrorMessage;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the jTextFieldUsername
     */
    public javax.swing.JTextField getjTextFieldUsername() {
        return jTextFieldUsername;
    }

    /**
     * @param jTextFieldUsername the jTextFieldUsername to set
     */
    public void setjTextFieldUsername(javax.swing.JTextField jTextFieldUsername) {
        this.jTextFieldUsername = jTextFieldUsername;
    }

    /**
     * @return the jPasswordFieldPassword
     */
    public javax.swing.JPasswordField getjPasswordFieldPassword() {
        return jPasswordFieldPassword;
    }

    /**
     * @param jPasswordFieldPassword the jPasswordFieldPassword to set
     */
    public void setjPasswordFieldPassword(javax.swing.JPasswordField jPasswordFieldPassword) {
        this.jPasswordFieldPassword = jPasswordFieldPassword;
    }

    /**
     * @return the jLabelErrorMessage
     */
    public javax.swing.JLabel getjLabelErrorMessage() {
        return jLabelErrorMessage;
    }

    /**
     * @param jLabelErrorMessage the jLabelErrorMessage to set
     */
    public void setjLabelErrorMessage(javax.swing.JLabel jLabelErrorMessage) {
        this.jLabelErrorMessage = jLabelErrorMessage;
    }

    private void quitApp() {
        try {
            int reply = JOptionPane.showConfirmDialog(this,
                    "Are you sure to exit CafSysv2 application?",
                    "AldrinPOS - Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (reply == JOptionPane.YES_OPTION) {
                System.exit(0);        //Close the Application.
            } else if (reply == JOptionPane.NO_OPTION) {
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void login() {
        UserAccountDAOImpl userDAOImpl = new UserAccountDAOImpl();
        LoginUser loginUser = new LoginUser();
        UserAccount user = new UserAccount();
        user.setUsername(jTextFieldUsername.getText());
        user.setPassword(jPasswordFieldPassword.getText());
        user = userDAOImpl.loginUserAccount(user);
        if (user != null) {
            dispose();
            loginUser.setUserAccount(user);
        } else {
            jLabelErrorMessage.setText("Please check your username and password and try again.");
            jTextFieldUsername.putClientProperty("JComponent.outline", "error");
            jPasswordFieldPassword.putClientProperty("JComponent.outline", "error");

        }
    }

}
