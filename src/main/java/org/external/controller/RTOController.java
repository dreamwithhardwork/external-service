package org.external.controller;

import org.models.core.dao.RTORepository;
import org.models.core.location.RTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rto")
public class RTOController {

    @Autowired
    RTORepository rtoRepository;


    private Boolean getOperating(Boolean operating){
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities()==null){
            operating = true;
        }
        else{
            List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if(!grantedAuthorities.get(0).getAuthority().equals("ROLE_ADMIN")){
                operating = true;
            }
        }
       return   operating;
    }

    @GetMapping("/getAll")
    public List<RTO> getAllRTO(@RequestParam("operating") Boolean operating){
        operating = getOperating(operating);
        return rtoRepository.findByOperating(operating);
    }

    @GetMapping("/state/{state}")
    public List<RTO> getAllRTOByState(@PathVariable("state") String state, @RequestParam("operating") Boolean operating){
        operating = getOperating(operating);
        return rtoRepository.findByStateAndOperating(state,operating);
    }


}
