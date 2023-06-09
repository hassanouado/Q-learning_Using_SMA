package ma.enset.Q_learning_sequentielle;

import java.util.Random;

import static ma.enset.GLUtils.*;

public class Q_learning {

    private double[][] qTable = new double[GRID_SIZE*GRID_SIZE][ACTIONS_SIZE];
    private int stateI;
    private int stateJ;

    public Q_learning(){
    }

    private void resetState(){
        stateI = 0;
        stateJ = 0;
    }

    private int chooseAction(double epsilon){
        Random random = new Random();
        int bestAction = 0;
        double bestQValue = Double.MIN_VALUE;

        if(random.nextDouble() < epsilon){
            bestAction = random.nextInt(ACTIONS_SIZE);
        }else{
            int state = stateI * GRID_SIZE + stateJ;
            for (int i=0; i<ACTIONS_SIZE; i++){
                if(qTable[state][i] > bestQValue){
                    bestQValue = qTable[state][i];
                    bestAction = i;
                }
            }
        }

        return bestAction;
    }

    private boolean finished(){
        return GRID[stateI][stateJ] == 1;
    }

    private int executeAction(int action){
        stateI = Math.max(0, Math.min(stateI + ACTIONS[action][0], GRID_SIZE-1));
        stateJ = Math.max(0, Math.min(stateJ + ACTIONS[action][1], GRID_SIZE-1));
        return stateI * GRID_SIZE + stateJ;
    }

    private void printQTable() {
        System.out.println("*********** qTable ***********");
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int state = i * GRID_SIZE + j;
                System.out.print("État (" + i + "," + j + "): ");

                // Afficher les valeurs Q pour chaque action
                for (int action = 0; action < ACTIONS_SIZE; action++) {
                    System.out.print("Action " + action + ": " + qTable[state][action] + " | ");
                }
                System.out.println();
            }
        }

        // Afficher le parcours de l'agent
        resetState();
        System.out.println("---- Actions : ");
        resetState();
        System.out.println("| States |   Actions    |  ");
        System.out.println("-------------------------");
        while (!finished()){
            int act= chooseAction(0);
            System.out.println("| "+stateI+" | "+stateJ+" |    action : "+act+" |");
            executeAction(act);
        }
    }

    public void runQLearning(){
        int iteration = 0;
        int currentState;
        int newState;
        resetState();
        while (iteration < MAX_EPOCHS){
            resetState();
            while (!finished()){
                currentState = stateI * GRID_SIZE + stateJ;
                int action = chooseAction(0.3);

                newState = executeAction(action);
                int action2 = chooseAction(0);

                qTable[currentState][action] = qTable[currentState][action]
                        + ALPHA * (GRID[stateI][stateJ]
                        + GAMMA * qTable[newState][action2]
                        - qTable[currentState][action]);
            }
            iteration++;
        }
        printQTable();
    }
}
