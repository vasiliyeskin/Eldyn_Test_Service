package ru.web.ets.model;

import java.awt.image.BufferedImage;
import java.util.List;

public class Question extends AbstractBaseEntity {

    private final String text;
    private final BufferedImage image;
    private final List<Answer> answerList;
    private final int correctAnswer;

    public Question(Integer id, String text, BufferedImage image, List<Answer> answerList, int correctAnswer) {
        super(id);
        this.text = text;
        this.image = image;
        this.answerList = answerList;
        this.correctAnswer = correctAnswer;
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

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", image=" + image +
                ", answerList=" + answerList +
                ", correctAnswer=" + correctAnswer +
                ", id=" + id +
                '}';
    }
}
