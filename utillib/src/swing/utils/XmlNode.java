package swing.utils;

import java.util.List;

public class XmlNode {
	
	private List<XmlNode> children;             //the list with the children
	private String nodeName;                    //the name of the node 
	
	public XmlNode(String nodeName){
		this.nodeName = nodeName;
	}

	public List<XmlNode> getChildren() {
		return children;
	}

	public void setChildren(List<XmlNode> children) {
		this.children = children;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
}
