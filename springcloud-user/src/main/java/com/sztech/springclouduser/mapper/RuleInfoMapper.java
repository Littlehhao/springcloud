package com.sztech.springclouduser.mapper;

import com.sztech.springclouduser.bean.RuleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RuleInfoMapper {


    @Select("select * from rule_info")
    @ResultType(RuleInfo.class)
    List<RuleInfo> select();
}
