
package com.vinay.accounts.service;

import com.vinay.accounts.entity.Accounts;
import com.vinay.accounts.entity.Customer;
import com.vinay.accounts.exceptions.CustomerAlreadyExistsException;
import com.vinay.accounts.exceptions.CustomerIsNotFoundException;
import com.vinay.accounts.dto.AccountsDto;
import com.vinay.accounts.dto.CustomerDto;
import com.vinay.accounts.mapper.AccountMapper;
import com.vinay.accounts.mapper.CustomerMapper;
import com.vinay.accounts.repository.AccountsRepository;
import com.vinay.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountsService {

    private final CustomerRepository customerRepository;
    private final AccountsRepository accountsRepository;


    @Override
    public void createAccount(CustomerDto customerDto) {

        try {
                Optional<Customer> customer = customerRepository
                        .findByMobileNumber(customerDto.getMobileNumber());

                if (customer.isPresent()) {
                    throw new CustomerAlreadyExistsException(
                            "Customer already registered with given mobileNumber "
                                    + customerDto.getMobileNumber());
                }



                Customer customerEntity=CustomerMapper.customerDtoToCustomerEntity(customerDto);


                // Save customer and get generated customer ID
                Customer customerResults = customerRepository.save(customerEntity);

                customerDto.setCustomerId(customerResults.getCustomerId());


                long randomAccNumber =
                        1000000000L + new Random().nextInt(900000000);

                Accounts accountsEntity = AccountMapper.customerDtoToAccountEntity(customerDto,randomAccNumber);

                accountsRepository.save(accountsEntity);


        } catch (CustomerAlreadyExistsException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    @Override
    public CustomerDto getCustomerByMobileNumber(String mobileNumber) {
        try {
            Optional<Customer> customer = customerRepository
                    .findByMobileNumber(mobileNumber);

            if (customer.isEmpty()) {
                throw new CustomerIsNotFoundException(
                        "Customer is not found by this mobile number"+ mobileNumber);
            }

            Accounts existingAccount = accountsRepository
                    .findByCustomerId(customer.get().getCustomerId());

            CustomerDto customerDto = new CustomerDto();

            customerDto.setCustomerId(customer.get().getCustomerId());
            customerDto.setEmail(customer.get().getEmail());
            customerDto.setName(customer.get().getName());
            customerDto.setMobileNumber(customer.get().getMobileNumber());

            customerDto.setAccountsDto(AccountsDto.builder()
                            .customerId(customer.get().getCustomerId())
                            .accountNumber(existingAccount.getAccountNumber())
                            .branchAddress(existingAccount.getBranchAddress())
                            .accountType(existingAccount.getAccountType())
                            .build());



            return customerDto;

        } catch (CustomerIsNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }


    @Override
    public boolean updateAccount(String mobileNumber, CustomerDto customerDto) {

        Optional<Customer> customer =
                customerRepository.findByMobileNumber(mobileNumber);

        if (customer.isEmpty()) {
            throw new CustomerIsNotFoundException(
                    "Customer not found with mobile number: " + mobileNumber);
        }

        Customer existingCustomer = customer.get();

        existingCustomer.setName(customerDto.getName());
        existingCustomer.setEmail(customerDto.getEmail());

        customerRepository.save(existingCustomer);


        Accounts existingAccount = accountsRepository
                    .findByCustomerId(existingCustomer.getCustomerId());

            if (existingAccount == null) {
                return false;
            }

            existingAccount.setAccountType(customerDto.getAccountsDto().getAccountType());
            existingAccount.setBranchAddress(customerDto.getAccountsDto().getBranchAddress());


            accountsRepository.save(existingAccount);


        return true;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        try {
            Optional<Customer> customer = customerRepository
                    .findByMobileNumber(mobileNumber);

            if (customer.isEmpty()) {
                throw new CustomerIsNotFoundException(
                        "Customer is not found by this mobile number"+ mobileNumber);
            }

            Accounts existingAccount = accountsRepository
                    .findByCustomerId(customer.get().getCustomerId());

            if (existingAccount == null) {
                return false;
            }

            accountsRepository.delete(existingAccount);

            customerRepository.delete(customer.get());

            return true;

        } catch (CustomerIsNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }
}

