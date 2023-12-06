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
        return name + "\t" + count;
    }
}
