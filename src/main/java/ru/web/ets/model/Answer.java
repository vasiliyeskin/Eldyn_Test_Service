package ru.web.ets.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Access(AccessType.FIELD)
@Entity
@Table(name="answer")
public class Answer implements BaseEntity {

    public static final int global_seqAnswer = 1;

    @Id
    @SequenceGenerator(name = "global_seqAnswer", sequenceName = "global_seqAnswer", allocationSize = 1, initialValue = global_seqAnswer)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seqAnswer")
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

    public Answer() { }

    public Answer(Integer id, String text, byte[] image) {
        this.id = id;
        this.text = text;
        this.image = image;
    }

    public Answer(Integer id, String text, byte[] image, boolean b) {
        this.id = id;
        this.text = text;
        this.image = image;
    }

    public Answer(Answer answer) {
        this.id = answer.getId();
        this.text = answer.getText();
        this.image = answer.getImage();
    }

    public String getText() {
        return text;
    }

    public byte[] getImage() {
        return image;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", image=" + image +
                ", id=" + this.getId() +
                '}';
    }
}
