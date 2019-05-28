package burlap.domain.singleagent.blocksworld;

import burlap.domain.singleagent.blocksworld.BlocksWorldState;
import burlap.mdp.core.action.Action;
import burlap.mdp.core.state.State;
import burlap.mdp.singleagent.model.RewardFunction;
import burlap.domain.singleagent.blocksworld.BlocksWorldTerminalFunction;


/**
 * This class is used for defining reward functions in blocksworlds that are a function of positions of the blocks to which
 * the agent transitions. 
 * @author Siwen Yan (referencing James MacGlashan's)
 *
 */
public class BlocksWorldRewardFunction implements RewardFunction {

	protected double terminalReward;
	protected double stepReward;
	protected BlocksWorldTerminalFunction tf;
	
	/**
	 * no reward
	 */
	public BlocksWorldRewardFunction() {
		this.terminalReward = 0.;
		this.stepReward = 0.;
	}

	/**
	 * only terminal reward
	 */
	public BlocksWorldRewardFunction(BlocksWorldTerminalFunction tf, double tReward) {
		this.tf = tf;
		this.terminalReward = tReward;
		this.stepReward = 0.;
	}

	/**
	 * terminal and step rewards
	 */
	public BlocksWorldRewardFunction(BlocksWorldTerminalFunction tf, double tReward, double sReward) {
		this.tf = tf;
		this.terminalReward = tReward;
		this.stepReward = sReward;
	}

	public double getTerminalReward() {
		return this.terminalReward;
	}

	public void setTerminalReward(double tReward) {
		this.terminalReward = tReward;
	}

	public double getStepReward() {
		return this.stepReward;
	}

	public void setStepReward(double sReward) {
		this.stepReward = sReward;
	}
	
	@Override
	public double reward(State s, Action a, State sprime) {
		if (this.tf == null || !this.tf.isTerminal(sprime) || 
			((BlocksWorldState) s).toString().equals(((BlocksWorldState) sprime).toString())) {
			return this.stepReward;
		}
		return this.terminalReward;
	}

}
