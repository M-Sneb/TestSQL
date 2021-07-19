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
        PreparedStatement patientStatement = con.prepareStatement("INSERT INTO dbo.Patient(id, fam, im , otch, birthday, pol, passport) VALUES (?,?,?,?,?,?,?)");
        PreparedStatement patExtraStatement = con.prepareStatement("INSERT INTO dbo.PatientExtraData(ItemID, PatientID, SNILS, UpdateUser) VALUES (?,?,?,?)");
        PreparedStatement patPoliciesStatement = con.prepareStatement("INSERT INTO dbo.Patient_policies(id, id_patient, policy_number) VALUES (?,?,?)");
        PreparedStatement patTrusteeStatement = con.prepareStatement("INSERT INTO dbo.Patient_trustee(id, id_patient, fam, im, otch) VALUES (?,?,?,?,?)");
        try {
            for (Patient p: patients) {
                patientStatement.setString(1, p.getId());
                patientStatement.setString(2, p.getFam());
                patientStatement.setString(3, p.getIm());
                patientStatement.setString(4, p.getOtch());
                patientStatement.setString(5, p.getBirthdate().toString());
                patientStatement.setString(6, p.getPol().toString());
                patientStatement.setString(7, p.getPassport());
                patientStatement.addBatch();

                patExtraStatement.setString(1, p.getPatientExtraData().getItemID());
                patExtraStatement.setString(2, p.getPatientExtraData().getPatientID());
                patExtraStatement.setString(3, p.getPatientExtraData().getSnils());
                patExtraStatement.setInt(4, p.getPatientExtraData().getUpdateUser());
                patExtraStatement.addBatch();

                patPoliciesStatement.setString(1, p.getPatientPolicies().getId());
                patPoliciesStatement.setString(2, p.getPatientPolicies().getIdPatient());
                patPoliciesStatement.setString(3, p.getPatientPolicies().getPolicy_number());
                patPoliciesStatement.addBatch();

                patTrusteeStatement.setString(1, p.getPatientTrustee().getId());
                patTrusteeStatement.setString(2, p.getPatientTrustee().getIdPatient());
                patTrusteeStatement.setString(3, p.getPatientTrustee().getFam());
                patTrusteeStatement.setString(4, p.getPatientTrustee().getIm());
                patTrusteeStatement.setString(5, p.getPatientTrustee().getOtch());
                patTrusteeStatement.addBatch();
            }
                try{
                    patientStatement.executeBatch();
                    patExtraStatement.executeBatch();
                    patPoliciesStatement.executeBatch();
                    patTrusteeStatement.executeBatch();
                    con.commit();
                }
                catch (SQLException e){
                    System.out.println(e);
                    con.rollback();
                }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
