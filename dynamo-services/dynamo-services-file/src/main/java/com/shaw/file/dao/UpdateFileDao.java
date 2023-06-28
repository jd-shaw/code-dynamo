package com.shaw.file.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.shaw.file.entity.UpdateFileInfo;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface UpdateFileDao extends JpaRepository<UpdateFileInfo, String>, JpaSpecificationExecutor<UpdateFileInfo> {

}
