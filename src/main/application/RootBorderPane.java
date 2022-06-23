package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Local;
import model.LocalException;
import model.Product;
import java.io.File;
import java.util.Optional;

import static application.AlertHelper.showAlert;
import static javafx.collections.FXCollections.observableList;

public class RootBorderPane extends BorderPane
{
    //Domain Mlodel
    private final Local local;

    // Menu
    private  final MenuBar menuBar = new MenuBar();

    // File ->  Load, Save, Exit
    private  final Menu mFile = new Menu("File");
    private final MenuItem miLoad = new MenuItem("Load");
    private final MenuItem miSave = new MenuItem("Save");
    private final MenuItem miExit = new MenuItem("Exit");

    // Edit ->  New, Edit, Delete
    private  final Menu mEdit = new Menu("Edit");
    private final MenuItem miNew = new MenuItem("New");
    private final MenuItem miEdit = new MenuItem("Edit");
    private final MenuItem miDelete = new MenuItem("Delete");

    // Sort ->  Price, Name(Absteigend)
    private  final Menu mSort = new Menu("Sort");
    private final MenuItem miSortPrice = new MenuItem("Sort Price");
    private final MenuItem miSortName = new MenuItem("Sort Name(Ab)");
    private final Button myButton = new Button("hit me one more time");


    // Info -> About
    private  final Menu mInfo = new Menu("Info");
    private final MenuItem miAbout = new MenuItem("About");
//    private final MenuItem miMaxPrice = new MenuItem("Max Price");

    // ListView
    private  final ListView<Product> productView = new ListView<>();

    // Dialog
    private final ProductDialog productDialog = new ProductDialog();
    public RootBorderPane(Local local)
    {
        this.local = local;

        initMenu();
        addMenu();
        initComponents();
        addComponents();
        addHandlers();
    }

    private void initMenu()
    {
        mEdit.setDisable(true);
    }

    private void addMenu()
    {
        menuBar.getMenus().addAll(mFile, mEdit, mSort, mInfo);
        mFile.getItems().addAll(miLoad, miSave, new SeparatorMenuItem(), miExit);
        mEdit.getItems().addAll(miNew, miEdit, miDelete);
        mSort.getItems().addAll(miSortPrice, miSortName);
        mInfo.getItems().addAll(miAbout); // miMaxPrice

    }

    private void initComponents()
    {

    }

    private void addComponents()
    {
        setTop(menuBar);
        setCenter(productView);
        setBottom(myButton);
    }

    private void addHandlers()
    {
        miExit.setOnAction(event -> Platform.exit());
        miAbout.setOnAction(event -> handelAbout());
        miLoad.setOnAction(event -> handelLoad());
        miSave.setOnAction(event -> handelSave());
        miDelete.setOnAction(event -> handelDelete());
        miEdit.setOnAction(event -> handelEdit());
        miNew.setOnAction(event -> handeNew());
        myButton.setOnAction(event -> handelMyButton());
        miSortPrice.setOnAction(event -> handelSortPrice());
        miSortName.setOnAction(event -> handelSortName());
//        miMaxPrice.setOnAction(event -> handelMaxPrice());
    }


//    private void handelMaxPrice()
//    {
//        double max;
//        max = Product.getMaxprice();
//        showAlert(Alert.AlertType.INFORMATION, "Max Price: " + max);
//        productView.refresh();
//    }

    private void handelSortName()
    {
        local.sortName();
        productView.refresh();
    }

    private void handelSortPrice()
    {
        local.sortPrice();
        productView.refresh();
    }

    private void handelMyButton()
    {
        local.sortName();
        productView.refresh();
    }

    private void handeNew()
    {
            try
            {
                Optional<ButtonType> buttonTypeOpt = productDialog.showNewDialog();
                ButtonType buttonType = buttonTypeOpt.get();
                if (buttonType == ButtonType.OK) {
                    local.addProduct(productDialog.getProduct());
                    productView.refresh();
                }
            }
            catch (LocalException e)
            {
                showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
    }

    private void handelEdit()
    {
        Product selectedProduct = productView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null)
        {
            Optional<ButtonType> buttonTypeOpt = productDialog.showEditDialog(selectedProduct);
            ButtonType buttonType = buttonTypeOpt.get();
            if (buttonType == ButtonType.OK) {
                productView.refresh();
//                productView.getSelectionModel().clearSelection();
            }
        }
        else
        {
            showAlert(Alert.AlertType.INFORMATION, "Please select a product");
        }

    }

    private void handelDelete()
    {
        Product selectProduct = productView.getSelectionModel().getSelectedItem();

        if (selectProduct != null)
        {
            local.removeProduct(selectProduct);
            productView.getSelectionModel().clearSelection();
        }
        else
        {
            showAlert(Alert.AlertType.INFORMATION, "Please select a product");
        }
    }

    private void handelLoad()
    {
        try
        {

            var filters = new FileChooser.ExtensionFilter("binary", "*.ser", "*.bin");
            var fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Local file");
            fileChooser.setInitialDirectory(new File("C:\\Users\\Elaheh\\OneDrive - HTL Spengergasse\\Desktop\\College\\Java_InteliJ\\Local-GUI"));
            fileChooser.getExtensionFilters().addAll(filters);
            File file = fileChooser.showOpenDialog(null);
            if (file != null)

            {
                local.load(file.getAbsolutePath());
//                System.out.println(local);
                productView.setItems(observableList(local.getProducts()));
                mEdit.setDisable(false);
            }
            }
            catch (LocalException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
    }

    private void handelSave()
    {

        try
        {
            var filters = new FileChooser.ExtensionFilter("binary", "*.ser", "*.bin");
            var fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Local file");
            fileChooser.setInitialDirectory(new File("C:\\Users\\Elaheh\\OneDrive - HTL Spengergasse\\Desktop\\College\\Java_InteliJ\\Local-GUI"));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
            File file = fileChooser.showSaveDialog(null);
            if (file != null)


            {
                local.save(file.getAbsolutePath());
            }
        }
        catch (LocalException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }

    }

    private void handelAbout()
    {
        showAlert(Alert.AlertType.INFORMATION, "Version 1.0");
    }
}
