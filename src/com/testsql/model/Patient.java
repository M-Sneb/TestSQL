package com.testsql.model;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Patient {
    private String id = "";
    private String fam = "";
    private String im = "";
    private String otch = "";
    private LocalDate birthdate = null;
    private Character pol = ' ';
    private String passport = "";
    private String passport_given = "";
    private LocalDate passport_date = null;
    private String phone = "";
    private String address = "";
    private String inn = "";
    private String e_mail = "";
    private String card_number = "";
    private LocalDate reg_date = null;
    private String id_reg = "";
    private String weight = "";
    private String delegates = "";
    private Boolean is_global = false;
    private static ArrayList<String> maleSurnames;
    private static ArrayList<String> femaleSurnames;
    private static ArrayList<String> maleNames;
    private static ArrayList<String> femaleNames;
    private static ArrayList<String> maleFathernames;
    private static ArrayList<String> femaleFathernames;
    private static final String digits = "0123456789";
    private static SecureRandom rnd = new SecureRandom();
    private PatientExtraData patientExtraData;
    private PatientPolicies patientPolicies;
    private PatientTrustee patientTrustee;

    public Patient(boolean isRandom){
        if(isRandom) generateRandomPatient();
        this.patientExtraData = new PatientExtraData(true);
        this.patientPolicies = new PatientPolicies(true);
        this.patientTrustee = new PatientTrustee(true);
    }

    public Patient(Patient patient){
        this.setId(patient.getId());
        this.setFam(patient.getFam());
        this.setIm(patient.getIm());
        this.setOtch(patient.getOtch());
        this.setBirthdate(patient.getBirthdate());
        this.setPol(patient.getPol());
        this.setPassport(patient.getPassport());
        this.setPassport_given(patient.getPassport_given());
        this.setPassport_date(patient.getPassport_date());
        this.setPhone(patient.getPhone());
        this.setAddress(patient.getAddress());
        this.setInn(patient.getInn());
        this.setE_mail(patient.getE_mail());
        this.setCard_number(patient.getCard_number());
        this.setReg_date(patient.getReg_date());
        this.setId_reg(patient.getId_reg());
        this.setWeight(patient.getWeight());
        this.setDelegates(patient.getDelegates());
        this.setIs_global(patient.getIs_global());
        this.patientExtraData = new PatientExtraData(true);
        this.patientPolicies = new PatientPolicies(true);
        this.patientTrustee = new PatientTrustee(true);
    }
    private void generateRandomPatient() {
        Patient.setDict();
        this.setId(UUID.randomUUID().toString());
        this.setPol(Math.random()>0.5?'М':'Ж');
        if(this.getPol() == 'М') {
            this.setFam(maleSurnames.get(new Random().nextInt(maleSurnames.size())));
            this.setIm(maleNames.get(new Random().nextInt(maleNames.size())));
            this.setOtch(maleFathernames.get(new Random().nextInt(maleFathernames.size())));
        }
        else {
            this.setFam(femaleSurnames.get(new Random().nextInt(femaleSurnames.size())));
            this.setIm(femaleNames.get(new Random().nextInt(femaleNames.size())));
            this.setOtch(femaleFathernames.get(new Random().nextInt(femaleFathernames.size())));
        }
        LocalDate startBirthDate = LocalDate.of(1950,1,1);
        long start = startBirthDate.toEpochDay();
        LocalDate endBirthDate = LocalDate.of(1999,12,31);
        long end = endBirthDate.toEpochDay();
        long rnd = ThreadLocalRandom.current().longs(start,end).findAny().getAsLong();
        this.setBirthdate(LocalDate.ofEpochDay(rnd));
        this.setPassport(getRndPassport());
    }

    private String getRndPassport(){
        String passportNumber;
        passportNumber = randomDigitString(4) + " №" + randomDigitString(6);
        return passportNumber;
    }

    private String randomDigitString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( digits.charAt( rnd.nextInt(digits.length()) ) );
        return sb.toString();
    }

    public static void setDict(){
        try {
            maleSurnames = new ArrayList(Files.readAllLines(Path.of("resources/MaleSurnames.txt"), StandardCharsets.UTF_8));
            maleNames = new ArrayList(Files.readAllLines(Path.of("resources/MaleNames.txt"), StandardCharsets.UTF_8));
            maleFathernames = new ArrayList(Files.readAllLines(Path.of("resources/MaleFathernames.txt"), StandardCharsets.UTF_8));
            femaleNames = new ArrayList(Files.readAllLines(Path.of("resources/FemaleNames.txt"), StandardCharsets.UTF_8));
            femaleFathernames = new ArrayList(Files.readAllLines(Path.of("resources/FemaleFathernames.txt"), StandardCharsets.UTF_8));
        }
        catch (Exception e){}
        if(maleSurnames != null){
            femaleSurnames = new ArrayList<>();
            for (String surname:maleSurnames) {
                femaleSurnames.add(surname + "а");
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if(this.patientExtraData != null) this.patientExtraData.setPatientID(id);
        if(this.patientPolicies != null) this.patientPolicies.setIdPatient(id);
        if(this.patientTrustee != null)this.patientTrustee.setIdPatient(id);
    }

    public String getFam() {
        return fam;
    }

    public void setFam(String fam) {
        this.fam = fam;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getOtch() {
        return otch;
    }

    public void setOtch(String otch) {
        this.otch = otch;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Character getPol() {
        return pol;
    }

    public void setPol(Character pol) {
        this.pol = pol;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassport_given() {
        return passport_given;
    }

    public void setPassport_given(String passport_given) {
        this.passport_given = passport_given;
    }

    public LocalDate getPassport_date() {
        return passport_date;
    }

    public void setPassport_date(LocalDate passport_date) {
        this.passport_date = passport_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public LocalDate getReg_date() {
        return reg_date;
    }

    public void setReg_date(LocalDate reg_date) {
        this.reg_date = reg_date;
    }

    public String getId_reg() {
        return id_reg;
    }

    public void setId_reg(String id_reg) {
        this.id_reg = id_reg;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDelegates() {
        return delegates;
    }

    public void setDelegates(String delegates) {
        this.delegates = delegates;
    }

    public Boolean getIs_global() {
        return is_global;
    }

    public void setIs_global(Boolean is_global) {
        this.is_global = is_global;
    }

    public PatientExtraData getPatientExtraData() {
        return patientExtraData;
    }

    public PatientPolicies getPatientPolicies() {
        return patientPolicies;
    }

    public PatientTrustee getPatientTrustee() {
        return patientTrustee;
    }

    public class PatientExtraData{
        private String itemID = "";
        private String patientID = "";
        private int terrainType = -1;
        private String snils = "";
        private int benefitCategory = -1;
        private int familyStatus = -1;
        private int educationType = -1;
        private int employmentStatus = -1;
        private int disabilityStatus = -1;
        private int disabilityGroup = -1;
        private LocalDate disabilityDate = null;
        private String workPlace = "";
        private String workPlaceChanging = "";
        private String addressChanging = "";
        private int bloodType = -1;
        private int bloodFactor = -1;
        private String allergicReactions = "";
        private LocalDate updateTime = null;
        private int updateUser = 359;

        public PatientExtraData(boolean isRandom) {
            this.patientID = Patient.this.id;
            if(isRandom) generateRandomExtraData();
        }

        private void generateRandomExtraData() {
            this.setItemID(UUID.randomUUID().toString());
            this.setSnils(getRndSnils());
        }

        private String getRndSnils(){
            String snils;
            snils = randomDigitString(3) + "-" + randomDigitString(3) + "-" + randomDigitString(3) + " " + randomDigitString(2);
            return snils;
        }

        public void setPatientID(String patientID) {
            this.patientID = patientID;
        }
        public String getItemID() {
            return itemID;
        }

        public void setItemID(String itemID) {
            this.itemID = itemID;
        }

        public String getPatientID() {
            return patientID;
        }

        public int getTerrainType() {
            return terrainType;
        }

        public void setTerrainType(int terrainType) {
            this.terrainType = terrainType;
        }

        public String getSnils() {
            return snils;
        }

        public void setSnils(String snils) {
            this.snils = snils;
        }

        public int getBenefitCategory() {
            return benefitCategory;
        }

        public void setBenefitCategory(int benefitCategory) {
            this.benefitCategory = benefitCategory;
        }

        public int getFamilyStatus() {
            return familyStatus;
        }

        public void setFamilyStatus(int familyStatus) {
            this.familyStatus = familyStatus;
        }

        public int getEducationType() {
            return educationType;
        }

        public void setEducationType(int educationType) {
            this.educationType = educationType;
        }

        public int getEmploymentStatus() {
            return employmentStatus;
        }

        public void setEmploymentStatus(int employmentStatus) {
            this.employmentStatus = employmentStatus;
        }

        public int getDisabilityStatus() {
            return disabilityStatus;
        }

        public void setDisabilityStatus(int disabilityStatus) {
            this.disabilityStatus = disabilityStatus;
        }

        public int getDisabilityGroup() {
            return disabilityGroup;
        }

        public void setDisabilityGroup(int disabilityGroup) {
            this.disabilityGroup = disabilityGroup;
        }

        public LocalDate getDisabilityDate() {
            return disabilityDate;
        }

        public void setDisabilityDate(LocalDate disabilityDate) {
            this.disabilityDate = disabilityDate;
        }

        public String getWorkPlace() {
            return workPlace;
        }

        public void setWorkPlace(String workPlace) {
            this.workPlace = workPlace;
        }

        public String getWorkPlaceChanging() {
            return workPlaceChanging;
        }

        public void setWorkPlaceChanging(String workPlaceChanging) {
            this.workPlaceChanging = workPlaceChanging;
        }

        public String getAddressChanging() {
            return addressChanging;
        }

        public void setAddressChanging(String addressChanging) {
            this.addressChanging = addressChanging;
        }

        public int getBloodType() {
            return bloodType;
        }

        public void setBloodType(int bloodType) {
            this.bloodType = bloodType;
        }

        public int getBloodFactor() {
            return bloodFactor;
        }

        public void setBloodFactor(int bloodFactor) {
            this.bloodFactor = bloodFactor;
        }

        public String getAllergicReactions() {
            return allergicReactions;
        }

        public void setAllergicReactions(String allergicReactions) {
            this.allergicReactions = allergicReactions;
        }

        public LocalDate getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(LocalDate updateTime) {
            this.updateTime = updateTime;
        }

        public int getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(int updateUser) {
            this.updateUser = updateUser;
        }
    }

    public class PatientPolicies{
        private String id = "";
        private String idPatient = "";
        private int policyType = 1;
        private String policy_number = "";
        private int id_enterprise = -1;
        private LocalDate dateBegin = null;
        private LocalDate dateEnd = null;
        private boolean isActive = true;
        private boolean isGlobal = false;
        private String empLastModified = "";
        private LocalDate dateLastModified = null;

        public PatientPolicies(boolean isRandom) {
            this.idPatient = Patient.this.id;
            if(isRandom) generateRandomPatientPolicies();
        }

        private void generateRandomPatientPolicies() {
            this.setId(UUID.randomUUID().toString());
            this.setPolicy_number(getRndPoliciesNumber());
        }
        private String getRndPoliciesNumber(){
            String pNumber;
            pNumber = randomDigitString(16);
            return pNumber;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setIdPatient(String idPatient) {
            this.idPatient = idPatient;
        }

        public String getIdPatient() {
            return idPatient;
        }

        public int getPolicyType() {
            return policyType;
        }

        public void setPolicyType(int policyType) {
            this.policyType = policyType;
        }

        public String getPolicy_number() {
            return policy_number;
        }

        public void setPolicy_number(String policy_number) {
            this.policy_number = policy_number;
        }

        public int getId_enterprise() {
            return id_enterprise;
        }

        public void setId_enterprise(int id_enterprise) {
            this.id_enterprise = id_enterprise;
        }

        public LocalDate getDateBegin() {
            return dateBegin;
        }

        public void setDateBegin(LocalDate dateBegin) {
            this.dateBegin = dateBegin;
        }

        public LocalDate getDateEnd() {
            return dateEnd;
        }

        public void setDateEnd(LocalDate dateEnd) {
            this.dateEnd = dateEnd;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public boolean isGlobal() {
            return isGlobal;
        }

        public void setGlobal(boolean global) {
            isGlobal = global;
        }

        public String getEmpLastModified() {
            return empLastModified;
        }

        public void setEmpLastModified(String empLastModified) {
            this.empLastModified = empLastModified;
        }

        public LocalDate getDateLastModified() {
            return dateLastModified;
        }

        public void setDateLastModified(LocalDate dateLastModified) {
            this.dateLastModified = dateLastModified;
        }
    }

    public class PatientTrustee{
        private String id  = "";
        private String idPatient = "";
        private String siblingDegree = "";
        private String fam = "";
        private String im = "";
        private String otch = "";
        private LocalDate birthday = null;
        private String phone = "";
        private String address = "";
        private String inn = "";
        private String passport = "";
        private boolean isGlobal = false;
        private LocalDate empLastModified = null;
        private LocalDate dateLastModified = null;

        public PatientTrustee(boolean isRandom) {
            this.idPatient = Patient.this.getId();
            if(isRandom) generateRandomPatientTrustee();
        }

        private void generateRandomPatientTrustee() {
            this.setId(UUID.randomUUID().toString());
            int Sex = Math.random() > 0.5 ? 0 : 1;
            if(Sex == 0) {
                this.setFam(maleSurnames.get(new Random().nextInt(maleSurnames.size())));
                this.setIm(maleNames.get(new Random().nextInt(maleNames.size())));
                this.setOtch(maleFathernames.get(new Random().nextInt(maleFathernames.size())));
            }
            else {
                this.setFam(femaleSurnames.get(new Random().nextInt(femaleSurnames.size())));
                this.setIm(femaleNames.get(new Random().nextInt(femaleNames.size())));
                this.setOtch(femaleFathernames.get(new Random().nextInt(femaleFathernames.size())));
            }

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdPatient() {
            return idPatient;
        }

        public void setIdPatient(String idPatient) {
            this.idPatient = idPatient;
        }

        public String getSiblingDegree() {
            return siblingDegree;
        }

        public void setSiblingDegree(String siblingDegree) {
            this.siblingDegree = siblingDegree;
        }

        public String getFam() {
            return fam;
        }

        public void setFam(String fam) {
            this.fam = fam;
        }

        public String getIm() {
            return im;
        }

        public void setIm(String im) {
            this.im = im;
        }

        public String getOtch() {
            return otch;
        }

        public void setOtch(String otch) {
            this.otch = otch;
        }

        public LocalDate getBirthday() {
            return birthday;
        }

        public void setBirthday(LocalDate birthday) {
            this.birthday = birthday;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getInn() {
            return inn;
        }

        public void setInn(String inn) {
            this.inn = inn;
        }

        public String getPassport() {
            return passport;
        }

        public void setPassport(String passport) {
            this.passport = passport;
        }

        public boolean isGlobal() {
            return isGlobal;
        }

        public void setGlobal(boolean global) {
            isGlobal = global;
        }

        public LocalDate getEmpLastModified() {
            return empLastModified;
        }

        public void setEmpLastModified(LocalDate empLastModified) {
            this.empLastModified = empLastModified;
        }

        public LocalDate getDateLastModified() {
            return dateLastModified;
        }

        public void setDateLastModified(LocalDate dateLastModified) {
            this.dateLastModified = dateLastModified;
        }
    }
}
