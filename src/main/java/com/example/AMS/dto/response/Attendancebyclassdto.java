package com.example.AMS.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Attendancebyclassdto
{

    private int studentId;
    private boolean IsPresent;
    private LocalDate date;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public boolean isPresent() {
        return IsPresent;
    }

    public void setPresent(boolean present) {
        IsPresent = present;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
