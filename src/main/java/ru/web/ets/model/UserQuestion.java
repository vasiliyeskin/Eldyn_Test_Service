package ru.web.ets.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;


@Access(AccessType.FIELD)
@Entity
@Table(name="UserQuestions")
public class UserQuestion implements BaseEntity {

    public static final int global_seqUserQuestion = 1;

    @Id
    @SequenceGenerator(name = "global_seqUserQuestion", sequenceName = "global_seqUserQuestion", allocationSize = 1, initialValue = global_seqUserQuestion)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seqUserQuestion")
    @Access(value = AccessType.PROPERTY)
    private Integer id;

    public UserQuestion() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usertestID", referencedColumnName = "id")
    private UserTest userTest;

    @Column(name = "creationdatetime", columnDefinition = "timestamp default now()")
    @NotNull
    private Date creationdatetime = new Date();

    @Column(name = "questionTestID")
    @NotNull
    private Integer questionTestID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userID")
    private User user;

    @OneToMany(mappedBy = "userQuestion", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<UserAnswer> userAnswersList;

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

    public UserTest getUserTest() {
        return userTest;
    }

    public void setUserTest(UserTest userTest) {
        this.userTest = userTest;
    }

    public Date getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Date creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    public Integer getQuestionTestID() {
        return questionTestID;
    }

    public void setQuestionTestID(Integer questionTestID) {
        this.questionTestID = questionTestID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserAnswer> getUserAnswersList() {
        return userAnswersList;
    }

    public void setUserAnswersList(List<UserAnswer> userAnswersList) {
        this.userAnswersList = userAnswersList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserQuestion that = (UserQuestion) o;
        return Objects.equals(userTest, that.userTest) &&
                Objects.equals(creationdatetime, that.creationdatetime) &&
                Objects.equals(questionTestID, that.questionTestID) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userTest, creationdatetime, questionTestID, user);
    }

    @Override
    public String toString() {
        return "UserQuestion{" +
                "id=" + id +
                ", userTestsID=" + userTest.getId() +
                ", creationdatetime=" + creationdatetime +
                ", questionTestID=" + questionTestID +
                ", userID=" + user.getId() +
                '}';
    }
}
