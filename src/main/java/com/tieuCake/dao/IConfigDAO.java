package com.tieuCake.dao;

import com.tieuCake.model.entities.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nguye on 27-Jul-17.
 */
public interface IConfigDAO extends JpaRepository<ConfigEntity,String> {
}
