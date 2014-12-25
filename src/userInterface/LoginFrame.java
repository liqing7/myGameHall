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
		
	}
	
	//Build avatar selection
	private void buildAvatarSelect() {
		this.avatarSelect = new JComboBox(this.avatars.keySet().toArray());
		this.avatarSelect.setMaximumRowCount(5);
		this.avatarSelect.setRenderer(new AvatarComboBoxReaderer(this.avatars));
	}
}