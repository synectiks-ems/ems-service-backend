package com.synectiks.cms.graphql.types.AdmissionPersonalDetails;

import com.synectiks.cms.entities.AdmissionApplication;

public class AbstractAdmissionPersonalDetailsPayload {
  private final AdmissionApplication admissionApplication;

    public AbstractAdmissionPersonalDetailsPayload(AdmissionApplication admissionApplication) {
        this.admissionApplication = admissionApplication;
    }

    public AdmissionApplication getAdmissionApplication() {
        return admissionApplication;
    }
}
