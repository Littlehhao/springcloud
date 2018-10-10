package com.sztech.cleangovern.service.service.provider;

import com.sztech.cleangovern.model.entity.FeebackProblemInterfaceDO;

import java.util.List;

public interface IFeedbackProblemInterfaceProvider {
    //根据PROBLEM_ID查询
    List<FeebackProblemInterfaceDO> selectByProblemId(Integer problemId);
    //插入数据
    Integer insertSelective(Integer problemId, String interfaceCode, String interfaceName, Integer dataNum, String interfaceParam, String paramValue, String tableName, String tableColumnName, String interfaceParamType, String problemDesc);
    //根据PROBLEM_ID删除
    Integer deleteByProblemId(Integer problemId);
    //插入多条数据
    Integer insertList(List<FeebackProblemInterfaceDO> feedbackProblemInterfaces);
}
