package com.testsql.dao;


import com.testsql.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientDao {
    private Connection con;

    public PatientDao(Connection con){
        this.con = con;
    }

    public void add(ArrayList<Patient> patients) throws SQLException {
        con.setAutoCommit(false);
        try(PreparedStatement prst = con.prepareStatement("INSERT INTO dbo.Patient(id, fam, im , otch, birthday, pol, passport) VALUES (?,?,?,?,?,?,?)")) {
            for (Patient p: patients) {
                prst.setString(1, p.getId());
                prst.setString(2, p.getFam());
                prst.setString(3, p.getIm());
                prst.setString(4, p.getOtch());
                prst.setString(5, p.getBirthdate().toString());
                prst.setString(6, p.getPol().toString());
                prst.setString(7, p.getPassport());
                prst.addBatch();
                try{
                    prst.executeBatch();
                    con.commit();
                }
                catch (SQLException e){
                    con.rollback();
                }
            }

        }
    }
}
