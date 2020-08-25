public class DeviceFactory {
    public Device createDeviceFromType(String type){
        switch(type) {
            case "1":
                return new GateAND();
            case "2":
                return new GateOR();
            case "3":
                return new GateNOT();
            default:
                return new Device();
        }
    }
}
