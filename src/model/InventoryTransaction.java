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
public class InventoryTransaction {

    public String  inventoryBatch;
    public String  inventoryMaterial;
    public String  inventoryThickness;

    public Double inventorySqft;

    public String  inventoryPicture;

    public InventoryTransaction(String inventoryBatch, String inventoryMaterial, String inventoryThickness, double inventorySqft,
            String inventoryPicture ) {
        this.inventoryBatch =  (inventoryBatch);
        this.inventoryMaterial =  (inventoryMaterial);
        this.inventoryThickness = (inventoryThickness);
        this.inventorySqft = (inventorySqft);
        this.inventoryPicture = ("");
    }

    public InventoryTransaction(Document document) {
        
        this.inventoryBatch =  document.getString("inventoryBatch");
        this.inventoryMaterial =  document.getString("inventoryMaterial");
        this.inventoryThickness = document.getString("inventoryThickness");
        try{
        this.inventorySqft = document.getDouble("inventorySqft");
        }catch(Exception ex)
        {
            ex.printStackTrace();
            this.inventorySqft = 0.0;
        }
        this.inventoryPicture = ("");
    }

    public String getInventoryBatch() {
        return inventoryBatch;
    }
//
//    public void setInventoryBatch(String inventoryBatch) {
//        this.inventoryBatch = inventoryBatch;
//    }

    public String getInventoryMaterial() {
        return inventoryMaterial;
    }

//    public void setInventoryMaterial(String inventoryMaterial) {
//        this.inventoryMaterial = inventoryMaterial;
//    }

    public String getInventoryThickness() {
        return inventoryThickness;
    }

//    public void setInventoryThickness(String inventoryThickness) {
//        this.inventoryThickness = inventoryThickness;
//    }

    public String getInventoryPicture() {
        return inventoryPicture;
    }


    public Double getInventorySqft() {
        return inventorySqft;
    }

    public Document toDocument() {
        return new Document()
                .append("inventoryMaterial", this.inventoryMaterial)
                .append("inventoryBatch", this.inventoryBatch)
                .append("inventorySqft", this.inventorySqft)
                .append("inventoryThickness", this.inventoryThickness)
                .append("inventoryPicture", this.inventoryPicture);
    }
   
    
    
    
}
