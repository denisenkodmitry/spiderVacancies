package ru.spider.resume.model;

import ru.spider.resume.model.vacancy.StrategyVacancy;
import ru.spider.resume.vo.Resume;
import ru.spider.resume.vo.Vacancy;

import java.util.List;

public class Provider {

    private StrategyVacancy strategyVacancy;
    private StrategyResume strategyResume;

    public Provider(StrategyVacancy strategyVacancy) {
        this.strategyVacancy = strategyVacancy;
    }

    public Provider(StrategyResume strategyResume) {
        this.strategyResume = strategyResume;
    }

    public List<Vacancy> getJavaVacancies(String searchString) {
        return strategyVacancy.getVacancies(searchString);
    }

    public List<Resume> getResumes(String searchString) {
        return strategyResume.getResumes(searchString);
    }

    public boolean isResume() {
        return this.strategyResume != null;
    }
}
