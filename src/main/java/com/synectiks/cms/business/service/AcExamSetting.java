package com.synectiks.cms.business.service;

import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public  class AcExamSetting implements Serializable{
    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String examType;
    private String departmnt;
    private String bctch;
    private String sectn;
    private String action;
    private String subject;
    private Date startDate;
    private Date endDate;
    private Date examDate;

    public AcExamSetting() { }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getDepartmnt() {
        return departmnt;
    }

    public void setDepartmnt(String departmnt) {
        this.departmnt = departmnt;
    }

    public String getBctch() {
        return bctch;
    }

    public void setBctch(String bctch) {
        this.bctch = bctch;
    }

    public String getSectn() {
        return sectn;
    }

    public void setSectn(String sectn) {
        this.sectn = sectn;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcExamSetting that = (AcExamSetting) o;
        return examType.equals(that.examType) &&
            departmnt.equals(that.departmnt) && bctch.equals(that.bctch);
    }


    @Override
    public int hashCode() {
        return Objects.hash(examType, departmnt, bctch);
    }


    public AcExamSetting(String examType, String departmnt,String bctch, String sectn, String action, String subject, Date examDate, Date startDate, Date endDate) {
        this.examType = examType;
        this.departmnt = departmnt;
        this.bctch = bctch;
        this.examDate= examDate;
        this.sectn = sectn;
        this.action = action;
        this.subject = subject;
        this.startDate = startDate;
        this.endDate = endDate;
    }



    public AcExamSetting merge(AcExamSetting other) {
        assert (this.equals(other));

        String str =this.subject+","+other.subject;
        String[] strWords = str.split("\\,+");
        Arrays.sort(strWords);
        LinkedHashSet<String> lhSetWords
            = new LinkedHashSet<String>( Arrays.asList(strWords) );

        StringBuilder sbTemp = new StringBuilder();
        int index = 0;

        for(String s : lhSetWords){

            if(index > 0)
                sbTemp.append(",");

            sbTemp.append(s);
            index++;
        }

        str = sbTemp.toString();


        String str1 =this.sectn+","+other.sectn;
        String[] strWords1 = str1.split("\\,+");
        Arrays.sort(strWords1);
        LinkedHashSet<String> lhSetWords1
            = new LinkedHashSet<String>( Arrays.asList(strWords1) );

        StringBuilder sbTemp1 = new StringBuilder();
        int index1 = 0;

        for(String s1 : lhSetWords1){

            if(index1 > 0)
                sbTemp1.append(",");

            sbTemp1.append(s1);
            index1++;
        }
        str1 = sbTemp1.toString();






//        action=this.examDate+","+other.examDate;
//        String[] strWords2 = action.split("\\,+");
//        Arrays.sort(strWords2);
//        LinkedHashSet<String> lhSetWords2
//            = new LinkedHashSet<String>( Arrays.asList(strWords2) );
//       String a =Collections.max(lhSetWords2);
//        StringBuilder sbTemp2 = new StringBuilder();
//        int index2 = 0;
//
//        for(String s2 : lhSetWords2){
//
//            if(index2 > 0)
//                sbTemp2.append(",");
//
//            sbTemp2.append(s2);
//            index2++;
//        }
//        action = sbTemp2.toString();
//
//

        return new AcExamSetting(
            this.examType,
            this.departmnt,
            this.bctch,
            sectn=str1,
           this.action,
            subject=str,
            this.examDate,
            this.endDate=other.examDate,
            this.startDate

        );
    }

}
