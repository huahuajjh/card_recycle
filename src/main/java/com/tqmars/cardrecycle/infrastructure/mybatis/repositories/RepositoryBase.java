package com.tqmars.cardrecycle.infrastructure.mybatis.repositories;

import com.tqmars.cardrecycle.domain.entities.IEntity;
import com.tqmars.cardrecycle.domain.entities.annotation.Table;
import com.tqmars.cardrecycle.domain.repositories.IRepository;
import com.tqmars.cardrecycle.infrastructure.log.LoggerFactory;
import com.tqmars.cardrecycle.infrastructure.mybatis.repositories.exceptions.ApplicationException;
import com.tqmars.cardrecycle.infrastructure.mybatis.util.MapToEntityTool;
import com.tqmars.cardrecycle.infrastructure.mybatis.util.SqlStatementTool;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jjh on 1/11/17.
 */
public class RepositoryBase<TEntity extends IEntity<TPrimaryKey>, TPrimaryKey> implements IRepository<TEntity, TPrimaryKey> {
    private Class<TEntity> entityClass;
    private DbContext context;

    private static String SELECT_LAST_ID = "select_last_id";
    private static String GET = "get";
    private static String SINGLE = "single";
    private static String GET_ALL = "getAll";
    private static String GET_ALL_WITH_CONDITION = "getAllWithCondition";
    private static String INSERT = "insert";
    private static String INSERT_AND_GET_ID = "insertAndGetId";
    private static String UPDATE_AND_RETURN_ENTITY = "updateAndReturnEntity";
    private static String DELETE = "delete";
    private static String DELETE_BY_ID = "deleteById";
    private static String DELETE_WITH_CONDITION = "deleteWithCondition";
    private static String COUNT = "count";
    private static String COUNT_WITH_CONDITION = "countWithCondition";
    private static String UPDATE = "update";


    public RepositoryBase(DbContext _context) {
        this.context = _context;
    }

    private String getTableName() {
        return entityClass.getAnnotation(Table.class).name();
    }

    @Override
    public TEntity get(TPrimaryKey id) {
        String sql = "select * from " + getTableName() + " where id=" + id.toString();
        Map<String, Object> map = context.getSession().selectOne(getId4Mapper(GET), sql);
        return MapToEntityTool.toEntity(entityClass, map);
    }

    @Override
    public TEntity single(String where) {
        String sql = "select * from " + getTableName() + " where " + where + " limit 0,1";
        Map<String, Object> map = context.getSession().selectOne(getId4Mapper(SINGLE), sql);
        return MapToEntityTool.toEntity(entityClass, map);
    }

    @Override
    public List<TEntity> getAll() {
        String sql = "select * from " + getTableName();
        List<Map<String, Object>> mapList = context.getSession().selectList(getId4Mapper(GET_ALL), sql);
        return MapToEntityTool.toEntityList(entityClass, mapList);
    }

    @Override
    public List<TEntity> getAllWithCondition(String where) {
        String sql = "select * from " + getTableName() + " where " + where;
        List<Map<String, Object>> mapList = context.getSession().selectList(getId4Mapper(GET_ALL_WITH_CONDITION), sql);
        return MapToEntityTool.toEntityList(entityClass, mapList);
    }

    @Override
    public TEntity insert(TEntity entity) {
        TPrimaryKey id = insertAndGetId(entity);
        entity.setId(id);
        return entity;
    }

    @Override
    public TPrimaryKey insertAndGetId(TEntity entity) {
        StringBuilder sb = new StringBuilder();
        Map<String,Object> map = new HashMap<>();
        sb.append("insert into ")
                .append(getTableName())
                .append("(")
                .append(SqlStatementTool.getColumnsAndValues(entity).getCol())
                .append(")")
                .append(" ")
                .append("values(")
                .append(SqlStatementTool.getColumnsAndValues(entity).getVal())
                .append(")");
        System.out.println(sb.toString());
        map.put("id",0);
        map.put("value",sb.toString());
        context.getSession().insert(getId4Mapper(INSERT_AND_GET_ID),map);

        return (TPrimaryKey) map.get("id");
    }

    @Override
    public TEntity updateAndReturnEntity(TEntity entity) {
        update(entity);
        return entity;
    }

    @Override
    public void update(TEntity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append("update ")
                .append(getTableName()).append(" ")
                .append("set").append(" ")
                .append(SqlStatementTool.getUpdateStatement(entity)).append(" ")
                .append("where").append(" ")
                .append("id=").append(entity.getId());

        System.out.println(sb.toString());
        context.getSession().insert(getId4Mapper(UPDATE),sb.toString());

    }

    @Override
    public void delete(TEntity entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(TPrimaryKey id) {
        String sql = "delete from "+getTableName()+" where id="+id;
        context.getSession().delete(getId4Mapper(DELETE),sql);
    }

    @Override
    public void deleteWithCondition(String where) {

    }

    @Override
    public int count() {
        String sql = "select count(1) from "+getTableName();
        int c = context.getSession().selectOne(getId4Mapper(COUNT),sql);
        return c;
    }

    @Override
    public int countWithCondition(String where) {
        String sql = "select count(1) from "+getTableName()+" where "+where;
        int c = context.getSession().selectOne(getId4Mapper(COUNT),sql);
        return c;
    }

    @Override
    public boolean isExists(String where) {
        if(countWithCondition(where)>=0)
        {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public IRepository<TEntity, TPrimaryKey> setEntityClass(Class<TEntity> _entityClass) {
        this.entityClass = _entityClass;
        return this;
    }

    @Override
    public Class<TEntity> getEntityClass() {
        if (entityClass == null) {
            LoggerFactory.getLogger().error("RepositoryBase:getEntityClass:error:not set entity class");
            throw new ApplicationException("not set entity class");
        } else {
            return entityClass;
        }
    }

    @Override
    public void closeSession() {
        context.closeSession();
    }

    @Override
    public void commit() {
        context.commitSession();
    }

    @Override
    public void rollBack() {
        context.rollbackSession();
    }

    private String getId4Mapper(String prefix) {
        return getEntityClass().getPackage().getName() + "." + prefix;
    }

}
