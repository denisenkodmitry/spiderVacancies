package ru.spider.resume.model.vacancy;

import ru.spider.resume.vo.Vacancy;

import java.util.List;

public interface StrategyVacancy {
    List<Vacancy> getVacancies(String searchString);
}
