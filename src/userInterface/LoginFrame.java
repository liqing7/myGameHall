package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.Socket;
import java.util.Map;
import java.util.UUID;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.omg.CORBA.PRIVATE_MEMBER;

import client.ClientThread;
import exception.ClientException;
import commons.Game;
import commons.User;
import commons.Connection;


public class LoginFrame extends JFrame {
	
	//User name
	private JLabel nameTextLabel = new JLabel("Username: ");
	private JTextField nameField = new JTextField(20);
	
	//Choose sex
	private JLabel sexTextLabel = new JLabel("Sex: ");
	private JComboBox sexSelect = new JComboBox();
	
	//Avatar
	private JLabel avatarTextLabel = new JLabel("Avatar: ");
	private JComboBox avatarSelect;
	
	//Game choice
	private JLabel gameTextLabel = new JLabel("Game: ");
	private JComboBox gameSelect = new JComboBox();
	
	//Connection address
	private JLabel connectionLabel = new JLabel("Connection IP: ");
	private JTextField connectionField = new JTextField(20);
	
	//Button
	private JButton confirmButton = new JButton("Confirm");
	private JButton cancelButton = new JButton("Cancel");
	
	//Avatar image
	private Map<String, ImageIcon> avatars;
	
	private User user;
	
	public LoginFrame() {
		
		//Choose sex
		this.sexSelect.addItem("Male");
		this.sexSelect.addItem("Female");
		
		//Avatar selection
		this.avatars = ImageUtil.getAvatars();
		
		//build avatar select
		buildAvatarSelect();
		
		//build game select
		//buildGameSelect();
		
		Box nameBox = Box.createHorizontalBox();
		nameBox.add(Box.createHorizontalStrut(30));
		nameBox.add(this.nameTextLabel);
		nameBox.add(Box.createHorizontalStrut(20));
		nameBox.add(this.nameField);
		nameBox.add(Box.createHorizontalStrut(30));
		
		Box sexBox = Box.createHorizontalBox();
		sexBox.add(Box.createHorizontalStrut(30));
		sexBox.add(this.sexTextLabel);
		sexBox.add(Box.createHorizontalStrut(30));
		sexBox.add(this.sexSelect);
		sexBox.add(Box.createHorizontalStrut(170));
		
		Box headBox = Box.createHorizontalBox();
		headBox.add(Box.createHorizontalStrut(30));
		headBox.add(this.avatarTextLabel);
		headBox.add(Box.createHorizontalStrut(33));
		headBox.add(this.avatarSelect);
		headBox.add(Box.createHorizontalStrut(170));
		
		
		Box gameBox = Box.createHorizontalBox();
		gameBox.add(Box.createHorizontalStrut(30));
		gameBox.add(this.gameTextLabel);
		gameBox.add(Box.createHorizontalStrut(7));
		gameBox.add(this.gameSelect);
		gameBox.add(Box.createHorizontalStrut(30));
		
		Box connectionBox = Box.createHorizontalBox();
		connectionBox.add(Box.createHorizontalStrut(30));
		connectionBox.add(this.connectionLabel);
		connectionBox.add(Box.createHorizontalStrut(7));
		connectionBox.add(this.connectionField);
		connectionBox.add(Box.createHorizontalStrut(30));
		
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(this.confirmButton);
		buttonBox.add(Box.createHorizontalStrut(30));
		buttonBox.add(this.cancelButton);
		
		Box mainBox = Box.createVerticalBox();
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(nameBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(sexBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(headBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(connectionBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(gameBox);
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(buttonBox);
		mainBox.add(Box.createVerticalStrut(20));
		
		this.add(mainBox);
		this.setLocation(300, 300);
		this.setTitle("GameHall Login");
		this.pack();
		this.setVisible(true);
		
		initListeners();
		this.user = new User();
	}
	
	//init button listener
	private void initListeners() {
		this.confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login();
			}
		});
		
		this.cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}
	//Build avatar selection
	private void buildAvatarSelect() {
		this.avatarSelect = new JComboBox(this.avatars.keySet().toArray());
		this.avatarSelect.setMaximumRowCount(5);
		this.avatarSelect.setRenderer(new AvatarComboBoxRenderer(this.avatars));
	}
	
	//
	private void login() {
		//if name is emtpy
		if (this.nameField.getText().equals("")) {
			JOptionPane.showConfirmDialog(null, "Please Input a Name", "Warning", JOptionPane.OK_CANCEL_OPTION);
			
			return;
		}
		
		//set user properties
		setUser();
		this.user.setSocket(createSocket(this.connectionField.getText(), 10001));
		
		//get user chosen game
		Game game = (Game) this.gameSelect.getSelectedItem();
		game.start(this.user);
		
		//Start thread
		ClientThread thread = new ClientThread(this.user);
		thread.start();
		this.setVisible(false);
	}
	
	private void setUser() {
		//Build user
		int sex = getSex();
		String name = this.nameField.getText();
		String id = UUID.randomUUID().toString();
		
		this.user.setId(id);
		this.user.setHeadImage((String) this.avatarSelect.getSelectedItem()); 
		this.user.setName(name);
		this.user.setSex(sex);
	}
	
	private int getSex() {
		String sex = (String) this.sexSelect.getSelectedItem();
		if (sex.equals("Male"))
			return 0;
		else
			return 1;
	}
	
	private Socket createSocket(String address, int port) {
		try {
			//Build a socket
			return new Socket(address, port);
		} catch (Exception e) {
			throw new ClientException(e.getMessage());
		}
	}
	
	private void buildGameSelect() {
		readDeployGame();
	}
	
	//Read game's MANIFEST.MF
	private void readDeployGame() {
		try {
			File folderFile = new File("game");
			for (File file : folderFile.listFiles()) {
				if (isJar(file.getName())) {
					//get jar
					JarFile jar = new JarFile(file);
					//get manifest
					Manifest mf = jar.getManifest();
					//get attributes
					Attributes gameClassAttrs = mf.getMainAttributes();
					//find game-class attribute
					String gameClass = gameClassAttrs.getValue("Game-Class");
					if (gameClass != null) {
						Game game = (Game) Class.forName(gameClass).newInstance();
						this.gameSelect.addItem(game);
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean isJar(String fileNameString) {
		int pointIndex = fileNameString.lastIndexOf(".");
		
		if (pointIndex != -1) {
			String subfixString = fileNameString.substring(pointIndex + 1, fileNameString.length());
			if ("jar".equals(subfixString)) {
				return true;
			}
			
		}
		return false;
	}
}