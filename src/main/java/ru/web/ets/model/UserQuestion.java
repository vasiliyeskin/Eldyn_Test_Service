package ru.web.ets.model;

import org.hibernate.annotations.BatchSize;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Access(AccessType.FIELD)
@Entity
@Table(name="question")
public class UserQuestion implements BaseEntity {

    public static final int global_seqQuestion = 1;

    @Id
    @SequenceGenerator(name = "global_seqQuestion", sequenceName = "global_seqTest", allocationSize = 1, initialValue = global_seqQuestion)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seqQuestion")
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

/*    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="answerAndQuestions",
            joinColumns = @JoinColumn(name = "questionID",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "answerID",
                    referencedColumnName = "id"))
    private List<Answer> answersList;*/

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "questionID")
    private List<UserAnswer> answersList;


    public UserQuestion(){}

    public UserQuestion(Question question) {
        this.id = question.getId();
        this.text = question.getText();
        this.creationdatetime = question.getCreationdatetime();
        this.image = question.getImage();
        this.creator = question.getCreator();
        if(this.answersList == null) this.answersList = new ArrayList<>();
        question.getAnswersList().forEach(
                x->this.answersList.add(new UserAnswer(x.getAnswer())));
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

    public List<UserAnswer> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<UserAnswer> answersList) {
        this.answersList = answersList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserQuestion question = (UserQuestion) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(text, question.text) &&
                Objects.equals(creationdatetime, question.creationdatetime) &&
                Objects.equals(creator, question.creator);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, creationdatetime, creator);
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", text='" + text +
                ", image=" + image +
                ", creationdatetime=" + creationdatetime +
                ", creator=" + creator.getId() +
                '}';
    }
}
