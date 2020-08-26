import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class LogicSimulator {
    private Vector<Device> circuits;
    private Vector<Device> iPins;
    private Vector<Device> oPins;
    private boolean isFileLoadSuccess = false;

    private void Initialize(){
        circuits = new Vector<Device>();
        iPins = new Vector<Device>();
        oPins = new Vector<Device>();
    }

    // ex: -1 -> 0, -2 -> 1
    private int ConvertStringToPositiveInteger(String convertedString){
        int result = Math.abs(Integer.parseInt(convertedString));

        return result - 1;
    }

    private void CreateIPin(Vector<String> inputCircuit){
        for (int i = 0; i < Integer.parseInt(inputCircuit.get(0)); i++){
            iPins.add(new IPin());
        }
    }

    private void CreateCircuit(Vector<String> inputCircuit){
        for (int i = 2; i < inputCircuit.size(); i++) {
            String[] splitInputCircuit = inputCircuit.get(i).split(" ");
            DeviceFactory deviceFactory = new DeviceFactory();
            Device nowGate = deviceFactory.createDeviceFromType(splitInputCircuit[0]);

            circuits.add(nowGate);
        }
    }

    private void ConnectCircuit(Vector<String> inputCircuit){
        for (int i = 2; i < inputCircuit.size(); i++){
            String[] spliteInputCircuit = inputCircuit.get(i).split(" ");
            Device nowGate = circuits.get(i-2);

            for (int j = 0; j < spliteInputCircuit.length; j++){
                if (spliteInputCircuit[j].matches("-[1-9]\\d*")){
                    int index = ConvertStringToPositiveInteger(spliteInputCircuit[j]);
                    nowGate.addInputPin(iPins.get(index));
                }
                else if (spliteInputCircuit[j].matches("[1-9]\\d*\\.[0-9]+")) {
                    String splitInputPin = spliteInputCircuit[j].split("\\.")[0];
                    nowGate.addInputPin(circuits.get(ConvertStringToPositiveInteger(splitInputPin)));
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
        Initialize();
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            Vector<String> inputCircuit = new Vector<String>();

            while (in.ready()) {
                inputCircuit.add(in.readLine());
            }

            in.close();

            if (inputCircuit.size() > 2){
                // input pins
                CreateIPin(inputCircuit);
                // input gates
                CreateCircuit(inputCircuit);
                // connect circuit
                ConnectCircuit(inputCircuit);
                // find oPin
                ChooseOPin();
            }
            isFileLoadSuccess = true;
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // 建立輸出的表頭
    private void SetHeader(StringBuilder outputSimulationResult){
        // i i i
        for (int i = 0; i < iPins.size(); i++){
            outputSimulationResult.append("i ");
        }

        // i i i |
        outputSimulationResult.append("|");

        // i i i | o
        for (int i = 0; i < oPins.size(); i++){
            outputSimulationResult.append(" o");
        }
        outputSimulationResult.append("\n");

        // i i i | o
        // 1 2 3
        for (int i = 0; i < iPins.size(); i++){
            outputSimulationResult.append(Integer.toString(i + 1) + " ");
        }

        // i i i | o
        // 1 2 3 |
        outputSimulationResult.append("|");

        // i i i | o
        // 1 2 3 | 1
        for (int i = 0; i < oPins.size(); i++){
            outputSimulationResult.append(" " + Integer.toString(i + 1));
        }
        outputSimulationResult.append("\n");

        // i i i | o
        // 1 2 3 | 1
        // ------
        for (int i = 0; i < iPins.size(); i++){
            outputSimulationResult.append("--");
        }

        // i i i | o
        // 1 2 3 | 1
        // ------+
        outputSimulationResult.append("+");

        // i i i | o
        // 1 2 3 | 1
        // ------+--
        for (int i = 0; i < oPins.size(); i++){
            outputSimulationResult.append("--");
        }
        outputSimulationResult.append("\n");
    }

    public String GetSimulationResult(Vector<Boolean> inputSimulationResult){
        StringBuilder outputSimulationResult = new StringBuilder();
        outputSimulationResult.append("Simulation Result:" + "\n");

        for (int i = 0; i < inputSimulationResult.size(); i++){
            iPins.get(i).setInput(inputSimulationResult.get(i));
        }

        SetHeader(outputSimulationResult);

        for (int i = 0; i< iPins.size(); i++){
            outputSimulationResult.append((iPins.get(i).getOutput() ? "1" : "0") + " ");
        }

        outputSimulationResult.append("|");

        for (int i = 0; i < oPins.size(); i++){
            outputSimulationResult.append(" " + (oPins.get(i).getOutput() ? "1" : "0"));
        }

        outputSimulationResult.append("\n");

        return outputSimulationResult.toString();
    }

    // 建立真值表
    private void CreateTruthTable(Vector<String> truthTable){
        int nBit = iPins.size(); //幾個輸入就幾個bit
        int rows = (int) Math.pow(2,nBit);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = nBit - 1; j >= 0; j--) {
                stringBuilder.append((i / (int)Math.pow(2, j)) % 2 + " ");
            }
            truthTable.add(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
    }

    public String GetTruthTable(){
        Vector<String> truthTable = new Vector<String>();
        StringBuilder outputResult = new StringBuilder();

        outputResult.append("Truth table:" + "\n");

        CreateTruthTable(truthTable);

        SetHeader(outputResult);

        for (int i = 0; i < truthTable.size(); i++){
            String[] splitBinaryNumber = truthTable.get(i).split(" ");
            for (int iPinsIdex = 0; iPinsIdex < iPins.size(); iPinsIdex++){
                outputResult.append(splitBinaryNumber[iPinsIdex] + " ");
                if (splitBinaryNumber[iPinsIdex].equals("1")){
                    iPins.get(iPinsIdex).setInput(true);
                }
                else if (splitBinaryNumber[iPinsIdex].equals("0")){
                    iPins.get(iPinsIdex).setInput(false);
                }
            }
            outputResult.append("|");

            for (int oPinsIndex = 0; oPinsIndex < oPins.size(); oPinsIndex++){
                outputResult.append(" " + (oPins.get(oPinsIndex).getOutput() ? "1" : "0"));
            }
            outputResult.append("\n");
        }

        return outputResult.toString();
    }

    public int GetInputPinsSize(){
        return iPins.size();
    }

    public int GetOutputPinsSize(){
        return oPins.size();
    }

    public int GetCircuitsSize(){
        return circuits.size();
    }

    public boolean IsFileLoadSuccess(){
        return isFileLoadSuccess;
    }
}
