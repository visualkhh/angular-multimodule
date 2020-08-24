package com.genome.dx.gmp.controller;

import com.genome.dx.wcore.config.security.WebSecurityConfigurerAdapter;
import com.genome.dx.wcore.service.AnonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(AnonController.URI_PREFIX)
public class AnonController {

    public static final String URI_PREFIX 		        = WebSecurityConfigurerAdapter.ANON_PATH;

    @Autowired
    private AnonService anonService;


    @GetMapping(value="/wow")
    public Object detail(){
        return  "wow";
    }


}
