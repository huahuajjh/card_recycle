package com.tqmars.cardrecycle.infrastructure.mybatis.util;

import com.tqmars.cardrecycle.domain.entities.annotation.Column;

import java.lang.reflect.Field;

/**
 * Created by jjh on 1/13/17.
 */
public class SqlStatementTool {
    public static <TEntity> ColAndVal getColumnsAndValues(TEntity entity){
        Field[] fields = entity.getClass().getDeclaredFields();
        StringBuilder sbCol = new StringBuilder();
        StringBuilder sbVal = new StringBuilder();
        for (int i=0;i<fields.length;i++) {
            String columnName = fields[i].getAnnotation(Column.class).name();
            fields[i].setAccessible(true);
            if(i>0)
            {
                sbCol.append(",");
                sbVal.append(",");
            }
            sbCol.append(columnName);
            try {
                sbVal.append("'");
                sbVal.append(fields[i].get(entity));
                sbVal.append("'");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new ColAndVal(sbCol.toString(),sbVal.toString());

    }

    public static <TEntity> String getUpdateStatement(TEntity entity){
        Field[] fields = entity.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<fields.length;i++) {
            String columnName = fields[i].getAnnotation(Column.class).name();
            fields[i].setAccessible(true);
            if(i>0)
            {
                sb.append(",");
            }

            try {
                sb.append(columnName).append("=").append("'"+fields[i].get(entity)+"'");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static class ColAndVal{
        String col;
        String val;

        public ColAndVal(String col, String val) {
            this.col = col;
            this.val = val;
        }

        public String getCol() {
            return col;
        }

        public String getVal() {
            return val;
        }
    }

}
