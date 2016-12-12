package search;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.spider.resume.helpers.JsoupHelper;
import ru.spider.resume.beans.Resume;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class SuperJob implements StrategyResume, Callable {
    private static final String URL_FORMAT = "https://www.superjob.ru/resume/search_resume.html" +
            "?sbmit=1&keywords[0][keys]=%s&catalogues[0]=33&page=%s";
    private static final String userAgent = "Chrome/49.0.2623.87";
    private static final int timeout = 60 * 1000;

    private String stringSearch;

    public SuperJob(String stringSearch) {
        this.stringSearch = stringSearch;
    }

    @Override
    public List<Resume> getResumes(String searchString) {
        List<Resume> resumes = new ArrayList<>();
        int nom = 0;
        try
        {
            while (true)
            {
                Document doc = JsoupHelper.getDocument(URL_FORMAT, userAgent, timeout, searchString, nom++); // получили документ и увеличили счетчик
                Elements elements = doc.select(".ResumeListElement");
                if (elements.size() == 0) break; // если нет резюме прекращаю
                for (Element element : elements)
                {
                    // id
                    Long id = Long.valueOf(element.id());
                    // salary
                    Element titleElement = element.select(".ResumeListElement_post").first();
                    String title = titleElement.text();

                    String url = titleElement.getElementsByTag("a").attr("href");

                    Document resumeDoc = JsoupHelper.getDocument(url, userAgent, timeout);
                    String payment = resumeDoc.select(".ResumeMainHR_payment").text();
                    String[] contentResume = resumeDoc.select(".m_linehieght").text().split(",");

                    String age = contentResume[0].split(" ")[0];
                    Resume resume = new Resume();
                    resume.setId(id);
                    resume.setTitle(title);
                    resume.setUrl(url);
                    resume.setPayment(payment);
                    resume.setAge(age);
                    resumes.add(resume);
                }
                if (resumes.size() == 25) {
                    break;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return resumes;
    }

    @Override
    public List<Resume> call() throws Exception {
        return getResumes(stringSearch);
    }
}
