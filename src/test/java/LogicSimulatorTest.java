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
        LogicSimulator logicSimulator = new LogicSimulator();

        logicSimulator.Load(successFilePath);

        Vector<Boolean> inputValues = new Vector<>();
        inputValues.add(false);
        inputValues.add(true);
        inputValues.add(true);
    }
}
