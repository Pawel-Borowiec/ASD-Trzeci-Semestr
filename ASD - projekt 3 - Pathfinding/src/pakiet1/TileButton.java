package pakiet1;

import javafx.scene.control.Button;

public class TileButton extends Button {
    TileType tileType;
    int x;
    int y;
    TileButton()
    {
        setProperties();
    }
    TileButton(String name)
    {
        setProperties();
        this.setText(name);
    }
    TileButton(Tile tile)
    {
        this.tileType=tile.typ;
        x=tile.x;
        y=tile.y;
        setProperties();
        this.setText("Hello");
    }
    public void setProperties()
    {
        this.setMaxSize(96,96);
        this.setPrefSize(96,96);
        this.setMinSize(96,96);
        this.setText("Tile");
        this.setColor();
    }
    public void setColor()
    {
        switch (tileType)
        {
            case ALLOWED:
                this.setStyle("-fx-background-color: #e1f7fa");
                break;
            case NOTALLOWED:
                this.setStyle("-fx-background-color: #6e0c19");
                break;
            case VISITED:
                this.setStyle("-fx-background-color: #14fc5a");
        }
    }
}
