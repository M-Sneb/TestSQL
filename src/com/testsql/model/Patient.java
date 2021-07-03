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

    public Patient(boolean isRandom){
        if(isRandom) generateRandomPatient();

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
}
