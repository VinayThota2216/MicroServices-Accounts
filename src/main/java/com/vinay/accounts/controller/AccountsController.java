package com.vinay.accounts.controller;


import com.vinay.accounts.constants.AccountConstant;
import com.vinay.accounts.dto.CustomerDto;
import com.vinay.accounts.dto.ResponseDto;
import com.vinay.accounts.service.AccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts/api")
public class AccountsController {

    @Autowired
    AccountsService accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstant.ACCOUNT_CREATED, AccountConstant.ACCOUNT_CREATED_MESSAGE));
    }


    @PutMapping("/update/{mobile_number}")
    public ResponseEntity updateAccount(@PathVariable("mobile_number")
                                            @Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
                                            String mobileNumber,
                                            @Valid @RequestBody CustomerDto customerDto) {
        accountsService.updateAccount(mobileNumber,customerDto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto(AccountConstant.ACCOUNT_UPDATED, AccountConstant.ACCOUNT_UPDATED_MESSAGE));

    }


    @DeleteMapping("/delete/{mobile_number}")
    public ResponseEntity deleteAccount(@PathVariable("mobile_number")
                                            @Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
                                             String mobileNumber) {
        accountsService.deleteAccount(mobileNumber);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto( AccountConstant.ACCOUNT_DELETED,AccountConstant.ACCOUNT_DELETED_MESSAGE));


    }


    @GetMapping("/get/{mobile_number}")
    public ResponseEntity getAccount(@Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
                                         @PathVariable("mobile_number") String mobileNumber) {

        CustomerDto customerDto=accountsService.getCustomerByMobileNumber(mobileNumber);

        return ResponseEntity.ok(customerDto);

    }
}
