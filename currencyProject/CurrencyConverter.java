package currencyProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class CurrencyConverter extends JFrame implements ActionListener{

    private JPanel mainPanel = new JPanel();
    private JPanel containerPanel = new JPanel();
    private JPanel tablePanel = new JPanel();

    private JLabel currencyOneLabel = new JLabel("Currency One");
    private JLabel currencyTwoLabel = new JLabel("Currency Two");

    private JLabel amountLabel = new JLabel("Amount: ");
    private JTextField amountTF = new JTextField(10);



    private JComboBox currency1, currency2;
    private JButton convertBtn = new JButton("Convert");
    private JButton exitBtn = new JButton("Exit");

    private String[] currencyNames = {
       "eur", "usd", "gbp"
    };

    private ArrayList<String> file_data = new ArrayList<>();
    private JComboBox options;

    public CurrencyConverter(){


        setTitle("Currency Converter");

        mainPanel.setLayout(new BorderLayout());

        setPanel();

        add(mainPanel, BorderLayout.NORTH);
        setSize(300,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void setPanel(){

        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        containerPanel.setLayout(new GridLayout(4, 2));
        containerPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        currency1 = new JComboBox(currencyNames);
        currency2 = new JComboBox(currencyNames);


        containerPanel.add(amountLabel);
        containerPanel.add(amountTF);
        containerPanel.add(currencyOneLabel);
        containerPanel.add(currency1);
        containerPanel.add(currencyTwoLabel);
        containerPanel.add(currency2);
        containerPanel.add(exitBtn);
        containerPanel.add(convertBtn);

        exitBtn.addActionListener(this);

        mainPanel.add(containerPanel, BorderLayout.CENTER);

        file_data = readFile();
        options = new JComboBox(file_data.toArray());

        convertBtn.addActionListener(this);

        tablePanel.setBorder(BorderFactory.createTitledBorder("Conversion Table"));
        tablePanel.add(options);
        mainPanel.add(tablePanel, BorderLayout.SOUTH);


    }



    private ArrayList<String> readFile(){

        ArrayList<String> currency = new ArrayList<>();

        try {

            Scanner scanner = new Scanner(new File("src/currencyProject/currency.txt"));

            while (scanner.hasNext()){

                String line = scanner.nextLine();
                currency.add(line);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }


        return currency;
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String currency_1 = currency1.getSelectedItem().toString();
        String currency_2 = currency2.getSelectedItem().toString();

        String amount = amountTF.getText().toString();

        if (!amount.isEmpty()){

            getConversion(currency_1, currency_2);

        }


        if (e.getSource()==exitBtn){
            System.exit(0);
        }

    }
    private String getCurrencySymbol(String currency){

        String symbol = "";

        switch (currency){
            case "usd":
                symbol = "$";
                break;
            case "eur":
                symbol = "€";
                break;

            case "gbp":
                symbol = "£";
                break;


        }

        return symbol;
    }

    private void getConversion(String cur1, String cur2){

        DecimalFormat df = new DecimalFormat("#,###.00");

        double amount = Double.parseDouble(amountTF.getText());


        switch (cur1){
            case "usd":

                String usd_to_eur = String.valueOf(amount * .88);
                String usd_to_gbp = String.valueOf(amount * 0.75);

                if (cur2.equalsIgnoreCase("eur")){
                    JOptionPane.showMessageDialog(null,
                            getCurrencySymbol(cur1) + amount + " converted to " +
                                     getCurrencySymbol(cur2) + " is:\n"
                                    +getCurrencySymbol(cur2) + usd_to_eur);
                }

                if (cur2.equalsIgnoreCase("gbp")){
                    JOptionPane.showMessageDialog(null,
                            getCurrencySymbol(cur1) + amount + " converted to " +
                                    getCurrencySymbol(cur2) + " is:\n"
                                    +getCurrencySymbol(cur2) + usd_to_gbp);
                }


                break;

            case "eur":

                String eur_to_usd = String.valueOf(amount * 1.13);
                String eur_to_gbp = String.valueOf(amount * 0.85);

                if (cur2.equalsIgnoreCase("usd")){
                    JOptionPane.showMessageDialog(null,
                            getCurrencySymbol(cur1) + amount + " converted to " +
                                    getCurrencySymbol(cur2) + " is:\n"
                                    +getCurrencySymbol(cur2) + eur_to_usd);
                }

                if (cur2.equalsIgnoreCase("gbp")){
                    JOptionPane.showMessageDialog(null,
                            getCurrencySymbol(cur1) + amount + " converted to " +
                                    getCurrencySymbol(cur2) + " is:\n"
                                    +getCurrencySymbol(cur2) + eur_to_gbp);
                }


                break;

            case "gbp":

                String gbp_to_usd = String.valueOf(amount * 1.34);
                String gbp_to_eur = String.valueOf(amount * 1.18);

                if (cur2.equalsIgnoreCase("usd")){
                    JOptionPane.showMessageDialog(null,
                            getCurrencySymbol(cur1) + amount + " converted to " +
                                    getCurrencySymbol(cur2) + " is:\n"
                                    +getCurrencySymbol(cur2) + gbp_to_usd);
                }

                if (cur2.equalsIgnoreCase("eur")){
                    JOptionPane.showMessageDialog(null,
                            getCurrencySymbol(cur1) + amount + " converted to " +
                                    getCurrencySymbol(cur2) + " is:\n"
                                    +getCurrencySymbol(cur2) + gbp_to_eur);
                }


                break;
        }

    }
}
