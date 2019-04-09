import java.util.ArrayList;
import java.util.List;

public class Album {
    private String name;
    private final List<Song> songs = new ArrayList<>();

    Album(String name) {
        this.name = name;
    }

    public int getSize() {
        return songs.size();
    }

    public void add(Song song) {
        songs.add(song);
    }

    public Song get(int i) {
        return songs.get(i);
    }

    public String getName() {
        return name;
    }

    public Collection getSongs() {
        Collection ans = new Collection();
        for (int i = 0; i < songs.size(); i++) {
            ans.add(songs.get(i));
        }
        return ans;
    }

    public Collection findSong(String string) {
        if (StringUtils.compare(name, string))
            return this.getSongs();
        else {
            Collection ans = new Collection();
            for (int i = 0; i < this.getSize(); ++i) {
                if (this.get(i).compare(string)) {
                    ans.add(this.get(i));
                }
            }
            return ans;
        }
    }

    public Collection findSong(Spec spec) {
        Collection ans = new Collection();
        if (StringUtils.compare(name, spec.getAlbum())) {
            for (int i = 0; i < this.getSize(); ++i) {
                if (this.get(i).compare(spec)) {
                    ans.add(this.get(i));
                }
            }
        }

        return ans;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(songs.get(0).getName());
        for (int i = 0; i < songs.size(); i++) {
            s.append(" ").append(songs.get(i).getName());
        }
        return s.toString();
    }

}