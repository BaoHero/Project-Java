package star;


class Board {

  // Enum to simplify the understanding of which direction is being used
  public enum Direction {
    UP, DOWN, LEFT, RIGHT
  }

  Node move(Direction direction, Node current) {

    // Get the x and y coordinates of the zero value.  Index 0 is x and 1 is y
    int[] coordinates = getCoords(current, 0);
    int temp;

    // Switch function, which direction are we moving the zero
    switch (direction) {

      // Zero is going up
      case UP:

        // If zero's x coordinate is already at the top, there's no where to go return null to be removed in breakdown
        if(coordinates[0] == 0)
          return null;

        // Swapping the zero with whichever value was in the position above it
        temp = current.puzzle[coordinates[0] - 1][coordinates[1]];
        current.puzzle[coordinates[0] - 1][coordinates[1]] = 0;
        current.puzzle[coordinates[0]][coordinates[1]] = temp;
        break;

        // Zero is going down
      case DOWN:

        // If zero's x coordinate is already at the bottom, there's no where to go return null to be removed in breakdown
        if(coordinates[0] == 2)
          return null;

        // Swapping the zero with whichever value was in the position below it
        temp = current.puzzle[coordinates[0] + 1][coordinates[1]];
        current.puzzle[coordinates[0] + 1][coordinates[1]] = 0;
        current.puzzle[coordinates[0]][coordinates[1]] = temp;
        break;

        // Zero is going left
      case LEFT:

        // If zero's y coordinate is already to the very left, there's no where to go return null to be removed in breakdown
        if(coordinates[1] == 0)
          return null;

        // Swapping the zero with whichever value was in the position to the left of it
        temp = current.puzzle[coordinates[0]][coordinates[1] - 1];
        current.puzzle[coordinates[0]][coordinates[1] - 1] = 0;
        current.puzzle[coordinates[0]][coordinates[1]] = temp;
        break;

        // Zero is going right
      case RIGHT:

        // If zero's y coordinate is already to the very right, there's no where to go return null to be removed in breakdown
        if(coordinates[1] == 2)
          return null;

        // Swapping the zero with whichever value was in the position to the right of it
        temp = current.puzzle[coordinates[0]][coordinates[1] + 1];
        current.puzzle[coordinates[0]][coordinates[1] + 1] = 0;
        current.puzzle[coordinates[0]][coordinates[1]] = temp;
        break;
    }

    // Return the modified node
    return current;
  }


  // Manhattan Distance Heuristic
  int heuristics(Node node, Node goal) {

	  // Start the score off as 0
	    int score = 0;

	    // For every x and y position, we subtract the goal position from the current position, then add it together except for zero
	    for(int i = 0; i < node.puzzle.length; i++) {
	      for(int j = 0; j < node.puzzle[i].length; j++) {
	        if(node.puzzle[i][j] == 0)
	          continue;
	        score += (Math.abs(i - getCoords(goal, node.puzzle[i][j])[0])) + (Math.abs(j - getCoords(goal, node.puzzle[i][j])[1]));
	      }
	    }

	    // Return the final Manhattan Distance calculated
	    return score;
	  }
  int gscore(Node node, Node goal) {

	    // Start the score off as 0
	    int score = 0;

	    // For every x and y position, we subtract the goal position from the current position, then add it together except for zero
	    for(int i = 0; i < node.puzzle.length; i++) {
	      for(int j = 0; j < node.puzzle[i].length; j++) {
	        if(node.puzzle[i][j] == 0)
	          continue;
	        score += (Math.abs(i - getCoords(goal, node.puzzle[i][j])[0])) + (Math.abs(j - getCoords(goal, node.puzzle[i][j])[1]));
	      }
	    }

	    // Return the final Manhattan Distance calculated
	    return score;
	  }

  private int[] getCoords(Node node, int n) {

    // This is only an 8 puzzle solver
    if(n < 0 || n > 8)
      return null;

    // Start the x and y coordinates as 0
    int x = 0, y = 0;

    // Find the value with i being the x coordinate and j the y.  Once found exit loop
    for(int i = 0; i < node.puzzle.length; i++) {
      for(int j = 0; j < node.puzzle[i].length; j++) {
        if(node.puzzle[i][j] == n) {
          x = i;
          y = j;
          break;
        }
      }
    }
    // Return array, 0 index is x and 1 index is y
    return new int[]{x, y};
  }

}

