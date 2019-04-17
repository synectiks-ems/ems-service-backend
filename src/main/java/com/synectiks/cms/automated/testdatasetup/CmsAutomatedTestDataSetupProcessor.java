
package com.synectiks.cms.automated.testdatasetup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.repository.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synectiks.cms.business.dto.LectureScheduleDTO;
import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.business.service.LectureService;
import com.synectiks.cms.domain.enumeration.AttendanceStatusEnum;
import com.synectiks.cms.filter.lecture.LectureScheduleFilter;

@RestController
@RequestMapping("/api")
public class CmsAutomatedTestDataSetupProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String DATA_FILE_XLSX = "cms_test_data_setup.xlsx";
    private static final String DATA_FILE_XLS = "cms_test_data_setup.xls";
    private TestDataPojoBuilder testDataPojoBuilder;

    private Country country = null;
    private State state = null;
    private City city = null;
    private AcademicYear academicYear = null;
    private Term term = null;
    private College college = null;
    private Branch branch = null;
    private Department department = null;
    private Batch batch = null;
    private Section section = null;
    private Student student = null;
    private Subject subject = null;
    private Teacher teacher = null;
    private Teach teach = null;
    private AttendanceMaster attendanceMaster = null;
    private FeeCategory feeCategory = null;
    private Facility facility = null;

    @Autowired
    private AcademicYearRepository academicYearRepository;

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeachRepository teachRepository;

    @Autowired
    private AttendanceMasterRepository attendanceMasterRepository;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private FeeCategoryRepository feeCategoryRepository;

    @Autowired
    private FacilityRepository facilityRepository;


    private ObjectMapper mapper = new ObjectMapper();
    String values[] = new String[1];
    LectureScheduleFilter filter = new LectureScheduleFilter();

    @RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/create")
    public void createCmsTestData() throws IOException, ParseException {
        try {
            execute();
        } catch (Exception e) {
            logger.error("Exception in creating CMS test data ", e);
        }
    }


    @RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createAcademicYear")
    public void createCmsTestAcademicYearData() throws IOException, ParseException {
        try {
            executeAcademicYear();
        } catch (Exception e) {
            logger.error("Exception in creating CMS AcademicYear test data ", e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createTerm")
    public void createCmsTestTermData() throws IOException, ParseException {
        try {
            executeTerm();
        } catch (Exception e) {
            logger.error("Exception in creating CMS Term test data ", e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createCollege")
    public void createCmsTestCollegeData() throws IOException, ParseException {
        try {
            executeCollege();
        } catch (Exception e) {
            logger.error("Exception in creating CMS College test data ", e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createTeacherTeachAttendanceMaster")
    public void createCmsTeacherTeachAttendanceMasterData() throws IOException, ParseException {
        try {
            executeTeacherTeachAttendanceMaster();
        } catch (Exception e) {
            logger.error("Exception in creating CMS TeacherTeachAttendanceMaster test data ", e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createBranch")
    public void createCmsTestBranchData() throws IOException, ParseException {
        try {
            executeBranch();
        } catch (Exception e) {
            logger.error("Exception in creating CMS Branch test data ", e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createDepartment")
    public void createCmsTestDepartmentData() throws IOException, ParseException {
        try {
            executeDepartment();
        } catch (Exception e) {
            logger.error("Exception in creating CMS Department test data ", e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createBatch")
    public void createCmsTestBatchData() throws IOException, ParseException {
        try {
            executeBatch();
        } catch (Exception e) {
            logger.error("Exception in creating CMS Batch test data ", e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createSection")
    public void createCmsTestSectionData() throws IOException, ParseException {
        try {
            executeSection();
        } catch (Exception e) {
            logger.error("Exception in creating CMS Section test data ", e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createStudent")
    public void createCmsTestStudentData() throws IOException, ParseException {
        try {
            executeStudent();
        } catch (Exception e) {
            logger.error("Exception in creating CMS Student test data ", e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createSubject")
    public void createCmsTestSubjectData() throws IOException, ParseException {
        try {
            executeSubject();
        } catch (Exception e) {
            logger.error("Exception in creating CMS Subject test data ", e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createLecture")
    public void createCmsTestLectureData() throws IOException, ParseException {
        try {
            executeLecture();
        } catch (Exception e) {
            logger.error("Exception in creating CMS  Lecture test data ", e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createFeeCategory")
    public void createCmsTestFeeCategoryData() throws IOException, ParseException {
        try {
            executeFeeCategory();
        } catch (Exception e) {
            logger.error("Exception in creating CMS  FeeCategory test data ", e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createFacility")
    public void createCmsTestFacilityData() throws IOException, ParseException {
        try {
            executeFacility();
        } catch (Exception e) {
            logger.error("Exception in creating CMS  Facility test data ", e);
        }
    }


    private void executeAcademicYear() throws IOException, ParseException, InterruptedException {
        this.testDataPojoBuilder = new TestDataPojoBuilder();
        FileInputStream fis = null;
        try {
            File f = getFile();
            fis = loadFile(f);
            Workbook wb = getWorkbook(fis);
            saveCmsAcademicYearData(wb);
            logger.info("AcademicYear TEST DATA LOADING COMPLETED......");
        } finally {
            if (fis != null) fis.close();
        }
    }


    private void executeTerm() throws IOException, ParseException, InterruptedException {
        this.testDataPojoBuilder = new TestDataPojoBuilder();
        FileInputStream fis = null;
        try {
            File f = getFile();
            fis = loadFile(f);
            Workbook wb = getWorkbook(fis);
            saveCmsAcademicYearData(wb);
            saveCmsTermData(wb);
            logger.info("Term TEST DATA LOADING COMPLETED......");
        } finally {
            if (fis != null) fis.close();
        }
    }

    private void executeCollege() throws IOException, ParseException, InterruptedException {
        this.testDataPojoBuilder = new TestDataPojoBuilder();
        FileInputStream fis = null;
        try {
            File f = getFile();
            fis = loadFile(f);
            Workbook wb = getWorkbook(fis);
            saveCmsCollegeData(wb);
            logger.info("College TEST DATA LOADING COMPLETED......");
        } finally {
            if (fis != null) fis.close();
        }
    }

    private void executeBranch() throws IOException, ParseException, InterruptedException {
        this.testDataPojoBuilder = new TestDataPojoBuilder();
        FileInputStream fis = null;
        try {
            File f = getFile();
            fis = loadFile(f);
            Workbook wb = getWorkbook(fis);
            saveCountryData();
            saveStateData(wb);
            saveCityData(wb);
            saveCmsCollegeData(wb);
            saveCmsBranchData(wb);
            logger.info("Branch TEST DATA LOADING COMPLETED......");
        } finally {
            if (fis != null) fis.close();
        }
    }

    private void executeTeacherTeachAttendanceMaster() throws IOException, ParseException, InterruptedException {
        this.testDataPojoBuilder = new TestDataPojoBuilder();
        FileInputStream fis = null;
        try {
            File f = getFile();
            fis = loadFile(f);
            Workbook wb = getWorkbook(fis);
            saveCountryData();
            saveStateData(wb);
            saveCityData(wb);
            saveCmsSubjectData(wb);
            saveCmsTeacherTeachAttendanceMaster(wb);
            saveCmsBatchData(wb);
            saveCmsSectionData(wb);
            saveCmsDepartmentData(wb);
            saveCmsBranchData(wb);
            logger.info("TeacherTeachAttendanceMaster TEST DATA LOADING COMPLETED......");
        } finally {
            if (fis != null) fis.close();
        }
    }


    private void executeDepartment() throws IOException, ParseException, InterruptedException {
        this.testDataPojoBuilder = new TestDataPojoBuilder();
        FileInputStream fis = null;
        try {
            File f = getFile();
            fis = loadFile(f);
            Workbook wb = getWorkbook(fis);
            saveCmsBranchData(wb);
            saveCmsAcademicYearData(wb);
            saveCmsDepartmentData(wb);
            logger.info("Department TEST DATA LOADING COMPLETED......");
        } finally {
            if (fis != null) fis.close();
        }
    }

    private void executeBatch() throws IOException, ParseException, InterruptedException {
        this.testDataPojoBuilder = new TestDataPojoBuilder();
        FileInputStream fis = null;
        try {
            File f = getFile();
            fis = loadFile(f);
            Workbook wb = getWorkbook(fis);
            saveCmsDepartmentData(wb);
            saveCmsBatchData(wb);
            logger.info("Batch TEST DATA LOADING COMPLETED......");
        } finally {
            if (fis != null) fis.close();
        }
    }

    private void executeSection() throws IOException, ParseException, InterruptedException {
        this.testDataPojoBuilder = new TestDataPojoBuilder();
        FileInputStream fis = null;
        try {
            File f = getFile();
            fis = loadFile(f);
            Workbook wb = getWorkbook(fis);
            saveCmsBatchData(wb);
            saveCmsSectionData(wb);
            logger.info("Section TEST DATA LOADING COMPLETED......");
        } finally {
            if (fis != null) fis.close();
        }
    }

    private void executeStudent() throws IOException, ParseException, InterruptedException {
        this.testDataPojoBuilder = new TestDataPojoBuilder();
        FileInputStream fis = null;
        try {
            File f = getFile();
            fis = loadFile(f);
            Workbook wb = getWorkbook(fis);
            saveCountryData();
            saveStateData(wb);
            saveCityData(wb);
            saveCmsDepartmentData(wb);
            saveCmsBatchData(wb);
            saveCmsSectionData(wb);
            saveCmsBranchData(wb);
            saveCmsStudentData(wb);
            logger.info("Student TEST DATA LOADING COMPLETED......");
        } finally {
            if (fis != null) fis.close();
        }
    }

    private void executeSubject() throws IOException, ParseException, InterruptedException {
        this.testDataPojoBuilder = new TestDataPojoBuilder();
        FileInputStream fis = null;
        try {
            File f = getFile();
            fis = loadFile(f);
            Workbook wb = getWorkbook(fis);
            saveCmsDepartmentData(wb);
            saveCmsBatchData(wb);
            saveCmsSubjectData(wb);
            logger.info("Subject TEST DATA LOADING COMPLETED......");
        } finally {
            if (fis != null) fis.close();
        }
    }

    private void executeLecture() throws IOException, ParseException, InterruptedException {
        this.testDataPojoBuilder = new TestDataPojoBuilder();
        FileInputStream fis = null;
        try {
            File f = getFile();
            fis = loadFile(f);
            Workbook wb = getWorkbook(fis);
            saveCmsTeacherTeachAttendanceMaster(wb);
            saveCmsLecture(wb);
            logger.info("Lecture TEST DATA LOADING COMPLETED......");
        } finally {
            if (fis != null) fis.close();
        }
    }

    private void executeFeeCategory() throws IOException, ParseException, InterruptedException {
        this.testDataPojoBuilder = new TestDataPojoBuilder();
        FileInputStream fis = null;
        try {
            File f = getFile();
            fis = loadFile(f);
            Workbook wb = getWorkbook(fis);
            saveCmsFeeCategory(wb);
            logger.info("FeeCategory TEST DATA LOADING COMPLETED......");
        } finally {
            if (fis != null) fis.close();
        }
    }

    private void executeFacility() throws IOException, ParseException, InterruptedException {
        this.testDataPojoBuilder = new TestDataPojoBuilder();
        FileInputStream fis = null;
        try {
            File f = getFile();
            fis = loadFile(f);
            Workbook wb = getWorkbook(fis);
            saveCmsAcademicYearData(wb);
            saveCmsBranchData(wb);
            saveCmsStudentData(wb);
            saveCmsFacilityData(wb);
            logger.info("Facility TEST DATA LOADING COMPLETED......");
        } finally {
            if (fis != null) fis.close();
        }
    }

    private void execute() throws IOException, ParseException, InterruptedException {
        this.testDataPojoBuilder = new TestDataPojoBuilder();
        FileInputStream fis = null;
        try {
            File f = getFile();
            fis = loadFile(f);
            Workbook wb = getWorkbook(fis);
            saveCountryData();
            saveStateData(wb);
            saveCityData(wb);
            saveCmsData(wb);
            logger.info("TEST DATA LOADING COMPLETED......");
        } finally {
            if (fis != null) fis.close();
        }
    }

    private File getFile() throws FileNotFoundException {
        String r = Paths.get("").toAbsolutePath() + "/src/test/" + DATA_FILE_XLSX;
        File f = new File(r);
        if (!f.exists()) {
            r = Paths.get("").toAbsolutePath() + "/src/test/" + DATA_FILE_XLS;
            f = new File(r);
            if (!f.exists()) {
                throw new FileNotFoundException("File not found : " + r);
            }
        }
        return f;
    }

    private FileInputStream loadFile(File f) throws FileNotFoundException {
        return new FileInputStream(f);
    }

    private Workbook getWorkbook(FileInputStream fis) throws IOException {
        Workbook workbook = new XSSFWorkbook(fis);
        return workbook;
    }

    private Sheet getSheet(Workbook workbook, String sheetName) {
        return workbook.getSheet(sheetName);
    }

    private void saveCountryData() {
        logger.debug("Saving country data started.");
        this.country = this.testDataPojoBuilder.createCountryPojo();
        try {
            Example<Country> example = Example.of(this.country);
            if (this.countryRepository.exists(example) == false) {
                this.country = this.countryRepository.save(this.country);
            } else {
                this.country = this.countryRepository.findOne(example).get();
            }
        } catch (Exception e) {
            logger.warn("Exception in saving country data. " + e.getMessage());
        }
        logger.debug("Saving country data completed.");
    }

    private void saveStateData(Workbook workbook) {
        logger.debug("Saving state data started.");
        Sheet sheet = getSheet(workbook, "state");
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.
            this.state = this.testDataPojoBuilder.createStatePojo(row, this.country);
            try {
                Example<State> example = Example.of(this.state);
                if (this.stateRepository.exists(example) == false) {
                    this.state = this.stateRepository.save(this.state);
                }
            } catch (Exception e) {
                logger.warn("Exception in saving state data. " + e.getMessage());
            }
        }
        logger.debug("Saving state data completed.");
    }

    private void saveCityData(Workbook workbook) {
        logger.debug("Saving city data started.");
        Sheet sheet = getSheet(workbook, "city");
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.
            this.city = this.testDataPojoBuilder.createCityPojo(row, this.stateRepository);
            try {
                Example<City> example = Example.of(this.city);
                if (this.cityRepository.exists(example) == false) {
                    this.city = this.cityRepository.save(this.city);
                }
            } catch (Exception e) {
                logger.warn("Exception in saving city data. " + e.getMessage());
            }
        }
        logger.debug("Saving city data completed.");
    }

    private void saveCmsData(Workbook workbook) throws ParseException, InterruptedException {
        Sheet sheet = getSheet(workbook, "cmstestdata");
        logger.debug(sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            logger.debug("Row number : " + row.getRowNum());
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getColumnIndex() == 0) {
                    saveAcademicYear(cell);
                }
                if (cell.getColumnIndex() == 1) {
                    saveTermData(cell);
                }
                if (cell.getColumnIndex() == 2) {
                    saveCollegeData(cell);
                }
                if (cell.getColumnIndex() == 3) {
                    saveBranchData(cell);
                }
                if (cell.getColumnIndex() == 4) {
                    saveDepartmentData(cell);
                }
                if (cell.getColumnIndex() == 5) {
                    saveBatchData(cell);
                }
                if (cell.getColumnIndex() == 6) {
                    saveSectionData(cell);
                }
                if (cell.getColumnIndex() == 7) {
                    saveStudentData(cell);
                }
                if (cell.getColumnIndex() == 8) {
                    saveSubjectData(cell);
                }
                if (cell.getColumnIndex() == 9) {
                    saveTeacherTeachAttendanceMasterData(cell);
                }
                if (cell.getColumnIndex() == 10) {
                    saveLectureSchedule("MONDAY", cell, Calendar.MONDAY);
                }
                if (cell.getColumnIndex() == 11) {
                    saveLectureSchedule("TUESDAY", cell, Calendar.TUESDAY);
                }
                if (cell.getColumnIndex() == 12) {
                    saveLectureSchedule("WEDNESDAY", cell, Calendar.WEDNESDAY);
                }
                if (cell.getColumnIndex() == 13) {
                    saveLectureSchedule("THURSDAY", cell, Calendar.THURSDAY);
                }
                if (cell.getColumnIndex() == 14) {
                    saveLectureSchedule("FRIDAY", cell, Calendar.FRIDAY);
                }
                if (cell.getColumnIndex() == 15) {
                    saveLectureSchedule("SATURDAY", cell, Calendar.SATURDAY);
                }
                if (cell.getColumnIndex() == 16) {
                    saveStudentAttendanceData();

                }
                if (cell.getColumnIndex() == 17) {
                    saveFeeCategory(cell);
                }
                if (cell.getColumnIndex() == 18) {
                    saveFacility(cell);
                }
            }
        }
    }


    private void saveCmsAcademicYearData(Workbook workbook) throws ParseException, InterruptedException {
        Sheet sheet = getSheet(workbook, "cmstestdata");
        logger.debug(sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            logger.debug("Row number : " + row.getRowNum());
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getColumnIndex() == 0) {
                    saveAcademicYear(cell);
                }
            }
        }
    }

    private void saveCmsTermData(Workbook workbook) throws ParseException, InterruptedException {
        Sheet sheet = getSheet(workbook, "cmstestdata");
        logger.debug(sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            logger.debug("Row number : " + row.getRowNum());
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getColumnIndex() == 1) {
                    saveTermData(cell);
                }
            }
        }
    }

    private void saveCmsCollegeData(Workbook workbook) throws ParseException, InterruptedException {
        Sheet sheet = getSheet(workbook, "cmstestdata");
        logger.debug(sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            logger.debug("Row number : " + row.getRowNum());
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getColumnIndex() == 2) {
                    saveCollegeData(cell);
                }
            }
        }
    }

    private void saveCmsBranchData(Workbook workbook) throws ParseException, InterruptedException {
        Sheet sheet = getSheet(workbook, "cmstestdata");
        logger.debug(sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            logger.debug("Row number : " + row.getRowNum());
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getColumnIndex() == 3) {
                    saveBranchData(cell);
                }
            }
        }
    }


    private void saveCmsDepartmentData(Workbook workbook) throws ParseException, InterruptedException {
        Sheet sheet = getSheet(workbook, "cmstestdata");
        logger.debug(sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            logger.debug("Row number : " + row.getRowNum());
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getColumnIndex() == 4) {
                    saveDepartmentData(cell);
                }
            }
        }
    }


    private void saveCmsBatchData(Workbook workbook) throws ParseException, InterruptedException {
        Sheet sheet = getSheet(workbook, "cmstestdata");
        logger.debug(sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            logger.debug("Row number : " + row.getRowNum());
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getColumnIndex() == 5) {
                    saveBatchData(cell);
                }
            }
        }
    }


    private void saveCmsSectionData(Workbook workbook) throws ParseException, InterruptedException {
        Sheet sheet = getSheet(workbook, "cmstestdata");
        logger.debug(sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            logger.debug("Row number : " + row.getRowNum());
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getColumnIndex() == 6) {
                    saveSectionData(cell);
                }
            }
        }
    }

    private void saveCmsStudentData(Workbook workbook) throws ParseException, InterruptedException {
        Sheet sheet = getSheet(workbook, "cmstestdata");
        logger.debug(sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            logger.debug("Row number : " + row.getRowNum());
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getColumnIndex() == 7) {
                    saveStudentData(cell);
                }
            }
        }
    }

    private void saveCmsSubjectData(Workbook workbook) throws ParseException, InterruptedException {
        Sheet sheet = getSheet(workbook, "cmstestdata");
        logger.debug(sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            logger.debug("Row number : " + row.getRowNum());
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getColumnIndex() == 8) {
                    saveSubjectData(cell);
                }
            }
        }
    }

    private void saveCmsTeacherTeachAttendanceMaster(Workbook workbook) throws ParseException, InterruptedException {
        Sheet sheet = getSheet(workbook, "cmstestdata");
        logger.debug(sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            logger.debug("Row number : " + row.getRowNum());
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getColumnIndex() == 9) {
                    saveTeacherTeachAttendanceMasterData(cell);
                }
            }
        }
    }

    private void saveCmsLecture(Workbook workbook) throws ParseException, InterruptedException {
        Sheet sheet = getSheet(workbook, "cmstestdata");
        logger.debug(sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            logger.debug("Row number : " + row.getRowNum());
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getColumnIndex() == 10) {
                    saveLectureSchedule("MONDAY", cell, Calendar.MONDAY);
                }
                if (cell.getColumnIndex() == 11) {
                    saveLectureSchedule("TUESDAY", cell, Calendar.TUESDAY);
                }
                if (cell.getColumnIndex() == 12) {
                    saveLectureSchedule("WEDNESDAY", cell, Calendar.WEDNESDAY);
                }
                if (cell.getColumnIndex() == 13) {
                    saveLectureSchedule("THURSDAY", cell, Calendar.THURSDAY);
                }
                if (cell.getColumnIndex() == 14) {
                    saveLectureSchedule("FRIDAY", cell, Calendar.FRIDAY);
                }
                if (cell.getColumnIndex() == 15) {
                    saveLectureSchedule("SATURDAY", cell, Calendar.SATURDAY);

                }

            }
        }
    }

    private void saveCmsFeeCategory(Workbook workbook) throws ParseException, InterruptedException {
        Sheet sheet = getSheet(workbook, "cmstestdata");
        logger.debug(sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            logger.debug("Row number : " + row.getRowNum());
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getColumnIndex() == 17) {
                    saveFeeCategory(cell);
                }
            }
        }
    }

    private void saveCmsFacilityData(Workbook workbook) throws ParseException, InterruptedException {
        Sheet sheet = getSheet(workbook, "cmstestdata");
        logger.debug(sheet.getSheetName());
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            logger.debug("Row number : " + row.getRowNum());
            if (row.getRowNum() <= 0) continue; // First row is a header row. skipping it.

            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getColumnIndex() == 18) {
                    saveFacility(cell);
                }
            }
        }
    }


    private void saveAcademicYear(Cell cell) {
        logger.debug("Saving academic year data started.");
        this.academicYear = this.testDataPojoBuilder.createAcademicYearPojo(cell);
        try {
            Example<AcademicYear> example = Example.of(this.academicYear);
            if (this.academicYearRepository.exists(example) == false) {
                this.academicYear = this.academicYearRepository.save(this.academicYear);
            } else {
                this.academicYear = this.academicYearRepository.findOne(example).get();
            }
        } catch (Exception e) {
            logger.warn("Exception in saving academic year data. " + e.getMessage());
        }
        logger.debug("Saving academic year data completed.");
    }

    private void saveTermData(Cell cell) throws ParseException {
        logger.debug("Saving term data started.");
        this.term = this.testDataPojoBuilder.createTermPojo(cell, this.academicYear);
        try {
            Example<Term> example = Example.of(this.term);
            if (this.termRepository.exists(example) == false) {
                this.term = this.termRepository.save(this.term);
            } else {
                this.term = this.termRepository.findOne(example).get();
            }
        } catch (Exception e) {
            logger.warn("Exception in saving term data. " + e.getMessage());
        }
        logger.debug("Saving term data completed.");
    }

    private void saveCollegeData(Cell cell) {
        logger.debug("Saving college data started.");
        this.college = this.testDataPojoBuilder.createCollegePojo(cell);
        try {
            Example<College> example = Example.of(this.college);
            if (this.collegeRepository.exists(example) == false) {
                this.college = this.collegeRepository.save(this.college);
            } else {
                this.college = this.collegeRepository.findOne(example).get();
            }
        } catch (Exception e) {
            logger.warn("Exception in saving college data. " + e.getMessage());
        }
        logger.debug("Saving college data completed.");
    }

    private void saveBranchData(Cell cell) {
        logger.debug("Saving branch data started.");
        State st = findStateWithStateName("Telangana");
        City ct = findCiyt(st, "Hyderabad");
        this.branch = this.testDataPojoBuilder.createBranchPojo(cell, this.college, st, ct);
        try {
            Example<Branch> example = Example.of(this.branch);
            if (this.branchRepository.exists(example) == false) {
                this.branch = this.branchRepository.save(this.branch);
            } else {
                this.branch = this.branchRepository.findOne(example).get();
            }
        } catch (Exception e) {
            logger.warn("Exception in saving branch data. " + e.getMessage());
        }
        logger.debug("Saving branch data completed.");
    }

    private State findStateWithStateName(String stateName) {
        State st = new State();
        st.setStateName(stateName);
        Example<State> example = Example.of(st);
        Optional<State> newTh = this.stateRepository.findOne(example);
        if (newTh.isPresent()) {
            return newTh.get();
        }
        return null;
    }

    private City findCiyt(State st, String cityName) {
        City ct = new City();
        ct.setState(st);
        ct.setCityName(cityName);
        Example<City> example = Example.of(ct);
        Optional<City> newTh = this.cityRepository.findOne(example);
        if (newTh.isPresent()) {
            return newTh.get();
        }
        return null;
    }

    private void saveDepartmentData(Cell cell) {
        logger.debug("Saving department data started.");
        this.department = this.testDataPojoBuilder.createDepartmentPojo(cell, this.branch, this.academicYear);
        try {
            Example<Department> example = Example.of(this.department);
            if (this.departmentRepository.exists(example) == false) {
                this.department = this.departmentRepository.save(this.department);
            } else {
                this.department = this.departmentRepository.findOne(example).get();
            }
        } catch (Exception e) {
            logger.warn("Exception in saving department data. " + e.getMessage());
        }
        logger.debug("Saving department data completed.");
    }

    private void saveBatchData(Cell cell) {
        logger.debug("Saving batch data started.");
        this.batch = this.testDataPojoBuilder.createBatchPojo(cell, this.department);
        try {
            Example<Batch> example = Example.of(this.batch);
            if (this.batchRepository.exists(example) == false) {
                this.batch = this.batchRepository.save(this.batch);
            } else {
                this.batch = this.batchRepository.findOne(example).get();
            }
        } catch (Exception e) {
            logger.warn("Exception in saving batch data. " + e.getMessage());
        }
        logger.debug("Saving batch data completed.");
    }

    private void saveSectionData(Cell cell) {
        logger.debug("Saving section data started.");
        this.section = this.testDataPojoBuilder.createSectionPojo(cell, this.batch);
        try {
            Example<Section> example = Example.of(this.section);
            if (this.sectionRepository.exists(example) == false) {
                this.section = this.sectionRepository.save(this.section);
            } else {
                this.section = this.sectionRepository.findOne(example).get();
            }
        } catch (Exception e) {
            logger.warn("Exception in saving section data. " + e.getMessage());
        }
        logger.debug("Saving section data completed.");
    }

    private void saveStudentData(Cell cell) {
        logger.debug("Saving student data started.");
        State st = findStateWithStateName("Telangana");
        City ct = findCiyt(st, "Hyderabad");
        this.student = this.testDataPojoBuilder.createStudentPojo(cell,this.department, this.batch, this.section, this.branch, st, ct, this.country);
        try {
            Example<Student> example = Example.of(this.student);
            if (this.studentRepository.exists(example) == false) {
                this.student = this.studentRepository.save(this.student);
            } else {
                this.student = this.studentRepository.findOne(example).get();
            }
        } catch (Exception e) {
            logger.warn("Exception in saving student data. " + e.getMessage());
        }
        logger.debug("Saving student data completed.");
    }

    private void saveSubjectData(Cell cell) {
        logger.debug("Saving subject data object started.");
        this.subject = this.testDataPojoBuilder.createSubjectPojo(cell, this.department, this.batch);
        try {
            Example<Subject> example = Example.of(this.subject);
            if (this.subjectRepository.exists(example) == false) {
                this.subject = this.subjectRepository.save(this.subject);
            } else {
                this.subject = this.subjectRepository.findOne(example).get();
            }
        } catch (Exception e) {
            logger.warn("Exception in saving subject data. " + e.getMessage());
        }
        logger.debug("Saving subject data object completed.");
    }

    private void saveTeacherTeachAttendanceMasterData(Cell cell) {
        logger.debug("Saving teacher, teach and attendance_master data started.");
        State st = findStateWithStateName("Telangana");
        City ct = findCiyt(st, "Hyderabad");

        try {
            this.teacher = this.testDataPojoBuilder.createTeacherPojo(cell, this.department, this.branch, st, ct, this.country);
            Example<Teacher> thrExample = Example.of(this.teacher);
            if (this.teacherRepository.exists(thrExample) == false) {
                this.teacher = this.teacherRepository.save(this.teacher);
            } else {
                this.teacher = this.teacherRepository.findOne(thrExample).get();
            }

            this.teach = this.testDataPojoBuilder.createTeachPojo(cell, this.subject, this.teacher);
            Example<Teach> thExample = Example.of(this.teach);
            if (this.teachRepository.exists(thExample) == false) {
                this.teach = this.teachRepository.save(this.teach);
            } else {
                this.teach = this.teachRepository.findOne(thExample).get();
            }

            this.attendanceMaster = this.testDataPojoBuilder.createAttendanceMasterPojo(cell, this.batch, this.section, this.teach, this.subject, this.teacher);
            Example<AttendanceMaster> amExample = Example.of(this.attendanceMaster);
            if (this.attendanceMasterRepository.exists(amExample) == false) {
                this.attendanceMaster = this.attendanceMasterRepository.save(this.attendanceMaster);
            } else {
                this.attendanceMaster = this.attendanceMasterRepository.findOne(amExample).get();
            }


        } catch (Exception e) {
            logger.warn("Exception in saving teacher, teach and attendance_master data. " + e.getMessage());
        }
        logger.debug("Saving teacher, teach and attendance_master data completed.");
    }

    private void saveLectureSchedule(String day, Cell cell, int intDay) {
        boolean isGoAhead = false;
        switch (cell.getCellTypeEnum()) {
            case BLANK:
                logger.warn("Blank cell. No lecture record will be created.");
                break;
            case BOOLEAN:
                logger.warn("Boolean cell. No lecture record will be created.");
                break;
            case ERROR:
                logger.warn("Error cell. No lecture record will be created.");
                break;
            case FORMULA:
                logger.warn("Formula cell. lecture No record will be created.");
                break;
            case NUMERIC:
                logger.warn("Numeric cell. lecture No record will be created.");
                break;
            case STRING:
                isGoAhead = true;
                break;
            case _NONE:
                logger.warn("_None cell. No lecture record will be created.");
                break;
            default:
                logger.warn("Blank or _None cell. lecture No record will be created.");
                break;
        }
        if (isGoAhead == false) {
            return;
        }
        logger.debug("Saving lecture data for " + day + " started.");
        try {
            LectureScheduleDTO dto = this.testDataPojoBuilder.getDto(day, cell, this.subject, this.teacher);
            values[0] = this.mapper.writeValueAsString(dto);
            filter.setTermId(String.valueOf(this.term.getId()));
            filter.setAcademicYear(this.academicYear.getYear());
            filter.setSectionId(String.valueOf(this.section.getId()));
            filter.setBatchId(String.valueOf(this.batch.getId()));

            List<Date> dtList = filterDates(intDay);

            Lecture lc = new Lecture();
            lc.setAttendancemaster(this.attendanceMaster);
            lc.setStartTime(dto.getStartTime());
            lc.setEndTime(dto.getEndTime());
            Example<Lecture> example = Example.of(lc);
            ;

            for (Date dt : dtList) {
                lc.setId(null);
                lc.setLecDate(dt);
                if (this.lectureRepository.exists(example) == false) {
                    this.lectureService.createLectureSchedule(dt, values, day, filter);
                }
            }
        } catch (Exception e) {
            logger.warn("Lecture data for " + day + " could not be saved in DB due the below exception.");
            logger.warn("Exception. " + e.getMessage());
        }
        logger.debug("Saving lecture data for " + day + " completed.");
    }

    private List<Date> filterDates(int day) throws ParseException {
        List<Date> dateList = this.lectureService.createDates(this.term);
        List<Holiday> holidayList = this.commonService.getHolidayList(this.academicYear.getYear());
        this.lectureService.filterHolidays(holidayList, dateList);
        this.lectureService.filterSundays(dateList);
        List<Date> list = this.lectureService.filterDateListOnDayOfweek(dateList, day);
        return list;
    }

    private void saveStudentAttendanceData() {
        List<Lecture> lcList = this.testDataPojoBuilder.findLectureByAttendanceMaster(this.lectureRepository, this.attendanceMaster);

        StudentAttendance sa = new StudentAttendance();
        sa.attendanceStatus(AttendanceStatusEnum.PRESENT);
        sa.setStudent(student);
        Example<StudentAttendance> example = Example.of(sa);
        for (Lecture lecture : lcList) {
            sa.setId(null);
            sa.setLecture(lecture);
            try {
                if (this.studentAttendanceRepository.exists(example) == false) {
                    this.studentAttendanceRepository.save(sa);
                }
            } catch (Exception e) {
                logger.warn("Exception in saving studentAttendance data. " + e.getMessage());
            }
        }
    }

    private void saveFeeCategory(Cell cell) {
        logger.debug("Saving feecategory data started.");
        this.feeCategory = this.testDataPojoBuilder.createFeeCategoryPojo(cell);
        try {
            Example<FeeCategory> example = Example.of(this.feeCategory);
            if (this.feeCategoryRepository.exists(example) == false) {
                this.feeCategory = this.feeCategoryRepository.save(this.feeCategory);
            } else {
                this.feeCategory = this.feeCategoryRepository.findOne(example).get();
            }
        } catch (Exception e) {
            logger.warn("Exception in saving feeCategory data. " + e.getMessage());
        }
        logger.debug("Saving feeCategory data completed.");
    }


    private void saveFacility(Cell cell) {
        logger.debug("Saving facility data started.");
        this.facility = this.testDataPojoBuilder.createFacilityPojo(cell,this.academicYear,this.branch,this.student);
        try {
            Example<Facility> example = Example.of(this.facility);
            if(this.facilityRepository.exists(example) == false) {
                this.facility = this.facilityRepository.save(this.facility);
            }else {
                this.facility = this.facilityRepository.findOne(example).get();
            }
        }catch(Exception e) {
            logger.warn("Exception in saving facility data. "+e.getMessage());
        }
        logger.debug("Saving facility data completed.");
    }
}

