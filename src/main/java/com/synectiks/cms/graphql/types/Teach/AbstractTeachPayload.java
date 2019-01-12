package com.synectiks.cms.graphql.types.Teach;

import com.synectiks.cms.domain.Teach;

public class AbstractTeachPayload {
    private final Teach teach;

    public AbstractTeachPayload(Teach teach) {
        this.teach = teach;
    }

	public Teach getTeach() {
		return teach;
	}

	

    
}
