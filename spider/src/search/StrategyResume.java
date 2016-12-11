package search;

import ru.spider.resume.beans.Resume;

import java.util.List;

public interface StrategyResume {
    List<Resume> getResumes(String searchString);
}
