package com.example.AMS.service.impl;

import com.example.AMS.Entities.AttendanceEntity;
import com.example.AMS.dto.mapper.AttendanceMapper;
import com.example.AMS.dto.request.AttendanceRequest;
import com.example.AMS.dto.response.AttendanceResponse;
import com.example.AMS.repository.AttendanceRepository;
import com.example.AMS.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {


    private final AttendanceRepository  attendanceRepository;
    private AttendanceEntity attendanceEntity;


    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }


    @Override
    public Optional<AttendanceEntity> findById(Long id) {
        return attendanceRepository.findById(id);
    }



    @Override
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);

    }
    @Override
    public List<AttendanceResponse> findAllAttendance() {
        List<AttendanceEntity> entityList = attendanceRepository.findAll();

        if(entityList.size() == 0) throw new RuntimeException("not a single item found!");

        List<AttendanceResponse> apiList = AttendanceMapper.MAPPER.fromEntityListToResponse(entityList);

        return apiList;
    }
    @Override
    public AttendanceResponse saveAttendance(AttendanceRequest attendanceRequest){
        AttendanceEntity attendanceEntity = AttendanceMapper.MAPPER.fromRequestToEntity(attendanceRequest);
        attendanceRepository.save(attendanceEntity);
        return AttendanceMapper.MAPPER.fromEntityToResponse(attendanceEntity);
    }
    @Override
    public AttendanceResponse updateAttendance(AttendanceRequest attendanceRequest,Long id){
        Optional<AttendanceEntity> checkExistingAttendance = findById(id);
        if (! checkExistingAttendance.isPresent())
            throw new RuntimeException("Attendance Id "+ id + " Not Found!");

        AttendanceEntity attendanceEntity = AttendanceMapper.MAPPER.fromRequestToEntity(attendanceRequest);
        attendanceEntity.setId(id);
        attendanceRepository.save(attendanceEntity);
        return AttendanceMapper.MAPPER.fromEntityToResponse(attendanceEntity);
    }


    @Override
    public List<AttendanceResponse> saveAllAttendance(List<AttendanceRequest> attendanceRequestList) {
        List<AttendanceEntity> attendanceEntityList = AttendanceMapper.MAPPER.fromRequestListToEntity(attendanceRequestList);
        attendanceRepository.saveAll(attendanceEntityList);
        return AttendanceMapper.MAPPER.fromEntityListToResponse(attendanceEntityList);
    }

  /*  @Override
    public List<AttendanceResponse> getBystudentidindaterange(int studentId, LocalDate startdate, LocalDate enddate) {
        List<AttendanceEntity> attendanceEntities = attendanceRepository.getBystudentidindaterangeusingJpql(studentId, startdate, enddate);
        return AttendanceMapper.MAPPER.fromEntityListToResponse(attendanceEntities);
    }


    @Override
   public List<AttendanceResponse> getByclassIDindaterange(String classId, LocalDate startdate, LocalDate enddate) {
        List<AttendanceEntity> attendanceEntities = attendanceRepository.getByclassIDindaterangeusingJpql(classId, startdate, enddate);
        return AttendanceMapper.MAPPER.fromEntityListToResponse(attendanceEntities);

   }
*/


    @Override
    public List<AttendanceEntity> getByclassIDindaterange(String classId, LocalDate startdate, LocalDate enddate) {
        return attendanceRepository.getByclassIDindaterangeusingJpql(classId, startdate, enddate);
    }

    @Override
    public List<AttendanceEntity> getBystudentidindaterange(int studentId, LocalDate startdate, LocalDate enddate) {



        // validation for student existence
        if (!studentExists(studentId)) {
            throw new IllegalArgumentException("Student not found");
        }

        return attendanceRepository.getBystudentidindaterangeusingJpql(studentId, startdate, enddate);
    }

    private boolean studentExists(int studentId) {
        return attendanceRepository.existsById((long) studentId);
    }

}
