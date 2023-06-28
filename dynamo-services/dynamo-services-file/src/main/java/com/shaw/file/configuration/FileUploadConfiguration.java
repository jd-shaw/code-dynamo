package com.shaw.file.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import lombok.RequiredArgsConstructor;

/**
 * 文件上传配置
 *
 * @author shaw
 * @date 2023/06/20
 */
@Configuration
@ConditionalOnClass(name="org.springframework.data.mongodb.gridfs.GridFsTemplate")
@RequiredArgsConstructor
public class FileUploadConfiguration {

    private final FileUploadProperties properties;

    /**
     * 自定义 GridFsTemplate
     */
    @Bean
    public GridFsTemplate gridFsTemplate(MongoDatabaseFactory dbFactory, MongoConverter converter) {
        return new GridFsTemplate(dbFactory, converter, properties.getMongo().getBucket());
    }

}
