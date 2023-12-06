package me.justin.modules.school;

import me.justin.modules.csv.CsvReader;
import me.justin.modules.csv.CsvService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SchoolServiceTest {

    private final static String HIGH_SCHOOL_NAME = "국립국악고등학교";
    private final static String MIDDLE_SCHOOL_NAME = "서울대학교사범대학부설중학교";

    private CsvService csvService;
    private SchoolService schoolService;

    @BeforeEach
    void beforeAll(){
        csvService = CsvService.getInstance();
        schoolService = SchoolService.getInstance();

        schoolService.save(School.createSchool(MIDDLE_SCHOOL_NAME));
    }

    @DisplayName("Singleton 적용 여부 확인")
    @Test
    void getInstance() {
        SchoolService otherSchoolService = SchoolService.getInstance();
        assertThat(true).isEqualTo(schoolService == otherSchoolService);
    }

    @DisplayName("save")
    @Test
    void save() {
        School school = School.createSchool(HIGH_SCHOOL_NAME);
        School saveSchool = schoolService.save(school);
        assertThat(true).isEqualTo(HIGH_SCHOOL_NAME.equals(saveSchool.getName()));
    }

    @DisplayName("존재 여부 확인")
    @Test
    void existByName() {
        schoolService.save(School.createSchool(HIGH_SCHOOL_NAME));
        assertThat(true).isEqualTo(schoolService.existByName(HIGH_SCHOOL_NAME));
    }

    @DisplayName("학교 이름으로 찾기")
    @Test
    void findByName() {
        School findSchool = schoolService.findByName(MIDDLE_SCHOOL_NAME);
        assertThat(true).isEqualTo(MIDDLE_SCHOOL_NAME.equals(findSchool.getName()));
    }

    @DisplayName("Count가 0이 아닌 전체 학교 이름 찾기 - 없을 때")
    @Test
    void findAllWithoutCountZero_No_Result() {
        List<School> schoolList = schoolService.findAllWithoutCountZero();
        assertThat(true).isEqualTo(schoolList.isEmpty());
    }

    @DisplayName("Count가 0이 아닌 전체 학교 이름 찾기 - 있을 때")
    @Test
    void findAllWithoutCountZero_Exist_Result() {
        School findSchool = schoolService.findByName(MIDDLE_SCHOOL_NAME);
        findSchool.addCount();

        List<School> schoolList = schoolService.findAllWithoutCountZero();
        assertThat(true).isEqualTo(schoolList.size() == 1);
    }

    @Test
    void saveSchoolList() {
        CsvReader csvReader = csvService.createHighSchoolReader();
        schoolService.saveSchoolList(csvReader.getReadCSV());
        School findSchool = schoolService.findByName(HIGH_SCHOOL_NAME);
        assertThat(true).isEqualTo(HIGH_SCHOOL_NAME.equals(findSchool.getName()));
    }
}