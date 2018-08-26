package com.tieuCake.model.entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguye on 22-Oct-17.
 */
@XmlRootElement(name="posts")
public class PostEntities implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<PostEntity> postEntities = new ArrayList<PostEntity>();

    public List<PostEntity> getPostEntities() {
        return postEntities;
    }

    public void setPostEntities(List<PostEntity> postEntities) {
        this.postEntities = postEntities;
    }
}
