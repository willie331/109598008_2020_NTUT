import org.junit.*;
import static org.junit.Assert.*;

public class DeviceFactoryTest {
    @Test
    public void TestCreateDeviceFromType(){
        DeviceFactory factory = new DeviceFactory();

        assertEquals(GateAND.class.getName(), factory.CreateDeviceFromType("1").getClass().getName());
        assertEquals(GateOR.class.getName(), factory.CreateDeviceFromType("2").getClass().getName());
        assertEquals(GateNOT.class.getName(), factory.CreateDeviceFromType("3").getClass().getName());
    }
}
