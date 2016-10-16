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
public enum ProjectDeliveryStatus {
    jobInShop(false),
    jobInAtHome(false),
    jobTemplateOnly(false);

    private final boolean name;

    private ProjectDeliveryStatus(boolean s) {
        name = s;
    }
 
}
