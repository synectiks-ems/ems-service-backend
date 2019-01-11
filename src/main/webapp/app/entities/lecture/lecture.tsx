import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './lecture.reducer';
import { ILecture } from 'app/shared/model/lecture.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILectureProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface ILectureState {
  search: string;
}

export class Lecture extends React.Component<ILectureProps, ILectureState> {
  state: ILectureState = {
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
    const { lectureList, match } = this.props;
    return (
      <div>
        <h2 id="lecture-heading">
          <Translate contentKey="cmsApp.lecture.home.title">Lectures</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="cmsApp.lecture.home.createLabel">Create new Lecture</Translate>
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
                    placeholder={translate('cmsApp.lecture.home.search')}
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
                  <Translate contentKey="cmsApp.lecture.lecDate">Lec Date</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.lecture.lastUpdatedOn">Last Updated On</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.lecture.lastUpdatedBy">Last Updated By</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.lecture.lecStatus">Lec Status</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.lecture.desc">Desc</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.lecture.attendancemaster">Attendancemaster</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {lectureList.map((lecture, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${lecture.id}`} color="link" size="sm">
                      {lecture.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={lecture.lecDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={lecture.lastUpdatedOn} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{lecture.lastUpdatedBy}</td>
                  <td>
                    <Translate contentKey={`cmsApp.LecStatusEnum.${lecture.lecStatus}`} />
                  </td>
                  <td>{lecture.desc}</td>
                  <td>
                    {lecture.attendancemasterId ? (
                      <Link to={`attendanceMaster/${lecture.attendancemasterId}`}>{lecture.attendancemasterId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${lecture.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${lecture.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${lecture.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ lecture }: IRootState) => ({
  lectureList: lecture.entities
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
)(Lecture);