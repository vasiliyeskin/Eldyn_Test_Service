package ru.web.ets.model;

import java.awt.image.BufferedImage;

public class Answer extends AbstractBaseEntity {

    private String text;
    private BufferedImage image;

    public Answer(Integer id, String text, BufferedImage image) {
        super(id);
        this.text = text;
        this.image = image;
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

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", image=" + image +
                ", id=" + id +
                '}';
    }
}
