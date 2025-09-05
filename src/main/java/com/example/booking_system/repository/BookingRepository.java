package com.example.booking_system.repository;

import com.example.booking_system.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    /**
     * Этот метод ищет бронирования для конкретной комнаты (roomId),
     * которые пересекаются с заданным временным интервалом (startTime, endTime).
     *
     * Логика пересечения: существующая бронь (b.startTime, b.endTime) пересекается с новой (startTime, endTime),
     * если начало существующей раньше конца новой И конец существующей позже начала новой.
     */
    @Query("SELECT b FROM Booking b WHERE b.meetingRoom.id = :roomId AND b.startTime < :endTime AND b.endTime > :startTime")
    List<Booking> findConflictingBookings(
            @Param("roomId") Long roomId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    List<Booking> findByUserId(Long userId);
}