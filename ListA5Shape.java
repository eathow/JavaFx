package sample;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class ListA5Shape {
    @XmlElement(name = "A5Shape")
    private List<A5Shape> lists = new ArrayList<>();
    public List<A5Shape> getLists() { return lists;}
    public List<A5Shape> addShape(A5Shape s) {
        s = new A5Shape(s.getXCoord(), s.getYCoord(), s.getRadius());
        lists.add(s);
        return lists;
    }
}
