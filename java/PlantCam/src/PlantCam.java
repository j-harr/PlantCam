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
			Node node = new Node();
			node.execute();
		}
		else {
			if(args[0].equals("-master")) {
				Master master = new Master();
				master.execute();
			} else if(args[0].equals("-node")) {
				Node node = new Node();
				node.execute();
			}
		}
	}
}
