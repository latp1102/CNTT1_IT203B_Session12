package org.example.b5;

public class Patients {
    private int id;
    private String code;
    private String name;
    private int age;
    private String department;
    private String disease;
    private int admissionDays;

    public Patients(int admissionDays, int age, String code, String department, String disease, int id, String name) {
        this.admissionDays = admissionDays;
        this.age = age;
        this.code = code;
        this.department = department;
        this.disease = disease;
        this.id = id;
        this.name = name;
    }

    public int getAdmissionDays() {
        return admissionDays;
    }

    public void setAdmissionDays(int admissionDays) {
        this.admissionDays = admissionDays;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
