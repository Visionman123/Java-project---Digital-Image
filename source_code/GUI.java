package source_code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GUI extends JPanel {

    private JFrame frame;
    private static JTextField textFieldOpen;
    private static Thread t1;
    private static JLabel lblGenVar;
    private static JLabel lblimpVar = new JLabel("0");
    private static JLabel lblFitnessVal = new JLabel("0");

    private static ImagePanel originalImage;
    private static ImagePanel bestImage;
    private static ImagePanel evolvingImage;

    private static int GenVal = 0, ImpVal = 0;

    private static BufferedImage OriImage;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GUI window = new GUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GUI() {
        initialize();
    }

    private void start() {
        if (new File(textFieldOpen.getText()).isFile()) {
            t1 = new Thread() {
                @Override
                public void run() {
                    Compare();
                }
            };
            t1.start();
        } else System.err.println("Error TextFieldOpen is not a File");
    }

    private void stop() {
        t1.stop();
    }


    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 828, 553);                    // Set the Size of the Window
        frame.setMinimumSize(new Dimension(828, 553));            // Set a fixed MinSize of the Window
        frame.setLocationRelativeTo(null);                    // Set Window in the Middle
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // Do Exit on the Window Cross

        JPanel panel = new JPanel();
        JPanel panel_1 = new JPanel();
        JPanel panel_3 = new JPanel();
        JPanel panel_4 = new JPanel();
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
        );
        panel.setLayout(new GridLayout(0, 1, 0, 0));

        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(1.0);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panel.add(splitPane);

        JLayeredPane panel_2 = new JLayeredPane();
        splitPane.setRightComponent(panel_2);

        originalImage = new ImagePanel(null);

        bestImage = new ImagePanel(null);

        evolvingImage = new ImagePanel(null);

        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        JButton btnStop = new JButton("Stop");
        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });

        textFieldOpen = new JTextField();
        textFieldOpen.setColumns(10);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setText(lblFitnessVal, "0");
                setText(lblimpVar, "0");
                setText(lblGenVar, "0");

                textFieldOpen.setText("");
                textFieldOpen.setEditable(true);
                GenVal = 0;
                ImpVal = 0;
            }

        });


        JButton btnOpen = new JButton("Open");
        btnOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldOpen.setText(FileOpenDialog());

                if (textFieldOpen.getText().isEmpty())
                    System.err.println("\nTextfield Open is empty.");
                else {
                    textFieldOpen.setEditable(false);
                    try {
                        OriImage = ImageIO.read(new File(textFieldOpen.getText()));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    originalImage.setImage(OriImage);
                    originalImage.repaint();

                    bestImage.createImageFrom(OriImage);
                    bestImage.renderPolygons();
                    bestImage.repaint();

                }
            }

        });

        JLabel lblGenerations = new JLabel("Generations");
        lblGenerations.setFont(new Font("Tahoma", Font.PLAIN, 12));

        lblGenVar = new JLabel("0");
        lblGenVar.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblGenVar.setForeground(Color.BLACK);

        JLabel lblImprovements = new JLabel("Improvements");
        lblImprovements.setFont(new Font("Tahoma", Font.PLAIN, 12));

        lblimpVar = new JLabel("0");
        lblimpVar.setFont(new Font("Tahoma", Font.BOLD, 12));

        JLabel lblFitness = new JLabel("Fitness");
        lblFitness.setFont(new Font("Tahoma", Font.PLAIN, 12));

        lblFitnessVal = new JLabel("0");
        lblFitnessVal.setFont(new Font("Tahoma", Font.BOLD, 12));


        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
                gl_panel_2.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_2.createSequentialGroup()
                                .addGap(21)
                                .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(btnReset, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnStart, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                .addGap(45)
                                .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnOpen))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(textFieldOpen))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblGenerations)
                                        .addComponent(lblImprovements))
                                .addGap(18)
                                .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblimpVar)
                                        .addGroup(gl_panel_2.createSequentialGroup()
                                                .addComponent(lblGenVar)
                                                .addGap(42)
                                                .addComponent(lblFitness)
                                                .addGap(18)
                                                .addComponent(lblFitnessVal)))
                                .addGap(46))
        );
        gl_panel_2.setVerticalGroup(
                gl_panel_2.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_2.createSequentialGroup()
                                .addContainerGap(23, Short.MAX_VALUE)
                                .addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnStart)
                                        .addComponent(textFieldOpen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnOpen)
                                        .addComponent(btnStop)
                                        .addComponent(lblGenerations)
                                        .addComponent(lblGenVar)
                                        .addComponent(lblFitness)
                                        .addComponent(lblFitnessVal))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnReset)
                                        .addComponent(lblImprovements)
                                        .addComponent(lblimpVar))
                                .addGap(30))
        );
        panel_2.setLayout(gl_panel_2);

        JSplitPane splitPane_1 = new JSplitPane();
        splitPane_1.setResizeWeight(.5d);
        splitPane.setLeftComponent(splitPane_1);

        splitPane_1.setLeftComponent(panel_1);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 200));

        FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
        flowLayout.setVgap(200);
        flowLayout.setHgap(200);
        splitPane_1.setRightComponent(panel_3);

        panel_1.setLayout(new BorderLayout(0, 0)); // BorderLayout to fix the Fullfill
        panel_1.add(originalImage);
        panel_3.setLayout(new BorderLayout(0, 0));
        panel_3.add(bestImage);

        splitPane.setEnabled(false);    // set SPlitPane Divider Fixed //
        splitPane_1.setEnabled(false);    // ****************************//

        frame.getContentPane().setLayout(groupLayout);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

    }

    public static String FileSaveDialog() {
        JFileChooser input = new JFileChooser();
        input.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result1 = input.showOpenDialog(input);
        if (result1 == JFileChooser.APPROVE_OPTION) {
            System.out.println("Apply was Selected");
            return input.getSelectedFile().getPath();
        } else if (result1 == JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancel was selected");
            return null;
        } else {

            return null;
        }
    }

    public static String FileOpenDialog() {

        JFileChooser input = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Bilder", "gif", "png", "jpg");
        input.setCurrentDirectory(new File("C:\\Users\\Jan\\git\\ProjectMonalisa\\EvoLisaTest\\src"));
        input.setFileFilter(filter);
        input.setAcceptAllFileFilterUsed(false);
        int result1 = input.showOpenDialog(input);
        if (result1 == JFileChooser.APPROVE_OPTION) {
            System.out.println("Apply was Selected");
            return input.getSelectedFile().getAbsolutePath();
        } else if (result1 == JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancel was selected");
            return null;
        } else {

            return null;
        }
    }

    public static void Compare() {
        Fitness f = new Fitness(OriImage);
        Mutation mutation = new Mutation(new Dimension(OriImage.getWidth(), OriImage.getHeight()), bestImage.getPolygongen());
        do {

            mutation.mutate();
            bestImage.renderPolygons();
            bestImage.repaint();
            if (f.getFitness(bestImage.getImage())) {
                ImpVal++;
                setText(lblimpVar, Long.toString(ImpVal));
            } else {
                mutation.undoLastMutation();
            }

            GenVal++;
            setText(lblGenVar, Long.toString(GenVal));

        } while (true);
    }

    private static void setText(final JLabel label, final String text) {
        label.setText(text);
        label.paintImmediately(label.getVisibleRect());
    }


    public static void setFitnessVal(double fitnessVal) {
        DecimalFormat df = new DecimalFormat("#.00");
        setText(lblFitnessVal, df.format(fitnessVal) + "%");
    }
}