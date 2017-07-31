
package moviemanager;

/**
 * Name:  Jody Kirton 
 *        Liu Yue(June)
 * File: Movie.java
 * Other files in this project: MovieManager.java
 * Main class: GUI.java
 */

import java.util.StringTokenizer;

public class Movie {
  private int id;
  private String title="";
  private String genre="Unknown";
  private int year;
  private String director="";
  private boolean instock;
  
  /**
      * Gets a String representation of the movie objects id, title, genre, 
      * year, director, and instock. 
      * 
      * @return the id, title, genre, year, director, or instock as a String.
      */
  @Override
  public String toString(){
    return String.format("%d|%s|%s|%d|%s|%d", id, title, genre, year, director, 
            instock ? 1 : 0);
  }
  
  /**
   * Takes the user-specified input for a movie listing and transforms it 
   * to match the property types. 
   * 
   * @param s the user-specified movie listing
   * @return movie listing information.
   */
  public static Movie fromString(String s){
    Movie movie = new Movie();
    StringTokenizer st = new StringTokenizer(s, "|");
    movie.setId(Integer.parseInt(st.nextToken()));
    movie.setTitle(st.nextToken());
    movie.setGenre(st.nextToken());
    movie.setYear(Integer.parseInt(st.nextToken()));
    movie.setDirector(st.nextToken());
    movie.setInstock(Integer.parseInt(st.nextToken())==1);
    return movie;
  }
  
  /**
   * Puts a user-specified id into the id member. 
   * 
   * @param id the user-specified id.
   */
  public void setId(int id){
    this.id = id;
  }
  
  /**
   * Puts a user-specified title into the title member. 
   * 
   * @param title the user-specified title. 
   */
  public void setTitle(String title){  
    this.title = title;
  }
  
  /**
   * Puts a user-specified genre into the genre member.
   * 
   * @param genre the user-specified genre.
   */
  public void setGenre(String genre){
    this.genre = genre;
  }
  
  /**
   * Puts a user-specified director into the director member. 
   * 
   * @param director the user-specified director. 
   */
  public void setDirector(String director){
    this.director = director;
  }
  
  /**
   * Puts a user-specified year into a year member. Year must be greater 
   * than 0, otherwise an exception is thrown.
   * 
   * @param year the user-specified year. 
   */
  public void setYear(int year){
    if(year>0){
      this.year = year;
    }
    else
      throw new IllegalArgumentException("please input the correct year value.");
  }
  
  /**
   * Puts a user-specified instock into a instock member.
   * 
   * @param instock the user-specified instock.
   */
  public void setInstock(boolean instock){
    this.instock = instock;
  }
  
  /**
   * Retrieves the id of this movie.
   * 
   * @return id of this movie.
   */
  public int getId() {
    return id;
  }
  
  /**
   * Retrieves the title of this movie.
   * 
   * @return title of this movie.
   */
  public String getTitle(){
    return title;
  }
  
  /**
   * Retrieves the genre of this movie.
   * 
   * @return genre of this movie.
   */
  public String getGenre(){
    return genre;
  }
  
  /**
   * Retrieves the year of this movie.
   * 
   * @return year of this movie. 
   */
  public int getYear(){
    return year;
  }
  
  /**
   * Retrieves the director of this movie.
   * 
   * @return director of this movie. 
   */
  public String getDirector(){
    return director;
  }
  
  /**
   * Retrieves the instock of this movie.
   * 
   * @return instock of this movie. 
   */
  public boolean getInstock(){
    return instock;
  }
}
