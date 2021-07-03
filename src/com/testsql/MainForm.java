package com.testsql;

import com.testsql.dao.PatientDao;
import com.testsql.model.Patient;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainForm extends JFrame {
    private JTextField tfConnString1;
    private JButton btnConnect;
    private JPanel mainPanel;
    private JTextField tfConnString2;
    private JTextField tfQantity;
    private JTextField tfIntersection;
    private JButton btnGenerate;
    private static PatientDao pDao1;
    private static PatientDao pDao2;
    private static Connection con1;
    private static Connection con2;

    public MainForm() throws HeadlessException {
        super("TestSQL");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strConnection1 = tfConnString1.getText();
                String strConnection2 = tfConnString2.getText();
                try  {
                    con1 = DriverManager.getConnection(strConnection1);
                    con2 = DriverManager.getConnection(strConnection2);
                    JOptionPane.showMessageDialog(null, "Подключение выполнено", "TestSQL", JOptionPane.PLAIN_MESSAGE);
                    pDao1 = new PatientDao(con1);
                    pDao2 = new PatientDao(con2);
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(null, "Подключение не выполнено", "TestSQL", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        btnGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Patient> patients = new ArrayList();
                int db2Writen = 0;
                for (int n = 0; n <= Integer.parseInt(tfQantity.getText()); n += 100) {
                    for (int i = 0; i < 100; i++) {
                        patients.add(new Patient(true));
                    }
                    if (pDao1 != null && pDao2 != null) {
                        try {
                            pDao1.add(patients);
                            if (Math.random() <= Double.parseDouble(tfIntersection.getText())) {
                                pDao2.add(patients);
                                db2Writen += 100;
                            }
                            patients.clear();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
                for (int n = 0; n <= Integer.parseInt(tfQantity.getText()) - db2Writen; n += 100) {
                    for (int i = 0; i < 100; i++) {
                        patients.add(new Patient(true));
                    }
                    if (pDao2 != null) {
                        try {
                            pDao2.add(patients);
                            patients.clear();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 5, new Insets(10, 10, 10, 10), -1, -1));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tfConnString1 = new JTextField();
        tfConnString1.setColumns(3);
        tfConnString1.setText("jdbc:sqlserver://127.0.0.1\\SQLEXPRESS:1433;database=OdcManage;user=OdcAdmin;password=OdcManager1!");
        mainPanel.add(tfConnString1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        btnConnect = new JButton();
        btnConnect.setText("Подключиться");
        mainPanel.add(btnConnect, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(159, 30), null, 0, false));
        tfConnString2 = new JTextField();
        tfConnString2.setColumns(3);
        tfConnString2.setText("jdbc:sqlserver://127.0.0.1\\SQLEXPRESS:1433;database=IvnManage;user=OdcAdmin;password=OdcManager1!");
        mainPanel.add(tfConnString2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 8), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Подключение 1");
        mainPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Подключение 2");
        mainPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(87, 11), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Количество");
        mainPanel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tfQantity = new JTextField();
        tfQantity.setText("50000");
        mainPanel.add(tfQantity, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(66, 30), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Пересечение");
        mainPanel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(28, 16), null, 0, false));
        btnGenerate = new JButton();
        btnGenerate.setText("Генерировать");
        mainPanel.add(btnGenerate, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(28, 30), null, 0, false));
        tfIntersection = new JTextField();
        tfIntersection.setText("0.3");
        mainPanel.add(tfIntersection, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(47, 30), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
