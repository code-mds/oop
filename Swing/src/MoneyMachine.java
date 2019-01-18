import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class MoneyMachine {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MoneyMachine().runApp());
    }

    static final String[] values ={ "5.00", "2.00", "1.00", "0.50", "0.20", "0.10" };

    private JTextField userField = new JTextField();
    private JTextField machineField = new JTextField();

    BigDecimal userAmount = new BigDecimal(39.9);
    BigDecimal machineAmount = BigDecimal.ZERO;

    void runApp() {
        JFrame frame = new JFrame(getClass().getName());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setLayout(new GridLayout(0,2));
        showAmount();
        frame.add(userField);

        machineField.setEnabled(false);
        frame.add(machineField);

        for (String val : values) {
            JButton userButton = new JButton(val);
            userButton.addActionListener((e) -> {
                BigDecimal buttonValue = BigDecimal.valueOf(Double.valueOf(val));
                BigDecimal newAmount = userAmount.subtract(buttonValue);
                if(newAmount.compareTo(BigDecimal.ZERO) < 0)
                    return;

                userAmount = newAmount;
                machineAmount = machineAmount.add(buttonValue);
                showAmount();
            } );

            JButton machineButton = new JButton(val);
            machineButton.addActionListener((e) -> {
                BigDecimal buttonValue = BigDecimal.valueOf(Double.valueOf(val));
                BigDecimal newAmount = machineAmount.subtract(buttonValue);
                if(newAmount.compareTo(BigDecimal.ZERO) < 0)
                    return;

                machineAmount = newAmount;
                userAmount = userAmount.add(buttonValue);
                showAmount();
            } );

            frame.add(userButton);
            frame.add(machineButton);
        }


        frame.pack();
        frame.setVisible(true);
    }

    private void showAmount() {
        userField.setText(String.format("%.2f", userAmount));
        machineField.setText(String.format("%.2f", machineAmount));
    }

}
