package ru.web.ets.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Access(AccessType.FIELD)
@Entity
@Table(name="UserAnswers")
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

    @Column(name = "answerID")
    @NotNull
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
    @JoinColumn(name = "userQuestionID")
    private UserQuestion userQuestion;

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

    public UserQuestion getUserQuestion() {
        return userQuestion;
    }

    public void setUserQuestion(UserQuestion userQuestion) {
        this.userQuestion = userQuestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAnswer that = (UserAnswer) o;
        return Objects.equals(answerID, that.answerID) &&
                Objects.equals(creationdatetime, that.creationdatetime) &&
                Objects.equals(user, that.user) &&
                Objects.equals(userQuestion, that.userQuestion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(answerID, creationdatetime, user, userQuestion);
    }

    @Override
    public String toString() {
        return "UserAnswer{" +
                "id=" + id +
                ", answerID=" + answerID +
                ", isRight=" + isRight +
                ", testAnswer='" + testAnswer + '\'' +
                ", creationdatetime=" + creationdatetime +
                ", userID=" + user.getId() +
                ", userQuestionID=" + userQuestion.getId() +
                '}';
    }
}
