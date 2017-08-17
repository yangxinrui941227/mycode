package map;

public class MapFactory {
	public static byte map[][][]={
		{
			{0,0,1,1,1,0,0,0,0},
			{0,0,1,3,1,0,0,0,0},
			{0,0,1,9,1,1,1,1,1},
			{1,1,1,2,9,2,9,3,1},
			{1,3,9,2,5,1,1,1,1},
			{1,1,1,1,2,1,0,0,0},
			{0,0,0,1,3,1,0,0,0},
			{0,0,0,1,1,1,0,0,0}
		}
	};
	static int count = map.length;
	public static byte[][] getMap(int grade){
		byte temp[][];
		if (grade>=0 && grade<count){
			temp = map[grade];
		} else{
			temp = map[0];
		}
		int row = temp.length;
		int col = temp[0].length;
		byte[][] result = new byte[row][col];
		for (int i = 0; i < row; i++){
			for (int j = 0; j < col; j++ ){
				result[i][j] = temp[i][j];
			}
		}
		return result;
	}
	public static int getCount(){
		return count;
	}
}
