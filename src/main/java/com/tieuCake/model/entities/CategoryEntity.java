package com.tieuCake.model.entities;

import javax.persistence.*;

/**
 * Created by nguye on 06-Aug-17.
 */
@Entity
@Table(name = "category", schema = "", catalog = "tieucake")
public class CategoryEntity {
    private String id;
    private String namecategory;
    private String catalogId;
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
    @Column(name = "namecategory")
    public String getNamecategory() {
        return namecategory;
    }

    public void setNamecategory(String namecategory) {
        this.namecategory = namecategory;
    }

    @Basic
    @Column(name = "catalogId")
    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
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

        CategoryEntity that = (CategoryEntity) o;

        if (active != that.active) return false;
        if (catalogId != null ? !catalogId.equals(that.catalogId) : that.catalogId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (namecategory != null ? !namecategory.equals(that.namecategory) : that.namecategory != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (namecategory != null ? namecategory.hashCode() : 0);
        result = 31 * result + (catalogId != null ? catalogId.hashCode() : 0);
        result = 31 * result + (int) active;
        return result;
    }
}
