package ru.spider.resume;

import ru.spider.resume.model.ResumeStrategy;
import ru.spider.resume.model.vacancy.HHStrategy;
import ru.spider.resume.model.Model;
import ru.spider.resume.model.vacancy.MoikrugStrategy;
import ru.spider.resume.model.Provider;
import ru.spider.resume.view.HtmlView;

public class Aggregator {
    public static void main(String[] args) {
        HtmlView view = new HtmlView();
        Provider providerHH = new Provider(new HHStrategy());
        Provider providerMK = new Provider(new MoikrugStrategy());
        Provider providerSJ = new Provider(new ResumeStrategy());
        Model model = new Model(view, providerHH, providerMK, providerSJ);

        view.setController(new Controller(model));
        view.userCitySelectEmulationMethod();
    }
}
