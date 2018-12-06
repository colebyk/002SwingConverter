import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class SwingConverter implements ActionListener {

	private JPanel inputPanel = initinputPanel();
	private JTextField BaseTenInputText;
	private JTextField calcInputText;
	private JTextArea resultArea;
	private JButton enterButton;
	private JButton calcButton;

	public SwingConverter() {
		JFrame myWindow = new JFrame("Base Converter / Sphere Calculator - Created by Coleby K.");
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myWindow.add(inputPanel);
		myWindow.setSize(600, 500);
		myWindow.setVisible(true);
	}

	private JPanel initinputPanel() {

		JPanel panel = new JPanel();

		panel.setPreferredSize(new java.awt.Dimension(600, 500));
		panel.setBackground(java.awt.Color.gray);
		panel.setLayout(null);

		JLabel baseTenLabel = new JLabel("Enter a base 10 number: ");
		baseTenLabel.setBounds(50, 50, 220, 30);
		panel.add(baseTenLabel);
		
		JLabel calcLabel = new JLabel("Enter a radius: ");
		calcLabel.setBounds(50, 100, 220, 30);
		panel.add(calcLabel);
		
		JLabel nameLabel = new JLabel("Created by C. K.");
		nameLabel.setBounds(450, 440, 150, 20);
		panel.add(nameLabel);

		JTextField BaseTenText; // weird but i'm not going to freak out
		BaseTenText = BaseTenInputText = new JTextField();
		BaseTenText.setBounds(230, 50, 100, 30);
		BaseTenText.setBackground(java.awt.Color.GREEN);
		panel.add(BaseTenText);
		
		JTextField calcText;
		calcText = calcInputText = new JTextField();
		calcText.setBounds(230, 100, 100, 30);
		calcText.setBackground(java.awt.Color.BLUE);
		panel.add(calcText);
		

		JButton button = new JButton("Enter");
		button.setActionCommand("Enter");
		button.setBounds(340, 50, 100, 30);
		button.addActionListener(this);
		button.setBackground(java.awt.Color.GREEN);
		panel.add(button);
		// save to the class variable, to be used in other methods
		enterButton = button; // assign it to a class var to be used in other methods

		button = new JButton("Reset");
		button.setActionCommand("Reset");
		button.setBounds(450, 75, 100, 30);
		button.addActionListener(this);
		button.setBackground(java.awt.Color.RED);
		panel.add(button);

		button = new JButton("Calculate");
		button.setActionCommand("Calculate");
		button.setBounds(340, 100, 100, 30);
		button.addActionListener(this);
		button.setBackground(java.awt.Color.BLUE);
		panel.add(button);
		calcButton = button;

		resultArea = new JTextArea();
		resultArea.setBounds(50, 180, 500, 200);
		resultArea.setEditable(false);
		resultArea.setBackground(java.awt.Color.WHITE);
		panel.add(resultArea);

		return panel;
	}

	public static void main(String[] args) throws Exception {
		// SwingConverter baseConverter = new SwingConverter();
		new SwingConverter();
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		String command = ev.getActionCommand();
		if (command.equals("Enter")) {
			showResult();
		} else if (command.equals("Reset")) {
			reset();
		} else if (command.equals("Calculate")) {
			calcPressed();
	}
}

	private void reset() {
		// System.out.println("reset entered");
		BaseTenInputText.setText("");
		calcInputText.setText("");
		resultArea.setText("");
		BaseTenInputText.setEnabled(true);
		calcInputText.setEnabled(true);
		enterButton.setEnabled(true);
		calcButton.setEnabled(true);
	}

	private void calcPressed() {
		resultArea.setText("");
		calcInputText.setEnabled(false);
		BaseTenInputText.setEnabled(false);
		enterButton.setEnabled(false);
		calcButton.setEnabled(false);
		
		Double d = getCalcInput();
		if (d == null) {
			resultArea.append("Invalid number");
			return;
		}
		double radius = d.doubleValue();
		resultArea.append("Surface Area: " + (Double.toString(4 * Math.PI * Math.pow(radius, 2))));
		resultArea.append("\nVolume: " + (Double.toString(4 * Math.PI * Math.pow(radius, 3) / 3)));
		
		
		

	}

	private void showResult() {
		// System.out.println("showResult entered");
		String hexUpperCase;
		resultArea.setText("");
		calcInputText.setEnabled(false);
		BaseTenInputText.setEnabled(false);
		enterButton.setEnabled(false);
		calcButton.setEnabled(false);

		Integer n = getInputNumber();
		if (n == null) {
			resultArea.append("Invalid number");
			return;
		}

		int baseTenNumber = n.intValue();
		resultArea.append("Base Ten = " + baseTenNumber + "\n");
		resultArea.append("Binary = " + Integer.toString(baseTenNumber, 2) + "\n");
		resultArea.append("Octal = " + Integer.toString(baseTenNumber, 8) + "\n");
		hexUpperCase = Integer.toString(baseTenNumber, 16).toUpperCase();
		resultArea.append("hexadecimal = " + hexUpperCase + "\n");
	}

	private Integer getInputNumber() {

		try {
			Integer n = Integer.parseInt(BaseTenInputText.getText());
			return n;
		} catch (Exception e) {
			/*
			 * problem with number entry this traps that error, now tell user the error
			 * returning null prevents a total crash, but not really fixing the problem not
			 * good!
			 */
			return null;
			
		}
	}
	
	private Double getCalcInput() {
		
		try {
			Double d = Double.parseDouble(calcInputText.getText());
			return d;
		} catch (Exception e) {
			return null;
		}
	}

}
