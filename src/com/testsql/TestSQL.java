package com.testsql;

import com.testsql.model.Patient;

public class TestSQL {
    public static void main(String[] args) {
        Patient p = new Patient(true);
        MainForm form = new MainForm();
        form.setVisible(true);
    }
}
