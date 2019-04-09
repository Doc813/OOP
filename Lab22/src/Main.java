import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        catalog.addGenre("Rock");

        catalog.addGenre("Country");

        catalog.addGenre("Metal", "Rock");

        catalog.addGenre("Alternative rock","Rock");

        catalog.addAlbum("Smoke and mirrors");

        catalog.fillAlbum("Smoke and mirrors", "Gold", "Alternative rock", "Imagine Dragons");

        catalog.fillAlbum("Smoke and mirrors", "Shots", "Alternative rock", "Imagine Dragons");

        catalog.fillAlbum("Smoke and mirrors", "Smoke and mirrors", "Alternative rock", "Imagine Dragons");

        catalog.addAlbum("Gold dust woman");

        catalog.fillAlbum("Gold dust woman", "Gold dust woman", "Rock", "Stevie Nicks");

        System.out.println(catalog.find("Gold"));
        System.out.println();

        System.out.println(catalog.find("Gold","","","Smoke and mirrors"));
        System.out.println();


        Scanner in = new Scanner(System.in);
        String request = "";
        String temp1, temp2, temp3;
        while(true) {
            System.out.println("Options:");
            System.out.println("addAlbum \"Name\" to add album");
            System.out.println("addGenre \"Name\" to add genre");
            System.out.println("addSong \"Name\" to add song");
            System.out.println("find to find songs");
            System.out.println("coolFind to find song");
            System.out.println("end to end");
            request = in.next();
            if(request.equals("end")) {
                System.out.println("bye");
                break;
            }
            switch(request){
                case "addAlbum":
                    request = in.next();
                    catalog.addAlbum(request);
                    break;

                case "addGenre":
                    request = in.next();
                    catalog.addGenre(request);
                    break;

                case "addSong":
                    System.out.println("Name?");
                    request = in.next();
                    System.out.println("Album?");
                    temp1 = in.next();
                    System.out.println("Genre?");
                    temp2 = in.next();
                    System.out.println("Artist?");
                    temp3 = in.next();
                    catalog.fillAlbum(temp1,request, temp2, temp3);
                    break;

                case "find":
                    request = in.next();
                    System.out.println(request);
                    System.out.println(catalog.find(request));
                    System.out.println();
                    break;

                case "coolFind":
                    System.out.println("Name?");
                    request = in.next();
                    if(request.equals("None")) request = "";
                    System.out.println("Album?(None for nothing)");
                    temp1 = in.next();
                    if(temp1.equals("None")) temp1 = "";
                    System.out.println("Genre?(None for nothing)");
                    temp2 = in.next();
                    if(temp2.equals("None")) temp2 = "";
                    System.out.println("Artist?(None for nothing)");
                    temp3 = in.next();
                    if(temp3.equals("None")) temp3 = "";
                    System.out.println(catalog.find(request,temp3,temp2,temp1));
                    System.out.println();
                    break;
            }

        }
    }
}
