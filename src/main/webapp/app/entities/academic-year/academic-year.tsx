import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './academic-year.reducer';
import { IAcademicYear } from 'app/shared/model/academic-year.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAcademicYearProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IAcademicYearState {
  search: string;
}

export class AcademicYear extends React.Component<IAcademicYearProps, IAcademicYearState> {
  state: IAcademicYearState = {
    search: ''
  };

  componentDidMount() {
    this.props.getEntities();
  }

  search = () => {
    if (this.state.search) {
      this.props.getSearchEntities(this.state.search);
    }
  };

  clear = () => {
    this.props.getEntities();
    this.setState({
      search: ''
    });
  };

  handleSearch = event => this.setState({ search: event.target.value });

  render() {
    const { academicYearList, match } = this.props;
    return (
      <div>
        <h2 id="academic-year-heading">
          <Translate contentKey="cmsApp.academicYear.home.title">Academic Years</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="cmsApp.academicYear.home.createLabel">Create new Academic Year</Translate>
          </Link>
        </h2>
        <Row>
          <Col sm="12">
            <AvForm onSubmit={this.search}>
              <AvGroup>
                <InputGroup>
                  <AvInput
                    type="text"
                    name="search"
                    value={this.state.search}
                    onChange={this.handleSearch}
                    placeholder={translate('cmsApp.academicYear.home.search')}
                  />
                  <Button className="input-group-addon">
                    <FontAwesomeIcon icon="search" />
                  </Button>
                  <Button type="reset" className="input-group-addon" onClick={this.clear}>
                    <FontAwesomeIcon icon="trash" />
                  </Button>
                </InputGroup>
              </AvGroup>
            </AvForm>
          </Col>
        </Row>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.academicYear.year">Year</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.academicYear.startDate">Start Date</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.academicYear.endDate">End Date</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.academicYear.holiday">Holiday</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.academicYear.term">Term</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {academicYearList.map((academicYear, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${academicYear.id}`} color="link" size="sm">
                      {academicYear.id}
                    </Button>
                  </td>
                  <td>{academicYear.year}</td>
                  <td>
                    <TextFormat type="date" value={academicYear.startDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={academicYear.endDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{academicYear.holidayId ? <Link to={`holiday/${academicYear.holidayId}`}>{academicYear.holidayId}</Link> : ''}</td>
                  <td>{academicYear.termId ? <Link to={`term/${academicYear.termId}`}>{academicYear.termId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${academicYear.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${academicYear.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${academicYear.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ academicYear }: IRootState) => ({
  academicYearList: academicYear.entities
});

const mapDispatchToProps = {
  getSearchEntities,
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AcademicYear);
