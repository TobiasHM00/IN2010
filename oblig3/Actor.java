import java.util.ArrayList;

public class Actor {
    public String id, navn;
    public ArrayList<Edge> kanter = new ArrayList<>();
    public Actor(String id, String navn) {
        this.id = id;
        this.navn = navn;
    }
}
