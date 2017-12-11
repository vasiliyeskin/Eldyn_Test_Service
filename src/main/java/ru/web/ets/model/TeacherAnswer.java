package ru.web.ets.model;


import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Access(AccessType.FIELD)
@Entity
@Table(name="answerAndQuestions")
public class TeacherAnswer implements BaseEntity{

    public static final int  global_seqATQ = 1;


    @Id
    @SequenceGenerator(name = "global_seqATQ", sequenceName = "global_seqATQ", allocationSize = 1, initialValue = global_seqATQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seqATQ")
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


    @Column(name = "creationdatetime", columnDefinition = "timestamp default now()")
    @NotNull
    private Date creationdatetime = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creatorId")
    private User creator;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeacherAnswer)) return false;

        TeacherAnswer that = (TeacherAnswer) o;

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
        return "TeacherAnswer{" +
                "id=" + id +
                ", answer=" + answer +
                ", isRight=" + isRight +
                ", testAnswer='" + testAnswer + '\'' +
                ", creationdatetime=" + creationdatetime +
                ", creator=" + creator +
                '}';
    }
}
