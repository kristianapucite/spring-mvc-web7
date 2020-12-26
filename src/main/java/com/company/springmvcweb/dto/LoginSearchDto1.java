package com.company.springmvcweb.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginSearchDto1 {
    @JsonProperty("e_mail")
    private String eMail;

    @JsonProperty("password")
    private String password;
}
