package ru.web.ets.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Access(AccessType.FIELD)
@Entity
@Table(name="UserAnswer")
public class UserAnswer implements BaseEntity {
    public static final int  global_seqUserAnswer = 1;


    @Id
    @SequenceGenerator(name = "global_seqUserAnswer", sequenceName = "global_seqUserAnswer", allocationSize = 1, initialValue = global_seqUserAnswer)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seqUserAnswer")
    @Access(value = AccessType.PROPERTY)
    private Integer id;

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

//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
//    @JoinColumn(name = "answerID")
//    private Answer answer;

    @Column(name = "answerID")
    private Integer answerID;

    @Column(name = "isRight")
    private Boolean isRight;

    @Column(name = "testAnswer")
    private String testAnswer;

    @Column(name = "creationdatetime", columnDefinition = "timestamp default now()")
    @NotNull
    private Date creationdatetime = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "questionId", referencedColumnName = "id")
    private Question question;

    public UserAnswer(){}

    public UserAnswer(Integer id) {
        this.answerID = id;
        this.isRight = false;
        this.testAnswer = "";
    }

    public UserAnswer(Integer answerID, Boolean isRight, String testAnswer, User user, Question question) {
       // this.id = answer.getId();
       // this.answer = answer;
        this.answerID = answerID;
        this.isRight = isRight;
        this.testAnswer = testAnswer;
        this.user = user;
        this.question = question;
    }
/*    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }*/

    public Integer getAnswerID() {
        return answerID;
    }

    public void setAnswerID(Integer answerID) {
        this.answerID = answerID;
    }

    public Boolean getRight() {
        return isRight;
    }

    public void setRight(Boolean right) {
        isRight = right;
    }

    public String getTestAnswer() {
        return testAnswer;
    }

    public void setTestAnswer(String testAnswer) {
        this.testAnswer = testAnswer;
    }

    public Date getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Date creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAnswer that = (UserAnswer) o;
        return Objects.equals(id, that.id) &&
              //  Objects.equals(answer, that.answer) &&
                Objects.equals(creationdatetime, that.creationdatetime) &&
                Objects.equals(user, that.user) &&
                Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, creationdatetime, user, question);
    }

    @Override
    public String toString() {
        return "UserAnswer{" +
                "id=" + id +
                ", answerID=" + answerID +
                ", isRight=" + isRight +
                ", testAnswer='" + testAnswer + '\'' +
                ", creationdatetime=" + creationdatetime +
                ", user=" + user +
                ", question=" + question +
                '}';
    }
}
