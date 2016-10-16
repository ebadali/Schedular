/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Ebad Ali
 */
public class Util {
    public static JobStatus GetJobStatus(Object str)
    {           System.out.println(str);

        return JobStatus.fromString(str);

    }
}
