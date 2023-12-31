package com.rhms.hms_backend.Controllers;


import com.rhms.hms_backend.Models.Room_assignment;
import com.rhms.hms_backend.Services.RoomAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/roomAssign")
public class RoomAssignmentController {



        private final RoomAssignmentService roomAssignmentService;

        @Autowired
        public RoomAssignmentController(RoomAssignmentService roomAssignmentService) {
            this.roomAssignmentService = roomAssignmentService;
        }

        @PostMapping("/assignRoom")
        public ResponseEntity<String> assignRoomToStudent(@RequestBody Room_assignment room_assignment) {
            try {
                String result = roomAssignmentService.assignRoomToStudent(room_assignment.getRoomId(), room_assignment.getUserId());

                if (result.equals("Success")) {
                    return ResponseEntity.ok("Room assigned successfully");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + result);
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
            }
        }



    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteRoomAssignmentByUserId(@PathVariable String userId) {
        roomAssignmentService.deleteRoomAssignmentByUserId(userId);
        return new ResponseEntity<>("Room assignments for user " + userId + " have been deleted", HttpStatus.OK);
    }


    @PostMapping("/create-view")
    public ResponseEntity<List<Room_assignment>> callCreateRoomAssignmentView(@RequestParam String roomNumber) {
        List<Room_assignment> result = roomAssignmentService.callCreateRoomAssignmentView(roomNumber);

        if (result != null && !result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/getByRoom")
    public List<Room_assignment> getAllRoomAssignments(@RequestParam String roomNumber) {
        return roomAssignmentService.findRoomAssignmentsByRoomNumber(roomNumber);
    }

}
