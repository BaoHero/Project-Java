package star;

class Node {


	  Node(int[][] puzzle) {
	    this.puzzle = puzzle;
	  }

	  // 2D array to represent the puzzle
	  int[][] puzzle;

	  // The different scores used to tell if this node will be used
	  private int g = 0, h ,f;
	  int id;

	  Node parent = null;

	  public void setId(int id)
	  {
		  this.id=id;
	  }
	  public int getId()
	  {
		  return this.id;
	  }
	  /** All the the getters and setters */
	  int getF() {
	    return f;
	  }

	  int getG() {
	    return g;
	  }

	  int getH() {
	    return h;
	  }

	  void setG(int g) {
	    this.g = g;
	  }

	  void setH(int h) {
	    this.h = h;
	  }

	  void setF(int f) {
	    this.f = f;
	  }

	}
