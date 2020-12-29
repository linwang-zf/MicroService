package com.oes.model.entity;

import com.oes.model.enums.PictureType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * (Picture)实体类
 *
 * @author makejava
 * @since 2020-03-27 14:40:13
 */
@ApiModel(value = "Picture",description = "图片模型")
public class Picture implements Serializable {
    private static final long serialVersionUID = 978243902815182799L;
    
    private long pictureId;
    
    private String title;
    
    private String description;
    /**
    * 0：‘png’；1：‘jpg’；2：‘tmp’；3：‘gif’；4：‘tif’等
    */
    @ApiModelProperty(value = "图片类型",dataType = "PictureType",example = "1")
    private PictureType type;
    
    private long width;
    
    private long height;

    private String data;

    @ApiModelProperty(hidden = true)
    private byte[] pictureData;

    public byte[] getPictureData() {
        return pictureData;
    }

    public void setPictureData(byte[] pictureData) {
        this.pictureData = pictureData;
    }

    public long getPictureId() {
        return pictureId;
    }

    public void setPictureId(long pictureId) {
        this.pictureId = pictureId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PictureType getType() {
        return type;
    }

    public void setType(PictureType type) {
        this.type = type;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pictureId=" + pictureId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", width=" + width +
                ", height=" + height +
                ", data=" + data +
                '}';
    }
}