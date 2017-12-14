package ru.web.ets.model;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Access(AccessType.FIELD)
@Entity
@Table(name="test")
public class UserTest implements BaseEntity {
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
    @BatchSize(size = 200)
    private User creator;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="testAndQuestions",
            joinColumns = @JoinColumn(name = "testId",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "questionID",
                    referencedColumnName = "id"))
    private List<UserQuestion> questionsList;

    public UserTest() {
    }

    public UserTest(UserTest test) {
        this.text = test.getText();
        this.creationdatetime = test.getCreationdatetime();
        this.image = test.getImage();
        this.creator = test.getCreator();
        this.questionsList = test.questionsList;
    }

    public UserTest(Test test) {
        this.id = 0;
        this.text = test.getText();
        this.creationdatetime = test.getCreationdatetime();
        this.image = test.getImage();
        this.creator = test.getCreator();
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

    public List<UserQuestion> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<UserQuestion> questionsList) {
        this.questionsList = questionsList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTest test = (UserTest) o;
        return Objects.equals(id, test.id) &&
                Objects.equals(text, test.text) &&
                Objects.equals(creationdatetime, test.creationdatetime)/* &&
                Objects.equals(creator, test.creator)*/;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, creationdatetime);
    }

    @Override
    public String toString() {
        return "UserTest{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", creationdatetime=" + creationdatetime +
                ", creator=" + creator +
                ", questionsList=" + questionsList +
                '}';
    }
}

