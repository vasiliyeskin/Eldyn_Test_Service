package ru.web.ets.dto;

import ru.web.ets.model.AbstractBaseEntity;
import ru.web.ets.model.Answer;
import ru.web.ets.model.Question;
import ru.web.ets.model.User;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class UserQuestion extends AbstractBaseEntity {

    private String text;
    private BufferedImage image;
    private List<UserAnswer> answerList;

    public UserQuestion(Integer id, String text, BufferedImage image, List<UserAnswer> answerList) {
        super(id);
        this.text = text;
        this.image = image;
        this.answerList = answerList;
    }

    public UserQuestion(Question question) {
        super(question.getId());
        this.text = question.getText();
        this.image = question.getImage();
        this.answerList = new ArrayList<>();
        question.getAnswerList().forEach(x->this.answerList.add(new UserAnswer(x)));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public List<UserAnswer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<UserAnswer> answerList) {
        this.answerList = answerList;
    }

    public List<UserAnswer> getCopyAnswerList() {
        List<UserAnswer> copy = new ArrayList<>();
        answerList.forEach(x-> copy.add(new UserAnswer(x)));
        return copy;
    }
}
