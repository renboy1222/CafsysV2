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
public class Category {
    private Long id;
    private String category;
    private String description;
    private byte[] photo;
    
    public Category(){
        
    }
    
    public Category(String category){
        this.category =category;
    }
    
    public Category(Long id, String category){
        this.id =id;
        this.category =category;
    }
}
