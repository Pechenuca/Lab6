package coreSources;

public class Coordinates {
    private Double x; //Поле не может быть null
    private float y; //Значение поля должно быть больше -98
    public Coordinates(Double x, long y) {
        this.x = x;
        this.y = y;
    }

}

