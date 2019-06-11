package com.synectiks.cms.filter.exam;

import com.synectiks.cms.business.service.exam.ExamService;
import com.synectiks.cms.domain.AcademicExamSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamFilterProcessor {


    @Autowired
    private ExamService examService;

    public List<AcademicExamSetting> searchSubject(ExamListFilterInput filter){
        return examService.searchSubject(filter);
    }
    

}

