package Project;

import java.awt.*;

public class FunctionParameters {
    private Color color;
    private int lineThickness;
    private double phaseShift;
    private double amplitude;

    public FunctionParameters(Color color, int lineThickness, double phaseShift, double amplitude) {
        this.color = color;
        this.lineThickness = lineThickness;
        this.phaseShift = phaseShift;
        this.amplitude = amplitude;
    }

    public Color getColor() {
        return color;
    }

    public int getLineThickness() {
        return lineThickness;
    }

    public double getPhaseShift() {
        return phaseShift;
    }

    public double getAmplitude() {
        return amplitude;
    }
}