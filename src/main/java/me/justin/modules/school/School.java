package me.justin.modules.school;

import lombok.*;

@Builder @AllArgsConstructor
@Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
public class School {
    private String name;
    private Integer count;

    public static School createSchool(String name){
        return School.builder()
                .name(name)
                .count(0)
                .build();
    }

    public void addCount(){
        this.count++;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if(name.endsWith("고")) {
            return builder.append(name).append("등학교").append("\t").append(count).toString();
        }else if(name.endsWith("중")){
            return builder.append(name).append("학교").append("\t").append(count).toString();
        }
        return builder.append(name).append("\t").append(count).toString();
    }
}
