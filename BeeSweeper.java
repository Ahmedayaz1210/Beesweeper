package application;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;

import javafx.scene.paint.ImagePattern;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.util.Duration;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;


import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.layout.BorderPane;




//EASY BOMBS = 10   W, H = 360  TILE_SIZE = 40
//MEDIUM BOMBS = 40   W, H = 640  TILE_SIZE = 40
//HARD BOMBS = 99   W = 1500, H = 800  TILE_SIZE = 40

//@Author: Ahmed Ayaz, Ben Boksanski
public class BeeSweeper extends Application {
	private int TILE_SIZE = 40;
    private int W = 360;
    private int H = 360;

    private int X_TILES = W / TILE_SIZE;
    private int Y_TILES = H / TILE_SIZE;
    
    private int BOMBS = 10;
    private int TILESWITHOUTBOMBS = (X_TILES * Y_TILES) - BOMBS;
   
    private boolean mineLocations[][] = new boolean[X_TILES][Y_TILES];
    
    //these two variables i used to see if at the end all tiles are opened except the bomb ones, the user gets displayed you won
    public int numTilesOpened;
    
    IntegerProperty flag = new SimpleIntegerProperty();
    
    private Tile[][] grid = new Tile[X_TILES][Y_TILES];
    private Scene scene;
    
