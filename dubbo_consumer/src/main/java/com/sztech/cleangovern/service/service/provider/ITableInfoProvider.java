package com.sztech.cleangovern.service.service.provider;

import com.sztech.cleangovern.model.entity.TableInfoDO;

import java.util.List;

public interface ITableInfoProvider {

    //查询所有的表信息
    public List<TableInfoDO> selectAllTables();

    public List<TableInfoDO> findAllTablesBytableName(String tableName1, String tableName2);

}
