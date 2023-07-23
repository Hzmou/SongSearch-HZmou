package student;

import java.util.stream.Stream;
import java.util.*;
import java.io.*;

/**
 *
 * @author boothe
 */
public class SongCollection {

    private Song[] songs;

    public SongCollection(String filename) {

        // use a try catch block
        // read in the song file and build the songs array
        // there are several ways to read in the lyrics correctly.
        // the line feeds between lines and the blank lines between verses
        // must be retained.
        // sort the songs array using Arrays.sort (see the Java API)
        // this will use the compareTo() in Song to do the job.
        ArrayList<Song> list = new ArrayList<>();

        Scanner fileReader = null;
        
         String lineTkn;
        StringBuilder lyrics = new StringBuilder();

        String titleTkn = null;
        String artistTkn = null;
        

        File file = new File(filename);
        try {
            fileReader = new Scanner(file);

        } catch (FileNotFoundException err) {
            System.out.println("File not Found.");
            System.exit(1);

        }

       
 
        while (fileReader.hasNext()) {

            lineTkn = fileReader.nextLine();
            
            
            // Parsing the Artist
            if (lineTkn.startsWith("ARTIST=")) {

                artistTkn = lineTkn.substring(8, lineTkn.length() - 1);

            }
            
            
            // Parsing the Title
            if (lineTkn.startsWith("TITLE=")) {

                titleTkn = lineTkn.substring(7, lineTkn.length() - 1);

            }
             
            
            // parsing the Lyrics
            if (lineTkn.startsWith("LYRICS=")) {

                lineTkn = lineTkn.substring(8, lineTkn.length() - 1);
                lyrics.append(lineTkn).append("\n");

            }
             
            // while we have not reached the end of the Song lyrics,
            //keep parsing
            while (lineTkn.indexOf('"') == -1) {

                lineTkn = fileReader.nextLine();
                lyrics.append(lineTkn).append("\n");

            }

            if (lineTkn.startsWith("\"")) {

                list.add(new Song(artistTkn, titleTkn,
                        lyrics.toString()));
                lyrics.setLength(0);

            }

        }

        
        fileReader.close();
        songs = new Song[list.size()];
        list.toArray(songs);
        Arrays.sort(songs);

    }

    /**
     * this is used as the data source for building other data structures.
     *
     * @return the songs array
     */
    public Song[] getAllSongs() {
        return songs;
    }

    /**
     * unit testing method, prints the first 10 songs in the song text file.
     * Author: Anne Applin
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("usage: prog songfile");
            return;
        }

        SongCollection sc = new SongCollection(args[0]);
        Song[] list = sc.getAllSongs();

        // todo: show song count and first 10 songs (name & title only, 1 per line)
        System.out.printf("Total songs =  %d, first songs: \n", list.length);
        // print the list or the first 10 which ever is less        
        Stream.of(list).limit(10).forEach(System.out::println);

    }

}
