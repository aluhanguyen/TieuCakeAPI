package com.tieuCake.model.entities;

import javax.persistence.*;

/**
 * Created by nguye on 27-Jul-17.
 */
@Entity
@Table(name = "config", schema = "", catalog = "tieucake")
public class ConfigEntity {
    private String clientId;
    private String refeshToken;

    public ConfigEntity() {
    }

    public ConfigEntity(String clientId, String refeshToken) {
        this.clientId = clientId;
        this.refeshToken = refeshToken;
    }

    @Id
    @Column(name = "CLIENT_ID")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }


    @Basic
    @Column(name = "REFESH_TOKEN")
    public String getRefeshToken() {
        return refeshToken;
    }

    public void setRefeshToken(String refeshToken) {
        this.refeshToken = refeshToken;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigEntity that = (ConfigEntity) o;

        if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) return false;
        if (refeshToken != null ? !refeshToken.equals(that.refeshToken) : that.refeshToken != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clientId != null ? clientId.hashCode() : 0;
        result = 31 * result + (refeshToken != null ? refeshToken.hashCode() : 0);
        return result;
    }
}
