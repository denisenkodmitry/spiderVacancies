package ru.spider.resume;

import ru.spider.resume.model.Model;

public class Controller {

    private Model model;

    public Controller(Model model) {
        if (model == null) throw new IllegalArgumentException();
        this.model = model;
    }

    public void onCitySelect(String cityName) {
        model.selectCity(cityName);
    }

    public void onCitySelectResume(String cityName) {
        model.selectCityResume(cityName);
    }
}
