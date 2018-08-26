package com.tieuCake.dao;

import com.tieuCake.model.entities.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by nguye on 23-Jul-17.
 */
public interface IPostDAO extends JpaRepository<PostEntity,String> {


    Page<PostEntity> findAll(Pageable pageable);
}
