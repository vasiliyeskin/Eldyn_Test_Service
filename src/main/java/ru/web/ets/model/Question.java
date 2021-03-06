package ru.web.ets.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Access(AccessType.FIELD)
@Entity
@Table(name = "question")
public class Question implements BaseEntity {
    public static final int global_seqQuestion = 1;

    @Id
    @SequenceGenerator(name = "global_seqQuestion", sequenceName = "global_seqQuestion", allocationSize = 1, initialValue = global_seqQuestion)
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
    private User creator;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TeacherAnswer> answersList;

//    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @Fetch(value = FetchMode.SUBSELECT)
//    private List<UserAnswer> userAnswersList;



/*    @JoinTable(name="answerAndQuestions",
            joinColumns = @JoinColumn(name = "questionID",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "answerID",
                    referencedColumnName = "id"))*/

/*    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name="testAndQuestions",
            joinColumns = @JoinColumn(name = "questionID",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "testId",
                    referencedColumnName = "id"))
    private Test test;*/


    public Question(){}

    public Question(Question question) {
        this.text = question.getText();
        this.creationdatetime = question.getCreationdatetime();
        this.image = question.getImage();
        this.creator = question.getCreator();
    }

    public Question(Integer id, String text, byte[] image, User user) {
        this.id = id;
        this.text = text;
        this.image = image;
        this.answersList = new ArrayList<>();
        this.creator = user;
    }

    public void addAnswer(Answer answer)
    {
        answersList.add(new TeacherAnswer(answer, this, false, answer.getCreator()));
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

    public List<TeacherAnswer> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<TeacherAnswer> answersList) {
        this.answersList = answersList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", creationdatetime=" + creationdatetime +
                ", creator=" + creator +
                ", answersList=" + answersList +
                '}';
    }
}
