/*
 * Nodes for crossroad
 *
 * @author Anggito Anju
 * @version ver 1.1 - 7 July 21 (revised by fadhil)
 */
public class crossRoadNode
{
    private int index;
    private String label;

    public crossRoadNode(int index, String label) {
        this.index = index;
        this.label = label;
    }

    // Getter and setter
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}