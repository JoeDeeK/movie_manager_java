
package moviemanager;

/**
 * Name:  Jody Kirton 
 *        Liu Yue(June)
 * File: MovieManager.java
 * Other files in this project: Movie.java
 * Main class: GUI.java
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MovieManager {
  private final ArrayList<Movie> movies = new ArrayList<>();
  private String storageFilePath = null;

  /**
   * Retrieves the movies from inventory.
   * 
   * @return movies from inventory.
   */
  public ArrayList<Movie> getMovies() {
    return movies;
  }
  
  /**
   * Retrieves the storageFilePath for a movie listing.
   * 
   * @return storageFilePath for a movie listing.
   */
  public String getStorageFilePath() {
    if (storageFilePath == null) {
      return "./movies.txt";
    }
    return storageFilePath;
  }

  /**
   * Puts a programmer-specified storageFilePath into the storageFilePath 
   * member. 
   * 
   * @param storageFilePath the programmer-specified storageFilePath.
   */
  public void setStorageFilePath(String storageFilePath) {
    this.storageFilePath = storageFilePath;
  }
  
  /**
   * Loads searched for movie listings into the visible table.
   * 
   * @throws IOException possible with BufferedReader.
   * @throws FileNotFoundException if searched movie not found in file.
   */
  public void loadMovies() throws IOException {
    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader(getStorageFilePath()));
    } catch (FileNotFoundException ex) {
      return;
    }
    
    String line;
    while ((line = br.readLine()) != null) {
      movies.add(Movie.fromString(line));
    }
    br.close();
  }
  
  /**
   * Saves new movie listings and edits made to movie listing. 
   * 
   * @throws IOException possible with BufferedWriter.
   */
  public void saveMovies() throws IOException {
    try (BufferedWriter bw = new BufferedWriter(new 
        FileWriter(getStorageFilePath()))) {
      for (Movie m : movies) {
        bw.write(m.toString() + "\n");
      }
    }
  }
  
  /**
   * Deletes a movie listing from file. 
   * 
   * @param m the user-specified movie listing.
   */
  public void deleteMovie(Movie m) {
      movies.remove(m);
  }
  
  /**
   * Adds a movie listing to file, and assigns it an ID number.
   * 
   * @param m the user-specified movie listing.
   */
  public void addMovie(Movie m) {
    if (movies.isEmpty()) {
      m.setId(1);
    }
    else {
      m.setId(movies.get(movies.size() - 1).getId() + 1);
    }
    movies.add(m);
  }
  
  /**
   * Searches stored movie listings for movies that match the search criteria.
   * 
   * @param genre the user-specified genre searched.
   * @param title the user-specified title searched.
   * @param director the user-specified title searched.
   * @param inStock the user-specified whether in stock or not when searching.
   * @return result for this search.
   */
  public ArrayList<Movie> search(String genre, String title, String director, 
          Boolean inStock) {
    ArrayList<Movie> result = new ArrayList<>();
    for (Movie m : movies) {
      if ((genre.equals("All") || genre.equals(m.getGenre())) &&
           m.getTitle().toLowerCase().contains(title.toLowerCase()) &&
           m.getDirector().toLowerCase().contains(director.toLowerCase()) &&
           (inStock == null || m.getInstock() == inStock)){
        result.add(m);
      }
    }
    return result;
  }
  
}
