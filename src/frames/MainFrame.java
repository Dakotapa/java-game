package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel ipLabel, portLabel;
	private JTextField txtIpAddress;
	private JTextField txtPort;
	private JButton btnJoin;
	boolean connected = false;
	//Socket s;
	
	MulticastSocket s;
	private final Action action = new SwingAction();
	private JLabel errorMsg;

	public MainFrame(MulticastSocket _s)
	{
		this.s = _s;
		initilizeConnectionPanel();
	}

	public void initilizeConnectionPanel() 
	{

		///Set close for X button top right corner
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set x,y,width,height
		setBounds(800, 600, 800, 600);
		//Create new JPanel for connection screen
//		contentPane = new JPanel();
		this.setBackground(Color.BLACK);
		//Set border
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//set content pane of this.Frame
//		setContentPane(contentPane);
		this.setLayout(null);
		
		ipLabel = new JLabel();
		ipLabel.setBounds(275, 219, 66, 16);
		ipLabel.setForeground(Color.WHITE);
		ipLabel.setText("IP Address");
		this.add(ipLabel);
		
		
		txtIpAddress = new JTextField();
		txtIpAddress.setBounds(352, 214, 130, 26);
		txtIpAddress.setText("");
		this.add(txtIpAddress);
		txtIpAddress.setColumns(10);
		
		portLabel = new JLabel();
		portLabel.setBounds(316, 254, 25, 16);
		portLabel.setForeground(Color.WHITE);
		portLabel.setText("Port");
		this.add(portLabel);
		
		txtPort = new JTextField();
		txtPort.setBounds(352, 249, 130, 26);
		txtPort.setText("");
		this.add(txtPort);
		txtPort.setColumns(10);
		
		btnJoin = new JButton("Join");
		btnJoin.setBounds(352, 325, 130, 38);
		btnJoin.setForeground(Color.BLACK);
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnJoin.setAction(action);
		this.add(btnJoin);
		
		JLabel lblGameName = new JLabel("Dragons & Fireballs");
		lblGameName.setForeground(Color.WHITE);
		lblGameName.setFont(new Font("Lucida Grande", Font.PLAIN, 42));
		lblGameName.setBounds(193, 88, 442, 82);
		this.add(lblGameName);
		
		errorMsg = new JLabel("");
		errorMsg.setForeground(Color.RED);
		errorMsg.setBounds(275, 279, 373, 30);
		this.add(errorMsg);
	}

	
	public void initializeGameBoardPanel()
	{
	}
	
	private class SwingAction extends AbstractAction 
	{
		private static final long serialVersionUID = 1L;
		public SwingAction() 
		{
			putValue(NAME, "Join");
			putValue(SHORT_DESCRIPTION, "Join the game.");
		}
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				System.out.println("Trying to connect from MainFrame Action method 'Join'");
				s.connect(new InetSocketAddress(txtIpAddress.getText(), 
												Integer.parseInt(txtPort.getText())));	
				System.out.println("Connection complete from MainFrame Action method 'Join'");
			}
			catch(SocketException se)
			{
				System.out.println("From Client: " + se );
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("nfe: " + nfe.getMessage() 
									+ "\nPort was not a number.");
			}		
		}
	}
}
