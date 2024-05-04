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
 * @author ALDRIN B. C.
 */
@Setter
@Getter
@ToString
public class Menu {

    private Long id;
    private String recipe;
    private String ingredients;
    private Float price;
    private byte[] photo;
    private Category category;
    private Boolean deleted;

    public Menu() {

    }

}
