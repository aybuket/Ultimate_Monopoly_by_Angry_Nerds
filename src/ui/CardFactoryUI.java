package ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class CardFactoryUI {



	public ImageIcon generateCardImage(String type, int num) {
		BufferedImage img=null;
		if(type.equals("chance")) {
			try {
				img = ImageIO.read(new File("cards/chance/chance"+num+".png"));
			} catch (IOException e) {
				//System.out.println("There is no image.");
			}

		}else if(type.equals("communitychest")) {
			try {
				img = ImageIO.read(new File("cards/commchest/commchest"+num+".png"));
			} catch (IOException e) {
//				System.out.println("There is no image.");
			}
//		}else if(type.equals("travelvoucher")) {
//			try {
//				img = ImageIO.read(new File("cards/travel/travel"+num+".png"));
//			} catch (IOException e) {
//				//System.out.println("There is no image.");
//			}
		}
		return new ImageIcon(img.getScaledInstance(400, 232,  java.awt.Image.SCALE_SMOOTH));
	}

	public BufferedImage generateTitleDeedCardImage(String name, boolean mortgage) {
		BufferedImage img=null;
			if(mortgage) {
				try {
					img = ImageIO.read(new File("titledeeds/"+name+" Mortgage.png"));
				} catch (IOException e) {
//					System.out.println("There is no image.");
				}
			}else {
				try {
					img = ImageIO.read(new File("titledeeds/"+name+".png"));
				} catch (IOException e) {
//					System.out.println("There is no image.");
				}
			}
		return img;
	}
}