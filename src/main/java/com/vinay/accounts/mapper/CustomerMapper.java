package com.vinay.accounts.mapper;

import com.vinay.accounts.dto.CustomerDto;
import com.vinay.accounts.entity.Accounts;
import com.vinay.accounts.entity.Customer;

public class CustomerMapper {
    public static Customer customerDtoToCustomerEntity(CustomerDto customerDto){

        return Customer.builder().name(customerDto.getName())
                .email(customerDto.getEmail())
                .mobileNumber(customerDto.getMobileNumber()).build();

    }

    public static CustomerDto customerAccountEntityToCustomerDto(Customer customer, Accounts accounts){

        return CustomerDto.builder().name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .accountsDto(AccountMapper.accountEntityToAccountDto(accounts)).build();

    }


}
