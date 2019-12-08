package com.synectiks.cms.graphql.types.Employee;

import com.synectiks.cms.entities.Employee;

public class AbstractEmployeePayload {
    private final Employee employee;

    public AbstractEmployeePayload(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
