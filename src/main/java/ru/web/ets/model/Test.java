package ru.web.ets.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Access(AccessType.FIELD)
@Entity
@Table(name="test")
public class Test implements BaseEntity {
    public static final int global_seqTest = 1;

    @Id
    @SequenceGenerator(name = "global_seqTest", sequenceName = "global_seqTest", allocationSize = 1, initialValue = global_seqTest)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seqTest")
    @Access(value = AccessType.PROPERTY)
    private Integer id;

    @Column(name = "text")
    private String text;

    @Lob
    @Column(name = "image", columnDefinition = "oid")
    private byte[] image;

    @Column(name = "creationdatetime", columnDefinition = "timestamp default now()")
    @NotNull
    private Date creationdatetime = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creatorId")
    private User creator;

    @OneToMany(mappedBy = "test", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<QuestionForTest> questionsList;
//    @JoinTable(name="testAndQuestions",
//            joinColumns = @JoinColumn(name = "testId",
//                    referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "questionID",
//                    referencedColumnName = "id"))
//    private List<Question> questionsList;

//    @Column(name = "creatorId")
//    private Integer creatorId;

    public Test() {
    }

    public Test(Test test) {
        this.text = test.getText();
        this.creationdatetime = test.getCreationdatetime();
        this.image = test.getImage();
        this.creator = test.getCreator();
        this.questionsList = test.questionsList;
    }

    public Test(Integer id, String text, byte[] image, User user) {
        this.id = id;
        this.text = text;
        this.image = image;
        this.questionsList = new ArrayList<>();
        this.creator = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    public List<QuestionForTest> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<QuestionForTest> questionsList) {
        this.questionsList = questionsList;
    }

    public void addQuestion(Question question)
    {
        questionsList.add(new QuestionForTest(question, this, question.getCreator()));
    }

    //    public Integer getCreatorId() {
//        return creatorId;
//    }
//
//    public void setCreatorId(Integer creatorId) {
//        this.creatorId = creatorId;
//    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(id, test.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", creationdatetime=" + creationdatetime +
                ", creator=" + creator +
                ", questionsList=" + questionsList +
                '}';
    }

    public Question getQuestion(int qid) {
        for (QuestionForTest tq: questionsList) {
            if(tq.getQuestion().getId() == qid)return tq.getQuestion();
        }
        return null;
    }

    public void deleteQuestion(int qid) {
        for (Iterator<QuestionForTest> iterator = questionsList.listIterator(); iterator.hasNext();) {
            QuestionForTest qft = iterator.next();
            if(qft.getId() == qid) {iterator.remove(); break;}
        }
    }
}
