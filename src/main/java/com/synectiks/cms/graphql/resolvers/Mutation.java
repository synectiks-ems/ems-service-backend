package com.synectiks.cms.graphql.resolvers;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.graphql.types.StudentSubject.*;
import com.synectiks.cms.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.collect.Lists;
import com.synectiks.cms.graphql.types.AcademicYear.AddAcademicYearInput;
import com.synectiks.cms.graphql.types.AcademicYear.AddAcademicYearPayload;
import com.synectiks.cms.graphql.types.AcademicYear.RemoveAcademicYearInput;
import com.synectiks.cms.graphql.types.AcademicYear.RemoveAcademicYearPayload;
import com.synectiks.cms.graphql.types.AcademicYear.UpdateAcademicYearInput;
import com.synectiks.cms.graphql.types.AcademicYear.UpdateAcademicYearPayload;
import com.synectiks.cms.graphql.types.AttendanceMaster.AddAttendanceMasterInput;
import com.synectiks.cms.graphql.types.AttendanceMaster.AddAttendanceMasterPayload;
import com.synectiks.cms.graphql.types.AttendanceMaster.RemoveAttendanceMasterInput;
import com.synectiks.cms.graphql.types.AttendanceMaster.RemoveAttendanceMasterPayload;
import com.synectiks.cms.graphql.types.AttendanceMaster.UpdateAttendanceMasterInput;
import com.synectiks.cms.graphql.types.AttendanceMaster.UpdateAttendanceMasterPayload;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.AddAuthorizedSignatoryInput;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.AddAuthorizedSignatoryPayload;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.RemoveAuthorizedSignatoryInput;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.RemoveAuthorizedSignatoryPayload;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.UpdateAuthorizedSignatoryInput;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.UpdateAuthorizedSignatoryPayload;
import com.synectiks.cms.graphql.types.BankAccounts.AddBankAccountsInput;
import com.synectiks.cms.graphql.types.BankAccounts.AddBankAccountsPayload;
import com.synectiks.cms.graphql.types.BankAccounts.RemoveBankAccountsInput;
import com.synectiks.cms.graphql.types.BankAccounts.RemoveBankAccountsPayload;
import com.synectiks.cms.graphql.types.BankAccounts.UpdateBankAccountsInput;
import com.synectiks.cms.graphql.types.BankAccounts.UpdateBankAccountsPayload;
import com.synectiks.cms.graphql.types.Batch.AddBatchInput;
import com.synectiks.cms.graphql.types.Batch.AddBatchPayload;
import com.synectiks.cms.graphql.types.Batch.RemoveBatchInput;
import com.synectiks.cms.graphql.types.Batch.RemoveBatchPayload;
import com.synectiks.cms.graphql.types.Batch.UpdateBatchInput;
import com.synectiks.cms.graphql.types.Batch.UpdateBatchPayload;
import com.synectiks.cms.graphql.types.Branch.AddBranchInput;
import com.synectiks.cms.graphql.types.Branch.AddBranchPayload;
import com.synectiks.cms.graphql.types.Branch.RemoveBranchInput;
import com.synectiks.cms.graphql.types.Branch.RemoveBranchPayload;
import com.synectiks.cms.graphql.types.Branch.UpdateBranchInput;
import com.synectiks.cms.graphql.types.Branch.UpdateBranchPayload;
import com.synectiks.cms.graphql.types.College.AddCollegeInput;
import com.synectiks.cms.graphql.types.College.AddCollegePayload;
import com.synectiks.cms.graphql.types.College.RemoveCollegeInput;
import com.synectiks.cms.graphql.types.College.RemoveCollegePayload;
import com.synectiks.cms.graphql.types.College.UpdateCollegeInput;
import com.synectiks.cms.graphql.types.College.UpdateCollegePayload;
import com.synectiks.cms.graphql.types.CourseOffer.AddCourseOfferInput;
import com.synectiks.cms.graphql.types.CourseOffer.AddCourseOfferPayload;
import com.synectiks.cms.graphql.types.CourseOffer.RemoveCourseOfferInput;
import com.synectiks.cms.graphql.types.CourseOffer.RemoveCourseOfferPayload;
import com.synectiks.cms.graphql.types.CourseOffer.UpdateCourseOfferInput;
import com.synectiks.cms.graphql.types.CourseOffer.UpdateCourseOfferPayload;
import com.synectiks.cms.graphql.types.Department.AddDepartmentInput;
import com.synectiks.cms.graphql.types.Department.AddDepartmentPayload;
import com.synectiks.cms.graphql.types.Department.RemoveDepartmentInput;
import com.synectiks.cms.graphql.types.Department.RemoveDepartmentPayload;
import com.synectiks.cms.graphql.types.Department.UpdateDepartmentInput;
import com.synectiks.cms.graphql.types.Department.UpdateDepartmentPayload;
import com.synectiks.cms.graphql.types.Holiday.AddHolidayInput;
import com.synectiks.cms.graphql.types.Holiday.AddHolidayPayload;
import com.synectiks.cms.graphql.types.Holiday.RemoveHolidayInput;
import com.synectiks.cms.graphql.types.Holiday.RemoveHolidayPayload;
import com.synectiks.cms.graphql.types.Holiday.UpdateHolidayInput;
import com.synectiks.cms.graphql.types.Holiday.UpdateHolidayPayload;
import com.synectiks.cms.graphql.types.Lecture.AddLectureInput;
import com.synectiks.cms.graphql.types.Lecture.AddLecturePayload;
import com.synectiks.cms.graphql.types.Lecture.RemoveLectureInput;
import com.synectiks.cms.graphql.types.Lecture.RemoveLecturePayload;
import com.synectiks.cms.graphql.types.Lecture.UpdateLectureInput;
import com.synectiks.cms.graphql.types.Lecture.UpdateLecturePayload;
import com.synectiks.cms.graphql.types.LegalEntity.AddLegalEntityInput;
import com.synectiks.cms.graphql.types.LegalEntity.AddLegalEntityPayload;
import com.synectiks.cms.graphql.types.LegalEntity.RemoveLegalEntityInput;
import com.synectiks.cms.graphql.types.LegalEntity.RemoveLegalEntityPayload;
import com.synectiks.cms.graphql.types.LegalEntity.UpdateLegalEntityInput;
import com.synectiks.cms.graphql.types.LegalEntity.UpdateLegalEntityPayload;
import com.synectiks.cms.graphql.types.Location.AddLocationInput;
import com.synectiks.cms.graphql.types.Location.AddLocationPayload;
import com.synectiks.cms.graphql.types.Location.RemoveLocationInput;
import com.synectiks.cms.graphql.types.Location.RemoveLocationPayload;
import com.synectiks.cms.graphql.types.Location.UpdateLocationInput;
import com.synectiks.cms.graphql.types.Location.UpdateLocationPayload;
import com.synectiks.cms.graphql.types.Section.AddSectionInput;
import com.synectiks.cms.graphql.types.Section.AddSectionPayload;
import com.synectiks.cms.graphql.types.Section.RemoveSectionInput;
import com.synectiks.cms.graphql.types.Section.RemoveSectionPayload;
import com.synectiks.cms.graphql.types.Section.UpdateSectionInput;
import com.synectiks.cms.graphql.types.Section.UpdateSectionPayload;
import com.synectiks.cms.graphql.types.Student.AddStudentInput;
import com.synectiks.cms.graphql.types.Student.AddStudentPayload;
import com.synectiks.cms.graphql.types.Student.RemoveStudentInput;
import com.synectiks.cms.graphql.types.Student.RemoveStudentPayload;
import com.synectiks.cms.graphql.types.Student.UpdateStudentInput;
import com.synectiks.cms.graphql.types.Student.UpdateStudentPayload;
import com.synectiks.cms.graphql.types.StudentAttendance.AddStudentAttendanceInput;
import com.synectiks.cms.graphql.types.StudentAttendance.AddStudentAttendancePayload;
import com.synectiks.cms.graphql.types.StudentAttendance.RemoveStudentAttendanceInput;
import com.synectiks.cms.graphql.types.StudentAttendance.RemoveStudentAttendancePayload;
import com.synectiks.cms.graphql.types.StudentAttendance.UpdateStudentAttendanceInput;
import com.synectiks.cms.graphql.types.StudentAttendance.UpdateStudentAttendancePayload;
import com.synectiks.cms.graphql.types.Subject.AddSubjectInput;
import com.synectiks.cms.graphql.types.Subject.AddSubjectPayload;
import com.synectiks.cms.graphql.types.Subject.RemoveSubjectInput;
import com.synectiks.cms.graphql.types.Subject.RemoveSubjectPayload;
import com.synectiks.cms.graphql.types.Subject.UpdateSubjectInput;
import com.synectiks.cms.graphql.types.Subject.UpdateSubjectPayload;
import com.synectiks.cms.graphql.types.Teach.AddTeachInput;
import com.synectiks.cms.graphql.types.Teach.AddTeachPayload;
import com.synectiks.cms.graphql.types.Teach.RemoveTeachInput;
import com.synectiks.cms.graphql.types.Teach.RemoveTeachPayload;
import com.synectiks.cms.graphql.types.Teach.UpdateTeachInput;
import com.synectiks.cms.graphql.types.Teach.UpdateTeachPayload;
import com.synectiks.cms.graphql.types.Teacher.AddTeacherInput;
import com.synectiks.cms.graphql.types.Teacher.AddTeacherPayload;
import com.synectiks.cms.graphql.types.Teacher.RemoveTeacherInput;
import com.synectiks.cms.graphql.types.Teacher.RemoveTeacherPayload;
import com.synectiks.cms.graphql.types.Teacher.UpdateTeacherInput;
import com.synectiks.cms.graphql.types.Teacher.UpdateTeacherPayload;
import com.synectiks.cms.graphql.types.Term.AddTermInput;
import com.synectiks.cms.graphql.types.Term.AddTermPayload;
import com.synectiks.cms.graphql.types.Term.RemoveTermInput;
import com.synectiks.cms.graphql.types.Term.RemoveTermPayload;
import com.synectiks.cms.graphql.types.Term.UpdateTermInput;
import com.synectiks.cms.graphql.types.Term.UpdateTermPayload;


