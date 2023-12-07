package me.justin.modules.comment;

import me.justin.modules.csv.CsvReader;
import me.justin.modules.csv.CsvService;
import me.justin.modules.school.School;
import me.justin.modules.school.SchoolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommentServiceTest {
    private static final String TEST_CSV_FILE_NAME = "target/classes/csv/Test.csv";
    private CsvService csvService;
    private SchoolService schoolService;
    private CommentService commentService;

    @BeforeEach
    void beforeAll(){
        csvService = CsvService.getInstance();
        schoolService = SchoolService.getInstance();
        commentService = CommentService.getInstance();
    }

    @DisplayName("Singleton 적용 여부 확인")
    @Test
    void getInstance() {
        CommentService oterCommentService = CommentService.getInstance();
        assertThat(true).isEqualTo(commentService == oterCommentService);
    }

    @DisplayName("Test CSV 추출 확인")
    @Test
    void extractSchoolNameFromCSV() {
        CsvReader csvReader = CsvReader.createCsvReader(TEST_CSV_FILE_NAME);
        schoolService.saveSchoolList();
        List<String[]> commentReaderReadCSV = csvReader.getReadCSV();
        List<School> schoolList = schoolService.findAll();
        for (School school : schoolList) {
            for (String[] strings : commentReaderReadCSV) {
                if (strings[0].contains(school.getName())){
                    school.addCount();
                    System.out.println("Line : " + strings[0]);
                    System.out.println("School Name : "+school.getName());
                }
            }
        }
        School school = schoolService.findByName("행신중");
        assertThat(true).isEqualTo(school.getCount().equals(2));
    }

}