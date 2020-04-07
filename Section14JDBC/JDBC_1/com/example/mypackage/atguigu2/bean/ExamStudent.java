package com.example.mypackage.atguigu2.bean;

public class ExamStudent {
    private int flowID;
    private int type;
    private String  iDCard;
    private String  examCard;
    private String studentName;
    private String location;
    private int grade;

    public ExamStudent() {
        super();
    }

    public ExamStudent(int flowID, int type, String iDCard, String examCard, String studentName, String location, int grade) {
        this.flowID = flowID;
        this.type = type;
        this.iDCard = iDCard;
        this.examCard = examCard;
        this.studentName = studentName;
        this.location = location;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "========查询结果======== \n" +
                "流水号:      " + flowID + "\n" +
                "四级/六级:   " + type + "\n" +
                "身份证号:    " + iDCard + "\n" +
                "准考证号:    " + examCard + "\n" +
                "学生姓名:    " + studentName + "\n" +
                "区域:        " + location + "\n" +
                "成绩:        " + grade;
    }

    public int getFlowID() {
        return flowID;
    }

    public void setFlowID(int flowID) {
        this.flowID = flowID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getiDCard() {
        return iDCard;
    }

    public void setiDCard(String iDCard) {
        this.iDCard = iDCard;
    }

    public String getExamCard() {
        return examCard;
    }

    public void setExamCard(String examCard) {
        this.examCard = examCard;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
