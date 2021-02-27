package ua.jackshen.plotter.Views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Design for Equation Menu
 *
 * @author Jack Shendrikov
 *
 */
public class EquationPane {

    private Pane equationPane = new Pane();

    private EquationRegion equationRegion = new EquationRegion();
    private BorderedTitledPane domainRegion = new BorderedTitledPane("Domain");
    private BorderedTitledPane rangeRegion = new BorderedTitledPane("Range");
    private BorderedTitledPane errorRegion = new BorderedTitledPane("Error Log");

    private Button graphButton = new Button("Graph");

    private TextField lowerDomain = new TextField("-10");
    private TextField upperDomain = new TextField("10");

    private TextField lowerRange = new TextField("-10");
    private TextField upperRange = new TextField("10");

    private Label errorLogLabel = new Label();


    EquationPane() {
        equationPane.setPrefSize(450, 810);
        equationPane.setStyle("-fx-background-color: #3d3c3b");

        setUpEquationRegion();
        setUpDomainRegion();
        setUpRangeRegion();
        setUpErrorRegion();

        graphButton.setLayoutX(125);
        graphButton.setLayoutY(700);
        graphButton.setPrefWidth(150);

        Button addEquationButton = new Button("Add Equation");
        addEquationButton.setLayoutX(20);
        addEquationButton.setLayoutY(650);
        addEquationButton.setPrefWidth(180);
        addEquationButton.setOnAction(event -> equationRegion.addEquation());

        Button removeEquationButton = new Button("Remove Equation");
        removeEquationButton.setLayoutX(220);
        removeEquationButton.setLayoutY(650);
        removeEquationButton.setPrefWidth(190);
        removeEquationButton.setOnAction(event -> equationRegion.removeEquation());

        equationPane.getChildren().addAll(graphButton, addEquationButton, removeEquationButton);

        equationPane.getStylesheets().add(getClass().getResource(
                "style.css"
        ).toExternalForm());
    }

    private void setUpEquationRegion() {
        equationRegion.setPrefSize(395, 350);
        equationRegion.setLayoutX(15);
        equationRegion.setLayoutY(50);


        equationPane.getChildren().addAll(equationRegion);
    }

    private void setUpDomainRegion() {
        domainRegion.setPrefSize(395, 75);
        domainRegion.setLayoutX(15);
        domainRegion.setLayoutY(450);

        lowerDomain.setLayoutX(35);
        lowerDomain.setLayoutY(470);
        lowerDomain.setPrefWidth(150);

        upperDomain.setLayoutX(230);
        upperDomain.setLayoutY(470);
        upperDomain.setPrefWidth(150);

        equationPane.getChildren().addAll(domainRegion, lowerDomain, upperDomain);
    }

    private void setUpRangeRegion() {
        rangeRegion.setPrefSize(395, 75);
        rangeRegion.setLayoutX(15);
        rangeRegion.setLayoutY(550);

        lowerRange.setLayoutX(35);
        lowerRange.setLayoutY(570);
        lowerRange.setPrefWidth(150);

        upperRange.setLayoutX(230);
        upperRange.setLayoutY(570);
        upperRange.setPrefWidth(150);

        equationPane.getChildren().addAll(rangeRegion, lowerRange, upperRange);
    }

    private void setUpErrorRegion() {
        errorRegion.setPrefSize(395, 50);
        errorRegion.setLayoutX(15);
        errorRegion.setLayoutY(750);

        errorLogLabel.setPrefWidth(350);
        errorLogLabel.setLayoutX(40);
        errorLogLabel.setLayoutY(770);
        errorLogLabel.setText("No Errors");
        errorLogLabel.setTextFill(Color.RED);

        equationPane.getChildren().addAll(errorRegion, errorLogLabel);
    }


    public Pair<String, String> getDomain() {
        return new Pair<>(lowerDomain.getText(), upperDomain.getText());
    }

