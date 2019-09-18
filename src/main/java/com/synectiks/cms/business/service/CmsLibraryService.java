package com.synectiks.cms.business.service;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.StudentTypeEnum;
import com.synectiks.cms.filter.library.LibraryFilterInput;
import com.synectiks.cms.filter.student.StudentListFilterInput;
import com.synectiks.cms.repository.LibraryRepository;
import com.synectiks.cms.service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class CmsLibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private CommonService commonService;

    public List<CmsLibrary> searchLib(String bookTitle, String author, Long batchId, Long subjectId) throws Exception {
        Library lib = new Library();
        Batch batch = new Batch();
        batch.setId(batchId);

        if (!CommonUtil.isNullOrEmpty(bookTitle)) {
            lib.setBookTitle(bookTitle);
        }
        if(!CommonUtil.isNullOrEmpty(author)){
            lib.setAuthor(author);
        }
        if (subjectId != null && subjectId >= 0) {
            Subject subject = new Subject();
            subject.setId(subjectId);
            lib.setSubject(subject);
        }
        Example<Library> example =Example.of(lib);
        List<Library>list=this.libraryRepository.findAll(example);
        List<CmsLibrary>ls =new ArrayList<>();
        return ls;
    }

    public List<Library> searchBook(LibraryFilterInput filter) {
        Library library = new Library();
        if(!CommonUtil.isNullOrEmpty(filter.getBatchId())) {
            Batch batch = this.commonService.getBatchById(Long.valueOf(filter.getBatchId()));
            if(batch != null) {
                library.setBatch(batch);
            }
        }
        if(!CommonUtil.isNullOrEmpty(filter.getSubjectId())){
            Subject subject =this.commonService.getSubjectById(Long.valueOf(filter.getSubjectId()));
            if(subject != null )
            {
                library.setSubject(subject);
            }
        }
        Example<Library> example = Example.of(library);
        List<Library> list = this.libraryRepository.findAll(example);
        return list;
    }
}
