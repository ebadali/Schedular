/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ebad Ali
 */
// Contains all the datastructures for list and db.
public class Container {

    static ObservableList<Inventory> inventoryList
            = FXCollections.observableArrayList(
                    new Inventory("A", "Z", "a@example.com", 211, ""),
                    new Inventory("B", "X", "b@example.com", 244, ""),
                    new Inventory("C", "W", "c@example.com", 255, ""),
                    new Inventory("D", "Y", "d@example.com", 266, ""),
                    new Inventory("E", "V", "e@example.com", 277, "")
            );

    static ObservableList<Job> jobList
            = FXCollections.observableArrayList(
                    new Job(JobStatus.COMPLETED, "sadasd2", "sadsad", "33dad", "asdsada3423",
                            "asd54as5d", "sadasd", "sasadsa", "asddasdll", "sadasd", LocalDate.MAX, LocalDate.MIN, true, true, true, new Project()),
                     new Job(JobStatus.COMPLETED, "sadasd3", "sadsad", "33dad", "asdsada3423",
                            "asd54as5d", "sadasd", "sasadsa4", "asddasdll", "sadasd", LocalDate.MAX, LocalDate.MIN, true, true, true, new Project()),
                      new Job(JobStatus.COMPLETED, "sadasd5", "sadsad", "33dad", "asdsada3423",
                            "asd54as5d", "sadasd", "sasadsa", "asddasdll", "sadasd", LocalDate.MAX, LocalDate.MIN, true, true, true, new Project()),
                       new Job(JobStatus.COMPLETED, "sadasd6", "sadsad", "33dad", "asdsada3423",
                            "asd54as5d", "sadasd", "sasadsa", "asddasdll", "sadasd", LocalDate.MAX, LocalDate.MIN, true, true, true, new Project()),

                                new Job(JobStatus.COMPLETED, "sadasd7", "sadsad", "33dad", "asdsada3423",
                            "asd54as5d", "sadasd", "sasadsa", "asddasdll", "sadasd", LocalDate.MAX, LocalDate.MIN, true, true, true, new Project()),
                     new Job(JobStatus.COMPLETED, "sadasd8", "sadsad", "33dad", "asdsada3423",
                            "asd54as5d", "sadasd", "sasadsa4", "asddasdll", "sadasd", LocalDate.MAX, LocalDate.MIN, true, true, true, new Project()),
                      new Job(JobStatus.COMPLETED, "sadasd9", "sadsad", "33dad", "asdsada3423",
                            "asd54as5d", "sadasd", "sasadsa", "asddasdll", "sadasd", LocalDate.MAX, LocalDate.MIN, true, true, true, new Project()),
                       new Job(JobStatus.COMPLETED, "sadasd10", "sadsad", "33dad", "asdsada3423",
                            "asd54as5d", "sadasd", "sasadsa", "asddasdll", "sadasd", LocalDate.MAX, LocalDate.MIN, true, true, true, new Project())

                     

            );
//    static List<Inventory> inventoryList = new ArrayList();

    //----Job Function----
    public static void AddJob(Job job) {
        jobList.add(job);
//        DBHandle.GetConn().Test("Yolo");
    }

    public static void SetJob(int index, Job job) {
        jobList.set(index, job);
//        DBHandle.GetConn().Test("Yolo");
    }

    public static void RemoveJob(int index) {
        jobList.remove(index);
//        DBHandle.GetConn().Test("Yolo");
    }

    public static Job GetJob(int index) {
        Job j = null;
        if (index >= 0 && index < jobList.size() )
            j = jobList.get(index);
        return j;
//        DBHandle.GetConn().Test("Yolo");
    }

    //--------------------    
    //----Inventory Function----
    public static void AddInventory(Inventory job) {
        inventoryList.add(job);
//        DBHandle.GetConn().Test("Yolo");
    }

    public static void SetInventory(int index, Inventory job) {
        inventoryList.set(index, job);
//        DBHandle.GetConn().Test("Yolo");
    }

    public static void RemoveInventory(int index) {
        inventoryList.remove(index);
//        DBHandle.GetConn().Test("Yolo");
    }

    public static Inventory GetInventory(int index) {
         Inventory j = null;
        if (index >= 0 && index < inventoryList.size() )
            j = inventoryList.get(index);
        return j;
       
//        DBHandle.GetConn().Test("Yolo");
    }

    //--------------------       
    public static ObservableList<Job> LoadJobs() {
        return jobList;
    }

    public static ObservableList<Inventory> LoadInventories() {
        return inventoryList;
    }

}
