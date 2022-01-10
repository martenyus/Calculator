package cz.metait.kleinhampl.jobinterview.ui;

import cz.metait.kleinhampl.jobinterview.model.Calculator;
import cz.metait.kleinhampl.jobinterview.ui.i18n.I18N;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainWindow implements ActionListener {

    private static final I18N I18N = new I18N(MainWindow.class);
    private final JFrame frame;
    private static Calculator calculator;
    private static JTextField display;

    public MainWindow(){
        frame = createFrame();
        frame.setLayout(new BorderLayout());
        var jPanel = new JPanel();
        display = new JTextField();

        calculator = new Calculator();

        frame.add(display,"North");

        display.setEditable(false);
        display.setPreferredSize(new Dimension(15, 40));
        frame.add(jPanel, "Center");
        insertButtons(jPanel, calculator);
        initializeButtons(calculator);

    }

    public void show() {
        frame.setVisible(true);
    }

    private void insertButtons(JPanel panel, Calculator c){
        panel.add(c.getClearEverythingButton());
        panel.add(c.getClearCharButton());
        panel.add(c.getPercentageButton());
        panel.add(c.getDivideButton());
        panel.add(c.getSevenButton());
        panel.add(c.getEightButton());
        panel.add(c.getNineButton());
        panel.add(c.getMultiplyButton());
        panel.add(c.getFourButton());
        panel.add(c.getFiveButton());
        panel.add(c.getSixButton());
        panel.add(c.getSubtractButton());
        panel.add(c.getOneButton());
        panel.add(c.getTwoButton());
        panel.add(c.getThreeButton());
        panel.add(c.getAddButton());
        panel.add(c.getEmptyButton());
        panel.add(c.getZeroButton());
        panel.add(c.getDecimalPointButton());
        panel.add(c.getEqualsButton());
        c.getEmptyButton().setVisible(false);
        panel.setLayout(new GridLayout(5,4));
    }

    private JFrame createFrame() {
        var frame = new JFrame(I18N.getString("title"));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(250,400));
        return frame;
    }

    private void addListener(JButton button){
        button.addActionListener(this);
    }

    private void addListeners(Calculator c){
        addListener(c.getClearEverythingButton());
        addListener(c.getClearCharButton());
        addListener(c.getPercentageButton());
        addListener(c.getDivideButton());
        addListener(c.getSevenButton());
        addListener(c.getEightButton());
        addListener(c.getNineButton());
        addListener(c.getMultiplyButton());
        addListener(c.getFourButton());
        addListener(c.getFiveButton());
        addListener(c.getSixButton());
        addListener(c.getSubtractButton());
        addListener(c.getOneButton());
        addListener(c.getTwoButton());
        addListener(c.getThreeButton());
        addListener(c.getAddButton());
        addListener(c.getEmptyButton());
        addListener(c.getZeroButton());
        addListener(c.getDecimalPointButton());
        addListener(c.getEqualsButton());
    }

    private void initializeButtons(Calculator c){
        addListeners(c);
        c.disableAllOperations();
        c.enableOperation(c.getSubtractButton());
    }

    public void continueCalculating(){
        calculator.setFirstOperand(display.getText());
        calculator.setSecondOperand("");
        calculator.setOperationType("");
        calculator.enableAllOperations();
        calculator.disableOperation(calculator.getDecimalPointButton());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object pressedButton = e.getSource();
        if (calculator.getDivideButton().equals(pressedButton)){
            display.setText(calculator.manageDivision());
            calculator.disableOperation(calculator.getEqualsButton());
        }
        else if (calculator.getMultiplyButton().equals(pressedButton)){
            display.setText(calculator.manageMultiplication());
            calculator.disableOperation(calculator.getEqualsButton());
        }
        else if (calculator.getAddButton().equals(pressedButton)){
            display.setText(calculator.manageAddition());
            calculator.disableOperation(calculator.getEqualsButton());
        }
        else if(calculator.getPercentageButton().equals(pressedButton)){
            display.setText(String.valueOf(calculator.divide(Double.parseDouble(calculator.getFirstOperand()), 100.0)));
            continueCalculating();
        }
        else if (calculator.getSubtractButton().equals(pressedButton)){
            display.setText(calculator.manageSubtraction());

        }
        else if(calculator.getEqualsButton().equals(pressedButton)){
            display.setText(calculator.manageEquals());
            continueCalculating();
        }
        else if (calculator.getDecimalPointButton().equals(pressedButton)){
            display.setText(calculator.manageDecimal());
        }
        else if(calculator.getClearEverythingButton().equals(pressedButton)){
            display.setText(calculator.manageClearEverything());
        }
        else if (calculator.getClearCharButton().equals(pressedButton)){
            display.setText(calculator.manageClearChar());
        }
        else{
            display.setText(calculator.manageNumbers(pressedButton));
        }

    }
}
