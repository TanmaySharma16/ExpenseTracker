import java.io.*;
import java.util.*;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    private final String FILE_NAME = "expenses.csv";

    public void addExpense(Expense e) {
        expenses.add(e);
        saveToFile();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                pw.println(e.toString());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadFromFile() {
        expenses.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                expenses.add(new Expense(parts[0], Double.parseDouble(parts[1]), parts[2]));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
