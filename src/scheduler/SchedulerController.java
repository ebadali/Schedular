/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this install file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import model.Container;
import model.Inventory;
import model.InventoryTransaction;
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

    @FXML
    TableView inventoryHistTable;
    @FXML
    TableColumn inventoryMaterialHistCol;
    @FXML
    TableColumn inventoryThicknessHistCol;
    @FXML
    TableColumn inventorySqftHistCol;
    @FXML
    TableColumn inventoryBatchHistCol;
    @FXML
    TableColumn inventoryPicHist;

    // -------------- Template -------------
    @FXML
    TableView templateList11, templateList12, templateList13, templateList14;
    @FXML
    TableView templateList20, templateList21, templateList22, templateList23, templateList24;
    @FXML
    TableView templateList30, templateList31, templateList32, templateList33, templateList34;
    @FXML
    TableView templateList40, templateList41, templateList42, templateList43, templateList44;

    @FXML
    TableView templateTable;

    @FXML
    TableColumn templateWorkOrderCol;
    @FXML
    TableColumn templateMaterialCol;

    @FXML
    ChoiceBox template_datepicker;

    @FXML
    private void template_forward(ActionEvent event) {
        Container.Forward();
        System.out.println("I am called");
        System.out.println(Container.from);

        UpdateTemplateCalender();
    }

    @FXML
    private void template_backword(ActionEvent event) {

        Container.Backward();
        UpdateTemplateCalender();
    }
    // -------------- Install -------------
    @FXML
    TableView installList11, installList12, installList13, installList14;
    @FXML
    TableView installList20, installList21, installList22, installList23, installList24;
    @FXML
    TableView installList30, installList31, installList32, installList33, installList34;
    @FXML
    TableView installList40, installList41, installList42, installList43, installList44;

    @FXML
    TableView installTable;
    @FXML
    TableColumn installWorkOrderCol;
    @FXML
    TableColumn installMaterialCol;

    @FXML
    ChoiceBox install_datepicker;

//    template
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

        JobStatus jb = Util.GetJobStatus(jobStatus.getSelectionModel().getSelectedItem());
        System.out.println(jb.name());

        Project project = getDisplayProject();
        System.out.println("Fine project");
        Job job = new Job(jb, jobWorkOrder.getText(), jobClient.getText(), jobAddress.getText(),
                jobPostalCode.getText(), jobContact.getText(), jobSite.getText(), jobSuperSite.getText(), jobSalesperson.getText(),
                jobWorkOrderComments.getText(), jobTemplateDate.getValue(), jobInstallDate.getValue(), jobInvoiced.isSelected(), jobRemoval.isSelected(),
                jobDisposal.isSelected(), project);
        System.out.println("Fine job");

        return job;
    }

    Project getDisplayProject() {

        Project project = new Project(jobProject.getText(), jobThickness.getText(), jobMaterial.getText(), jobSupplier.getText(), jobTagNum.getText(),
                jobEdgeProfile.getText(), jobSink.getText(),
                jobSinkQty.getText(), jobFaucet.getText(), jobFaucetQty.getText(), jobStove.getText(), GetDouble(jobSqft),
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
        jobSqft.setText(job.project.jobSqft + "");
        jobProjectComments.setText(job.project.jobProjectComments);
        // To fix it.
        jobInShop.setSelected(job.project.jobInShop);
        jobInAtHome.setSelected(job.project.jobInAtHome);
        jobTemplateOnly.setSelected(job.project.jobTemplateOnly);

        jobWorkOrder.setText(job.getjobWorkOrder());
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
//            jobTable.getItems().remove(index);
        }

    }

    void AddJob() {
        Job job = getDisplayJob();
        int index = jobTable.getSelectionModel().getSelectedIndex();

        if (index > -1 && index < jobTable.getItems().size() && jobTable.getSelectionModel().isSelected(index)) {
            // Replace Existing.
//            Container.jobList.set(index, job);
            Container.SetJob(index, job);
//            jobTable.getItems().set(index, job.jobWorkOrder);
        } else if (ValidationLayer.IsValidJob(job)) {
//            System.out.println("Validation Layer");
            Container.AddJob(job);
//            Container.jobList.add(job);
//            jobTable.getItems().add(job.jobWorkOrder);
        }

    }

    void DuplicateJob() {

        int index = jobTable.getSelectionModel().getSelectedIndex();

        if (index > -1 && index < jobTable.getItems().size()) {
            Job j = Container.GetJob(index);
            if (j == null) {
                System.out.println("null");
            } else {
                setDisplayJob(j);
                System.out.println(j);
                System.out.println(j.jobTemplateDate);
            }

            //            
        }

    }

