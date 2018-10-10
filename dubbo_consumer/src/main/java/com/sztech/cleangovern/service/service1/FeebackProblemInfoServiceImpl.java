package com.sztech.cleangovern.service.service1;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.sztech.cleangovern.model.entity.FeebackProblemInfoDO;
import com.sztech.cleangovern.model.entity.FeebackProblemInterfaceDO;
import com.sztech.cleangovern.model.entity.TableColumnInfoDO;
import com.sztech.cleangovern.model.entity.TableInfoDO;
import com.sztech.cleangovern.model.helper.CheckMangerSearcher;
import com.sztech.cleangovern.service.service.provider.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: springcloud
 * @description:
 * @author: jiefu
 * @create: 2018-10-09 09:13
 **/
@Service
public class FeebackProblemInfoServiceImpl implements ITestService {

    @Reference
    private IFeebackProblemInfoProvider feebackProblemInfoProvider;

    @Reference
    private ITableInfoProvider tableInfoProvider;

    @Reference
    private ITableColumnInfoProvider tableColumnInfoProvider;

    @Reference
    private IFeedbackResultInfoProvider feedbackResultInfoProvider;

    @Reference
    private IFeedbackProblemInterfaceProvider feedbackProblemInterfaceProvider;

    @Override
    public List<TableInfoDO> test(String tableName1, String tableName2) {
        System.out.println("消费者开启");
        return tableInfoProvider.findAllTablesBytableName(tableName1,tableName2);
        //return "hello world";
    }

    @Override
    public List<TableColumnInfoDO> getByTableName(String tableName) {
        return tableColumnInfoProvider.getByTableName(tableName);
    }

    /*@Override
    public List<FeedbackResultInfoDO> selectByProblemId(Integer problemId) {
        return feedbackResultInfoProvider.selectByProblemId(problemId);
    }*/

    @Override
    public List<FeebackProblemInterfaceDO> selectByProblemId(Integer problemId) {
        return feedbackProblemInterfaceProvider.selectByProblemId(problemId);
    }

    @Override
    public Integer insertSelective(Integer problemId, String interfaceCode, String interfaceName, Integer dataNum, String interfaceParam, String paramValue, String tableName, String tableColumnName, String interfaceParamType, String problemDesc) {
        return feedbackProblemInterfaceProvider.insertSelective(problemId,interfaceCode,interfaceName,dataNum,interfaceParam,paramValue,tableName,tableColumnName,interfaceParamType,problemDesc);
    }

    @Override
    public Integer deleteByProblemId(Integer problemId) {
        return feedbackProblemInterfaceProvider.deleteByProblemId(problemId);
    }

    @Override
    public Integer insertList(List<FeebackProblemInterfaceDO> feedbackProblemInterfaces) {
        return feedbackProblemInterfaceProvider.insertList(feedbackProblemInterfaces);
    }

    @Override
    public Integer updateRemindCount(Integer problemId) {
        return feebackProblemInfoProvider.updateRemindCount(problemId);
    }

    @Override
    public Integer insertForProbleamIdSelective(String deptCode, String userToken, String submitter, String linkTel, Integer feedbackTypeId, String feedbackResult, String feedbackDesc, String feedbackTime, Integer remindCount) {
        return feebackProblemInfoProvider.insertForProbleamIdSelective(deptCode,userToken,submitter,linkTel,feedbackTypeId,feedbackResult,feedbackDesc,feedbackTime,remindCount);
    }

    @Override
    public Integer deleteByProbleId(Integer problemId) {
        return feebackProblemInfoProvider.deleteByProbleId(problemId);
    }

    @Override
    public FeebackProblemInfoDO selectByPrimaryId(Integer problemId) {
        return feebackProblemInfoProvider.selectByPrimaryId(problemId);
    }

    @Override
    public PageInfo selectByResultTime(CheckMangerSearcher searcher) {
        return feebackProblemInfoProvider.selectByResultTime(searcher);
    }
}
