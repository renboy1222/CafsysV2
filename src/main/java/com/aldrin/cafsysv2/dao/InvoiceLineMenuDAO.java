/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.cafsysv2.dao;

import com.aldrin.cafsysv2.model.Invoice;
import com.aldrin.cafsysv2.model.InvoiceLineMenu;
import java.util.ArrayList;

/**
 *
 * @author ALDRIN B. C.
 */
public interface InvoiceLineMenuDAO {

    public Long getMaxId();

//    add InvoiceLineMenu
    public void addInvoiceLineMenu(InvoiceLineMenu invoiceLineMenu);

//    update InvoiceLineMenu
    public void updateInvoiceLineMenu(InvoiceLineMenu invoiceLineMenu);

//    delete InvoiceLineMenu
    public void deleteInvoiceLineMenu(InvoiceLineMenu invoiceLineMenu);

//    list of InvoiceLineMenu
    public ArrayList<InvoiceLineMenu> selectInvoiceLineMenu();

    public void comboBoxInvoiceLineMenu();

    public ArrayList<InvoiceLineMenu> selectInvoiceLineMenuByInvoiceId(Long id);

}
