package mert;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Mert Yacan
 */
public class Assignment11APP implements ActionListener {
    static JFrame window;
    static JPanel fileP, resultP, statusP;
    static JTextField fileLoc;
    static JLabel statusText, vowel, consonant;
    static JButton go;
    static ArrayList<String> list = new ArrayList<String>();
    static String filePath = "src/mert/default.txt";
    static File file = new File(filePath);
    static int vowelNum;
    static int consonantNum;
    
    public void start(){
        window = new JFrame("Vowel-Consonant Counter");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fileP = new JPanel(new FlowLayout());
        fileLoc = new JTextField("default.txt");
        fileLoc.setPreferredSize(new Dimension(200,30));
        fileP.setPreferredSize(new Dimension(450, 60));
        go = new JButton("Analyse");
        go.setPreferredSize(new Dimension(200,30));
	go.addActionListener(this);
        fileP.add(fileLoc);
        fileP.add(go);
        resultP = new JPanel(new BorderLayout());
        vowel = new JLabel("number of Vowels in the text", SwingConstants.CENTER);
        consonant = new JLabel("number of Consonants in the text", SwingConstants.CENTER);
        resultP.add(vowel, BorderLayout.NORTH);
        resultP.add(consonant, BorderLayout.SOUTH);
        resultP.setPreferredSize(new Dimension(450,40));
        statusP = new JPanel(new BorderLayout());
        statusText = new JLabel("         Ready to go!", SwingConstants.LEFT);
        statusP.add(statusText, BorderLayout.CENTER);
        
        window.add(resultP, BorderLayout.CENTER);
        window.add(fileP, BorderLayout.NORTH);
        window.add(statusP, BorderLayout.SOUTH);
        
        window.pack();
	window.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        statusText.setText("         Working!");
        String loc = fileLoc.getText();
        filePath = "src/mert/" + loc;
        file = new File(filePath);
        
        //calculataions
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                list.add(data);
                System.out.println(data);
            }
            inputStream.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Assignment11APP.class
                    .getName()).log(Level.SEVERE, null, ex);
        }


        //Checking our collected data
        for (String r : list) {
            r = r.toLowerCase();
            for (int i = 0; i < r.length(); i++) {
                char ch = r.charAt(i);
                if (ch == 'a' || ch == 'e' || ch == 'i'
                        || ch == 'o' || ch == 'u') {
                    ++vowelNum;
                } else if ((ch >= 'a' && ch <= 'z')) {
                    ++consonantNum;
                }
            }

        }
        vowel.setText(vowelNum + "     number of Vowels in the text");
        consonant.setText(consonantNum + "     number of Consonants in the text");
        statusText.setText("         Complete!");
        window.repaint();
    }

    public static void main(String[] args){
	Assignment11APP program = new Assignment11APP();
        program.start();
    }

}
