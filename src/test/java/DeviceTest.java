import org.junit.*;
import static org.junit.Assert.*;

public class DeviceTest
{
    @Test
    public void testDevicePolymorphism(){
        Device device = new IPin();
        assertEquals(IPin.class.getName(), device.getClass().getName());

        device = new OPin();
        assertEquals(OPin.class.getName(), device.getClass().getName());

        device = new GateOR();
        assertEquals(GateOR.class.getName(), device.getClass().getName());

        device = new GateAND();
        assertEquals(GateAND.class.getName(), device.getClass().getName());

        device = new GateNOT();
        assertEquals(GateNOT.class.getName(), device.getClass().getName());
    }

    @Test
    public void testIPinAndOPin()
    {
        // 0 = 0
        IPin iPin = new IPin();
        iPin.SetInput(false);

        OPin oPin = new OPin();
        oPin.AddInputPin(iPin);

        assertEquals(false, oPin.GetOutput());

        // 1 = 1
        iPin = new IPin();
        iPin.SetInput(true);

        oPin = new OPin();
        oPin.AddInputPin(iPin);

        assertEquals(true, oPin.GetOutput());
    }

    @Test
    public  void testGateNOT(){
        // NOT 0 = 1
        IPin iPin = new IPin();
        iPin.SetInput(false);

        GateNOT gateNOT = new GateNOT();
        gateNOT.AddInputPin(iPin);

        assertEquals(true, gateNOT.GetOutput());

        // NOT 1 = 0
        iPin = new IPin();
        iPin.SetInput(true);

        gateNOT = new GateNOT();
        gateNOT.AddInputPin(iPin);

        assertEquals(false, gateNOT.GetOutput());
    }

    @Test
    public void testGateAND(){
        // 0 AND 0 = 0
        IPin iPin1 = new IPin();
        IPin iPin2 = new IPin();
        iPin1.SetInput(false);
        iPin2.SetInput(false);

        GateAND gateAND = new GateAND();
        gateAND.AddInputPin(iPin1);
        gateAND.AddInputPin(iPin2);

        assertEquals(false, gateAND.GetOutput());

        // 0 AND 1 = 0
        iPin1 = new IPin();
        iPin2 = new IPin();
        iPin1.SetInput(false);
        iPin2.SetInput(true);

        gateAND = new GateAND();
        gateAND.AddInputPin(iPin1);
        gateAND.AddInputPin(iPin2);

        assertEquals(false, gateAND.GetOutput());

        // 1 AND 0 = 0
        iPin1 = new IPin();
        iPin2 = new IPin();
        iPin1.SetInput(true);
        iPin2.SetInput(false);

        gateAND = new GateAND();
        gateAND.AddInputPin(iPin1);
        gateAND.AddInputPin(iPin2);

        assertEquals(false, gateAND.GetOutput());

        // 1 AND 1 = 1
        iPin1 = new IPin();
        iPin2 = new IPin();
        iPin1.SetInput(true);
        iPin2.SetInput(true);

        gateAND = new GateAND();
        gateAND.AddInputPin(iPin1);
        gateAND.AddInputPin(iPin2);

        assertEquals(true, gateAND.GetOutput());
    }

    @Test
    public void testGateOR(){
        // 0 OR 0 = 0
        IPin iPin1 = new IPin();
        IPin iPin2 = new IPin();
        iPin1.SetInput(false);
        iPin2.SetInput(false);

        GateOR gateOR = new GateOR();
        gateOR.AddInputPin(iPin1);
        gateOR.AddInputPin(iPin2);

        assertEquals(false, gateOR.GetOutput());

        // 0 OR 1 = 1
        iPin1 = new IPin();
        iPin2 = new IPin();
        iPin1.SetInput(false);
        iPin2.SetInput(true);

        gateOR = new GateOR();
        gateOR.AddInputPin(iPin1);
        gateOR.AddInputPin(iPin2);

        assertEquals(true, gateOR.GetOutput());

        // 1 OR 0 = 1
        iPin1 = new IPin();
        iPin2 = new IPin();
        iPin1.SetInput(true);
        iPin2.SetInput(false);

        gateOR = new GateOR();
        gateOR.AddInputPin(iPin1);
        gateOR.AddInputPin(iPin2);

        assertEquals(true, gateOR.GetOutput());

        // 1 OR 1 = 1
        iPin1 = new IPin();
        iPin2 = new IPin();
        iPin1.SetInput(true);
        iPin2.SetInput(true);

        gateOR = new GateOR();
        gateOR.AddInputPin(iPin1);
        gateOR.AddInputPin(iPin2);

        assertEquals(true, gateOR.GetOutput());

    }
}
