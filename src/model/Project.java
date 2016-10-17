/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ebad Ali
 */
public class Project {

    public String jobProject = "";
    public String jobThickness = "";
    public SimpleStringProperty jobMaterial = new SimpleStringProperty("");
    public String jobSupplier;

    public String jobTagNum = "";

    public String jobEdgeProfile = "";
    public String jobSink = "";
    public String jobSinkQty = "";
    public String jobFaucet = "";
    public String jobFaucetQty = "";
    public String jobStove = "";
    public String jobSqft = "";
    public String jobProjectComments = "";

    public boolean jobInShop = false;
    public boolean jobInAtHome;
    public boolean jobTemplateOnly;

    public Project() {

    }

    public Project(String jobProject, String jobThickness, String jobMaterial, String jobSupplier, String jobTagNum,
            String jobEdgeProfile, String jobSink, String jobSinkQty, String jobFaucet, String jobFaucetQty,
            String jobStove, String jobSqft, String jobProjectComments, boolean jobInShop, boolean jobInAtHome,
            boolean jobTemplateOnly) {
        this.jobProject = jobProject;
        this.jobThickness = jobThickness;
        this.jobMaterial = new SimpleStringProperty(jobMaterial);
        this.jobSupplier = jobSupplier;
        this.jobTagNum = jobTagNum;
        this.jobEdgeProfile = jobEdgeProfile;
        this.jobSink = jobSink;
        this.jobSinkQty = jobSinkQty;
        this.jobFaucet = jobFaucet;
        this.jobFaucetQty = jobFaucetQty;
        this.jobStove = jobStove;
        this.jobSqft = jobSqft;
        this.jobProjectComments = jobProjectComments;
        this.jobInShop = jobInShop;
        this.jobInAtHome = jobInAtHome;
        this.jobTemplateOnly = jobTemplateOnly;
    }

    public SimpleStringProperty getJobMaterial() {
        return jobMaterial;
    }

    @Override
    public String toString() {
        return this.jobMaterial.get(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    

}
