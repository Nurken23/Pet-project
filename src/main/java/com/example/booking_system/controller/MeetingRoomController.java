package com.example.booking_system.controller;

import com.example.booking_system.model.MeetingRoom;
import com.example.booking_system.repository.MeetingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class MeetingRoomController {

    @Autowired
    private MeetingRoomRepository meetingRoomRepository;

    @PostMapping
    public MeetingRoom createRoom(@RequestBody MeetingRoom meetingRoom) {
        return meetingRoomRepository.save(meetingRoom);
    }
}