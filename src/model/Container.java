/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ebad Ali
 */
// Contains all the datastructures for list and db.
public class Container {
    
    public static ObservableList<Inventory> inventoryList =
        FXCollections.observableArrayList(
            new Inventory("A", "Z", "a@example.com",211,""),
            new Inventory("B", "X", "b@example.com",244,""),
            new Inventory("C", "W", "c@example.com",255,""),
            new Inventory("D", "Y", "d@example.com",266,""),
            new Inventory("E", "V", "e@example.com",277,"")
        ); 
    static List<Job> jobList = new ArrayList();
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
        return jobList.get(index);
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
        return inventoryList.get(index);
//        DBHandle.GetConn().Test("Yolo");
    }
    
    //--------------------       

    public static Object[] LoadJobs() {
 String [] arr = new String[jobList.size()];
        
        for (int i = 0; i < jobList.size(); i++) {
            arr[i] = jobList.get(i).jobWorkOrder;
        }
        
        return arr;    }

    public static Object[] LoadInventories() {
        String [] arr = new String[inventoryList.size()];
        
        for (int i = 0; i < inventoryList.size(); i++) {
//            arr[i] = inventoryList.get(i).inventoryBatch;
        }
        
        return arr;
    }
    
    
}
