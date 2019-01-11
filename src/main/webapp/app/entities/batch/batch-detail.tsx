import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './batch.reducer';
import { IBatch } from 'app/shared/model/batch.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBatchDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class BatchDetail extends React.Component<IBatchDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { batchEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.batch.detail.title">Batch</Translate> [<b>{batchEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="batch">
                <Translate contentKey="cmsApp.batch.batch">Batch</Translate>
              </span>
            </dt>
            <dd>{batchEntity.batch}</dd>
            <dt>
              <Translate contentKey="cmsApp.batch.department">Department</Translate>
            </dt>
            <dd>{batchEntity.departmentId ? batchEntity.departmentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/batch" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/batch/${batchEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ batch }: IRootState) => ({
  batchEntity: batch.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BatchDetail);