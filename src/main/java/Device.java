import java.util.Vector;

public class Device {
    protected Vector<Device> iPins;
    private boolean isOtherDeviceIPin = false;

    public Device()
    {
        iPins = new Vector<>();
    }

    public void AddInputPin(Device iPin)
    {
        iPin.isOtherDeviceIPin = true;
        iPins.add(iPin);
    }

    public void SetInput(boolean value)
    {
        // complete this method by yourself
    }

    public boolean GetOutput()
    {
        // complete this method by yourself
        return false;
    }

    public boolean GetIsOtherDeviceIPin(){
        return isOtherDeviceIPin;
    }
}