    //change to menu
    private Parent createContent() {
    	Image patternImage = new Image("C:\\Users\\Ahmed Ayaz\\git\\beesweeper\\JavaFX17\\src\\application\\walll.jpg");

    	BackgroundImage backgroundImage = new BackgroundImage(
    			patternImage,
    			BackgroundRepeat.REPEAT,
    			BackgroundRepeat.REPEAT,
    			BackgroundPosition.DEFAULT,
    			BackgroundSize.DEFAULT);
    	Background background = new Background(backgroundImage);
    	// Create the BorderPane and set the background
    	BorderPane border = new BorderPane();
    	border.setBackground(background);
    	border.setPrefSize(W, (H + 200));

    	
    	Button button1 = new Button("Easy");
    	
    	button1.setOnAction(e ->  {
    		TILE_SIZE = 40;
    	    W = 360;
    	    H = 360;
    	    BOMBS = 10;
    	    X_TILES = W / TILE_SIZE;
    	    Y_TILES = H / TILE_SIZE;
    	    TILESWITHOUTBOMBS = (X_TILES * Y_TILES) - BOMBS;


    	    flag.set(BOMBS);
    	    numTilesOpened = 0;

    	    mineLocations = new boolean[X_TILES][Y_TILES];
    	    grid = new Tile[X_TILES][Y_TILES];

    	    Scene medium = new Scene(createContent());
    	    Stage stage = (Stage) button1.getScene().getWindow();
    	    stage.setScene(medium);    		
    	       
    	});
    	
    	Button button2 = new Button("Medium");
    	
    	button2.setOnAction(e ->  {
    		TILE_SIZE = 40;
    	    W = 640;
    	    H = 640;
    	    BOMBS = 40;
    	    X_TILES = W / TILE_SIZE;
    	    Y_TILES = H / TILE_SIZE;
    	    TILESWITHOUTBOMBS = (X_TILES * Y_TILES) - BOMBS;

    	    flag.set(BOMBS);
    	    numTilesOpened = 0;

    	    mineLocations = new boolean[X_TILES][Y_TILES];
    	    grid = new Tile[X_TILES][Y_TILES];

    	    Scene medium = new Scene(createContent());
    	    Stage stage = (Stage) button2.getScene().getWindow();
    	    stage.setScene(medium);    		
    	       
    	});
    	
    	Button button3 = new Button("Hard");
    	
    	button3.setOnAction(e ->  {
    		TILE_SIZE = 40;
    	    W = 1200;
    	    H = 640;
    	    BOMBS = 99;
    	    X_TILES = W / TILE_SIZE;
    	    Y_TILES = H / TILE_SIZE;
    	    TILESWITHOUTBOMBS = (X_TILES * Y_TILES) - BOMBS;

    	    flag.set(BOMBS);
    	    numTilesOpened = 0;

    	    mineLocations = new boolean[X_TILES][Y_TILES];
    	    grid = new Tile[X_TILES][Y_TILES];

    	    Scene medium = new Scene(createContent());
    	    Stage stage = (Stage) button3.getScene().getWindow();
    	    stage.setScene(medium);    		
    	       
    	});	
    	
    	Label flagsMsg = new Label("MARKED HIVES = ");
      flagsMsg.setStyle("-fx-font-size:24pt;");
      flagsMsg.setStyle("-fx-background-color: white;");
      Label numbFlags = new Label();
      numbFlags.setStyle("-fx-font-size: 24pt;");
      numbFlags.setStyle("-fx-background-color: white;");
      numbFlags.textProperty().bind(flag.asString());
      
      
      Image image = new Image("C:\\Users\\Ahmed Ayaz\\git\\beesweeper\\JavaFX17\\src\\application\\header.png");

      // Create an ImageView object
      ImageView imageView = new ImageView(image);
      imageView.setFitWidth(360);
      imageView.setFitHeight(100);
      
      
      HBox hbox = new HBox(flagsMsg, numbFlags);
      
      VBox vbox = new VBox(hbox, button1, button2, button3);
      
      
      vbox.getChildren().add(imageView);
      
      vbox.setAlignment(Pos.CENTER);
      
      vbox.setPrefSize(W, 100);
      
          Pane root = new Pane();
          
          
          
          root.setPrefSize(W, H);
          border.setTop(vbox);
          
          
          
          Random rn = new Random();
          
      
          flag.set(BOMBS);
          
      
          
          numTilesOpened = 0;
          
          for (int y = 0; y < Y_TILES; y++) {
              for (int x = 0; x < X_TILES; x++) {
            	  mineLocations[x][y] = false;
              }
          }
          
          
          int counter = 0;
          while (counter < BOMBS) { //looping while # bombs distinct cells are not set to true
              int rRow = rn.nextInt(X_TILES);
              int rCol = rn.nextInt(Y_TILES);
              
              if (!mineLocations[rRow][rCol]) {
                  mineLocations[rRow][rCol] = true;
                  counter++; //increasing the counter only when a new cell is set to true
              }
          }
          
          for(int y = 0; y < Y_TILES;y++) {
        	  for(int x = 0; x < X_TILES; x++) {
        		  if(mineLocations[x][y] == true) {
        			  Tile tile = new Tile(x,y,true,false);
        			  grid[x][y] = tile;
        			  root.getChildren().add(tile);
        		  }else {
        			  Tile tile = new Tile(x,y,false,false);
        			  grid[x][y] = tile;
        			  root.getChildren().add(tile);
        		  }
        	  }
          }
          
          for (int y = 0; y < Y_TILES; y++) {
              for (int x = 0; x < X_TILES; x++) {
                  Tile tile = grid[x][y];

                  if (tile.hasBomb)
                      continue;

                  long bombs = getNeighbors(tile).stream().filter(t -> t.hasBomb).count();

                  if (bombs > 0)
                      tile.text.setText(String.valueOf(bombs));
              }
          }
          
          HBox root2 = new HBox(root);
          root2.setAlignment(Pos.CENTER);
          
          border.setCenter(root2);
          
          return border;
      }
      

    
 
    
    private Parent addContent(int pos_X, int pos_Y) {
    	BorderPane border = new BorderPane();
      border.setStyle("-fx-background-color: red;");
      border.setPrefSize(W, (H + 200));
      
      Button button1 = new Button("Easy");
      
      button1.setOnAction(e ->  {
      TILE_SIZE = 40;
      W = 360;
      H = 360;
      BOMBS = 10;
      X_TILES = W / TILE_SIZE;
      Y_TILES = H / TILE_SIZE;
      TILESWITHOUTBOMBS = (X_TILES * Y_TILES) - BOMBS;


      flag.set(BOMBS);
      numTilesOpened = 0;

      mineLocations = new boolean[X_TILES][Y_TILES];
      grid = new Tile[X_TILES][Y_TILES];

      Scene medium = new Scene(createContent());
      Stage stage = (Stage) button1.getScene().getWindow();
      stage.setScene(medium);
      });

      Button button2 = new Button("Medium");
      
      button2.setOnAction(e ->  {
      TILE_SIZE = 40;
      W = 640;
      H = 640;
      BOMBS = 40;
      X_TILES = W / TILE_SIZE;
      Y_TILES = H / TILE_SIZE;
      TILESWITHOUTBOMBS = (X_TILES * Y_TILES) - BOMBS;

      flag.set(BOMBS);
      numTilesOpened = 0;

      mineLocations = new boolean[X_TILES][Y_TILES];
      grid = new Tile[X_TILES][Y_TILES];

      Scene medium = new Scene(createContent());
      Stage stage = (Stage) button2.getScene().getWindow();
      stage.setScene(medium);
      });
      
      Button button3 = new Button("Hard");
      
      button3.setOnAction(e ->  {
      TILE_SIZE = 40;
      W = 1200;
      H = 640;
      BOMBS = 99;
      X_TILES = W / TILE_SIZE;
      Y_TILES = H / TILE_SIZE;
      TILESWITHOUTBOMBS = (X_TILES * Y_TILES) - BOMBS;

      flag.set(BOMBS);
      numTilesOpened = 0;

      mineLocations = new boolean[X_TILES][Y_TILES];
      grid = new Tile[X_TILES][Y_TILES];

      Scene medium = new Scene(createContent());
      Stage stage = (Stage) button3.getScene().getWindow();
      stage.setScene(medium);
      });
      
      Label flagsMsg = new Label("MARKED HIVES = ");
      Label numbFlags = new Label();
      
      numbFlags.textProperty().bind(flag.asString());
      
      
      Image image = new Image("C:\\Users\\Ahmed Ayaz\\git\\beesweeper\\JavaFX17\\src\\application\\header.png");

      // Create an ImageView object
      ImageView imageView = new ImageView(image);
      imageView.setFitWidth(360);
      imageView.setFitHeight(100);
 
      HBox hbox = new HBox(flagsMsg, numbFlags);
      
      VBox vbox = new VBox(hbox, button1, button2, button3);
      
      
      vbox.getChildren().add(imageView);
      
      vbox.setAlignment(Pos.CENTER);
      
      vbox.setPrefSize(W, 100);
      
          Pane root = new Pane();
          root.setPrefSize(W, H);
          border.setTop(vbox);
          Random rn = new Random();
        
          
      
          flag.set(BOMBS);
          
          numTilesOpened = 1;
          
          
          int counter = BOMBS - 1;
          while(counter < BOMBS) {
        	  //looping while # bombs distinct cells are not set to true
        	  int rRow, rCol;
        	  do {
        		  rRow = rn.nextInt(X_TILES);
        		  rCol = rn.nextInt(Y_TILES);
        	  }while(rRow != pos_X && rCol != pos_Y);
        	  if (!mineLocations[rRow][rCol]) {
                  mineLocations[rRow][rCol] = true;
                  counter++; //increasing the counter only when a new cell is set to true
              }
          }

          
          for (int y = 0; y < Y_TILES; y++) {
        	  for (int x = 0; x < X_TILES; x++) {
        		  if(x == pos_X && y == pos_Y) {
        			  Tile tile = new Tile(x, y, false, true);
        			  grid[x][y] = tile;
        			  root.getChildren().add(tile);
        		  }else if(mineLocations[x][y] == true) {
        			  Tile tile = new Tile(x, y, true, false);
        			  grid[x][y] = tile;
        			  root.getChildren().add(tile);
        		  }else {
        			  Tile tile = new Tile(x, y, false, false);
        			  grid[x][y] = tile;
        			  root.getChildren().add(tile);
        		  }
        	  }
          }

          for (int y = 0; y < Y_TILES; y++) {
              for (int x = 0; x < X_TILES; x++) {
                  Tile tile = grid[x][y];

                  if (tile.hasBomb)
                      continue;

                  long bombs = getNeighbors(tile).stream().filter(t -> t.hasBomb).count();

                  if (bombs > 0)
                      tile.text.setText(String.valueOf(bombs));  
                  
              }
          }
          
          HBox root2 = new HBox(root);
          root2.setAlignment(Pos.CENTER);
          
          border.setCenter(root2);
          
          
          return border;

    }

