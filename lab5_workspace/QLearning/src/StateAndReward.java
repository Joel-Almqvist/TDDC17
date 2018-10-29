public class StateAndReward {

	
	// NUMBER_OF_ANGLE_STATES is actually 2 lower than how many states we truly have
	public final static int NUMBER_OF_ANGLE_STATES = 20;
	public final static String ANGLE_NAME_BASE = "ANGLE";
	
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
			return NUMBER_OF_ANGLE_STATES*20;
		}
		return (10*NUMBER_OF_ANGLE_STATES)/stateNumber;
		//stateNumber = Math.abs(stateNumber - NUMBER_OF_ANGLE_STATES/2);
	}

	/* State discretization function for the full hover controller */
	public static String getStateHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */

		String state = "OneStateToRuleThemAll2";
		
		return state;
	}

	/* Reward function for the full hover controller */
	public static double getRewardHover(double angle, double vx, double vy) {

		/* TODO: IMPLEMENT THIS FUNCTION */
		
		double reward = 0;

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
