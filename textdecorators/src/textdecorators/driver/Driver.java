package textdecorators.driver;

import java.io.IOException;

/**
 * @author Omkar Udawant
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

	public static void main(String[] args) throws IOException {

		/*
		 * As the build.xml specifies the arguments as arg1, arg2, arg3, arg4, arg5, arg6 in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if(!validateArgs(args)){
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		DriverHelper driverHelper = new DriverHelper();

		driverHelper.processInput(args);
	}

	/**
	 * Validate CommandLine Arguments
	 * @param args array of File names
	 * @return Boolean value which indicates if
	 * arguments are correct
	 */
	private static boolean validateArgs(String[] args){
		if(args.length != 5){
		 	return false;
		}

		for(int i = 0; i < args.length ; i++){
			if(args[i].equals("${arg"+(i+1)+"}")){
				return false;
			}
		}
		return true;
	}
}