import Item.*; 
import java.time.*; 
import java.util.Date;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.text.*;
import java.time.LocalDate;
import javafx.collections.*;
import javafx.scene.image.*;
import javafx.beans.value.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import java.text.SimpleDateFormat;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import java.time.format.DateTimeFormatter;

public class AddStage extends Stage{

    //================================================================================
    // INITIALIZATION
    //================================================================================
    private static int addpresscount = 0;
    private static int editpresscount = 0;
    private static double addwidth = 600;
    private static double addw_to_h = 0.85;
    private static double addheight = addwidth / addw_to_h;
    private static double numbertextwidth = 100.0;; 
    private static String tbcolour = "#006733;";
    private static String midcolour = "#d5f0e2;";

    //OBJECTS 
    private static Font f = new Font(15);
    private static Text a = new Text("*");
    private static Text a2 = new Text("*");
    private static Text a3 = new Text("*");
    private static Text a4 = new Text("*");
    private static Text a5 = new Text("*");
    private static Text itemname = new Text("Item Name:");
    private static Text quantity = new Text("Quantity:");
    private static Text minimum = new Text("Minimum:");
    private static Text shipping = new Text("Delivery Time:");
    private static Text describe = new Text("Description:");
    private static Text logitem = new Text("Log Item?");
    private static Text missing = new Text();
    private static Text addtext = new Text();
    private static TextField itemtext = new TextField();
    private static TextField qtext = new TextField();
    private static TextField mtext = new TextField();
    private static TextField stext = new TextField();
    private static TextArea dtext = new TextArea();
    private static AnchorPane ianchor = new AnchorPane();
    private static AnchorPane qanchor = new AnchorPane();
    private static AnchorPane manchor = new AnchorPane();
    private static AnchorPane sanchor = new AnchorPane();
    private static AnchorPane danchor = new AnchorPane();
    private static Text qdesc = new Text("How much of this item do you currently have on hand?");
    private static Text qdesc2 = new Text("Entry should be in singular units (eg. 3 boxes of 50 gloves = 150).");
    private static Text mdesc = new Text("What is the minimum number of this item you want in your office?");
    private static Text mdesc2 = new Text("Entry should be in singular units (eg. 3 boxes of 50 gloves = 150).");
    private static Text sdesc = new Text("An estimate of how long the item would take to deliver to your location.");
    private static Text sdesc2 = new Text("Entry should be in the number of days (if bought in person, enter 0).");
    private static Button cancelbtn = new Button();
    private static Button createbtn = new Button();
    private static HBox checkhbox = new HBox(10);
    private static Label yeslabel = new Label("Yes");
    private static RadioButton yesbox = new RadioButton();
    private static Label nolabel = new Label("No");
    private static RadioButton nobox = new RadioButton();
    private static String addtip = "The date that you want logging to begin on. This date cannot be changed later.";
    private static String edittip = "The date you wish this log to correspond to.";
    private static Tooltip helptip = new Tooltip();
    private static Button helpBtn = new Button();
    private static BorderPane abase = new BorderPane();
    private static AnchorPane addtop = new AnchorPane();
    private static AnchorPane addbottom = new AnchorPane();
    private static HBox bottomBox = new HBox(10);
    private static VBox wcenter = new VBox();
    private static Image helpBtnImg = new Image("./Misc/help.png");
    private static ImageView helpImg = new ImageView(); 
    private static DatePicker datepicker = new DatePicker();
    private static ObservableList<Item> placeholder = FXCollections.observableArrayList();
    private static int filterSel = 0;

    //objects from kettlelog app
    private static Kettlelog kettle = new Kettlelog();
    private static FilterComparators filterObject = new FilterComparators();
    private static FilterHandler filterHandler = new FilterHandler();

    //CHANGING VARIABLES
    private String prename;
    private String prequan;
    private String premin;
    private String predel;
    private String predesc;
    private String predate;

