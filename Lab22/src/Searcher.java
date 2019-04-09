public class Searcher {


    public Collection searchByArtist(final Catalog catalog, final String artist){
        Collection ans = new Collection();
        for(int i = 0; i<catalog.albumCount();i++){
            for(int j = 0; j<catalog.getAlbum(i).getSize();j++){
                if(catalog.getAlbum(i).get(j).getArtistName().equals(artist)){
                    ans.add(catalog.getAlbum(i).get(j));
                }
            }
        }
        return ans;
    }


    public Collection searchByGenre(final Catalog catalog, final String genre){
        Collection ans = new Collection();
        for(int i = 0; i<catalog.albumCount();i++){
            for(int j = 0; j<catalog.getAlbum(i).getSize();j++){
                Genre temp = catalog.getAlbum(i).get(i).getGenre();
                while(temp.getParent()!=null) {
                    if (temp.getName().equals(genre)) {
                        ans.add(catalog.getAlbum(i).get(j));
                    }
                    temp = temp.getParent();
                }
                if (temp.getName().equals(genre)) {
                    ans.add(catalog.getAlbum(i).get(j));
                }
            }
        }
        return ans;
    }


    public Collection searchByAlbum(final Catalog catalog, final String album){
        int i = 0;
        Collection ans = new Collection();
        while(i < catalog.albumCount()&& !album.equals(catalog.getAlbum(i).getName())){
            i++;
        }
        if(i!=catalog.albumCount())
            ans = catalog.getAlbum(i).getSongs();
        return ans;
    }


    public Collection searchByName(final Catalog catalog, final String song){
        Collection ans = new Collection();
        for(int i = 0; i<catalog.albumCount();i++){
            for(int j = 0; j<catalog.getAlbum(i).getSize();j++){
                if(catalog.getAlbum(i).get(j).getName().equals(song)){
                    ans.add(catalog.getAlbum(i).get(j));
                }
            }
        }
        return ans;
    }
}
