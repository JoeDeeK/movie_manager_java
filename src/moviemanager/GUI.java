
package moviemanager;

/**
 * Name:  Jody Kirton
 *        Liu Yue(June)
 * Assignment:  Final Project - Movie Manager
 * Program: Computer Programmer and System's Analysis
 *
 * Movie Manager GUI. Allows user to search through movies stored in inventory. 
 * User is also able to add, edit and delete movie listings. 
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.TOP_RIGHT;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
    private Label lblSearchBy, lblGenre1, lblGenre2, lblTitle1, lblTitle2, 
            lblDirector1, lblDirector2, lblInstock, lblTotal, lblYear, 
            lblTotal2, lblManG, lblManT, lblManD, lblManY;
    private CheckBox chkInstock;
    private Button btnSearch, btnNew, btnDelete, btnSave;
    private TextField txtTitle1, txtTitle2, txtDirector1, txtDirector2, txtYear;
    private ComboBox<String> cbxGenre1, cbxGenre2, cbxInstock;
    private TableView<Movie> table;
    private ObservableList<Movie> tableData = 
            FXCollections.observableArrayList();
    String[] strGenre = {"All", "Action", "Adventure", "Comedy", "Drama", 
        "Horror", "Science Fiction", "Animation", "Unknown"};
    String[] strInstock = {"All", "Yes", "No"};
    String[] columnName = {"ID", "Title", "Genre", "Director", "Year", 
        "Instock"};
    
    private final MovieManager movieManager = new MovieManager();
    private Movie currentMovie;
    
    private void createUIComponents(Stage primaryStage) {
        lblSearchBy = new Label("Search By");
        lblGenre1 = new Label("Genre: ");
        lblTitle1 = new Label("Title: ");
        lblDirector1 = new Label("Director: ");
        lblGenre2 = new Label("Genre: ");
        lblTitle2 = new Label("Title: ");
        lblDirector2 = new Label("Director: ");
        lblInstock = new Label("In Stock: ");
        lblTotal = new Label("Total: ");
        lblYear = new Label("Year: ");
        lblTotal2 = new Label("0");
        lblManG = new Label(" ");
        lblManG.setStyle("-fx-text-fill: red;");
        lblManT = new Label(" ");
        lblManT.setStyle("-fx-text-fill: red;");
        lblManD = new Label(" ");
        lblManD.setStyle("-fx-text-fill: red;");
        lblManY = new Label(" ");
        lblManY.setStyle("-fx-text-fill: red;");

        chkInstock = new CheckBox("_In Stock");
        chkInstock.setAllowIndeterminate(false);
        
        btnSearch = new Button("Searc_h");
        btnNew = new Button("_New");
        btnDelete = new Button("_Delete");
        btnSave = new Button("_Save");
        
        txtTitle1 = new TextField();
        txtTitle2 = new TextField();
        txtDirector1 = new TextField();
        txtDirector2 = new TextField();
        txtYear = new TextField();
        
        table = new TableView();
      
        for (String colname : columnName) {
          TableColumn column = new TableColumn(colname);
          column.setCellValueFactory(new 
                PropertyValueFactory<>(colname.toLowerCase()));
          table.getColumns().add(column);
        }
        table.setItems(tableData);
        
        cbxGenre1 = new ComboBox();
        ObservableList<String> genreList1 = FXCollections.observableArrayList
               (strGenre);
        cbxGenre1.setItems(genreList1);
        cbxGenre1.setValue(strGenre[0]);
        
        cbxGenre2 = new ComboBox();
        ObservableList<String> genreList2 = FXCollections.observableArrayList
               (Arrays.asList(strGenre).subList(1, strGenre.length));
        cbxGenre2.setItems(genreList2);
        cbxGenre2.setValue("Unknown");
        
        cbxInstock = new ComboBox();
        ObservableList<String> instockList = FXCollections.observableArrayList
               (strInstock);
        cbxInstock.setItems(instockList);
        cbxInstock.setValue(strInstock[0]);
        
        GridPane topPane = new GridPane();
        VBox searchBy = new VBox();
        searchBy.getChildren().add(lblSearchBy);
        VBox searchLbls = new VBox();
        searchLbls.getChildren().addAll(lblGenre1, lblTitle1, lblDirector1, 
                lblInstock);
        searchLbls.setAlignment(TOP_RIGHT);
        searchLbls.setMargin(lblGenre1, new Insets(5, 0, 5, 0));
        searchLbls.setMargin(lblTitle1, new Insets(5, 0, 5, 0));
        searchLbls.setMargin(lblDirector1, new Insets(5, 0, 5, 0));
        searchLbls.setMargin(lblInstock, new Insets(5, 0, 5, 0));
        VBox searchOptions = new VBox();
        searchOptions.getChildren().addAll(cbxGenre1, txtTitle1, txtDirector1, 
                cbxInstock, btnSearch);
        searchOptions.setMargin(cbxGenre1, new Insets(1, 0, 1, 0));
        searchOptions.setMargin(txtTitle1, new Insets(1, 0, 1, 0));
        searchOptions.setMargin(txtDirector1, new Insets(1, 0, 1, 0));
        searchOptions.setMargin(cbxInstock, new Insets(1, 0, 1, 0));
        searchOptions.setMargin(btnSearch, new Insets(5, 0, 5, 0));
        VBox newLbls = new VBox();
        newLbls.getChildren().addAll(btnNew, lblGenre2, lblTitle2, lblDirector2, 
                lblYear, btnDelete);
        newLbls.setAlignment(TOP_RIGHT);
        newLbls.setMargin(btnNew, new Insets(5, 0, 5, 0));
        newLbls.setMargin(lblTitle2, new Insets(5, 0, 5, 0));
        newLbls.setMargin(lblGenre2, new Insets(5, 0, 5, 0));
        newLbls.setMargin(lblDirector2, new Insets(5, 0, 5, 0));
        newLbls.setMargin(lblYear, new Insets(5, 0, 5, 0));
        newLbls.setMargin(chkInstock, new Insets(5, 0, 5, 0));
        newLbls.setMargin(btnDelete, new Insets(28, 0, 5, 0));
        VBox listTxts = new VBox();
        listTxts.getChildren().addAll(cbxGenre2, txtTitle2, txtDirector2, 
                txtYear, chkInstock, btnSave);
        listTxts.setMargin(txtTitle2, new Insets(1, 0, 1, 0));
        listTxts.setMargin(cbxGenre2, new Insets(1, 0, 1, 0));
        listTxts.setMargin(txtDirector2, new Insets(1, 0, 1, 0));
        listTxts.setMargin(txtYear, new Insets(1, 0, 1, 0));
        listTxts.setMargin(btnSave, new Insets(1, 0, 5, 0));
        VBox manFields = new VBox();
        manFields.getChildren().addAll(lblManG, lblManT, lblManD, lblManY);
        manFields.setMargin(lblManG, new Insets(5, 0, 0, 0));
        manFields.setMargin(lblManT, new Insets(7, 0, 0, 0));
        manFields.setMargin(lblManD, new Insets(8, 0, 0, 0));
        manFields.setMargin(lblManY, new Insets(8, 0, 0, 0));
        topPane.add(searchBy, 0, 0);
        topPane.add(searchLbls, 1, 0);
        topPane.add(searchOptions, 2, 0);
        topPane.add(newLbls, 3, 0);
        topPane.add(listTxts, 4, 0);
        topPane.add(manFields, 5, 0);
        topPane.setMargin(searchBy, new Insets(20));
        topPane.setMargin(searchLbls, new Insets(20, 5, 20, 20));
        topPane.setMargin(searchOptions, new Insets(20, 40, 20, 5));
        topPane.setMargin(newLbls, new Insets(20, 5, 20, 40));
        topPane.setMargin(listTxts, new Insets(55, 20, 20, 5));
        topPane.setMargin(manFields, new Insets(60, 0, 0, 0));
        
        GridPane bottomPane = new GridPane();
        bottomPane.add(lblTotal, 0, 0);
        bottomPane.add(lblTotal2, 1, 0);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setMargin(lblTotal, new Insets(5, 5, 20, 5));
        bottomPane.setMargin(lblTotal2, new Insets(5, 5, 20, 5));
                
        BorderPane root = new BorderPane();
        root.setTop(topPane);
        root.setCenter(table);
        root.setBottom(bottomPane);
        
        Scene scene = new Scene(root, 700, 400);
        
        primaryStage.setTitle("Movie Manager");
        primaryStage.setScene(scene);
        primaryStage.show();  
    }
    
    private void updateTableData(ArrayList<Movie> movies) {
      tableData.clear();
      tableData.addAll(movies);
      lblTotal2.setText(String.format("%d",movies.size())); 
    }
    
    private void setCurrentMovie(Movie movie) {
        currentMovie = movie;
        cbxGenre2.setValue(movie.getGenre());
        txtTitle2.setText(movie.getTitle());
        txtDirector2.setText(movie.getDirector());
        txtYear.setText(String.format("%d", movie.getYear()));
        chkInstock.setSelected(movie.getInstock()); 
        clearErrors();
    }
    
    private void clearErrors() {
        lblManD.setText(" ");
        lblManY.setText(" ");
        lblManT.setText(" ");
        lblManG.setText(" ");
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        createUIComponents(primaryStage);
       
        movieManager.loadMovies();
        updateTableData(movieManager.getMovies());
        setCurrentMovie(new Movie());
        
        table.getSelectionModel().selectedItemProperty().addListener(new 
                ChangeListener() {
          @Override
          public void changed(ObservableValue observable, Object oldValue, 
                  Object newValue) {
            if (newValue != null) {
              setCurrentMovie((Movie) newValue);
            }
          }
        });
        
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Boolean inStock = null;
                String inStockString = cbxInstock.getValue();
                if (inStockString.equals("Yes")) {
                  inStock = true;
                }
                else if (inStockString.equals("No")) {
                  inStock = false;
                }
                updateTableData(movieManager.search(cbxGenre1.getValue(), 
                        txtTitle1.getText(), txtDirector1.getText(), inStock));
            }       
        });
        
        btnNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setCurrentMovie(new Movie());
            }       
        });
        
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentMovie.getId() != 0) {
                  movieManager.deleteMovie(currentMovie);
                  updateTableData(movieManager.getMovies());
                  try {
                    movieManager.saveMovies();
                  } catch (IOException ex)     {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, 
                            null, ex);
                  }
                }
                setCurrentMovie(new Movie());
            }       
        });
        
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearErrors();
                boolean inputIsValid = true;
                
                String title = txtTitle2.getText();
                if ("".equals(title)) {
                  inputIsValid = false;
                  lblManT.setText("*");
                }
                
                String director = txtDirector2.getText();
                if ("".equals(director)) {
                  inputIsValid = false;
                  lblManD.setText("*");
                }
                
                String yearString = txtYear.getText();
                int year = 0;
                try {
                  year = Integer.parseInt(yearString);
                  if (year < 1920 || year > 2016) {
                    inputIsValid = false;
                    lblManY.setText("*");
                  }
                }
                catch (NumberFormatException e) {
                  inputIsValid = false;
                  lblManY.setText("*");
                }
                
                if (inputIsValid) {
                  currentMovie.setTitle(title);
                  currentMovie.setDirector(director);
                  currentMovie.setYear(year);   
                  currentMovie.setGenre(cbxGenre2.getValue());
                  currentMovie.setInstock(chkInstock.isSelected());
                  if (currentMovie.getId() == 0) {
                    movieManager.addMovie(currentMovie);
                  }
                  try {
                     movieManager.saveMovies();
                   } catch (IOException ex) {
                     Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, 
                             null, ex);
                   }
                  updateTableData(movieManager.getMovies());
                  setCurrentMovie(new Movie());
                }
            }
        });
    }

    /**
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }   
}
