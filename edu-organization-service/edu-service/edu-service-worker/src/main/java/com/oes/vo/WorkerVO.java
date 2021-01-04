package com.oes.vo;

import com.oes.dto.WorkerDTO;
import com.oes.model.dto.AuthUserDTO;
import com.oes.model.dto.UserDTO;


public class WorkerVO {
    UserDTO baseInfo;
    AuthUserDTO authInfo;
    WorkerDTO workerInfo;

    public WorkerVO() {
    }

    public WorkerVO(UserDTO baseInfo, AuthUserDTO authInfo, WorkerDTO workerInfo) {
        this.baseInfo = baseInfo;
        this.authInfo = authInfo;
        this.workerInfo = workerInfo;
    }

    public UserDTO getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(UserDTO baseInfo) {
        this.baseInfo = baseInfo;
    }

    public AuthUserDTO getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(AuthUserDTO authInfo) {
        this.authInfo = authInfo;
    }

    public WorkerDTO getWorkerInfo() {
        return workerInfo;
    }

    public void setWorkerInfo(WorkerDTO workerInfo) {
        this.workerInfo = workerInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"baseInfo\":")
                .append(baseInfo);
        sb.append(",\"authInfo\":")
                .append(authInfo);
        sb.append(",\"workerInfo\":")
                .append(workerInfo);
        sb.append('}');
        return sb.toString();
    }
}
