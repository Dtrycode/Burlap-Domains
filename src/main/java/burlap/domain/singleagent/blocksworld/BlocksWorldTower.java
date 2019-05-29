package burlap.domain.singleagent.blocksworld;

import burlap.mdp.core.oo.state.OOStateUtilities;
import burlap.mdp.core.oo.state.ObjectInstance;
import burlap.mdp.core.state.annotations.ShallowCopyState;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

import static burlap.domain.singleagent.blocksworld.BlocksWorld.*;

/**
 * @author Siwen Yan (referencing James MacGlashan's).
 */
@ShallowCopyState
public class BlocksWorldTower implements ObjectInstance {

	protected String name = "tower";
	protected int height = 0;
	protected List<BlocksWorldBlock> top = new LinkedList<BlocksWorldBlock>();

	private final List<Object> keys = Arrays.<Object>asList(VAR_HEIGHT, VAR_TOP);

	public BlocksWorldTower() {
	}

	public BlocksWorldTower(String name) {
		this.name = name;
	}

	public BlocksWorldTower(String name, int height, List<BlocksWorldBlock> top) {
		this.name = name;
		this.height = height;
		this.top = top;
	}

	public void addBlockTop(BlocksWorldBlock b) {
		if (b != null) {
			this.height++;
			this.top.add(0, b);
			b.tower = this;
		}
	}

	public void removeBlockTop() {
		if (this.height > 0) {
			this.height--;
			this.top.remove(0);
		}
	}

	public BlocksWorldBlock getTopBlock() {
		if (top.isEmpty()) {
			return null;
		} else {
			return top.get(0);
		}
	}

	public int getHeight() {
		return this.height;
	}

	public List<BlocksWorldBlock> getBlocks() {
		return this.top;
	}

	@Override
	public String className() {
		return CLASS_TOWER;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public ObjectInstance copyWithName(String objectName) {
		return new BlocksWorldTower(objectName, height, top);
	}

	@Override
	public List<Object> variableKeys() {
		return keys;
	}

	@Override
	public Object get(Object variableKey) {
		if(!(variableKey instanceof String)){
			throw new RuntimeException("Key must be a string");
		}
		String key = (String)variableKey;
		if(key.equals(VAR_HEIGHT)){
			return height;
		}
		else if(key.equals(VAR_TOP)){
			return top;
		}
		throw new RuntimeException("Unknown key " + key);
	}

	@Override
	public BlocksWorldTower copy() {
		return new BlocksWorldTower(name, height, top);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return OOStateUtilities.objectInstanceToString(this);
	}
}
