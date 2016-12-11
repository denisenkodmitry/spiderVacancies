package ru.spider.resume.model;

import ru.spider.resume.vo.Resume;

import java.util.List;

public interface StrategyResume {
    List<Resume> getResumes(String searchString);
}
