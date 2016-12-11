package ru.spider.resume.model.vacancy;

import ru.spider.resume.beans.Vacancy;

import java.util.List;

public interface StrategyVacancy {
    List<Vacancy> getVacancies(String searchString);
}
