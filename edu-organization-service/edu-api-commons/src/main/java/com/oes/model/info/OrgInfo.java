package com.oes.model.info;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrgInfo {
    private String orgName;
    private String contactName;
    private String contactPhone;
    private String province;
    private String city;
    private String district;
    private String address;
}
