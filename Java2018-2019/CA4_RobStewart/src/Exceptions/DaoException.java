/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Rob
 */
import java.sql.SQLException;

public class DaoException extends SQLException {
    
    public DaoException(){};

    public DaoException(String aMessage){
        super(aMessage);
    }
}