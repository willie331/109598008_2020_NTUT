import org.junit.*;

import java.util.Vector;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class LogicSimulatorTest
{
    String successFilePath;
    String successFilePath2;
    String failFilePath;

    @Before
    public void setUp()
    {
        successFilePath = "src/File1.lcf";
        successFilePath2 = "src/File2.lcf";
        failFilePath = "src/nothing.lcf";
    }

    @Test
    public void testLoad(){
        LogicSimulator logicSimulator = new LogicSimulator();

        // success file path
        Boolean loadStatus = logicSimulator.Load(successFilePath);

        assertEquals(true, loadStatus);

        // fail file path
        loadStatus = logicSimulator.Load(failFilePath);

        assertEquals(false, loadStatus);
    }

    @Test
    public  void testGetSimulationResult(){
        Vector<Device> test = new Vector<Device>();
        test.setSize(2);
        assertEquals(2, test.size());

        test.add(0, new IPin());

        assertEquals(IPin.class.getName(), test.elementAt(0).getClass().getName());
        assertEquals(2, test.size());
    }
}
