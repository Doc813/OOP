import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private final List<Genre> allGenres = new ArrayList<>();
    private final List<Album> albums = new ArrayList<>();

    public Album getAlbum(int i) {
        return albums.get(i);
    }


    public Genre getGenre(int i) {
        return allGenres.get(i);
    }


    public int albumCount() {
        return albums.size();
    }


    public int genreCount() {
        return allGenres.size();
    }


    public void addGenre(final String name) {

        int i = 0;
        try {
            i = 0;
            if (allGenres.size() != 0) {
                while (i < allGenres.size() && !name.equals(allGenres.get(i).getName())) {
                    i++;
                }
                if (i != allGenres.size())
                    throw new RuntimeException("This Genre is already Exists");
            }
        } catch (RuntimeException e) {
            System.out.println("Exception: " + e.toString());
        }
        allGenres.add(new Genre(name));
    }


    public void addGenre(final String name, final String parent) {
        Genre tempParent = null;
        for (Genre genre : allGenres) {
            if (genre.getName().equals(name)) {
                throw new RuntimeException("Already exists");
            }
            if (genre.getName().equals(parent)) {
                tempParent = genre;
            }
        }

        if (tempParent == null) {
            throw new RuntimeException("No such parent");
        }

        int i = 0;
        try {
            i = 0;
            if (allGenres.size() != 0) {
                while (i < allGenres.size() && !name.equals(allGenres.get(i).getName())) {
                    i++;
                }
                if (i != allGenres.size())
                    throw new RuntimeException("This Genre is already Exists");
            } else
                throw new RuntimeException("Parent Genre doesn't exist");
            i = 0;
            while (i < allGenres.size() && !parent.equals(allGenres.get(i).getName())) {
                i++;
            }
            if (i == allGenres.size())
                throw new RuntimeException("Parent Genre doesn't exist");


            tempParent = allGenres.get(i);
            Genre temp = new Genre(name, tempParent);
            allGenres.add(temp);
        } catch (RuntimeException e) {
            System.out.println("Exception: " + e.toString());
        }
    }


    public void addAlbum(final String name) {
        try {
            int i = 0;
            while (i < albums.size() && !name.equals(albums.get(i).getName())) {
                i++;
            }
            if (i != albums.size()) {
                throw new RuntimeException("This album is already exists");
            }
        } catch (RuntimeException e) {
            System.out.println("Exception: " + e.toString());
        }
        albums.add(new Album(name));
    }


    public void fillAlbum(final String album, final String song, final String genre, final String artist) {
        int i = 0;
        try {
            while (i < albums.size() && !album.equals(albums.get(i).getName())) {
                i++;
            }
            if (i == albums.size())
                throw new RuntimeException("This album doesn't exist");
            int j = i;
            i = 0;
            while (i < allGenres.size() && !genre.equals(allGenres.get(i).getName())) {
                i++;
            }
            if (i == allGenres.size()) {
                throw new RuntimeException("This genre doesn't exist");
            }
            Genre tempGenre = allGenres.get(i);
            i = 0;
            while (i < albums.get(j).getSize() && !song.equals(albums.get(j).get(i).getName())) {
                i++;
            }
            if (i != albums.get(j).getSize()) {
                throw new RuntimeException("This song is already exists");
            }
            albums.get(j).add(new Song(song, artist, tempGenre));
        } catch (RuntimeException e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    public Collection find(String request) {
        Collection ans = new Collection();
        String tempString = "";
        String[] parts = request.split(" ");
        for (int j = 0; j < request.length(); ++j) {
            if (request.charAt(j) != ' ') {
                tempString = tempString + request.charAt(j);
            } else {
                for (int i = 0; i < this.albumCount(); ++i) {
                    ans.add(this.getAlbum(i).findSong(tempString));
                }
                tempString = "";
            }
        }
        for (int i = 0; i < this.albumCount(); ++i) {
            ans.add(this.getAlbum(i).findSong(tempString));
        }

        return ans;
    }

    public Collection find(String name, String artist, String genre, String album) {
        Spec spec = new Spec(name, artist, genre, album);
        Collection ans = new Collection();
        for (int i = 0; i < this.albumCount(); ++i) {
            ans.add(this.getAlbum(i).findSong(spec));
        }

        return ans;
    }
}