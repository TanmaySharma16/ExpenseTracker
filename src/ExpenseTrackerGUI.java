import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExpenseTrackerGUI extends JFrame {
    private ExpenseManager manager = new ExpenseManager();
    private JTextField categoryField, amountField, dateField;
    private JTextArea displayArea;

    public ExpenseTrackerGUI() {
        setTitle("Expense Tracker");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        categoryField = new JTextField(10);
        amountField = new JTextField(10);
        dateField = new JTextField(10);
        JButton addButton = new JButton("Add Expense");
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        add(new JLabel("Category:"));
        add(categoryField);
        add(new JLabel("Amount:"));
        add(amountField);
        add(new JLabel("Date (YYYY-MM-DD):"));
        add(dateField);
        add(addButton);
        add(new JScrollPane(displayArea));

        manager.loadFromFile();
        refreshDisplay();

        addButton.addActionListener(e -> {
            String category = categoryField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String date = dateField.getText();
            manager.addExpense(new Expense(category, amount, date));
            refreshDisplay();
        });
    }

    private void refreshDisplay() {
        displayArea.setText("");
        for (Expense e : manager.getExpenses()) {
            displayArea.append(e.getCategory() + " - ₹" + e.getAmount() + " on " + e.getDate() + "\n");
        }
    }
}
