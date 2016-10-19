/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.Comparator;

/**
 *
 * @author Umar
 */
public class DateStruct implements Comparator<LocalDate> {
    
    @Override
    public int compare(LocalDate o1, LocalDate o2) {
        
        int f =  (o1.isBefore(o2) || o1.isEqual(o2) )? 1 : -1 ;
        // write comparison logic here like below , it's just a sample
//        return o1.getID().compareTo(o2.getID());
        return f;
    }
}