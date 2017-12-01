package ru.web.ets.dto;

import ru.web.ets.model.Answer;

import java.awt.image.BufferedImage;

public class UserAnswer extends Answer {

    private boolean userChoose;

    public UserAnswer(Integer id, String text, BufferedImage image) {
        super(id, text, image);
    }

    public UserAnswer(Integer id, String text, BufferedImage image, boolean isCorrect) {
        super(id, text, image, isCorrect);
    }

    public UserAnswer(Integer id, String text, BufferedImage image, boolean isCorrect, boolean userChoose) {
        super(id, text, image, isCorrect);
        this.userChoose = userChoose;
    }

    public UserAnswer(Answer answer) {
        super(answer);
    }

    public UserAnswer(Answer answer, boolean userChoose) {
        super(answer);
        this.userChoose = userChoose;
    }

    public void setUserChoose(boolean userChoose) {
        this.userChoose = userChoose;
    }

    public boolean isUserChoose() {
        return userChoose;
    }
}
