public class record {
    private String title, author, release_date, isbn, checkout;
    public record(){
        this.title = null;
        this.author = null;
        this.release_date = null;
        this.isbn = null;
        this.checkout = null;
    }
    public record(String title, String author, String release_date, String isbn, String checkout){
        this.title = title;
        this.author = author;
        this.release_date = release_date;
        this.isbn = isbn;
        this.checkout = checkout;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCheckout() {
        return checkout;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getRelease_date() {

        return release_date;
    }

    public String toString(){

        return title + " " + author + " " + release_date + " " + isbn + " " + checkout;
    }
}
