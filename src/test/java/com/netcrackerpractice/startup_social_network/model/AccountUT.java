package com.netcrackerpractice.startup_social_network.model;

import com.netcrackerpractice.startup_social_network.dto.AccountDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;

/**
 * Created by Елизавета on 13.11.2018.
 */
public class AccountUT {
    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkAccountMapping() {
        AccountDTO creation = new AccountDTO();
        creation.setFirstName("Test first name");
        creation.setSecondName("Test second name");

        Account account = modelMapper.map(creation, Account.class);
        assertEquals(creation.getFirstName(), account.getFirstName());
        assertEquals(creation.getSecondName(), account.getSecondName());
    }
}
