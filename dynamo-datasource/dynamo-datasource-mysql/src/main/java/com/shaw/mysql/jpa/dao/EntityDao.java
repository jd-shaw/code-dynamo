package com.shaw.mysql.jpa.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityDao<T, ID extends Serializable> extends JpaRepository<T, ID> {

}
