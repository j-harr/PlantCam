/**
 * 
 * @author Jacob Harrison
 * 
 *
 */

public class PlantCam {
	public static void main(String[] args) throws Exception {
		if(args.length == 0) {
			System.out.println("Please use the format : "
					+ "java PlantCam -master  OR  java PlantCam -node");
			Node.execute();
		}
		else {
			if(args[0].equals("-master")) {
				Master.execute();
			} else if(args[0].equals("-node")) {
				Node.execute();
			}
		}
	}
}
