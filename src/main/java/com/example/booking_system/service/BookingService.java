package com.example.booking_system.service;

import com.example.booking_system.exception.BookingConflictException;
import com.example.booking_system.model.Booking;
import com.example.booking_system.model.MeetingRoom;
import com.example.booking_system.model.User;
import com.example.booking_system.repository.BookingRepository;
import com.example.booking_system.repository.MeetingRoomRepository;
import com.example.booking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MeetingRoomRepository meetingRoomRepository;

    public Booking createBooking(Long userId, Long roomId, LocalDateTime startTime, LocalDateTime endTime) {
        // 1. Проверяем, существуют ли пользователь и комната
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        MeetingRoom room = meetingRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("MeetingRoom not found"));

        // 2. Ищем существующие бронирования, которые пересекаются с нашим временем
        List<Booking> conflictingBookings = bookingRepository.findConflictingBookings(roomId, startTime, endTime);

        if (!conflictingBookings.isEmpty()) {
            // Если список не пустой, значит, конфликт найден. Выдаем ошибку.
            throw new BookingConflictException("Booking conflict: The room is already booked for this time.");
        }

        // 3. Если конфликтов нет, создаем и сохраняем новое бронирование
        Booking newBooking = new Booking();
        newBooking.setUser(user);
        newBooking.setMeetingRoom(room);
        newBooking.setStartTime(startTime);
        newBooking.setEndTime(endTime);
        // newBooking.setTitle("Some Title");

        return bookingRepository.save(newBooking);
    }

    public List<Booking> getBookingsForUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return bookingRepository.findByUserId(userId);
    }

    public void deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        bookingRepository.delete(booking);
    }
}