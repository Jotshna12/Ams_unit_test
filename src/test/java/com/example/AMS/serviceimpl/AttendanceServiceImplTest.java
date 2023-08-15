package com.example.AMS.serviceimpl;

import com.example.AMS.Entities.AttendanceEntity;
import com.example.AMS.repository.AttendanceRepository;

import com.example.AMS.service.impl.AttendanceServiceImpl;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;


public class AttendanceServiceImplTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private AttendanceServiceImpl attendanceService;

    @BeforeEach
    public void setUp() {
        // Initialize the mock objects
        MockitoAnnotations.initMocks(this);

        // Create an instance of the service implementation using the mocked repository
        attendanceService = new AttendanceServiceImpl(attendanceRepository);
    }

    @Test
    void testGetBystudentidindaterange() {
        int studentId = 333;
        LocalDate startdate = LocalDate.parse("2023-01-01");
        LocalDate enddate = LocalDate.parse("2023-01-31");

        List<AttendanceEntity> expectedEntityList = new ArrayList<>();
        AttendanceEntity entity1 = new AttendanceEntity(100, studentId, 5002, startdate, true, "X A", 5002, 8);
        AttendanceEntity entity2 = new AttendanceEntity(101, studentId, 5002, enddate, true, "X A", 5002, 8);
        expectedEntityList.add(entity1);
        expectedEntityList.add(entity2);

        Mockito.when(attendanceRepository.existsById((long) studentId)).thenReturn(true);
        Mockito.when(attendanceRepository.getBystudentidindaterangeusingJpql(studentId, startdate, enddate))
                .thenReturn(expectedEntityList);


            List<AttendanceEntity> result = attendanceService.getBystudentidindaterange(studentId, startdate, enddate);
            assertNotNull(result); // Check that the result is not null
            assertEquals(expectedEntityList, result);
    }


}