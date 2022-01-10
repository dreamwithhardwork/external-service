package org.external.controller;


import org.external.models.VehicleBooking;
import org.models.core.dao.BookingRepository;
import org.models.core.domain.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingsController {

    @Autowired
    BookingRepository bookingRepository;




}
