package me.justin.modules;

import me.justin.modules.comment.CommentService;
import me.justin.modules.school.School;
import me.justin.modules.school.SchoolService;
import me.justin.modules.text.TextService;

import java.util.List;

public class MainController {

    private final CommentService commentService = CommentService.getInstance();
    private final SchoolService schoolService = SchoolService.getInstance();
    private final TextService textService = TextService.getInstance();

    private MainController(){}

    private static class MainControllerHelper {
        private static final MainController MAIN_CONTROLLER = new MainController();
    }

    public static MainController getInstance(){
        return MainControllerHelper.MAIN_CONTROLLER;
    }

    public void writeTextFile(){
        schoolService.saveSchoolList();
        commentService.extractSchoolNameFromCSV();
        List<School> schoolList = schoolService.findAllWithoutCountZero();
        textService.writeTextFile(schoolList);
    }

}
