/*
  Show user the path

  @author Anggito Anju
 * @version ver 1.1 - 7 July 21 (revised by fadhil)
 */

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class PathFrame
{

    /* === PRIVATE VARIABLES === */
    public static JFrame pathFrame = new JFrame("Runner Path");

    // Header Panel
    private static final JPanel headerPanel = new JPanel();
    // Labels
    private static final JLabel headerTitle = new JLabel("Runner Path");

    // Path Panel
    private static final JPanel pathPanel = new JPanel();
    // Labels INIT
    private static final JLabel startLabel = new JLabel("START");
    private static final JLabel finishLabel = new JLabel("END");
    private static final JLabel distLabel = new JLabel("Distance");
    private static final JLabel pathLabel = new JLabel("Path");
    private static final JLabel end = new JLabel("<html><div style='text-align: center;'>" + "Run Everyday Keeps<br>The Doctor Away" + "</div></html>");
    // Labels ANS
    private static final JLabel ansStartLabel = new JLabel("");
    private static final JLabel ansFinishLabel = new JLabel("");
    private static final JLabel ansDistLabel = new JLabel("");
    private static final JTextArea ansPathLabel = new JTextArea("");

    /* ===== UTILITY ===== */
    private static final Font f1 = new Font("Verdana", Font.ITALIC + Font.BOLD, 32);     // title
    private static final Font f2 = new Font(Font.SERIF, Font.PLAIN, 12);          // path
    private static final Font f3 = new Font("Helvetica", Font.PLAIN, 16);        // end
    private static final Font f4 = new Font(Font.SERIF, Font.PLAIN, 16);         // menus
    private static final Font f5 = new Font(Font.SERIF, Font.PLAIN, 14);         // ans
 
    private static final Color c1 = new Color(245, 163, 0);      // yellow
    private static final Color c3 = new Color(255, 204, 0);
    private static final Color c2 = new Color(46, 44, 40);       // brown
    /* ===== END OF UTILITY ===== */

    private static String from, to;

    public PathFrame(int s, int f, String str, String fin) {
        // Constructur for Path Frame
        from = str;
        to = fin;

        findBest(s, f);
    }

    private static void initComponents() {
        /* ========== CONTAINER SETTINGS ========== */
        pathFrame.setSize(300, 400);
        pathFrame.setLocationRelativeTo(null);
        pathFrame.setLayout(new BorderLayout());
        pathFrame.setResizable(false);
        pathFrame.setVisible(true);
        pathFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* === ADD PANELS === */
        pathFrame.add(headerPanel, BorderLayout.PAGE_START);
        pathFrame.add(pathPanel, BorderLayout.PAGE_END);

        /* ========== END CONTAINER SETTINGS ========== */

        /* ========== COMPONENTS SETTINGS ========== */
        /* ===== HEADER PANEL ===== */
        headerPanel.setBackground(c1);
        headerPanel.setPreferredSize(new Dimension(300, 80));
        headerPanel.setLayout(null);
        
        headerTitle.setBounds(30, 25, 250, 35);
        headerTitle.setFont(f1);
        headerTitle.setForeground(Color.WHITE);

        headerPanel.add(headerTitle);
        /* ===== END OF HEADER PANEL ===== */

        /* ===== PATH PANEL ===== */
        pathPanel.setBackground(Color.WHITE);
        pathPanel.setPreferredSize(new Dimension(300, 280));
        pathPanel.setLayout(null);

        startLabel.setBounds(30, 50, 80, 20);
        startLabel.setFont(f4);
        startLabel.setForeground(c2);
        //startLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        ansStartLabel.setBounds(115, 51, 160, 18);
        ansStartLabel.setFont(f5);
        ansStartLabel.setForeground(c2);
        ansStartLabel.setBackground(c3);
        //ansStartLabel.setBorder(BorderFactory.createLineBorder(c2));

        finishLabel.setBounds(30, 80, 80, 20);
        finishLabel.setFont(f4);
        finishLabel.setForeground(c2);
        //.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        ansFinishLabel.setBounds(115, 81, 160, 18);
        ansFinishLabel.setFont(f5);
        ansFinishLabel.setForeground(c2);
        ansFinishLabel.setBackground(c3);
        //ansFinishLabel.setBorder(BorderFactory.createLineBorder(c2));

        distLabel.setBounds(30, 110, 80, 20);
        distLabel.setFont(f5);
        distLabel.setForeground(c2);
        //distLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        ansDistLabel.setBounds(115, 111, 80, 18);
        ansDistLabel.setFont(f5);
        ansDistLabel.setForeground(c2);
        ansDistLabel.setBackground(c3);
        //ansDistLabel.setBorder(BorderFactory.createLineBorder(c2));

        pathLabel.setBounds(30, 140, 80, 20);
        pathLabel.setFont(f4);
        pathLabel.setForeground(c2);
        //pathLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        ansPathLabel.setBounds(115, 141, 130, 60);
        ansPathLabel.setFont(f5);
        ansPathLabel.setForeground(c2);
        ansPathLabel.setBackground(c3);
        ansPathLabel.setLineWrap(true);
        ansPathLabel.setWrapStyleWord(true);
        ansPathLabel.setEditable(false);

        JScrollPane scroll = new JScrollPane(ansPathLabel);

        scroll.setBounds(115, 141, 160, 80);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        end.setBounds(72, 225, 150, 50);
        end.setFont(f3);
        end.setForeground(c2);
        //end.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pathPanel.add(startLabel);
        pathPanel.add(ansStartLabel);
        pathPanel.add(finishLabel);
        pathPanel.add(ansFinishLabel);
        pathPanel.add(distLabel);
        pathPanel.add(ansDistLabel);
        pathPanel.add(pathLabel);
        pathPanel.add(scroll);
        pathPanel.add(end);
    }

    // Find Path
    private static final int NO_PAR = -1;

    private static void djikstraAlgo(int[][] adjMatrix, int src, int dest, Vector<crossRoadNode> roadList) {
        int vertexAmount = adjMatrix[src].length;

        // array to trace the path
        int[] shortestDist = new int[vertexAmount];

        // visited bool
        boolean[] visited = new boolean[vertexAmount];

        // initialize distances
        for(int i = 0; i < vertexAmount; i++) {
            shortestDist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // dist to self
        shortestDist[src] = 0;

        // parents for tracing path
        int[] parents = new int[vertexAmount];

        // start vertex has no parent
        parents[src] = NO_PAR;

        for (int j = 1; j < vertexAmount; j++)
        {
            // find shortest path
            int adjVertex = -1;
            int shortest = Integer.MAX_VALUE;

            for(int i = 0; i < vertexAmount; i++) {
                if(shortestDist[i] < shortest && !visited[i]) {
                    adjVertex = i;
                    shortest = shortestDist[i];
                }
            }

            visited[adjVertex] = true;

            for(int i = 0; i < vertexAmount; i++) {
                int edgeDist = adjMatrix[adjVertex][i];

                if(edgeDist > 0 && ((shortest + edgeDist) < shortestDist[i])) {
                    parents[i] = adjVertex;
                    shortestDist[i] = shortest + edgeDist;
                }
            }
        }
        printSolution(src, shortestDist, parents, dest, roadList);
    }

    private static void printSolution(int src, int[] distances, int[] parents, int dest, Vector<crossRoadNode> roadList) {

        ansDistLabel.setText(": " + distances[dest]);
        //initComponents();
        //System.out.println(distances[dest]);

        if(dest != src) {
            //System.out.println("Jalan yang akan anda lalui");
            //System.out.print(roadList.elementAt(dest).getLabel());
            ansPathLabel.append("START HERE");
            printPath(dest, parents, roadList);
            initComponents();
        }
        /*else {
            System.out.print("Jalan yang Anda pilih sama");
        }*/
    }

    private static void printPath(int currVertex, int[] parents, Vector<crossRoadNode> roadList) {
        if(currVertex == NO_PAR) {
            return;
        }
        
        printPath(parents[currVertex], parents, roadList);
        //System.out.print(" -> " + roadList.elementAt(currVertex).getLabel());
        ansPathLabel.append(" => " + roadList.elementAt(currVertex).getLabel());
    }
    
    public static void findBest(int src, int dest) {
        // Initialize crossroad's amount
        int amount = 12;

        // array of crossroads
        Vector<crossRoadNode> crossroadList = new Vector<>();

        // Initialize crossroads
        crossRoadNode cross0 = new crossRoadNode(0, "Teknik Kimia");
        crossRoadNode cross1 = new crossRoadNode(1, "Teknik Hidrodinamika");
        crossRoadNode cross2 = new crossRoadNode(2, "Taman Teknologi");
        crossRoadNode cross3 = new crossRoadNode(3, "Teknik Perkapalan");
        crossRoadNode cross4 = new crossRoadNode(4, "Teknik Mesin 2");
        crossRoadNode cross5 = new crossRoadNode(5, "Perpustakaan ITS");
        crossRoadNode cross6 = new crossRoadNode(6, "Taman Dr. Angka");
        crossRoadNode cross7 = new crossRoadNode(7, "Teknik Mesin 1");
        crossRoadNode cross8 = new crossRoadNode(8, "Taman Alumni");
        crossRoadNode cross9 = new crossRoadNode(9, "Bundaran ITS 1");
        crossRoadNode cross10 = new crossRoadNode(10, "Bundaran ITS 2");
        crossRoadNode cross11 = new crossRoadNode(11, "Bundaran ITS 3");
        
        crossroadList.add(cross0);
        crossroadList.add(cross1);
        crossroadList.add(cross2);
        crossroadList.add(cross3);
        crossroadList.add(cross4);
        crossroadList.add(cross5);
        crossroadList.add(cross6);
        crossroadList.add(cross7);
        crossroadList.add(cross8);
        crossroadList.add(cross9);
        crossroadList.add(cross10);
        crossroadList.add(cross11);

        // Initializing maps
        int[][] graph = new int[amount][amount];

        for(int i = 0; i < amount; i++) {
            for(int j = 0; j < amount; j++) {
                graph[i][j] = 0;
            }
        }

        // Mark all crossroad unvisited
        boolean[] visited = new boolean[amount];
        for(int i = 0; i<amount; i++) {
            visited[i] = false;
        }

        // Initializing all roads
        graph[0][1] = 12;
        graph[0][2] = 40;
        graph[0][3] = 15;
        graph[1][2] = 32;
        graph[2][0] = 40;
        graph[2][7] = 45;
        graph[3][4] = 20;
        graph[4][8] = 22;
        graph[5][6] = 20;
        graph[6][5] = 20;
        graph[6][7] = 18;
        graph[7][2] = 45;
        graph[7][6] = 18;
        graph[7][8] = 20;
        graph[8][7] = 20;
        graph[8][11] = 11;
        graph[9][11] = 8;
        graph[10][9] = 8;
        graph[10][11] = 8;
        graph[11][10] = 8;
        graph[11][6] = 24;

        ansStartLabel.setText(": " + from);
        ansFinishLabel.setText(": " + to);

        djikstraAlgo(graph, src, dest, crossroadList);
    }
}