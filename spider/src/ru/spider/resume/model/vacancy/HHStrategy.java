package ru.spider.resume.model.vacancy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.spider.resume.helpers.JsoupHelper;
import ru.spider.resume.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HHStrategy implements StrategyVacancy {

    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
    private static final String userAgent = "Chrome/49.0.2623.87";
    private static final int timeout = 60 * 1000;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        int nom = 0;
        Long time = new Date().getTime();
        try
        {

            while (true)
            {
                Document doc = JsoupHelper.getDocument(URL_FORMAT, userAgent, timeout, searchString, nom++); // получили документ и увеличили счетчик
                Elements elements = (Elements) doc.select("[data-qa=vacancy-serp__vacancy]");
                if (elements.size() == 0) break; // если нет вакансий прекращаю
                for (Element element : elements)
                {
                    // title
                    Element titleElement = element.select("[data-qa=vacancy-serp__vacancy-title]").first();
                    String title = titleElement.text();
                    // salary
                    Element salaryElement = element.select("[data-qa=vacancy-serp__vacancy-compensation]").first();
                    String salary = "";
                    if (salaryElement != null)
                    {
                        salary = salaryElement.text();
                    }
                    // city
                    String city = element.select("[data-qa=vacancy-serp__vacancy-address]").first().text();
                    // company
                    Element companyNameElement = element.select("[data-qa=vacancy-serp__vacancy-employer]").first();
                    String companyName = companyNameElement != null ? companyNameElement.text() : "";
                    // site
                    String siteName = "http://hh.ua/";
                    // url
                    String url = titleElement.attr("href");
                    // add vacancy to the list
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(title);
                    vacancy.setSalary(salary);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(companyName);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(url);
                    vacancies.add(vacancy);
                }
            }
            System.out.println((new Date().getTime() - time) / 1000);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println((new Date().getTime() - time) / 1000);
        }
        return vacancies;
    }

}
