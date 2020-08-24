import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class LogicSimulator {
    private Vector<Device> circuits;
    private Vector<Device> iPins;
    private Vector<Device> oPins;

    public boolean Load(String filePath){
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            String str = "";
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String GetSimulationResult(Vector<Boolean> simulationResult){
        return "";
    }

    public String GetTruthTable(){
        return "";
    }
}
