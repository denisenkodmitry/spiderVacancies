package ru.spider.resume.model;


import ru.spider.resume.view.View;
import ru.spider.resume.beans.Resume;
import ru.spider.resume.beans.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) {

        if (view == null || providers == null || providers.length == 0) throw new IllegalArgumentException();
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city) {
        List<Vacancy> vacancies = new ArrayList<>();
        for (Provider provider : providers) {
            if (!provider.isResume()) {
                vacancies.addAll(provider.getJavaVacancies(city));
            }
        }
        view.update(vacancies);
    }

    public void selectCityResume(String city) {
        List<Resume> resumes = new ArrayList<>();
        for (Provider provider : providers) {
            if (provider.isResume()) {
                resumes.addAll(provider.getResumes(city));
            }
        }
        view.updateResume(resumes);
    }
}
