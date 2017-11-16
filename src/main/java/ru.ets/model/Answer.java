package ru.ets.model;

import java.awt.image.BufferedImage;

public class Answer extends AbstractBaseEntity {

    private final String text;
    private final BufferedImage image;

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

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", image=" + image +
                ", id=" + id +
                '}';
    }
}
