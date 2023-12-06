package me.justin.modules.comment;


import me.justin.modules.csv.CsvReader;
import me.justin.modules.csv.CsvService;
import me.justin.modules.school.School;
import me.justin.modules.school.SchoolService;

import java.util.Arrays;
import java.util.List;

public class CommentService {

    private final static String[] SCHOOL_NAME_ENDS_WITH_ARRAY = {"고등학교", "중학교", "고등학", "고등", "중학", "중", "고", "학교"};

    private final CsvService csvService = CsvService.getInstance();
    private final SchoolService schoolService = SchoolService.getInstance();

    private CommentService(){}

    private static class CommentServiceHelper {
        private static final CommentService COMMENT_SERVICE = new CommentService();
    }

    public static CommentService getInstance(){
        return CommentServiceHelper.COMMENT_SERVICE;
    }

    public void extractSchoolNameFromCSV(){
        CsvReader commentReader = csvService.createCommentsReader();
        List<String[]> commentReaderReadCSV = commentReader.getReadCSV();
        for (String[] strings : commentReaderReadCSV) {
            String[] words = strings[0].split(" ");
            for (String word : words) {
                boolean isSchoolName = false;
                for (String s : SCHOOL_NAME_ENDS_WITH_ARRAY) {
                    if (word.endsWith(s) && schoolService.existByName(word)) {
                        School school = schoolService.findByName(word);
                        school.addCount();
                        isSchoolName = true;
                        break;
                    }
                }
                if(isSchoolName) break;
            }
        }
    }



}
