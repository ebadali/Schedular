/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import model.Container;
import model.Inventory;
import model.Job;
import model.JobStatus;
import model.Project;
import model.Util;
import model.ValidationLayer;

public class SchedulerController implements Initializable {

    @FXML
    ListView jobTable;
    @FXML
    Button jobAddBtn;
    @FXML
    Button jobMinusBtn;
    @FXML
    Button jobDuplicateBtn;
    @FXML
    Button jobSaveBtn;
    @FXML
    ComboBox jobStatus;
    @FXML
    TextField jobWorkOrder;
    @FXML
    TextField jobClient;
    @FXML
    TextField jobAddress;
    @FXML
    TextField jobPostalCode;
    @FXML
    TextField jobContact;
    @FXML
    TextField jobSite;
    @FXML
    TextField jobSuperSite;
    @FXML
    TextField jobSalesperson;
    @FXML
    TextArea jobWorkOrderComments;
    @FXML
    DatePicker jobTemplateDate;
    @FXML
    DatePicker jobInstallDate;
    @FXML
    CheckBox jobInvoiced;
    @FXML
    CheckBox jobRemoval;
    @FXML
    CheckBox jobDisposal;
    ///////////////////////////////////////////////////////////////////////////
    @FXML
    TextField jobProject;
    @FXML
    TextField jobThickness;
    @FXML
    TextField jobMaterial;
    @FXML
    TextField jobSupplier;
    @FXML
    TextField jobTagNum;
    @FXML
    TextField jobEdgeProfile;
    @FXML
    TextField jobSink;
    @FXML
    TextField jobSinkQty;
    @FXML
    TextField jobFaucet;
    @FXML
    TextField jobFaucetQty;
    @FXML
    TextField jobStove;
    @FXML
    TextField jobSqft;
    @FXML
    TextArea jobProjectComments;
    @FXML
    RadioButton jobInShop;
    @FXML
    RadioButton jobInAtHome;
    @FXML
    RadioButton jobTemplateOnly;
    @FXML
    Label label;

    @FXML
    TextField inventoryMaterial;
    @FXML
    TextField inventoryThickness;
    @FXML
    TextField inventoryBatch;
    @FXML
    TextField inventorySqft;

    @FXML
    TabPane mainTabPane;
    
    @FXML
    TableView inventoryTable;
    
    @FXML
    TableColumn inventoryMaterialCol;
    
    @FXML
    TableColumn inventorySqftCol;

    //    -------Job Setup Callbacks--------
    @FXML
    private void handleJobAddAction(ActionEvent event) {
        label.setText("Hello World!");
        jobTable.getSelectionModel().select(-1);
        clearDisplayJob();

    }

    @FXML
    private void handleJobMinusAction(ActionEvent event) {
        label.setText("Hello World!");

        RemoveJob();

    }

    @FXML
    private void handleJobDublicateAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

