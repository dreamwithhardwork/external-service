package org.external.controller;

import org.models.core.dao.RTORepository;
import org.models.core.location.RTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rto")
public class RTOController {

    @Autowired
    RTORepository rtoRepository;

    @GetMapping("/getAll")
    public List<RTO> getAllRTO(){
        return rtoRepository.findByOperating(true);
    }

    @GetMapping("/state/{state}")
    public List<RTO> getAllRTOByState(@PathVariable("state") String state){
        return rtoRepository.findByStateAndOperating(state,true);
    }


}
