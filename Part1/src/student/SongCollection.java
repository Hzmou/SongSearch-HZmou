
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
