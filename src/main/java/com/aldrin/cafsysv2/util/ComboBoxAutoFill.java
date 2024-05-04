/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.cafsysv2.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Java Programming with Aldrin
 */

//        jComboBoxBarcodeItem.setEditable(true);
//        JTextComponent editor = (JTextComponent) jComboBoxBarcodeItem.getEditor().getEditorComponent();
//        editor.setDocument(new ComboBoxAutoFill(jComboBoxBarcodeItem));

public class ComboBoxAutoFill extends PlainDocument {

    JComboBox comboBox;
    ComboBoxModel model;
    JTextComponent editor;
    boolean selecting = false;

    public ComboBoxAutoFill(final JComboBox comboBox) {
        this.comboBox = comboBox;
        model = comboBox.getModel();
        editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (comboBox.isDisplayable()) {
                    comboBox.setPopupVisible(true);
                }
            }
        });
    }

    public void remove(int offs, int len) throws BadLocationException {
        if (selecting) {
            return;
        }
        super.remove(offs, len);
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (selecting) {
            return;
        }
        super.insertString(offs, str, a);
        Object item = lookupItem(getText(0, getLength()));
        if (item != null) {
            setSelectedItem(item);
            setText(item.toString());
        highlightCompletedText(offs + str.length());
        } else {
            item = comboBox.getSelectedItem();
            offs = offs - str.length();
            comboBox.getToolkit().beep();
        }
//        setText(item.toString());
//        highlightCompletedText(offs + str.length());
    }
    

    private void setText(String text) throws BadLocationException {
        super.remove(0, getLength());
        super.insertString(0, text, null);
    }

    private void highlightCompletedText(int start) {
        editor.setSelectionStart(start);
        editor.setSelectionEnd(getLength());
    }

    private void setSelectedItem(Object item) {
        selecting = true;
        model.setSelectedItem(item);
        selecting = false;
    }

   

    private boolean startsWithIgnoreCase(String str1, String strIgnore) {
        return str1.toUpperCase().startsWith(strIgnore.toUpperCase());
    }
    
     private Object lookupItem(String pattern) {
        Object selectedItem = model.getSelectedItem();
        if (selectedItem != null && startsWithIgnoreCase(selectedItem.toString(), pattern)) {
            return selectedItem;
        } else {
            for (int i = 0, n = model.getSize(); i < n; i++) {
                Object currentItem = model.getElementAt(i);
                if (startsWithIgnoreCase(currentItem.toString(), pattern)) {
                    return currentItem;
                }
            }
        }
        return null;
    }
}
