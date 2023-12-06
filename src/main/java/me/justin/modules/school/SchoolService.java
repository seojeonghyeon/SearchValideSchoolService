package me.justin.modules.school;

import java.util.List;

public class SchoolService {
    private final SchoolRepository schoolRepository = SchoolRepository.getInstance();

    private SchoolService(){}

    private static class SchoolServiceHelper {
        private static final SchoolService SCHOOL_SERVICE = new SchoolService();
    }

    public static SchoolService getInstance(){
        return SchoolServiceHelper.SCHOOL_SERVICE;
    }

    public School save(School school){
        return schoolRepository.save(school);
    }

    public boolean existByName(String name){
        return schoolRepository.existByName(name);
    }

    public School findByName(String name){
        return schoolRepository.findByName(name).orElse(null);
    }

    public List<School> findAllWithoutCountZero(){
        return schoolRepository.findAllWithoutCountZero();
    }

    public void saveSchoolList(List<String[]> schoolList){
        schoolList.forEach(strings -> save(School.createSchool(strings[0])));
    }

}