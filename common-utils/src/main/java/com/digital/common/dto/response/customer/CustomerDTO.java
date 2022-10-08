package com.digital.common.dto.response.customer;

import com.digital.common.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String postcode;

    private String country;

    private AddressType addressType;
}
