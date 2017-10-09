public class ExecTimer {
    private long startExecutionTime;
    private long endExecutionTime;

    /**
     * There is for sure a much better way to make a timer class.
     * I have no idea how though :P
     */
    public ExecTimer() {
        startExecutionTime = 0;
        endExecutionTime = 0;
    }

    /**
     * Holds the system nanoTime in the first variable. Needs to be called before stopTimer() or
     * else you don't know how timers work!
     */
    public void startTimer() {
        startExecutionTime = System.nanoTime();
    }

    /**
     * Displaying calculated time in nano seconds based on oth variables.
     */
    public void displayTime() {
        System.out.println("Execution time in timer: " + (endExecutionTime - startExecutionTime) + " ns");
    }

    /**
     * Holds the nanoTime in the second variable, throws a cool exception if you didn't start the timer.
     * @throws Exception
     */
    public void stopTimer() throws Exception {
        if (startExecutionTime == 0) {
            throw new Exception("You didn't start the timer! Start time is 0");//Epic failure of the brain
        }
        endExecutionTime = System.nanoTime();
    }

    /**
     * Returns the current duration based on the beginning of the timer in nano seconds as a Long to be used for calculation.
     */
    public Long getCurrentNanoDuration() {
        return System.nanoTime() - startExecutionTime;
    }

    /**
     * Returns the duration between the beginning and end time in nano seconds as a Long to be used for calculation.
     */
    public Long getNanoDuration() {
        return endExecutionTime - startExecutionTime;
    }
}
