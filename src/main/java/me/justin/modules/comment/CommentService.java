package me.justin.modules.comment;


import me.justin.modules.csv.CsvReader;
import me.justin.modules.csv.CsvService;
import me.justin.modules.school.School;
import me.justin.modules.school.SchoolService;

import java.util.Arrays;
import java.util.List;

public class CommentService {
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
        List<School> schoolList = schoolService.findAll();
        for (School school : schoolList) {
            for (String[] strings : commentReaderReadCSV) {
                if (strings[0].contains(school.getName())){
                    school.addCount();
                }
            }
        }
    }

}
