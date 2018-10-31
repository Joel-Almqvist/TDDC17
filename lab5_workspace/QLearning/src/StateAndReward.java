public class StateAndReward {

	
	public final static int NUMBER_OF_ANGLE_STATES = 15;
	public final static double MAX_ANGLE_VALUE = Math.PI;
	
	public final static int NUMBER_OF_VY_STATES = 8;
	public final static double MAX_VY_VALUE = 6;
	
	public final static int NUMBER_OF_VX_STATES = 10;
	public final static double MAX_VX_VALUE = 5;
	
	// This marks the granularity of different rewards
	public final static int NUMBER_OF_REWARD_INTERVALS = 10;
	
	public final static String ANGLE_NAME_BASE = "ANGLE_";
	public final static String VY_NAME_BASE = " VY_";
	public final static String VX_NAME_BASE = " VX_";
	
	/* State discretization function for the angle controller */
	public static String getStateAngle(double angle, double vx, double vy) {
		return ANGLE_NAME_BASE+discretize(angle, NUMBER_OF_ANGLE_STATES, -Math.PI, Math.PI);
	}

	/* Reward function for the angle controller */
	public static double getRewardAngle(double angle, double vx, double vy) {
		//throw new OutOfMemoryError();
		/* TODO: IMPLEMENT THIS FUNCTION */
		
		int stateNumber = discretize(Math.abs(angle), NUMBER_OF_ANGLE_STATES, 0, Math.PI);
		if(stateNumber == 1) {
			// Give significantly higher reward to the correct state
			return NUMBER_OF_ANGLE_STATES*10;
		}
		return (10*NUMBER_OF_ANGLE_STATES)/stateNumber;
		//stateNumber = Math.abs(stateNumber - NUMBER_OF_ANGLE_STATES/2);
	}

	/* State discretization function for the full hover controller */
	public static String getStateHover(double angle, double vx, double vy) {
		// Somewhat arbitrary choice of max/min speed of -5 and 5
		int vyStates = discretize(vy, NUMBER_OF_VY_STATES, -MAX_VY_VALUE, MAX_VY_VALUE);
		int vxStates = discretize(vx, NUMBER_OF_VX_STATES, -MAX_VX_VALUE, MAX_VX_VALUE);
		int angleStates = discretize(angle, NUMBER_OF_ANGLE_STATES, -Math.PI, Math.PI);
		//Add the separator "-" to guarantee a unique name for state+action combination
		return ANGLE_NAME_BASE+angleStates+VY_NAME_BASE+vyStates+VX_NAME_BASE+vxStates+"-";
	}

	/* Reward function for the full hover controller */
	public static double getRewardHover(double angle, double vx, double vy) {
		double vyStates = discretize(Math.abs(vy), NUMBER_OF_VY_STATES, 0, MAX_VY_VALUE);
		double vxStates = discretize(Math.abs(vx), NUMBER_OF_VX_STATES, 0, MAX_VX_VALUE);
		double angleStates = discretize(Math.abs(angle), NUMBER_OF_ANGLE_STATES, 0, MAX_ANGLE_VALUE);
		double reward = (1 - vyStates/NUMBER_OF_VY_STATES) + (1 - angleStates/NUMBER_OF_ANGLE_STATES) + (1 - vxStates/NUMBER_OF_VX_STATES); 
		return reward;
		
	}

	// ///////////////////////////////////////////////////////////
	// discretize() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 1 and nrValues-2 is returned.
	//
	// Use discretize2() if you want a discretization method that does
	// not handle values lower than min and higher than max.
	// ///////////////////////////////////////////////////////////
	public static int discretize(double value, int nrValues, double min,
			double max) {
		if (nrValues < 2) {
			return 0;
		}

		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * (nrValues - 2)) + 1;
	}

	// ///////////////////////////////////////////////////////////
	// discretize2() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 0 and nrValues-1 is returned.
	// ///////////////////////////////////////////////////////////
	public static int discretize2(double value, int nrValues, double min,
			double max) {
		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * nrValues);
	}

}
