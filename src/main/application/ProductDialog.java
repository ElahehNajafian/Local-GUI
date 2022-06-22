package application;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Product;

import java.util.Optional;

public class ProductDialog extends Dialog<ButtonType>
{
    private Product product;
    private final GridPane content = new GridPane();
    private final Label lID = new Label("ID");
    private final TextField tfID = new TextField();
    private final Label lName = new Label("Name");
    private final TextField tfName = new TextField();
    private final Label lPrice = new Label("Price");
    private final TextField tfPrice = new TextField();
    private final Label lCount = new Label("Count");
    private final Spinner<Integer> spCount = new Spinner<>(10, 500, 20);
    private final Label lMl = new Label("ml");
    private final Slider slMl = new Slider(50, 1000, 500);
    private final Label lWithAlcohol = new Label("Alcohol");
    private final HBox boxAlcohol = new HBox();
    private final ToggleGroup tgAlcohol = new ToggleGroup();
    private final RadioButton rbWithAlcohol = new RadioButton("With Alcohol");
    private final RadioButton rbWithOutAlcohol = new RadioButton("Without Alcohol");
    private final ButtonType btOK = ButtonType.OK;
    private final ButtonType btCancel = ButtonType.CANCEL;

    public ProductDialog()
    {
        initComponents();
        addComponents();
        addHandlers();
    }

    public Product getProduct()
    {
        return product;
    }
    private void initComponents()
    {
        boxAlcohol.setSpacing(15);
        content.setVgap(20);
        content.setHgap(20);
        content.setPadding(new Insets(20, 15, 10 ,15));

        slMl.setMajorTickUnit(50);
        slMl.setShowTickLabels(true);
        
        rbWithAlcohol.setToggleGroup(tgAlcohol);
        rbWithOutAlcohol.setToggleGroup(tgAlcohol);
    }

    private void addComponents()
    {
        boxAlcohol.getChildren().addAll(rbWithAlcohol, rbWithOutAlcohol);

        content.add(lID, 0,0);
        content.add(tfID, 1,0);
        content.add(lName, 0,1);
        content.add(tfName, 1 ,1);
        content.add(lPrice, 0,2);
        content.add(tfPrice, 1,2);
        content.add(lCount, 0,3);
        content.add(spCount, 1,3);
        content.add(lMl,0,4);
        content.add(slMl, 1, 4);
        content.add(lWithAlcohol, 0,5);
        content.add(boxAlcohol, 1, 5);
        content.add(new Separator(Orientation.HORIZONTAL), 0, 6, 2, 1);

        getDialogPane().getButtonTypes().addAll(btOK, btCancel);
        getDialogPane().setContent(content);
    }

    private void addHandlers()
    {
        Button okButton = (Button) getDialogPane().lookupButton(btOK);
        okButton.addEventFilter(ActionEvent.ACTION, event -> updateProduct(event));
    }

    private void updateProduct(ActionEvent event)
    {
        try
        {
            product.setId(Integer.parseInt(tfID.getText()));
            product.setDrinkName(tfName.getText());
            product.setWithAlcohol(rbWithAlcohol.isSelected());
            product.setMl(slMl.getMinorTickCount());
            product.setprice(Double.parseDouble(String.valueOf(tfPrice)));
            product.setCount(product.getCount());

        }
        catch (Exception e)
        {
            AlertHelper.showAlert(Alert.AlertType.ERROR, e.getMessage());
            event.consume();
        }
    }

    // int id, String drinkName, boolean withAlcohol, int ml, double price, int count
    public Optional<ButtonType> showEditDialog(Product product)
    {
        this.product = product;
        setTitle("Edit Product");

        tfID.setText(String.valueOf(product.getId()));
        tfName.setText(product.getDrinkName());
        tfPrice.setText(String.valueOf(product.getPrice()));
        spCount.getValueFactory().setValue(product.getCount());
        slMl.setMajorTickUnit(product.getMl());
        rbWithAlcohol.setSelected(product.isWithAlcohol());
        rbWithOutAlcohol.setSelected(!product.isWithAlcohol());

        return showAndWait();

    }

    public Optional<ButtonType> showNewDialog()
    {
        this.product = new Product();
        setTitle("New Product");

        tfID.setText("100");
        tfID.setPromptText("ID");
        tfName.setText("");
//        tfName.setPromptText("Name");
        tfPrice.setText("2.0");
        tfPrice.setPromptText("Price");
        spCount.getValueFactory().setValue(500);
        slMl.setShowTickLabels(true);
        rbWithAlcohol.setSelected(true);
        rbWithOutAlcohol.setSelected(false);

        return showAndWait();
    }
}
