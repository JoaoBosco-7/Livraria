package models;

public class Book {

    private String title;
    private String author;
    private String gender;
    private String vol;

    public Book(String title, String author, String gender, String vol) {

        this.title = title;
        this.author = author;
        this.gender = gender;
        this.vol = vol;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }
}