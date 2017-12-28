package ru.web.ets.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Access(AccessType.FIELD)
@Entity
@Table(name="UserTests")
public class UserTest implements BaseEntity {
    public static final int global_seqUserTest = 1;

    @Id
    @SequenceGenerator(name = "global_seqUserTest", sequenceName = "global_seqUserTest", allocationSize = 1, initialValue = global_seqUserTest)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seqUserTest")
    @Access(value = AccessType.PROPERTY)
    private Integer id;

    @Column(name = "testID")
    @NotNull
    private Integer testID;

    @Column(name = "creationdatetime", columnDefinition = "timestamp default now()")
    @NotNull
    private Date creationdatetime = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userID")
    private User user;

    @OneToMany(mappedBy = "userTest", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<UserQuestion> userQuestions;

    public UserTest() {
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

    public Integer getTestID() {
        return testID;
    }

    public void setTestID(Integer testID) {
        this.testID = testID;
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

    public List<UserQuestion> getUserQuestions() {
        return userQuestions;
    }

    public void setUserQuestions(List<UserQuestion> userQuestions) {
        this.userQuestions = userQuestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTest userTest = (UserTest) o;
        return Objects.equals(testID, userTest.testID) &&
                Objects.equals(creationdatetime, userTest.creationdatetime) &&
                Objects.equals(user, userTest.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(testID, creationdatetime, user);
    }

    @Override
    public String toString() {
        return "UserTest{" +
                "id=" + id +
                ", testID=" + testID +
                ", creationdatetime=" + creationdatetime +
                ", userID=" + user.getId() +
                '}';
    }
}
