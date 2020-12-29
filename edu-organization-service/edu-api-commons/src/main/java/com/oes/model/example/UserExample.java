package com.oes.model.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public  UserExample() {
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

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andMailIsNull() {
            addCriterion("mail is null");
            return (Criteria) this;
        }

        public Criteria andMailIsNotNull() {
            addCriterion("mail is not null");
            return (Criteria) this;
        }

        public Criteria andMailEqualTo(String value) {
            addCriterion("mail =", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotEqualTo(String value) {
            addCriterion("mail <>", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailGreaterThan(String value) {
            addCriterion("mail >", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailGreaterThanOrEqualTo(String value) {
            addCriterion("mail >=", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLessThan(String value) {
            addCriterion("mail <", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLessThanOrEqualTo(String value) {
            addCriterion("mail <=", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLike(String value) {
            addCriterion("mail like", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotLike(String value) {
            addCriterion("mail not like", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailIn(List<String> values) {
            addCriterion("mail in", values, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotIn(List<String> values) {
            addCriterion("mail not in", values, "mail");
            return (Criteria) this;
        }

        public Criteria andMailBetween(String value1, String value2) {
            addCriterion("mail between", value1, value2, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotBetween(String value1, String value2) {
            addCriterion("mail not between", value1, value2, "mail");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(Byte value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(Byte value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(Byte value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(Byte value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(Byte value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(Byte value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<Byte> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<Byte> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(Byte value1, Byte value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(Byte value1, Byte value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andBirthIsNull() {
            addCriterion("birth is null");
            return (Criteria) this;
        }

        public Criteria andBirthIsNotNull() {
            addCriterion("birth is not null");
            return (Criteria) this;
        }

        public Criteria andBirthEqualTo(Date value) {
            addCriterionForJDBCDate("birth =", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthNotEqualTo(Date value) {
            addCriterionForJDBCDate("birth <>", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthGreaterThan(Date value) {
            addCriterionForJDBCDate("birth >", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birth >=", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthLessThan(Date value) {
            addCriterionForJDBCDate("birth <", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birth <=", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthIn(List<Date> values) {
            addCriterionForJDBCDate("birth in", values, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthNotIn(List<Date> values) {
            addCriterionForJDBCDate("birth not in", values, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birth between", value1, value2, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birth not between", value1, value2, "birth");
            return (Criteria) this;
        }

        public Criteria andProfilePhotoIsNull() {
            addCriterion("profile_photo is null");
            return (Criteria) this;
        }

        public Criteria andProfilePhotoIsNotNull() {
            addCriterion("profile_photo is not null");
            return (Criteria) this;
        }

        public Criteria andProfilePhotoEqualTo(Integer value) {
            addCriterion("profile_photo =", value, "profilePhoto");
            return (Criteria) this;
        }

        public Criteria andProfilePhotoNotEqualTo(Integer value) {
            addCriterion("profile_photo <>", value, "profilePhoto");
            return (Criteria) this;
        }

        public Criteria andProfilePhotoGreaterThan(Integer value) {
            addCriterion("profile_photo >", value, "profilePhoto");
            return (Criteria) this;
        }

        public Criteria andProfilePhotoGreaterThanOrEqualTo(Integer value) {
            addCriterion("profile_photo >=", value, "profilePhoto");
            return (Criteria) this;
        }

        public Criteria andProfilePhotoLessThan(Integer value) {
            addCriterion("profile_photo <", value, "profilePhoto");
            return (Criteria) this;
        }

        public Criteria andProfilePhotoLessThanOrEqualTo(Integer value) {
            addCriterion("profile_photo <=", value, "profilePhoto");
            return (Criteria) this;
        }

        public Criteria andProfilePhotoIn(List<Integer> values) {
            addCriterion("profile_photo in", values, "profilePhoto");
            return (Criteria) this;
        }

        public Criteria andProfilePhotoNotIn(List<Integer> values) {
            addCriterion("profile_photo not in", values, "profilePhoto");
            return (Criteria) this;
        }

        public Criteria andProfilePhotoBetween(Integer value1, Integer value2) {
            addCriterion("profile_photo between", value1, value2, "profilePhoto");
            return (Criteria) this;
        }

        public Criteria andProfilePhotoNotBetween(Integer value1, Integer value2) {
            addCriterion("profile_photo not between", value1, value2, "profilePhoto");
            return (Criteria) this;
        }

        public Criteria andAuthenticatedIsNull() {
            addCriterion("authenticated is null");
            return (Criteria) this;
        }

        public Criteria andAuthenticatedIsNotNull() {
            addCriterion("authenticated is not null");
            return (Criteria) this;
        }

        public Criteria andAuthenticatedEqualTo(Byte value) {
            addCriterion("authenticated =", value, "authenticated");
            return (Criteria) this;
        }

        public Criteria andAuthenticatedNotEqualTo(Byte value) {
            addCriterion("authenticated <>", value, "authenticated");
            return (Criteria) this;
        }

        public Criteria andAuthenticatedGreaterThan(Byte value) {
            addCriterion("authenticated >", value, "authenticated");
            return (Criteria) this;
        }

        public Criteria andAuthenticatedGreaterThanOrEqualTo(Byte value) {
            addCriterion("authenticated >=", value, "authenticated");
            return (Criteria) this;
        }

        public Criteria andAuthenticatedLessThan(Byte value) {
            addCriterion("authenticated <", value, "authenticated");
            return (Criteria) this;
        }

        public Criteria andAuthenticatedLessThanOrEqualTo(Byte value) {
            addCriterion("authenticated <=", value, "authenticated");
            return (Criteria) this;
        }

        public Criteria andAuthenticatedIn(List<Byte> values) {
            addCriterion("authenticated in", values, "authenticated");
            return (Criteria) this;
        }

        public Criteria andAuthenticatedNotIn(List<Byte> values) {
            addCriterion("authenticated not in", values, "authenticated");
            return (Criteria) this;
        }

        public Criteria andAuthenticatedBetween(Byte value1, Byte value2) {
            addCriterion("authenticated between", value1, value2, "authenticated");
            return (Criteria) this;
        }

        public Criteria andAuthenticatedNotBetween(Byte value1, Byte value2) {
            addCriterion("authenticated not between", value1, value2, "authenticated");
            return (Criteria) this;
        }

        public Criteria andHasBankcardIsNull() {
            addCriterion("has_bankcard is null");
            return (Criteria) this;
        }

        public Criteria andHasBankcardIsNotNull() {
            addCriterion("has_bankcard is not null");
            return (Criteria) this;
        }

        public Criteria andHasBankcardEqualTo(Byte value) {
            addCriterion("has_bankcard =", value, "hasBankcard");
            return (Criteria) this;
        }

        public Criteria andHasBankcardNotEqualTo(Byte value) {
            addCriterion("has_bankcard <>", value, "hasBankcard");
            return (Criteria) this;
        }

        public Criteria andHasBankcardGreaterThan(Byte value) {
            addCriterion("has_bankcard >", value, "hasBankcard");
            return (Criteria) this;
        }

        public Criteria andHasBankcardGreaterThanOrEqualTo(Byte value) {
            addCriterion("has_bankcard >=", value, "hasBankcard");
            return (Criteria) this;
        }

        public Criteria andHasBankcardLessThan(Byte value) {
            addCriterion("has_bankcard <", value, "hasBankcard");
            return (Criteria) this;
        }

        public Criteria andHasBankcardLessThanOrEqualTo(Byte value) {
            addCriterion("has_bankcard <=", value, "hasBankcard");
            return (Criteria) this;
        }

        public Criteria andHasBankcardIn(List<Byte> values) {
            addCriterion("has_bankcard in", values, "hasBankcard");
            return (Criteria) this;
        }

        public Criteria andHasBankcardNotIn(List<Byte> values) {
            addCriterion("has_bankcard not in", values, "hasBankcard");
            return (Criteria) this;
        }

        public Criteria andHasBankcardBetween(Byte value1, Byte value2) {
            addCriterion("has_bankcard between", value1, value2, "hasBankcard");
            return (Criteria) this;
        }

        public Criteria andHasBankcardNotBetween(Byte value1, Byte value2) {
            addCriterion("has_bankcard not between", value1, value2, "hasBankcard");
            return (Criteria) this;
        }

        public Criteria andDefaultRoleIsNull() {
            addCriterion("default_role is null");
            return (Criteria) this;
        }

        public Criteria andDefaultRoleIsNotNull() {
            addCriterion("default_role is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultRoleEqualTo(Byte value) {
            addCriterion("default_role =", value, "defaultRole");
            return (Criteria) this;
        }

        public Criteria andDefaultRoleNotEqualTo(Byte value) {
            addCriterion("default_role <>", value, "defaultRole");
            return (Criteria) this;
        }

        public Criteria andDefaultRoleGreaterThan(Byte value) {
            addCriterion("default_role >", value, "defaultRole");
            return (Criteria) this;
        }

        public Criteria andDefaultRoleGreaterThanOrEqualTo(Byte value) {
            addCriterion("default_role >=", value, "defaultRole");
            return (Criteria) this;
        }

        public Criteria andDefaultRoleLessThan(Byte value) {
            addCriterion("default_role <", value, "defaultRole");
            return (Criteria) this;
        }

        public Criteria andDefaultRoleLessThanOrEqualTo(Byte value) {
            addCriterion("default_role <=", value, "defaultRole");
            return (Criteria) this;
        }

        public Criteria andDefaultRoleIn(List<Byte> values) {
            addCriterion("default_role in", values, "defaultRole");
            return (Criteria) this;
        }

        public Criteria andDefaultRoleNotIn(List<Byte> values) {
            addCriterion("default_role not in", values, "defaultRole");
            return (Criteria) this;
        }

        public Criteria andDefaultRoleBetween(Byte value1, Byte value2) {
            addCriterion("default_role between", value1, value2, "defaultRole");
            return (Criteria) this;
        }

        public Criteria andDefaultRoleNotBetween(Byte value1, Byte value2) {
            addCriterion("default_role not between", value1, value2, "defaultRole");
            return (Criteria) this;
        }

        public Criteria andRole1IsNull() {
            addCriterion("role1 is null");
            return (Criteria) this;
        }

        public Criteria andRole1IsNotNull() {
            addCriterion("role1 is not null");
            return (Criteria) this;
        }

        public Criteria andRole1EqualTo(Byte value) {
            addCriterion("role1 =", value, "role1");
            return (Criteria) this;
        }

        public Criteria andRole1NotEqualTo(Byte value) {
            addCriterion("role1 <>", value, "role1");
            return (Criteria) this;
        }

        public Criteria andRole1GreaterThan(Byte value) {
            addCriterion("role1 >", value, "role1");
            return (Criteria) this;
        }

        public Criteria andRole1GreaterThanOrEqualTo(Byte value) {
            addCriterion("role1 >=", value, "role1");
            return (Criteria) this;
        }

        public Criteria andRole1LessThan(Byte value) {
            addCriterion("role1 <", value, "role1");
            return (Criteria) this;
        }

        public Criteria andRole1LessThanOrEqualTo(Byte value) {
            addCriterion("role1 <=", value, "role1");
            return (Criteria) this;
        }

        public Criteria andRole1In(List<Byte> values) {
            addCriterion("role1 in", values, "role1");
            return (Criteria) this;
        }

        public Criteria andRole1NotIn(List<Byte> values) {
            addCriterion("role1 not in", values, "role1");
            return (Criteria) this;
        }

        public Criteria andRole1Between(Byte value1, Byte value2) {
            addCriterion("role1 between", value1, value2, "role1");
            return (Criteria) this;
        }

        public Criteria andRole1NotBetween(Byte value1, Byte value2) {
            addCriterion("role1 not between", value1, value2, "role1");
            return (Criteria) this;
        }

        public Criteria andRole2IsNull() {
            addCriterion("role2 is null");
            return (Criteria) this;
        }

        public Criteria andRole2IsNotNull() {
            addCriterion("role2 is not null");
            return (Criteria) this;
        }

        public Criteria andRole2EqualTo(Byte value) {
            addCriterion("role2 =", value, "role2");
            return (Criteria) this;
        }

        public Criteria andRole2NotEqualTo(Byte value) {
            addCriterion("role2 <>", value, "role2");
            return (Criteria) this;
        }

        public Criteria andRole2GreaterThan(Byte value) {
            addCriterion("role2 >", value, "role2");
            return (Criteria) this;
        }

        public Criteria andRole2GreaterThanOrEqualTo(Byte value) {
            addCriterion("role2 >=", value, "role2");
            return (Criteria) this;
        }

        public Criteria andRole2LessThan(Byte value) {
            addCriterion("role2 <", value, "role2");
            return (Criteria) this;
        }

        public Criteria andRole2LessThanOrEqualTo(Byte value) {
            addCriterion("role2 <=", value, "role2");
            return (Criteria) this;
        }

        public Criteria andRole2In(List<Byte> values) {
            addCriterion("role2 in", values, "role2");
            return (Criteria) this;
        }

        public Criteria andRole2NotIn(List<Byte> values) {
            addCriterion("role2 not in", values, "role2");
            return (Criteria) this;
        }

        public Criteria andRole2Between(Byte value1, Byte value2) {
            addCriterion("role2 between", value1, value2, "role2");
            return (Criteria) this;
        }

        public Criteria andRole2NotBetween(Byte value1, Byte value2) {
            addCriterion("role2 not between", value1, value2, "role2");
            return (Criteria) this;
        }

        public Criteria andRole3IsNull() {
            addCriterion("role3 is null");
            return (Criteria) this;
        }

        public Criteria andRole3IsNotNull() {
            addCriterion("role3 is not null");
            return (Criteria) this;
        }

        public Criteria andRole3EqualTo(Byte value) {
            addCriterion("role3 =", value, "role3");
            return (Criteria) this;
        }

        public Criteria andRole3NotEqualTo(Byte value) {
            addCriterion("role3 <>", value, "role3");
            return (Criteria) this;
        }

        public Criteria andRole3GreaterThan(Byte value) {
            addCriterion("role3 >", value, "role3");
            return (Criteria) this;
        }

        public Criteria andRole3GreaterThanOrEqualTo(Byte value) {
            addCriterion("role3 >=", value, "role3");
            return (Criteria) this;
        }

        public Criteria andRole3LessThan(Byte value) {
            addCriterion("role3 <", value, "role3");
            return (Criteria) this;
        }

        public Criteria andRole3LessThanOrEqualTo(Byte value) {
            addCriterion("role3 <=", value, "role3");
            return (Criteria) this;
        }

        public Criteria andRole3In(List<Byte> values) {
            addCriterion("role3 in", values, "role3");
            return (Criteria) this;
        }

        public Criteria andRole3NotIn(List<Byte> values) {
            addCriterion("role3 not in", values, "role3");
            return (Criteria) this;
        }

        public Criteria andRole3Between(Byte value1, Byte value2) {
            addCriterion("role3 between", value1, value2, "role3");
            return (Criteria) this;
        }

        public Criteria andRole3NotBetween(Byte value1, Byte value2) {
            addCriterion("role3 not between", value1, value2, "role3");
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
