import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './student-attendance.reducer';
import { IStudentAttendance } from 'app/shared/model/student-attendance.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStudentAttendanceProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IStudentAttendanceState {
  search: string;
}

export class StudentAttendance extends React.Component<IStudentAttendanceProps, IStudentAttendanceState> {
  state: IStudentAttendanceState = {
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
    const { studentAttendanceList, match } = this.props;
    return (
      <div>
        <h2 id="student-attendance-heading">
          <Translate contentKey="cmsApp.studentAttendance.home.title">Student Attendances</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="cmsApp.studentAttendance.home.createLabel">Create new Student Attendance</Translate>
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
                    placeholder={translate('cmsApp.studentAttendance.home.search')}
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
                  <Translate contentKey="cmsApp.studentAttendance.attendanceStatus">Attendance Status</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.studentAttendance.comments">Comments</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.studentAttendance.student">Student</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.studentAttendance.lecture">Lecture</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {studentAttendanceList.map((studentAttendance, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${studentAttendance.id}`} color="link" size="sm">
                      {studentAttendance.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`cmsApp.AttendanceStatusEnum.${studentAttendance.attendanceStatus}`} />
                  </td>
                  <td>{studentAttendance.comments}</td>
                  <td>
                    {studentAttendance.studentId ? (
                      <Link to={`student/${studentAttendance.studentId}`}>{studentAttendance.studentId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {studentAttendance.lectureId ? (
                      <Link to={`lecture/${studentAttendance.lectureId}`}>{studentAttendance.lectureId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${studentAttendance.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${studentAttendance.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${studentAttendance.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ studentAttendance }: IRootState) => ({
  studentAttendanceList: studentAttendance.entities
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
)(StudentAttendance);
