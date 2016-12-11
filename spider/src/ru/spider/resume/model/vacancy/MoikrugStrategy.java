package ru.spider.resume.model.vacancy;

import ru.spider.resume.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements StrategyVacancy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        return new ArrayList<>();
    }
}
