package ru.web.ets.model;


import javassist.bytecode.ByteArray;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Access(AccessType.FIELD)
@Entity
@Table(name="test")
public class Test implements BaseEntity {
    public static final int global_seqTest = 1;

    @Id
    @SequenceGenerator(name = "global_seqTest", sequenceName = "global_seqTest", allocationSize = 1, initialValue = global_seqTest)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seqTest")
    @Access(value = AccessType.PROPERTY)
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "image")
    private ByteArray image;

    @Column(name = "creationdatetime", columnDefinition = "timestamp default now()")
    @NotNull
    private Date creationdatetime = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    private User creator;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ByteArray getImage() {
        return image;
    }

    public void setImage(ByteArray image) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(id, test.id) &&
                Objects.equals(text, test.text) &&
                Objects.equals(image, test.image) &&
                Objects.equals(creationdatetime, test.creationdatetime) &&
                Objects.equals(creator, test.creator);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, creationdatetime, creator);
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", image=" + image +
                ", creationdatetime=" + creationdatetime +
                ", creator=" + creator +
                '}';
    }
}
