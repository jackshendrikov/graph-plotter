package ua.jackshen.plotter.Models;

import javafx.scene.shape.Circle;
import javafx.util.Pair;

/**
 * @author Jack Shendrikov
 *
 */
public class CoordinatePair extends Pair<Double, Double> {

    private Circle graphPoint = new Circle(); // visual representation of the point for these coordinates

    private boolean isIntersection = false;

    CoordinatePair(Double key, Double value) {
        super(key, value);
    }

    public CoordinatePair(int key, int value) {
        super((double)key, (double)value);
    }

    public Circle getGraphPoint() {
        return graphPoint;
    }

    void setGraphPoint(Circle graphPoint) {
        this.graphPoint = graphPoint;
    }

    void setIntersection(boolean intersection) {
        isIntersection = intersection;
    }

    public boolean isIntersection() {
        return isIntersection;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof CoordinatePair) {
            if(((CoordinatePair) o).getKey().equals(this.getKey()) && ((CoordinatePair) o).getValue().equals(this.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int)(this.getKey() + this.getValue());
    }

}
