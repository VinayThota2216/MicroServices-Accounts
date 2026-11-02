package com.vinay.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountsDto {

    private Long customerId;
    private Long accountNumber;

    @NotBlank(message = "Account type is required")
    private String  accountType;

    @NotBlank(message = "Branch Address is required")
    private String  branchAddress;

}
