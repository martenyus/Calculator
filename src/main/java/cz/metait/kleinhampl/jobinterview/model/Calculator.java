package cz.metait.kleinhampl.jobinterview.model;

import javax.swing.*;

public class Calculator {

    private final JButton clearEverythingButton;
    private final JButton clearCharButton;
    private final JButton percentageButton;
    private final JButton divideButton;
    private final JButton subtractButton;
    private final JButton addButton;
    private final JButton multiplyButton;
    private final JButton decimalPointButton;
    private final JButton equalsButton;

    private final JButton emptyButton;

    private final JButton zeroButton;
    private final JButton oneButton;
    private final JButton twoButton;
    private final JButton threeButton;
    private final JButton fourButton;
    private final JButton fiveButton;
    private final JButton sixButton;
    private final JButton sevenButton;
    private final JButton eightButton;
    private final JButton nineButton;

    private String firstOperand;
    private String secondOperand;
    private String operationType;

    public Calculator(){
        clearEverythingButton = createButton("CE");
        clearCharButton = createButton("C");
        percentageButton = createButton("%");
        divideButton = createButton("/");
        multiplyButton = createButton("x");
        subtractButton = createButton("-");
        addButton = createButton("+");
        equalsButton = createButton("=");
        decimalPointButton = createButton(".");
        emptyButton = createButton("");
        zeroButton = createButton("0");
        oneButton = createButton("1");
        twoButton = createButton("2");
        threeButton = createButton("3");
        fourButton = createButton("4");
        fiveButton = createButton("5");
        sixButton = createButton("6");
        sevenButton = createButton("7");
        eightButton = createButton("8");
        nineButton = createButton("9");
        firstOperand = "";
        secondOperand = "";
        operationType = "";
    }

    private JButton createButton(String labelText){
        return new JButton(labelText);
    }

    public void disableOperation(JButton button){
        button.setEnabled(false);
    }

    public void enableOperation(JButton button){
        button.setEnabled(true);
    }

    public void disableAllOperations(){
        disableOperation(getAddButton());
        disableOperation(getPercentageButton());
        disableOperation(getDivideButton());
        disableOperation(getMultiplyButton());
        disableOperation(getEqualsButton());
        disableOperation(getDecimalPointButton());
        disableOperation(getClearEverythingButton());
        disableOperation(getClearCharButton());
        disableOperation(getSubtractButton());
    }

    public void enableAllOperations(){
        enableOperation(getSubtractButton());
        enableOperation(getEqualsButton());
        enableOperation(getClearEverythingButton());
        enableOperation(getClearCharButton());
        enableOperation(getPercentageButton());
        enableOperation(getDivideButton());
        enableOperation(getMultiplyButton());
        enableOperation(getAddButton());
        enableOperation(getDecimalPointButton());
    }

    public void enableButtonsAfterOpType(){
        disableAllOperations();
        enableOperation(getClearEverythingButton());
        enableOperation(getClearCharButton());
        enableOperation(getSubtractButton());
    }

    public double divide(Double firstOperand, Double secondOperand){
        return firstOperand/secondOperand;
    }

    public double multiply(Double firstOperand, Double secondOperand){
        return firstOperand * secondOperand;
    }

    public double add(Double firstOperand, Double secondOperand){
        return firstOperand + secondOperand;
    }

    public double subtract(Double firstOperand, Double secondOperand){
        return firstOperand - secondOperand;
    }

    public String manageEquals(){
        if(operationType.isEmpty()){
            return firstOperand;
        }
        else if (operationType.equals(getMultiplyButton().getText())){
            return String.valueOf(multiply(Double.parseDouble(firstOperand), Double.parseDouble(secondOperand)));
        }
        else if (operationType.equals(getDivideButton().getText())){
            return String.valueOf(divide(Double.parseDouble(firstOperand), Double.parseDouble(secondOperand)));
        }
        else if (operationType.equals(getAddButton().getText())){
            return String.valueOf(add(Double.parseDouble(firstOperand), Double.parseDouble(secondOperand)));
        }
        else if(operationType.equals(getSubtractButton().getText())){
            return String.valueOf(subtract(Double.parseDouble(firstOperand), Double.parseDouble(secondOperand)));
        }
        else return "ERROR";
    }

    public String clearChar(String str){
        if (str.endsWith(".")){
            enableOperation(getDecimalPointButton());
        }
        else if (str.endsWith("-")){
            enableOperation(getSubtractButton());
        }

        var str2 = str.substring(0, str.length() - 1);
        if(str2.endsWith(getDecimalPointButton().getText()) || str2.endsWith(getSubtractButton().getText())){
            disableOperation(getEqualsButton());
            disableOperation(getDecimalPointButton());
        }

        return str2;
    }

