/*
  Map canvas for run path

  @author Anggito Anju
 * @version ver 1.1 - 7 July 21 (revised by fadhil)
 */

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MapCanvas extends JPanel
{

    MapCanvas() {
        setLayout(new FlowLayout());

        ImageIcon mapImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("maps_no_weight.png")));

        JLabel label1 = new JLabel(mapImage);
        add(label1);
    }
}