/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.bson.Document;

/**
 *
 * @author Ebad Ali
 */
public class Job {

    public JobStatus selectedStatus = JobStatus.NONE;

    public String jobWorkOrder = ("");

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

    public String jobMaterial = ("");
    ///////////////////////////////////////////////////////////////////////////
    public Project project = new Project();

    public Job() {
        selectedStatus = JobStatus.NONE;
    }

    public Job(JobStatus selectedStatus, String jobWorkOrder, String jobClient, String jobAddress, String jobPostalCode,
            String jobContact, String jobSite, String jobSuperSite, String jobSalesperson, String jobWorkOrderComments,
            LocalDate jobTemplateDate, LocalDate jobInstallDate, boolean jobInvoiced, boolean jobRemoval,
            boolean jobDisposal, Project project) {

        this.selectedStatus = selectedStatus;
        this.jobWorkOrder = (jobWorkOrder);
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
        this.project = (project);

        this.jobMaterial = this.project.getJobMaterial();
    }

    public Job(Document obj) {

        selectedStatus = JobStatus.fromString(obj.getString("selectedStatus"));

        jobWorkOrder = (obj.getString("jobWorkOrder"));
        jobClient = obj.getString("jobClient");
        jobAddress = obj.getString("jobAddress");
        jobPostalCode = obj.getString("jobPostalCode");
        jobContact = obj.getString("jobContact");
        jobSite = obj.getString("jobSite");
        jobSuperSite = obj.getString("jobSuperSite");
        jobSalesperson = obj.getString("jobSalesperson");
        jobWorkOrderComments = obj.getString("jobWorkOrderComments");

        jobTemplateDate = getFixedDate(obj.getString("jobTemplateDate"));//.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
        jobInstallDate = getFixedDate(obj.getString("jobInstallDate"));//.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        jobInvoiced = obj.getBoolean("jobInvoiced", false);
        jobRemoval = obj.getBoolean("jobRemoval", false);
        jobDisposal = obj.getBoolean("jobDisposal", false);

        project = new Project(obj.get("project", Document.class));
        jobWorkOrderComments = project.jobMaterial;
    }

    public LocalDate getFixedDate(String str) {
        LocalDate parsedDate = LocalDate.MAX;
        if (str != "" && str != null && !str.isEmpty() ) {
//            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
//            String text = date.format(formatter);
            parsedDate = LocalDate.parse(str, formatter);
        }
        return parsedDate;
    }

    public Document toBasicDBObject() {
        String s1= "", s2="";
        try{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        s1 = jobTemplateDate.format(formatter);
        s2 = jobInstallDate.format(formatter);
        }catch(Exception ex)
        {
        }
        return new Document()
                .append("selectedStatus", selectedStatus.name())
                .append("jobWorkOrder", jobWorkOrder)
                .append("jobClient", jobClient)
                .append("jobAddress", jobAddress)
                .append("jobPostalCode", jobPostalCode)
                .append("jobContact", jobContact)
                .append("jobSuperSite", jobSuperSite)
                .append("jobSalesperson", jobSalesperson)
                .append("jobWorkOrderComments", jobWorkOrderComments)
                .append("jobTemplateDate", s1)
                .append("jobInstallDate", s2)
                .append("jobInvoiced", jobInvoiced)
                .append("jobRemoval", jobRemoval)
                .append("jobDisposal", jobDisposal)
                .append("jobMaterial", jobMaterial)
                .append("project", project.toDocument());
    }

    @Override
    public String toString() {
        return jobWorkOrder; //To change body of generated methods, choose Tools | Templates.
    }

    public String getJobWorkOrder() {
        return jobWorkOrder;
    }

    public String getProject() {
        return project.getJobMaterial();
    }

    public String getJobMaterial() {
        return jobMaterial;
    }

}
