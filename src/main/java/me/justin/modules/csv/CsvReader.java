package me.justin.modules.csv;

import lombok.*;
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
    private List<String[]> readCSV;

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
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            String replacement = fileName.endsWith("_학교명.csv") ? "" : " ";
            String[] lineContents = line
                    .replaceAll("[^a-zA-Z0-9가-힣\\s]", replacement)
                    .split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            this.readCSV.add(lineContents);
        }
    }

    public String[] nextRead(){
        if(readCSV.size() == index){
            return null;
        }
        return readCSV.get(index++);
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
