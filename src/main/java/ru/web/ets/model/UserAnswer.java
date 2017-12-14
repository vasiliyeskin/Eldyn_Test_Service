package ru.web.ets.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "answerID")
    @BatchSize(size = 200)
    private Answer answer;

    @Column(name = "isRight")
    private Boolean isRight;

    @Column(name = "testAnswer")
    private String testAnswer;

    public UserAnswer(){}

    public UserAnswer(Answer answer) {
        this.id = answer.getId();
        this.answer = answer;
        this.isRight = false;
        this.testAnswer = "";
    }

    public UserAnswer(Answer answer, Boolean isRight, String testAnswer) {
        this.id = answer.getId();
        this.answer = answer;
        this.isRight = isRight;
        this.testAnswer = testAnswer;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
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
}
