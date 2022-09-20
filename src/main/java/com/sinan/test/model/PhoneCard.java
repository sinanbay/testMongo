package com.sinan.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneCard {
    private String id;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;

}
