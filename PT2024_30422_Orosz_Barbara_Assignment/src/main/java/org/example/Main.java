package org.example;

import org.example.view.SimulationView;

public class Main {

    public static void main(String[] args) {

        SimulationView viewsim = new SimulationView();
        //    viewsim.setVisibleCheck();
        viewsim.setUserMode();
        viewsim.initialize();
    }
}