    private List<Tile> getNeighbors(Tile tile) {
        List<Tile> neighbors = new ArrayList<>();

        // ttt
        // tXt
        // ttt

        int[] points = new int[] {
              -1, -1,
              -1, 0,
              -1, 1,
              0, -1,
              0, 1,
              1, -1,
              1, 0,
              1, 1
        };

        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];

            int newX = tile.x + dx;
            int newY = tile.y + dy;

            if (newX >= 0 && newX < X_TILES
                    && newY >= 0 && newY < Y_TILES) {
                neighbors.add(grid[newX][newY]);
            }
        }

        return neighbors;
    }

    private class Tile extends StackPane {
    	private int x, y;
        private boolean hasBomb;
        private boolean isFlaged = false;
        private boolean isOpen;

        private Rectangle border = new Rectangle(TILE_SIZE , TILE_SIZE);
        
        private Text text = new Text();
       
        
        private ImagePattern pattern = new ImagePattern(new Image("C:\\Users\\Ahmed Ayaz\\git\\beesweeper\\JavaFX17\\src\\application\\flower.png"));

        
        public Tile(int x, int y, boolean hasBomb, boolean isOpen) {
            this.x = x;
            this.y = y;
            this.hasBomb = hasBomb;
            this.isOpen = isOpen;
            
            text.setFill(Color.RED);
            border.setFill(pattern);
            border.setStroke(Color.BLACK);
            

            
            text.setFont(Font.font(30));
            text.setText(hasBomb ? "X" : "");
            
            text.setVisible(false);

            getChildren().addAll(border, text);



            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);

            if(isOpen) {
            	open();
            }
           
            
            setOnMouseClicked(e ->
            {
                if (e.getButton() == MouseButton.PRIMARY)
                {
                	open();
                
                } else if (e.getButton() == MouseButton.SECONDARY)
                {
                	setFlag();
                }
            });

            
        }
 

        public void setFlag() {
        	if (isOpen) {
        		return;
        	}
        	if (!(isFlaged) && flag.get() <= BOMBS && flag.get() > 0) {
        		isFlaged = true;
        		ImagePattern pattern = new ImagePattern(new Image("C:\\Users\\Ahmed Ayaz\\git\\beesweeper\\JavaFX17\\src\\application\\bee.png"));
        		border.setFill(pattern);
        		flag.set(flag.get() - 1);
        		return;
        	}
        	isFlaged = false;
        	ImagePattern pattern = new ImagePattern(new Image("C:\\Users\\Ahmed Ayaz\\git\\beesweeper\\JavaFX17\\src\\application\\flower.png"));

        	border.setFill(pattern);
        	flag.set(flag.get() + 1);

        }

        public void open() {
        	
        	if(isOpen) {
        		text.setVisible(true);
        		ImagePattern pattern = new ImagePattern(new Image("C:\\Users\\Ahmed Ayaz\\git\\beesweeper\\JavaFX17\\src\\application\\grass.png"));
        		border.setFill(pattern);
        		return;
        	}
        	
        	if(numTilesOpened == 0 && hasBomb) {
        		mineLocations[this.x][this.y] = false;
        		Scene removeBomb = new Scene(addContent(this.x, this.y));
        		Stage stage = (Stage) getScene().getWindow();
        		stage.setScene(removeBomb);
        		return;
        	}

            if ((hasBomb && isFlaged) || (!isOpen && isFlaged)) {
                return;
            }  
            
            
            MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("C:\\Users\\Ahmed Ayaz\\git\\beesweeper\\JavaFX17\\src\\application\\buzz2.mp3").toURI().toString()));
            mediaPlayer.play();
            isOpen = true;
            numTilesOpened++;
            text.setVisible(true);
            
            ImagePattern pattern = new ImagePattern(new Image("C:\\Users\\Ahmed Ayaz\\git\\beesweeper\\JavaFX17\\src\\application\\grass.png"));

            border.setFill(pattern);

            if (text.getText().isEmpty()) {
                getNeighbors(this).forEach(Tile::open);
                return;
            }
            
            
            
            
            
        
            if (hasBomb && !(isFlaged)) {
            ImagePattern ptrn2 = new ImagePattern(new Image("C:\\Users\\Ahmed Ayaz\\git\\beesweeper\\JavaFX17\\src\\application\\bomb_hive.png"));
                border.setFill(ptrn2);

                Label label = new Label("You lost - Game Over");
                label.setFont(Font.font(24));
                label.setTextFill(Color.BLUE);
                Button playAgain = new Button();
                playAgain.setStyle("-fx-background-color: #32cd32; ");
                playAgain.setText("Play Again!");
                playAgain.setTranslateX(10);
                playAgain.setTranslateY(60);

                StackPane root = new StackPane(label, playAgain);
                root.setPrefSize(W, H);
                Scene gameOverScene = new Scene(root);
                MediaPlayer mediaPlayer2 = new MediaPlayer(new Media(new File("C:\\Users\\Ahmed Ayaz\\git\\beesweeper\\JavaFX17\\src\\application\\failure.mp3").toURI().toString()));
                mediaPlayer2.play();
                Stage stage = (Stage) getScene().getWindow();
                playAgain.setOnAction(e -> {
                    stage.setScene(new Scene(createContent()));
                });

                // Create a pause transition to delay the display of the game over message and button by 1 second
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> {
                    stage.setScene(gameOverScene);
                });
                delay.play();
            }
            

         
            
            if (numTilesOpened == TILESWITHOUTBOMBS) {
            	Label label = new Label("You won - Congratulations!");
            	MediaPlayer mediaPlayer3 = new MediaPlayer(new Media(new File("C:\\Users\\Ahmed Ayaz\\git\\beesweeper\\JavaFX17\\src\\application\\win-sound.mp3").toURI().toString()));
            	mediaPlayer3.play();
                label.setFont(Font.font(24));
                label.setTextFill(Color.GREEN);
                Button playAgain = new Button();
                playAgain.setStyle("-fx-background-color: #32cd32; ");
                playAgain.setText("Play Again!");
                playAgain.setTranslateX(10);
                playAgain.setTranslateY(60);

                StackPane root = new StackPane(label, playAgain);
                root.setPrefSize(W, H);
                Scene gameOverScene = new Scene(root);
                Stage stage = (Stage) getScene().getWindow();
                playAgain.setOnAction(e ->{
                    stage.setScene(new Scene(createContent()));
                });
                stage.setScene(gameOverScene);
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(createContent());
        
        stage.setScene(scene);
        stage.show();
        stage.setTitle("BeeSweeper");
        
    }

	public static void main(String[] args) {
		launch(args);

	}

}