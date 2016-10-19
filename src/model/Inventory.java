/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.bson.Document;

/**
 *
 * @author Ebad Ali
 */
public class Inventory {

    public String inventoryMaterial;
    public double inventorySqft;

    public Inventory(String inventoryMaterial, double inventorySqft) {
        this.inventoryMaterial = inventoryMaterial;
        this.inventorySqft = inventorySqft;
    }

    public Inventory(Document document) {
        this.inventoryMaterial = document.getString("inventoryMaterial");

        try {
            this.inventorySqft = document.getDouble("inventorySqft");
        }catch(Exception ex)
        {
            this.inventorySqft = 0.0;
        }

    }

    public String getInventoryMaterial() {
        return inventoryMaterial;
    }

    public Double getInventorySqft() {
        return inventorySqft;
    }

    public void AddInventorySqft(Double d) {
        inventorySqft = (inventorySqft + d);
    }

    public void RemoveInventorySqft(Double d) {
        inventorySqft = (inventorySqft - d);
    }

//    @Override
//    public String toString() {
//        return "Name " + this.getInventoryMaterial() + " , " + this.getInventorySqft(); //To change body of generated methods, choose Tools | Templates.
//    }

    public Document toDocument() {
        return new Document()
                .append("inventoryMaterial", inventoryMaterial)
                .append("inventorySqft", inventorySqft);

    }

}
