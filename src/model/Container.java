/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DBLayer.DBHandle;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ebad Ali
 */
// Contains all the datastructures for list and db.
public class Container {

    static ObservableList<InventoryTransaction> inventoryTransactionList
            = FXCollections.observableArrayList(
                    new InventoryTransaction("A", "Z", "a@example.com", 211, ""),
                    new InventoryTransaction("B", "X", "b@example.com", 244, ""),
                    new InventoryTransaction("C", "W", "c@example.com", 255, ""),
                    new InventoryTransaction("D", "Y", "d@example.com", 266, ""),
                    new InventoryTransaction("E", "V", "e@example.com", 277, "")
            );

    static ObservableList<Job> jobList
            = FXCollections.observableArrayList();
//                    new Job(JobStatus.COMPLETED, "sadasd10", "sadsad", "33dad", "asdsada3423",
//                            "asd54as5d", "sadasd", "sasadsa", "asddasdll", "sadasd", LocalDate.MAX, LocalDate.MIN, true, true, true, new Project())
//            );

//    static ObservableMap<String, Inventory> inventory
//            = FXCollections.observableHashMap();
    static ObservableList<Inventory> inventory = FXCollections.observableArrayList(
            new Inventory("A", 211),
            new Inventory("Z", 500)
    );

    //----Job Function----
    public static void AddJob(Job job) {
        System.out.println("Fine Till here");
        jobList.add(job);
        if (job.project != null) {
            AddInventory(job.project.jobMaterial, job.project.jobSqft);
        }

//        DBHandle.GetConn().Test("Yolo");
        DBHandle.GetConn().AddJob(job);
    }

    public static void SetJob(int index, Job job) {
        Job old = jobList.get(index);
        jobList.set(index, job);
        if (old != null && old.project != null && job.project != null) {
            SetInventory(job.project.jobMaterial,
                    job.project.jobSqft,
                    old.project.jobSqft);
        }

        DBHandle.GetConn().SetJob(job, old);

//        DBHandle.GetConn().Test("Yolo");
    }

    public static void RemoveJob(int index) {
        Job temp = jobList.remove(index);

        if (temp != null && temp.project != null) {
            RemoveInventory(temp.project.jobMaterial, temp.project.jobSqft);
            DBHandle.GetConn().RemoveJob(temp);
        }

//        DBHandle.GetConn().Test("Yolo");
    }

    public static Job GetJob(int index) {
        Job j = null;
        if (index >= 0 && index < jobList.size()) {
            j = jobList.get(index);
        }
        return j;
//        DBHandle.GetConn().Test("Yolo");
    }

    //--------------------    
    //----Inventory  Function----
    public static void AddInventory(String materia, Double sqft) {
        int found = -1;
        Inventory invent = null;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).inventoryMaterial.equalsIgnoreCase(materia)) {
                invent = inventory.get(i);
                inventory.set(i, new Inventory(materia,
                        (inventory.get(i).getInventorySqft() + sqft)));
                found = i;
                break;
            }
        }

        if (found == -1) {
            Inventory obj = new Inventory(materia, sqft);
            inventory.add(obj);
            DBHandle.GetConn().AddInventory(obj);
        } else {

            DBHandle.GetConn().UpdateInventory(inventory.get(found), invent);

        }

    }

    public static void RemoveInventory(String materia, Double sqft) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).inventoryMaterial.equalsIgnoreCase(materia)) {
                Inventory temp = inventory.get(i);
                inventory.set(i, new Inventory(materia,
                        (inventory.get(i).getInventorySqft() - sqft)));

                DBHandle.GetConn().UpdateInventory(inventory.get(i), temp);

                break;
            }
        }
//        if (job != null && inventory.containsKey(job.inventoryMaterial)) {
//            inventory.get(job.inventoryMaterial).RemoveInventorySqft(job.inventorySqft);
//        }

