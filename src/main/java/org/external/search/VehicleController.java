package org.external.search;


import org.models.core.dao.SearchFilterRepository;
import org.models.core.dao.SearchRepository;
import org.models.core.domain.Vehicle;
import org.models.core.domain.report.AutomobileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/cars")
@RestController
public class VehicleController {


    @Autowired
    SearchRepository searchRepository;

    @Autowired
    SearchFilterRepository searchFilterRepository;

    private static final AutomobileType type = AutomobileType.CAR;


    @GetMapping("/all")
    List<Vehicle> getAllCars(){
        return searchRepository.findByAutomobileType(type);
    }

    @GetMapping("/color/{color}")
    List<Vehicle> getAllCarsByColor(@PathVariable String color){
        return searchRepository.findByColorAndAutomobileType(color,type);
    }

    @GetMapping("/model/{model}")
    List<Vehicle> getAllCarsByModel(@PathVariable String model){
        return searchRepository.findByModelAndAutomobileType(model,type);
    }

    @GetMapping("/year/{year}")
    List<Vehicle> getAllCarsByYear(@PathVariable Integer year){
        return searchRepository.findByMakeYearAndType(year,type);
    }

    @GetMapping("/make/{make}")
    List<Vehicle> getAllCarsByMake(@PathVariable String make){
        return searchRepository.findByMakeAndAutomobileType(make,type);
    }

    @GetMapping("/mileage")
    List<Vehicle> getAllCarsByMileage(@RequestParam("from") Integer from, @RequestParam("to") Integer to){
        to = to==null?2022:to;
        return searchRepository.findByMileageBetweenAndAutomobileType(from,to,type);
    }

    @GetMapping("/price")
    List<Vehicle> getAllCarsByPrice(@RequestParam("from") Float from, @RequestParam("to") Float to){
        to = to == null?Float.MAX_VALUE:to;
        return searchRepository.findByPriceBetweenAndAutomobileType(from,to,type);
    }

    @GetMapping("/filter")
    List<Vehicle> getAllCarsByFilter(@RequestParam("color") Optional<String> color, @RequestParam("year") Optional<String> year,
                                     @RequestParam("make") Optional<String> make, @RequestParam("model") Optional<String> model,
                                     @RequestParam("price") Optional<String> price, @RequestParam("mileage") Optional<String> mileage){
        Map<String,String> filter = new HashMap<>();
        filter.put("color",color.orElse(null));
        filter.put("year",year.orElse(null));
        filter.put("make",make.orElse(null));
        filter.put("model",model.orElse(null));
        filter.put("price",make.orElse(null));
        filter.put("mileage",model.orElse(null));
        filter.put("automobileType","CAR");

        return searchFilterRepository.getVehiclesByFilter(filter);
    }

}
