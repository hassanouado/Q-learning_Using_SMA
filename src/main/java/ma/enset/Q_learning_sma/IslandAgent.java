package ma.enset.Q_learning_sma;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import ma.enset.GLUtils;

import java.util.Random;

public class IslandAgent extends Agent {
    double[][] qTable = new double[GLUtils.GRID_SIZE * GLUtils.GRID_SIZE][GLUtils.ACTIONS_SIZE];
    int stateI = 0;
    int stateJ = 0;

    @Override
    public void setup() {
        System.out.println("agent " + getAID().getName() + " est prêt.");
        SequentialBehaviour sb = new SequentialBehaviour();

        sb.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                resetState();
            }
        });
        sb.addSubBehaviour(new Behaviour() {
            int currentState;
            int newState;
            int iteration;

            @Override
            public void action() {
                runQLearning();
            }

            @Override
            public boolean done() {
                return iteration >= GLUtils.MAX_EPOCHS || finished();
            }
        });

        sb.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                //printQTable();
                sendQTable();
            }
        });
        addBehaviour(sb);
    }

    private void resetState() {
        stateI = 0;
        stateJ = 0;
    }

    private int chooseAction(double epsilon) {
        Random random = new Random();
        int bestAction = 0;
        double bestQValue = Double.MIN_VALUE;
        if (random.nextDouble() < epsilon) {
            bestAction = random.nextInt(GLUtils.ACTIONS_SIZE);
        } else {
            int state = stateI * GLUtils.GRID_SIZE + stateJ;
            for (int i = 0; i < GLUtils.ACTIONS_SIZE; i++) {
                if (qTable[state][i] > bestQValue) {
                    bestQValue = qTable[state][i];
                    bestAction = i;
                }
            }
        }
        return bestAction;
    }

    private boolean finished() {
        return GLUtils.GRID[stateI][stateJ] == 1;
    }

    private int executeAction(int action) {
        stateI = Math.max(0, Math.min(stateI + GLUtils.ACTIONS[action][0], GLUtils.GRID_SIZE - 1));
        stateJ = Math.max(0, Math.min(stateJ + GLUtils.ACTIONS[action][1], GLUtils.GRID_SIZE - 1));
        return stateI * GLUtils.GRID_SIZE + stateJ;
    }

    private void printBestPath() {
        System.out.println("Agent "+getAID().getName()+" is starting from ("+stateI+","+stateJ+")");
        System.out.println(" ----- QTable results :");
        for (double []lines:qTable){
            System.out.print("[");
            for (double q_value :lines) {
                System.out.print(q_value+" , ");
            }
            System.out.println("]");
        }
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

    public void sendQTable() {
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("QLearning");
        dfd.addServices(sd);
        DFAgentDescription[] result = null;
        try {
            result = DFService.search(this, dfd);
        } catch (FIPAException e) {
            throw new RuntimeException(e);
        }
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(result[0].getName());
        for (double[] doubles : qTable) {
            for (double aDouble : doubles) {
                msg.setContent(msg.getContent() + aDouble + ",");
            }
            msg.setContent(msg.getContent() + "\n");
        }
        send(msg);
    }

    @Override
    public void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            throw new RuntimeException(e);
        }
    }

    public void runQLearning() {
        int iteration = 0;
        int currentState;
        int newState;
        resetState();
        while (iteration < GLUtils.MAX_EPOCHS) {
            resetState();
            while (!finished()) {
                currentState = stateI * GLUtils.GRID_SIZE + stateJ;
                int action = chooseAction(0.3);

                newState = executeAction(action);
                int action2 = chooseAction(0);

                qTable[currentState][action] = qTable[currentState][action]
                        + GLUtils.ALPHA * (GLUtils.GRID[stateI][stateJ]
                        + GLUtils.GAMMA * qTable[newState][action2]
                        - qTable[currentState][action]
                );
            }
            iteration++;
        }
        printBestPath();
    }
}
