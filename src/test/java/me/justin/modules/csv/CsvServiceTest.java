package me.justin.modules.csv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CsvServiceTest {

    private final static String COMMENTS_CONTENT = " message ";
    private final static String HIGH_SCHOOL_NAME = "국립국악고등학교";
    private final static String MIDDLE_SCHOOL_NAME = "서울대학교사범대학부설중학교";

    private CsvService csvService;

    @BeforeEach
    void beforeAll(){
        csvService = CsvService.getInstance();
    }

    @DisplayName("Singleton 적용 여부 확인")
    @Test
    void getInstance() {
        CsvService otherCsvService = CsvService.getInstance();
        assertThat(true).isEqualTo(csvService == otherCsvService);
    }

    @DisplayName("Comments CSV 읽기 여부 확인")
    @Test
    void createCommentsReader() {
        CsvReader csvReader = csvService.createCommentsReader();
        String[] getNext = csvReader.nextRead();
        assertThat(COMMENTS_CONTENT).isEqualTo(getNext[0]);
    }

    @DisplayName("High School List CSV 읽기 여부 확인")
    @Test
    void createHighSchoolReader() {
        CsvReader csvReader = csvService.createHighSchoolReader();
        String[] getNext = csvReader.nextRead();
        assertThat(HIGH_SCHOOL_NAME).isEqualTo(getNext[0]);
    }

    @DisplayName("Middle School List CSV 읽기 여부 확인")
    @Test
    void createMiddleSchoolReader() {
        CsvReader csvReader = csvService.createMiddleSchoolReader();
        String[] getNext = csvReader.nextRead();
        assertThat(MIDDLE_SCHOOL_NAME).isEqualTo(getNext[0]);
    }
}