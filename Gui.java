import java.awt.*;
import java.awt.image.*;

import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class Gui extends JFrame {
	JButton myBtn1;
	JButton myBtn2;
	JButton myBtn3;
	JButton myBtn4;
	JTextField txtField;
	JLabel lbl; 
	JButton openFileBtn;
	JLabel songName;


	private String fileName;
	private Streamer streamer;
	// Flags for playing
	private int isPlaying;
	
	public Gui(  ){
		
		// Streamer Settings
		this.fileName = fileName;
		
		this.isPlaying = 0;


		

		/*try{
			BufferedImage myImage = ImageIO.read( new File( "bg.gif" ) );
			this.setContentPane(new ImagePanel(myImage));
		}
		catch( Exception ex ){
			
		}*/

		this.setLayout(null);	
		lbl = new JLabel("name:");
		lbl.setBounds(100, 10, 100, 25);
		this.add(lbl);




		this.openFileBtn = new JButton("Open");
		this.openFileBtn.setBounds(100,50,90,25);
		JFrame mainFrame = this;
		this.openFileBtn.addActionListener(
			new ActionListener(){
				public void actionPerformed( ActionEvent ae ){
					final JFileChooser  fileDialog = new JFileChooser();
					int token = fileDialog.showOpenDialog( mainFrame );
					if ( token == JFileChooser.APPROVE_OPTION ){
						File file = fileDialog.getSelectedFile();
						setFileName( file.getAbsolutePath() );
						if( isPlaying>0 ){
							isPlaying = 0;
							streamer.end();
							streamer = null;
						}
						streamer = new Streamer( getFileName() );
						streamer.play();
						isPlaying = 1;
						if( songName!=null )
							mainFrame.remove(songName);
						songName = new JLabel( getFileName() );
						songName.setBounds( 150,10,600,25 );
						mainFrame.add( songName );
						mainFrame.setVisible(false);
						setVisible(true);
					}
				}
			}
		);
		this.add( openFileBtn );



		
		this.myBtn1 = new JButton("play");
		this.myBtn1.setBounds(200,  50, 80, 25);
		this.myBtn1.addActionListener(
			new ActionListener(){
				public void actionPerformed( ActionEvent ae ){
					if( isPlaying==0 ){
						streamer = new Streamer( getFileName() );
						streamer.play();
						isPlaying = 1;
					}
				}
			}
		);
		this.add(myBtn1);
		
		
		this.myBtn2 = new JButton("pause");
		this.myBtn2.setBounds(300,  50, 80, 25);
		this.myBtn2.addActionListener(
			new ActionListener(){
				public void actionPerformed( ActionEvent ae ){
					streamer.pause();
				}
			}
		);		
		this.add(myBtn2);


		
		this.myBtn3 = new JButton("stop");
		this.myBtn3.setBounds(400,  50, 80, 25);
		this.myBtn3.addActionListener(
			new ActionListener(){
				public void actionPerformed( ActionEvent ae ){
					if( isPlaying!=0 ){
						streamer.end();
						isPlaying = 0;
					}
				}
			}
		);	
		this.add(myBtn3);
		
		
		this.myBtn4 = new JButton("resume");
		this.myBtn4.setBounds(500,  50, 80, 25);
		this.myBtn4.addActionListener(
			new ActionListener(){
				public void actionPerformed( ActionEvent ae ){
					streamer.resume();
				}
			}
		);
		this.add(myBtn4);
		
		this.setBounds(100, 50, 700, 150);
		this.setTitle("MiniMP3 Player v0.0.0.1 Summer-2016-17");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		

		//this.getContentPane().setBackground( Color.White );
		this.setVisible(true);
	}


	public void setFileName( String name ){
		this.fileName = name;
	}
	public String getFileName(){
		return this.fileName;
	}
}



/*class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}*/