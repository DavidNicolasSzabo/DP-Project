package ro.uvt.fi.dp.UI;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import ro.uvt.fi.dp.Objects.*;
import ro.uvt.fi.dp.Handlers.ClientFileHandler;

public class ClientView extends JFrame {
    private Client client;
    private Account selectedAccount;
    private JComboBox<String> accountSelector;
    private JRadioButton rbDepose, rbRetrieve, rbTotal, rbTransfer, rbLoan, rbPayLoan;
    private JTextField amountInput, targetAccInput;
    private JLabel statusLabel;

    public ClientView(Client client) {
        this.client = client;
        this.selectedAccount = client.getAccounts().iterator().next();
        setTitle("Client Management - " + client.getName());
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(204, 240, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.gridx = 0; gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(new JLabel("<html><b>CLIENT:</b> " + client.getName() + "</html>"), gbc);
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Select Account:"), gbc);
        gbc.gridy = 2;
        accountSelector = new JComboBox<>();
        for(Account a : client.getAccounts()) accountSelector.addItem(a.getAccountCode());
        accountSelector.addActionListener(e -> {
            selectedAccount = client.getAccount((String)accountSelector.getSelectedItem());
            statusLabel.setText("Active: " + selectedAccount.getAccountCode() + " ($" + selectedAccount.getAmount() + ")");
        });
        mainPanel.add(accountSelector, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        ButtonGroup actionGroup = new ButtonGroup();
        rbDepose = createActionRB("Depose", actionGroup, mainPanel, gbc, 0);
        rbRetrieve = createActionRB("Retrieve", actionGroup, mainPanel, gbc, 1);
        rbTotal = createActionRB("Get Total Amount", actionGroup, mainPanel, gbc, 2);
        rbTransfer = createActionRB("Transfer", actionGroup, mainPanel, gbc, 3);
        rbLoan = createActionRB("Loan", actionGroup, mainPanel, gbc, 4);
        rbPayLoan = createActionRB("Pay Loan", actionGroup, mainPanel, gbc, 5);
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel cassette = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cassette.setBorder(BorderFactory.createTitledBorder("Transaction Cassette"));
        cassette.setOpaque(false);
        cassette.add(new JLabel("Amount:"));
        amountInput = new JTextField(8);
        cassette.add(amountInput);
        cassette.add(new JLabel("Target IBAN (Transfer Only):"));
        targetAccInput = new JTextField(8);
        cassette.add(targetAccInput);
        mainPanel.add(cassette, gbc);
        gbc.gridy = 7; gbc.gridwidth = 1;
        statusLabel = new JLabel("Balance: $" + selectedAccount.getAmount());
        mainPanel.add(statusLabel, gbc);
        gbc.gridx = 1;
        JButton okBtn = new JButton("Ok");
        okBtn.addActionListener(e -> executeTransaction());
        mainPanel.add(okBtn, gbc);
        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private JRadioButton createActionRB(String text, ButtonGroup group, JPanel panel, GridBagConstraints gbc, int y) {
        JRadioButton rb = new JRadioButton(text);
        rb.setOpaque(false);
        group.add(rb);
        int oldY = gbc.gridy;
        gbc.gridy = y;
        panel.add(rb, gbc);
        gbc.gridy = oldY;
        return rb;
    }
    private void executeTransaction() {
        try {
            double val = amountInput.getText().isEmpty() ? 0 : Double.parseDouble(amountInput.getText());
            if (rbDepose.isSelected()) {
                selectedAccount.getTransaction().depose(val);
            } else if (rbRetrieve.isSelected()) {
                selectedAccount.getTransaction().retrieve(val);
            } else if (rbTotal.isSelected()) {
                double total = selectedAccount.getTransaction().getTotalAmount();
                JOptionPane.showMessageDialog(this, "Total with Interest: " + total);
            } else if (rbTransfer.isSelected()) {
                Account target = client.getAccount(targetAccInput.getText());
                if(target != null) selectedAccount.getTransaction().transfer(target, val);
            } else if (rbLoan.isSelected()) {
                selectedAccount.loan(val);
            } else if (rbPayLoan.isSelected()) {
                selectedAccount.payLoan(val);
            }
            ClientFileHandler.saveClient(this.client);
            statusLabel.setText("Balance: $" + selectedAccount.getAmount());
            JOptionPane.showMessageDialog(this, "Success! Data saved per client.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}