import java.util.Scanner;
import java.util.Vector;

public class Main
{
    private static void ShowToolbar(){
        System.out.println("1. Load logic circuit file");
        System.out.println("2. Simulation");
        System.out.println("3. Display truth table");
        System.out.println("4. Exit");
        System.out.print("Command:");
    }

    public static void main(String args[])
    {
        LogicSimulator logicSimulator = new LogicSimulator();
        while (true){
            ShowToolbar();
            Scanner scanner = new Scanner(System.in);
            String inputText = scanner.nextLine();

            if (inputText.equals("1")){
                System.out.print("Please key in a file path:");
                String filePath = scanner.nextLine();
                if (logicSimulator.Load(filePath)){
                    System.out.printf("Circuit: %d input pins, %d output pins and %d gates\n\n",
                                            logicSimulator.GetInputPinsSize(),
                                            logicSimulator.GetOutputPinsSize(),
                                            logicSimulator.GetCircuitsSize());
                }
                else{
                    System.out.println("File not found or file format error!!\n");
                }
            }
            else if(inputText.equals("2")){
                if (logicSimulator.IsFileLoadSuccess()){
                    int inputPinQuantity = logicSimulator.GetInputPinsSize();
                    Vector<Boolean> inputPinsValue = new Vector<Boolean>();

                    while (inputPinQuantity > 0){
                        System.out.printf("Please key in the value of input pin %d: ", logicSimulator.GetInputPinsSize() - inputPinQuantity + 1);
                        String inputValue = scanner.nextLine();

                        if (inputValue.matches("[0-1]")){
                            inputPinQuantity--;
                            inputPinsValue.add(inputValue.equals("1") ? true : false);
                        }
                        else{
                            System.out.println("The value of input pin must be 0/1");
                        }
                    }
                    System.out.println(logicSimulator.GetSimulationResult(inputPinsValue));
                }
                else{
                    System.out.println("Please load an lcf file, before using this operation.\n");
                }
            }
            else if(inputText.equals("3")){
                if (logicSimulator.IsFileLoadSuccess()){
                    System.out.println(logicSimulator.GetTruthTable());
                }
                else {
                    System.out.println("Please load an lcf file, before using this operation.\n");
                }
            }
            else if (inputText.equals("4")){
                System.out.println("Goodbye, thanks for using LS.");
                break;
            }
            else {
                System.out.println("Error Input Please scan 1-4\n");
            }

        }


    }
}
