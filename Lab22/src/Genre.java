public class Genre {
    private String name;
    private Genre parent;
    Genre(final String name, final Genre parent){
        this.parent = parent;
        this.name = name;
    }
    Genre(final String name){
        this.name = name;
        parent = null;
    }
    public String getName(){
        return name;
    }
    public Genre getParent(){
        return parent;
    }
}
