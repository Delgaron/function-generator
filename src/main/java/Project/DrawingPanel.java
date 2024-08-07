package Project;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private FunctionType functionType;
    private FunctionParameters functionParameters;
    double piHalf = Math.PI / 2;
    double piQuarter = Math.PI / 4;
    double pi64th = Math.PI / 64;

    public DrawingPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 400));
    }

    public void setFunctionType(FunctionType functionType) {
        this.functionType = functionType;
    }

    public void setFunctionParameters(FunctionParameters functionParameters) {
        this.functionParameters = functionParameters;
    }
    // paintComponent is the one doing all the work
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (functionParameters == null) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        // Drawing x and y axis
        g.setColor(Color.BLACK);
        g.drawLine(0, height / 2, width, height / 2);
        g.drawLine(width / 2, 0, width / 2, height);

        // Variable calculating amount of points drawn. (function is not a constatnt line but rather many points drawn in short succesion of each other at calculated coordinates)
        int pointAmount = width*80;

        // Calculation of the distance between points drawn
        double distanceBetweenX = (double) width / pointAmount;

        // Begining coordinate
        double x = -width / 2;

        // Setting proper function parameters
        g.setColor(functionParameters.getColor());
        int lineThickness = functionParameters.getLineThickness();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(lineThickness));

        // Arccotangent function is drawn differently
        if (functionType == FunctionType.ARCCOTANGENT) {
            for (int i = 0; i < pointAmount; i++) {
                double y = functionParameters.getAmplitude() * (Math.PI / 2 - Math.atan((x / 30) + (functionParameters.getPhaseShift()*pi64th)));
                double xPixel = width / 2 + x;
                double yPixel = height / 2 - y;

                g.fillRect((int) xPixel, (int) yPixel, lineThickness, lineThickness);
                x += distanceBetweenX;
            }
        } else {
            // These 3 cyclometric functions are drawn the same way as trygonometric but their phase is done differently
            // here the pi64th variable is utilized
            if (functionType == FunctionType.ARCSINE || functionType == FunctionType.ARCCOSINE || functionType == FunctionType.ARCTANGENT) {
                for (int i = 0; i < pointAmount; i++) {
                double y = functionParameters.getAmplitude() * functionType.getFunctionType((x / 30) + (functionParameters.getPhaseShift()*pi64th));
                double xPixel = width / 2 + x;
                double yPixel = height / 2 - y;

                g.fillRect((int) xPixel, (int) yPixel, lineThickness, lineThickness);
                x += distanceBetweenX;
                }
            }   else {
                    //narysowanie funkcji dla reszty przypadkow
                     for (int i = 0; i < pointAmount; i++) {
                        double y = functionParameters.getAmplitude() * functionType.getFunctionType((x / 30) + (functionParameters.getPhaseShift()*piQuarter));
                        double xPixel = width / 2 + x;
                        double yPixel = height / 2 - y;

                        g.fillRect((int) xPixel, (int) yPixel, lineThickness, lineThickness);
                        x += distanceBetweenX;
                    }
                }
        }
        
        // Graphical object created just for the marking
        Graphics2D Axis = (Graphics2D) g.create();

        // Setting axis markings thickness
        int axisThickness = 1;
        Axis.setStroke(new BasicStroke(axisThickness));
        Axis.setColor(Color.BLACK);
    
        // X axis marks
        
        for (double i = -2; i <= 2; i++) {
            double xMark = width / 2 + (i * piHalf * 30);
            Axis.drawLine((int) xMark, height / 2 - 5, (int) xMark, height / 2 + 5);

            // naming x axis marks
            if (i == -1) {
                Axis.drawString("pi/2", (int) xMark - 12, height / 2 + 20);
            } else if (i == 0) {
                Axis.drawString("0", (int) xMark - 5, height / 2 + 20);
            } else if (i == 1) {
                Axis.drawString("pi/2", (int) xMark - 5, height / 2 + 20);
            } else {
                Axis.drawString("pi", (int) xMark - 12, height / 2 + 20);
            }
        }

        // Y axis marks
        Axis.drawLine(width / 2 - 5, height / 2 - 30, width / 2 + 5, height / 2 - (30));
        Axis.drawLine(width / 2 - 5, height / 2 + 30, width / 2 + 5, height / 2 + 30);

        // naming y axis marks
        Axis.drawString("1", width / 2 + 10, height / 2 - 30);
        Axis.drawString("-1", width / 2 + 10, height / 2 + 30);
    }
}