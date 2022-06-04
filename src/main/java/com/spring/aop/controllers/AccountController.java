package com.spring.aop.controllers;

import com.spring.aop.model.Account;
import com.spring.aop.service.AccountService;
import com.spring.aop.service.MembershipService;
import com.spring.aop.service.TrafficFortuneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aop")
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private TrafficFortuneService trafficFortuneService;

    @GetMapping("/before")
    public String endpointController() {
        Account account = new Account("Kevin", "Platinum");
        account.setName("Kevin");
        account.setLevel("Premium");
        accountService.addAccount(account, true);
        membershipService.addSillyMember();
        accountService.doWork();
        membershipService.goToSleep();
        log.info("Request endpointController()");
        accountService.setName("name");
        accountService.getName();
        accountService.setCode("code");
        accountService.getCode();
        log.info("AfterReturning");
        List<Account> accounts = accountService.findAccounts(false);
        log.info(accounts.toString());
        accounts.stream().forEach(a -> log.info(a.toString()));
        return "ok";
    }

    @GetMapping("/exception")
    public String endpointExceptionController() {
        log.info("AfterThrowing");
        Boolean flag = true;
        List<Account> accounts = null;
        try {
            accounts = accountService.findAccounts(flag);
        } catch (Exception e) {
            log.error(e.toString());
        }
        log.info(accounts.toString());
        accounts.stream().forEach(a -> log.info(a.toString()));
        return "ok";
    }

    @GetMapping("/after")
    public String endpointAfterController() {
        log.info("After");
        Boolean flag = false;
        List<Account> accounts = null;
        try {
            accounts = accountService.findAccounts(flag);
        } catch (Exception e) {
            log.error(e.toString());
        }
        log.info(accounts.toString());
        accounts.stream().forEach(a -> log.info(a.toString()));
        return "ok";
    }

    @GetMapping("/around")
    public String endpointAroundController() {
        log.info("Around");
        log.info("Calling Fortune Service");
        String fortune = trafficFortuneService.getFortune(false);
        log.info("Fortune Service result: {}", fortune);
        log.info("Call Finished");
        return "ok";
    }

    @GetMapping("/around/exception")
    public String endpointAroundExceptionController() {
        log.info("Around");
        log.info("Calling Fortune Service");
        Boolean flag = true;
        String fortune = trafficFortuneService.getFortune(flag);
        log.info("Fortune Service result: {}", fortune);
        log.info("Call Finished");
        return "ok";
    }
}
