import java.util.ArrayList;
import java.util.List;

public class Collection {
    private final List<Song> songs = new ArrayList<>();

    public void add(Song song) {
        songs.add(song);
    }

    public Song get(int i) {
        return songs.get(i);
    }

    public int getSize() {
        return songs.size();
    }

    public void add(Collection other) {
        for (Song otherSong :
                other.songs) {
            boolean exists = false;
            for (Song song: songs){
                if (otherSong.equals(song)){
                    exists = true;
                    break;
                }
            }
            if (!exists){
                songs.add(otherSong);
            }
        }
    }

    @Override
    public String toString() {
        if (this.getSize() == 0) {
            return "";
        }
        String s = songs.get(0).getArtistName() + " \"" + songs.get(0).getName() + "\"";
        int i = 1;
        while (i < songs.size()) {
            s = s + "\n" + songs.get(i).getArtistName() + " \"" + songs.get(i).getName() + "\"";
            i++;
        }
        return s;
    }


}
