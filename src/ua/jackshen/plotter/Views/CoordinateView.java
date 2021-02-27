package ua.jackshen.plotter.Views;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Design for coordinates
 *
 * @author Jack Shendrikov
 *
 */
public class CoordinateView extends Pane {

    public CoordinateView(double xValue, double yValue) {
        this.setPrefSize(125, 30);
        this.setStyle("-fx-background-color: #ffffff");

        this.setBorder(new Border(new BorderStroke
                (Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(7.5), new BorderWidths(2.5))));

        Label coordinateLabel = new Label(" (" + xValue + "," + yValue + ") ");
        coordinateLabel.setFont(new Font("Cambria Math", 20));

        coordinateLabel.setLayoutX(0);
        coordinateLabel.setLayoutY(0);
        coordinateLabel.setPrefWidth(125);
        coordinateLabel.setAlignment(Pos.CENTER);

        this.getChildren().add(coordinateLabel);
    }
}
