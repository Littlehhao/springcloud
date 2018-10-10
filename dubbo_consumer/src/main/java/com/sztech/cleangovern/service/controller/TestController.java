package com.sztech.cleangovern.service.controller;

import com.github.pagehelper.PageInfo;
import com.sztech.cleangovern.model.entity.FeebackProblemInfoDO;
import com.sztech.cleangovern.model.entity.FeebackProblemInterfaceDO;
import com.sztech.cleangovern.model.entity.TableColumnInfoDO;
import com.sztech.cleangovern.model.entity.TableInfoDO;
import com.sztech.cleangovern.model.helper.CheckMangerSearcher;
import com.sztech.cleangovern.service.service1.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: springcloud
 * @description:
 * @author: jiefu
 * @create: 2018-10-08 18:08
 **/
@RestController
public class TestController {

    @Autowired
    private ITestService test;

    @GetMapping(value = "test",produces = "application/json;charset=UTF-8")
    public List<TableInfoDO> test(@RequestParam String tableName1, @RequestParam String tableName2){
        List<TableInfoDO> list=test.test(tableName1,tableName2);
        return list;
    }


    @GetMapping(value = "getByTableName",produces = "application/json;charset=UTF-8")
    public List<TableColumnInfoDO> getByTableName(@RequestParam String tableName1){
        List<TableColumnInfoDO> list=test.getByTableName(tableName1);
        return list;
    }

    @GetMapping(value = "selectByProblemId",produces = "application/json;charset=UTF-8")
    public List<FeebackProblemInterfaceDO> selectByProblemId(@RequestParam Integer problemId){
        List<FeebackProblemInterfaceDO> list=test.selectByProblemId(problemId);
        return list;
    }

    @GetMapping(value = "insertSelective",produces = "application/json;charset=UTF-8")
    public Integer insertSelective(@RequestParam Integer problemId, @RequestParam String interfaceCode, @RequestParam String interfaceName,@RequestParam Integer dataNum,@RequestParam String interfaceParam,@RequestParam String paramValue,@RequestParam String tableName, @RequestParam String tableColumnName, @RequestParam String interfaceParamType,@RequestParam String problemDesc){
        Integer count=test.insertSelective(problemId,interfaceCode,interfaceName,dataNum,interfaceParam,paramValue,tableName,tableColumnName,interfaceParamType,problemDesc);
        return count;
    }

    @GetMapping(value = "deleteByProblemId",produces = "application/json;charset=UTF-8")
    public Integer deleteByProblemId(@RequestParam Integer problemId){
        Integer list=test.deleteByProblemId(problemId);
        return list;
    }

    @PostMapping(value = "insertList",produces = "application/json;charset=UTF-8")
    public Integer insertList(@RequestBody List<FeebackProblemInterfaceDO> feedbackProblemInterfaces){
        Integer list=test.insertList(feedbackProblemInterfaces);
        return list;
    }

    @PostMapping(value = "updateRemindCount",produces = "application/json;charset=UTF-8")
    public Integer updateRemindCount(@RequestParam Integer problemId){
        Integer list=test.updateRemindCount(problemId);
        return list;
    }

    @PostMapping(value = "insertForProbleamIdSelective",produces = "application/json;charset=UTF-8")
    public Integer insertForProbleamIdSelective(@RequestParam String deptCode, @RequestParam String userToken, @RequestParam String submitter, @RequestParam String linkTel, @RequestParam Integer feedbackTypeId, @RequestParam String feedbackResult, @RequestParam String feedbackDesc,@RequestParam String feedbackTime, @RequestParam Integer remindCount){
        Integer list=test.insertForProbleamIdSelective(deptCode,userToken,submitter,linkTel,feedbackTypeId,feedbackResult,feedbackDesc,feedbackTime,remindCount);
        return list;
    }

    @PostMapping(value = "deleteByProbleId",produces = "application/json;charset=UTF-8")
    public Integer deleteByProbleId(@RequestParam Integer problemId){
        Integer list=test.deleteByProbleId(problemId);
        return list;
    }

    @PostMapping(value = "selectByPrimaryId",produces = "application/json;charset=UTF-8")
    public FeebackProblemInfoDO selectByPrimaryId(@RequestParam Integer problemId){
        FeebackProblemInfoDO list=test.selectByPrimaryId(problemId);
        return list;
    }

    @PostMapping(value = "selectByResultTime",produces = "application/json;charset=UTF-8")
    public PageInfo selectByResultTime(CheckMangerSearcher searcher){
        PageInfo list=test.selectByResultTime(searcher);
        return list;
    }


    @GetMapping("test1")
    public String test1(){
        return "你好";
    }


}
