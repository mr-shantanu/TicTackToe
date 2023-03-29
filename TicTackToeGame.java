import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application{
	private static Scene scene;
	private GridPane gridPane=new GridPane();
	private BorderPane borderPane=new BorderPane();
	private Label title=new Label("Tick Tac Toe Game");
	private Button restartButton=new Button("Restart Game");
	Font font =Font.font("Roboto",FontWeight.BOLD,30);
	
	
	private Button[] btns=new Button[9];
	
	//all logic variable
	boolean gameOver=false;
	int activePlayer =0;
	int gameState []= {3,3,3,3,3,3,3,3,3};
	int winingPosition[][] = {
			{0,1,2},
			{3,4,5},
			{6,7,8},
			{0,3,6},
			{1,4,7},
			{2,5,8},
			{0,4,8},
			{2,4,6}
			};
	
	 public static void main(String[] args) {
 		// TODO Auto-generated method stub
 		 
 		        launch(args);
 		    }

	
		    
		    @Override
		    public void start(Stage Stage) throws IOException {
		       
		     
		        this.createGUI();
		        Scene scene=new Scene(borderPane, 550, 650);
		        Stage.setScene(scene);
		        Stage.show();
		        
		       
		    }


//this function for gui
			private void createGUI() {
				// TODO Auto-generated method stub
				//creating title
				title.setFont(font);
				//creating restart button
				restartButton.setFont(font);
				restartButton.setDisable(true);
				//setting title and restart button to border pane
				borderPane.setTop(title);
				borderPane.setBottom(restartButton);
				
				//Setting border pane  component to center
				borderPane.setAlignment(title,Pos.CENTER);
				borderPane.setAlignment(restartButton,Pos.CENTER);
				borderPane.setPadding(new Insets(20,20,20,30));
				
			//For Reset button
				restartButton.setOnAction(new EventHandler<ActionEvent>() {
					 
		            @Override
		            public void handle(ActionEvent event) {
		                System.out.println(" Restart Button clicked");
		                for(int i=1;i<9;i++) {
		                	gameState[i]=3;
		                	//btns[i].setText("");
		                	btns[i].setGraphic(null);
		                	gameOver =false;
		                	restartButton.setDisable(true);
		                }
		            }
		        });
				
				
				
				
				//working on buttons
				int label=0;
				
				for(int i=0;i<3;i++) {
					for(int j=0;j<3;j++) {
						Button button =new Button();
						button.setId(label+"");
						button.setFont(font);
						button.setPrefWidth(150);
						button.setPrefHeight(150);
						
						
						gridPane.add(button,j,i);
						gridPane.setAlignment(Pos.CENTER);
						btns[label]=button;
						label++;
					}
				}
				
			
				for(Button btn:btns) {
					btn.setOnAction(new EventHandler<ActionEvent>() {
						 
			            @Override
			            public void handle(ActionEvent actionEvent) {
			                
			                Button currentBtn =(Button)actionEvent.getSource();
			                String ids = currentBtn.getId();
			                int id=Integer.parseInt(ids);
			                System.out.println(id);
			                
//Logic for game
			                if(gameOver == true) {
			                	//game over
			                	Alert alert =new Alert(Alert.AlertType.ERROR);
			                	alert.setTitle("Error Message");
			                	alert.setContentText("Game Over !! Try to Restart Game..");
			                	alert.show();
			                }else {
			                	//game not over
			                	//check for player
			                	if(gameState [id]==3) {
			                		//procced
			                		if(activePlayer == 1) {
			                			currentBtn.setGraphic(new ImageView(new Image("file:\\Users\\shant\\eclipse-workspace\\TicTacToe\\src\\img\\x.png")));
			                			gameState[id]=activePlayer;
			                			checkForWinner();
			                			activePlayer=0;
			                		}else {
			                			currentBtn.setGraphic(new ImageView(new Image("file:\\Users\\shant\\eclipse-workspace\\TicTacToe\\src\\img\\0.png")));
			                			gameState[id]=activePlayer;
			                			checkForWinner();
			                			activePlayer=1;
			                		}
			                	}else {
			                		Alert alert =new Alert(Alert.AlertType.ERROR);
				                	alert.setTitle("Error Message");
				                	alert.setContentText("Place is already occupied");
				                	alert.show();
			                		
			                	}
			                }
			                
			            }

						private void checkForWinner() {
							// TODO Auto-generated method stub
							//check for winner
							if(!gameOver) {
								for(int wp[] :winingPosition) {
									if(gameState[wp[0]] == gameState[wp[1]] && gameState[wp[1]] == gameState[wp[2]] && gameState[wp[1]] != 3) {
										//Active player Has won
										Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
					                	alert.setTitle("Success Message");
					                	alert.setContentText(activePlayer+" Has Won the Game");
					                	alert.show();
					                	restartButton.setDisable(false);
					                	break;
								
					
									}
								}
							}
							
						}
			        });
				}
				borderPane.setCenter(gridPane);
			}
}
				
				

			
			
			
		


