package in.co.codeplanet.spring_boot.bean;

public class Student {

    private int rollNo;
    private String name;
    private String collageName;
    private double percentage;
    private String fatherName;

    public Student(int rollNo, String name, String collageName, double percentage, String fatherName) {
        this.rollNo = rollNo;
        this.name = name;
        this.collageName = collageName;
        this.percentage = percentage;
        this.fatherName = fatherName;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollageName() {
        return collageName;
    }

    public void setCollageName(String collageName) {
        this.collageName = collageName;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
}