//    --------- Inventory Specific Functions -------------
    InventoryTransaction getDisplayInventory() {
        Double d = 0.0;
        try {
            d = Double.parseDouble(inventorySqft.getText());
        } catch (Exception ex) {
        }
        InventoryTransaction inv = new InventoryTransaction(inventoryBatch.getText(), inventoryMaterial.getText(), inventoryThickness.getText(),
                d, "");
//        return job;
        return inv;
    }

    void clearDisplayInventory() {

        inventoryBatch.clear();
        inventorySqft.clear();
        inventoryThickness.clear();
        inventoryMaterial.clear();

    }

    void setDisplayInventory(InventoryTransaction invt) {
        inventoryMaterial.setText(invt.getInventoryMaterial().toString());
        inventoryBatch.setText(invt.getInventoryBatch().toString());

        inventorySqft.setText(24 + "");
        inventoryThickness.setText(invt.getInventoryThickness().toString());

    }

    void RemoveInventory() {
//        int index = inventoryTable.getSelectionModel().getSelectedIndex();
//
//        if (index > -1 && index < inventoryTable.getItems().size()) {
//            Container.RemoveInventoryTransaction(index);
//        }

    }

    void AddInventory() {
        InventoryTransaction inventory = getDisplayInventory();

        if (ValidationLayer.IsValidInventory(inventory)) {
            System.out.println("Validation Layer");
            Container.AddInventoryTransaction(inventory);

        }
//        int index = inventoryHistTable.getSelectionModel().getSelectedIndex();
//
//        if (index > -1 && index < inventoryHistTable.getItems().size() && inventoryHistTable.getSelectionModel().isSelected(index)) {
//            // Replace Existing.
//            Container.SetInventory(index, inventory);
//        } else if (ValidationLayer.IsValidInventory(inventory)) {
//            System.out.println("Validation Layer");
//            Container.AddInventory(inventory);
//
//        }

    }

    void DuplicateInventory() {

        int index = inventoryTable.getSelectionModel().getSelectedIndex();

        if (index > -1 && index < inventoryTable.getItems().size()) {

            setDisplayInventory(Container.GetInventoryTransaction(index));

        }

    }

    //    -------Inventory Callbacks--------
    @FXML
    private void handleInventoryAddAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        inventoryHistTable.getSelectionModel().select(-1);
