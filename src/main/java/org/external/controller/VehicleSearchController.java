package org.external.controller;

import org.models.core.dao.SearchFilterRepository;
import org.models.core.dao.SearchRepository;
import org.models.core.domain.Vehicle;
import org.models.core.domain.report.AutomobileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class VehicleSearchController {


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
    List<Vehicle> getAllCarsByColor(@PathVariable String color, @RequestParam("status") Optional<String> status){
        return searchRepository.findByColorAndAutomobileType(color,type);
    }

    @GetMapping("/model/{model}")
    List<Vehicle> getAllCarsByModel(@PathVariable String model,@RequestParam("status") Optional<String> status){
        return searchRepository.findByModelAndAutomobileType(model,type);
    }

    @GetMapping("/year/{year}")
    List<Vehicle> getAllCarsByYear(@PathVariable Integer year,@RequestParam("status") Optional<String> status){
        return searchRepository.findByMakeYearAndType(year,type);
    }

    @GetMapping("/make/{make}")
    List<Vehicle> getAllCarsByMake(@PathVariable String make,@RequestParam("status") Optional<String> status){
        return searchRepository.findByMakeAndAutomobileType(make,type);
    }

    @GetMapping("/mileage")
    List<Vehicle> getAllCarsByMileage(@RequestParam("from") Integer from, @RequestParam("to") Integer to,@RequestParam("status") Optional<String> status){
        to = to==null?2022:to;
        return searchRepository.findByMileageBetweenAndAutomobileType(from,to,type);
    }

    @GetMapping("/price")
    List<Vehicle> getAllCarsByPrice(@RequestParam("from") Float from, @RequestParam("to") Float to,@RequestParam("status") Optional<String> status){
        to = to == null?Float.MAX_VALUE:to;
        return searchRepository.findByPriceBetweenAndAutomobileType(from,to,type);
    }

    @GetMapping("/filter")
    List<Vehicle> getAllCarsByFilter(@RequestParam("color") Optional<String> color, @RequestParam("year") Optional<String> year,
                                     @RequestParam("make") Optional<String> make, @RequestParam("model") Optional<String> model,
                                     @RequestParam("fromPrice") Optional<String> minPrice, @RequestParam("toPrice") Optional<String> maxPrice ,
                                     @RequestParam("fromMileage") Optional<String> minMileage, @RequestParam("toMileage") Optional<String> maxMileage
                                      ,@RequestParam("status") Optional<String> status){
        Map<String,String> filter = new HashMap<>();
        filter.put("color",color.orElse(null));
        filter.put("year",year.orElse(null));
        filter.put("make",make.orElse(null));
        filter.put("model",model.orElse(null));
        filter.put("minPrice",minPrice.orElse(null));
        filter.put("maxPrice",maxPrice.orElse(null));
        filter.put("minMileage",minMileage.orElse(null));
        filter.put("maxMileage",maxMileage.orElse(null));
        filter.put("status",status.orElse(null));
        filter.put("automobileType","CAR");

        return searchFilterRepository.getVehiclesByFilter(filter);
    }

}
