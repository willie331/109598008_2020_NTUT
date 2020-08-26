public class IPin extends Device {
    private boolean inputValue;
    @Override
    public void SetInput(boolean value)
    {
        inputValue = value;
    }

    @Override
    public boolean GetOutput()
    {
        return inputValue;
    }
}
