package model;

import java.sql.Timestamp;

public class FeedBack {
    private int ID;
    private int AccountID;
    private String Content;
    private String Image;
    private Timestamp CreatedAt;
    private boolean Status;

    // Constructors
    public FeedBack() {
    }

    public FeedBack(int ID, int AccountID, String Content, String Image, Timestamp CreatedAt, boolean Status) {
        this.ID = ID;
        this.AccountID = AccountID;
        this.Content = Content;
        this.Image = Image;
        this.CreatedAt = CreatedAt;
        this.Status = Status;
    }

    // Getters and Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public Timestamp getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Timestamp CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "FeedBack{" +
                "ID=" + ID +
                ", AccountID=" + AccountID +
                ", Content='" + Content + '\'' +
                ", Image='" + Image + '\'' +
                ", CreatedAt=" + CreatedAt +
                ", Status=" + Status +
                '}';
    }
}
