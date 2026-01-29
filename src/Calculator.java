import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JTextArea conversionArea;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, divButton, mulButton;
    JButton delButton, clrButton, eqlButton, decButton, percButton;
    JPanel panel;

    Font myFont = new Font("Perpetua", Font.BOLD, 30);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 600);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);


        conversionArea = new JTextArea();
        conversionArea.setBounds(50, 80, 300, 60);
        conversionArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        conversionArea.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        eqlButton = new JButton("=");
        clrButton = new JButton("CE");
        delButton = new JButton("Del");
        percButton = new JButton("%");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = delButton;
        functionButtons[6] = eqlButton;
        functionButtons[7] = clrButton;

        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        delButton.setBounds(45, 480, 145, 50);
        clrButton.setBounds(215, 480, 145, 50);

        panel = new JPanel();
        panel.setBounds(50, 150, 300, 300);
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBackground(Color.LIGHT_GRAY);

        panel.add(numberButtons[0]);
        panel.add(numberButtons[1]);

        panel.add(addButton);
        panel.add(subButton);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(eqlButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
        frame.add(conversionArea);  // Add conversion area
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            textfield.setText(textfield.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            textfield.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
        }
        if (e.getSource() == eqlButton) {
            num2 = Double.parseDouble(textfield.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;   // fixed subtraction bug
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }
            textfield.setText(String.valueOf(result));
            num1 = result;

            // Show binary & hex if result is an integer
            if (result == (int) result) {
                int intResult = (int) result;
                conversionArea.setText("Binary: " + Integer.toBinaryString(intResult) +
                        "\nHex: " + Integer.toHexString(intResult).toUpperCase());
            } else {
                conversionArea.setText("Binary/Hex only for integers.");
            }
        }

        if (e.getSource() == clrButton) {
            textfield.setText("");
            conversionArea.setText("");
        }

        if (e.getSource() == delButton) {
            String string = textfield.getText();
            textfield.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textfield.setText(textfield.getText() + string.charAt(i));
            }
        }
    }
}
