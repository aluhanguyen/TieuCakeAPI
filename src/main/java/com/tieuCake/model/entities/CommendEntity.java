package com.tieuCake.model.entities;

import javax.persistence.*;

/**
 * Created by nguye on 06-Aug-17.
 */
@Entity
@Table(name = "commend", schema = "", catalog = "tieucake")
public class CommendEntity {
    private String id;
    private String convertionId;
    private String contentCommend;
    private String userId;
    private byte active;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "convertionId")
    public String getConvertionId() {
        return convertionId;
    }

    public void setConvertionId(String convertionId) {
        this.convertionId = convertionId;
    }

    @Basic
    @Column(name = "contentCommend")
    public String getContentCommend() {
        return contentCommend;
    }

    public void setContentCommend(String contentCommend) {
        this.contentCommend = contentCommend;
    }

    @Basic
    @Column(name = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "active")
    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommendEntity that = (CommendEntity) o;

        if (active != that.active) return false;
        if (contentCommend != null ? !contentCommend.equals(that.contentCommend) : that.contentCommend != null)
            return false;
        if (convertionId != null ? !convertionId.equals(that.convertionId) : that.convertionId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (convertionId != null ? convertionId.hashCode() : 0);
        result = 31 * result + (contentCommend != null ? contentCommend.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (int) active;
        return result;
    }
}
