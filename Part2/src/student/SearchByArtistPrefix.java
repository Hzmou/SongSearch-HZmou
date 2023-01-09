/*

* This classs implements the code for searching for an artist trough the artist Prefix,

* it returns an array of Songs with all the artists names matching the prefix that has been inputed. 
* The original code was written by professor Bob Boothe.
* 
* The search method was implemented by Hamza Zmou

*/
package student;

import java.util.*;



import java.util.stream.Stream;

/**
 * Search by artist class that will implement the search method, used to search for an Artist by their
 * prefix
 * implemented by Hamza Zmou and 
 *
 * @author Bob Booth
 */
public class SearchByArtistPrefix {

    // keep a local direct reference to the song array
    private Song[] songs;

    /**
     * constructor initializes the property. [Done]
     *
     * @param sc a SongCollection object
     */
    
    
    
    public SearchByArtistPrefix(SongCollection sc) {
        songs = sc.getAllSongs();
    }

    /**
     * find all songs matching artist prefix uses binary search should operate
     * in time log n + k (# matches) converts artistPrefix to lowercase and
     * creates a Song object with artist prefix as the artist in order to have a
     * Song to compare. walks back to find the first "beginsWith" match, then
     * walks forward adding to the arrayList until it finds the last match.
     *
     * Authors: Max Tuttle (implementing the search methods -Task 2) - Hamza
     * Zmou(added code to keep track of the number of comparisons- Task 4)
     *
     * @param artistPrefix all or part of the artist's name
     * @return an array of songs by artists with substrings that match the
     * prefix
     */
    public Song[] search(String artistPrefix) {
        // write this method

        int firstMatch;
        int firstMatch1;

        int comp;
        int cmpCount = 0;

        int songsLen = songs.length;

        Song[] tempSong = null;

        ArrayList<Song> temp = new ArrayList();
        Song song = new Song(artistPrefix, "", "");
        Comparator<Song> cmp = new Song.CmpArtist();

        /* starting the Binary search with songs and the temp Song object.
      
         */
        firstMatch1 = Arrays.binarySearch(songs, song, cmp);
        comp = ((CmpCnt) cmp).getCmpCnt();

        // turn  the integer returned by the Binary Search.
        if (firstMatch1 < 0) {

            firstMatch = firstMatch1 * (-1);

        } else {
            firstMatch = firstMatch1;
        }

        int lastMatch = firstMatch;

        /*
         * we start the logic by finding a match, we walk backward and inward
         * to make sure that we found the correct match.
         */
        if (songs[firstMatch].getArtist().toLowerCase().startsWith(artistPrefix
                .toLowerCase())) {

            // walking backwards to see if there are any other matches of the 
            //same artist. 
            while (firstMatch > 0 && songs[firstMatch - 1].getArtist().toLowerCase()
                    .startsWith(artistPrefix.toLowerCase())) {
                cmpCount++;
                firstMatch--;

            }
            // increase the comparator count. 
            cmpCount++;

            // scanning inward and looking for matches. 
            while (lastMatch + 1 < songs.length && songs[lastMatch + 1].getArtist()
                    .toLowerCase().startsWith(artistPrefix.toLowerCase())) {
                cmpCount++;
                lastMatch++;

            }
            cmpCount++;

            for (int i = firstMatch; i <= lastMatch; i++) {

                temp.add(songs[i]);
            }

        } else {
            System.out.println("Artist not found please try again!");
        }

        /*
         * Printing statistics about the comparisons and the time complexity
         * of the Binary Search.
      
         */
        Song[] results = new Song[temp.size()];

        temp.toArray(results);

        StringBuilder stb = new StringBuilder();

        stb.append("Index of from the Binary Search: ").append(firstMatch).append("\n");
        stb.append("Binary Search comparisons: ").append(comp).append("\n");
        stb.append("Front found at: ").append(firstMatch).append("\n");
        stb.append("Actual complexity is: ").append(comp + cmpCount).append("\n");
        stb.append("\n");

        // If the total size of the data.
        stb.append("K is:").append(temp.size()).append("\n");
        stb.append("K is:").append(temp.size()).append("\n");
        stb.append("log_2(N) = ").append((int) ((Math.log(songsLen))) / 
                (Math.log(2))).append("\n");

        // integer representing the theoretical complexity K+LogN
        int KLogN = (int) (temp.size() + (int) (Math.log(songsLen)) / (Math.log(2)));

        stb.append("Theoretical complexity k+log n is: ").append(KLogN).append("\n");
        stb.append("\n");

        System.out.println(stb.toString());

        return results;
    }

    /**
     * testing method for this unit
     *
     * @param args command line arguments set in Project Properties - the first
     * argument is the data file name and the second is the partial artist name,
     * e.g. be which should return beatles, beach boys, bee gees, etc.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("usage: prog songfile [search string]");
            return;
        }

        SongCollection sc = new SongCollection(args[0]);
        SearchByArtistPrefix sbap = new SearchByArtistPrefix(sc);

        if (args.length > 1) {
            System.out.println("searching for: " + args[1]);
            Song[] byArtistResult = sbap.search(args[1]);

            System.out.println("Total Songs = " + byArtistResult.length
                    + ", first songs: ");
            Stream.of(byArtistResult).limit(10).forEach(System.out::println);

        }

    }
}
