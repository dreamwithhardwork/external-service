package org.external.controller;

import org.models.core.dao.SearchFilterRepository;
import org.models.core.dao.SearchRepository;
import org.models.core.domain.Vehicle;
import org.models.core.domain.report.AutomobileType;
import org.models.core.enums.VehicleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private VehicleStatus getVehicleStatus(VehicleStatus vehicleStatus){
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities()==null){
            vehicleStatus = VehicleStatus.UNSOLD;
        }
        else{
            List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if(!grantedAuthorities.get(0).getAuthority().equals("ROLE_ADMIN")){
                vehicleStatus = VehicleStatus.UNSOLD;
            }
        }
        return   vehicleStatus;
    }


    @GetMapping("/all")
    List<Vehicle> getAllVehicles(@RequestParam("type") AutomobileType type){
        List<Vehicle> response= new ArrayList<>();
        response.addAll(searchRepository.findByAutomobileTypeAndStatus(type,VehicleStatus.UNSOLD));
        return response;
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
    List<Vehicle> getAllByMake(@PathVariable String make,@RequestParam("status") Optional<VehicleStatus> status, @RequestParam("type") AutomobileType type){
        return searchRepository.findByMakeAndAutomobileTypeAndStatus(make,type, VehicleStatus.UNSOLD);
    }

    @GetMapping("/mileage")
    List<Vehicle> getAllCarsByMileage(@RequestParam("from") Integer from, @RequestParam("to") Integer to,@RequestParam("status") Optional<VehicleStatus> status,
                                      @RequestParam("type") AutomobileType type){
        to = to==null?2022:to;
        return searchRepository.findByMileageBetweenAndAutomobileType(from,to,type);
    }

    @GetMapping("/price")
    List<Vehicle> getAllByPrice(@RequestParam("from") Float from, @RequestParam("to") Float to,@RequestParam("status") Optional<VehicleStatus> status, @RequestParam("type") AutomobileType type){
        to = to == null?Float.MAX_VALUE:to;
        return searchRepository.findByPriceBetweenAndAutomobileType(from,to,type);
    }

    @GetMapping("/filter")
    List<Vehicle> getAllByFilter(@RequestParam("color") Optional<String> color, @RequestParam("year") Optional<String> year,
                                     @RequestParam("make") Optional<String> make, @RequestParam("model") Optional<String> model,
                                     @RequestParam("fromPrice") Optional<String> minPrice, @RequestParam("toPrice") Optional<String> maxPrice ,
                                     @RequestParam("fromMileage") Optional<String> minMileage, @RequestParam("toMileage") Optional<String> maxMileage
                                      , @RequestParam("type") AutomobileType type){
        Map<String,String> filter = new HashMap<>();
        filter.put("color",color.orElse(null));
        filter.put("year",year.orElse(null));
        filter.put("make",make.orElse(null));
        filter.put("model",model.orElse(null));
        filter.put("minPrice",minPrice.orElse(null));
        filter.put("maxPrice",maxPrice.orElse(null));
        filter.put("minMileage",minMileage.orElse(null));
        filter.put("maxMileage",maxMileage.orElse(null));
        filter.put("type",type.toString());
        filter.put("status",VehicleStatus.UNSOLD.toString());
        return searchFilterRepository.getVehiclesByFilter(filter);
    }

}