//        clearDisplayInventory();
        clearDisplayInventory();
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
        inventoryHistTable.getSelectionModel().select(-1);

        AddInventory();

    }

    //    -------Template Schedule Callbacks--------
    @FXML
    private void handleTemplateForwardAction(ActionEvent event) {
        System.out.println("You clicked me!");
        Container.Forward();
        System.out.println("I am called");
        System.out.println(Container.from);

        UpdateTemplateCalender();

    }

    @FXML
    private void handleTemplateBackwardAction(ActionEvent event) {
        System.out.println("You clicked me!");

        Container.Backward();
        System.out.println("I am called");
        System.out.println(Container.from);

        UpdateTemplateCalender();

    }

    private void handleTemplateValueChangeAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    //    -------Install Schedule Callbacks--------
    @FXML
    private void handleInstallForwardAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");

        System.out.println("You clicked me!");
        Container.Forward();
        System.out.println("I am called");
        System.out.println(Container.from);

        UpdateInstallCalender();

    }

    @FXML
    private void handleInstallBackwardAction(ActionEvent event) {
        Container.Backward();
        System.out.println("I am called");
        System.out.println(Container.from);

        UpdateInstallCalender();
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
        jobSqft.setTextFormatter(new TextFormatter<>(new DoubleStringConverter(), 0.0, new UnaryOperator<TextFormatter.Change>() {
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

    public void PrepFor(String text) {
        if (text.contains("Job")) {
            LoadJob();
        } else if (text.contains("Template")) {
            LoadTemplate();
        } else if (text.contains("Install")) {
            LoadInstall();
        } else if (text.contains("Inventory")) {
            LoadInvntory();
        }
    }

    private void LoadJob() {
//        jobTable.getItems().clear();
//        jobTable.getItems().addAll(Container.LoadJobs());
//        inventorySqftCol.setCellValueFactory(
//                new PropertyValueFactory<Inventory, Double>("inventorySqft")
//        );
        jobTable.setItems(Container.LoadJobs());

    }

    int templateDate = 0;

    private void LoadTemplate() {

        templateMaterialCol.setCellValueFactory(
                new PropertyValueFactory<String, String>("jobMaterial")
        );
        templateWorkOrderCol.setCellValueFactory(
                new PropertyValueFactory<String, String>("jobWorkOrder")
        );

        if (templateList11.getColumns().isEmpty()) {

            templateList11.getColumns().addAll(templateTable.getColumns());
            templateList12.getColumns().addAll(templateTable.getColumns());
            templateList13.getColumns().addAll(templateTable.getColumns());
            templateList14.getColumns().addAll(templateTable.getColumns());

            templateList20.getColumns().addAll(templateTable.getColumns());
            templateList21.getColumns().addAll(templateTable.getColumns());
            templateList22.getColumns().addAll(templateTable.getColumns());
            templateList23.getColumns().addAll(templateTable.getColumns());
            templateList24.getColumns().addAll(templateTable.getColumns());

            templateList30.getColumns().addAll(templateTable.getColumns());
            templateList31.getColumns().addAll(templateTable.getColumns());
            templateList32.getColumns().addAll(templateTable.getColumns());
            templateList33.getColumns().addAll(templateTable.getColumns());
            templateList34.getColumns().addAll(templateTable.getColumns());

            templateList40.getColumns().addAll(templateTable.getColumns());
            templateList41.getColumns().addAll(templateTable.getColumns());
            templateList42.getColumns().addAll(templateTable.getColumns());
            templateList43.getColumns().addAll(templateTable.getColumns());
            templateList44.getColumns().addAll(templateTable.getColumns());
            
            this.template_datepicker.selectionModelProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    System.out.println(newValue);
                }
            });
        }

//        1. Saperate Unique Template Dates.
//        2. Fill Data From that Date to forward 20.
        UpdateTemplateCalender();

    }

    private void UpdateTemplateCalender() {
        List<LocalDate> j = Container.LoadTemplateDate();

        this.template_datepicker.setItems(FXCollections.observableArrayList(j));
        System.out.println(j);
        if (j.size() > 0) {
            LocalDate first = j.get(0);
            int index = 0;

            System.out.println(j);

//            template_datepicker.setItems(FXCollections.observableArrayList(j));
//            templateTable.setItems(Container.LoadJobs());
            ObservableList<Job> josb = Container.LoadJobsOfDate(first.plusDays(index++));
            System.out.println(josb);

            templateTable.setItems(josb);
//        1st Row.
            templateList11.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList12.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList13.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList14.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            System.out.println(index + "");
//
//        //        2nd Row.
            templateList20.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList21.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList22.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList23.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList24.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));

            //        3rd Row.
            templateList30.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList31.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList32.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList33.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList34.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));

            //        4th Row.
            templateList40.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList41.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList42.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList43.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            templateList44.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
        }

    }

    private void LoadInstall() {

        installMaterialCol.setCellValueFactory(
                new PropertyValueFactory<String, String>("jobMaterial")
        );
        installWorkOrderCol.setCellValueFactory(
                new PropertyValueFactory<String, String>("jobWorkOrder")
        );

        if (installList11.getColumns().isEmpty()) {

            installList11.getColumns().addAll(installTable.getColumns());
            installList12.getColumns().addAll(installTable.getColumns());
            installList13.getColumns().addAll(installTable.getColumns());
            installList14.getColumns().addAll(installTable.getColumns());

            installList20.getColumns().addAll(installTable.getColumns());
            installList21.getColumns().addAll(installTable.getColumns());
            installList22.getColumns().addAll(installTable.getColumns());
            installList23.getColumns().addAll(installTable.getColumns());
            installList24.getColumns().addAll(installTable.getColumns());

            installList30.getColumns().addAll(installTable.getColumns());
            installList31.getColumns().addAll(installTable.getColumns());
            installList32.getColumns().addAll(installTable.getColumns());
            installList33.getColumns().addAll(installTable.getColumns());
            installList34.getColumns().addAll(installTable.getColumns());

            installList40.getColumns().addAll(installTable.getColumns());
            installList41.getColumns().addAll(installTable.getColumns());
            installList42.getColumns().addAll(installTable.getColumns());
            installList43.getColumns().addAll(installTable.getColumns());
            installList44.getColumns().addAll(templateTable.getColumns());
            
            this.install_datepicker.selectionModelProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    System.out.println(newValue);
                }
            });
        }

        UpdateInstallCalender();
    }

    private void UpdateInstallCalender() {
        List<LocalDate> j = Container.LoadTemplateDate();
        
        this.install_datepicker.setItems(FXCollections.observableArrayList(j));

        System.out.println(j);

        if (j.size() > 0) {
            LocalDate first = j.get(0);
            int index = 0;

            installTable.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));

