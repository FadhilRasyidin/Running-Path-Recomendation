/*
  GUI for Running Path Recommendation Application

  @author Anggito Anju
 * @version ver 1.1 - 7 July 21 (revised by fadhil)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppFrame implements ActionListener
{
    /* ===== PRIVATE VARIABLE FOR MAIN FRAME ===== */
    // Frame
    public JFrame mainFrame = new JFrame("Runner Path");

    // Header Panel
    private final JPanel headerPanel = new JPanel();
    // Labels
    private final JLabel headerTitle = new JLabel("Runner Path");

    // Map Panel
    //MapCanvas imageCanvas = new MapCanvas();
    //JPanel mapPanel = new JPanel();
    //private MapCanvas map = new MapCanvas();
    // Map label
    // Image
    //private ImageIcon i = new ImageIcon("maps_no_weight.png");
    // Image Icon
    MapCanvas drawMap = new MapCanvas();

    // User Panel
    private final JPanel userPanel = new JPanel();
    // Labels
    private final JLabel srcLabel = new JLabel("START");
    private final JLabel destLabel = new JLabel("FINISH");
    // ComboBox
    // => Crossroads
    private final String[] crossRoads = {"Teknik Kimia", "Teknik Hidrodinamika", "Taman Teknologi",
        "Teknik Perkapalan", "Teknik Mesin 2", "Perpustakaan ITS", "Taman Dr. Angka", "Teknik Mesin 1",
        "Taman Alumni", "Bundaran ITS 1", "Bundaran ITS 2", "Bundaran ITS 3"};
    private final JComboBox<String> srcCB = new JComboBox<>(crossRoads);
    private final JComboBox<String> destCB = new JComboBox<>(crossRoads);
    // Button
    private final JButton calcButton = new JButton("Find My Path");
    
    /* ===== UTILITY ===== */
    private final Font f1 = new Font("Verdana", Font.ITALIC + Font.BOLD, 32);     // title
    private final Font f2 = new Font("Helvetica", Font.BOLD, 25);          // calc
    private final Font f3 = new Font(Font.SERIF, Font.BOLD, 18);          // buttons
    private final Font f4 = new Font(Font.SERIF, Font.PLAIN, 16);         // menus

    private final Color c1 = new Color(245, 163, 0);      // yellow
    private final Color c3 = new Color(255, 204, 0);
    private final Color c2 = new Color(46, 44, 40);       // brown
    /* ===== END OF UTILITY ===== */

    // Constructur for App Frame
    public AppFrame() {
        initComponents();
    }

    private void initComponents() {
        /* ========== CONTAINER SETTINGS ========== */
        mainFrame.setSize(680, 830);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* ===== ADD PANELS ===== */
        mainFrame.add(headerPanel, BorderLayout.PAGE_START);
        mainFrame.add(drawMap, BorderLayout.CENTER);
        //mainFrame.add(mapPanel, BorderLayout.CENTER);
        mainFrame.add(userPanel, BorderLayout.PAGE_END);

        /* ========== END CONTAINER SETTINGS ========== */
        
        /* ========== COMPONENTS SETTINGS ========== */
        /* ===== HEADER PANEL ===== */
        headerPanel.setBackground(c1);
        headerPanel.setPreferredSize(new Dimension(680, 80));
        headerPanel.setLayout(null);
        
        headerTitle.setBounds(30, 25, 250, 35);
        headerTitle.setFont(f1);
        headerTitle.setForeground(Color.WHITE);

        headerPanel.add(headerTitle);
        /* ===== END OF HEADER PANEL ===== */

        /* ===== MAP PANEL ===== */
        //mapPanel.setBackground(Color.WHITE);
        //mapPanel.setPreferredSize(new Dimension(500, 500));
        //mapPanel.setLayout(null);
    
        //mapPanel.add(imageCanvas);
        /* ===== END OF MAP PANEL ===== */

        /* ===== USER PANEL ===== */
        userPanel.setPreferredSize(new Dimension(680, 180));
        userPanel.setLayout(null);

        srcLabel.setBounds(200, 25, 100, 32);
        //setBorder(BorderFactory.createLineBorder(Color.BLACK));
        srcLabel.setForeground(c2);
        srcLabel.setFont(f2);

        destLabel.setBounds(200, 84, 100, 32);
        //destLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        destLabel.setForeground(c2);
        destLabel.setFont(f2);

        srcCB.setBounds(320, 32, 180, 24);
        srcCB.setFont(f4);
        srcCB.setBackground(c3);
        // Source Combobox Event
        srcCB.addActionListener(this);
        srcCB.setActionCommand("START");

        destCB.setBounds(320, 91, 180, 24);
        destCB.setFont(f4);
        destCB.setBackground(c3);
        // Destination Combobox Event
        destCB.addActionListener(this);
        destCB.setActionCommand("DESTINATION");

        calcButton.setBounds(280, 140, 150, 30);
        calcButton.setFont(f3);
        calcButton.setBackground(c1);
        calcButton.setForeground(Color.WHITE);
        calcButton.setBorder(BorderFactory.createLineBorder(c2, 1));
        // Calc Button Event
        calcButton.addActionListener(this);
        calcButton.setActionCommand("CALCULATE");

        userPanel.add(srcLabel);
        userPanel.add(destLabel);
        userPanel.add(srcCB);
        userPanel.add(destCB);
        userPanel.add(calcButton);
        /* ===== END OF USER PANEL ===== */


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int src, dest;
        String from, to;

        if(command.equals("START")) {
            src = srcCB.getSelectedIndex();
            from = (String) srcCB.getSelectedItem();
        }

        if(command.equals("DESTINATION")) {
            dest = destCB.getSelectedIndex();
            to = (String) destCB.getSelectedItem();
        }

        if(command.equals("CALCULATE")) {
            src = srcCB.getSelectedIndex();
            from = (String) srcCB.getSelectedItem();
            dest = destCB.getSelectedIndex();
            to = (String) destCB.getSelectedItem();
            new PathFrame(src, dest, from, to);
        }   
    }
}