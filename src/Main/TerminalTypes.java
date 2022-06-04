package Main;

import Main.Buildings.*;

public enum TerminalTypes {
    BUSS_TERMINAL, SHIPPING_PORT, TRAIN_STATION, AIRPORT;

    static TerminalTypes terminalToEnum (Terminal terminal) {

        if (terminal instanceof BussTerminal)
            return BUSS_TERMINAL;

        else if (terminal instanceof ShippingPort)
            return SHIPPING_PORT;

        else if (terminal instanceof AirPort)
            return AIRPORT;

        else if (terminal instanceof TrainStation)
            return TRAIN_STATION;

        throw new RuntimeException();
    }

    static Terminal enumToTerminal (String terminalKind, String name, City city){
        TerminalTypes terminalType = TerminalTypes.valueOf(terminalKind);

        if (terminalType == TerminalTypes.BUSS_TERMINAL)
            return new BussTerminal(name, city, null);

        else if (terminalType == TerminalTypes.SHIPPING_PORT)
            return new ShippingPort(name, city, null);

        else if (terminalType == TerminalTypes.AIRPORT)
            return new AirPort(name, city, null);

        else if (terminalType == TerminalTypes.TRAIN_STATION)
            return new TrainStation(name, city, null);

        throw new RuntimeException();
    }
}
