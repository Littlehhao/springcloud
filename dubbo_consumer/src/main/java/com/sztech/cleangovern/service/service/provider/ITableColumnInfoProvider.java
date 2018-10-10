package com.sztech.cleangovern.service.service.provider;

import com.sztech.cleangovern.model.entity.TableColumnInfoDO;

import java.util.List;

public interface ITableColumnInfoProvider {

    //通过表名查询所有的字段
    public List<TableColumnInfoDO> getByTableName(String tableName);
}
