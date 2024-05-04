/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.cafsysv2.util;

import com.aldrin.cafsysv2.model.UserAccount;

/**
 *
 * @author Java Programming with Aldrin
 */

public class LoginUser {
    private static UserAccount userAccount;

    /**
     * @return the userAccount
     */
    public static UserAccount getUserAccount() {
        return userAccount;
    }

    /**
     * @param aUserAccount the userAccount to set
     */
    public static void setUserAccount(UserAccount aUserAccount) {
        userAccount = aUserAccount;
    }
}
