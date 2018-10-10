package com.sztech.cleangovern.service.service.provider;

import com.sztech.cleangovern.model.entity.FeedbackResultInfoDO;

import java.util.List;

public interface IFeedbackResultInfoProvider {

    /**
     *根据PROBLEM_ID查询
     * @param problemId
     * @return
     */
    List<FeedbackResultInfoDO> selectByProblemId(Integer problemId);
}
