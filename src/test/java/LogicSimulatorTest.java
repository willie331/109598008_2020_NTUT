import org.junit.*;

import java.math.BigInteger;
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
        assertEquals(3, logicSimulator.GetInputPinsSize()); // replace it with your own
        assertEquals(1, logicSimulator.GetOutputPinsSize()); // replace it with your own
        assertEquals(3, logicSimulator.GetCircuitsSize()); // replace it with your own


        // fail file path
        loadStatus = logicSimulator.Load(failFilePath);

        assertEquals(false, loadStatus);
    }

    @Test
    public void testGetSimulationResult(){
        LogicSimulator logicSimulator = new LogicSimulator();

        logicSimulator.Load(successFilePath);

        Vector<Boolean> inputValues = new Vector<>();
        inputValues.add(false);
        inputValues.add(true);
        inputValues.add(true);

        assertEquals("Simulation Result:\n" +
                "i i i | o\n" +
                "1 2 3 | 1\n" +
                "------+--\n" +
                "0 1 1 | 0\n", logicSimulator.GetSimulationResult(inputValues));

        inputValues = new Vector<>();
        inputValues.add(true);
        inputValues.add(false);
        inputValues.add(false);

        assertEquals("Simulation Result:\n" +
                "i i i | o\n" +
                "1 2 3 | 1\n" +
                "------+--\n" +
                "1 0 0 | 1\n", logicSimulator.GetSimulationResult(inputValues));
    }

    @Test
    public void testGetTruthTable(){
        LogicSimulator logicSimulator = new LogicSimulator();

        logicSimulator.Load(successFilePath);

        assertEquals("Truth table:\n" +
                "i i i | o\n" +
                "1 2 3 | 1\n" +
                "------+--\n" +
                "0 0 0 | 0\n" +
                "0 0 1 | 0\n" +
                "0 1 0 | 0\n" +
                "0 1 1 | 0\n" +
                "1 0 0 | 1\n" +
                "1 0 1 | 1\n" +
                "1 1 0 | 0\n" +
                "1 1 1 | 0\n", logicSimulator.GetTruthTable());

        logicSimulator = new LogicSimulator();

        logicSimulator.Load(successFilePath2);

        assertEquals("Truth table:\n" +
                "i i i | o o\n" +
                "1 2 3 | 1 2\n" +
                "------+----\n" +
                "0 0 0 | 0 1\n" +
                "0 0 1 | 0 1\n" +
                "0 1 0 | 0 1\n" +
                "0 1 1 | 0 1\n" +
                "1 0 0 | 1 0\n" +
                "1 0 1 | 1 0\n" +
                "1 1 0 | 0 1\n" +
                "1 1 1 | 0 1\n", logicSimulator.GetTruthTable());
    }
}
