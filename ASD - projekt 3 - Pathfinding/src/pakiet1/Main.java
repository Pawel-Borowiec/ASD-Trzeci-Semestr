package pakiet1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Main extends Application  {
    public static TileButton buttons[][] = new TileButton[10][10];
    static Algorithm algorithm;
    static HBox hbox;
    public static void main (String[] args) throws FileNotFoundException {
            algorithm = new Algorithm();
            launch(args);
            algorithm.startAlgorithm();
            algorithm.startPathFinding();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Path Finding");


        hbox = new HBox();
        hbox.getChildren().addAll(getButtons(),getOptionsPanel());

        Scene scene = new Scene(hbox, 1300, 960);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public GridPane getButtons()
    {
        GridPane gridPane = new GridPane();
        for(int i=0;i<10;i++)
        {

            for(int j=0;j<10;j++)
            {
                TileButton tileButton = new TileButton(algorithm.plansza.Data[j][i]);
                tileButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                    if(algorithm.phase==0 && tileButton.tileType==TileType.ALLOWED)
                    {
                        algorithm.phase=1;
                        algorithm.setS(algorithm.plansza,tileButton.y,tileButton.x);
                        tileButton.tileType=TileType.START;
                        tileButton.setStyle("-fx-background-color: #fc2b1c");

                    }else if(algorithm.phase==1 && tileButton.tileType==TileType.ALLOWED)
                    {
                        algorithm.phase=2;
                        algorithm.setK(algorithm.plansza,tileButton.y,tileButton.x);
                        tileButton.tileType=TileType.KONIEC;
                        tileButton.setStyle("-fx-background-color: #3918f2");
                        for(int k=0;k<10;k++)
                        {
                            for(int l=0;l<10;l++)
                            {
                                if(algorithm.plansza.Data[k][l].typ==TileType.VISITED)
                                {
                                    System.out.println("V");
                                    buttons[l][k].tileType=TileType.VISITED;
                                    buttons[l][k].setColor();
                                }
                            }
                        }

                    }

                    }
                });
                buttons[i][j]=tileButton;

                gridPane.add(buttons[i][j],i,j);
            }
        }
        return gridPane;
    }
    public VBox getOptionsPanel()
    {
        VBox vBox = new VBox();
        vBox.setPrefSize(340,960);
        vBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #ffa3f3"
                );

        VBox temp = new VBox();
        temp.getChildren().add(newPathButton());
        temp.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 2;"+
                "-fx-border-color: #ffa3f3"
        );
        vBox.getChildren().addAll(temp,getLegendaLabel(), createLegend());
        return vBox;
    }
    public VBox getLegendaLabel()
    {
        VBox vBox = new VBox();
        Label label = new Label("Legenda");
        setPropertiesOfElement(label);
        label.setStyle("-fx-background-color: #fce144");
        label.setAlignment(Pos.BASELINE_CENTER);
        vBox.getChildren().add(label);
        vBox.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 2;"+
                "-fx-border-color: #ffa3f3"
        );
        return vBox;
    }
    public Button newPathButton()
    {
        Button button = new Button("Wygeneruj nową mapę");
        setPropertiesOfElement(button);
        button.setStyle("-fx-background-color: #ff6b9b");
        button.setAlignment(Pos.BASELINE_CENTER);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                algorithm.phase=0;
                algorithm.plansza=new Plansza();
                for(int k=0;k<10;k++)
                {
                    for(int l=0;l<10;l++)
                    {
                            buttons[l][k].tileType=algorithm.plansza.Data[k][l].typ;
                            buttons[l][k].setColor();
                        }
                    }
                }
        });
        return  button;
    }
    public void setPropertiesOfElement(Labeled labeled)
    {
        labeled.setMaxSize(306,96);
        labeled.setPrefSize(306,96);
        labeled.setMinSize(306,96);
    }
    public Label createLegendLabel(String text)
    {
        Label label = new Label();
        set224Size(label);
        label.setAlignment(Pos.BASELINE_CENTER);
        label.setStyle("-fx-background-color: #fce144");
        label.setText(text);
        return  label;
    }
    public void set224Size(Label label)
    {
        label.setMaxSize(208,96);
        label.setPrefSize(208,96);
        label.setMinSize(208,96);
    }
    public Label createColorLabel()
    {
        Label label = new Label();
        label.setMaxSize(96,96);
        label.setPrefSize(96,96);
        label.setMinSize(96,96);

        return  label;
    }
    public VBox createLegend()
    {
        VBox vBox =new VBox();
        HBox first = new HBox();
        Label label11 = createColorLabel();
        label11.setStyle("-fx-background-color: #e1f7fa");
        Label label12 = createLegendLabel("Pole dostępne");
        first.getChildren().addAll(label11,label12);
        first.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 2;"+
                "-fx-border-color: #ffa3f3"
        );

        Label label21 = createColorLabel();
        label21.setStyle("-fx-background-color: #6e0c19");
        Label label22 = createLegendLabel("Pole niedostępne");
        HBox second = new HBox();
        second.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 2;"+
                "-fx-border-color: #ffa3f3"
        );
        second.getChildren().addAll(label21,label22);

        Label label31 = createColorLabel();
        label31.setStyle("-fx-background-color: #14fc5a");
        Label label32 = createLegendLabel("Pole odwiedzone");
        HBox third = new HBox();
        third.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 2;"+
                "-fx-border-color: #ffa3f3"
        );
        third.getChildren().addAll(label31,label32);

        Label label41 = createColorLabel();
        label41.setStyle("-fx-background-color: #fc2b1c");
        Label label42 = createLegendLabel("Poczatek Sciezki");
        HBox fourth = new HBox();
        fourth.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 2;"+
                "-fx-border-color: #ffa3f3"
        );
        fourth.getChildren().addAll(label41,label42);

        Label label51 = createColorLabel();
        label51.setStyle("-fx-background-color: #3918f2");
        Label label52 = createLegendLabel("Koniec Sciezki");
        HBox fifth = new HBox();
        fifth.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 2;"+
                "-fx-border-color: #ffa3f3"
        );
        fifth.getChildren().addAll(label51,label52);

        vBox.getChildren().addAll(first,second, third, fourth, fifth);
        return vBox;
    }

}
