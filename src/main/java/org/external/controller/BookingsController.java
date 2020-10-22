package org.external.controller;


import org.external.models.VehicleBooking;
import org.models.core.dao.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingsController {

    @Autowired
    BookingRepository bookingRepository;



    @PostMapping("/add")
    public Boolean addBooking(@RequestBody VehicleBooking vehicleBooking){
       return bookingRepository.save(vehicleBooking.getVehicleId(),vehicleBooking.getBooking());
    }


}
