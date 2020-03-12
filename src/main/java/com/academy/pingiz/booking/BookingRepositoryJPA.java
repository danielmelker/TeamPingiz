package com.academy.pingiz.booking;

import org.springframework.data.repository.CrudRepository;

public interface BookingRepositoryJPA extends CrudRepository <BookingSlot, Integer> {

}
