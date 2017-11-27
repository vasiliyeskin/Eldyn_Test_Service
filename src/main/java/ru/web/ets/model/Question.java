package ru.web.ets.model;

import java.awt.image.BufferedImage;
import java.util.List;

public class Question extends AbstractBaseEntity {

    private final String text;
    private final BufferedImage image;
    private final List<Answer> answerList;

    public Question(Integer id, String text, BufferedImage image, List<Answer> answerList) {
        super(id);
        this.text = text;
        this.image = image;
        this.answerList = answerList;
    }

    public Question(Question question, List<Answer> answerList) {
        super(question.getId());
        this.text = question.text;
        this.image = question.image;
        this.answerList = answerList;
    }

    public String getText() {
        return text;
    }

    public BufferedImage getImage() {
        return image;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }


    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", image=" + image +
                ", answerList=" + answerList +
                ", id=" + id +
                '}';
    }
}
