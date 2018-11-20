package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.dto.AccountDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/account-list")
    public Iterable<Account> getAllAccounts() {
        return accountService.findAllAccounts();
    }

    @RequestMapping(value = "account-list/{accountId}", method = RequestMethod.GET)
    public AccountDTO getAccountWithDTO(@PathVariable("accountId") UUID id) {
        return new AccountDTO(accountService.findAccountById(id).get());
    }
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =
//            MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserDto> create(@RequestBody UserDto dto) {
//        return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
//    }
//

}