@Component
public class Mutation implements GraphQLMutationResolver {

    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);

    private final AcademicYearRepository academicYearRepository;
    private final AttendanceMasterRepository attendanceMasterRepository;
    private final AuthorizedSignatoryRepository authorizedSignatoryRepository;
    private final BankAccountsRepository bankAccountsRepository;
    private final BatchRepository batchRepository;
    private final BranchRepository branchRepository;
    private final CollegeRepository collegeRepository;
    private final CourseOfferRepository courseOfferRepository;
    private final DepartmentRepository departmentRepository;
    private final HolidayRepository holidayRepository;
    private final LectureRepository lectureRepository;

    //    private final InstituteRepository instituteRepository;
    private final LegalEntityRepository legalEntityRepository;
    private final LocationRepository locationRepository;
    private final SectionRepository sectionRepository;
//  private final SemesterRepository semesterRepository;
    private final StudentRepository studentRepository;
    private final StudentAttendanceRepository studentAttendanceRepository;
//    private final StudentYearRepository studentYearRepository;
    private final SubjectRepository subjectRepository;
    private final TeachRepository teachRepository;
    private final TeacherRepository teacherRepository;
    private final TermRepository termRepository;
    private final StudentSubjectRepository studentSubjectRepository;

    public Mutation(LectureRepository lectureRepository, AttendanceMasterRepository attendanceMasterRepository, CourseOfferRepository courseOfferRepository, TeachRepository teachRepository, BatchRepository batchRepository, StudentRepository studentRepository, CollegeRepository collegeRepository, BranchRepository branchRepository, SectionRepository sectionRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, LegalEntityRepository legalEntityRepository, AuthorizedSignatoryRepository authorizedSignatoryRepository, BankAccountsRepository bankAccountsRepository, DepartmentRepository departmentRepository, LocationRepository locationRepository, StudentAttendanceRepository studentAttendanceRepository, AcademicYearRepository academicYearRepository, HolidayRepository holidayRepository, TermRepository termRepository, StudentSubjectRepository studentSubjectRepository) {
        this.batchRepository = batchRepository;
    	this.studentRepository = studentRepository;
//        this.instituteRepository = instituteRepository;
        this.collegeRepository = collegeRepository;
        this.courseOfferRepository= courseOfferRepository;
//        this.studentYearRepository = studentYearRepository;
//        this.semesterRepository = semesterRepository;
        this.branchRepository = branchRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.legalEntityRepository = legalEntityRepository;
        this.authorizedSignatoryRepository = authorizedSignatoryRepository;
        this.bankAccountsRepository = bankAccountsRepository;
        this.departmentRepository = departmentRepository;
        this.locationRepository = locationRepository;
        this.studentAttendanceRepository = studentAttendanceRepository;
        this.academicYearRepository = academicYearRepository;
        this.holidayRepository = holidayRepository;
        this.termRepository = termRepository;
        this.teachRepository = teachRepository;
        this.attendanceMasterRepository=attendanceMasterRepository;
        this.lectureRepository = lectureRepository;
        this.studentSubjectRepository = studentSubjectRepository;
    }

    public AddStudentPayload addStudent(AddStudentInput addStudentInput) {
        final Section section = sectionRepository.findById(addStudentInput.getSectionId()).get();
        final Branch branch = branchRepository.findById(addStudentInput.getBranchId()).get();
        final Department department = departmentRepository.findById(addStudentInput.getDepartmentId()).get();
        final Batch batch = batchRepository.findById(addStudentInput.getBatchId()).get();
        final Student student = new Student();
        student.setStudentName(addStudentInput.getStudentName());
        student.setBatch(batch);
        student.setSection(section);
        student.setBranch(branch);
        student.setDepartment(department);
        studentRepository.save(student);
        return new AddStudentPayload(student);
    }

    public UpdateStudentPayload updateStudent(UpdateStudentInput updateStudentInput) {
        Student student = studentRepository.findById(updateStudentInput.getId()).get();
        if (updateStudentInput.getStudentName() != null) {
            student.setStudentName(updateStudentInput.getStudentName());
        }
        if (updateStudentInput.getBatchId() != null) {
        	final Batch batch = batchRepository.findById(updateStudentInput.getBatchId()).get();
            student.setBatch(batch);
        }
        if (updateStudentInput.getSectionId() != null) {
        	final Section section = sectionRepository.findById(updateStudentInput.getSectionId()).get();
            student.setSection(section);
        }
        if (updateStudentInput.getBranchId() != null) {
        	final Branch branch = branchRepository.findById(updateStudentInput.getBranchId()).get();
            student.setBranch(branch);
        }
        if (updateStudentInput.getDepartmentId() != null) {
        	final Department department = departmentRepository.findById(updateStudentInput.getDepartmentId()).get();
            student.setDepartment(department);
        }
        studentRepository.save(student);

        return new UpdateStudentPayload(student);
    }

    public RemoveStudentPayload removeStudent(RemoveStudentInput removeStudentInput) {
        Student student = studentRepository.findById(removeStudentInput.getStudentId()).get();
        studentRepository.delete(student);
        return new RemoveStudentPayload(Lists.newArrayList(studentRepository.findAll()));
    }

    public AddStudentSubjectPayload addStudentSubject(AddStudentSubjectInput addStudentSubjectInput) {
        final Student student = studentRepository.findById(addStudentSubjectInput.getStudentId()).get();
        final Subject subject = subjectRepository.findById(addStudentSubjectInput.getSubjectId()).get();
        final StudentSubject studentSubject = new StudentSubject();
        studentSubject.setComments(addStudentSubjectInput.getComments());
        studentSubject.setLastupdatedDate(addStudentSubjectInput.getLastupdatedDate());
        studentSubject.setStudent(student);
        studentSubject.setSubject(subject);
        studentSubjectRepository.save(studentSubject);
        return new AddStudentSubjectPayload(studentSubject);
    }

    public UpdateStudentSubjectPayload updateStudentSubject(UpdatestudentSubjectInput updateStudentSubjectInput) {
        StudentSubject studentSubject = studentSubjectRepository.findById(updateStudentSubjectInput.getId()).get();
        if (updateStudentSubjectInput.getComments() != null) {
            studentSubject.setComments(updateStudentSubjectInput.getComments());
        }
        if (updateStudentSubjectInput.getLastupdatedDate() != null) {
            studentSubject.setLastupdatedDate(updateStudentSubjectInput.getLastupdatedDate());
        }
        if (updateStudentSubjectInput.getStudentId() != null) {
            final Student student = studentRepository.findById(updateStudentSubjectInput.getStudentId()).get();
            studentSubject.setStudent(student);
        }
        if (updateStudentSubjectInput.getSubjectId() != null) {
            final Subject subject = subjectRepository.findById(updateStudentSubjectInput.getSubjectId()).get();
            studentSubject.setSubject(subject);
        }
        studentSubjectRepository.save(studentSubject);

        return new UpdateStudentSubjectPayload(studentSubject);
    }

    public RemoveStudentSubjectPayload removeStudentSubject(RemoveStudentSubjectInput removeStudentSubjectInput) {
        StudentSubject studentSubject = studentSubjectRepository.findById(removeStudentSubjectInput.getStudentSubjectId()).get();
        studentSubjectRepository.delete(studentSubject);
        return new RemoveStudentSubjectPayload(Lists.newArrayList(studentSubjectRepository.findAll()));
    }


