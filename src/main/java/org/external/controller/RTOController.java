package org.external.controller;

import org.models.core.dao.RTORepository;
import org.models.core.location.RTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rto")
public class RTOController {

    @Autowired
    RTORepository rtoRepository;


    @GetMapping("/getAll")
    public List<RTO> getAllRTO(@RequestParam("operating") Boolean operating){
        return operating==null?rtoRepository.findAll():rtoRepository.findByOperating(operating);
    }

    @GetMapping("/state/{state}")
    public List<RTO> getAllRTOByState(@PathVariable("state") String state, @RequestParam("operating") Boolean operating){
        return operating==null?rtoRepository.findByState(state):rtoRepository.findByStateAndOperating(state,operating);
    }


}
