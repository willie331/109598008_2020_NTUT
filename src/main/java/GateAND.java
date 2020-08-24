public class GateAND extends Device {
    @Override
    public boolean getOutput()
    {
        boolean defaultIPin = iPins.get(0).getOutput();
        for (Device iPin: iPins){
            defaultIPin = defaultIPin && iPin.getOutput();
        }
        return defaultIPin;
    }
}
