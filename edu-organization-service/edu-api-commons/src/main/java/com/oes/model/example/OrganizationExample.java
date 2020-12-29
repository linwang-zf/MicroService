package com.oes.model.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrganizationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrganizationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("org_id is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("org_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(Integer value) {
            addCriterion("org_id =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(Integer value) {
            addCriterion("org_id <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(Integer value) {
            addCriterion("org_id >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("org_id >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(Integer value) {
            addCriterion("org_id <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("org_id <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<Integer> values) {
            addCriterion("org_id in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<Integer> values) {
            addCriterion("org_id not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(Integer value1, Integer value2) {
            addCriterion("org_id between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("org_id not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(Short value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(Short value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(Short value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(Short value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(Short value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(Short value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<Short> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<Short> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(Short value1, Short value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(Short value1, Short value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIsNull() {
            addCriterion("legal_person is null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIsNotNull() {
            addCriterion("legal_person is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonEqualTo(String value) {
            addCriterion("legal_person =", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotEqualTo(String value) {
            addCriterion("legal_person <>", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonGreaterThan(String value) {
            addCriterion("legal_person >", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonGreaterThanOrEqualTo(String value) {
            addCriterion("legal_person >=", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonLessThan(String value) {
            addCriterion("legal_person <", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonLessThanOrEqualTo(String value) {
            addCriterion("legal_person <=", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonLike(String value) {
            addCriterion("legal_person like", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotLike(String value) {
            addCriterion("legal_person not like", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIn(List<String> values) {
            addCriterion("legal_person in", values, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotIn(List<String> values) {
            addCriterion("legal_person not in", values, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonBetween(String value1, String value2) {
            addCriterion("legal_person between", value1, value2, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotBetween(String value1, String value2) {
            addCriterion("legal_person not between", value1, value2, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andCreditCodeIsNull() {
            addCriterion("credit_code is null");
            return (Criteria) this;
        }

        public Criteria andCreditCodeIsNotNull() {
            addCriterion("credit_code is not null");
            return (Criteria) this;
        }

        public Criteria andCreditCodeEqualTo(String value) {
            addCriterion("credit_code =", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotEqualTo(String value) {
            addCriterion("credit_code <>", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeGreaterThan(String value) {
            addCriterion("credit_code >", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeGreaterThanOrEqualTo(String value) {
            addCriterion("credit_code >=", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeLessThan(String value) {
            addCriterion("credit_code <", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeLessThanOrEqualTo(String value) {
            addCriterion("credit_code <=", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeLike(String value) {
            addCriterion("credit_code like", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotLike(String value) {
            addCriterion("credit_code not like", value, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeIn(List<String> values) {
            addCriterion("credit_code in", values, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotIn(List<String> values) {
            addCriterion("credit_code not in", values, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeBetween(String value1, String value2) {
            addCriterion("credit_code between", value1, value2, "creditCode");
            return (Criteria) this;
        }

        public Criteria andCreditCodeNotBetween(String value1, String value2) {
            addCriterion("credit_code not between", value1, value2, "creditCode");
            return (Criteria) this;
        }

        public Criteria andEstabDateIsNull() {
            addCriterion("estab_date is null");
            return (Criteria) this;
        }

        public Criteria andEstabDateIsNotNull() {
            addCriterion("estab_date is not null");
            return (Criteria) this;
        }

        public Criteria andEstabDateEqualTo(Date value) {
            addCriterionForJDBCDate("estab_date =", value, "estabDate");
            return (Criteria) this;
        }

        public Criteria andEstabDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("estab_date <>", value, "estabDate");
            return (Criteria) this;
        }

        public Criteria andEstabDateGreaterThan(Date value) {
            addCriterionForJDBCDate("estab_date >", value, "estabDate");
            return (Criteria) this;
        }

        public Criteria andEstabDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("estab_date >=", value, "estabDate");
            return (Criteria) this;
        }

        public Criteria andEstabDateLessThan(Date value) {
            addCriterionForJDBCDate("estab_date <", value, "estabDate");
            return (Criteria) this;
        }

        public Criteria andEstabDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("estab_date <=", value, "estabDate");
            return (Criteria) this;
        }

        public Criteria andEstabDateIn(List<Date> values) {
            addCriterionForJDBCDate("estab_date in", values, "estabDate");
            return (Criteria) this;
        }

        public Criteria andEstabDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("estab_date not in", values, "estabDate");
            return (Criteria) this;
        }

        public Criteria andEstabDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("estab_date between", value1, value2, "estabDate");
            return (Criteria) this;
        }

        public Criteria andEstabDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("estab_date not between", value1, value2, "estabDate");
            return (Criteria) this;
        }

        public Criteria andAnnulSalesVolumeIsNull() {
            addCriterion("annul_sales_volume is null");
            return (Criteria) this;
        }

        public Criteria andAnnulSalesVolumeIsNotNull() {
            addCriterion("annul_sales_volume is not null");
            return (Criteria) this;
        }

        public Criteria andAnnulSalesVolumeEqualTo(Long value) {
            addCriterion("annul_sales_volume =", value, "annulSalesVolume");
            return (Criteria) this;
        }

        public Criteria andAnnulSalesVolumeNotEqualTo(Long value) {
            addCriterion("annul_sales_volume <>", value, "annulSalesVolume");
            return (Criteria) this;
        }

        public Criteria andAnnulSalesVolumeGreaterThan(Long value) {
            addCriterion("annul_sales_volume >", value, "annulSalesVolume");
            return (Criteria) this;
        }

        public Criteria andAnnulSalesVolumeGreaterThanOrEqualTo(Long value) {
            addCriterion("annul_sales_volume >=", value, "annulSalesVolume");
            return (Criteria) this;
        }

        public Criteria andAnnulSalesVolumeLessThan(Long value) {
            addCriterion("annul_sales_volume <", value, "annulSalesVolume");
            return (Criteria) this;
        }

        public Criteria andAnnulSalesVolumeLessThanOrEqualTo(Long value) {
            addCriterion("annul_sales_volume <=", value, "annulSalesVolume");
            return (Criteria) this;
        }

        public Criteria andAnnulSalesVolumeIn(List<Long> values) {
            addCriterion("annul_sales_volume in", values, "annulSalesVolume");
            return (Criteria) this;
        }

        public Criteria andAnnulSalesVolumeNotIn(List<Long> values) {
            addCriterion("annul_sales_volume not in", values, "annulSalesVolume");
            return (Criteria) this;
        }

        public Criteria andAnnulSalesVolumeBetween(Long value1, Long value2) {
            addCriterion("annul_sales_volume between", value1, value2, "annulSalesVolume");
            return (Criteria) this;
        }

        public Criteria andAnnulSalesVolumeNotBetween(Long value1, Long value2) {
            addCriterion("annul_sales_volume not between", value1, value2, "annulSalesVolume");
            return (Criteria) this;
        }

        public Criteria andStaffScaleIsNull() {
            addCriterion("staff_scale is null");
            return (Criteria) this;
        }

        public Criteria andStaffScaleIsNotNull() {
            addCriterion("staff_scale is not null");
            return (Criteria) this;
        }

        public Criteria andStaffScaleEqualTo(Integer value) {
            addCriterion("staff_scale =", value, "staffScale");
            return (Criteria) this;
        }

        public Criteria andStaffScaleNotEqualTo(Integer value) {
            addCriterion("staff_scale <>", value, "staffScale");
            return (Criteria) this;
        }

        public Criteria andStaffScaleGreaterThan(Integer value) {
            addCriterion("staff_scale >", value, "staffScale");
            return (Criteria) this;
        }

        public Criteria andStaffScaleGreaterThanOrEqualTo(Integer value) {
            addCriterion("staff_scale >=", value, "staffScale");
            return (Criteria) this;
        }

        public Criteria andStaffScaleLessThan(Integer value) {
            addCriterion("staff_scale <", value, "staffScale");
            return (Criteria) this;
        }

        public Criteria andStaffScaleLessThanOrEqualTo(Integer value) {
            addCriterion("staff_scale <=", value, "staffScale");
            return (Criteria) this;
        }

        public Criteria andStaffScaleIn(List<Integer> values) {
            addCriterion("staff_scale in", values, "staffScale");
            return (Criteria) this;
        }

        public Criteria andStaffScaleNotIn(List<Integer> values) {
            addCriterion("staff_scale not in", values, "staffScale");
            return (Criteria) this;
        }

        public Criteria andStaffScaleBetween(Integer value1, Integer value2) {
            addCriterion("staff_scale between", value1, value2, "staffScale");
            return (Criteria) this;
        }

        public Criteria andStaffScaleNotBetween(Integer value1, Integer value2) {
            addCriterion("staff_scale not between", value1, value2, "staffScale");
            return (Criteria) this;
        }

        public Criteria andRecruitScaleIsNull() {
            addCriterion("recruit_scale is null");
            return (Criteria) this;
        }

        public Criteria andRecruitScaleIsNotNull() {
            addCriterion("recruit_scale is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitScaleEqualTo(Integer value) {
            addCriterion("recruit_scale =", value, "recruitScale");
            return (Criteria) this;
        }

        public Criteria andRecruitScaleNotEqualTo(Integer value) {
            addCriterion("recruit_scale <>", value, "recruitScale");
            return (Criteria) this;
        }

        public Criteria andRecruitScaleGreaterThan(Integer value) {
            addCriterion("recruit_scale >", value, "recruitScale");
            return (Criteria) this;
        }

        public Criteria andRecruitScaleGreaterThanOrEqualTo(Integer value) {
            addCriterion("recruit_scale >=", value, "recruitScale");
            return (Criteria) this;
        }

        public Criteria andRecruitScaleLessThan(Integer value) {
            addCriterion("recruit_scale <", value, "recruitScale");
            return (Criteria) this;
        }

        public Criteria andRecruitScaleLessThanOrEqualTo(Integer value) {
            addCriterion("recruit_scale <=", value, "recruitScale");
            return (Criteria) this;
        }

        public Criteria andRecruitScaleIn(List<Integer> values) {
            addCriterion("recruit_scale in", values, "recruitScale");
            return (Criteria) this;
        }

        public Criteria andRecruitScaleNotIn(List<Integer> values) {
            addCriterion("recruit_scale not in", values, "recruitScale");
            return (Criteria) this;
        }

        public Criteria andRecruitScaleBetween(Integer value1, Integer value2) {
            addCriterion("recruit_scale between", value1, value2, "recruitScale");
            return (Criteria) this;
        }

        public Criteria andRecruitScaleNotBetween(Integer value1, Integer value2) {
            addCriterion("recruit_scale not between", value1, value2, "recruitScale");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNull() {
            addCriterion("district is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNotNull() {
            addCriterion("district is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictEqualTo(String value) {
            addCriterion("district =", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotEqualTo(String value) {
            addCriterion("district <>", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThan(String value) {
            addCriterion("district >", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThanOrEqualTo(String value) {
            addCriterion("district >=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThan(String value) {
            addCriterion("district <", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThanOrEqualTo(String value) {
            addCriterion("district <=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLike(String value) {
            addCriterion("district like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotLike(String value) {
            addCriterion("district not like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictIn(List<String> values) {
            addCriterion("district in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotIn(List<String> values) {
            addCriterion("district not in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictBetween(String value1, String value2) {
            addCriterion("district between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotBetween(String value1, String value2) {
            addCriterion("district not between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(Double value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(Double value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(Double value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(Double value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<Double> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<Double> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(Double value1, Double value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Double value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Double value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Double value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Double value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Double> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Double> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Double value1, Double value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberIsNull() {
            addCriterion("license_number is null");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberIsNotNull() {
            addCriterion("license_number is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberEqualTo(String value) {
            addCriterion("license_number =", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberNotEqualTo(String value) {
            addCriterion("license_number <>", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberGreaterThan(String value) {
            addCriterion("license_number >", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberGreaterThanOrEqualTo(String value) {
            addCriterion("license_number >=", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberLessThan(String value) {
            addCriterion("license_number <", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberLessThanOrEqualTo(String value) {
            addCriterion("license_number <=", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberLike(String value) {
            addCriterion("license_number like", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberNotLike(String value) {
            addCriterion("license_number not like", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberIn(List<String> values) {
            addCriterion("license_number in", values, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberNotIn(List<String> values) {
            addCriterion("license_number not in", values, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberBetween(String value1, String value2) {
            addCriterion("license_number between", value1, value2, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberNotBetween(String value1, String value2) {
            addCriterion("license_number not between", value1, value2, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicensePhotoIsNull() {
            addCriterion("license_photo is null");
            return (Criteria) this;
        }

        public Criteria andLicensePhotoIsNotNull() {
            addCriterion("license_photo is not null");
            return (Criteria) this;
        }

        public Criteria andLicensePhotoEqualTo(Integer value) {
            addCriterion("license_photo =", value, "licensePhoto");
            return (Criteria) this;
        }

        public Criteria andLicensePhotoNotEqualTo(Integer value) {
            addCriterion("license_photo <>", value, "licensePhoto");
            return (Criteria) this;
        }

        public Criteria andLicensePhotoGreaterThan(Integer value) {
            addCriterion("license_photo >", value, "licensePhoto");
            return (Criteria) this;
        }

        public Criteria andLicensePhotoGreaterThanOrEqualTo(Integer value) {
            addCriterion("license_photo >=", value, "licensePhoto");
            return (Criteria) this;
        }

        public Criteria andLicensePhotoLessThan(Integer value) {
            addCriterion("license_photo <", value, "licensePhoto");
            return (Criteria) this;
        }

        public Criteria andLicensePhotoLessThanOrEqualTo(Integer value) {
            addCriterion("license_photo <=", value, "licensePhoto");
            return (Criteria) this;
        }

        public Criteria andLicensePhotoIn(List<Integer> values) {
            addCriterion("license_photo in", values, "licensePhoto");
            return (Criteria) this;
        }

        public Criteria andLicensePhotoNotIn(List<Integer> values) {
            addCriterion("license_photo not in", values, "licensePhoto");
            return (Criteria) this;
        }

        public Criteria andLicensePhotoBetween(Integer value1, Integer value2) {
            addCriterion("license_photo between", value1, value2, "licensePhoto");
            return (Criteria) this;
        }

        public Criteria andLicensePhotoNotBetween(Integer value1, Integer value2) {
            addCriterion("license_photo not between", value1, value2, "licensePhoto");
            return (Criteria) this;
        }

        public Criteria andBankAccountPhotoIsNull() {
            addCriterion("bank_account_photo is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountPhotoIsNotNull() {
            addCriterion("bank_account_photo is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountPhotoEqualTo(Integer value) {
            addCriterion("bank_account_photo =", value, "bankAccountPhoto");
            return (Criteria) this;
        }

        public Criteria andBankAccountPhotoNotEqualTo(Integer value) {
            addCriterion("bank_account_photo <>", value, "bankAccountPhoto");
            return (Criteria) this;
        }

        public Criteria andBankAccountPhotoGreaterThan(Integer value) {
            addCriterion("bank_account_photo >", value, "bankAccountPhoto");
            return (Criteria) this;
        }

        public Criteria andBankAccountPhotoGreaterThanOrEqualTo(Integer value) {
            addCriterion("bank_account_photo >=", value, "bankAccountPhoto");
            return (Criteria) this;
        }

        public Criteria andBankAccountPhotoLessThan(Integer value) {
            addCriterion("bank_account_photo <", value, "bankAccountPhoto");
            return (Criteria) this;
        }

        public Criteria andBankAccountPhotoLessThanOrEqualTo(Integer value) {
            addCriterion("bank_account_photo <=", value, "bankAccountPhoto");
            return (Criteria) this;
        }

        public Criteria andBankAccountPhotoIn(List<Integer> values) {
            addCriterion("bank_account_photo in", values, "bankAccountPhoto");
            return (Criteria) this;
        }

        public Criteria andBankAccountPhotoNotIn(List<Integer> values) {
            addCriterion("bank_account_photo not in", values, "bankAccountPhoto");
            return (Criteria) this;
        }

        public Criteria andBankAccountPhotoBetween(Integer value1, Integer value2) {
            addCriterion("bank_account_photo between", value1, value2, "bankAccountPhoto");
            return (Criteria) this;
        }

        public Criteria andBankAccountPhotoNotBetween(Integer value1, Integer value2) {
            addCriterion("bank_account_photo not between", value1, value2, "bankAccountPhoto");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNull() {
            addCriterion("contact_name is null");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNotNull() {
            addCriterion("contact_name is not null");
            return (Criteria) this;
        }

        public Criteria andContactNameEqualTo(String value) {
            addCriterion("contact_name =", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotEqualTo(String value) {
            addCriterion("contact_name <>", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThan(String value) {
            addCriterion("contact_name >", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("contact_name >=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThan(String value) {
            addCriterion("contact_name <", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThanOrEqualTo(String value) {
            addCriterion("contact_name <=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLike(String value) {
            addCriterion("contact_name like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotLike(String value) {
            addCriterion("contact_name not like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameIn(List<String> values) {
            addCriterion("contact_name in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotIn(List<String> values) {
            addCriterion("contact_name not in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameBetween(String value1, String value2) {
            addCriterion("contact_name between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotBetween(String value1, String value2) {
            addCriterion("contact_name not between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNull() {
            addCriterion("contact_phone is null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNotNull() {
            addCriterion("contact_phone is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneEqualTo(String value) {
            addCriterion("contact_phone =", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotEqualTo(String value) {
            addCriterion("contact_phone <>", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThan(String value) {
            addCriterion("contact_phone >", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("contact_phone >=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThan(String value) {
            addCriterion("contact_phone <", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("contact_phone <=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLike(String value) {
            addCriterion("contact_phone like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotLike(String value) {
            addCriterion("contact_phone not like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIn(List<String> values) {
            addCriterion("contact_phone in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotIn(List<String> values) {
            addCriterion("contact_phone not in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneBetween(String value1, String value2) {
            addCriterion("contact_phone between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotBetween(String value1, String value2) {
            addCriterion("contact_phone not between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andIntroductionIsNull() {
            addCriterion("introduction is null");
            return (Criteria) this;
        }

        public Criteria andIntroductionIsNotNull() {
            addCriterion("introduction is not null");
            return (Criteria) this;
        }

        public Criteria andIntroductionEqualTo(String value) {
            addCriterion("introduction =", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotEqualTo(String value) {
            addCriterion("introduction <>", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionGreaterThan(String value) {
            addCriterion("introduction >", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("introduction >=", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLessThan(String value) {
            addCriterion("introduction <", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLessThanOrEqualTo(String value) {
            addCriterion("introduction <=", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLike(String value) {
            addCriterion("introduction like", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotLike(String value) {
            addCriterion("introduction not like", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionIn(List<String> values) {
            addCriterion("introduction in", values, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotIn(List<String> values) {
            addCriterion("introduction not in", values, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionBetween(String value1, String value2) {
            addCriterion("introduction between", value1, value2, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotBetween(String value1, String value2) {
            addCriterion("introduction not between", value1, value2, "introduction");
            return (Criteria) this;
        }

        public Criteria andQualificationNumberIsNull() {
            addCriterion("qualification_number is null");
            return (Criteria) this;
        }

        public Criteria andQualificationNumberIsNotNull() {
            addCriterion("qualification_number is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationNumberEqualTo(String value) {
            addCriterion("qualification_number =", value, "qualificationNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationNumberNotEqualTo(String value) {
            addCriterion("qualification_number <>", value, "qualificationNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationNumberGreaterThan(String value) {
            addCriterion("qualification_number >", value, "qualificationNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationNumberGreaterThanOrEqualTo(String value) {
            addCriterion("qualification_number >=", value, "qualificationNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationNumberLessThan(String value) {
            addCriterion("qualification_number <", value, "qualificationNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationNumberLessThanOrEqualTo(String value) {
            addCriterion("qualification_number <=", value, "qualificationNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationNumberLike(String value) {
            addCriterion("qualification_number like", value, "qualificationNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationNumberNotLike(String value) {
            addCriterion("qualification_number not like", value, "qualificationNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationNumberIn(List<String> values) {
            addCriterion("qualification_number in", values, "qualificationNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationNumberNotIn(List<String> values) {
            addCriterion("qualification_number not in", values, "qualificationNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationNumberBetween(String value1, String value2) {
            addCriterion("qualification_number between", value1, value2, "qualificationNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationNumberNotBetween(String value1, String value2) {
            addCriterion("qualification_number not between", value1, value2, "qualificationNumber");
            return (Criteria) this;
        }

        public Criteria andQualificationPhotoIsNull() {
            addCriterion("qualification_photo is null");
            return (Criteria) this;
        }

        public Criteria andQualificationPhotoIsNotNull() {
            addCriterion("qualification_photo is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationPhotoEqualTo(Integer value) {
            addCriterion("qualification_photo =", value, "qualificationPhoto");
            return (Criteria) this;
        }

        public Criteria andQualificationPhotoNotEqualTo(Integer value) {
            addCriterion("qualification_photo <>", value, "qualificationPhoto");
            return (Criteria) this;
        }

        public Criteria andQualificationPhotoGreaterThan(Integer value) {
            addCriterion("qualification_photo >", value, "qualificationPhoto");
            return (Criteria) this;
        }

        public Criteria andQualificationPhotoGreaterThanOrEqualTo(Integer value) {
            addCriterion("qualification_photo >=", value, "qualificationPhoto");
            return (Criteria) this;
        }

        public Criteria andQualificationPhotoLessThan(Integer value) {
            addCriterion("qualification_photo <", value, "qualificationPhoto");
            return (Criteria) this;
        }

        public Criteria andQualificationPhotoLessThanOrEqualTo(Integer value) {
            addCriterion("qualification_photo <=", value, "qualificationPhoto");
            return (Criteria) this;
        }

        public Criteria andQualificationPhotoIn(List<Integer> values) {
            addCriterion("qualification_photo in", values, "qualificationPhoto");
            return (Criteria) this;
        }

        public Criteria andQualificationPhotoNotIn(List<Integer> values) {
            addCriterion("qualification_photo not in", values, "qualificationPhoto");
            return (Criteria) this;
        }

        public Criteria andQualificationPhotoBetween(Integer value1, Integer value2) {
            addCriterion("qualification_photo between", value1, value2, "qualificationPhoto");
            return (Criteria) this;
        }

        public Criteria andQualificationPhotoNotBetween(Integer value1, Integer value2) {
            addCriterion("qualification_photo not between", value1, value2, "qualificationPhoto");
            return (Criteria) this;
        }

        public Criteria andAdverPhotoIsNull() {
            addCriterion("adver_photo is null");
            return (Criteria) this;
        }

        public Criteria andAdverPhotoIsNotNull() {
            addCriterion("adver_photo is not null");
            return (Criteria) this;
        }

        public Criteria andAdverPhotoEqualTo(Integer value) {
            addCriterion("adver_photo =", value, "adverPhoto");
            return (Criteria) this;
        }

        public Criteria andAdverPhotoNotEqualTo(Integer value) {
            addCriterion("adver_photo <>", value, "adverPhoto");
            return (Criteria) this;
        }

        public Criteria andAdverPhotoGreaterThan(Integer value) {
            addCriterion("adver_photo >", value, "adverPhoto");
            return (Criteria) this;
        }

        public Criteria andAdverPhotoGreaterThanOrEqualTo(Integer value) {
            addCriterion("adver_photo >=", value, "adverPhoto");
            return (Criteria) this;
        }

        public Criteria andAdverPhotoLessThan(Integer value) {
            addCriterion("adver_photo <", value, "adverPhoto");
            return (Criteria) this;
        }

        public Criteria andAdverPhotoLessThanOrEqualTo(Integer value) {
            addCriterion("adver_photo <=", value, "adverPhoto");
            return (Criteria) this;
        }

        public Criteria andAdverPhotoIn(List<Integer> values) {
            addCriterion("adver_photo in", values, "adverPhoto");
            return (Criteria) this;
        }

        public Criteria andAdverPhotoNotIn(List<Integer> values) {
            addCriterion("adver_photo not in", values, "adverPhoto");
            return (Criteria) this;
        }

        public Criteria andAdverPhotoBetween(Integer value1, Integer value2) {
            addCriterion("adver_photo between", value1, value2, "adverPhoto");
            return (Criteria) this;
        }

        public Criteria andAdverPhotoNotBetween(Integer value1, Integer value2) {
            addCriterion("adver_photo not between", value1, value2, "adverPhoto");
            return (Criteria) this;
        }

        public Criteria andPhotoNumberIsNull() {
            addCriterion("photo_number is null");
            return (Criteria) this;
        }

        public Criteria andPhotoNumberIsNotNull() {
            addCriterion("photo_number is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoNumberEqualTo(Byte value) {
            addCriterion("photo_number =", value, "photoNumber");
            return (Criteria) this;
        }

        public Criteria andPhotoNumberNotEqualTo(Byte value) {
            addCriterion("photo_number <>", value, "photoNumber");
            return (Criteria) this;
        }

        public Criteria andPhotoNumberGreaterThan(Byte value) {
            addCriterion("photo_number >", value, "photoNumber");
            return (Criteria) this;
        }

        public Criteria andPhotoNumberGreaterThanOrEqualTo(Byte value) {
            addCriterion("photo_number >=", value, "photoNumber");
            return (Criteria) this;
        }

        public Criteria andPhotoNumberLessThan(Byte value) {
            addCriterion("photo_number <", value, "photoNumber");
            return (Criteria) this;
        }

        public Criteria andPhotoNumberLessThanOrEqualTo(Byte value) {
            addCriterion("photo_number <=", value, "photoNumber");
            return (Criteria) this;
        }

        public Criteria andPhotoNumberIn(List<Byte> values) {
            addCriterion("photo_number in", values, "photoNumber");
            return (Criteria) this;
        }

        public Criteria andPhotoNumberNotIn(List<Byte> values) {
            addCriterion("photo_number not in", values, "photoNumber");
            return (Criteria) this;
        }

        public Criteria andPhotoNumberBetween(Byte value1, Byte value2) {
            addCriterion("photo_number between", value1, value2, "photoNumber");
            return (Criteria) this;
        }

        public Criteria andPhotoNumberNotBetween(Byte value1, Byte value2) {
            addCriterion("photo_number not between", value1, value2, "photoNumber");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNumberIsNull() {
            addCriterion("course_category_number is null");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNumberIsNotNull() {
            addCriterion("course_category_number is not null");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNumberEqualTo(Byte value) {
            addCriterion("course_category_number =", value, "courseCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNumberNotEqualTo(Byte value) {
            addCriterion("course_category_number <>", value, "courseCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNumberGreaterThan(Byte value) {
            addCriterion("course_category_number >", value, "courseCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNumberGreaterThanOrEqualTo(Byte value) {
            addCriterion("course_category_number >=", value, "courseCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNumberLessThan(Byte value) {
            addCriterion("course_category_number <", value, "courseCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNumberLessThanOrEqualTo(Byte value) {
            addCriterion("course_category_number <=", value, "courseCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNumberIn(List<Byte> values) {
            addCriterion("course_category_number in", values, "courseCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNumberNotIn(List<Byte> values) {
            addCriterion("course_category_number not in", values, "courseCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNumberBetween(Byte value1, Byte value2) {
            addCriterion("course_category_number between", value1, value2, "courseCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andCourseCategoryNumberNotBetween(Byte value1, Byte value2) {
            addCriterion("course_category_number not between", value1, value2, "courseCategoryNumber");
            return (Criteria) this;
        }

        public Criteria andRegisterStateIsNull() {
            addCriterion("register_state is null");
            return (Criteria) this;
        }

        public Criteria andRegisterStateIsNotNull() {
            addCriterion("register_state is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterStateEqualTo(Byte value) {
            addCriterion("register_state =", value, "registerState");
            return (Criteria) this;
        }

        public Criteria andRegisterStateNotEqualTo(Byte value) {
            addCriterion("register_state <>", value, "registerState");
            return (Criteria) this;
        }

        public Criteria andRegisterStateGreaterThan(Byte value) {
            addCriterion("register_state >", value, "registerState");
            return (Criteria) this;
        }

        public Criteria andRegisterStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("register_state >=", value, "registerState");
            return (Criteria) this;
        }

        public Criteria andRegisterStateLessThan(Byte value) {
            addCriterion("register_state <", value, "registerState");
            return (Criteria) this;
        }

        public Criteria andRegisterStateLessThanOrEqualTo(Byte value) {
            addCriterion("register_state <=", value, "registerState");
            return (Criteria) this;
        }

        public Criteria andRegisterStateIn(List<Byte> values) {
            addCriterion("register_state in", values, "registerState");
            return (Criteria) this;
        }

        public Criteria andRegisterStateNotIn(List<Byte> values) {
            addCriterion("register_state not in", values, "registerState");
            return (Criteria) this;
        }

        public Criteria andRegisterStateBetween(Byte value1, Byte value2) {
            addCriterion("register_state between", value1, value2, "registerState");
            return (Criteria) this;
        }

        public Criteria andRegisterStateNotBetween(Byte value1, Byte value2) {
            addCriterion("register_state not between", value1, value2, "registerState");
            return (Criteria) this;
        }

        public Criteria andFailReasonIsNull() {
            addCriterion("fail_reason is null");
            return (Criteria) this;
        }

        public Criteria andFailReasonIsNotNull() {
            addCriterion("fail_reason is not null");
            return (Criteria) this;
        }

        public Criteria andFailReasonEqualTo(String value) {
            addCriterion("fail_reason =", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonNotEqualTo(String value) {
            addCriterion("fail_reason <>", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonGreaterThan(String value) {
            addCriterion("fail_reason >", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonGreaterThanOrEqualTo(String value) {
            addCriterion("fail_reason >=", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonLessThan(String value) {
            addCriterion("fail_reason <", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonLessThanOrEqualTo(String value) {
            addCriterion("fail_reason <=", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonLike(String value) {
            addCriterion("fail_reason like", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonNotLike(String value) {
            addCriterion("fail_reason not like", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonIn(List<String> values) {
            addCriterion("fail_reason in", values, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonNotIn(List<String> values) {
            addCriterion("fail_reason not in", values, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonBetween(String value1, String value2) {
            addCriterion("fail_reason between", value1, value2, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonNotBetween(String value1, String value2) {
            addCriterion("fail_reason not between", value1, value2, "failReason");
            return (Criteria) this;
        }

        public Criteria andQrcodeIsNull() {
            addCriterion("qrcode is null");
            return (Criteria) this;
        }

        public Criteria andQrcodeIsNotNull() {
            addCriterion("qrcode is not null");
            return (Criteria) this;
        }

        public Criteria andQrcodeEqualTo(Integer value) {
            addCriterion("qrcode =", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotEqualTo(Integer value) {
            addCriterion("qrcode <>", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeGreaterThan(Integer value) {
            addCriterion("qrcode >", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("qrcode >=", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeLessThan(Integer value) {
            addCriterion("qrcode <", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeLessThanOrEqualTo(Integer value) {
            addCriterion("qrcode <=", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeIn(List<Integer> values) {
            addCriterion("qrcode in", values, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotIn(List<Integer> values) {
            addCriterion("qrcode not in", values, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeBetween(Integer value1, Integer value2) {
            addCriterion("qrcode between", value1, value2, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotBetween(Integer value1, Integer value2) {
            addCriterion("qrcode not between", value1, value2, "qrcode");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}