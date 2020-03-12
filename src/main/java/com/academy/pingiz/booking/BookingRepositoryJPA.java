package com.academy.pingiz.booking;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepositoryJPA extends CrudRepository <BookingSlot, Integer> {
    List<BookingSlot> findByDate(LocalDate date);
}
