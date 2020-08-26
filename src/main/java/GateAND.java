public class GateAND extends Device {
    @Override
    public boolean GetOutput()
    {
        boolean defaultIPin = iPins.get(0).GetOutput();
        for (Device iPin: iPins){
            defaultIPin = defaultIPin && iPin.GetOutput();
        }
        return defaultIPin;
    }
}
