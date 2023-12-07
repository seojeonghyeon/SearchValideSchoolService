package me.justin.modules.text;


import me.justin.modules.school.School;
import java.util.List;

public class TextService {

    private final static String TEXT_FILE_NAME = "target/classes/text/result.txt";

    private TextService(){}

    private static class TextServiceHelper {
        private static final TextService TEXT_SERVICE = new TextService();
    }

    public static TextService getInstance(){
        return TextServiceHelper.TEXT_SERVICE;
    }

    public void writeTextFile(List<School> schoolList){
        TextFile textFile = TextFile.createTextFile(TEXT_FILE_NAME, schoolList);
        textFile.writeTextFile();
    }

}
