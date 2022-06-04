package com.spring.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MembershipService {
    public Boolean addSillyMember() {
        log.info("Exec addAcount()");
        return true;
    }

    public void goToSleep() {
        log.info("Exec goToSleep()");
    }
}
