package org.external.models;

import lombok.Getter;
import lombok.Setter;
import org.models.core.domain.Booking;


@Getter
@Setter
public class VehicleBooking {

    private String vehicleId;
    private Booking booking;
}
