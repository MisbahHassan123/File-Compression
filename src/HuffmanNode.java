public class HuffmanNode {

	HuffmanNode left,right;
	public long freq;
	public char ch;
	
	public String huffcode;
	
	
	public HuffmanNode()
	{
		
		freq = 0;
		ch = 0;
		huffcode = "";
		left = null;
		right = null;
		}
	public HuffmanNode(long xfreq,char xch,HuffmanNode lchild,HuffmanNode rchild){
		freq = xfreq;
		ch = xch;
		left = lchild;
		right = rchild;
		huffcode = "";
		
}
	}
