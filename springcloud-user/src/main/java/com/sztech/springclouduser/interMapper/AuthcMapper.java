package com.sztech.springclouduser.interMapper;

import com.sztech.springclouduser.bean.Authc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthcMapper {

    /**
     * 根据主体身份查询
     * @param authc
     * @return
     */
    @Select("select * from daji_authc where subject_id=#{subjectId} and secure_key=#{securekey}")
    @ResultType(Authc.class)
    Authc findAuth(Authc authc);
}
