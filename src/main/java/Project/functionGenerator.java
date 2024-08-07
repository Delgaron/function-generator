package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class functionGenerator {
    private JFrame frame;
    private JComboBox<String> functionComboBox;
    private JComboBox<String> colorComboBox;
    private JSlider thicknessSlider;
    private JSlider shiftSlider;
    private JSlider amplitudeSlider;
    private JButton generateButton;
    private DrawingPanel drawingPanel;

    public functionGenerator() {
        createShowGUI();
    }

    private void createShowGUI() {
        // Creation of main component
        frame = new JFrame("Function Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Choose your function panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Welcome message
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Welcome! Choose the type of function you want to generate, its color, amplitude and phase shift."), gbc);

        // Choosing function
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Function:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        functionComboBox = new JComboBox<>(new String[]{
                "Cosine", "Sine", "Tangent", "Cotangent", "Arcsine", "Arccosine", "Arctangent", "Arccotangent"
        });
        mainPanel.add(functionComboBox, gbc);

        // Choosing color
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Color:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        colorComboBox = new JComboBox<>(new String[]{"Red", "Blue", "Green", "Yellow"});
        mainPanel.add(colorComboBox, gbc);

        // Thickness slider 
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Line thickness:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        thicknessSlider = new JSlider(1, 5, 1);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setMajorTickSpacing(1);
        thicknessSlider.setSnapToTicks(true);
        mainPanel.add(thicknessSlider, gbc);

        // Phase slider
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(new JLabel("Phase shift:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        shiftSlider = new JSlider(0, 10, 0);
        shiftSlider.setPaintTicks(true);
        shiftSlider.setMajorTickSpacing(1);
        shiftSlider.setSnapToTicks(true);
        mainPanel.add(shiftSlider, gbc);

        // Amplitude slider
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(new JLabel("Amplitude:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        amplitudeSlider = new JSlider(1, 10, 1);
        amplitudeSlider.setPaintTicks(true);
        amplitudeSlider.setMajorTickSpacing(1);
        amplitudeSlider.setSnapToTicks(true);
        mainPanel.add(amplitudeSlider, gbc);

        // Generating button
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        generateButton = new JButton("Generate");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingFunction();
            }
        });
        mainPanel.add(generateButton, gbc);

        // Creating panel with function
        drawingPanel = new DrawingPanel();

        // Combining panels
        frame.add(mainPanel, BorderLayout.NORTH);
        frame.add(drawingPanel, BorderLayout.CENTER);

        // Setting dimensions
        frame.setSize(1000, 800);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    // Drawing function uses FunctionType class to determine type of function to draw, FunctionParameters to determine user input, DrawingPanel to draw the function using
    // Graphics2D library

    private void drawingFunction() {
        String choosenFunction = (String) functionComboBox.getSelectedItem();
        FunctionType functionType = getFunctionType(choosenFunction);

        String wybranyColor = (String) colorComboBox.getSelectedItem();
        Color color = getColor(wybranyColor);

        int lineThickness = thicknessSlider.getValue();
        double phaseShift = shiftSlider.getValue() * 30;
        double amplitude = amplitudeSlider.getValue() * 30;

        FunctionParameters functionParameters = new FunctionParameters(color, lineThickness, phaseShift, amplitude);

        drawingPanel.setFunctionType(functionType);
        drawingPanel.setFunctionParameters(functionParameters);
        drawingPanel.repaint();
    }

    private FunctionType getFunctionType(String functionName) {
        switch (functionName) {
            case "Cosine":
                return FunctionType.COSINE;
            case "Sine":
                return FunctionType.SINE;
            case "Tangent":
                return FunctionType.TANGENT;
            case "Cotangent":
                return FunctionType.COTANGENT;
            case "Arcsine":
                return FunctionType.ARCSINE;
            case "Arccosine":
                return FunctionType.ARCCOSINE;
            case "Arctangent":
                return FunctionType.ARCTANGENT;
            case "Arccotangent":
                return FunctionType.ARCCOTANGENT;
            default:
                return FunctionType.COSINE;
        }
    }

    private Color getColor(String colorName) {
        switch (colorName) {
            case "Red":
                return Color.RED;
            case "Blue":
                return Color.BLUE;
            case "Green":
                return Color.GREEN;
            case "Yellow":
                return Color.YELLOW;
            default:
                return Color.RED;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new functionGenerator();
            }
        });
    }
}