//        DBHandle.GetConn().Test("Yolo");
    }

    public static void SetInventory(String materia, Double sqft, Double old) {
        boolean found = false;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).inventoryMaterial.equalsIgnoreCase(materia)) {
                Inventory temp = inventory.get(i);
                inventory.set(i, new Inventory(materia,
                        ((inventory.get(i).getInventorySqft() - old) + sqft)));

                DBHandle.GetConn().UpdateInventory(inventory.get(i), temp);

                found = true;
                break;
            }
        }

        if (!found) {
            inventory.add(new Inventory(materia, sqft));
        }

    }

    public static InventoryTransaction GetInventory(int index) {
        InventoryTransaction j = null;
        if (index >= 0 && index < inventoryTransactionList.size()) {
            j = inventoryTransactionList.get(index);
        }
        return j;

//        DBHandle.GetConn().Test("Yolo");
    }

    //--------------------   
    //----InventoryTransaction Function----
    public static void AddInventoryTransaction(final InventoryTransaction job) {

        if (job != null) {
            AddInventory(job.getInventoryMaterial(), job.getInventorySqft());
            inventoryTransactionList.add(job);

        }

//        inventory.add();
//        DBHandle.GetConn().Test("Yolo");
//        System.out.println(inventory.get(job.inventoryMaterial));
    }

//    public static void SetInventoryTransaction(int index, InventoryTransaction job) {
//        inventoryTransactionList.set(index, job);
////        DBHandle.GetConn().Test("Yolo");
//    }
    public static InventoryTransaction GetInventoryTransaction(int index) {
        InventoryTransaction j = null;
        if (index >= 0 && index < inventoryTransactionList.size()) {
            j = inventoryTransactionList.get(index);
        }
        return j;

//        DBHandle.GetConn().Test("Yolo");
    }

    //--------------------        
    public static ObservableList<Job> LoadJobs() {
        List<Job> job = DBHandle.GetConn().GetJobs();
        jobList = FXCollections.observableArrayList(job);
        return jobList;
    }

    public static ObservableList<Job> LoadJobsOfDate(LocalDate someDate) {

        List<Job> list = new ArrayList();
        for (Job job : jobList) {
            if (job.jobTemplateDate.isEqual(someDate)) {
                list.add(job);
            }
        }
        
        
        return FXCollections.observableArrayList(list);
    }

    public static ObservableList<InventoryTransaction> LoadInventoriesTransaction() {

        return inventoryTransactionList;
    }

    public static ObservableList<Inventory> LoadInventories() {
//        inventory.clear();
//        for (InventoryTransaction invnt : inventoryTransactionList) {
//            inventory.add(new Inventory(invnt.getInventoryMaterial(), invnt.getInventorySqft()));
//        }

        return inventory;
    }

    public static void Forward() {

        from = from == null ? GetFirst() : from.plusDays(20);
    }

    public static void Backward() {
        from = from == null ? GetFirst() : from.minusDays(20);
    }

    public interface Predicate<Job> {

        void apply(LocalDate type);

        void clearMap();

        void setRange(LocalDate f, LocalDate t);

        List<LocalDate> getMap();
    }
//  Returns Local Date in Sorted Order.
    static Predicate<LocalDate> predicate = new Predicate<LocalDate>() {

        public List<LocalDate> list = new ArrayList<>();
        LocalDate from, to;

        @Override
        public void apply(LocalDate lDate) {

            if ((lDate.isEqual(from) || lDate.isAfter(from))
                    && (lDate.isEqual(to) || lDate.isBefore(to))) {
                list.add(lDate);
            }
        }

        @Override
        public List<LocalDate> getMap() {
            Collections.sort(list, new DateStruct());
            return list;
        }

        @Override
        public void clearMap() {
            list.clear();
        }

        @Override
        public void setRange(LocalDate f, LocalDate t) {

            from = f;
            to = t;
        }
    };
    public static LocalDate from = null;

    public static List<LocalDate> LoadTemplateDate() {

        predicate.clearMap();

        from = from == null ? GetFirst() : from;

        LocalDate to = from.plusDays(20);
        predicate.setRange(from, to);
        for (Job job : jobList) {
            if (job.jobTemplateDate != null) {
                predicate.apply(job.jobTemplateDate);
            }
        }
//        Collections.sort(jobList, new DateStruct());
//        
        return predicate.getMap();

    }

    static LocalDate GetFirst() {

        LocalDate from = LocalDate.now();
        boolean found = false;
        for (Job jobList1 : jobList) {
            if (jobList1.jobTemplateDate != null) {
                from = jobList1.jobTemplateDate;
                found = true;
                break;
            }
        }

        return from;
    }

}
