import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface extends JFrame implements ActionListener {
    private JTextField amountField;
    private JLabel balanceLabel, messageLabel;
    private JButton checkBalanceButton, depositButton, withdrawButton;
    private double balance = 0.0;

    public ATMInterface() {
        setTitle("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(45, 45, 45)); // Dark background color
        setLayout(new GridLayout(5, 2, 10, 10));

        // Balance Label
        balanceLabel = new JLabel("Balance: $0.00", SwingConstants.CENTER);
        balanceLabel.setForeground(Color.WHITE);
        add(balanceLabel);

        // Amount Field
        amountField = new JTextField();
        amountField.setHorizontalAlignment(JTextField.CENTER);
        amountField.setBackground(new Color(30, 30, 30)); // Dark input field
        amountField.setForeground(Color.WHITE);
        add(amountField);

        // Check Balance Button
        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(this);
        checkBalanceButton.setBackground(new Color(60, 60, 60)); // Dark button background
        checkBalanceButton.setForeground(Color.WHITE);
        add(checkBalanceButton);

        // Deposit Button
        depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        depositButton.setBackground(new Color(60, 60, 60)); // Dark button background
        depositButton.setForeground(Color.WHITE);
        add(depositButton);

        // Withdraw Button
        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        withdrawButton.setBackground(new Color(60, 60, 60)); // Dark button background
        withdrawButton.setForeground(Color.WHITE);
        add(withdrawButton);

        // Message Label
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE);
        add(messageLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        double amount;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid amount.");
            return;
        }

        switch (command) {
            case "Check Balance":
                messageLabel.setText("Current Balance: $" + String.format("%.2f", balance));
                break;
            case "Deposit":
                if (amount > 0) {
                    balance += amount;
                    messageLabel.setText("Deposited: $" + String.format("%.2f", amount));
                    balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
                } else {
                    messageLabel.setText("Please enter a positive amount.");
                }
                break;
            case "Withdraw":
                if (amount > 0) {
                    if (amount <= balance) {
                        balance -= amount;
                        messageLabel.setText("Withdrew: $" + String.format("%.2f", amount));
                        balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
                    } else {
                        messageLabel.setText("Insufficient funds.");
                    }
                } else {
                    messageLabel.setText("Please enter a positive amount.");
                }
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMInterface::new);
    }
}
