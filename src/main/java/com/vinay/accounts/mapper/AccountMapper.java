package com.vinay.accounts.mapper;

import com.vinay.accounts.dto.AccountsDto;
import com.vinay.accounts.dto.CustomerDto;
import com.vinay.accounts.entity.Accounts;

public class AccountMapper {
    public static Accounts customerDtoToAccountEntity(
            CustomerDto customerDto,
            Long randomAccNumber) {

        return Accounts.builder()
                .customerId(customerDto.getCustomerId())
                .accountNumber(randomAccNumber)
                .accountType(customerDto.getAccountsDto().getAccountType())
                .branchAddress(customerDto.getAccountsDto().getBranchAddress())
                .build();
    }

    public static AccountsDto accountEntityToAccountDto(Accounts account) {

        return AccountsDto.builder().accountType(account.getAccountType())
                .branchAddress(account.getBranchAddress())
                .accountNumber(account.getAccountNumber())
                .build();

    }
}
