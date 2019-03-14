/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.synectiks.cms.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import com.synectiks.cms.domain.*;
import com.synectiks.cms.filter.academicsubject.AcademicSubjectProcessor;
import com.synectiks.cms.filter.academicsubject.AcademicSubjectQueryPayload;
import com.synectiks.cms.filter.invoice.InvoiceFilterProcessor;
import com.synectiks.cms.filter.studentattendance.DailyAttendanceVo;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceFilterImpl;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceFilterInput;
import com.synectiks.cms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Query Resolver for CMS Queries
 *
 */
@Component
public class Query implements GraphQLQueryResolver {

    private final AcademicYearRepository academicYearRepository;
    private final AttendanceMasterRepository attendanceMasterRepository;
    private final AuthorizedSignatoryRepository authorizedSignatoryRepository;
    private final AcademicHistoryRepository academicHistoryRepository;
    private final AdmissionApplicationRepository admissionApplicationRepository;
    private final AdmissionEnquiryRepository admissionEnquiryRepository;
    private final BankAccountsRepository bankAccountsRepository;
    private final BatchRepository batchRepository;
    private final BranchRepository branchRepository;
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;
    private final HolidayRepository holidayRepository;
    private final LectureRepository lectureRepository;
    //  private final InstituteRepository instituteRepository;
    private final LegalEntityRepository legalEntityRepository;
    private final SectionRepository sectionRepository;
    //    private final SemesterRepository semesterRepository;
    private final StudentRepository studentRepository;
    private final StudentAttendanceRepository studentAttendanceRepository;
    //    private final StudentYearRepository studentYearRepository;
    private final SubjectRepository subjectRepository;
    private final TeachRepository teachRepository;
    private final TeacherRepository teacherRepository;
    private final TermRepository termRepository;
    private final CityRepository cityRepository;
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;
    private final FeeCategoryRepository feeCategoryRepository;
    private final FacilityRepository facilityRepository;
    private final TransportRouteRepository transportRouteRepository;
    private final FeeDetailsRepository feeDetailsRepository;
    private final DueDateRepository dueDateRepository;
    private final LateFeeRepository lateFeeRepository;
    private final PaymentRemainderRepository paymentRemainderRepository;
    private final InvoiceRepository invoiceRepository;
    private final CompetitiveExamRepository competitiveExamRepository;
    private final DocumentsRepository documentsRepository;


    @Autowired
    private StudentAttendanceFilterImpl studentAttendanceFilterImpl;

    @Autowired
    private AcademicSubjectProcessor academicSubjectProcessor;

    @Autowired
    LegalEntitySelectRepository legalEntitySelectRepository;
    
    @Autowired
    private InvoiceFilterProcessor invoiceFilterProcessor;

    public Query(AcademicHistoryRepository academicHistoryRepository, AdmissionEnquiryRepository admissionEnquiryRepository, LectureRepository lectureRepository, AttendanceMasterRepository attendanceMasterRepository, TeachRepository teachRepository, BatchRepository batchRepository, StudentRepository studentRepository, CollegeRepository collegeRepository, BranchRepository branchRepository, SectionRepository sectionRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, LegalEntityRepository legalEntityRepository, AuthorizedSignatoryRepository authorizedSignatoryRepository, BankAccountsRepository bankAccountsRepository, DepartmentRepository departmentRepository, StudentAttendanceRepository studentAttendanceRepository, AcademicYearRepository academicYearRepository, AdmissionApplicationRepository admissionApplicationRepository, HolidayRepository holidayRepository, TermRepository termRepository, CityRepository cityRepository, StateRepository stateRepository, CountryRepository countryRepository, FeeCategoryRepository feeCategoryRepository, FacilityRepository facilityRepository, TransportRouteRepository transportRouteRepository, FeeDetailsRepository feeDetailsRepository, DueDateRepository dueDateRepository, LateFeeRepository lateFeeRepository, PaymentRemainderRepository paymentRemainderRepository, InvoiceRepository invoiceRepository, CompetitiveExamRepository competitiveExamRepository, DocumentsRepository documentsRepository) {
        this.academicHistoryRepository = academicHistoryRepository;
        this.admissionEnquiryRepository = admissionEnquiryRepository;
        this.batchRepository = batchRepository;
        this.studentRepository = studentRepository;
//        this.instituteRepository=instituteRepository;
        this.collegeRepository = collegeRepository;
        this.branchRepository = branchRepository;
//        this.studentYearRepository = studentYearRepository;
//        this.semesterRepository = semesterRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.legalEntityRepository = legalEntityRepository;
        this.authorizedSignatoryRepository = authorizedSignatoryRepository;
        this.bankAccountsRepository = bankAccountsRepository;
        this.departmentRepository = departmentRepository;
        this.studentAttendanceRepository = studentAttendanceRepository;
        this.academicYearRepository = academicYearRepository;
        this.admissionApplicationRepository = admissionApplicationRepository;
        this.holidayRepository = holidayRepository;
        this.termRepository = termRepository;
        this.teachRepository = teachRepository;
        this.attendanceMasterRepository = attendanceMasterRepository;
        this.lectureRepository = lectureRepository;
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
        this.feeCategoryRepository = feeCategoryRepository;
        this.facilityRepository = facilityRepository;
        this.transportRouteRepository = transportRouteRepository;
        this.feeDetailsRepository = feeDetailsRepository;
        this.dueDateRepository = dueDateRepository;
        this.lateFeeRepository = lateFeeRepository;
        this.paymentRemainderRepository = paymentRemainderRepository;
        this.invoiceRepository = invoiceRepository;
        this.competitiveExamRepository = competitiveExamRepository;
        this.documentsRepository = documentsRepository;
    }

