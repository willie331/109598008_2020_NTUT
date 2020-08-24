import org.junit.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class LogicSimulatorTest
{
    String file1Path;
    String file2Path;

    @Before
    public void setUp()
    {
        file1Path = "src/File1.lcf";
        file2Path = "src/File2.lcf";
    }

    @Test
    public void testLoad(){
        LogicSimulator logicSimulator = new LogicSimulator();

        Boolean loadStatus = logicSimulator.Load(file1Path);

        assertEquals(true,loadStatus);
    }
}
