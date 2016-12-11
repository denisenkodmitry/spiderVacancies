package ru.spider.resume.view;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.spider.resume.Controller;
import ru.spider.resume.beans.Resume;
import ru.spider.resume.beans.Vacancy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {

    private final String filePathVacancies = new File("").getAbsolutePath() + "\\spider\\src\\" + this.getClass().getPackage().getName().replace('.', '\\') + "\\vacancies.html";
    private final String filePathResumes = new File("").getAbsolutePath() + "\\spider\\src\\" + this.getClass().getPackage().getName().replace('.', '\\') + "\\resumes.html";
    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies), filePathVacancies);
    }

    @Override
    public void updateResume(List<Resume> resumes) {
        updateFile(getUpdatedFileContentResume(resumes), filePathResumes);
    }


    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelectResume("Череповец");
        controller.onCitySelect("cherepovets");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        String fileContent;
        try {
            Document doc = getDocument(filePathVacancies);
            Element templateElement = doc.select(".template").first();

            Element patternElement = templateElement.clone();
            patternElement.removeAttr("style");
            patternElement.removeClass("template");

            doc.select("tr[class=vacancy]").remove();

            for (Vacancy vacancy : vacancies) {
                Element newVacancyElement = patternElement.clone();
                newVacancyElement.getElementsByClass("city").first().text(vacancy.getCity());
                newVacancyElement.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                newVacancyElement.getElementsByClass("salary").first().text(vacancy.getSalary());
                Element link = newVacancyElement.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                templateElement.before(newVacancyElement.outerHtml());
            }
            fileContent = doc.html();
        } catch (IOException e) {
            e.printStackTrace();
            fileContent = "Some exception occurred";
        }
        return fileContent;
    }

    private String getUpdatedFileContentResume(List<Resume> resumes) {
        String fileContent;
        try {
            Document doc = getDocument(filePathResumes);
            Element templateElement = doc.select(".template").first();

            Element patternElement = templateElement.clone();
            patternElement.removeAttr("style");
            patternElement.removeClass("template");

            doc.select("tr[class=resumes]").remove();

            for (Resume resume : resumes) {
                Element newResumeElement = patternElement.clone();
                newResumeElement.getElementsByClass("id").first().text(resume.getId().toString());
                newResumeElement.getElementsByClass("payment").first().text(resume.getPayment());
                newResumeElement.getElementsByClass("age").first().text(resume.getAge());

                Element link = newResumeElement.getElementsByTag("a").first();
                link.text(resume.getTitle());
                link.attr("href", resume.getUrl());
                templateElement.before(newResumeElement.outerHtml());
            }
            fileContent = doc.html();
        } catch (IOException e) {
            e.printStackTrace();
            fileContent = "Some exception occurred";
        }
        return fileContent;
    }

    private void updateFile(String fileContent, String filePath) {
        try {
            BufferedWriter fWriter = new BufferedWriter(new FileWriter(filePath));
            fWriter.write(fileContent);
            fWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument(String filePath) throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
