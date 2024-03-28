/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.example.DAO;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface DAOInterface<T> {
    
    public int them(T t);
    
    public int capNhat(T t);
    
    public int xoa(T t);
    
    public ArrayList<T> selectAll();
    
    public T selectById(T t);
    
    
    public ArrayList<T> selectByDieuKien(String dk);
    
    
    
    
}
