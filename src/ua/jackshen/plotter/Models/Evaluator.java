package ua.jackshen.plotter.Models;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


/**
 * Evaluator for Text to Equation and then to Graph
 *
 * @author Jack Shendrikov
 *
 */
public class Evaluator {

    private NumberAxis xAxis;

    public Evaluator(LineChart graph) {
        xAxis = (NumberAxis)graph.getXAxis();
    }

    public CoordinatePair[] evaluate(String expr) {
        double lower = xAxis.getLowerBound();
        double upper = xAxis.getUpperBound();

        final int stepDivider = 1500;

        double arrayStep = (upper - lower) / stepDivider;
        CoordinatePair[] coordinateArray = new CoordinatePair[stepDivider + 1];

        int arrayIterator = 0;

        for(double i = xAxis.getLowerBound(); i < xAxis.getUpperBound(); i += arrayStep) {
            Expression e = new ExpressionBuilder(expr)
                    .variables("x")
                    .build()
                    .setVariable("x", i);

            try {
                coordinateArray[arrayIterator] =  new CoordinatePair(i, e.evaluate()); //sets new coordinate pair with x and y values
            } catch(ArithmeticException ex) {
                coordinateArray[arrayIterator] = null;
            }

            arrayIterator++;
        }

        return coordinateArray;

    }


}
