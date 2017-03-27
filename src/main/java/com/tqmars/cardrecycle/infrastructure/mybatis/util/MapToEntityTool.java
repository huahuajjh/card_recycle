package com.tqmars.cardrecycle.infrastructure.mybatis.util;

import com.tqmars.cardrecycle.domain.entities.IEntity;
import com.tqmars.cardrecycle.domain.entities.annotation.Column;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jjh on 1/13/17.
 */
public class MapToEntityTool {
    public static <TEntity extends IEntity> TEntity toEntity(Class<TEntity> entityClass, Map<String, Object> map) {
        TEntity entity = initEntity(entityClass);
        // entity.setId = map.get("id");
        
        if (map == null || map.size() == 0) {
                return null;
        }
        Field[] fields = entityClass.getDeclaredFields();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            for (Field field : fields) {
                if(!field.isAnnotationPresent(Column.class)){
                    continue;
                }
                //get column name
                field.setAccessible(true);
                String colName = field.getAnnotation(Column.class).name();
                if (colName.equals(entry.getKey())) {
                    try {
                        field.set(entity, entry.getValue());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
        return entity;
    }

    public static <TEntity> List<TEntity> toEntityList(Class<TEntity> entityClass, List<Map<String, Object>> mapList) {
        List<TEntity> list = new ArrayList<>();
        if (mapList == null || mapList.size() == 0) {
            //return a empty entity
//            list.add(initEntity(entityClass));
            return null;
//            return list;
        }

        mapList.forEach(map -> list.add(setEntity(entityClass, map)));
        return list;

    }

    private static <TEntity> TEntity setEntity(Class<TEntity> entityClass, Map<String, Object> map) {
        Field[] fields = entityClass.getDeclaredFields();
        TEntity entity = null;
        try {
            entity = entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            for (Field field : fields) {
                if(!field.isAnnotationPresent(Column.class)){
                    continue;
                }

                //get column name
                field.setAccessible(true);
                String colName = field.getAnnotation(Column.class).name();
                if (colName.equals(entry.getKey())) {
                    try {
                        field.set(entity, entry.getValue());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
        return entity;
    }

    private static <TEntity> TEntity initEntity(Class<TEntity> clazz){
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("TEntity init error");
        }
    }

}
