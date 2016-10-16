/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ebad Ali
 */
public class Inventory {

    public SimpleStringProperty  inventoryBatch;
    public SimpleStringProperty  inventoryMaterial;
    public SimpleStringProperty  inventoryThickness;

    public SimpleDoubleProperty inventorySqft;

    public SimpleStringProperty  inventoryPicture;

    public Inventory(String inventoryBatch, String inventoryMaterial, String inventoryThickness, double inventorySqft,
            String inventoryPicture ) {
        this.inventoryBatch =  new SimpleStringProperty(inventoryBatch);
        this.inventoryMaterial =  new SimpleStringProperty(inventoryMaterial);
        this.inventoryThickness = new SimpleStringProperty(inventoryThickness);
        this.inventorySqft = new SimpleDoubleProperty(inventorySqft);
        this.inventoryPicture = new SimpleStringProperty("");
    }

    public String getInventoryBatch() {
        return inventoryBatch.get();
    }
//
//    public void setInventoryBatch(SimpleStringProperty inventoryBatch) {
//        this.inventoryBatch = inventoryBatch;
//    }

    public String getInventoryMaterial() {
        return inventoryMaterial.get();
    }

//    public void setInventoryMaterial(SimpleStringProperty inventoryMaterial) {
//        this.inventoryMaterial = inventoryMaterial;
//    }

    public String getInventoryThickness() {
        return inventoryThickness.get();
    }

//    public void setInventoryThickness(SimpleStringProperty inventoryThickness) {
//        this.inventoryThickness = inventoryThickness;
//    }

    public String getInventoryPicture() {
        return inventoryPicture.get();
    }

//    public void setInventoryPicture(SimpleStringProperty inventoryPicture) {
//        this.inventoryPicture = inventoryPicture;
//    }

    public Double getInventorySqft() {
        return inventorySqft.get();
    }
   
    
    
    
}
