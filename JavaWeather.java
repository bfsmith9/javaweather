/* JavaWeather Program
 * Barry Smith - December 2019
 * Reads weather forecast, conditions, and radar from NOAA and 
 * other sources.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
// Why needed if importing all java.swing above?
import java.io.*;
import javax.imageio.*;

public class JavaWeather {

  public JavaWeather() throws Exception {
  // MetaWeather constructor
	  new JavaWeatherFrame();
      //weatherframe.setBackground(Color.BLACK);
  }	  // end constructor

  public static void main(String args[]) throws Exception {
    new GetWeather();	
    // Retrieve weather text; comment out to prevent Internet use
    new GetRadar();
    // Retrieve radar image; comment out to prevent Internet use
    
    new GetConditions();
    // Retrieve current conditions; comment out to prevent Internet use
    new ConditionParser();

    new RegExWeather();
    // Cut out St. Albans portion of weather

    new JavaWeather();
    // new MetaWeather instance

  } // end main

} // end class MetaWeather ########################################

// Begin class MetaWeatherFrame
class JavaWeatherFrame extends JFrame {
    /**
	 Just added this serialization based on warning 
	 It has something to do with compiler variance.
	 https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it* 
	 */
	private static final long serialVersionUID = 1L;
	private JButton refreshRadar;
    private JButton refreshText;
    private JButton refreshConditions;
    private JButton largeRadar;
    private JButton regionalRadar;

  public JavaWeatherFrame() throws Exception {
  // Constructor for MetaWeatherFrame
    // Inherit from JFrame
    super(" WEATHER CONDITIONS -- North America, NW Vermont -- " + 
      "LAT 44.45, LON 73.03, ELV 259 m");

    setSize(1080,620);
    //setBackground(Color.BLACK);
    setLayout(new FlowLayout(1,15,15) );
    // To allow window closing
    WindowListener l = new WindowAdapter() {
		  public void windowClosing(WindowEvent e) {
        System.exit(0); 
      } // end windowClosing

    }; // end WindowAdaptor
    addWindowListener(l);

    // Read weather forcast using FileReadingObject
    new FileReadingObject();
    String[] lines = FileReadingObject.readingMethod("cut-weather.txt");

    // Read weather conditions
    BufferedReader conditionReader = new BufferedReader(new FileReader
        ("conditions.txt"));
    new StringBuffer();
    final String date;
    final String description;
    final String temperature;
    final String windspeed;
    final String wind_dir;
    date = conditionReader.readLine();
    description = conditionReader.readLine();
    temperature = conditionReader.readLine();
    windspeed = conditionReader.readLine();
    wind_dir = conditionReader.readLine();
    
    conditionReader.close();

    // Panel for radar buttons - below here ------->
    // Button to refresh radar image
    // The creation of the buttons must be before their usage in the
    // panel
    refreshRadar = new JButton("Refresh radar now");
    largeRadar = new JButton("Large radar");
    regionalRadar = new JButton("Regional radar");
    JPanel radarButtonsPanel = new JPanel(new BorderLayout(5,5) );
    radarButtonsPanel.add(largeRadar, BorderLayout.CENTER);
    radarButtonsPanel.add(regionalRadar, BorderLayout.EAST);
    radarButtonsPanel.add(refreshRadar, BorderLayout.WEST);
    // Panel for radar buttons - above here ------->

    // Panel for radar image and refresh button
    JPanel eastPanel = new JPanel(new BorderLayout(10,10) );

    // set up the radar image
    //
    ImageIcon radarImage = new ImageIcon("radar.gif");
    final JLabel radarPicture = new JLabel(radarImage);
    radarPicture.setSize(300,500); // don't know how to set image size
    eastPanel.add(radarPicture, BorderLayout.NORTH);
    eastPanel.add(radarButtonsPanel, BorderLayout.CENTER);
    // end set up radar image and button (added to FlowLayout below)

    // Panel for conditions and refresh button
    JPanel conditionsPanel = new JPanel(new BorderLayout(10,10) );
    // Text area for current conditions
    final JTextArea conditions_box = new JTextArea();
    Font condfont = new Font("Monospaced",Font.PLAIN,14);
    conditions_box.setFont(condfont);
    conditions_box.setBorder(new LineBorder(Color.black, 1));
    conditions_box.append("CURRENT CONDITIONS:\n");
    conditions_box.append(date + "\n");
    conditions_box.append(description + "\n");
    conditions_box.append(temperature + "\n");
    conditions_box.append(windspeed + "\n");
    conditions_box.append(wind_dir + "\n");
    conditionsPanel.add(conditions_box, BorderLayout.NORTH);

    /* Button to refresh conditions */
    refreshConditions = new JButton("Refresh conditions");
    conditionsPanel.add(refreshConditions, BorderLayout.SOUTH);
    
    // Add conditionsPanel to eastPanel
    eastPanel.add(conditionsPanel, BorderLayout.SOUTH);

    // Panel for text and refresh button
    final JPanel westPanel = new JPanel(new BorderLayout(10,10) );

    // set up text and button
    final JTextArea jta = new JTextArea();
    Font bfsfont = new Font("Monospaced",Font.PLAIN,15);
    //Font bfsfont = new Font("Courier 10 Pitch",Font.PLAIN,16);
    jta.setFont(bfsfont);
    refreshText = new JButton("Get latest forecast");
    int d = 0;
    while (d < 60) {
      // This method appends a line to the end of the text area
      jta.append(lines[d]);
      jta.append("\n");
      d++;
  	} 
    final JScrollPane jsp = new JScrollPane(jta);
    jsp.setPreferredSize(new Dimension(630, 530));
    westPanel.add(jsp,BorderLayout.NORTH);
    westPanel.add(refreshText, BorderLayout.SOUTH);
    add(westPanel); // added to Flow Layout
    add(eastPanel); // added to Flow Layout
    setVisible(true); 

    // Action for refreshRadar button
    refreshRadar.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
         try {
           ImageIcon blankImage = new ImageIcon("blackscreen.jpg");
           radarPicture.setIcon(blankImage);
           new GetRadar();
           /* Need this way of reading a new image that's still called 
              "radar.gif", b/c otherwise Java will cache the original 
              radar.gif, and won't reload from disk */
           Image bufferedRadarImage = ImageIO.read(new File("radar.gif"));
           ImageIcon newRadarImage = new ImageIcon(bufferedRadarImage);
           radarPicture.setIcon(newRadarImage);
         } // end try
         catch (Exception f ) {
           System.out.println("Error"); 
         } // end catch

      } // end actionPerformed
    }); // end refreshRadar.addActionListener

    // Action for largeRadar button
    largeRadar.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
         try {
   	   JFrame largeRadarFrame = new JFrame();
   	   largeRadarFrame.setTitle("largeRadarImage");
     	   largeRadarFrame.setLocation(200,100);
           largeRadarFrame.setSize(800,600);
           largeRadarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           ImageIcon blankImage = new ImageIcon("blackscreen.jpg");
           final JLabel largeRadarPicture = new JLabel(blankImage);
           largeRadarPicture.setSize(800,600); // does this work?
           largeRadarPicture.setIcon(blankImage);
           largeRadarFrame.add(largeRadarPicture);
           new GetLargeRadar();
           /* Need this way of reading a new image that's still called 
              "radar.gif", b/c otherwise Java will cache the original 
              radar.gif, and won't reload from disk */
           Image bufferedRadarImage = ImageIO.read(new File("largeradar.gif"));
           ImageIcon newLargeRadarImage = new ImageIcon(bufferedRadarImage);

           largeRadarPicture.setIcon(newLargeRadarImage);
           largeRadarPicture.setVisible(true);
           largeRadarFrame.setVisible(true);

         } // end try
         catch (Exception f ) {
           System.out.println("Error"); 
         } // end catch
      } // end actionPerformed
    }); // end refreshLargeRadar.addActionListener

    // Action for regionalRadar button
    regionalRadar.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
         try {
   	   JFrame regionalRadarFrame = new JFrame();
   	   regionalRadarFrame.setTitle("regionalRadarImage");
     	   regionalRadarFrame.setLocation(200,100);
           regionalRadarFrame.setSize(750,650);
           regionalRadarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           ImageIcon blankImage = new ImageIcon("blackscreen.jpg");
           final JLabel regionalRadarPicture = new JLabel(blankImage);
           regionalRadarPicture.setSize(750,650); 
           regionalRadarPicture.setIcon(blankImage);
           regionalRadarFrame.add(regionalRadarPicture);
           new GetRegionalRadar();
           /* Need this way of reading a new image that's still called 
              "radar.gif", b/c otherwise Java will cache the original 
              radar.gif, and won't reload from disk */
           Image bufferedRadarImage = ImageIO.read(new File("regionalradar.gif"));
           ImageIcon newRegionalRadarImage = new ImageIcon(bufferedRadarImage);

           regionalRadarPicture.setIcon(newRegionalRadarImage);
           regionalRadarPicture.setVisible(true);
           regionalRadarFrame.setVisible(true);

         } // end try
         catch (Exception f ) {
           System.out.println("Error"); 
         } // end catch
      } // end actionPerformed
    }); // end regionalRadar.addActionListener


    // Action for refreshText button
    refreshText.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          new GetWeather();
          new RegExWeather();
          
          // Read forecast file using FileReadingObject
          new FileReadingObject();
          String[] lines = FileReadingObject.readingMethod("cut-weather.txt");

          // set up text and button
          int d = 0;
          jta.setText("");
          while (d < 60) {
            // This method appends a line to the end of the text area
            jta.append(lines[d]);
            jta.append("\n");
            d++;
          } 
        } // end try
        catch (Exception f ) {
          System.out.println("Error"); 
        } // end catch

        // This function sets scrollbar to home position
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() { 
            jsp.getVerticalScrollBar().setValue(0);
          }
        });

      } // end actionPerformed
    }); // end refreshText.addActionListener


    // Action for refreshConditions button
    refreshConditions.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          BufferedReader conditionReader = new BufferedReader(new FileReader
            ("conditions.txt"));
          new GetConditions();
          new ConditionParser();

          String date;
          String description;
          String temperature;
          String windspeed;
          String wind_dir;
          date = conditionReader.readLine();
          description = conditionReader.readLine();
          temperature = conditionReader.readLine();
          windspeed = conditionReader.readLine();
          wind_dir = conditionReader.readLine();
          conditionReader.close();
          
          conditions_box.setText("");
          conditions_box.append("CURRENT CONDITIONS:\n");
          conditions_box.append(date + "\n");
          conditions_box.append(description + "\n");
          conditions_box.append(temperature + "\n");
          conditions_box.append(windspeed + "\n");
          conditions_box.append(wind_dir + "\n");
        } //end try
        catch (Exception f) {
          System.out.println("Error");
        } // end catch

      } // end actionPerformed
    }); // end refreshConditions.addActionListener

  } // end MetaWeatherFrame constructor

} // end MetaWeatherFrame class

