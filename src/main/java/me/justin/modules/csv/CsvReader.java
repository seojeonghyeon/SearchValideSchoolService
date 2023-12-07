package me.justin.modules.csv;

import lombok.*;
import me.justin.modules.comment.Comment;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor @Builder
public class CsvReader {

    private String fileName;
    private int index;
    private List<String> readCSV;

    public static CsvReader createCsvReader(String fileName) {
        CsvReader csvReader = new CsvReader();
        csvReader.setFileName(fileName);
        csvReader.setReadCSV(new ArrayList<>());
        try {
            csvReader.addCSVStringList();
        }catch (IOException e){
            e.printStackTrace();
        }
        return csvReader;
    }

    public void addCSVStringList() throws IOException{
        if(!hasText(fileName)){
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileName), StandardCharsets.UTF_8));
        boolean isSchoolFile = fileName.endsWith("학교_학교명.csv");

        if(isSchoolFile){
            addLineContentsAboutSchool(bufferedReader);
            return;
        }
        addLineContentsAboutComments(bufferedReader);
    }

    private void addLineContentsAboutSchool(BufferedReader bufferedReader) throws IOException{
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            String replacement = "";
            String regex = fileName.endsWith("고등학교_학교명.csv") ? "등학교" : "학교";
            String lineContents = line.replaceAll(regex, replacement);
            this.readCSV.add(lineContents);
        }
    }
    private void addLineContentsAboutComments(BufferedReader bufferedReader) throws IOException{
        Comment comment = Comment.createComment();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            translateComments(line, comment);
        }
    }

    private void translateComments(String line, Comment comment){
        String replacement = "";
        String doubleQuotes = "\"";
        String regex = "[^가-힣\\s\"]";
        boolean isExistTwoDoubleQuotesInOneLine = line.contains("\"") && line.codePoints().filter(ch -> ch == doubleQuotes.codePointAt(0)).count() % 2 == 0;

        if(isExistTwoDoubleQuotesInOneLine){
            regex = "[^가-힣\s]";
        }
        String str = line.replaceAll(regex, replacement);

        if(isExistTwoDoubleQuotesInOneLine){
            this.readCSV.add(str);
            comment.initStringBuffer();
            comment.setFalseExistDoubleQuotes();
        }else if(str.contains(doubleQuotes) && !comment.isExistDoubleQuotes()){
            comment.getStringBuffer().append(str);
            comment.setTrueExistDoubleQuotes();
            return;
        }else if(str.contains(doubleQuotes) && comment.isExistDoubleQuotes()){
            comment.getStringBuffer().append(str);
            comment.setFalseExistDoubleQuotes();
            str = comment.getStringBuffer().toString();
            comment.initStringBuffer();
        }else if(!str.contains(doubleQuotes) && comment.isExistDoubleQuotes()){
            comment.getStringBuffer().append(str);
            str = comment.getStringBuffer().toString();
            return;
        }

        this.readCSV.add(str);
        comment.initStringBuffer();
    }


    private static boolean hasText(String str){
        return str != null && !str.isEmpty() && containsText(str);
    }

    private static boolean containsText(CharSequence str){
        int strLen = str.length();

        for(int i=0; i<strLen; ++i){
            if(!Character.isWhitespace(str.charAt(i))){
                return true;
            }
        }
        return false;
    }
}
