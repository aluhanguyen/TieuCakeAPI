package com.tieuCake.model.entities;

import javax.persistence.*;

/**
 * Created by nguye on 06-Aug-17.
 */
@Entity
@Table(name = "catalog", schema = "", catalog = "tieucake")
public class CatalogEntity {
    private String id;
    private String namecatalog;
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
    @Column(name = "namecatalog")
    public String getNamecatalog() {
        return namecatalog;
    }

    public void setNamecatalog(String namecatalog) {
        this.namecatalog = namecatalog;
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

        CatalogEntity that = (CatalogEntity) o;

        if (active != that.active) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (namecatalog != null ? !namecatalog.equals(that.namecatalog) : that.namecatalog != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (namecatalog != null ? namecatalog.hashCode() : 0);
        result = 31 * result + (int) active;
        return result;
    }
}
