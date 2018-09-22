
public class PlantCam {
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Please use the format : "
					+ "java PlantCam -m  OR  java PlantCam -n");
			Node.execute();
		}
		else {
			if(args[0].equals("-m")) {
				Master.execute();
			} else if(args[0].equals("-n")) {
				Node.execute();
			}
		}
	}
}
