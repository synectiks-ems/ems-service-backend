package com.synectiks.cms.graphql.types.AdmissionApplication;

import com.synectiks.cms.domain.enumeration.AdmissionStatusEnum;
import com.synectiks.cms.domain.enumeration.CourseEnum;

import java.util.Date;
import java.util.Objects;

public class AbstractAdmissionApplicationInput
{
    private Long id;
    private AdmissionStatusEnum admissionStatus;
    private CourseEnum course;
    private Date admissionDate;
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmissionStatusEnum getAdmissionStatus() {
        return admissionStatus;
    }

    public void setAdmissionStatus(AdmissionStatusEnum admissionStatus) {
        this.admissionStatus = admissionStatus;
    }

    public CourseEnum getCourse() {
        return course;
    }

    public void setCourse(CourseEnum course) {
        this.course = course;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAdmissionApplicationInput that = (AbstractAdmissionApplicationInput) o;
        return Objects.equals(id, that.id) &&
            admissionStatus == that.admissionStatus &&
            course == that.course &&
            Objects.equals(admissionDate, that.admissionDate) &&
            Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, admissionStatus, course, admissionDate, comments);
    }

    @Override
    public String toString() {
        return "AbstractAdmissionApplicationInput{" +
            "id=" + id +
            ", admissionStatus=" + admissionStatus +
            ", course=" + course +
            ", admissionDate=" + admissionDate +
            ", comments='" + comments + '\'' +
            '}';
    }
}
