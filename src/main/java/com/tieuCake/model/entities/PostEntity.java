package com.tieuCake.model.entities;


import com.google.api.client.util.DateTime;
import com.tieuCake.util.DateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by nguye on 23-Jul-17.
 */
@XmlRootElement(name = "postEntity")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name = "post", schema = "", catalog = "tieucake")
public class PostEntity implements Serializable {

    @XmlElement
    private String categoryId;
    @XmlElement
    private String bookId;
    @XmlAttribute
    private String id;
    @XmlElement
    private String title;
    @XmlElement
    private String author;
    @XmlElement
    private String content;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Timestamp published;
    @XmlElement
    private String url;
    @XmlElement
    private String status;

    public PostEntity(String id, String title, String author, String content, DateTime published, String url, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.published = new Timestamp(new Date(published.getValue()).getTime());
        this.url = url;
        this.status = status;
    }

    public PostEntity() {
    }

    @Column(name = "category_Id")
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }





    @Column(name = "book_Id")
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "published")
    public Timestamp getPublished() {
        return published;
    }

    public void setPublished(Timestamp published) {
        this.published = published;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostEntity that = (PostEntity) o;

        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (published != null ? !published.equals(that.published) : that.published != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (published != null ? published.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
