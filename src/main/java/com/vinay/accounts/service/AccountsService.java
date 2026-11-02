package com.vinay.accounts.service;

import com.vinay.accounts.dto.CustomerDto;

public interface AccountsService {

    void createAccount(CustomerDto customerDto);
    CustomerDto getCustomerByMobileNumber(String mobileNumber);

    boolean updateAccount(String mobileNumber,CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);




}
