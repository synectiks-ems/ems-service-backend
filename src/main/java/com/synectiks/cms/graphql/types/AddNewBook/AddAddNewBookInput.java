package com.synectiks.cms.graphql.types.AddNewBook;

import com.synectiks.cms.domain.Batch;

import java.util.Objects;

public class AddAddNewBookInput extends AbstractAddNewBookInput {
    private Long batchId;
    private Long subjectId;

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddAddNewBookInput that = (AddAddNewBookInput) o;
        return Objects.equals(batchId, that.batchId) &&
            Objects.equals(subjectId, that.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(batchId, subjectId);
    }

    @Override
    public String toString() {
        return "AddNewBookInput{" +
            "batchId=" + batchId +
            ", subjectId=" + subjectId +
            '}';
    }
}
