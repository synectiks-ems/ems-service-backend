import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './bank-accounts.reducer';
import { IBankAccounts } from 'app/shared/model/bank-accounts.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IBankAccountsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IBankAccountsUpdateState {
  isNew: boolean;
}

export class BankAccountsUpdate extends React.Component<IBankAccountsUpdateProps, IBankAccountsUpdateState> {
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
      const { bankAccountsEntity } = this.props;
      const entity = {
        ...bankAccountsEntity,
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
    this.props.history.push('/entity/bank-accounts');
  };

  render() {
    const { bankAccountsEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.bankAccounts.home.createOrEditLabel">Create or edit a BankAccounts</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : bankAccountsEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="bank-accounts-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameOfBankLabel">Name Of Bank</Label>
                  <AvInput
                    id="bank-accounts-nameOfBank"
                    type="select"
                    className="form-control"
                    name="nameOfBank"
                    value={(!isNew && bankAccountsEntity.nameOfBank) || 'HDFC'}
                  >
                    <option value="HDFC">HDFC</option>
                    <option value="SBI">SBI</option>
                    <option value="ICICI">ICICI</option>
                    <option value="ANDHRABANK">ANDHRABANK</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="accountNumberLabel" for="accountNumber">
                    Account Number
                  </Label>
                  <AvField
                    id="bank-accounts-accountNumber"
                    type="number"
                    className="form-control"
                    name="accountNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="typeOfAccountLabel" for="typeOfAccount">
                    Type Of Account
                  </Label>
                  <AvField
                    id="bank-accounts-typeOfAccount"
                    type="text"
                    name="typeOfAccount"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ifsCodeLabel" for="ifsCode">
                    Ifs Code
                  </Label>
                  <AvField
                    id="bank-accounts-ifsCode"
                    type="text"
                    name="ifsCode"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="branchLabel" for="branch">
                    Branch
                  </Label>
                  <AvField
                    id="bank-accounts-branch"
                    type="text"
                    name="branch"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="corporateIdLabel" for="corporateId">
                    Corporate Id
                  </Label>
                  <AvField
                    id="bank-accounts-corporateId"
                    type="number"
                    className="form-control"
                    name="corporateId"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/bank-accounts" replace color="info">
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
  bankAccountsEntity: storeState.bankAccounts.entity,
  loading: storeState.bankAccounts.loading,
  updating: storeState.bankAccounts.updating
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
)(BankAccountsUpdate);
