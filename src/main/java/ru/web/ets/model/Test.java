package ru.web.ets.model;

import java.util.Collection;

public class Test extends AbstractBaseEntity {
    Collection<Question> questionList;

    public Test(Integer id, Collection<Question> questionList) {
        super(id);
        this.questionList = questionList;
    }

    public Collection<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(Collection<Question> questionList) {
        this.questionList = questionList;
    }
}
