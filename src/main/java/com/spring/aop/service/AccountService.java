package com.spring.aop.service;

import com.spring.aop.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class AccountService {

    private String name;
    private String code;
    public List<Account> findAccounts(Boolean flag) {
        if (flag) {
            throw new RuntimeException("findAccountsException(true)");
        }
        return Arrays.asList(new Account("Brando", "Gold"),
                new Account("Garcia", "Bronze"),
                new Account("Kevin", "Platinum"));
    }
    public void addAccount(Account account, Boolean bool) {
        log.info("Exec addAcount()");
    }
    public Boolean doWork() {
        log.info("Exec doWork()");
        return false;
    }

    public String getName() {
        log.info("Exec getName()");
        return name;
    }

    public void setName(String name) {
        log.info("Exec setName(name)");
        this.name = name;
    }

    public String getCode() {
        log.info("Exec getCode()");
        return code;
    }

    public void setCode(String code) {
        log.info("Exec setCode(code)");
        this.code = code;
    }
}
