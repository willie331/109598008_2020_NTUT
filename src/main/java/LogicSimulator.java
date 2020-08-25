import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class LogicSimulator {
    private Vector<Device> circuits = new Vector<Device>();
    private Vector<Device> iPins = new Vector<Device>();
    private Vector<Device> oPins = new Vector<Device>();

    private int ConvertStringToPositiveInteger(String convertedString){
        int result = Math.abs(Integer.parseInt(convertedString));

        return result - 1;
    }

    private void CreateCircuit(Vector<String> inputCircuit){
        for (int i = 2; i < inputCircuit.size(); i++) {
            String[] spliteInputCircuit = inputCircuit.get(i).split(" ");
            DeviceFactory deviceFactory = new DeviceFactory();
            Device nowGate = deviceFactory.createDeviceFromType(spliteInputCircuit[0]);

            circuits.add(nowGate);
        }
    }

    private void ConnectCircuit(Vector<String> inputCircuit){
        for (int i = 2; i < inputCircuit.size(); i++){
            String[] spliteInputCircuit = inputCircuit.get(i).split(" ");
            Device nowGate = circuits.get(i-2);

            for (int j = 0; j < spliteInputCircuit.length; j++){
                if (spliteInputCircuit[j].matches("-[1-9]\\d*")){
                    Integer index = ConvertStringToPositiveInteger(spliteInputCircuit[j]);
                    nowGate.addInputPin(iPins.get(index));
                }
                else if (spliteInputCircuit[j].matches("[1-9]\\d*\\.[0-9]+")) {
                    String spliteInputPin = spliteInputCircuit[j].split("\\.")[0];
                    nowGate.addInputPin(circuits.get(ConvertStringToPositiveInteger(spliteInputPin)));
                }
            }
        }
    }

    private void ChooseOPin(){
        for (Device device: circuits){
            if (!device.GetIsOtherDeviceIPin()){
                oPins.add(device);
            }
        }
    }

    public boolean Load(String filePath){
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            Vector<String> inputCircuit = new Vector<String>();

            while (in.ready()) {
                inputCircuit.add(in.readLine());
            }

            in.close();

            if (inputCircuit.size() > 2){
                // input pins
                for (int i = 0; i < Integer.parseInt(inputCircuit.get(0)); i++){
                    iPins.add(new IPin());
                }

                // input gates
                CreateCircuit(inputCircuit);

                // connect circuit
                ConnectCircuit(inputCircuit);

                // find oPin
                ChooseOPin();
            }
//            System.out.println(inputCircuit);
//            System.out.println(oPins.get(0).getOutput());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String GetSimulationResult(Vector<Boolean> inputSimulationResult){
        String outputSimulationResult = "Simulation Result:\\n";

        for (int i = 0; i < inputSimulationResult.size(); i++){
            iPins.get(i).setInput(inputSimulationResult.get(i));
        }

        // i i i
        for (int i = 0; i < inputSimulationResult.size(); i++){
            outputSimulationResult += "i ";
        }

        // i i i |
        outputSimulationResult += "|";

        // i i i | o
        for (int i = 0; i < oPins.size(); i++){
            outputSimulationResult += " o";
        }

        outputSimulationResult += "\n";

        // i i i | o
        // 1 2 3
        for (int i = 0; i < inputSimulationResult.size(); i++){
            outputSimulationResult += Integer.toString(i + 1) + " ";
        }

        // i i i | o
        // 1 2 3 |
        outputSimulationResult += "|";

        // i i i | o
        // 1 2 3 | 1
        for (int i = 0; i < oPins.size(); i++){
            outputSimulationResult += " " + Integer.toString(i + 1);
        }

        outputSimulationResult += "\n";

        // i i i | o
        // 1 2 3 | 1
        // ------
        for (int i = 0; i < inputSimulationResult.size(); i++){
            outputSimulationResult += "--";
        }

        // i i i | o
        // 1 2 3 | 1
        // ------+
        outputSimulationResult += "+";

        // i i i | o
        // 1 2 3 | 1
        // ------+--
        for (int i = 0; i < oPins.size(); i++){
            outputSimulationResult += "--";
        }

        outputSimulationResult += "\n";



        return outputSimulationResult;
    }

    public String GetTruthTable(){
        return "";
    }
}
