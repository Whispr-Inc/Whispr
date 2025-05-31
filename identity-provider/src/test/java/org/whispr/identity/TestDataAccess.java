package org.whispr.identity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.whispr.identity.dao.repositories.AccountRepository;

@SpringBootTest
public class TestDataAccess {

    @Autowired
    private AccountRepository accountRepository;

    // Example test method to verify the repository is autowired correctly
    @Test
    public void test() {
        // This test will pass if the application context loads and the repository is available
        assert accountRepository != null : "AccountRepository should not be null";

        // Optionally, you can perform some operations to verify functionality
        accountRepository.findAll().forEach(System.out::println);
    }
}
