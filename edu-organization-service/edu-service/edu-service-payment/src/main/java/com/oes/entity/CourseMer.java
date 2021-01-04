package com.oes.entity;

import com.oes.model.entity.Courses;
import com.oes.model.entity.Organization;
import com.oes.model.entity.Teacher;
import com.oes.util.datatime.DateTimeUtil;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * course_mer
 * @author
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseMer extends Merchandise implements Serializable {
    /**
     * 关联商品表中的mer_id
     */
    //private Integer merId;

    /**
     * 商品类型，此处为“课程”
     */
    //private String merType;

    /**
     * 商品创建时间
     */
    //private Date merTime;

    /**
     * 商品状态：0-下架，1-上架
     */
    //private Byte merState;

    /**
     * 其值等于商品库存
     */
    private Byte courseMerStock;


    /**
     * 与课程信息表中的id关联
     */
    private Integer courseId;

    /**
     * 课程类别id
     */
    private Integer courseTypeId;

    /**
     * 与课程信息表中的name关联
     */
    private String courseName;

    /**
     * 课程简介
     */
    private String courseIntroduction;

    /**
     * 授课场地
     */
    private String site;

    /**
     * 起始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 总共的课时数
     */
    private Byte totalCount;

    /**
     * 每周课数
     */
    private Byte weekCount;

    /**
     * 每次课程时长
     */
    private Byte minutes;

    /**
     * 每周上课时间1
     */
    private String courseTime1;

    /**
     * 每周上课时间2
     */
    private String courseTime2;

    /**
     * 每周上课时间3
     */
    private String courseTime3;

    /**
     * 每周上课时间4
     */
    private String courseTime4;

    /**
     * 每周上课时间5
     */
    private String courseTime5;

    /**
     * 每周上课时间6
     */
    private String courseTime6;

    /**
     * 最大学生数目
     */
    private Byte maxStuCount;

    /**
     * 已上过课时数小于refund_limit可以退钱
     */
    private Byte refundLimit;

    /**
     * 课时费
     */
    private BigDecimal pricePerHour;

    /**
     * 课程价格，其值等于商品价格
     */
    private BigDecimal coursePrice;

    /**
     * 教师id
     */
    private Integer teacherId;

    /**
     * 教师挂靠机构id
     */
    private Integer teacherOrgId;

    /**
     * pictures表外键
     */
    private Integer adverPhoto;

    /**
     * 逗号隔开的关键字
     */
    private String keywords;

    /**
     * 学历
     */
    private String education;

    /**
     * 学位
     */
    private String degree;

    /**
     * 0-兼职，1-全职
     */
    private Byte fullTime;

    /**
     * 机构id
     */
    private Integer orgId;

    /**
     * 机构名
     */
    private String orgName;

    /**
     * 统一信用代码
     */
    private String creditCode;

    /**
     * 机构所在省
     */
    private String province;

    /**
     * 机构所在市
     */
    private String city;

    /**
     * 机构所在行政区
     */
    private String district;

    /**
     * 机构所在详细地址
     */
    private String address;

    /**
     * 机构所在经度
     */
    private Double longitude;

    /**
     * 机构所在纬度
     */
    private Double latitude;

    /**
     * 营业执照照片id
     */
    private Integer licensePhoto;

    /**
     * 机构联系人电话
     */
    private String contactPhone;

    /**
     * 机构简介
     */
    private String orgIntroduction;

    /**
     * 教学资质照片
     */
    private Integer qualificationPhoto;

    /**
     * 预留字段1
     */
    private String info1;

    /**
     * 预留字段2
     */
    private String info2;

    /**
     * 预留字段3
     */
    private String info3;

    /**
     * 预留字段4
     */
    private String info4;

    /**
     * 预留字段5
     */
    private String info5;

    private static final long serialVersionUID = 1L;

    public CourseMer(Integer merId, Integer merStock, Courses course, Teacher teacher, Organization organization) {
        super(merId, "课程", new Date(), new BigDecimal(0), (byte) 0, merStock);
        this.courseMerStock = merStock.byteValue();
        this.courseId = course.getCourseId();
        this.courseTypeId = course.getTypeId();
        this.courseName = course.getName();
        this.courseIntroduction = course.getIntroduction();
        this.site = course.getSite();
        this.startTime = DateTimeUtil.LocalDate2Date(course.getStartTime());
        this.endTime = DateTimeUtil.LocalDate2Date(course.getEndTime());
        this.totalCount = course.getTotalCount().byteValue();
        this.weekCount = course.getWeekCount().byteValue();
        this.minutes = course.getMinutes().byteValue();
        this.courseTime1 = course.getCourseTime1();
        this.courseTime2 = course.getCourseTime2();
        this.courseTime3 = course.getCourseTime3();
        this.courseTime4 = course.getCourseTime4();
        this.courseTime5 = course.getCourseTime5();
        this.courseTime6 = course.getCourseTime6();
        this.maxStuCount = course.getMaxStuCount().byteValue();
        this.refundLimit = course.getRefundLimit().byteValue();
        this.pricePerHour = new BigDecimal(course.getPricePerHour());
        this.coursePrice = this.pricePerHour.multiply(new BigDecimal(this.totalCount));
        this.teacherId = course.getTeacher1Id();
        this.teacherOrgId = (int)teacher.getOrganizationId();
        this.adverPhoto = (int)teacher.getAdverPhoto();
        this.keywords = teacher.getKeywords();
        this.education = teacher.getEducation();
        this.degree = teacher.getDegree();
        if (teacher.isFullTime()) {
            this.fullTime = 1;
        } else {
            this.fullTime = 0;
        }
        this.orgId = Math.toIntExact(organization.getOrgId());
        this.orgName = organization.getName();
        this.creditCode = organization.getCreditCode();
        this.province = organization.getProvince();
        this.city = organization.getCity();
        this.district = organization.getDistrict();
        this.address = organization.getDistrict();
        this.longitude = organization.getLongitude();
        this.latitude = organization.getLatitude();
        this.licensePhoto = Math.toIntExact(organization.getLicensePhoto());
        this.contactPhone = organization.getContactPhone();
        this.orgIntroduction = organization.getIntroduction();
        this.qualificationPhoto = Math.toIntExact(organization.getQualificationPhoto());
    }
    public CourseMer(Integer merId, Integer merStock, Courses course, Teacher teacher, Organization organization, Integer courseType) {
        super(merId, "试听课", new Date(), new BigDecimal(0), (byte) 0, merStock);
        this.courseMerStock = merStock.byteValue();
        this.courseId = course.getCourseId();
        this.courseTypeId = course.getTypeId();
        this.courseName = course.getName();
        this.courseIntroduction = course.getIntroduction();
        this.site = course.getSite();
        this.startTime = DateTimeUtil.LocalDate2Date(course.getStartTime());
        this.endTime = DateTimeUtil.LocalDate2Date(course.getEndTime());
        this.totalCount = course.getTotalCount().byteValue();
        this.weekCount = course.getWeekCount().byteValue();
        this.minutes = course.getMinutes().byteValue();
        this.courseTime1 = course.getCourseTime1();
        this.courseTime2 = course.getCourseTime2();
        this.courseTime3 = course.getCourseTime3();
        this.courseTime4 = course.getCourseTime4();
        this.courseTime5 = course.getCourseTime5();
        this.courseTime6 = course.getCourseTime6();
        this.maxStuCount = course.getMaxStuCount().byteValue();
        this.refundLimit = course.getRefundLimit().byteValue();
        this.pricePerHour = new BigDecimal(course.getPricePerHour());
        this.coursePrice = this.pricePerHour.multiply(new BigDecimal(this.totalCount));
        this.teacherId = course.getTeacher1Id();
        this.teacherOrgId = (int)teacher.getOrganizationId();
        this.adverPhoto = (int)teacher.getAdverPhoto();
        this.keywords = teacher.getKeywords();
        this.education = teacher.getEducation();
        this.degree = teacher.getDegree();
        if (teacher.isFullTime()) {
            this.fullTime = 1;
        } else {
            this.fullTime = 0;
        }
        this.orgId = Math.toIntExact(organization.getOrgId());
        this.orgName = organization.getName();
        this.creditCode = organization.getCreditCode();
        this.province = organization.getProvince();
        this.city = organization.getCity();
        this.district = organization.getDistrict();
        this.address = organization.getDistrict();
        this.longitude = organization.getLongitude();
        this.latitude = organization.getLatitude();
        this.licensePhoto = Math.toIntExact(organization.getLicensePhoto());
        this.contactPhone = organization.getContactPhone();
        this.orgIntroduction = organization.getIntroduction();
        this.qualificationPhoto = Math.toIntExact(organization.getQualificationPhoto());
    }
}