        DuplicateJob();
    }

    @FXML
    private void handleJobSaveAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

        AddJob();

    }

    //    --------- Job Specific Functions -------------
    Job getDisplayJob() {
        int rowcount = jobStatus.getSelectionModel().getSelectedIndex();
        System.out.println(rowcount);
        JobStatus jb = Util.GetJobStatus(jobStatus.getSelectionModel().getSelectedItem());
        System.out.println(jb.name());

        Project project = getDisplayProject();

        Job job = new Job(jb, jobWorkOrder.getText(), jobClient.getText(), jobAddress.getText(),
                jobPostalCode.getText(), jobContact.getText(), jobSite.getText(), jobSuperSite.getText(), jobSalesperson.getText(),
                jobWorkOrderComments.getText(), jobTemplateDate.getValue(), jobInstallDate.getValue(), jobInvoiced.isSelected(), jobRemoval.isSelected(),
                jobDisposal.isSelected(), project);
        return job;
    }

    Project getDisplayProject() {

        Project project = new Project(jobProject.getText(), jobThickness.getText(), jobMaterial.getText(), jobSupplier.getText(), jobTagNum.getText(),
                jobEdgeProfile.getText(), jobSink.getText(),
                jobSinkQty.getText(), jobFaucet.getText(), jobFaucetQty.getText(), jobStove.getText(), jobSqft.getText(),
                jobProjectComments.getText(), jobInShop.isSelected(), jobInAtHome.isSelected(), jobTemplateOnly.isSelected());
        return project;
    }

    void clearDisplayJob() {

        jobProject.clear();
        jobThickness.clear();
        jobMaterial.clear();
        jobSupplier.clear();
        jobTagNum.clear();
        jobEdgeProfile.clear();
        jobSink.clear();
        jobSinkQty.clear();
        jobFaucet.clear();
        jobFaucetQty.clear();
        jobStove.clear();
        jobSqft.clear();
        jobProjectComments.clear();
        jobInShop.setSelected(false);
        jobInAtHome.setSelected(false);
        jobTemplateOnly.setSelected(false);

        jobWorkOrder.clear();
        jobClient.clear();
        jobAddress.clear();
        jobPostalCode.clear();
        jobContact.clear();
        jobSite.clear();
        jobSuperSite.clear();
        jobSalesperson.clear();
        jobWorkOrderComments.clear();
        jobTemplateDate.getEditor().clear();
        jobInstallDate.getEditor().clear();

        jobInvoiced.setSelected(false);
        jobRemoval.setSelected(false);
        jobDisposal.setSelected(false);

        jobStatus.setValue("");
    }

    void setDisplayJob(Job job) {
        jobProject.setText(job.project.jobProject);
        jobThickness.setText(job.project.jobThickness);
        jobMaterial.setText(job.project.jobMaterial);
        jobSupplier.setText(job.project.jobSupplier);
        jobTagNum.setText(job.project.jobTagNum);
        jobEdgeProfile.setText(job.project.jobEdgeProfile);
        jobSink.setText(job.project.jobSink);
        jobSinkQty.setText(job.project.jobSinkQty);
        jobFaucet.setText(job.project.jobFaucet);
        jobFaucetQty.setText(job.project.jobFaucetQty);
        jobStove.setText(job.project.jobStove);
        jobSqft.setText(job.project.jobSqft);
        jobProjectComments.setText(job.project.jobProjectComments);
        // To fix it.
        jobInShop.setSelected(job.project.jobInShop);
        jobInAtHome.setSelected(job.project.jobInAtHome);
        jobTemplateOnly.setSelected(job.project.jobTemplateOnly);

        jobWorkOrder.setText(job.jobWorkOrder);
        jobClient.setText(job.jobClient);
        jobAddress.setText(job.jobAddress);
        jobPostalCode.setText(job.jobPostalCode);
        jobContact.setText(job.jobContact);
        jobSite.setText(job.jobSite);
        jobSuperSite.setText(job.jobSuperSite);
        jobSalesperson.setText(job.jobSalesperson);
        jobWorkOrderComments.setText(job.jobWorkOrderComments);
        jobTemplateDate.setValue(job.jobTemplateDate);
        jobInstallDate.setValue(job.jobInstallDate);

        jobInvoiced.setSelected(job.jobInvoiced);
        jobRemoval.setSelected(job.jobRemoval);
        jobDisposal.setSelected(job.jobDisposal);

        jobStatus.getSelectionModel().select(job.selectedStatus.toString());
    }

    void RemoveJob() {
        int index = jobTable.getSelectionModel().getSelectedIndex();

        if (index > -1 && index < jobTable.getItems().size()) {
            Container.RemoveJob(index);
            jobTable.getItems().remove(index);
        }

    }

    void AddJob() {
        Job job = getDisplayJob();
        int index = jobTable.getSelectionModel().getSelectedIndex();

        if (index > -1 && index < jobTable.getItems().size() && jobTable.getSelectionModel().isSelected(index)) {
            // Replace Existing.
//            Container.jobList.set(index, job);
            Container.SetJob(index, job);
            jobTable.getItems().set(index, job.jobWorkOrder);
        } else if (ValidationLayer.IsValidJob(job)) {
            System.out.println("Validation Layer");
            Container.AddJob(job);
//            Container.jobList.add(job);
            jobTable.getItems().add(job.jobWorkOrder);
        }

    }

    void DuplicateJob() {

        int index = jobTable.getSelectionModel().getSelectedIndex();

        if (index > -1 && index < jobTable.getItems().size()) {

            setDisplayJob(Container.GetJob(index));

        }

    }

//    --------- Inventory Specific Functions -------------
    Inventory getDisplayInventory() {
        Double d = 0.0;
        try{ d = Double.parseDouble(inventorySqft.getText());}
        catch(Exception ex){}
        Inventory inv = new Inventory(inventoryBatch.getText(), inventoryMaterial.getText(),inventoryThickness.getText(),
               d,"");
//        return job;
        return inv;
    }

    void clearDisplayInventory() {

        inventoryBatch.clear();
        inventorySqft.clear();
        inventoryThickness.clear();
        inventoryMaterial.clear();

    }

    void setDisplayInventory(Inventory invt) {
        inventoryMaterial.setText(invt.getInventoryMaterial().toString());
        inventoryBatch.setText(invt.getInventoryBatch().toString());

        inventorySqft.setText(24+ "");
        inventoryThickness.setText(invt.getInventoryThickness().toString());

    }

    void RemoveInventory() {
        int index = inventoryTable.getSelectionModel().getSelectedIndex();

        if (index > -1 && index < inventoryTable.getItems().size()) {
            Container.RemoveInventory(index);
            inventoryTable.getItems().remove(index);
        }

    }

    void AddInventory() {
        Inventory inventory = getDisplayInventory();
        int index = inventoryTable.getSelectionModel().getSelectedIndex();

        if (index > -1 && index < inventoryTable.getItems().size() && inventoryTable.getSelectionModel().isSelected(index)) {
            // Replace Existing.
//            Container.jobList.set(index, job);
            Container.SetInventory(index, inventory);
//            inventoryTable.getItems().set(index, inventory.inventoryBatch);
        } else if (ValidationLayer.IsValidInventory(inventory)) {
            System.out.println("Validation Layer");
            Container.AddInventory(inventory);
//            Container.jobList.add(job);
//            inventoryTable.getItems().add(arr)
//            inventoryTable.getItems().add(inventory.inventoryBatch);
        }

    }

    void DuplicateInventory() {

        int index = inventoryTable.getSelectionModel().getSelectedIndex();

        if (index > -1 && index < inventoryTable.getItems().size()) {

            setDisplayInventory(Container.GetInventory(index));

        }

    }

    //    -------Inventory Callbacks--------
    @FXML
    private void handleInventoryAddAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        inventoryTable.getSelectionModel().select(-1);
