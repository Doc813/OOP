public class Song {
    private String name;
    private String artist;
    private Genre genre;

    Song(final String name, final String artist, final Genre genre) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getArtistName() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }


    public boolean compare(final String word) {
        boolean ans = StringUtils.compare(name, word);
        if (StringUtils.compare(artist, word)) {
            ans = true;
        } else
        if (StringUtils.compare(genre.getName(), word)) {
            ans = true;
        } else
        if (genre.getParent() != null &&
                (StringUtils.compare(genre.getParent().getName(), word))) {
                ans = true;
        }
        return ans;
    }

    public boolean compare(final Spec spec) {
        boolean ans = true;
        if (!StringUtils.compare(name, spec.getName())) {
            ans = false;
        } else
        if (!StringUtils.compare(artist, spec.getArtist())) {
            ans = false;
        } else
        if (!StringUtils.compare(genre.getName(), spec.getGenre())) {
            if (genre.getParent() != null) {
                if (!StringUtils.compare(genre.getParent().getName(), spec.getGenre())) {
                    ans = false;
                }
            } else
                ans = false;
        }
        return ans;
    }
}
