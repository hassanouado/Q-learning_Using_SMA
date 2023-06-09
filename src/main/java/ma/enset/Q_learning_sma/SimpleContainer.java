package ma.enset.Q_learning_sma;


import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import ma.enset.GLUtils;
import jade.core.Runtime;
import jade.wrapper.ControllerException;

public class SimpleContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        agentContainer.createNewAgent("masterAgent", MasterAgent.class.getName(), new Object[]{}).start();
        for (int i = 0; i < GLUtils.NUMBER_OF_AGENTS; i++) {
            try {
                AgentController serverAgent= agentContainer.createNewAgent("agent" + i, IslandAgent.class.getName(),new Object[]{0,i});
                serverAgent.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
