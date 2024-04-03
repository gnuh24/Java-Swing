/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface DAOInterface<T> {

    List<T> getAll(Integer maKhoHang);

    T getById(Integer id);


    //

    boolean create(Integer maKhoHang, T t);

    boolean update(T t);

    boolean delete(T t);

}
