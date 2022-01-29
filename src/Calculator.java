import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements Operations, ActionListener {

    static JPanel resultPanel = new JPanel();
    static JPanel btnPanel = new JPanel();


    static JButton[] btns = new JButton[20];
    static JTextArea ta = new JTextArea();
    static JScrollPane sc = new JScrollPane(ta);

    static String[] btnsTitles = {
             "AC", "+/-", "%", "÷",
            "7", "8", "9", "X",
            "4", "5", "6", "+",
            "1", "2", "3", "-",
            "0", ".", "Adv", "="
    };


    public Calculator(){

        setTitle("Calculator");
        setLayout(new GridLayout(2,1));
        btnPanel.setLayout(new GridLayout(5,4));
        resultPanel.setLayout(new GridLayout(1,1));

        ta.setEditable(false);

        resultPanel.add(sc);

        resultPanel.setBackground(Color.RED);
        ta.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        for (int i =0;i<btns.length;i++){
            btns[i] = new JButton(btnsTitles[i]);
            btns[i].addActionListener(this::actionPerformed);
            btnPanel.add(btns[i]);
        }

        getContentPane().add(resultPanel);
        getContentPane().add(btnPanel);



        setSize(250,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }


    public static void main(String[] args) {

        new Calculator();

    }

    @Override
    public double add(double fNum, double sNum) {
        return 0;
    }

    @Override
    public double subtract(double fNum, double sNum) {
        return 0;
    }

    @Override
    public double multiply(double fNum, double sNum) {
        return 0;
    }

    @Override
    public double divide(double fNum, double sNum) {
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String str = ta.getText().toString();

        ta.append(e.getActionCommand());

    }
}
