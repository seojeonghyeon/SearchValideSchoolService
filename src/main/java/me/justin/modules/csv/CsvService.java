package me.justin.modules.csv;


public class CsvService {
    private static final String COMMENTS_CSV_FILE_NAME = "target/classes/csv/comments.csv";
    private static final String HIGH_SCHOOL_LIST_FILE_NAME = "target/classes/csv/2023년도_전국고등학교_학교명.csv";
    private static final String MIDDLE_SCHOOL_LIST_FILE_NAME = "target/classes/csv/2023년도_전국중학교_학교명.csv";

    private CsvService(){}

    private static class CsvServiceHelper {
        private static final CsvService CSV_SERVICE = new CsvService();
    }

    public static CsvService getInstance(){
        return CsvServiceHelper.CSV_SERVICE;
    }

    public CsvReader createCommentsReader(){
        return CsvReader.createCsvReader(COMMENTS_CSV_FILE_NAME);
    }

    public CsvReader createHighSchoolReader(){
        return CsvReader.createCsvReader(HIGH_SCHOOL_LIST_FILE_NAME);
    }

    public CsvReader createMiddleSchoolReader(){
        return CsvReader.createCsvReader(MIDDLE_SCHOOL_LIST_FILE_NAME);
    }

}