    public String manageClearChar(){
        if (!getSecondOperand().isEmpty()){
            setSecondOperand(clearChar(getSecondOperand()));
        }
        else if(!getOperationType().isEmpty()){
            setOperationType("");
            enableAllOperations();
            disableOperation(getDecimalPointButton());
        }
        else{
            setFirstOperand(clearChar(getFirstOperand()));
        }
        if (getFirstOperand().isEmpty()){
            disableAllOperations();
            enableOperation(getSubtractButton());
        }
        return stringForDisplay();
    }

    public String manageClearEverything(){
        setFirstOperand("");
        setSecondOperand("");
        setOperationType("");
        disableAllOperations();
        enableOperation(getSubtractButton());
        return stringForDisplay();
    }

    public String manageDivision(){
        enableButtonsAfterOpType();
        setOperationType(getDivideButton().getText());
        return stringForDisplay();
    }

    public String manageMultiplication(){
        enableButtonsAfterOpType();
        setOperationType(getMultiplyButton().getText());
        return stringForDisplay();
    }

    public String manageAddition(){
        enableButtonsAfterOpType();
        setOperationType(getAddButton().getText());
        return stringForDisplay();
    }

    public String manageSubtraction(){
        if (firstOperand.isEmpty()){
            setFirstOperand(firstOperand.concat(getSubtractButton().getText()));
            disableOperation(getSubtractButton());
        }else if (getOperationType().isEmpty()){
            setOperationType(getSubtractButton().getText());
            enableButtonsAfterOpType();
        } else {
            setSecondOperand(secondOperand.concat(getSubtractButton().getText()));
            disableOperation(getSubtractButton());
        }
        return stringForDisplay();
    }

    public String identifyNumber(Object pressedButton){
        if (getZeroButton().equals(pressedButton)){
            return getZeroButton().getText();
        }
        else if(getOneButton().equals(pressedButton)){
            return getOneButton().getText();
        }
        else if (getTwoButton().equals(pressedButton)){
            return getTwoButton().getText();
        }
        else if(getThreeButton().equals(pressedButton)){
            return getThreeButton().getText();
        }
        else if (getFourButton().equals(pressedButton)){
            return getFourButton().getText();
        }
        else if (getFiveButton().equals(pressedButton)){
            return getFiveButton().getText();
        }
        else if (getSixButton().equals(pressedButton)){
            return getSixButton().getText();
        }
        else if (getSevenButton().equals(pressedButton)){
            return getSevenButton().getText();
        }
        else if (getEightButton().equals(pressedButton)){
            return getEightButton().getText();
        }
        return getNineButton().getText();
    }

    public String manageNumbers(Object pressedButton){
        var number = identifyNumber(pressedButton);
        if (getOperationType().isEmpty()){
            setFirstOperand(getFirstOperand().concat(number));
            enableAllOperations();
            if (getFirstOperand().contains(getDecimalPointButton().getText())){
                disableOperation(getDecimalPointButton());
            }
        }else {
            if (!getSecondOperand().contains(getDecimalPointButton().getText())){
                enableOperation(getDecimalPointButton());
            }
            setSecondOperand(getSecondOperand().concat(number));
            disableOperation(getSubtractButton());
            enableOperation(getEqualsButton());
            enableOperation(getClearCharButton());
            enableOperation(getClearEverythingButton());
        }

        return stringForDisplay();
    }

    public String manageDecimal(){
        if (getOperationType().isEmpty()){
            setFirstOperand(firstOperand.concat(getDecimalPointButton().getText()));
        }else {
            setSecondOperand(secondOperand.concat(getDecimalPointButton().getText()));
        }
        disableAllOperations();
        enableOperation(getClearEverythingButton());
        enableOperation(getClearCharButton());
        return stringForDisplay();
    }

    public String stringForDisplay(){
        return firstOperand + operationType + secondOperand;
    }

    public JButton getClearEverythingButton() {
        return clearEverythingButton;
    }

    public JButton getClearCharButton() {
        return clearCharButton;
    }

    public JButton getPercentageButton() {
        return percentageButton;
    }

    public JButton getDivideButton() {
        return divideButton;
    }

    public JButton getSubtractButton() {
        return subtractButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEmptyButton() {
        return emptyButton;
    }

    public JButton getMultiplyButton() {
        return multiplyButton;
    }

    public JButton getDecimalPointButton() {
        return decimalPointButton;
    }

    public JButton getEqualsButton() {
        return equalsButton;
    }

    public JButton getZeroButton() {
        return zeroButton;
    }

    public JButton getOneButton() {
        return oneButton;
    }

    public JButton getTwoButton() {
        return twoButton;
    }

    public JButton getThreeButton() {
        return threeButton;
    }

    public JButton getFourButton() {
        return fourButton;
    }

    public JButton getFiveButton() {
        return fiveButton;
    }

    public JButton getSixButton() {
        return sixButton;
    }

    public JButton getSevenButton() {
        return sevenButton;
    }

    public JButton getEightButton() {
        return eightButton;
    }

    public JButton getNineButton() {
        return nineButton;
    }

    public String getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(String firstOperand) {
        this.firstOperand = firstOperand;
    }

    public String getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(String secondOperand) {
        this.secondOperand = secondOperand;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
