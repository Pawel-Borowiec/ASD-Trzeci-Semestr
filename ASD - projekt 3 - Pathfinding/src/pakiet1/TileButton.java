package pakiet1;

import javafx.scene.control.Button;

public class TileButton extends Button {
    TileType tileType;
    int x;
    int y;
    TileButton(Tile tile){
        this.tileType=tile.type;
        x=tile.x;
        y=tile.y;
        setProperties();
    }
    private void setProperties(){
        this.setMaxSize(96,96);
        this.setPrefSize(96,96);
        this.setMinSize(96,96);
        this.setColor();
    }
    public void setColor(){
        switch (tileType)
        {
            case ALLOWED:
                this.setStyle("-fx-background-color: #e1f7fa");
                break;
            case NOT_ALLOWED:
                this.setStyle("-fx-background-color: #b3b3b3");
                break;
            case VISITED:
                this.setStyle("-fx-background-color: #14fc5a");
        }
    }
}
