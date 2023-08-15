package com.example.AMS.service;
import com.example.AMS.Entities.AttendanceEntity;
import com.example.AMS.dto.request.AttendanceRequest;
import com.example.AMS.dto.response.AttendanceResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceService {

    Optional<AttendanceEntity> findById(Long id);

    void deleteAttendance(Long id);

    //Request and Response
    public List<AttendanceResponse> findAllAttendance();

    public AttendanceResponse saveAttendance(AttendanceRequest attendanceRequest);

    public AttendanceResponse updateAttendance(AttendanceRequest attendanceRequest, Long id);

    public List<AttendanceResponse> saveAllAttendance(List<AttendanceRequest> attendanceRequestList);
    public List<AttendanceEntity> getBystudentidindaterange(int studentId, LocalDate startdate , LocalDate enddate );
    public  List<AttendanceEntity> getByclassIDindaterange(String classId, LocalDate startdate, LocalDate enddate);



//    public List<AttendanceResponse> getBystudentidindaterange(int studentId, LocalDate startdate, LocalDate enddate);
//
//    public List<AttendanceResponse> getByclassIDindaterange(String classId, LocalDate startdate, LocalDate enddate);




}
