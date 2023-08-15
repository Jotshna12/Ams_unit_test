package com.example.AMS.controller;

import com.example.AMS.AmsApplication;
import com.example.AMS.Controller.AttendanceController;
import com.example.AMS.Entities.AttendanceEntity;
import com.example.AMS.service.AttendanceService;
import com.example.AMS.service.impl.AttendanceServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;





@WebMvcTest(AttendanceController.class)
//@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)

public class AttendanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AttendanceServiceImpl attendanceService;

//    @Mock
//    private AttendanceService attendanceService;


    @InjectMocks
    private AttendanceController attendanceController;



    @Test
    public void testGetByStudentIdInDateRange() throws Exception {
        // Mock data
        int studentId = 1;
        LocalDate startdate = LocalDate.parse("2023-07-30");
        LocalDate enddate = LocalDate.parse("2023-08-01");


        AttendanceEntity entity1 = new AttendanceEntity(100, studentId, 5002, startdate, true, "X A", 5002, 8);
        AttendanceEntity entity2 = new AttendanceEntity(101, studentId, 5002, enddate, true, "X A", 5002, 8);
        List<AttendanceEntity> mockAttendanceList = Arrays.asList(entity1, entity2);

        // Mock the service method
        when(attendanceService.getBystudentidindaterange(studentId, startdate, enddate))
                .thenReturn(mockAttendanceList);

        // Perform the GET request
        mockMvc.perform(get("/api/v1/attendance/students/id-date-range")
                        .param("studentId", String.valueOf(studentId))
                        .param("startdate", startdate.toString())
                        .param("enddate", enddate.toString()))
                .andExpect(status().isOk());
        verify(attendanceService).getBystudentidindaterange(studentId, startdate, enddate);
    }

    @Test
    public void testEmptyListInput() throws Exception {
        // Perform the GET request with empty list parameters
        mockMvc.perform(get("/api/v1/attendance/students/id-date-range")
                        .param("studentId", "")
                        .param("startdate", "")
                        .param("enddate", ""))
                .andExpect(status().isBadRequest());
    }





    @Test
    public void testStartDateAfterEndDate() throws Exception {
        // Perform the GET request with invalid parameters (start date after end date)
        try {
            mockMvc.perform(get("/api/v1/attendance/students/id-date-range")
                            .param("studentId", "1")
                            .param("startdate", "2023-08-01")
                            .param("enddate", "2023-07-30"))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testValidationConstraints() throws Exception {
        // Perform the GET request with invalid parameters
        mockMvc.perform(get("/api/v1/attendance/students/id-date-range")
                        .param("studentId", "-1")  // Invalid studentId
                        .param("startdate", "2023-07-30")
                        .param("enddate", "2023/08/01"))  // Incorrect date format
                .andExpect(status().isBadRequest());
    }




}