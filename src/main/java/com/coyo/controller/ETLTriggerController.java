package com.coyo.controller;


import com.coyo.client.GetLoginDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ETLTriggerController {

    private GetLoginDetails loginDetails;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ETLTriggerController(GetLoginDetails loginDetails) {
        this.loginDetails = loginDetails;
    }

    @RequestMapping(value = "triggeretl")
    @PostMapping
    public void triggerETL() throws Exception {
        log.info("Triggering the ETL");
        this.loginDetails.getLoginDetails();
    }

}