    public Pair<String, String> getRange() {
        return new Pair<>(lowerRange.getText(), upperRange.getText());
    }

    public String getErrorLogText() {
        return errorLogLabel.getText();
    }

    public void addToErrorLog(String text) {
        if(errorLogLabel.getText().contains(text)) return; //do nothing as don't need duplicate errors

        if(errorLogLabel.getText().equals("")) {
            errorLogLabel.setText(errorLogLabel.getText() + " " + text);
        } else {
            errorLogLabel.setText(errorLogLabel.getText() + " & " + text);
        }

        if(errorLogLabel.getPrefWidth() > errorLogLabel.getWidth()) {
            errorLogLabel.setFont(new Font(8));
        }
    }

    public void setErrorLog(String text) {
        errorLogLabel.setText(text);
        errorLogLabel.setPrefWidth(325);
    }

    Pane getPane() {
        return equationPane;
    }

    public Button getGraphButton() {
        return graphButton;
    }

    public EquationRegion getEquationRegion() {
        return equationRegion;
    }


    private class BorderedTitledPane extends StackPane {

        Label title;

        private BorderedTitledPane(String titleString) {
            title = new Label(" " + titleString + " ");
            title.getStyleClass().add("bordered-titled-title");
            StackPane.setAlignment(title, Pos.TOP_CENTER);

            getStylesheets().add(getClass().getResource(
                    "style.css"
            ).toExternalForm());
            getStyleClass().add("border-pane");

            getStyleClass().add("bordered-titled-border");
            getChildren().add(title);
        }
    }

    public class EquationRegion extends BorderedTitledPane {

        private ArrayList<Equation> equationList = new ArrayList<>();

        private ScrollPane scrollPane = new ScrollPane();
        private FlowPane content = new FlowPane();

        private EquationRegion() {
            super("Equation");

            scrollPane.setLayoutX(0);
            scrollPane.setLayoutY(0);
            scrollPane.setPrefSize(this.getWidth() / 2, this.getHeight());

            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            scrollPane.setFocusTraversable(false);

            content.setPrefSize(scrollPane.getWidth(), scrollPane.getHeight());
            scrollPane.setContent(content);
            content.getStyleClass().add("flow-pane");

            Equation firstEquation = new Equation();
            firstEquation.setLayoutY(20);
            firstEquation.requestFocus();
            equationList.add(firstEquation);

            content.getChildren().addAll(firstEquation);

            getChildren().addAll(scrollPane);

            title.toFront();
        }

        void addEquation() {
            Equation lastEquation = equationList.get(equationList.size() - 1);

            content.setPrefHeight(content.getPrefHeight() + 60);

            Equation newEquation = new Equation();
            newEquation.setLayoutX(0);
            newEquation.setLayoutY(lastEquation.getLayoutY() + 60);

            newEquation.requestFocus();

            content.getChildren().add(newEquation);

            equationList.add(newEquation);
        }

        void removeEquation() {
            Equation lastEquation = equationList.get(equationList.size() - 1);
            if (equationList.size() > 1) {
                content.setPrefHeight(content.getPrefHeight() - 60);
                content.getChildren().remove(lastEquation);
                equationList.remove(lastEquation);
            }
        }

        public ArrayList<String> getEquations() {
            ArrayList<String> equations = new ArrayList<>();

            for(Equation eq : equationList) {
                equations.add(eq.getEquation());
            }

            return equations;
        }

        private class Equation extends Pane{
            private Label equationText = new Label("y = ");
            private TextField equationTextBox = new TextField();


            private Equation() {
                this.setPrefSize(375, 60);

                equationText.setLayoutX(5);
                equationText.setLayoutY(10);

                equationTextBox.setLayoutX(35);
                equationTextBox.setLayoutY(10);
                equationTextBox.setPrefWidth(325);


                this.getChildren().addAll(equationText, equationTextBox);
            }

            String getEquation() {
                return equationTextBox.getText();
            }
        }
    }

}
