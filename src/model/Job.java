/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mongodb.BasicDBObject;
import java.time.LocalDate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ebad Ali
 */
public class Job {

    public JobStatus selectedStatus;

    public SimpleStringProperty jobWorkOrder;

    public String jobClient;

    public String jobAddress;

    public String jobPostalCode;

    public String jobContact;
    public String jobSite;
    public String jobSuperSite;
    public String jobSalesperson;
    public String jobWorkOrderComments;
    public LocalDate jobTemplateDate;
    public LocalDate jobInstallDate;
    public boolean jobInvoiced;
    public boolean jobRemoval;
    public boolean jobDisposal;

    public SimpleStringProperty jobMaterial = new SimpleStringProperty("");
    ///////////////////////////////////////////////////////////////////////////
    public SimpleObjectProperty<Project> project;

    public Job() {
        selectedStatus = JobStatus.NONE;
        project = new SimpleObjectProperty<>();
    }

    public Job(JobStatus selectedStatus, String jobWorkOrder, String jobClient, String jobAddress, String jobPostalCode, 
            String jobContact, String jobSite, String jobSuperSite, String jobSalesperson, String jobWorkOrderComments,
            LocalDate jobTemplateDate, LocalDate jobInstallDate, boolean jobInvoiced, boolean jobRemoval,
            boolean jobDisposal, Project project) {
        this.selectedStatus = selectedStatus;
        this.jobWorkOrder = new SimpleStringProperty(jobWorkOrder);
        this.jobClient = jobClient;
        this.jobAddress = jobAddress;
        this.jobPostalCode = jobPostalCode;
        this.jobContact = jobContact;
        this.jobSite = jobSite;
        this.jobSuperSite = jobSuperSite;
        this.jobSalesperson = jobSalesperson;
        this.jobWorkOrderComments = jobWorkOrderComments;
        this.jobTemplateDate = jobTemplateDate;
        this.jobInstallDate = jobInstallDate;
        this.jobInvoiced = jobInvoiced;
        this.jobRemoval = jobRemoval;
        this.jobDisposal = jobDisposal;
        this.project = new SimpleObjectProperty<>(project);
        
        this.jobMaterial = this.project.get().getJobMaterial();
    }

    public BasicDBObject toBasicDBObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return jobWorkOrder.get(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getJobWorkOrder() {
        return jobWorkOrder.get();
    }

    public String getProject() {
        return project.getName();
    }

    public String getJobMaterial() {
        return jobMaterial.get();
    }
    
    
   
}
