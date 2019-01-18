import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './legal-entity.reducer';
import { ILegalEntity } from 'app/shared/model/legal-entity.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface ILegalEntityUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface ILegalEntityUpdateState {
  isNew: boolean;
}

export class LegalEntityUpdate extends React.Component<ILegalEntityUpdateProps, ILegalEntityUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { legalEntityEntity } = this.props;
      const entity = {
        ...legalEntityEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/legal-entity');
  };

  render() {
    const { legalEntityEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.legalEntity.home.createOrEditLabel">Create or edit a LegalEntity</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : legalEntityEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="legal-entity-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="logoLabel" for="logo">
                    Logo
                  </Label>
                  <AvField
                    id="legal-entity-logo"
                    type="number"
                    className="form-control"
                    name="logo"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="legalNameOfTheCollegeLabel" for="legalNameOfTheCollege">
                    Legal Name Of The College
                  </Label>
                  <AvField
                    id="legal-entity-legalNameOfTheCollege"
                    type="text"
                    name="legalNameOfTheCollege"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="typeOfCollegeLabel">Type Of College</Label>
                  <AvInput
                    id="legal-entity-typeOfCollege"
                    type="select"
                    className="form-control"
                    name="typeOfCollege"
                    value={(!isNew && legalEntityEntity.typeOfCollege) || 'PRIVATE'}
                  >
                    <option value="PRIVATE">PRIVATE</option>
                    <option value="PUBLIC">PUBLIC</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="dateOfIncorporationLabel" for="dateOfIncorporation">
                    Date Of Incorporation
                  </Label>
                  <AvField
                    id="legal-entity-dateOfIncorporation"
                    type="date"
                    className="form-control"
                    name="dateOfIncorporation"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="registeredOfficeAddressLabel" for="registeredOfficeAddress">
                    Registered Office Address
                  </Label>
                  <AvField
                    id="legal-entity-registeredOfficeAddress"
                    type="text"
                    name="registeredOfficeAddress"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="collegeIdentificationNumberLabel" for="collegeIdentificationNumber">
                    College Identification Number
                  </Label>
                  <AvField
                    id="legal-entity-collegeIdentificationNumber"
                    type="text"
                    name="collegeIdentificationNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="panLabel" for="pan">
                    Pan
                  </Label>
                  <AvField
                    id="legal-entity-pan"
                    type="text"
                    name="pan"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tanLabel" for="tan">
                    Tan
                  </Label>
                  <AvField
                    id="legal-entity-tan"
                    type="text"
                    name="tan"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tanCircleNumberLabel" for="tanCircleNumber">
                    Tan Circle Number
                  </Label>
                  <AvField
                    id="legal-entity-tanCircleNumber"
                    type="text"
                    name="tanCircleNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="citTdsLocationLabel" for="citTdsLocation">
                    Cit Tds Location
                  </Label>
                  <AvField
                    id="legal-entity-citTdsLocation"
                    type="text"
                    name="citTdsLocation"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="formSignatoryLabel" for="formSignatory">
                    Form Signatory
                  </Label>
                  <AvField
                    id="legal-entity-formSignatory"
                    type="text"
                    name="formSignatory"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="pfNumberLabel" for="pfNumber">
                    Pf Number
                  </Label>
                  <AvField
                    id="legal-entity-pfNumber"
                    type="text"
                    name="pfNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="registrationDateLabel" for="registrationDate">
                    Registration Date
                  </Label>
                  <AvField
                    id="legal-entity-registrationDate"
                    type="date"
                    className="form-control"
                    name="registrationDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="esiNumberLabel" for="esiNumber">
                    Esi Number
                  </Label>
                  <AvField
                    id="legal-entity-esiNumber"
                    type="number"
                    className="form-control"
                    name="esiNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ptRegistrationDateLabel" for="ptRegistrationDate">
                    Pt Registration Date
                  </Label>
                  <AvField
                    id="legal-entity-ptRegistrationDate"
                    type="date"
                    className="form-control"
                    name="ptRegistrationDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ptSignatoryLabel" for="ptSignatory">
                    Pt Signatory
                  </Label>
                  <AvField
                    id="legal-entity-ptSignatory"
                    type="text"
                    name="ptSignatory"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ptNumberLabel" for="ptNumber">
                    Pt Number
                  </Label>
                  <AvField
                    id="legal-entity-ptNumber"
                    type="number"
                    className="form-control"
                    name="ptNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/legal-entity" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  legalEntityEntity: storeState.legalEntity.entity,
  loading: storeState.legalEntity.loading,
  updating: storeState.legalEntity.updating
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LegalEntityUpdate);
