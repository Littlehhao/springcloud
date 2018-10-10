package com.sztech.cleangovern.service.service.provider;

import com.github.pagehelper.PageInfo;
import com.sztech.cleangovern.model.entity.FeebackProblemInfoDO;
import com.sztech.cleangovern.model.helper.CheckMangerSearcher;

public interface IFeebackProblemInfoProvider {


    /**
     * 根据问题反馈状态和日期查询
     * @param searcher
     * @return
     */
    PageInfo selectByResultTime(CheckMangerSearcher searcher);



    /**
     *更新remind_count
     * @param problemId
     * @return
     */
    Integer updateRemindCount(Integer problemId);


    /**
     * 插入一条数据，并返回主键ID
     * @param deptCode
     * @param userToken
     * @param submitter
     * @param linkTel
     * @param feedbackTypeId
     * @param feedbackResult
     * @param feedbackDesc
     * @param feedbackTime
     * @param remindCount
     * @return
     */
    Integer insertForProbleamIdSelective(String deptCode, String userToken, String submitter, String linkTel, Integer feedbackTypeId, String feedbackResult, String feedbackDesc, String feedbackTime, Integer remindCount);


    /**
     * 根据PROBLEM_ID删除数据
     * @param problemId
     * @return
     */
    Integer deleteByProbleId(Integer problemId);


    /**
     * 根据主键PROBLEM_ID查询数据
     */
    FeebackProblemInfoDO selectByPrimaryId(Integer problemId);
}
