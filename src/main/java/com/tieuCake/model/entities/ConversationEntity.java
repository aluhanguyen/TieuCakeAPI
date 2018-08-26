package com.tieuCake.model.entities;

import javax.persistence.*;

/**
 * Created by nguye on 06-Aug-17.
 */
@Entity
@Table(name = "conversation", schema = "", catalog = "tieucake")
public class ConversationEntity {
    private String id;
    private String postId;
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
    @Column(name = "postId")
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

        ConversationEntity that = (ConversationEntity) o;

        if (active != that.active) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (postId != null ? !postId.equals(that.postId) : that.postId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (postId != null ? postId.hashCode() : 0);
        result = 31 * result + (int) active;
        return result;
    }
}
