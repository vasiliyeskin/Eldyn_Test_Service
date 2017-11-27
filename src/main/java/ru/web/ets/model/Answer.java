package ru.web.ets.model;

import java.awt.image.BufferedImage;

public class Answer extends AbstractBaseEntity {

    private String text;
    private BufferedImage image;
    private boolean isCorrect;

    public Answer(Integer id, String text, BufferedImage image) {
        super(id);
        this.text = text;
        this.image = image;
        this.isCorrect = false;
    }

    public Answer(Integer id, String text, BufferedImage image, boolean isCorrect) {
        super(id);
        this.text = text;
        this.image = image;
        this.isCorrect = isCorrect;
    }

    public String getText() {
        return text;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", image=" + image +
                ", isCorrect=" + isCorrect +
                ", id=" + id +
                '}';
    }
}
