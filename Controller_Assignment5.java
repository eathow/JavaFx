package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import javax.xml.bind.JAXB;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

public class Controller_Assignment5 {
    private enum PenSize {

        SMALL(2),
        MEDIUM(6),
        LARGE(10),
        XLARGE(14);

        final int radius;

        PenSize(int radius) {
            this.radius = radius;
        }

    }

    private enum DrawColor {

        BLACK(Color.BLACK),
        RED(Color.RED),
        GREEN(Color.GREEN),
        BLUE(Color.BLUE),
        BROWN(Color.BROWN);

        final Color color;

        DrawColor(Color color) {
            this.color = color;
        }
    }

    private PenSize penSize = PenSize.MEDIUM;
    private DrawColor drawColor = DrawColor.BLACK;

    @FXML
    private Slider slider;

    @FXML
    private Label lblBackground;

    @FXML
    private Label lblWhite;

    @FXML
    private Label lblBlue;

    @FXML
    private RadioButton rbBlack;

    @FXML
    private ToggleGroup tgColor;

    @FXML
    private RadioButton rbGreen;

    @FXML
    private RadioButton rbRed;

    @FXML
    private RadioButton rbBlue;

    @FXML
    private RadioButton rbBrown;

    @FXML
    private RadioButton rbSmall;

    @FXML
    private ToggleGroup tgPenSize;

    @FXML
    private RadioButton rbMed;

    @FXML
    private RadioButton rbLarge;

    @FXML
    private RadioButton rbXLarge;

    @FXML
    private Button btnUndo;

    @FXML
    private Button btnSerialize;

    @FXML
    private Button btnClear;

    @FXML
    private Pane panelDraw;

    @FXML
    private BorderPane bPane;

    @FXML
    void serializeButtonEventHandler(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the file you want");
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showOpenDialog(bPane.getScene().getWindow());
        ObservableList<Node> circles = panelDraw.getChildren();
        ListA5Shape record = new ListA5Shape();
        for(Node node: panelDraw.getChildren()) {
            if(node instanceof Circle) {
                Circle circle = (Circle) node;
                A5Shape shape = new A5Shape(circle.getCenterX(), circle.getCenterY(), circle.getRadius());
                record.addShape(shape);
            }
        }
        try {
            BufferedWriter output = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()));
            JAXB.marshal(record, output);
        }
        catch(IOException ioException) {
            System.err.println("Error opening file. Terminating.");
        }
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {

        panelDraw.getChildren().add(new Circle(event.getX(), event.getY(), penSize.radius, drawColor.color));
    }

    @FXML
    void colorChangeEventHandler(ActionEvent event) {
        if (rbBlack.isSelected())
            drawColor = DrawColor.BLACK;
        else if (rbGreen.isSelected())
            drawColor = DrawColor.GREEN;
        else if (rbRed.isSelected())
            drawColor = DrawColor.RED;
        else if (rbBlue.isSelected())
            drawColor = DrawColor.BLUE;
        else
            drawColor = DrawColor.BROWN;
    }

    @FXML
    void penSizeChangeEventHandler(ActionEvent event) {

        if (rbSmall.isSelected())
            penSize = PenSize.SMALL;
        else if (rbMed.isSelected())
            penSize = PenSize.MEDIUM;
        else if (rbLarge.isSelected())
            penSize = PenSize.LARGE;
        else
            penSize = PenSize.XLARGE;
    }

    @FXML
    void undoButtonEventHandler(ActionEvent event) {

        panelDraw.getChildren().remove(panelDraw.getChildren().size() - 1);
    }

    @FXML
    void clearButtonEventHandler(ActionEvent event) {

        panelDraw.getChildren().clear();
    }

    @FXML
    void initialize() {
        assert lblBackground != null : "fx:id=\"lblBackground\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert lblWhite != null : "fx:id=\"lblWhite\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert lblBlue != null : "fx:id=\"lblBlue\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert rbBlack != null : "fx:id=\"rbBlack\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert tgColor != null : "fx:id=\"tgColor\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert rbGreen != null : "fx:id=\"rbGreen\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert rbRed != null : "fx:id=\"rbRed\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert rbBlue != null : "fx:id=\"rbBlue\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert rbSmall != null : "fx:id=\"rbSmall\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert tgPenSize != null : "fx:id=\"tgPenSize\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert rbMed != null : "fx:id=\"rbMed\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert rbLarge != null : "fx:id=\"rbLarge\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert rbXLarge != null : "fx:id=\"rbXLarge\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert btnSerialize != null : "fx:id=\"btnSerialize\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert btnUndo != null : "fx:id=\"btnUndo\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Assignment5.fxml'.";
        assert panelDraw != null : "fx:id=\"panelDraw\" was not injected: check your FXML file 'Assignment5.fxml'.";

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int background = (int) slider.getValue();
                int red = 255-background;
                int green = 255-background;
                String rHex;
                String gHex;
                if(red < 16) {
                    rHex = "0" + Integer.toHexString(red);
                }
                else {
                    rHex = Integer.toHexString(red);
                }
                if(green < 16) {
                    gHex = "0" + Integer.toHexString(green);
                }
                else {
                    gHex = Integer.toHexString(green);
                }
                String color =  rHex + gHex + Integer.toHexString(255);
                String backgroundColor = "-fx-background-color: #" + color;
                panelDraw.setStyle(backgroundColor);
            }
        });
    }
}