public class Edge {
    Actor skue1,skue2;
    float vekt;
    String film;
    public Edge(Actor skue1, Actor skue2, float vekt, String film) {
        this.skue1 = skue1;
        this.skue2 = skue2;
        this.vekt = (10 - vekt);
        this.film = film;
    }

    //Sammenligner to kanter, fordi de er urettede har det ikke noe aa si hvilken vei de gaar.
    public boolean sammenlign(Edge kant) {
        if((skue1 == kant.skue1 && skue2 == kant.skue2) || (skue1 == kant.skue2 && skue2 == kant.skue1)) {
            return true;
        } else {
            return false;
        }
    }

    //Henter skuespilleren paa andre side av kanten.
    public Actor andreSkue(Actor skue) {
        if(skue == skue1) {
            return skue2;
        } else if(skue == skue2) {
            return skue1;
        } else {
            return null;
        }
    }
}
