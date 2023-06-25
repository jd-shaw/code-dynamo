package com.shaw.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ScanClassUtils {

    @SuppressWarnings("unchecked")
    public static <T> List<Class<T>> findClassByPkg(String pkg, TypeFilter... typeFilters) {
        try {
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                    + ClassUtils.convertClassNameToResourcePath(pkg) + "/**/*.class";
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            List<Class<T>> classes = new ArrayList<Class<T>>();
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader reader = readerFactory.getMetadataReader(resource);
                    String className = reader.getClassMetadata().getClassName();
                    Class<?> clazz = resourcePatternResolver.getClassLoader().loadClass(className);
                    if (matchesFilter(clazz, typeFilters)) {
                        classes.add((Class<T>) clazz);
                    }
                }
            }
            return classes;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to scan classpath for unlisted classes", ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Failed to load annotated classes from classpath", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to addAnnotatedClass", ex);
        }
    }

    private static boolean matchesFilter(Class<?> clazz, TypeFilter[] typeFilters) throws IOException {
        if (typeFilters != null) {
            for (TypeFilter filter : typeFilters) {
                if (!filter.match(clazz)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static interface TypeFilter {
        public boolean match(Class<?> clazz);
    }

    public static class AnnotationTypeFilter implements TypeFilter {
        private Class<? extends Annotation> annotationType;

        public AnnotationTypeFilter(Class<? extends Annotation> annotationType) {
            this.annotationType = annotationType;
        }

        @Override
        public boolean match(Class<?> clazz) {
            if (clazz == null)
                return false;

            return clazz.isAnnotationPresent(annotationType);
        }
    }

    public static class AssignableTypeFilter implements TypeFilter {
        private Class<?> assignableType;
        private boolean isEntity;

        public AssignableTypeFilter(Class<?> assignableType) {
            this.assignableType = assignableType;
            this.isEntity = true;
        }

        public AssignableTypeFilter(Class<?> assignableType, boolean isEntity) {
            this.assignableType = assignableType;
            this.isEntity = isEntity;
        }

        @Override
        public boolean match(Class<?> clazz) {
            if (clazz == null)
                return false;

            if (isEntity)
                return assignableType.isAssignableFrom(clazz) && !Modifier.isAbstract(clazz.getModifiers())
                        && !Modifier.isInterface(clazz.getModifiers());
            else
                return assignableType.isAssignableFrom(clazz);
        }
    }
}
