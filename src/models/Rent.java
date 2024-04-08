package models;

import java.text.SimpleDateFormat;
import java.util.Date;

/* Para armazenar as informações do aluguel de livros. */
public class Rent {
    private String acountId;
    private String bookTitle;
    private String bookGender;
    private Date rentDate;
    private Date returnDate;

    public Rent(String acountId, String bookTitle, String bookGender, Date rentDate, Date returnDate) {
        this.acountId = acountId;
        this.bookTitle = bookTitle;
        this.bookGender = bookGender;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public String getAcountId() {
        return acountId;
    }

    public void setAcountId(String acountId) {
        this.acountId = acountId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookGender() {
        return bookGender;
    }

    public void setBookGender(String bookGender) {
        this.bookGender = bookGender;
    }

    public String getRentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(rentDate);
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public String getReturnDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(returnDate);
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
