/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.cafsysv2.dao;


import com.aldrin.cafsysv2.model.Role;
import java.util.List;

/**
 *
 * @author ALDRIN B. C.
 */
public interface RoleDAO {
     public Long getMaxId();
     
//    add Role
    public void addRole(Role role);
    
//    update Role
    public void updateRole(Role role);
    
//    delete Role
    public void deleteRole(Role role);
    
//    list of Role
    public List<Role> selectRole();
    
    public void comboBoxRole();
}
