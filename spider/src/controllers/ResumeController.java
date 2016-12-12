package controllers;

import ru.spider.resume.beans.Resume;
import search.SuperJob;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author dmitry d
 */

@ManagedBean
@RequestScoped
public class ResumeController {

    private String start;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void searchResume() {
        FutureTask<List<Resume>> futureTask = new FutureTask<List<Resume>>(new SuperJob("Череповец"));
        new Thread(futureTask).start();
        try {
            if (futureTask.get() != null) {
                start="cool";
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
