package ua.jackshen.plotter.Views;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;


/**
 * Design for Graph
 *
 * @author Jack Shendrikov
 *
 */
public class GraphView {

    private NumberAxis xAxis = new NumberAxis();
    private NumberAxis yAxis = new NumberAxis();
    private LineChart graph;

    GraphView() {
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);

        xAxis.setTickUnit(1);
        xAxis.setLowerBound(-10);
        xAxis.setUpperBound(10);

        yAxis.setTickUnit(1);
        yAxis.setLowerBound(-10);
        yAxis.setUpperBound(10);

        graph = new LineChart(xAxis, yAxis);

        graph.setLegendVisible(false);
        graph.setAnimated(false);

        graph.setPrefSize(880, 800);
        graph.setLayoutY(0);

        graph.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }

    public void setDomain(double lowerDomain, double upperDomain) {
        xAxis.setLowerBound(lowerDomain);
        xAxis.setUpperBound(upperDomain);
        xAxis.setTickUnit((upperDomain - lowerDomain) / 20);
    }

    public void setRange(double lowerRange, double upperRange) {
        yAxis.setLowerBound(lowerRange);
        yAxis.setUpperBound(upperRange);
        yAxis.setTickUnit((upperRange - lowerRange) / 20);
    }


    public LineChart getGraph() {
        return graph;
    }
}
