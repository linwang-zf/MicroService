package com.oes.model.entity;


import com.oes.model.enums.RegistState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * (Organization)实体类
 *
 * @author makejava
 * @since 2020-03-27 14:38:35
 */

@ApiModel(value = "Organization",description = "机构模型")
public class Organization implements Serializable {
    private static final long serialVersionUID = -78556415354374690L;

    private long orgId;
    
    private String name;
    
    private int categoryId;
    
    private String legalPerson;
    
    private String creditCode;
    
    private LocalDate estabDate;
    
    private BigDecimal annulSalesVolume;
    
    private int staffScale;
    
    private int recruitScale;
    
    private String province;
    
    private String city;
    
    private String district;
    
    private String address;
    
    private double longitude;
    
    private double latitude;
    
    private String licenseNumber;
    
    private long licensePhoto;
    
    private long bankAccountPhoto;
    
    private String contactName;
    
    private String contactPhone;
    
    private String introduction;
    
    private String qualificationNumber;
    
    private long qualificationPhoto;
    
    private long adverPhoto;
    
    private int photoNumber;
    
    private int courseCategoryNumber;
    /**
    * 0-未注册,1-已注册,2-已注销
    */
    @ApiModelProperty(value = "注册状态",dataType = "RegistState",example = "0",allowableValues = "0-未审核,1-审核成功,2-审核失败")
    private RegistState registerState;

    private String fail_reason;

    private long qrcode;

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public LocalDate getEstabDate() {
        return estabDate;
    }

    public void setEstabDate(LocalDate estabDate) {
        this.estabDate = estabDate;
    }

    public BigDecimal getAnnulSalesVolume() {
        return annulSalesVolume;
    }

    public void setAnnulSalesVolume(BigDecimal annulSalesVolume) {
        this.annulSalesVolume = annulSalesVolume;
    }

    public int getStaffScale() {
        return staffScale;
    }

    public void setStaffScale(int staffScale) {
        this.staffScale = staffScale;
    }

    public int getRecruitScale() {
        return recruitScale;
    }

    public void setRecruitScale(int recruitScale) {
        this.recruitScale = recruitScale;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public long getLicensePhoto() {
        return licensePhoto;
    }

    public void setLicensePhoto(long licensePhoto) {
        this.licensePhoto = licensePhoto;
    }

    public long getBankAccountPhoto() {
        return bankAccountPhoto;
    }

    public void setBankAccountPhoto(long bankAccountPhoto) {
        this.bankAccountPhoto = bankAccountPhoto;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getQualificationNumber() {
        return qualificationNumber;
    }

    public void setQualificationNumber(String qualificationNumber) {
        this.qualificationNumber = qualificationNumber;
    }

    public long getQualificationPhoto() {
        return qualificationPhoto;
    }

    public void setQualificationPhoto(long qualificationPhoto) {
        this.qualificationPhoto = qualificationPhoto;
    }

    public long getAdverPhoto() {
        return adverPhoto;
    }

    public void setAdverPhoto(long adverPhoto) {
        this.adverPhoto = adverPhoto;
    }

    public int getPhotoNumber() {
        return photoNumber;
    }

    public void setPhotoNumber(int photoNumber) {
        this.photoNumber = photoNumber;
    }

    public int getCourseCategoryNumber() {
        return courseCategoryNumber;
    }

    public void setCourseCategoryNumber(int courseCategoryNumber) {
        this.courseCategoryNumber = courseCategoryNumber;
    }

    public RegistState getRegisterState() {
        return registerState;
    }

    public void setRegisterState(RegistState registerState) {
        this.registerState = registerState;
    }

    public String getFail_reason() {
        return fail_reason;
    }

    public void setFail_reason(String fail_reason) {
        this.fail_reason = fail_reason;
    }

    public long getQrcode() {
        return qrcode;
    }

    public void setQrcode(long qrcode) {
        this.qrcode = qrcode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"orgId\":")
                .append(orgId);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"categoryId\":")
                .append(categoryId);
        sb.append(",\"legalPerson\":\"")
                .append(legalPerson).append('\"');
        sb.append(",\"creditCode\":\"")
                .append(creditCode).append('\"');
        sb.append(",\"estabDate\":\"")
                .append(estabDate).append('\"');
        sb.append(",\"annulSalesVolume\":")
                .append(annulSalesVolume);
        sb.append(",\"staffScale\":")
                .append(staffScale);
        sb.append(",\"recruitScale\":")
                .append(recruitScale);
        sb.append(",\"province\":\"")
                .append(province).append('\"');
        sb.append(",\"city\":\"")
                .append(city).append('\"');
        sb.append(",\"district\":\"")
                .append(district).append('\"');
        sb.append(",\"address\":\"")
                .append(address).append('\"');
        sb.append(",\"longitude\":")
                .append(longitude);
        sb.append(",\"latitude\":")
                .append(latitude);
        sb.append(",\"licenseNumber\":\"")
                .append(licenseNumber).append('\"');
        sb.append(",\"licensePhoto\":")
                .append(licensePhoto);
        sb.append(",\"bankAccountPhoto\":")
                .append(bankAccountPhoto);
        sb.append(",\"contactName\":\"")
                .append(contactName).append('\"');
        sb.append(",\"contactPhone\":\"")
                .append(contactPhone).append('\"');
        sb.append(",\"introduction\":\"")
                .append(introduction).append('\"');
        sb.append(",\"qualificationNumber\":\"")
                .append(qualificationNumber).append('\"');
        sb.append(",\"qualificationPhoto\":")
                .append(qualificationPhoto);
        sb.append(",\"adverPhoto\":")
                .append(adverPhoto);
        sb.append(",\"photoNumber\":")
                .append(photoNumber);
        sb.append(",\"courseCategoryNumber\":")
                .append(courseCategoryNumber);
        sb.append(",\"registerState\":")
                .append(registerState);
        sb.append(",\"fail_reason\":\"")
                .append(fail_reason).append('\"');
        sb.append(",\"qrcode\":")
                .append(qrcode);
        sb.append('}');
        return sb.toString();
    }
}