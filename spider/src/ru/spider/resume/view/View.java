package ru.spider.resume.view;

import ru.spider.resume.Controller;
import ru.spider.resume.beans.Resume;
import ru.spider.resume.beans.Vacancy;

import java.util.List;

public interface View {
    void update(List<Vacancy> vacancies);
    void updateResume(List<Resume> resumes);
    void setController(Controller controller);
}

