package com.example.todolist;

public class CongViec {
    private int IdCV;
    private String TenCV;
    private String DateCV;
    private String TimeCV;



    public CongViec(int idCV, String tenCV, String dateCV, String timeCV)
    {
        IdCV = idCV;
        TenCV = tenCV;
        DateCV = dateCV;
        TimeCV = timeCV;
    }
    public CongViec(int idCV, String dateCV, String timeCV)
    {
        IdCV = idCV;
        DateCV = dateCV;
        TimeCV = timeCV;
    }

    public int getIdCV() {
        return IdCV;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setIdCV(int idCV) {
        IdCV = idCV;
    }

    public void setTenCV(String tenCV) {
        TenCV = tenCV;
    }
    public String getDateCV() {
        return DateCV;
    }

    public void setDateCV(String dateCV) {
        DateCV = dateCV;
    }

    public String getTimeCV() {
        return TimeCV;
    }

    public void setTimeCV(String timeCV) {
        TimeCV = timeCV;
    }
}
