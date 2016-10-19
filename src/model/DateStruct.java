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
    public int compare(LocalDate o2, LocalDate o1) {
        int f = 0;
        if(o1.isEqual(o2))
            f = 0;
        else if (o1.isBefore(o2))
            f = 1;
        else
            f = -1;
//        int f =  (o1.isBefore(o2) || o1.isEqual(o2) )? 1 : -1 ;
        // write comparison logic here like below , it's just a sample
//        return o1.getID().compareTo(o2.getID());
        return f;
    }
}