//    public AddInstitutePayload addInstitute(AddInstituteInput addInstituteInput) {
//        final Institute institute = new Institute();
//        institute.setCode(addInstituteInput.getCode());
//        institute.setName(addInstituteInput.getName());
//        institute.setYear(addInstituteInput.getYear());
//
//        instituteRepository.save(institute);
//
//        return new AddInstitutePayload(institute);
//    }

    /*public AddStudentYearPayload addStudentYear(AddStudentYearInput addStudentYearInput) {
        final StudentYear studentYear = new StudentYear();
        studentYear.setYearDesc(addStudentYearInput.getYearDesc());
        studentYearRepository.save(studentYear);

        return new AddStudentYearPayload(studentYear);
    }

    public UpdateStudentYearPayload updateStudentYear(UpdateStudentYearInput updateStudentYearInput) {
        StudentYear studentYear = studentYearRepository.findById(updateStudentYearInput.getId()).get();
        if (updateStudentYearInput.getYearDesc() != null) {
            studentYear.setYearDesc(updateStudentYearInput.getYearDesc());
        }

        studentYearRepository.save(studentYear);

        return new UpdateStudentYearPayload(studentYear);
    }

    public RemoveStudentYearPayload removeStudentYear(RemoveStudentYearInput removeStudentYearInput) {
        StudentYear studentYear = studentYearRepository.findById(removeStudentYearInput.getStudentYearId()).get();
        studentYearRepository.delete(studentYear);

        return new RemoveStudentYearPayload(Lists.newArrayList(studentYearRepository.findAll()));
    }*/

    public AddBankAccountsPayload addBankAccounts(AddBankAccountsInput addBankAccountsInput) {
        final BankAccounts bankAccounts = new BankAccounts();
        bankAccounts.setNameOfBank(addBankAccountsInput.getNameOfBank());
        bankAccounts.setAccountNumber(addBankAccountsInput.getAccountNumber());
        bankAccounts.setTypeOfAccount(addBankAccountsInput.getTypeOfAccount());
        bankAccounts.setIfsCode(addBankAccountsInput.getIfsCode());
        bankAccounts.setBranch(addBankAccountsInput.getBranch());
        bankAccounts.setCorporateId(addBankAccountsInput.getCorporateId());
        bankAccountsRepository.save(bankAccounts);

        return new AddBankAccountsPayload(bankAccounts);
    }

    public UpdateBankAccountsPayload updateBankAccounts(UpdateBankAccountsInput updateBankAccountsInput) {
        BankAccounts bankAccounts = bankAccountsRepository.findById(updateBankAccountsInput.getId()).get();
        if (updateBankAccountsInput.getNameOfBank() != null) {
            bankAccounts.setNameOfBank(updateBankAccountsInput.getNameOfBank());
        }
        if (updateBankAccountsInput.getAccountNumber() != null) {
            bankAccounts.setAccountNumber(updateBankAccountsInput.getAccountNumber());
        }
        if (updateBankAccountsInput.getTypeOfAccount() != null) {
            bankAccounts.setTypeOfAccount(updateBankAccountsInput.getTypeOfAccount());
        }
        if (updateBankAccountsInput.getIfsCode() != null) {
            bankAccounts.setIfsCode(updateBankAccountsInput.getIfsCode());
        }
        if (updateBankAccountsInput.getBranch() != null) {
            bankAccounts.setBranch(updateBankAccountsInput.getBranch());
        }
        if (updateBankAccountsInput.getCorporateId() != null) {
            bankAccounts.setCorporateId(updateBankAccountsInput.getCorporateId());
        }
        bankAccountsRepository.save(bankAccounts);

        return new UpdateBankAccountsPayload(bankAccounts);
    }

    public RemoveBankAccountsPayload removeBankAccounts(RemoveBankAccountsInput removeBankAccountsInput) {
        BankAccounts bankAccounts = bankAccountsRepository.findById(removeBankAccountsInput.getBankAccountsId()).get();
        bankAccountsRepository.delete(bankAccounts);
        return new RemoveBankAccountsPayload(Lists.newArrayList(bankAccountsRepository.findAll()));
    }

    public AddDepartmentPayload addDepartment(AddDepartmentInput addDepartmentsInput) {
//    	Student student = studentRepository.findById(addDepartmentsInput.getStudentId()).get();
    	AcademicYear academicYear = academicYearRepository.findById(addDepartmentsInput.getAcademicyearId()).get();
    	Branch branch = branchRepository.findById(addDepartmentsInput.getBranchId()).get();
        final Department departments = new Department();
        departments.setName(addDepartmentsInput.getName());
        departments.setDescription(addDepartmentsInput.getDescription());
        departments.setDeptHead(addDepartmentsInput.getDeptHead());
//        departments.setStudent(student);
        departments.setBranch(branch);
        departments.setAcademicyear(academicYear);
        departmentRepository.save(departments);

        return new AddDepartmentPayload(departments);
    }

    public UpdateDepartmentPayload updateDepartment(UpdateDepartmentInput updateDepartmentsInput) {
        Department departments = departmentRepository.findById(updateDepartmentsInput.getId()).get();
        if (updateDepartmentsInput.getName() != null) {
            departments.setName(updateDepartmentsInput.getName());
        }
        if (updateDepartmentsInput.getDescription() != null) {
            departments.setDescription(updateDepartmentsInput.getDescription());
        }
        if (updateDepartmentsInput.getDeptHead() != null) {
            departments.setDeptHead(updateDepartmentsInput.getDeptHead());
        }
//        if(updateDepartmentsInput.getStudentId() != null) {
//        	Student student = studentRepository.findById(updateDepartmentsInput.getStudentId()).get();
//        	departments.setStudent(student);
//        }
        if(updateDepartmentsInput.getBranchId() != null) {
        	Branch branch = branchRepository.findById(updateDepartmentsInput.getBranchId()).get();
        	departments.setBranch(branch);
        }
        if(updateDepartmentsInput.getAcademicyearId() != null) {
        	AcademicYear academicYear = academicYearRepository.findById(updateDepartmentsInput.getAcademicyearId()).get();
        	departments.setAcademicyear(academicYear);
        }
        departmentRepository.save(departments);

        return new UpdateDepartmentPayload(departments);
    }

    public RemoveDepartmentPayload removeDepartment(RemoveDepartmentInput removeDepartmentsInput) {
        Department departments = departmentRepository.findById(removeDepartmentsInput.getDepartmentId()).get();
        departmentRepository.delete(departments);
        return new RemoveDepartmentPayload(Lists.newArrayList(departmentRepository.findAll()));
    }

    public AddLocationPayload addLocation(AddLocationInput addLocationInput) {
        final Location location = new Location();
        location.setName(addLocationInput.getName());
        location.setAddress(addLocationInput.getAddress());
        location.setAppliesTo(addLocationInput.getAppliesTo());
        locationRepository.save(location);

        return new AddLocationPayload(location);
    }

    public UpdateLocationPayload updateLocation(UpdateLocationInput updateLocationInput) {
        Location location = locationRepository.findById(updateLocationInput.getId()).get();
        if (updateLocationInput.getName() != null) {
            location.setName(updateLocationInput.getName());
        }

        if (updateLocationInput.getAppliesTo() != null) {
            location.setAppliesTo(updateLocationInput.getAppliesTo());
        }

        if (updateLocationInput.getAddress() != null) {
            location.setAddress(updateLocationInput.getAddress());
        }
        locationRepository.save(location);

        return new UpdateLocationPayload(location);
    }

    public RemoveLocationPayload removeLocation(RemoveLocationInput removeLocationInput) {
        Location location = locationRepository.findById(removeLocationInput.getLocationId()).get();
        locationRepository.delete(location);
        return new RemoveLocationPayload(Lists.newArrayList(locationRepository.findAll()));
    }

    /*public AddSemesterPayload addSemester(AddSemesterInput addSemesterInput) {
        final Semester semester = new Semester();
        semester.setSem(addSemesterInput.getSem());
        semesterRepository.save(semester);

        return new AddSemesterPayload(semester);
    }

    public UpdateSemesterPayload updateSemester(UpdateSemesterInput updateSemesterInput) {
        Semester semester = semesterRepository.findById(updateSemesterInput.getId()).get();
        if (updateSemesterInput.getSem() != null) {
            semester.setSem(updateSemesterInput.getSem());
        }

        semesterRepository.save(semester);

        return new UpdateSemesterPayload(semester);
    }

    public RemoveSemesterPayload removeSemester(RemoveSemesterInput removeSemesterInput) {
        Semester semester = semesterRepository.findById(removeSemesterInput.getSemesterId()).get();
        semesterRepository.delete(semester);
        return new RemoveSemesterPayload(Lists.newArrayList(semesterRepository.findAll()));
    }
*/

