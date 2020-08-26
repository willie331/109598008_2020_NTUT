import org.junit.*;
import static org.junit.Assert.*;

public class DeviceFactoryTest {
    @Test
    public void TestCreateDeviceFromType(){
        DeviceFactory factory = new DeviceFactory();

        assertEquals(GateAND.class.getName(), factory.createDeviceFromType("1").getClass().getName());
        assertEquals(GateOR.class.getName(), factory.createDeviceFromType("2").getClass().getName());
        assertEquals(GateNOT.class.getName(), factory.createDeviceFromType("3").getClass().getName());
    }
}
