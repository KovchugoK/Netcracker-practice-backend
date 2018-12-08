package com.netcrackerpractice.startup_social_network.controller;

import com.netcrackerpractice.startup_social_network.DTO.DetailAccountDTO;
import com.netcrackerpractice.startup_social_network.entity.Account;
import com.netcrackerpractice.startup_social_network.mapper.AccountMapper;
import com.netcrackerpractice.startup_social_network.service.AccountService;
import com.netcrackerpractice.startup_social_network.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/account")
public class AccountController {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    ImageService imageService;

    @GetMapping("/account-list")
    public Iterable<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping(value = "/{accountId}")
    public ResponseEntity<?> getAccount(@PathVariable("accountId") UUID id) {
        return new ResponseEntity<>(accountMapper.entityToDto(accountService.findAccountById(id).get()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody DetailAccountDTO dto) {
        return new ResponseEntity<>(accountService.saveAccount(accountMapper.dtoToEntity(dto)), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody DetailAccountDTO accountDTO) throws URISyntaxException {
        if (accountDTO.getId() == null) {
            return ResponseEntity.badRequest().header("Failure", "You cannot create a new user").build();
        }
        Account account = accountMapper.dtoToEntity(accountDTO);
        accountService.saveAccount(account);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@PathVariable("accountId") UUID id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/image")
    private ResponseEntity<?> saveImages(@RequestParam("image") MultipartFile image,
                                         @RequestParam(name = "Id") UUID accountId) {
        try {
            Optional<Account> accountOptional = accountService.findAccountById(accountId);

            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                accountService.saveImages(image, account);
            } else
                return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("FAIL to upload. Did't find account");

            return ResponseEntity.status(HttpStatus.OK).body("You successfully uploaded");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("FAIL to upload");
        }
    }
}