    public Student student(long id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> students() {
        return Lists.newArrayList(studentRepository.findAll());
    }


   /* public List<Institute> institutes() {
        return Lists.newArrayList(instituteRepository.findAll());
    }

    public Institute institute(int id) {
        return instituteRepository.findById(id);
    }*/

    public College college(long id) {
        return collegeRepository.findById(id).get();
    }

    public List<College> colleges() {
        return Lists.newArrayList(collegeRepository.findAll());
    }

    public Branch branch(long id) {
        return branchRepository.findById(id).get();
    }

    public List<Branch> branches() {
        return Lists.newArrayList(branchRepository.findAll());
    }

    public Batch batch(long id) {
        return batchRepository.findById(id).get();
    }

    public List<Batch> batches() {
        return Lists.newArrayList(batchRepository.findAll());
    }

    public CompetitiveExam competitiveExam(long id) {
        return competitiveExamRepository.findById(id).get();
    }

    public List<CompetitiveExam> competitiveExams() {
        return Lists.newArrayList(competitiveExamRepository.findAll());
    }
    public Documents document(long id) {
        return documentsRepository.findById(id).get();
    }

    public List<Documents> documents() {
        return Lists.newArrayList(documentsRepository.findAll());
    }

    public AdmissionApplication admissionApplication(long id) {
        return admissionApplicationRepository.findById(id).get();
    }

    public List<AdmissionApplication> admissionApplications() {
        return Lists.newArrayList(admissionApplicationRepository.findAll());
    }

    public AcademicHistory academicHistory(long id) {
        return academicHistoryRepository.findById(id).get();
    }

    public List<AcademicHistory> academicHistories() {
        return Lists.newArrayList(academicHistoryRepository.findAll());
    }

    /*public StudentYear studentYear(long id)
    {
        return studentYearRepository.findById(id).get();
    }

    public List<StudentYear> studentYears()
    {
        return Lists.newArrayList(studentYearRepository.findAll());
    }*/

   /* public Semester semester(long id)
    {
        return semesterRepository.findById(id).get();
    }

    public List<Semester> semesters()
    {
        return Lists.newArrayList(semesterRepository.findAll());
    }*/

    public Section section(long id) {
        return sectionRepository.findById(id).get();
    }

    public List<Section> sections() {
        return Lists.newArrayList(sectionRepository.findAll());
    }

    public Subject subject(long id) {
        return subjectRepository.findById(id).get();
    }

    public List<Subject> subjects() {
        return Lists.newArrayList(subjectRepository.findAll());
    }

    public Teacher teacher(long id) {
        return teacherRepository.findById(id).get();
    }

    public List<Teacher> teachers() {
        return Lists.newArrayList(teacherRepository.findAll());
    }

    public LegalEntityAuthSignatoryLink legalEntity(long id) {
        return legalEntitySelectRepository.findById(id).get();
    }

    public List<LegalEntityAuthSignatoryLink> legalEntities() {
        return Lists.newArrayList(legalEntitySelectRepository.findAll());
    }

    public AuthorizedSignatory authorizedSignatory(long id) {
        return authorizedSignatoryRepository.findById(id).get();
    }

    public List<AuthorizedSignatory> authorizedSignatories() {
        return Lists.newArrayList(authorizedSignatoryRepository.findAll());
    }

    public BankAccounts bankAccount(long id) {
        return bankAccountsRepository.findById(id).get();
    }

    public List<BankAccounts> bankAccounts() {
        return Lists.newArrayList(bankAccountsRepository.findAll());
    }

    public Department department(long id) {
        return departmentRepository.findById(id).get();
    }

    public List<Department> departments() {
        return Lists.newArrayList(departmentRepository.findAll());
    }


    public StudentAttendance studentAttendance(long id) {
        return studentAttendanceRepository.findById(id).get();
    }

    public List<StudentAttendance> studentAttendances() {
        return Lists.newArrayList(studentAttendanceRepository.findAll());
    }

    public AdmissionEnquiry admissionEnquiry(long id) {
        return admissionEnquiryRepository.findById(id).get();
    }

    public List<AdmissionEnquiry> admissionEnquiries() {
        return Lists.newArrayList(admissionEnquiryRepository.findAll());
    }


    public List<AcademicYear> academicYears() {
        return Lists.newArrayList(academicYearRepository.findAll());
    }

    public AcademicYear academicYear(long id) {
        return academicYearRepository.findById(id).get();
    }

    public List<Holiday> holidays() {
        return Lists.newArrayList(holidayRepository.findAll());
    }

    public Holiday holiday(long id) {
        return holidayRepository.findById(id).get();
    }

    public List<Term> terms() {
        return Lists.newArrayList(termRepository.findAll());
    }

    public Term term(long id) {
        return termRepository.findById(id).get();
    }

    public Teach teach(long id) {
        return teachRepository.findById(id).get();
    }

    public List<Teach> teaches() {
        return Lists.newArrayList(teachRepository.findAll());
    }

    public AttendanceMaster attendanceMaster(long id) {
        return attendanceMasterRepository.findById(id).get();
    }

    public List<AttendanceMaster> attendanceMasters() {
        return Lists.newArrayList(attendanceMasterRepository.findAll());
    }

    public Lecture lecture(long id) {
        return lectureRepository.findById(id).get();
    }

    public List<Lecture> lectures() {
        return Lists.newArrayList(lectureRepository.findAll());
    }

    public List<DailyAttendanceVo> getDailyStudentAttendance(StudentAttendanceFilterInput filter) {
        return Lists.newArrayList(studentAttendanceFilterImpl.getStudenceAttendance(filter));
    }

    public List<Subject> getAcademicSubjects(AcademicSubjectQueryPayload academicSubjectQueryPayload) {
        return Lists.newArrayList(this.academicSubjectProcessor.getAcademicSubjects(academicSubjectQueryPayload));
    }
    public City city(long id) {
        return cityRepository.findById(id).get();
    }

    public List<City> cities() {
        return Lists.newArrayList(cityRepository.findAll());
    }

    public State state(long id) {
        return stateRepository.findById(id).get();
    }

    public List<State> states() {
        return Lists.newArrayList(stateRepository.findAll());
    }

    public Country country(long id){
        return countryRepository.findById(id).get();
    }
    public List<Country> countries(){
        return Lists.newArrayList(countryRepository.findAll());
    }

    public FeeCategory feeCategory(long id){
        return feeCategoryRepository.findById(id).get();
    }

    public List<FeeCategory> feeCategories() {
        return Lists.newArrayList(feeCategoryRepository.findAll());
    }

    public Facility facility(long id){
        return facilityRepository.findById(id).get();
    }

    public List<Facility> facilities() {
        return Lists.newArrayList(facilityRepository.findAll());
    }

    public TransportRoute transportRoute(long id){
        return transportRouteRepository.findById(id).get();
    }

    public List<TransportRoute> transportRoutes(){
        return Lists.newArrayList(transportRouteRepository.findAll());
    }

    public FeeDetails feeDetail(long id){
        return feeDetailsRepository.findById(id).get();
    }

    public List<FeeDetails> feeDetails(){
        return Lists.newArrayList(feeDetailsRepository.findAll());
    }

    public DueDate dueDate(long id) {return dueDateRepository.findById(id).get();}
    public List<DueDate> dueDates() {return  Lists.newArrayList (dueDateRepository.findAll());}
    public LateFee lateFee(long id ){return lateFeeRepository.findById(id).get();}
    public List<LateFee> lateFees(){return Lists.newArrayList(lateFeeRepository.findAll());}

    public PaymentRemainder paymentRemainder(Long id){return paymentRemainderRepository.findById(id).get();}
    public List<PaymentRemainder>paymentRemainders(){return Lists.newArrayList(paymentRemainderRepository.findAll());}

    public Invoice invoice(long id){
        return invoiceRepository.findById(id).get();
    }

    public List<Invoice>  invoices(){
        return  Lists.newArrayList(invoiceRepository.findAll());
    }

    
    public List<Invoice> searchInvoice(String invoiceNumber, long studentId){
		return Lists.newArrayList(invoiceFilterProcessor.searchInvoice(invoiceNumber, studentId));
	}
	
	public Long getTotalInvoice(long collegeId, long branchId, long academicYearId) {
		return invoiceFilterProcessor.getTotalInvoice(collegeId, branchId, academicYearId);
	}
	
	public Long getTotalPaidInvoice(long collegeId, long branchId, long academicYearId) {
		return invoiceFilterProcessor.getTotalPaidInvoice(collegeId, branchId, academicYearId);
	}
	
	public Long getTotalUnPaidInvoice(long collegeId, long branchId, long academicYearId) {
		return invoiceFilterProcessor.getTotalUnPaidInvoice(collegeId, branchId, academicYearId);
	}
	
	public Long getTotalCanceledInvoice(long collegeId, long branchId, long academicYearId) {
		return invoiceFilterProcessor.getTotalCanceledInvoice(collegeId, branchId, academicYearId);
	}
	
	public InvoiceFilterProcessor getInvoiceData(long collegeId, long branchId, long academicYearId) {
		return invoiceFilterProcessor.getInvoiceData(collegeId, branchId, academicYearId);
	}
}
