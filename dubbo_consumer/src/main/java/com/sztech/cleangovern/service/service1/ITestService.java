package com.sztech.cleangovern.service.service1;

import com.github.pagehelper.PageInfo;
import com.sztech.cleangovern.model.entity.FeebackProblemInfoDO;
import com.sztech.cleangovern.model.entity.FeebackProblemInterfaceDO;
import com.sztech.cleangovern.model.entity.TableColumnInfoDO;
import com.sztech.cleangovern.model.entity.TableInfoDO;
import com.sztech.cleangovern.model.helper.CheckMangerSearcher;

import java.util.List;

public interface ITestService {

    List<TableInfoDO> test(String name1, String name2);

    List<TableColumnInfoDO> getByTableName(String tableName);

   // List<FeedbackResultInfoDO> selectByProblemId(Integer problemId);

    List<FeebackProblemInterfaceDO> selectByProblemId(Integer problemId);

    Integer insertSelective(Integer problemId, String interfaceCode, String interfaceName, Integer dataNum, String interfaceParam, String paramValue, String tableName, String tableColumnName, String interfaceParamType, String problemDesc);

    Integer deleteByProblemId(Integer problemId);

    Integer insertList(List<FeebackProblemInterfaceDO> feedbackProblemInterfaces);

    Integer updateRemindCount(Integer problemId);

    Integer insertForProbleamIdSelective(String deptCode, String userToken, String submitter, String linkTel, Integer feedbackTypeId, String feedbackResult, String feedbackDesc, String feedbackTime, Integer remindCount);

    Integer deleteByProbleId(Integer problemId);

    FeebackProblemInfoDO selectByPrimaryId(Integer problemId);

    PageInfo selectByResultTime(CheckMangerSearcher searcher);

}
