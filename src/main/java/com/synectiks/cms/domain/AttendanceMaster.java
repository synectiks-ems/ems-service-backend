package com.synectiks.cms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AttendanceMaster.
 */
@Entity
@Table(name = "attendance_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "attendancemaster")
public class AttendanceMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "jhi_desc")
    private String desc;

    @OneToOne
    @JoinColumn(unique = true)
    private Teach teach;

    @OneToOne
    @JoinColumn(unique = true)
    private Section section;

    @OneToOne
    @JoinColumn(unique = true)
    private AcademicYear academicyear;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public AttendanceMaster desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Teach getTeach() {
        return teach;
    }

    public AttendanceMaster teach(Teach teach) {
        this.teach = teach;
        return this;
    }

    public void setTeach(Teach teach) {
        this.teach = teach;
    }

    public Section getSection() {
        return section;
    }

    public AttendanceMaster section(Section section) {
        this.section = section;
        return this;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public AcademicYear getAcademicyear() {
        return academicyear;
    }

    public AttendanceMaster academicyear(AcademicYear academicYear) {
        this.academicyear = academicYear;
        return this;
    }

    public void setAcademicyear(AcademicYear academicYear) {
        this.academicyear = academicYear;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AttendanceMaster attendanceMaster = (AttendanceMaster) o;
        if (attendanceMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attendanceMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttendanceMaster{" +
            "id=" + getId() +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
