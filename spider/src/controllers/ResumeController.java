package controllers;

import search.StrategyResume;
import search.SuperJob;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author dmitry d
 */

@ManagedBean
@RequestScoped
public class ResumeController {

    public void getResume() {
        StrategyResume superJob = new SuperJob();
        superJob.getResumes("Череповец");
    }
}