//        Their will be many like these.
//        1st Row.
            installList11.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList12.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList13.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList14.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));

//
//        //        2nd Row.
            installList20.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList21.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList22.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList23.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList24.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));

            //        3rd Row.
            installList30.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList31.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList32.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList33.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList34.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));

            //        4th Row.
            installList40.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList41.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList42.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList43.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));
            installList44.setItems(Container.LoadJobsOfDate(first.plusDays(index++)));

        }
    }

    private void LoadInvntory() {
        inventorySqftCol.setCellValueFactory(new PropertyValueFactory<Inventory, String>("inventorySqft")
        );
        inventoryMaterialCol.setCellValueFactory(new PropertyValueFactory<Inventory, String>("inventoryMaterial")
        );
        inventoryTable.setItems(Container.LoadInventories());

        inventoryMaterialHistCol.setCellValueFactory(new PropertyValueFactory<InventoryTransaction, String>("inventoryMaterial")
        );
        inventoryThicknessHistCol.setCellValueFactory(new PropertyValueFactory<InventoryTransaction, String>("inventoryThickness")
        );
        inventorySqftHistCol.setCellValueFactory(new PropertyValueFactory<InventoryTransaction, String>("inventorySqft")
        );
        inventoryBatchHistCol.setCellValueFactory(new PropertyValueFactory<InventoryTransaction, String>("inventoryBatch")
        );
        inventoryPicHist.setCellValueFactory(new PropertyValueFactory<InventoryTransaction, String>("inventoryPicture")
        );
        inventoryHistTable.setItems(Container.LoadInventoriesTransaction());
    }

    private Double GetDouble(TextField jobSqft) {
        Double d = 0.0;
        try {
            d = Double.parseDouble(jobSqft.getText());
        } catch (Exception ex) {

        }
        return d;
    }

    final int TWENTY = 20;

    private LocalDate GetRange(LocalDate d) {
        return d.plusDays(TWENTY);
    }

}
