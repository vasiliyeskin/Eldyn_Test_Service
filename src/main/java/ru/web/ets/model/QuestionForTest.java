package ru.web.ets.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Access(AccessType.FIELD)
@Entity
@Table(name="testAndQuestions")
public class QuestionForTest implements BaseEntity {

    public static final int  global_seqTQ = 1;

    @Id
    @SequenceGenerator(name = "global_seqTQ", sequenceName = "global_seqTQ", allocationSize = 1, initialValue = global_seqTQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seqTQ")
    @Access(value = AccessType.PROPERTY)
    private Integer id;

    public QuestionForTest() {
    }

    public QuestionForTest(Question question, Test test) {
        this.question = question;
        this.test = test;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "testId", referencedColumnName = "id")
    private Test test;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "questionID")
    private Question question;

    @Column(name = "creationdatetime", columnDefinition = "timestamp default now()")
    @NotNull
    private Date creationdatetime = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creatorId", referencedColumnName = "id")
    private User creator;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Date getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Date creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionForTest)) return false;

        QuestionForTest that = (QuestionForTest) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        return getCreator() != null ? getCreator().equals(that.getCreator()) : that.getCreator() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCreator() != null ? getCreator().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuestionForTest{" +
                "id=" + id +
                ", question=" + question +
                ", creationdatetime=" + creationdatetime +
                ", creator=" + creator +
                '}';
    }

    /*    @OneToMany
    //@JoinTable(name="testAndQuestions")
    @JoinTable(name="testAndQuestions",
            joinColumns = @JoinColumn(name = "questionID",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id",
                    referencedColumnName = "questionID"))
    List<Question> questionList;*/

//    public QuestionForTest(Integer id, Collection<Question> questionList) {
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
