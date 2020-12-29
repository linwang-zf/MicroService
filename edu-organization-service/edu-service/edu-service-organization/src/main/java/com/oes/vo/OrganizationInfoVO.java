package com.oes.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class OrganizationInfoVO {
    /*机构id*/
    private long orgId;

    /*机构名称*/
    private String name;

    /*机构法人*/
    private String legalPerson;

    /*统一信用代码*/
    private String creditCode;

    /*成立时间*/
    private LocalDate estabDate;

    /*年营业额*/
    private BigDecimal annulSalesVolume;

    /*员工规模*/
    private int staffScale;

    /*招生规模*/
    private int recruitScale;

    /*详细地址*/
    private String orgAddress;

    /*机构联系人*/
    private String contactName;

    /*联系人电话*/
    private String contactPhone;

    private String fail_reason;
}
