package org.external.controller;

import org.models.core.dao.SearchFilterRepository;
import org.models.core.dao.SearchRepository;
import org.models.core.domain.Car;
import org.models.core.domain.Vehicle;
import org.models.core.enums.AutomobileType;
import org.models.core.enums.VehicleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleSearchController {


    @Autowired
    SearchRepository searchRepository;

    @Autowired
    SearchFilterRepository searchFilterRepository;


    @GetMapping("/all")
    List<Vehicle> getAllVehicles(@RequestParam("type") AutomobileType type){
        return searchRepository.findByAutomobileTypeAndStatus(AutomobileType.BIKE,VehicleStatus.UNSOLD);
    }
    @GetMapping("/cars")
    List<Vehicle> getAllUnsoldCars(){
        return searchRepository.findByAutomobileType(AutomobileType.CAR);
    }


    @GetMapping("/color/{color}")
    List<Vehicle> getAllByColor(@PathVariable String color,@RequestParam("type") AutomobileType type){
        return searchRepository.findByColorAndAutomobileTypeAndStatus(color,type,VehicleStatus.UNSOLD);
    }

    @GetMapping("/model/{model}")
    List<Vehicle> getAllByModel(@PathVariable String model, @RequestParam("type") AutomobileType type){
        return searchRepository.findByModelAndAutomobileTypeAndStatus(model,type,VehicleStatus.UNSOLD);
    }

    @GetMapping("/year/{year}")
    List<Vehicle> getAllByYear(@PathVariable Integer year, @RequestParam("type") AutomobileType type){
        return searchRepository.findByMakeYearAndTypeAndStatus(year,type,VehicleStatus.UNSOLD);
    }

    @GetMapping("/make/{make}")
    List<Vehicle> getAllByMake(@PathVariable String make, @RequestParam("type") AutomobileType type){
        return searchRepository.findByMakeAndAutomobileTypeAndStatus(make,type, VehicleStatus.UNSOLD);
    }

    @GetMapping("/mileage")
    List<Vehicle> getAllCarsByMileage(@RequestParam("from") Integer from, @RequestParam("to") Integer to,
                                      @RequestParam("type") AutomobileType type){
        to = to==null?2022:to;
        return searchRepository.findByMileageBetweenAndAutomobileType(from,to,type);
    }

    @GetMapping("/price")
    List<Vehicle> getAllByPrice(@RequestParam("from") Float from, @RequestParam("to") Float to, @RequestParam("type") AutomobileType type){
        to = to == null?Float.MAX_VALUE:to;
        return searchRepository.findByPriceBetweenAndAutomobileTypeAndStatus(from,to,type,VehicleStatus.UNSOLD);
    }

    @GetMapping("/filter")
    List<Vehicle> getAllByFilter(@RequestParam("color") Optional<String> color, @RequestParam("year") Optional<Integer> year,
                                 @RequestParam("make") Optional<String> make, @RequestParam("model") Optional<String> model,
                                 @RequestParam("fromPrice") Optional<Float> minPrice, @RequestParam("toPrice") Optional<Float> maxPrice ,
                                 @RequestParam("fromMileage") Optional<Integer> minMileage, @RequestParam("toMileage") Optional<Integer> maxMileage
            , @RequestParam("type") AutomobileType type){
        Map<String,Object> filter = new HashMap<>();
        filter.put("color",color.orElse(null));
        filter.put("year",year.orElse(null));
        filter.put("make",make.orElse(null));
        filter.put("model",model.orElse(null));
        filter.put("minPrice",minPrice.orElse(null));
        filter.put("maxPrice",maxPrice.orElse(null));
        filter.put("minMileage",minMileage.orElse(null));
        filter.put("maxMileage",maxMileage.orElse(null));
        filter.put("automobileType",type.toString());
        filter.put("status", VehicleStatus.UNSOLD);
        return searchFilterRepository.getVehiclesByFilter(filter);
    }

}
