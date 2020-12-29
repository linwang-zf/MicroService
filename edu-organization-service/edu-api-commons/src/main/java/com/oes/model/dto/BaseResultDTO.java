package com.oes.model.dto;

import com.oes.constant.enums.BusinessType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResultDTO {
    /**
     * 业务类型
     */
    private BusinessType type;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 备注消息
     */
    private String message;

    /**
     * 其余返回数据
     */
    private Object data;


    public BaseResultDTO(boolean success, String message) {
        this(null, success, message, null);
    }

    public BaseResultDTO(BusinessType type, boolean success, String message) {
        this(type, success, message, null);
    }

    public BaseResultDTO(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public BaseResultDTO(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }



    public BaseResultDTO(boolean success) {
        this.setSuccess(success);
    }

    public void set(boolean success, String message) {
        this.setSuccess(success);
        this.setMessage(message);
    }

    public void set(boolean success, String message, Object data) {
        this.set(success, message);
        this.setData(data);
    }
}
