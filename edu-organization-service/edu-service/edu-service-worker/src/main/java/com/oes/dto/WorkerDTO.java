package com.oes.dto;

import java.time.LocalDate;

public class WorkerDTO {

    private long organizationId;

    private int role;

    private int roleSubLevel;//enum?

    /*入职时间*/
    private LocalDate entryTime;

    private String standbyPhone;

    private String relativeName;

    private String relationship;

    private String relativePhone;

    private String fail_reason;

    public LocalDate getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDate entryTime) {
        this.entryTime = entryTime;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getRoleSubLevel() {
        return roleSubLevel;
    }

    public void setRoleSubLevel(int roleSubLevel) {
        this.roleSubLevel = roleSubLevel;
    }

    public String getStandbyPhone() {
        return standbyPhone;
    }

    public void setStandbyPhone(String standbyPhone) {
        this.standbyPhone = standbyPhone;
    }

    public String getRelativeName() {
        return relativeName;
    }

    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getRelativePhone() {
        return relativePhone;
    }

    public void setRelativePhone(String relativePhone) {
        this.relativePhone = relativePhone;
    }

    public String getFail_reason() {
        return fail_reason;
    }

    public void setFail_reason(String fail_reason) {
        this.fail_reason = fail_reason;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"organizationId\":")
                .append(organizationId);
        sb.append(",\"role\":")
                .append(role);
        sb.append(",\"roleSubLevel\":")
                .append(roleSubLevel);
        sb.append(",\"standbyPhone\":\"")
                .append(standbyPhone).append('\"');
        sb.append(",\"relativeName\":\"")
                .append(relativeName).append('\"');
        sb.append(",\"relationship\":\"")
                .append(relationship).append('\"');
        sb.append(",\"relativePhone\":\"")
                .append(relativePhone).append('\"');
        sb.append(",\"fail_reason\":\"")
                .append(fail_reason).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