//    public UpdateInstitutePayload updateInstitute(UpdateInstituteInput updateInstituteInput) {
//        Institute institute = instituteRepository.findById(updateInstituteInput.getInstituteId());
//        if (updateInstituteInput.getCode() != null) {
//            institute.setCode(updateInstituteInput.getCode());
//        }
//
//        if (updateInstituteInput.getName() != null) {
//            institute.setName(updateInstituteInput.getName());
//        }
//
//        if (updateInstituteInput.getYear() != null) {
//            institute.setYear(updateInstituteInput.getYear());
//        }
//
//        instituteRepository.save(institute);
//
//        return new UpdateInstitutePayload(institute);
//    }

//    public RemoveInstitutePayload removeInstitute(RemoveInstituteInput removeInstituteInput) {
//        Institute institute = instituteRepository.findById(removeInstituteInput.getInstituteId());
//        instituteRepository.delete(institute);
//
//        return new RemoveInstitutePayload(Lists.newArrayList(instituteRepository.findAll()));
//    }

    public AddCollegePayload addCollege(AddCollegeInput addCollegeInput) {
        final College college = new College();
        college.setShortName(addCollegeInput.getShortName());
        college.setLogo(addCollegeInput.getLogo());
        college.setBackgroundImage(addCollegeInput.getBackgroundImage());
        college.setInstructionInformation(addCollegeInput.getInstructionInformation());

        collegeRepository.save(college);

        return new AddCollegePayload(college);
    }

    public UpdateCollegePayload updateCollege(UpdateCollegeInput updateCollegeInput) {
        College college = collegeRepository.findById(updateCollegeInput.getId()).get();
        if (updateCollegeInput.getShortName() != null) {
            college.setShortName(updateCollegeInput.getShortName());
        }

        if (updateCollegeInput.getLogo() != null) {
            college.setLogo(updateCollegeInput.getLogo());
        }

        if (updateCollegeInput.getBackgroundImage() != null) {
            college.setBackgroundImage(updateCollegeInput.getBackgroundImage());
        }

        if (updateCollegeInput.getInstructionInformation() != null) {
            college.setInstructionInformation(updateCollegeInput.getInstructionInformation());
        }

        collegeRepository.save(college);

        return new UpdateCollegePayload(college);
    }

    public RemoveCollegePayload removeCollege(RemoveCollegeInput removeCollegeInput) {
        College college = collegeRepository.getOne(removeCollegeInput.getCollegeId());
        collegeRepository.delete(college);

        return new RemoveCollegePayload(Lists.newArrayList(collegeRepository.findAll()));
    }

    public AddBranchPayload addBranch(AddBranchInput addBranchInput) {
    	College college = collegeRepository.findById(addBranchInput.getCollegeId()).get();
        final Branch branch = new Branch();
        branch.setBranchName(addBranchInput.getBranchName());
        branch.setDescription(addBranchInput.getDescription());
        branch.setCollegeHead(addBranchInput.getCollegeHead());
        branch.setCollege(college);
        branchRepository.save(branch);

        return new AddBranchPayload(branch);
    }

    public UpdateBranchPayload updateBranch(UpdateBranchInput updateBranchInput) {
        Branch branch = branchRepository.findById(updateBranchInput.getId()).get();
        if (updateBranchInput.getBranchName() != null) {
            branch.setBranchName(updateBranchInput.getBranchName());
        }

        if (updateBranchInput.getDescription() != null) {
            branch.setDescription(updateBranchInput.getDescription());
        }

        if (updateBranchInput.getCollegeHead() != null) {
            branch.setCollegeHead(updateBranchInput.getCollegeHead());
        }
        if(updateBranchInput.getCollegeId() != null) {
        	College college = collegeRepository.findById(updateBranchInput.getCollegeId()).get();
        	branch.setCollege(college);
        }
        branchRepository.save(branch);
        return new UpdateBranchPayload(branch);
    }

    public RemoveBranchPayload removeBranch(RemoveBranchInput removeBranchsInput) {
        Branch branch = branchRepository.findById(removeBranchsInput.getBranchId()).get();
        branchRepository.delete(branch);
        return new RemoveBranchPayload(Lists.newArrayList(branchRepository.findAll()));
    }

    public AddBatchPayload addBatch(AddBatchInput addBatchInput) {
    	Department department = departmentRepository.findById(addBatchInput.getDepartmentId()).get();
        final Batch batch = new Batch();
        batch.setBatch(addBatchInput.getBatch());
        batch.setDepartment(department);
        batchRepository.save(batch);
        return new AddBatchPayload(batch);
    }

    public UpdateBatchPayload updateBatch(UpdateBatchInput updateBatchInput) {
    	Batch batch = batchRepository.findById(updateBatchInput.getId()).get();
        if (updateBatchInput.getBatch() != null) {
        	batch.setBatch(updateBatchInput.getBatch());
        }
        if(updateBatchInput.getDepartmentId() != null) {
        	Department department = departmentRepository.findById(updateBatchInput.getDepartmentId()).get();
        	batch.setDepartment(department);
        }
        batchRepository.save(batch);
        return new UpdateBatchPayload(batch);
    }

    public RemoveBatchPayload removeBatch(RemoveBatchInput removeBatchInput) {
    	Batch batch = batchRepository.findById(removeBatchInput.getBatchId()).get();
    	batchRepository.delete(batch);
        return new RemoveBatchPayload(Lists.newArrayList(batchRepository.findAll()));
    }

    public AddSectionPayload addSection(AddSectionInput addSectionInput) {
        final Batch batch = batchRepository.findById(addSectionInput.getBatchId()).get();
        final Section section = new Section();
        section.setSection(addSectionInput.getSection());
        section.setBatch(batch);
        sectionRepository.save(section);
        return new AddSectionPayload(section);
    }

    public UpdateSectionPayload updateSection(UpdateSectionInput updateSectionInput) {
        Section section = sectionRepository.findById(updateSectionInput.getId()).get();
        if (updateSectionInput.getSection() != null) {
            section.setSection(updateSectionInput.getSection());
        }
        if(updateSectionInput.getBatchId() != null) {
        	final Batch batch = batchRepository.findById(updateSectionInput.getBatchId()).get();
        	section.setBatch(batch);
        }
        sectionRepository.save(section);

        return new UpdateSectionPayload(section);
    }

    public RemoveSectionPayload removeSection(RemoveSectionInput removeSectionInput) {
        Section section = sectionRepository.findById(removeSectionInput.getSectionId()).get();
        sectionRepository.delete(section);
        return new RemoveSectionPayload(Lists.newArrayList(sectionRepository.findAll()));
    }

    public AddSubjectPayload addSubject(AddSubjectInput addSubjectInput) {
    	final Department department = departmentRepository.findById(addSubjectInput.getDepartmentId()).get();
    	final Teacher teacher = teacherRepository.findById(addSubjectInput.getTeacherId()).get();

    	final Subject subject = new Subject();
        subject.setDepartment(department);
        subject.setTeacher(teacher);
        subject.setSubjectType(addSubjectInput.getSubjectType());
        subject.setSubjectCode(addSubjectInput.getSubjectCode());
        subject.setSubjectDesc(addSubjectInput.getSubjectDesc());
        subjectRepository.save(subject);
        return new AddSubjectPayload(subject);
    }

    public UpdateSubjectPayload updateSubject(UpdateSubjectInput updateSubjectInput) {
        Subject subject = subjectRepository.findById(updateSubjectInput.getId()).get();
        if (updateSubjectInput.getSubjectCode() != null) {
            subject.setSubjectCode(updateSubjectInput.getSubjectCode());
        }
        if (updateSubjectInput.getSubjectType() != null) {
            subject.setSubjectType(updateSubjectInput.getSubjectType());
        }
        if (updateSubjectInput.getSubjectDesc() != null) {
            subject.setSubjectDesc(updateSubjectInput.getSubjectDesc());
        }
        if (updateSubjectInput.getDepartmentId() != null) {
        	final Department department = departmentRepository.findById(updateSubjectInput.getDepartmentId()).get();
            subject.setDepartment(department);
        }
        if(updateSubjectInput.getTeacherId() != null) {
        	final Teacher teacher = teacherRepository.findById(updateSubjectInput.getTeacherId()).get();
        	subject.setTeacher(teacher);
        }
        subjectRepository.save(subject);

        return new UpdateSubjectPayload(subject);
    }

    public RemoveSubjectPayload removeSubject(RemoveSubjectInput removeSubjectInput) {
        Subject subject = subjectRepository.findById(removeSubjectInput.getSubjectId()).get();
        subjectRepository.delete(subject);
        return new RemoveSubjectPayload(Lists.newArrayList(subjectRepository.findAll()));
    }

    public AddStudentAttendancePayload addStudentAttendance(AddStudentAttendanceInput addStudentAttendanceInput) {
        final Student student = studentRepository.findById(addStudentAttendanceInput.getStudentId()).get();
        final Lecture lecture = lectureRepository.findById(addStudentAttendanceInput.getLectureId()).get();
        final StudentAttendance studentAttendance = new StudentAttendance();
        studentAttendance.setAttendanceStatus(addStudentAttendanceInput.getAttendanceStatus());
        studentAttendance.setComments(addStudentAttendanceInput.getComments());
        studentAttendance.setStudent(student);
        studentAttendance.setLecture(lecture);
        studentAttendance.setAttendanceDate(addStudentAttendanceInput.getAttendanceDate());
        studentAttendanceRepository.save(studentAttendance);
        return new AddStudentAttendancePayload(studentAttendance);
    }

    public UpdateStudentAttendancePayload updateStudentAttendance(UpdateStudentAttendanceInput updateStudentAttendanceInput) {
        StudentAttendance studentAttendance = studentAttendanceRepository.findById(updateStudentAttendanceInput.getId()).get();


        if (updateStudentAttendanceInput.getAttendanceStatus() != null) {
            studentAttendance.setAttendanceStatus(updateStudentAttendanceInput.getAttendanceStatus());
        }
        if (updateStudentAttendanceInput.getComments() != null) {
            studentAttendance.setComments(updateStudentAttendanceInput.getComments());
        }
        if (updateStudentAttendanceInput.getAttendanceDate() != null) {
            studentAttendance.setAttendanceDate(updateStudentAttendanceInput.getAttendanceDate());
        }
        if(updateStudentAttendanceInput.getStudentId() != null) {
        	final Student student = studentRepository.findById(updateStudentAttendanceInput.getStudentId()).get();
        	studentAttendance.setStudent(student);
        }
        if(updateStudentAttendanceInput.getLectureId() != null) {
        	final Lecture lecture = lectureRepository.findById(updateStudentAttendanceInput.getLectureId()).get();
        	studentAttendance.setLecture(lecture);
        }
        studentAttendanceRepository.save(studentAttendance);

        return new UpdateStudentAttendancePayload(studentAttendance);
    }

    public RemoveStudentAttendancePayload removeStudentAttendance(RemoveStudentAttendanceInput removeStudentAttendanceInput) {
        StudentAttendance studentAttendance = studentAttendanceRepository.findById(removeStudentAttendanceInput.getStudentAttendanceId()).get();
        studentAttendanceRepository.delete(studentAttendance);
        return new RemoveStudentAttendancePayload(Lists.newArrayList(studentAttendanceRepository.findAll()));
    }


    public AddTeacherPayload addTeacher(AddTeacherInput addTeacherInput) {
        final Teacher teacher = new Teacher();
        teacher.setTeacherName(addTeacherInput.getTeacherName());

        teacherRepository.save(teacher);

        return new AddTeacherPayload(teacher);
    }

    public UpdateTeacherPayload updateTeacher(UpdateTeacherInput updateTeacherInput) {
        Teacher teacher = teacherRepository.findById(updateTeacherInput.getId()).get();
        if (updateTeacherInput.getTeacherName() != null) {
            teacher.setTeacherName(updateTeacherInput.getTeacherName());
        }
        teacherRepository.save(teacher);

        return new UpdateTeacherPayload(teacher);
    }

    public RemoveTeacherPayload removeTeacher(RemoveTeacherInput removeTeacherInput) {
        Teacher teacher = teacherRepository.findById(removeTeacherInput.getTeacherId()).get();
        teacherRepository.delete(teacher);
        return new RemoveTeacherPayload(Lists.newArrayList(teacherRepository.findAll()));
    }

    public AddAuthorizedSignatoryPayload addAuthorizedSignatory(AddAuthorizedSignatoryInput addAuthorizedSignatoryInput) {
        final LegalEntity legalEntity = legalEntityRepository.findById(addAuthorizedSignatoryInput.getLegalEntityId()).get();
        final AuthorizedSignatory authorizedSignatory = new AuthorizedSignatory();
        authorizedSignatory.setSignatoryName(addAuthorizedSignatoryInput.getSignatoryName());
        authorizedSignatory.setSignatoryFatherName(addAuthorizedSignatoryInput.getSignatoryFatherName());
        authorizedSignatory.setSignatoryDesignation(addAuthorizedSignatoryInput.getSignatoryDesignation());
        authorizedSignatory.setAddress(addAuthorizedSignatoryInput.getAddress());
        authorizedSignatory.setEmail(addAuthorizedSignatoryInput.getEmail());
        authorizedSignatory.setPanCardNumber(addAuthorizedSignatoryInput.getPanCardNumber());
        authorizedSignatory.setLegalEntity(legalEntity);
        authorizedSignatoryRepository.save(authorizedSignatory);

        return new AddAuthorizedSignatoryPayload(authorizedSignatory);
    }

    public UpdateAuthorizedSignatoryPayload updateAuthorizedSignatory(UpdateAuthorizedSignatoryInput updateAuthorizedSignatoryInput) {
        AuthorizedSignatory authorizedSignatory = authorizedSignatoryRepository.findById(updateAuthorizedSignatoryInput.getId()).get();
        if (updateAuthorizedSignatoryInput.getSignatoryName() != null) {
            authorizedSignatory.setSignatoryName(updateAuthorizedSignatoryInput.getSignatoryName());
        }
        if (updateAuthorizedSignatoryInput.getSignatoryFatherName() != null) {
            authorizedSignatory.setSignatoryFatherName(updateAuthorizedSignatoryInput.getSignatoryFatherName());
        }
        if (updateAuthorizedSignatoryInput.getSignatoryDesignation() != null) {
            authorizedSignatory.setSignatoryDesignation(updateAuthorizedSignatoryInput.getSignatoryDesignation());
        }

        if (updateAuthorizedSignatoryInput.getAddress() != null) {
            authorizedSignatory.setAddress(updateAuthorizedSignatoryInput.getAddress());
        }

        if (updateAuthorizedSignatoryInput.getEmail() != null) {
            authorizedSignatory.setEmail(updateAuthorizedSignatoryInput.getEmail());
        }

        if (updateAuthorizedSignatoryInput.getPanCardNumber() != null) {
            authorizedSignatory.setPanCardNumber(updateAuthorizedSignatoryInput.getPanCardNumber());
        }
        if(updateAuthorizedSignatoryInput.getLegalEntityId() != null) {
        	LegalEntity legalEntity = legalEntityRepository.findById(updateAuthorizedSignatoryInput.getLegalEntityId()).get();
        	authorizedSignatory.setLegalEntity(legalEntity);
        }
        authorizedSignatoryRepository.save(authorizedSignatory);

        return new UpdateAuthorizedSignatoryPayload(authorizedSignatory);
    }

    public RemoveAuthorizedSignatoryPayload removeAuthorizedSignatory(RemoveAuthorizedSignatoryInput removeAuthorizedSignatoryInput) {
        AuthorizedSignatory authorizedSignatory = authorizedSignatoryRepository.findById(removeAuthorizedSignatoryInput.getAuthorizedSignatoryId()).get();
        authorizedSignatoryRepository.delete(authorizedSignatory);
        return new RemoveAuthorizedSignatoryPayload(Lists.newArrayList(authorizedSignatoryRepository.findAll()));
    }

    public AddLegalEntityPayload addLegalEntity(AddLegalEntityInput addLegalEntityInput) {
        final LegalEntity legalEntity = new LegalEntity();
        legalEntity.setLogo(addLegalEntityInput.getLogo());
        legalEntity.setLegalNameOfTheCollege(addLegalEntityInput.getLegalNameOfTheCollege());
        legalEntity.setTypeOfCollege(addLegalEntityInput.getTypeOfCollege());
        legalEntity.setDateOfIncorporation(addLegalEntityInput.getDateOfIncorporation());
        legalEntity.setRegisteredOfficeAddress(addLegalEntityInput.getRegisteredOfficeAddress());
        legalEntity.setCollegeIdentificationNumber(addLegalEntityInput.getCollegeIdentificationNumber());
        legalEntity.setPan(addLegalEntityInput.getPan());
        legalEntity.setTan(addLegalEntityInput.getTan());
        legalEntity.setTanCircleNumber(addLegalEntityInput.getTanCircleNumber());
        legalEntity.setCitTdsLocation(addLegalEntityInput.getCitTdsLocation());
        legalEntity.setFormSignatory(addLegalEntityInput.getFormSignatory());
        legalEntity.setPfNumber(addLegalEntityInput.getPfNumber());
        legalEntity.setRegistrationDate(addLegalEntityInput.getRegistrationDate());
        legalEntity.setEsiNumber(addLegalEntityInput.getEsiNumber());
        legalEntity.setPtRegistrationDate(addLegalEntityInput.getPtRegistrationDate());
        legalEntity.setPtSignatory(addLegalEntityInput.getPtSignatory());
        legalEntity.setPtNumber(addLegalEntityInput.getPtNumber());

        legalEntityRepository.save(legalEntity);

        return new AddLegalEntityPayload(legalEntity);
    }

    public UpdateLegalEntityPayload updateLegalEntity(UpdateLegalEntityInput updateLegalEntityInput) {
        LegalEntity legalEntity = legalEntityRepository.findById(updateLegalEntityInput.getId()).get();
        if (updateLegalEntityInput.getLogo() != null) {
            legalEntity.setLogo(updateLegalEntityInput.getLogo());
        }
        if (updateLegalEntityInput.getLegalNameOfTheCollege() != null) {
            legalEntity.setLegalNameOfTheCollege(updateLegalEntityInput.getLegalNameOfTheCollege());
        }
        if (updateLegalEntityInput.getTypeOfCollege() != null) {
            legalEntity.setTypeOfCollege(updateLegalEntityInput.getTypeOfCollege());
        }
        if (updateLegalEntityInput.getDateOfIncorporation() != null) {
            legalEntity.setDateOfIncorporation(updateLegalEntityInput.getDateOfIncorporation());
        }
        if (updateLegalEntityInput.getRegisteredOfficeAddress() != null) {
            legalEntity.setRegisteredOfficeAddress(updateLegalEntityInput.getRegisteredOfficeAddress());
        }
        if (updateLegalEntityInput.getCollegeIdentificationNumber() != null) {
            legalEntity.setCollegeIdentificationNumber(updateLegalEntityInput.getCollegeIdentificationNumber());
        }
        if (updateLegalEntityInput.getPan() != null) {
            legalEntity.setPan(updateLegalEntityInput.getPan());
        }
        if (updateLegalEntityInput.getTan() != null) {
            legalEntity.setTan(updateLegalEntityInput.getTan());
        }
        if (updateLegalEntityInput.getTanCircleNumber() != null) {
            legalEntity.setTanCircleNumber(updateLegalEntityInput.getTanCircleNumber());
        }
        if (updateLegalEntityInput.getCitTdsLocation() != null) {
            legalEntity.setCitTdsLocation(updateLegalEntityInput.getCitTdsLocation());
        }
        if (updateLegalEntityInput.getFormSignatory() != null) {
            legalEntity.setFormSignatory(updateLegalEntityInput.getFormSignatory());
        }
        if (updateLegalEntityInput.getPfNumber() != null) {
            legalEntity.setPfNumber(updateLegalEntityInput.getPfNumber());
        }
        if (updateLegalEntityInput.getRegistrationDate() != null) {
            legalEntity.setRegistrationDate(updateLegalEntityInput.getRegistrationDate());
        }
        if (updateLegalEntityInput.getEsiNumber() != null) {
            legalEntity.setEsiNumber(updateLegalEntityInput.getEsiNumber());
        }
        if (updateLegalEntityInput.getPtRegistrationDate() != null) {
            legalEntity.setPtRegistrationDate(updateLegalEntityInput.getPtRegistrationDate());
        }
        if (updateLegalEntityInput.getPtSignatory() != null) {
            legalEntity.setPtSignatory(updateLegalEntityInput.getPtSignatory());
        }
        if (updateLegalEntityInput.getPtNumber() != null) {
            legalEntity.setPtNumber(updateLegalEntityInput.getPtNumber());
        }

        legalEntityRepository.save(legalEntity);

        return new UpdateLegalEntityPayload(legalEntity);
    }

    public RemoveLegalEntityPayload removeLegalEntity(RemoveLegalEntityInput removeLegalEntityInput) {
        LegalEntity legalEntity = legalEntityRepository.findById(removeLegalEntityInput.getLegalEntityId()).get();
        legalEntityRepository.delete(legalEntity);
        return new RemoveLegalEntityPayload(Lists.newArrayList(legalEntityRepository.findAll()));
    }

    public AddAcademicYearPayload addAcademicYear(AddAcademicYearInput addAcademicYearInput) {
        final AcademicYear academicYear = new AcademicYear();
        academicYear.setYear(addAcademicYearInput.getYear());
        academicYear.setStartDate(addAcademicYearInput.getStartDate());
        academicYear.setEndDate(addAcademicYearInput.getEndDate());
        academicYear.setDesc(addAcademicYearInput.getDesc());
        academicYearRepository.save(academicYear);
        return new AddAcademicYearPayload(academicYear);
    }


    public UpdateAcademicYearPayload updateAcademicYear(UpdateAcademicYearInput updateAcademicYearInput) {
        AcademicYear academicYear = academicYearRepository.findById(updateAcademicYearInput.getId()).get();
        if (updateAcademicYearInput.getYear() != null) {
            academicYear.setYear(updateAcademicYearInput.getYear());
        }
        if (updateAcademicYearInput.getStartDate() != null) {
            academicYear.setStartDate(updateAcademicYearInput.getStartDate());
        }

        if (updateAcademicYearInput.getEndDate() != null) {
            academicYear.setEndDate(updateAcademicYearInput.getEndDate());
        }

        if (updateAcademicYearInput.getDesc() != null) {
            academicYear.setDesc(updateAcademicYearInput.getDesc());
        }

        academicYearRepository.save(academicYear);

        return new UpdateAcademicYearPayload(academicYear);
    }

    public RemoveAcademicYearPayload removeAcademicYear(RemoveAcademicYearInput removeAcademicYearInput) {
        AcademicYear academicYear = academicYearRepository.findById(removeAcademicYearInput.getAcademicYearId()).get();
        academicYearRepository.delete(academicYear);
        return new RemoveAcademicYearPayload(Lists.newArrayList(academicYearRepository.findAll()));
    }

    public AddHolidayPayload addHoliday(AddHolidayInput addHolidayInput) {
        final AcademicYear academicYear = academicYearRepository.findById(addHolidayInput.getAcademicYearId()).get();
        final Holiday holiday = new Holiday();
        holiday.setHolidayDesc(addHolidayInput.getHolidayDesc());
        holiday.setHolidayDate(addHolidayInput.getHolidayDate());
        holiday.setHolidayStatus(addHolidayInput.getHolidayStatus());
        holiday.setAcademicyear(academicYear);
        holidayRepository.save(holiday);

        return new AddHolidayPayload(holiday);
    }

    public UpdateHolidayPayload updateHoliday(UpdateHolidayInput updateHolidayInput) {
        Holiday holiday = holidayRepository.findById(updateHolidayInput.getId()).get();
        if (updateHolidayInput.getHolidayDesc() != null) {
            holiday.setHolidayDesc(updateHolidayInput.getHolidayDesc());
        }
        if (updateHolidayInput.getHolidayDate() != null) {
            holiday.setHolidayDate(updateHolidayInput.getHolidayDate());
        }

        if (updateHolidayInput.getHolidayStatus() != null) {
            holiday.setHolidayStatus(updateHolidayInput.getHolidayStatus());
        }
        if(updateHolidayInput.getAcademicYearId() != null) {
        	final AcademicYear academicYear = academicYearRepository.findById(updateHolidayInput.getAcademicYearId()).get();
        	holiday.setAcademicyear(academicYear);
        }
        holidayRepository.save(holiday);

        return new UpdateHolidayPayload(holiday);
    }

    public RemoveHolidayPayload removeHoliday(RemoveHolidayInput removeHolidayInput) {
        Holiday holiday = holidayRepository.findById(removeHolidayInput.getHolidayId()).get();
        holidayRepository.delete(holiday);
        return new RemoveHolidayPayload(Lists.newArrayList(holidayRepository.findAll()));
    }

    public AddTermPayload addTerm(AddTermInput addTermInput) {
        final AcademicYear academicYear = academicYearRepository.findById(addTermInput.getAcademicYearId()).get();
        final Term term = new Term();
        term.setTermsDesc(addTermInput.getTermsDesc());
        term.setStartDate(addTermInput.getStartDate());
        term.setEndDate(addTermInput.getEndDate());
        term.setTermStatus(addTermInput.getTermStatus());
        term.setAcademicyear(academicYear);
        termRepository.save(term);

        return new AddTermPayload(term);
    }

    public UpdateTermPayload updateTerm(UpdateTermInput updateTermInput) {
        Term term = termRepository.findById(updateTermInput.getId()).get();

        if (updateTermInput.getTermsDesc() != null) {
            term.setTermsDesc(updateTermInput.getTermsDesc());
        }
        if (updateTermInput.getStartDate() != null) {
            term.setStartDate(updateTermInput.getStartDate());
        }

        if (updateTermInput.getEndDate() != null) {
            term.setEndDate(updateTermInput.getEndDate());
        }

        if (updateTermInput.getTermStatus() != null){
            term.setTermStatus(updateTermInput.getTermStatus());
        }
        if(updateTermInput.getAcademicYearId() != null) {
        	final AcademicYear academicYear = academicYearRepository.findById(updateTermInput.getAcademicYearId()).get();
        	term.setAcademicyear(academicYear);
        }
        termRepository.save(term);

        return new UpdateTermPayload(term);
    }

    public RemoveTermPayload removeTerm(RemoveTermInput removeTermInput) {
        Term term = termRepository.findById(removeTermInput.getTermId()).get();
        termRepository.delete(term);
        return new RemoveTermPayload(Lists.newArrayList(termRepository.findAll()));
    }

    public AddTeachPayload addTeach(AddTeachInput addTeachInput) {
        final Teacher teacher = teacherRepository.findById(addTeachInput.getTeacherId()).get();
        final Subject subject = subjectRepository.findById(addTeachInput.getSubjectId()).get();
        final Teach teach = new Teach();
        teach.setDesc(addTeachInput.getDesc());
        teach.setTeacher(teacher);
        teach.setSubject(subject);
        teachRepository.save(teach);
        return new AddTeachPayload(teach);
    }

    public UpdateTeachPayload updateTeach(UpdateTeachInput updateTeachInput) {
        Teach teach = teachRepository.findById(updateTeachInput.getId()).get();

        if (updateTeachInput.getDesc() != null) {
        	teach.setDesc(updateTeachInput.getDesc());
        }
        if (updateTeachInput.getTeacherId() != null) {
        	final Teacher teacher = teacherRepository.findById(updateTeachInput.getTeacherId()).get();
        	teach.setTeacher(teacher);
        }
        if (updateTeachInput.getSubjectId() != null) {
        	final Subject subject = subjectRepository.findById(updateTeachInput.getSubjectId()).get();
        	teach.setSubject(subject);
        }
        teachRepository.save(teach);
        return new UpdateTeachPayload(teach);
    }

    public RemoveTeachPayload removeTeach(RemoveTeachInput removeTeachInput) {
        Teach teach = teachRepository.findById(removeTeachInput.getTeachId()).get();
        teachRepository.delete(teach);
        return new RemoveTeachPayload(Lists.newArrayList(teachRepository.findAll()));
    }

    public AddCourseOfferPayload addCourseOffer(AddCourseOfferInput addCourseOfferInput) {
        final College college = collegeRepository.findById(addCourseOfferInput.getCollegeId()).get();
        final Department department = departmentRepository.findById(addCourseOfferInput.getDepartmentId()).get();
        final Subject subject = subjectRepository.findById(addCourseOfferInput.getSubjectId()).get();
        final CourseOffer courseOffer = new CourseOffer();
        courseOffer.setDesc(addCourseOfferInput.getDesc());
        courseOffer.setCollege(college);
        courseOffer.setDepartment(department);
        courseOffer.setSubject(subject);
        courseOfferRepository.save(courseOffer);
        return new AddCourseOfferPayload(courseOffer);
    }

    public UpdateCourseOfferPayload updateCourseOffer(UpdateCourseOfferInput updateCourseOfferInput) {
    	CourseOffer courseOffer = courseOfferRepository.findById(updateCourseOfferInput.getId()).get();

        if (updateCourseOfferInput.getDesc() != null) {
        	courseOffer.setDesc(updateCourseOfferInput.getDesc());
        }
        if (updateCourseOfferInput.getCollegeId() != null) {
        	final College college = collegeRepository.findById(updateCourseOfferInput.getCollegeId()).get();
        	courseOffer.setCollege(college);
        }
        if (updateCourseOfferInput.getDepartmentId() != null) {
        	final Department department = departmentRepository.findById(updateCourseOfferInput.getDepartmentId()).get();
        	courseOffer.setDepartment(department);
        }
        if (updateCourseOfferInput.getSubjectId() != null) {
        	final Subject subject = subjectRepository.findById(updateCourseOfferInput.getSubjectId()).get();
        	courseOffer.setSubject(subject);
        }
        courseOfferRepository.save(courseOffer);
        return new UpdateCourseOfferPayload(courseOffer);
    }

    public RemoveCourseOfferPayload removeCourseOffer(RemoveCourseOfferInput removeCourseOfferInput) {
    	CourseOffer courseOffer = courseOfferRepository.findById(removeCourseOfferInput.getCourseOfferId()).get();
    	courseOfferRepository.delete(courseOffer);
        return new RemoveCourseOfferPayload(Lists.newArrayList(courseOfferRepository.findAll()));
    }

    public AddAttendanceMasterPayload addAttendanceMaster(AddAttendanceMasterInput addAttendanceMasterInput) {
        final Teach teach = teachRepository.findById(addAttendanceMasterInput.getTeachId()).get();
        final Section section = sectionRepository.findById(addAttendanceMasterInput.getSectionId()).get();
        final AcademicYear academicYear = academicYearRepository.findById(addAttendanceMasterInput.getAcademicYearId()).get();
        final AttendanceMaster attendanceMaster = new AttendanceMaster();

        attendanceMaster.setDesc(addAttendanceMasterInput.getDesc());
        attendanceMaster.setTeach(teach);
        attendanceMaster.setSection(section);
        attendanceMaster.setAcademicyear(academicYear);
        attendanceMasterRepository.save(attendanceMaster);
        return new AddAttendanceMasterPayload(attendanceMaster);
    }

    public UpdateAttendanceMasterPayload updateAttendanceMaster(UpdateAttendanceMasterInput updateAttendanceMasterInput) {
    	AttendanceMaster attendanceMaster = attendanceMasterRepository.findById(updateAttendanceMasterInput.getId()).get();

        if (updateAttendanceMasterInput.getDesc() != null) {
        	attendanceMaster.setDesc(updateAttendanceMasterInput.getDesc());
        }
        if (updateAttendanceMasterInput.getTeachId() != null) {
        	Teach teach = teachRepository.findById(updateAttendanceMasterInput.getTeachId()).get();
        	attendanceMaster.setTeach(teach);
        }
        if (updateAttendanceMasterInput.getSectionId() != null) {
        	Section section = sectionRepository.findById(updateAttendanceMasterInput.getSectionId()).get();
        	attendanceMaster.setSection(section);
        }
        if (updateAttendanceMasterInput.getAcademicYearId() != null) {
        	AcademicYear academicYear = academicYearRepository.findById(updateAttendanceMasterInput.getAcademicYearId()).get();
        	attendanceMaster.setAcademicyear(academicYear);
        }
        attendanceMasterRepository.save(attendanceMaster);
        return new UpdateAttendanceMasterPayload(attendanceMaster);
    }

    public RemoveAttendanceMasterPayload removeAttendanceMaster(RemoveAttendanceMasterInput removeAttendanceMasterInput) {
    	AttendanceMaster attendanceMaster = attendanceMasterRepository.findById(removeAttendanceMasterInput.getAttendanceMasterId()).get();
    	attendanceMasterRepository.delete(attendanceMaster);
        return new RemoveAttendanceMasterPayload(Lists.newArrayList(attendanceMasterRepository.findAll()));
    }


    public AddLecturePayload addLecture(AddLectureInput addLectureInput) {
        final AttendanceMaster attendanceMaster = attendanceMasterRepository.findById(addLectureInput.getAttendanceMasterId()).get();
        final Lecture lecture = new Lecture();
        lecture.setLecDate(addLectureInput.getLecDate());
        lecture.setLastUpdatedOn(addLectureInput.getLastUpdatedOn());
        lecture.setLastUpdatedBy(addLectureInput.getLastUpdatedBy());
        lecture.setLecStatus(addLectureInput.getLecStatus());
        lecture.setDesc(addLectureInput.getDesc());
        lecture.setAttendancemaster(attendanceMaster);
        lectureRepository.save(lecture);
        return new AddLecturePayload(lecture);
    }

    public UpdateLecturePayload updateLecture(UpdateLectureInput updateLectureInput) {
    	Lecture lecture = lectureRepository.findById(updateLectureInput.getId()).get();

    	if(updateLectureInput.getLecDate() != null) {
    		lecture.setLecDate(updateLectureInput.getLecDate());
    	}
    	if(updateLectureInput.getLastUpdatedOn() != null) {
    		lecture.setLastUpdatedOn(updateLectureInput.getLastUpdatedOn());
    	}
    	if(updateLectureInput.getLastUpdatedBy() != null) {
    		lecture.setLastUpdatedBy(updateLectureInput.getLastUpdatedBy());
    	}
    	if(updateLectureInput.getLecStatus() != null) {
    		lecture.setLecStatus(updateLectureInput.getLecStatus());
    	}
        if (updateLectureInput.getDesc() != null) {
        	lecture.setDesc(updateLectureInput.getDesc());
        }
        if (updateLectureInput.getAttendanceMasterId() != null) {
        	final AttendanceMaster attendanceMaster = attendanceMasterRepository.findById(updateLectureInput.getAttendanceMasterId()).get();
        	lecture.setAttendancemaster(attendanceMaster);
        }
        lectureRepository.save(lecture);
        return new UpdateLecturePayload(lecture);
    }

    public RemoveLecturePayload removeLecture(RemoveLectureInput removeLectureInput) {
    	Lecture lecture = lectureRepository.findById(removeLectureInput.getLectureId()).get();
    	lectureRepository.delete(lecture);
        return new RemoveLecturePayload(Lists.newArrayList(lectureRepository.findAll()));
    }
}



