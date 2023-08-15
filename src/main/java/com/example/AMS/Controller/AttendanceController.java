package com.example.AMS.Controller;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.AMS.Entities.AttendanceEntity;
import com.example.AMS.dto.request.AttendanceRequest;
import com.example.AMS.dto.response.AttendanceResponse;
import com.example.AMS.exception.InvalidDateRangeException;
import com.example.AMS.service.AttendanceService;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController  implements AttendanceService {
    private final AttendanceService attendanceService;


    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }


    @GetMapping("/")
    public List<AttendanceResponse> findAllAttendance() {
        return attendanceService.findAllAttendance();
    }

    @GetMapping("/{id}")
    public Optional<AttendanceEntity> findById(@PathVariable("id") Long id) {
        return attendanceService.findById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable("id") Long id) {
        attendanceService.deleteAttendance(id);
    }

    @PostMapping()
    public AttendanceResponse saveAttendance(@RequestBody AttendanceRequest attendanceRequest) {
        return attendanceService.saveAttendance(attendanceRequest);

    }

    @PutMapping("/{id}")
    public AttendanceResponse updateAttendance(@RequestBody AttendanceRequest attendanceRequest, @PathVariable("id") Long id) {
        return attendanceService.updateAttendance(attendanceRequest, id);
    }

    @PostMapping("/")
    @Override
    public List<AttendanceResponse> saveAllAttendance(@RequestBody List<AttendanceRequest> attendanceRequestList) {
        return attendanceService.saveAllAttendance(attendanceRequestList);
    }


    /*  @GetMapping("/students")
      public List<AttendanceResponse> getBystudentidindaterange(@RequestParam int studentId, @RequestParam LocalDate startdate, @RequestParam LocalDate enddate){
          return attendanceService.getBystudentidindaterange(studentId, startdate, enddate);
      }

      @GetMapping("/studentsbyclass")
      public List<AttendanceResponse> getByclassIDindaterange(@RequestParam String classId, @RequestParam LocalDate startdate, @RequestParam LocalDate enddate) {
          return attendanceService.getByclassIDindaterange(classId, startdate, enddate);
      }

     */
    //@GetMapping("/students/attendance/date-range")
 /* @GetMapping("/students/id-date-range")
  public List<AttendanceEntity> getBystudentidindaterange(@RequestParam int studentId, @RequestParam LocalDate startdate, @RequestParam LocalDate enddate){
      return attendanceService.getBystudentidindaterange(studentId, startdate, enddate);
  }*/


    //@GetMapping("/class/attendance/date-range")
    @GetMapping("/class/date-range")
    public List<AttendanceEntity> getByclassIDindaterange(@RequestParam String classId, @RequestParam LocalDate startdate, @RequestParam LocalDate enddate) {
        return attendanceService.getByclassIDindaterange(classId, startdate, enddate);
    }


    //@GetMapping("/students/attendance/date-range")
    @GetMapping("/students/id-date-range")
    public List<AttendanceEntity> getBystudentidindaterange(
            @RequestParam(name = "studentId") @NotNull @Min(value = 1, message = "Student ID must be greater than or equal to 1") int studentId,
            @RequestParam(name = "startdate") @NotBlank @Past(message = "Start date must be in the past or present") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startdate,
            @RequestParam(name = "enddate") @NotBlank @FutureOrPresent(message = "End date must be in the present or future") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddate) {


        //  validation for datechecks
        if (startdate.isAfter(enddate)) {
            throw new InvalidDateRangeException("Start date must be before or equal to end date.");
        }
        return attendanceService.getBystudentidindaterange(studentId, startdate, enddate);
    }

    @ExceptionHandler(InvalidDateRangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidDateRangeException(InvalidDateRangeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());


    }
}