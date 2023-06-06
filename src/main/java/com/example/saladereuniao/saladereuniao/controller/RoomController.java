package com.example.saladereuniao.saladereuniao.controller;

import com.example.saladereuniao.saladereuniao.exception.ResourceNotFoundException;
import com.example.saladereuniao.saladereuniao.model.Room;
import com.example.saladereuniao.saladereuniao.repository.RoomRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/id")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") long roomId)throws ResourceNotFoundException{
        Room room =  roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + roomId));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room){
        return roomRepository.save(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value = "id") long roomId, @Valid @RequestBody Room roomDetails) throws
            ResourceNotFoundException{
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not foundfor this id: " + roomId));
        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setStartHour(roomDetails.getStartHour());
        room.setEndHour(roomDetails.getEndHour());
        final Room updateRoom = roomRepository.save(room);
        return ResponseEntity.ok(updateRoom);
    }

    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") long roomId)throws
            ResourceNotFoundException{
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not foundfor this id: " + roomId));
        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
