package util;

import java.awt.Image;
import java.awt.Toolkit;

public enum MapEnum {
	WALL((byte) 1,"image\\Wall_Black.png"), 
	BOX((byte) 2,"image\\CrateDark_Beige.png"),
	BOXONEND((byte) 3,"image\\EndPoint_Red.png"),
	END((byte) 4,"image\\CrateDark_Red.png"),
	MANDOWN((byte) 5,"image\\Character5.png"),
	MANLEFT((byte) 6,"image\\Character10.png"), 
	MANRIGHT((byte) 7,"image\\Character3.png"),
	MANUP((byte) 8,"image\\Character8.png"), 
	GRASS((byte) 9,"image\\GroundGravel_Grass.png"), 
	MANDOWNONEND((byte) 10,"image\\Character4.png"),
	MANLEFTONEND((byte) 11,"image\\Character1.png"),
	MANRIGHTONEND((byte) 12,"image\\Character2.png"),
	MANUPONEND((byte) 13,"image\\Character7.png");

	private byte key;
	private Image pic;
	private MapEnum(byte key,String path) {
		this.key = key;
		this.pic = Toolkit.getDefaultToolkit().getImage(path);
	}

	public byte getKey(){
		return this.key;
	}
	public static Image getImage(byte index){
			for (MapEnum m : MapEnum.values()) {
				if (m.getKey() == index){
					return m.pic;
				}
			}
			return null;
	}
}