//        clearDisplayInventory();
        AddInventory();
    }

    @FXML
    private void handleInventoryDeleteAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void handleInventoryCopyAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    //    -------Template Schedule Callbacks--------
    @FXML
    private void handleTemplateForwardAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void handleTemplateBackwardAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void handleTemplateValueChangeAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    //    -------Install Schedule Callbacks--------
    @FXML
    private void handleInstallForwardAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void handleInstallBackwardAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void handleInstallValueChangeAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void TabJobSelection(ActionEvent event) {
        System.out.println("Tabbed for Job ");

    }

    @FXML
    private void TabInventorySelection(ActionEvent event) {
        System.out.println("Tabbed for Inventory ");

    }

    String[] arr = {"(green) Template Completed", "(yellow) To Be Templated",
        "(purple) Project on Hold", "(red) Missing Material", "(blue) Ready for Install",
        "(orange) Waiting for Fabrication"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //create group for radio buttons
        ToggleGroup group = new ToggleGroup();
        jobInShop.setToggleGroup(group);
        jobInAtHome.setToggleGroup(group);
        jobTemplateOnly.setToggleGroup(group);

        jobStatus.getItems().addAll(arr);

        jobStatus.setCellFactory(
                new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                final ListCell<String> cell = new ListCell<String>() {
                    {
                        super.setPrefWidth(100);
                    }

                    @Override
                    public void updateItem(String item,
                            boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            if (item.contains("red")) {
                                setTextFill(Color.RED);
                            } else if (item.contains("green")) {
                                setTextFill(Color.GREEN);
                            } else if (item.contains("orange")) {
                                setTextFill(Color.ORANGE);
                            } else if (item.contains("yellow")) {
                                setTextFill(Color.YELLOW);
                            } else if (item.contains("blue")) {
                                setTextFill(Color.BLUE);
                            } else if (item.contains("purple")) {
                                setTextFill(Color.PURPLE);
                            } else {
                                setTextFill(Color.BLACK);
                            }
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });
        final Pattern validDoubleText = Pattern.compile("-?((\\d*)|(\\d+\\.\\d*))");
        TextFormatter<Double> textFormatter = new TextFormatter<>(new DoubleStringConverter(), 0.0, new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                String newText = t.getControlNewText();
                if (validDoubleText.matcher(newText).matches()) {
                    return t;
                } else {
                    return null;
                }
            }
        });
        this.jobThickness.setTextFormatter(textFormatter);

        // Inventory.
        inventorySqft.setTextFormatter(new TextFormatter<>(new DoubleStringConverter(), 0.0, new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                String newText = t.getControlNewText();
                if (validDoubleText.matcher(newText).matches()) {
                    return t;
                } else {
                    return null;
                }
            }
        }));
//        inventoryThickness.setTextFormatter( new TextFormatter<>(new DoubleStringConverter(), 0.0, new UnaryOperator<TextFormatter.Change>() {
//            @Override
//            public TextFormatter.Change apply(TextFormatter.Change t) {
//                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                String newText = t.getControlNewText() ;
//                if (validDoubleText.matcher(newText).matches()) {
//                    return t ;
//                } else return null ;
//            }
//        }));

        mainTabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                System.out.println("Tab Selection changed");
                System.out.println("  " + newValue.getId() + " , " + newValue.getText());
                PrepFor(newValue.getText());
            }

        }
        );
        
       
              
        LoadJob();
    }
    
    public void PrepFor(String text){
        if (text.contains("Job")){
            LoadJob();
        }else if(text.contains("Template")){
            LoadTemplate();
        }else if(text.contains("Install")){
            LoadInstall();
        }
        else if(text.contains("Inventory")){
            LoadInvntory();
        }
    }

    private void LoadJob() {
        jobTable.getItems().clear();
        jobTable.getItems().addAll(Container.LoadJobs());
    }

    private void LoadTemplate() {
    }

    private void LoadInstall() {
    }

    private void LoadInvntory() {
        inventorySqftCol.setCellValueFactory(
            new PropertyValueFactory<Inventory,Double>("inventorySqft")
        );
        inventoryMaterialCol.setCellValueFactory(
            new PropertyValueFactory<Inventory,String>("inventoryMaterial")
        );
        inventoryTable.setItems(Container.inventoryList);

    }
    
    

}
