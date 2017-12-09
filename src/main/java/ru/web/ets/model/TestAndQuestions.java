package ru.web.ets.model;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestAndQuestions{

/*    @OneToMany
    //@JoinTable(name="testAndQuestions")
    @JoinTable(name="testAndQuestions",
            joinColumns = @JoinColumn(name = "questionID",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id",
                    referencedColumnName = "questionID"))
    List<Question> questionList;*/

//    public TestAndQuestions(Integer id, Collection<Question> questionList) {
//        super(id);
//        this.questionList = questionList;
//    }
//
//    public Collection<Question> getQuestionList() {
//        return questionList;
//    }
//
//    public Collection<Question> getCopyQuestionList() {
//        List<Question> copy = new ArrayList<>();
//        questionList.forEach(x-> copy.add(new Question(x, x.getAnswerList())));
//        return copy;
//    }
//
//
//    public void setQuestionList(Collection<Question> questionList) {
//        this.questionList = questionList;
//    }
}
