package com.vinay.accounts.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private Long customerId;

    @NotBlank(message = "Customer name shouldn't be empty or blank or null")
    @Size(min = 4,max = 20 , message = "Customer name  must be contain 4-20 characters")
    private String name;

    @Pattern(regexp = "^[6-9][0-9]{9}$" , message = "Invalid Mobile Number")
    private String mobileNumber;


    @NotBlank(message = "Email is required")
    @Email(regexp = "^[A-Za-z0-9+._-]+@[A-Za-z0-9._-]+\\.(com)$", message = "Invalid Email")
    private String email;


    @Valid
    AccountsDto accountsDto;
}
