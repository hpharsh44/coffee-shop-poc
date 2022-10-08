package com.digital.consumer.entity;

import com.digital.common.dto.response.customer.CustomerDTO;
import com.digital.common.entity.Auditable;
import com.digital.common.enums.AddressType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Auditable {
    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String addressLine;

    private String city;

    private String postcode;

    private String country;

    private String password;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    public static CustomerDTO build(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .mobileNumber(customer.getMobileNumber())
                .addressLine1(customer.getAddressLine())
                .city(customer.getCity())
                .postcode(customer.getPostcode())
                .country(customer.getCountry())
                .addressType(customer.getAddressType())
                .build();
    }
}