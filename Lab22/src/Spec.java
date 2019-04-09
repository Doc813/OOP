public class Spec {
    private String name;
    private String artist;
    private String genre;
    private String album;

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    Spec(final String name, final String artist, final String genre, final String album) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
    }
}
