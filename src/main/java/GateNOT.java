public class GateNOT extends Device {
    @Override
    public boolean GetOutput()
    {
        return !iPins.get(0).GetOutput();
    }
}
