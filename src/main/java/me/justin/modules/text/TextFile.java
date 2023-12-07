package me.justin.modules.text;

import lombok.*;
import me.justin.modules.school.School;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TextFile {
    private String fileName;
    private String contents;

    public static TextFile createTextFile(String fileName, List<School> schoolList){
        TextFile textFile = new TextFile();
        textFile.setFileName(fileName);
        textFile.setContents(textFile.contents(schoolList));
        return textFile;
    }

    private String contents(List<School> schoolList){
        StringBuffer buffer = new StringBuffer();
        schoolList.forEach(school -> buffer.append(school.toString()+"\n"));
        return buffer.toString();
    }

    public void writeTextFile(){
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
