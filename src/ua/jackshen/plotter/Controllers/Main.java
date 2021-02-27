package ua.jackshen.plotter.Controllers;

import javafx.scene.image.Image;
import ua.jackshen.plotter.Models.CoordinatePair;
import ua.jackshen.plotter.Models.Evaluator;
import ua.jackshen.plotter.Models.Plotter;
import ua.jackshen.plotter.Views.EquationPane;
import ua.jackshen.plotter.Views.ScreenView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Main Class
 *
 * @author Jack Shendrikov
 *
 */
public class Main extends Application {

    private ScreenView screenView = new ScreenView();
    private Pane screen = screenView.getScreen();
    private EquationPane equationPane = screenView.getEquationPane();
    private LineChart graph = screenView.getGraphView().getGraph();

    private Button graphButton = screenView.getEquationPane().getGraphButton();

    private Evaluator evaluator = new Evaluator(graph);
    private Plotter plotter = new Plotter(graph, screen);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Graphing Calculator");
        primaryStage.setScene(new Scene(screen, 1340, 810));
        primaryStage.getIcons().add(new Image("https://i.imgur.com/AHN3iMx.png"));
        primaryStage.show();

        setGraphButtonListener();
    }

    private void setGraphButtonListener() {
        graphButton.setOnAction(event -> {

            equationPane.setErrorLog("");
            setDomainandRange();
            graph.getData().clear();
            plotPoints();

            if(equationPane.getErrorLogText().equals("")) {
                equationPane.setErrorLog("No Errors");
            }
        });
    }

    private void plotPoints() {
        ArrayList<String> equations = screenView.getEquationPane().getEquationRegion().getEquations();

        ArrayList<CoordinatePair[]> coordinateArrayList = new ArrayList<>();
        ArrayList<CoordinatePair> allPoints = new ArrayList<>();

        for(String equation : equations) {
            try {
                CoordinatePair[] coordinateArray = evaluator.evaluate(equation);
                coordinateArrayList.add(coordinateArray);

            } catch (IllegalArgumentException ex) {
                if(equation.equals("")) {
                    equationPane.addToErrorLog("Missing Equation(s)");
                } else {
                    equationPane.addToErrorLog("Invalid Equation(s)");
                }
            }
        }

        System.gc();

        for(CoordinatePair[] pairArray : coordinateArrayList) {
            Collections.addAll(allPoints, pairArray);
        }

        ArrayList<CoordinatePair> intersections = plotter.findIntersections(allPoints);
        System.out.println("New Graph: " + intersections);

        for(CoordinatePair[] coordinateArray : coordinateArrayList) {
            plotter.plot(coordinateArray, intersections);
        }

        System.gc();
    }


    private void setDomainandRange() {
        Pair<Double, Double> sortedDomain = sortValues(screenView.getEquationPane().getDomain());
        if(sortedDomain != null) {
            screenView.getGraphView().setDomain(sortedDomain.getKey(), sortedDomain.getValue());
        }

        Pair<Double, Double> sortedRange = sortValues(screenView.getEquationPane().getRange());
        if(sortedRange != null) {
            screenView.getGraphView().setRange(sortedRange.getKey(), sortedRange.getValue());
        }


    }

    private Pair<Double, Double> sortValues(Pair<String, String> values) {
        double lowerValue, upperValue;

        try {
            lowerValue = Double.parseDouble(values.getKey());
            upperValue = Double.parseDouble(values.getValue());
        } catch(NumberFormatException ex) {
            equationPane.addToErrorLog("Non-Numeric Domain(s) or Range(s)");

            return null; //no way to fix a non-numeric input
        }

        if(lowerValue > upperValue) {
            double temp = upperValue;
            upperValue = lowerValue;
            lowerValue = temp;
        }

        return new Pair<>(lowerValue, upperValue);
    }


    public static void main(String[] args) {
        launch(args);
    }
}