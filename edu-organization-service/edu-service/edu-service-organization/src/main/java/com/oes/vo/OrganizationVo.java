package com.oes.vo;

import com.oes.model.enums.RegistState;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Date;

public class OrganizationVo {

    /*机构id*/
    private long orgId;

    /*机构名称*/
    private String name;

    /*机构类别*/
    private int categoryId;

    /*机构法人*/
    private String legalPerson;

    /*统一信用代码*/
    private String creditCode;

    /*成立时间*/
    private Date estabDate;

    /*年营业额*/
    private BigDecimal annulSalesVolume;

    /*员工规模*/
    private int staffScale;

    /*招生规模*/
    private int recruitScale;

    /*所在省*/
    private String province;

    /*所在市*/
    private String city;

    /*所在区*/
    private String district;

    /*详细地址*/
    private String address;

    /*经度*/
    private double longitude;

    /*维度*/
    private double latitude;

    /*营业执照编号*/
    private String licenseNumber;

    private String licensePhoto;       //许可证图片

    private String bankAccountPhoto;   //图片

    /*机构联系人*/
    private String contactName;

    /*联系人电话*/
    private String contactPhone;

    /*机构介绍*/
    private String introduction;

    /*教学资质编号*/
    private String qualificationNumber;

    /*教学资质照片*/
    private String qualificationPhoto;  // 图片

    /*宣传首页照片*/
    private String adverPhoto;         //头像

    /*照片数目*/
    private int photoNumber;

    /*培训课程类别数目*/
    private int courseCategoryNumber;
    /**
     * 0-未注册,1-已注册,2-已注销
     */
    @ApiModelProperty(value = "注册状态",dataType = "RegistState",example = "0",allowableValues = "0-未审核,1-审核成功,2-审核失败")
    private RegistState registerState;

    //二维码
    private String qrcode;

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

    public Date getEstabDate() {
        return estabDate;
    }

    public void setEstabDate(Date estabDate) {
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

    public String getLicensePhoto() {
        return licensePhoto;
    }

    public void setLicensePhoto(String licensePhoto) {
        this.licensePhoto = licensePhoto;
    }

    public String getBankAccountPhoto() {
        return bankAccountPhoto;
    }

    public void setBankAccountPhoto(String bankAccountPhoto) {
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

    public String getQualificationPhoto() {
        return qualificationPhoto;
    }

    public void setQualificationPhoto(String qualificationPhoto) {
        this.qualificationPhoto = qualificationPhoto;
    }

    public String getAdverPhoto() {
        return adverPhoto;
    }

    public void setAdverPhoto(String adverPhoto) {
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

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
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
        sb.append(",\"licensePhoto\":\"")
                .append(licensePhoto).append('\"');
        sb.append(",\"bankAccountPhoto\":\"")
                .append(bankAccountPhoto).append('\"');
        sb.append(",\"contactName\":\"")
                .append(contactName).append('\"');
        sb.append(",\"contactPhone\":\"")
                .append(contactPhone).append('\"');
        sb.append(",\"introduction\":\"")
                .append(introduction).append('\"');
        sb.append(",\"qualificationNumber\":\"")
                .append(qualificationNumber).append('\"');
        sb.append(",\"qualificationPhoto\":\"")
                .append(qualificationPhoto).append('\"');
        sb.append(",\"adverPhoto\":\"")
                .append(adverPhoto).append('\"');
        sb.append(",\"photoNumber\":")
                .append(photoNumber);
        sb.append(",\"courseCategoryNumber\":")
                .append(courseCategoryNumber);
        sb.append(",\"registerState\":")
                .append(registerState);
        sb.append(",\"qrcode\":\"")
                .append(qrcode).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
