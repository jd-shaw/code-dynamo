package com.shaw.mysql.jpa.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractEntityDao<T, ID extends Serializable> implements EntityDao<T, ID> {

}
