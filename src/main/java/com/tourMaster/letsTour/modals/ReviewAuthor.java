package com.tourMaster.letsTour.modals;

public class ReviewAuthor {
    private String authorName;
    private String photoUrl;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public ReviewAuthor(String authorName, String photoUrl) {
        this.authorName = authorName;
        this.photoUrl = photoUrl;
    }

    public ReviewAuthor() {
    }

    @Override
    public String toString() {
        return "ReviewAuthor{" +
                "authorName='" + authorName + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
