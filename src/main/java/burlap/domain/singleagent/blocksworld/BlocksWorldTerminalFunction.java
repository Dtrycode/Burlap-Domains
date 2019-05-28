package burlap.domain.singleagent.blocksworld;

import burlap.domain.singleagent.blocksworld.BlocksWorld;
import burlap.domain.singleagent.blocksworld.BlocksWorldState;
import burlap.mdp.core.oo.state.ObjectInstance;
import burlap.mdp.core.TerminalFunction;
import burlap.mdp.core.state.State;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

/**
 * This class is used for setting a terminal function for BlocksWorlds that is based on the positions of blocks in the world.
 * @author Siwen Yan (referencing James MacGlashan's)
 *
 */
public class BlocksWorldTerminalFunction implements TerminalFunction {

	protected String op;
	protected String[] blocks;

	/**
	 * Initializes without any terminal states specified.
	 */
	public BlocksWorldTerminalFunction(){
		
	}

	/**
	 * Initializes with one of the three terminal states
	 * op can be stack, unstack, on, clear
	 */
	public BlocksWorldTerminalFunction(String op, String... blocks) {
		this.op = op;
		this.blocks = blocks;
	}

	/**
	 * Change terminal state selection
	 * op can be stack, unstack, on, clear
	 */
	public void updateTerminalState(String op, String... blocks) {
		this.op = op;
		this.blocks = blocks;
	}
	
	@Override
	public boolean isTerminal(State s) {
		if (this.op == null) {
			return false;
		}
		if (this.op.equals("stack")) {
			List<ObjectInstance> allBlocks = ((BlocksWorldState) s).objects();
			int cnt = 0;
			for (ObjectInstance b : allBlocks) {
				BlocksWorldBlock bwb = (BlocksWorldBlock) b;
				if (bwb.on.equals(BlocksWorld.TABLE_VAL)) {
					cnt++;
				}
			}
			if (cnt == 1) {
				return true;
			}
		} else if (this.op.equals("unstack")) {
			List<ObjectInstance> allBlocks = ((BlocksWorldState) s).objects();
			for (ObjectInstance b : allBlocks) {
				BlocksWorldBlock bwb = (BlocksWorldBlock) b;
				if (!bwb.clear) {
					return false;
				}
			}
			return true;
		} else if (this.op.equals("on")) {
			BlocksWorldBlock bwb = (BlocksWorldBlock) ((BlocksWorldState) s).object(this.blocks[0]);
			return bwb.on.equals(this.blocks[1]);
		} else if (this.op.equals("clear")) {
			BlocksWorldBlock bwb = (BlocksWorldBlock) ((BlocksWorldState) s).object(this.blocks[0]);
			return bwb.clear;
		}
		return false;

	}

}
