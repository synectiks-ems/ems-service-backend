package com.synectiks.cms.graphql.types.TypeOfGrading;

import com.synectiks.cms.entities.TypeOfGrading;

public class UpdateTypeOfGradingPayload extends AddTypeOfGradingPayload{
    public UpdateTypeOfGradingPayload(TypeOfGrading typeOfGrading){
        super(typeOfGrading);
    }
}
