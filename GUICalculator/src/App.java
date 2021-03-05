import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.EventQueue;
// import javax.swing.ImageIcon;

public class App extends JFrame {

    // Inheritance - use existing functionality, and add more - is a relationship 
    // Composition - use many objects together
    private JPanel panel1;

    private JLabel text;
     // made all of the buttons for the calculator
    private JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bc;
    private JButton negate,sqrt,add,subtract,multiply,divide,equals;
    private static ArrayList<String> functions = new ArrayList<String>(Arrays.asList("-","+","x","÷"));
    private boolean firstTime = true;

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.setVisible(true);

        // EventQueue.invokeLater(() -> {
        //     var icon = new FrameIconEx();
        //     icon.setVisible(true);
        // });
    }
    
    public App() {
        JFrame frame = new JFrame("Nandini-Breggin's Calculator :)");
        setSize(1000, 1000);
        setLocationRelativeTo(null); // center window on screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container myPanel = frame.getContentPane();
        GroupLayout group1 = new GroupLayout(myPanel);

        group1.setAutoCreateGaps(true);
        group1.setAutoCreateContainerGaps(true);

        myPanel.setSize(1000, 1000);
        myPanel.setLayout(group1);


        // var webIcon = new ImageIcon("src/Calculator.png");
        // setIconImage(webIcon.getImage());


        text = new JLabel("", SwingConstants.CENTER);
        text.setFont(new Font("Monospace",Font.ITALIC, 20));
        text.setBounds(20,20,getBounds().width * 20,10);
        // the number buttons
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        b0 = new JButton("0");
        // the function buttons
        bc = new JButton("clear"); // clear button
        sqrt = new JButton("√"); // square root
        add = new JButton("+");
        subtract = new JButton("-");
        multiply = new JButton("x");
        divide = new JButton("÷");
        equals = new JButton("=");
        negate = new JButton("+/-"); // negate -> makes value negative or positive

        ArrayList<JButton> buttons = new ArrayList<>(Arrays.asList(b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,sqrt,divide,multiply,add,subtract));

        // formatting the buttons
        group1.setHorizontalGroup(group1.createSequentialGroup()
            .addComponent(text)
            .addGroup(group1.createParallelGroup(Alignment.LEADING).addComponent(b1).addComponent(b4).addComponent(b7).addComponent(b0))
            .addGroup(group1.createParallelGroup().addComponent(b2).addComponent(b5).addComponent(b8).addComponent(sqrt))
            .addGroup(group1.createParallelGroup().addComponent(negate).addComponent(b3).addComponent(b6).addComponent(b9).addComponent(bc))
            .addGroup(group1.createParallelGroup().addComponent(divide).addComponent(multiply).addComponent(add).addComponent(subtract).addComponent(equals)));


        group1.setVerticalGroup(group1.createSequentialGroup()
            .addGroup(group1.createParallelGroup(Alignment.BASELINE).addComponent(text).addComponent(negate).addComponent(divide))
            .addGroup(group1.createParallelGroup(Alignment.BASELINE).addComponent(b1).addComponent(b2).addComponent(b3).addComponent(multiply))
            .addGroup(group1.createParallelGroup(Alignment.BASELINE).addComponent(b4).addComponent(b5).addComponent(b6).addComponent(add))
            .addGroup(group1.createParallelGroup(Alignment.BASELINE).addComponent(b7).addComponent(b8).addComponent(b9).addComponent(subtract))
            .addGroup(group1.createParallelGroup().addComponent(b0).addComponent(sqrt).addComponent(bc).addComponent(equals)));

        frame.pack();
        frame.setVisible(true);

        for (int i = 0; i < buttons.size(); i++) {

            final Integer inner = new Integer(i); // value can't be changed

            buttons.get(i).addActionListener(new ActionListener() {
                @Override

                public void actionPerformed(ActionEvent e) {
                    if (!text.getText().equals("")) {
                        String[] text_split = text.getText().split(" ");
                        System.out.println(text_split[text_split.length-1]);

                        if (!functions.contains(text_split[text_split.length-1])) {
                            if (!functions.contains(buttons.get(inner).getText())) {
                                if (text_split.length > 1 || firstTime) {

                            
                                    text.setText(text.getText() + buttons.get(inner).getText());
                                }
                            
                            } else {
                                text.setText(text.getText() + " " + buttons.get(inner).getText() + " ");
                            }
                                
                        } else {
                            if (!functions.contains(buttons.get(inner).getText())) {
                                text.setText(text.getText() + buttons.get(inner).getText());
                            }
                        }
                        
                    } else {
                        text.setText(buttons.get(inner).getText());
                    }
                
                }
            });
        }

        equals.addActionListener(new ActionListener() {
            // equals
            @Override
            public void actionPerformed(ActionEvent e){ 
                firstTime = false;

                text.setText(Double.toString(parseSolve(text.getText())));
            }
        });

        bc.addActionListener(new ActionListener() {
            // clear
            @Override
            public void actionPerformed(ActionEvent e){ 
                firstTime = true;
                text.setText("");
            }
        });

        sqrt.addActionListener(new ActionListener() {
            // Square root
            @Override
            public void actionPerformed(ActionEvent e){ 
                firstTime = false;

                text.setText(Double.toString(Math.sqrt(parseSolve(text.getText()))));
            }
        });

        negate.addActionListener((event)-> {
            // negate -> make negative or positive
                firstTime = false;

                text.setText(Double.toString(-1 * parseSolve(text.getText())));
                    // this takes the value and muliplies it by -1, to negate it
           
        });
       

        panel1.setLayout(null);
        panel1.setLocation(50,50);

    }

    // doing commands for the calculator
    private static double parseSolve(String s) {
        double final_result = 0;
        String[] parsed = s.split(" ");
        ArrayList<String> parsedCommands = new ArrayList<String>();
        
        
        for (int i = 1; i < parsed.length; i += 2) {
            parsedCommands.add(parsed[i]);
        }

        System.out.println(Arrays.toString(parsed));

        System.out.println(parsedCommands);
        int x = 2;

        final_result = Double.parseDouble(parsed[0]);

        for (String op : parsedCommands) {
            if (op.equals("-")) {
                final_result -= Double.parseDouble(parsed[x]);
            } else if (op.equals("+")) {
                final_result += Double.parseDouble(parsed[x]);
            } else if (op.equals("x")) {
                final_result *= Double.parseDouble(parsed[x]);
            } else if (op.equals("÷")) {
                final_result /= Double.parseDouble(parsed[x]);
            }

            x += 2;

        }

        return final_result;
    }

}