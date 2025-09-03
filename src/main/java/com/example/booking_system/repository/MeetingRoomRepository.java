package com.example.booking_system.repository;

import com.example.booking_system.model.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {
    // save(), findById(), findAll(), delete()
}