    AddStage(){

        //0 --> ADD WINDOW
        //1 --> EDIT WINDOW

        String topbottom = String.format("-fx-background-color: %s", tbcolour);
        String middle = String.format("-fx-background-color: %s", midcolour);

        bottomBox.getChildren().addAll(cancelbtn, createbtn);
        addtop.setStyle(topbottom);
        addtop.setPrefSize(60, 80); 

        AnchorPane.setLeftAnchor(addtext, 42.0);
        AnchorPane.setBottomAnchor(addtext, 10.0);
        addtext.setFont(new Font(18));
        addtext.setFill(Color.WHITE); 
        addtop.getChildren().addAll(addtext);
        addbottom.setStyle(topbottom);
        addbottom.setPrefSize(30, 40); 

        createbtn.setId("createbtn");  
        createbtn.setStyle("-fx-background-color: #093d23;");
        createbtn.setTextFill(Color.WHITE);
        cancelbtn.setText("Cancel");
        cancelbtn.setId("cancelBtn");
        cancelbtn.setStyle("-fx-background-color: #d5f0e2;");
        AnchorPane.setRightAnchor(bottomBox, 6.25);
        AnchorPane.setTopAnchor(bottomBox, 6.25);
        addbottom.getChildren().addAll(bottomBox);

        abase.setTop(addtop);
        abase.setBottom(addbottom);

        ianchor.setPrefSize(addwidth, 80);
        itemname.setFont(f);
        itemname.setFill(Color.BLACK);
        AnchorPane.setRightAnchor(itemname, 460.0);
        AnchorPane.setBottomAnchor(itemname, 15.0);

        a.setFont(new Font (15));
        a.setFill(Color.RED);
        AnchorPane.setRightAnchor(a, 545.0);
        AnchorPane.setBottomAnchor(a, 16.0);

        itemtext.setPrefWidth(150);
        AnchorPane.setLeftAnchor(itemtext, 150.0); 
        AnchorPane.setBottomAnchor(itemtext, 10.0);

        //Icon taken from flaticon.com
        helpImg.setImage(helpBtnImg);
        helpImg.setFitWidth(20);
        helpImg.setPreserveRatio(true);
        helpImg.setSmooth(true);
        helpImg.setCache(true);  
        helpBtn.setGraphic(helpImg);    
        helpBtn.setStyle("-fx-background-color: transparent;");  
        helptip.setPrefWidth(150);
        helptip.setWrapText(true);  
        helpBtn.setTooltip(helptip);     
        AnchorPane.setRightAnchor(helpBtn, 175.0);
        AnchorPane.setBottomAnchor(helpBtn, 8.0);

        datepicker.setPrefWidth(125);
        datepicker.setEditable(false);
        AnchorPane.setRightAnchor(datepicker, 50.0);
        AnchorPane.setBottomAnchor(datepicker, 10.0);

        ianchor.setStyle(middle);
        ianchor.getChildren().addAll(itemname, itemtext, a, datepicker, helpBtn);  
        qanchor.setPrefSize(addwidth, 90);
        quantity.setFont(f);
        quantity.setFill(Color.BLACK);
        AnchorPane.setRightAnchor(quantity, 460.0);
        AnchorPane.setBottomAnchor(quantity, 45.0);

        HBox iBox = new HBox(ianchor);

        a2.setFont(new Font (15));
        a2.setFill(Color.RED);
        AnchorPane.setRightAnchor(a2, 527.8);
        AnchorPane.setBottomAnchor(a2, 46.0);

        qtext.setPrefWidth(numbertextwidth);
        AnchorPane.setLeftAnchor(qtext, 150.0); 
        AnchorPane.setBottomAnchor(qtext, 40.0);

        qtext.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(javafx.beans.value.ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    qtext.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        qdesc.setFont(new Font(12));
        qdesc.setFill(Color.GREY);
        AnchorPane.setLeftAnchor(qdesc, 150.0);
        AnchorPane.setBottomAnchor(qdesc, 20.0);

        qdesc2.setFont(new Font(12));
        qdesc2.setFill(Color.GREY);
        AnchorPane.setLeftAnchor(qdesc2, 150.0);
        AnchorPane.setBottomAnchor(qdesc2, 5.0);
 
        qanchor.setStyle(middle);
        qanchor.getChildren().addAll(quantity, qtext, qdesc, qdesc2, a2);
        HBox qBox = new HBox(qanchor);

        manchor.setPrefSize(addwidth, 90);
        minimum.setFont(f);
        minimum.setFill(Color.BLACK);
        AnchorPane.setRightAnchor(minimum, 460.0);
        AnchorPane.setBottomAnchor(minimum, 45.0);

        a3.setFont(new Font (15));
        a3.setFill(Color.RED);
        AnchorPane.setRightAnchor(a3, 532.0);
        AnchorPane.setBottomAnchor(a3, 46.0);

        mtext.setPrefWidth(numbertextwidth);
        AnchorPane.setLeftAnchor(mtext, 150.0); 
        AnchorPane.setBottomAnchor(mtext, 40.0);

        mtext.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(javafx.beans.value.ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    mtext.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        mdesc.setFont(new Font(12));
        mdesc.setFill(Color.GREY);
        AnchorPane.setLeftAnchor(mdesc, 150.0);
        AnchorPane.setBottomAnchor(mdesc, 20.0);

        mdesc2.setFont(new Font(12));
        mdesc2.setFill(Color.GREY);
        AnchorPane.setLeftAnchor(mdesc2, 150.0);
        AnchorPane.setBottomAnchor(mdesc2, 5.0);
 
        manchor.setStyle(middle);
        manchor.getChildren().addAll(minimum, mtext, mdesc, mdesc2, a3);

        sanchor.setPrefSize(addwidth, 90);
        shipping.setFont(f);
        shipping.setFill(Color.BLACK);
        AnchorPane.setRightAnchor(shipping, 460.0);
        AnchorPane.setBottomAnchor(shipping, 45.0);

        HBox mBox = new HBox(manchor);
        HBox sBox = new HBox(sanchor);

        a4.setFont(new Font (15));
        a4.setFill(Color.RED);
        AnchorPane.setRightAnchor(a4, 565.0);
        AnchorPane.setBottomAnchor(a4, 46.0);

        stext.setPrefWidth(numbertextwidth);
        AnchorPane.setLeftAnchor(stext, 150.0); 
        AnchorPane.setBottomAnchor(stext, 40.0);

        stext.textProperty().addListener(new ChangeListener<String>() {
        @Override
            public void changed(javafx.beans.value.ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    stext.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        sdesc.setFont(new Font(12));
        sdesc.setFill(Color.GREY);
        AnchorPane.setLeftAnchor(sdesc, 150.0);
        AnchorPane.setBottomAnchor(sdesc, 20.0);

        sdesc2.setFont(new Font(12));
        sdesc2.setFill(Color.GREY);
        AnchorPane.setLeftAnchor(sdesc2, 150.0);
        AnchorPane.setBottomAnchor(sdesc2, 5.0);
 
        sanchor.setStyle(middle);
        sanchor.getChildren().addAll(shipping, stext, sdesc, sdesc2, a4);

        danchor.setPrefSize(addwidth, 240);     
        describe.setFont(f);
        describe.setFill(Color.BLACK);
        AnchorPane.setRightAnchor(describe, 460.0);
        AnchorPane.setTopAnchor(describe, 20.0);

        dtext.positionCaret(4);
        dtext.setPrefWidth(400);
        dtext.setPrefHeight(175);
        dtext.setWrapText(true);

        AnchorPane.setLeftAnchor(dtext, 150.0); 
        AnchorPane.setTopAnchor(dtext, 20.0);
        
        missing.setFont(new Font(12));
        missing.setFill(Color.FIREBRICK);
        AnchorPane.setRightAnchor(missing, 50.0);
        AnchorPane.setBottomAnchor(missing, 15.0);
        missing.setVisible(false);

        danchor.setStyle(middle);
        danchor.getChildren().addAll(describe, dtext, missing);

        HBox dBox = new HBox(danchor);
        
        wcenter.getChildren().addAll(iBox, qBox, mBox, sBox, dBox);
        abase.setCenter(wcenter);

        cancelbtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
            public void handle(ActionEvent event) {
                kettle.hideAddStage();
                missing.setVisible(false);
                addpresscount = 0;
                editpresscount = 0;
                checkhbox.setVisible(false);
                logitem.setVisible(false);
                a5.setVisible(false);
            }
        });

            //A togglegroup allows only one of the radiobuttons to be selected.
            ToggleGroup radioGroup = new ToggleGroup();
            yesbox.setToggleGroup(radioGroup);
            nobox.setToggleGroup(radioGroup);
            //Logging Label & RadioButtons
            yeslabel.setGraphic(yesbox);
            yeslabel.setContentDisplay(ContentDisplay.RIGHT); 
            nolabel.setGraphic(nobox);
            nolabel.setContentDisplay(ContentDisplay.RIGHT); 
            checkhbox.getChildren().addAll(yeslabel, nolabel);
            checkhbox.setVisible(false);
            AnchorPane.setRightAnchor(checkhbox, 50.0);
            AnchorPane.setBottomAnchor(checkhbox, 45.0);
            //Log Item Text & Asterisk
            logitem.setFont(f);
            logitem.setFill(Color.BLACK);
            logitem.setVisible(false);
            double logdistance = 160.0;
            AnchorPane.setRightAnchor(logitem, logdistance);
            AnchorPane.setBottomAnchor(logitem, 45.0);
            a5.setFont(new Font (15));
            a5.setFill(Color.RED);
            a5.setVisible(false);
            AnchorPane.setRightAnchor(a5, logdistance + 75.0); 
            AnchorPane.setBottomAnchor(a5, 46.0);
            qanchor.getChildren().addAll(logitem, a5, checkhbox);

        this.setScene(new Scene(abase, addwidth, addheight));
        this.initStyle(StageStyle.UNDECORATED);
        this.initOwner(kettle.getPrimaryStage());
        this.initModality(Modality.WINDOW_MODAL);
        this.setResizable(false);
        this.setTitle("Add New Item");
    }


    public void updateAddStage(int popuptype, String[] textarray, Item rowinfo){

        //PRE-SET TEXT FIELDS TAKEN FROM 5-ELEMENT ARRAY
        //This array takes the name, quantity, minimum, shipping time, description and date of the column.
        //The purpose of this is to save the information so that it can be displayed when the edit button is clicked.
        
        String prename = textarray[0];
        String prequan = textarray[1];
        String premin = textarray[2];
        String predel = textarray[3];
        String predesc = textarray[4];
        String predate = textarray[5];

        itemtext.setText(prename);
        qtext.setText(prequan);
        mtext.setText(premin);
        stext.setText(predel);
        dtext.setText(predesc);

        if (popuptype == 0) {
            addtext.setText("Add New Item");
            createbtn.setText("Create");
            helptip.setText(addtip);
            datepicker.setValue(LocalDate.now());
        } else {
            addtext.setText("Log/Edit Item");
            createbtn.setText("Edit");
            helptip.setText(edittip);
            LocalDate originaldate = LocalDate.parse(predate);
            datepicker.setValue(originaldate);
            checkhbox.setVisible(true);
            logitem.setVisible(true);
            a5.setVisible(true);

        }

        createbtn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
            public void handle(ActionEvent event) {

                boolean incomplete = false;
                String itemStatus = "";
                String iName = itemtext.getText();
                String curQuan = qtext.getText();
                String minQuan = mtext.getText();
                String delTime = stext.getText();
                String itemDesc = dtext.getText();

                //we need to get the value that the user sets as the date and convert it to a string
                String newdate = datepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                //CHECKS IF THERE ARE ANY REQUIRED FIELDS THAT ARE LEFT EMPTY
                if ((iName.trim().length() <= 0) || curQuan.isEmpty() || minQuan.isEmpty() || delTime.isEmpty()) {
                    incomplete = true;
                }

                if (incomplete) {
                    missing.setText("* One or more required fields have not been filled out.");
                    missing.setVisible(true);
                }

                else {
                    missing.setVisible(false);

                    //Checking if the item name is already in the list of data.
                    if (kettle.duplicatefound(iName)) {
                        addpresscount++;
                    } else {
                        addpresscount = 2; 
                    }

                    int intQuan = Integer.parseInt(curQuan);
                    int intMin = Integer.parseInt(minQuan);
                    int total = intQuan + intMin;
                    double health = (((double) intQuan / total)) * 100;

                    //DETERMINING THE STATUS OF THE ITEM 
                    if (intQuan == 0) {
                        itemStatus = "Empty";
                    } else if (health < 25) {
                        itemStatus = "Very Poor";
                    } else if (health < 40) {
                        itemStatus = "Poor";
                    } else if (health < 50) {
                        itemStatus = "Moderate";
                    } else if (health < 75) {
                        itemStatus = "Good";
                    } else {
                        itemStatus = "Very Good";
                    }

                    //user tries to add a duplicate for the first time, so we should display the message.
                    if (popuptype == 0 && addpresscount == 1) {
                        missing.setText("* Possible duplicate found. Are you sure you want to add this item?");
                        missing.setVisible(true);
                    }

                    //The user either reached a presscount of 2 from adding a non-duplicate item or pressing "Create" twice with a duplicate item.
                    else if (popuptype == 0 & addpresscount == 2) { 
                        String olddate = newdate;
                        Item newitem = new Item(iName, itemStatus, curQuan, minQuan, delTime, itemDesc, false, false, newdate, olddate);
                        ObservableList<Item> addthisitem = FXCollections.observableArrayList(newitem);
                        kettle.setData(addthisitem, 0);
                        kettle.clearSearchBar();
                        kettle.hideAddStage();
                        addpresscount = 0;
                        editpresscount = 0;
                        checkhbox.setVisible(false);
                        logitem.setVisible(false);
                        a5.setVisible(false);
                    }

                    //if the type is to edit, update the information at every field.
                    else {
                        //We don't want to display the warning if the name is not changed. 
                        String currentname = rowinfo.getName().toLowerCase();
                        String editedname = iName.toLowerCase();
                        if (currentname.equals(editedname)) {
                            editpresscount = 2;
                        } 
                        else {
                            if (kettle.duplicatefound(iName)) {
                                editpresscount++;
                            } 
                            else {
                                editpresscount = 2; 
                            }
                        }

                        //If a user edits an item name to be the same as an existing one, we display the warning again.
                        if (popuptype == 1 && editpresscount == 1) {
                            missing.setText("* An item with this name already exists. Are you sure you want to continue?");
                            missing.setVisible(true);
                        }

                        //THe user has reached this stage by either pressing Edit twice or editing the item name to a non-duplicate.
                        else if (popuptype == 1 && editpresscount == 2) {
                            rowinfo.setName(iName);
                            rowinfo.setStatus(itemStatus);
                            rowinfo.setQuantity(curQuan);
                            rowinfo.setMinimum(minQuan);
                            rowinfo.setDelivery(delTime);
                            rowinfo.setDesc(itemDesc);
                            rowinfo.setDate(newdate);
                            kettle.clearSearchBar();

                            checkhbox.setVisible(false);
                            logitem.setVisible(false);
                            a5.setVisible(false);

                            //JUST PUTTING IN AN EMPTY OBSERVABLELIST FOR THE SETDATA FUNCTION
                            kettle.setData(placeholder, 1);
                            kettle.hideAddStage();
                            addpresscount = 0;
                            editpresscount = 0;
                        }

                    }
                }

                filterSel = filterHandler.getFilterSel();

                if(filterSel==1){
                    filterObject.sortByStarred();
                }
                else if(filterSel==2 || filterSel==3){
                    filterObject.sortByMostRecent(filterSel);
                }
            }
        }); 
    }
}

 