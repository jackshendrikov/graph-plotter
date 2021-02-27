package ua.jackshen.plotter.Views;

import javafx.scene.chart.LineChart;
import javafx.scene.layout.Pane;

/**
 * General design
 *
 * @author Jack Shendrikov
 *
 */
public class ScreenView {

    private Pane screen = new Pane();

    private GraphView graphView = new GraphView();

    private EquationPane equationPane = new EquationPane();

    public ScreenView() {
        screen.setPrefSize(1340, 810);
        screen.setStyle("-fx-background-color: #ffffff");

        LineChart graph = graphView.getGraph();
        graph.setLayoutX(450);
        graph.setLayoutY(0);

        Pane ePane = equationPane.getPane();
        ePane.setLayoutX(0);
        ePane.setLayoutY(0);

        screen.getChildren().addAll(ePane, graph);
    }

    public Pane getScreen() {
        return screen;
    }

    public GraphView getGraphView() {
        return graphView;
    }

    public EquationPane getEquationPane() {
        return equationPane;
    }

}
