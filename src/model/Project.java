/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mongodb.BasicDBObject;
import javafx.beans.property.SimpleStringProperty;
import org.bson.Document;

/**
 *
 * @author Ebad Ali
 */
public class Project {

    public String jobProject = "";
    public String jobThickness = "";
    public String jobMaterial = "Z";
    public String jobSupplier = "";

    public String jobTagNum = "";

    public String jobEdgeProfile = "";
    public String jobSink = "";
    public String jobSinkQty = "";
    public String jobFaucet = "";
    public String jobFaucetQty = "";
    public String jobStove = "";
    public Double jobSqft = 0.0;
    public String jobProjectComments = "";

    public boolean jobInShop = false;
    public boolean jobInAtHome = false;
    public boolean jobTemplateOnly = false;

    public Project() {

    }

    public Project(String jobProject, String jobThickness, String jobMaterial, String jobSupplier, String jobTagNum,
            String jobEdgeProfile, String jobSink, String jobSinkQty, String jobFaucet, String jobFaucetQty,
            String jobStove, Double jobSqft, String jobProjectComments, boolean jobInShop, boolean jobInAtHome,
            boolean jobTemplateOnly) {
        this.jobProject = jobProject;
        this.jobThickness = jobThickness;
        this.jobMaterial = (jobMaterial);
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

    Project(Document obj) {
        
        this.jobProject = obj.getString("jobProject");
        this.jobThickness = obj.getString("jobThickness");
        this.jobMaterial = (obj.getString("jobMaterial"));
        this.jobSupplier = obj.getString("jobSupplier");
        this.jobTagNum = obj.getString("jobTagNum");
        this.jobEdgeProfile = obj.getString("jobEdgeProfile");
        this.jobSink = obj.getString("jobSink");
        this.jobSinkQty = obj.getString("jobSinkQty");
        this.jobFaucet = obj.getString("jobFaucet");
        this.jobFaucetQty = obj.getString("jobFaucetQty");
        this.jobStove = obj.getString("jobStove");
        this.jobSqft = obj.getDouble("jobSqft");
        this.jobProjectComments = obj.getString("jobProjectComments");
        this.jobInShop = obj.getBoolean("jobInShop", false);
        this.jobInAtHome = obj.getBoolean("jobInAtHome", false);
        this.jobTemplateOnly = obj.getBoolean("jobTemplateOnly", false);
    }
        
    

    public String getJobMaterial() {
        return jobMaterial;
    }

    @Override
    public String toString() {
        return this.jobMaterial; //To change body of generated methods, choose Tools | Templates.
    }

    Document toDocument() {
        
        return  new Document()                    
                    .append("jobProject", jobProject)
                    .append("jobThickness", jobThickness)
                    .append("jobMaterial", jobMaterial)
                    .append("jobSupplier",jobSupplier)
                    .append("jobEdgeProfile",jobEdgeProfile)
                 

                    .append("jobTagNum", jobTagNum)
                    .append("jobSink", jobSink)
                    .append("jobSinkQty", jobSinkQty)
                 
                    .append("jobFaucet", jobFaucet)
                    .append("jobFaucetQty", jobFaucetQty)
                    .append("jobStove",jobStove)
                    .append("jobSqft", jobSqft)
                    .append("jobProjectComments", jobProjectComments)
                    .append("jobInShop", jobInShop)
                    .append("jobInAtHome", jobInAtHome)
                    .append("jobTemplateOnly",jobTemplateOnly);
         
    }
    
    
    
    

}
