package com.example.AMS.dto.mapper;

import com.example.AMS.Entities.AttendanceEntity;
import com.example.AMS.dto.request.AttendanceRequest;
import com.example.AMS.dto.response.AttendanceResponse;
import com.example.AMS.dto.response.Attendancebyclassdto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AttendanceMapper {
    AttendanceMapper MAPPER = Mappers.getMapper(AttendanceMapper.class);
    AttendanceEntity fromRequestToEntity(AttendanceRequest employeeRequest);
    AttendanceResponse fromEntityToResponse(AttendanceEntity employeeEntity);
    List<AttendanceResponse> fromEntityListToResponse(List<AttendanceEntity> entityList);
    List<AttendanceEntity> fromRequestListToEntity(List<AttendanceRequest> attendanceRequestList);

}
