/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.cafsysv2.dao;

import com.aldrin.cafsysv2.model.Invoice;
import java.util.ArrayList;

/**
 *
 * @author ALDRIN B. C.
 */
public interface InvoiceDAO {

    public Long getMaxId();

//    add Invoice
    public void addInvoice(Invoice invoice);

//    update Invoice
    public void updateInvoice(Invoice invoice);

//    delete Invoice
    public void deleteInvoice(Invoice invoice);

//    list of Invoice
    public ArrayList<Invoice> selectInvoice();

    public void comboBoxInvoice();

    public ArrayList<com.aldrin.cafsysv2.model.Invoice> getSalesUser(Long userId, Long startId, Long endId);

    public void invoiceDateTimeComboBox();
    
    public void invoiceDateTimeComboBox(Long userId) ;

}
