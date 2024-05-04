/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.cafsysv2.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Java Programming with Aldrin
 */
@ToString
@Getter
@Setter
public class InvoiceLineMenu {

    private Long id;

    private Integer qty;
    //Data integration
    private Invoice invoice;
    private TodaysMenu todaysMenu;
}
