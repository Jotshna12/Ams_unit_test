package com.example.AMS.Entities;



import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name = "Attendance")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AttendanceEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "student_ID")
    private int studentId;
    @Column(name = "teacher_ID")
    private int teacherId;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "isPresent")
    private boolean IsPresent;
    @Column(name = "class_ID")
    private String classId;
    @Column(name = "hours")
    private int hours;
    @Column(name = "delid")
    private int delegationId;
//    @OneToOne(mappedBy = "Attendance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private LeaveEntity leaveEntity;

    public AttendanceEntity(){

    }
    public AttendanceEntity(long id, int studentId,int teacherId,LocalDate date,boolean IsPresent, String classId,int hours, int delid){
        this.id = id;
        this.studentId =studentId;
        this.teacherId =teacherId;
        this.date = date;
        this.IsPresent = IsPresent;
        this.classId = classId;
        this.delegationId = delid;
        this.hours =8;


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getDelegationId() {
        return delegationId;
    }

    public void setDelegationId(int delegationId) {
        this.delegationId = delegationId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPresent() {
        return IsPresent;
    }

    public void setPresent(boolean present) {
        IsPresent = present;
    }



    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }


}
