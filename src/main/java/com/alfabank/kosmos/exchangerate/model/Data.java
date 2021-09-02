package com.alfabank.kosmos.exchangerate.model;

public class Data {
    private String id;
    private String title;
    private String rating;
    private String embed_url; //A URL used for embedding this GIF
    private String url; //The unique URL for this GIF

    public Data(String id, String title, String rating, String embed_url, String url) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.embed_url = embed_url;
        this.url = url;
    }

    public Data() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getEmbed_url() {
        return embed_url;
    }

    public void setEmbed_url(String embed_url) {
        this.embed_url = embed_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (getId() != null ? !getId().equals(data.getId()) : data.getId() != null) return false;
        if (getTitle() != null ? !getTitle().equals(data.getTitle()) : data.getTitle() != null) return false;
        if (getRating() != null ? !getRating().equals(data.getRating()) : data.getRating() != null) return false;
        if (getEmbed_url() != null ? !getEmbed_url().equals(data.getEmbed_url()) : data.getEmbed_url() != null)
            return false;
        return getUrl() != null ? getUrl().equals(data.getUrl()) : data.getUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getRating() != null ? getRating().hashCode() : 0);
        result = 31 * result + (getEmbed_url() != null ? getEmbed_url().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                ", embed_url='" + embed_url + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
