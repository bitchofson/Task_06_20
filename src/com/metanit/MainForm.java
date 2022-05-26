package com.metanit;

import util.JTableUtils;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.metanit.SimpleHashMap.Entry;

public class MainForm {

    private JPanel MainPanel;
    private JButton ButtonEx;
    private JTable tableFirst;
    private JTable tableSecond;
    private JTextField textField1;


    MainForm() {
        JFrame jfrm = new JFrame();

        jfrm.setTitle("Task_06_20");
        jfrm.setContentPane(MainPanel);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();

        JTableUtils.initJTableForArray(tableFirst, 200, true, true, false, false);
        JTableUtils.initJTableForArray(tableSecond, 200, true, true, false, false);
        tableFirst.setRowHeight(20);
        tableSecond.setRowHeight(20);

        jfrm.pack();
        jfrm.setSize(800, 500);
        jfrm.setVisible(true);

        ButtonEx.addActionListener(e -> {

            File dir = new File(textField1.getText());
            HashMap<String, ArrayList<String>> hashFiles = new HashMap<>();
            SimpleHashMap<String, ArrayList<String>> simpleHashFiles = new SimpleHashMap<>();


            String[] resultFirst = new String[0];
            String[] resultSecond = new String[0];

            try {
                Logic.findFiles(dir, hashFiles);
                Logic.findFiles(dir, simpleHashFiles);

            } catch (Exception exc) {
                exc.printStackTrace();
            }

            System.out.println("Standards Java realization: ");
            for (Map.Entry<String, ArrayList<String>> entry: hashFiles.entrySet())
                System.out.println("Digest(in hex format):: " + entry.getKey() + "\tFile name:: " + entry.getValue());

            System.out.println("Custom realization: ");
            for (Entry<String, ArrayList<String>> entry: simpleHashFiles.entrySet())
                System.out.println("Digest(in hex format):: " + entry.getKey() + "\tFile name:: " + entry.getValue());


            for (Map.Entry<String, ArrayList<String>> entry: hashFiles.entrySet()) {
                if (entry.getValue().size() > 1)
                    resultFirst = entry.getValue().toArray(new String[0]);
            }

            for (Entry<String, ArrayList<String>> entry: simpleHashFiles.entrySet()) {
                if (entry.getValue().size() > 1)
                    resultSecond = entry.getValue().toArray(new String[0]);
            }

            JTableUtils.writeArrayToJTable(tableFirst, resultFirst);
            JTableUtils.writeArrayToJTable(tableSecond, resultSecond);

        });
    }

}
