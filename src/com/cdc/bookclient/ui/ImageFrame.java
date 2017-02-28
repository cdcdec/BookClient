package com.cdc.bookclient.ui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 查看大图片的JFrame
 * @author aaron
 *
 */
public class ImageFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2056477105903054112L;
	JLabel imageLabel;
	
	public ImageFrame(ImageIcon image) {
		JPanel mainPanel = new JPanel();
		this.imageLabel = new JLabel();
		this.imageLabel.setIcon(image);
		mainPanel.add(this.imageLabel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(mainPanel);
		this.pack();
		this.setVisible(true);
	}

	public void refresh(ImageIcon image) {
		this.imageLabel.setIcon(image);
		this.setVisible(true);
		super.repaint();
	}
}

