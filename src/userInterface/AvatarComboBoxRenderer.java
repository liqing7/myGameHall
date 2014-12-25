package userInterface;

import java.awt.Component;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * 
 * @author Qing
 *
 */
public class AvatarComboBoxRenderer extends JLabel implements ListCellRenderer {
	
	private Map<String, ImageIcon> avatars;
	
	public AvatarComboBoxRenderer(Map<String, ImageIcon> avatars) {
		
		this.avatars = avatars;
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}
	
	public Component getListCellRendererComponent(JList list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		String selectValue = (String) value;
		
		//set background
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		
		//
		Icon icon = this.avatars.get(selectValue);
		setIcon(icon);
		if (icon != null)
			setFont(list.getFont());
		
		return this;
	}
}