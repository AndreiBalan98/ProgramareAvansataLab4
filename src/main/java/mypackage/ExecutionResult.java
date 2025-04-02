package mypackage;

public class ExecutionResult {
    private final TestType testType;
    private final int locations;
    private final int connections;
    private final long executionTime;

    public ExecutionResult(TestType testType, int locations, int connections, long executionTime) {
        this.testType = testType;
        this.locations = locations;
        this.connections = connections;
        this.executionTime = executionTime;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public TestType getTestType() {
        return testType;
    }

    @Override
    public String toString() {
        return testType.toString() + ": " + locations + " locatii - " + connections + " conexiuni - " + executionTime + " ms";
    }
}