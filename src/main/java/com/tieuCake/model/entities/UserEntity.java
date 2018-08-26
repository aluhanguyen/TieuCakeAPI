package com.tieuCake.model.entities;

import javax.persistence.*;

/**
 * Created by nguye on 06-Aug-17.
 */
@Entity
@Table(name = "user", schema = "", catalog = "tieucake")
public class UserEntity {
    private String id;
    private String mailuser;
    private String passworduser;
    private String lastname;
    private String firstname;
    private byte active;
    private byte notification;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "mailuser")
    public String getMailuser() {
        return mailuser;
    }

    public void setMailuser(String mailuser) {
        this.mailuser = mailuser;
    }

    @Basic
    @Column(name = "passworduser")
    public String getPassworduser() {
        return passworduser;
    }

    public void setPassworduser(String passworduser) {
        this.passworduser = passworduser;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "active")
    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    @Basic
    @Column(name = "notification")
    public byte getNotification() {
        return notification;
    }

    public void setNotification(byte notification) {
        this.notification = notification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (active != that.active) return false;
        if (notification != that.notification) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (mailuser != null ? !mailuser.equals(that.mailuser) : that.mailuser != null) return false;
        if (passworduser != null ? !passworduser.equals(that.passworduser) : that.passworduser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (mailuser != null ? mailuser.hashCode() : 0);
        result = 31 * result + (passworduser != null ? passworduser.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (int) active;
        result = 31 * result + (int) notification;
        return result;
    }
}
