/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.cafsysv2.model;

import java.util.Date;
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
public class Invoice {

    private Long id;
    private Date createdAt;
    private Float customerCash;
    private Integer noOfOrders;
    //Data integration
    private Float total;
    private Integer tableNo;
    private OrderOption orderOption;
    private UserAccount userAccount;
}
