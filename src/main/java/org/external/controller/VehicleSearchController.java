package org.external.controller;

import org.models.core.dao.SearchFilterRepository;
import org.models.core.dao.SearchRepository;
import org.models.core.domain.Vehicle;
import org.models.core.domain.report.AutomobileType;
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

    private AutomobileType type = AutomobileType.CAR;


    @GetMapping("/all")
    List<Vehicle> getAllVehicles(@RequestParam("type") Optional<AutomobileType> type){
        List<Vehicle> response= new ArrayList<>();
        if(type.isEmpty()){
            response.addAll(searchRepository.findByAutomobileType(AutomobileType.BIKE));
            response.addAll(searchRepository.findByAutomobileType(AutomobileType.CAR));
        }
        else{
            response.addAll(searchRepository.findByAutomobileType(type.get()));
        }
        return response;
    }


    @GetMapping("/color/{color}")
    List<Vehicle> getAllByColor(@PathVariable String color, @RequestParam("status") Optional<String> status,@RequestParam("type") AutomobileType type){
        return status.isEmpty()?searchRepository.findByColorAndAutomobileType(color,type):
                searchRepository.findByColorAndAutomobileTypeAndStatus(color,type,status.get());
    }

    @GetMapping("/model/{model}")
    List<Vehicle> getAllByModel(@PathVariable String model,@RequestParam("status") Optional<String> status, @RequestParam("type") AutomobileType type){
        return status.isEmpty()? searchRepository.findByModelAndAutomobileType(model,type):
                searchRepository.findByModelAndAutomobileTypeAndStatus(model,type,status.get());
    }

    @GetMapping("/year/{year}")
    List<Vehicle> getAllByYear(@PathVariable Integer year,@RequestParam("status") Optional<String> status, @RequestParam("type") AutomobileType type){
        return status.isEmpty()? searchRepository.findByMakeYearAndType(year,type):
                searchRepository.findByMakeYearAndTypeAndStatus(year,type,status.get());
    }

    @GetMapping("/make/{make}")
    List<Vehicle> getAllByMake(@PathVariable String make,@RequestParam("status") Optional<String> status, @RequestParam("type") AutomobileType type){
        return status.isEmpty()? searchRepository.findByMakeAndAutomobileType(make,type):
                searchRepository.findByMakeAndAutomobileTypeAndStatus(make,type,status.get());
    }

    @GetMapping("/mileage")
    List<Vehicle> getAllCarsByMileage(@RequestParam("from") Integer from, @RequestParam("to") Integer to,@RequestParam("status") Optional<String> status,
                                      @RequestParam("type") AutomobileType type){
        to = to==null?2022:to;
        return searchRepository.findByMileageBetweenAndAutomobileType(from,to,type);
    }

    @GetMapping("/price")
    List<Vehicle> getAllByPrice(@RequestParam("from") Float from, @RequestParam("to") Float to,@RequestParam("status") Optional<String> status, @RequestParam("type") AutomobileType type){
        to = to == null?Float.MAX_VALUE:to;
        return searchRepository.findByPriceBetweenAndAutomobileType(from,to,type);
    }

    @GetMapping("/filter")
    List<Vehicle> getAllByFilter(@RequestParam("color") Optional<String> color, @RequestParam("year") Optional<String> year,
                                     @RequestParam("make") Optional<String> make, @RequestParam("model") Optional<String> model,
                                     @RequestParam("fromPrice") Optional<String> minPrice, @RequestParam("toPrice") Optional<String> maxPrice ,
                                     @RequestParam("fromMileage") Optional<String> minMileage, @RequestParam("toMileage") Optional<String> maxMileage
                                      ,@RequestParam("status") Optional<String> status, @RequestParam("type") AutomobileType type){
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
        filter.put("type",type.toString());
        return searchFilterRepository.getVehiclesByFilter(filter);
    }

}
