# Maze Solver
This program was developed as an assignment to practice using graphs and
graph traversal algorithms. The maze is randomly generated using the size
selected at the bottom.

You can solve the maze using either [Depth-First Search](https://en.wikipedia.org/wiki/Depth-first_search) (DFS) or
[Breadth-First Search](https://en.wikipedia.org/wiki/Breadth-first_search) (BFS). The time it takes to solve and render
the resulting path is displayed in the bottom right.

## Generation
The maze starts as an integer array of disjoint sets and as a graph of vertices 
representing each cell in the maze. The disjoint sets are managed using a [weighted 
quick-union](https://medium.com/@dhaneshchaudhary99/union-find-by-rank-and-path-compression-5f461a5b9839)
(Union-Find) algorithm, with path compression. This allows connections 
between large maze sections to be made in near constant time.

The cells in the maze are randomly selected using a [Fiser-Yates](https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle)
algorithm (loops over all cells, in random order). Each cell selects a random neighboring
cell, and a union is made in the disjoint sets array if they are not already 
connected. The connection/edge is also stored in the graph using an adjacency list.

This process repeats until the maze has joined all cells in one spanning tree. 
This means there is only path from the entrance to the exit. 

## Solving

Depth-First Search. There are two implementations of DFS in this program because
it was intended that we practice writing recursive methods, however, since we run 
into stackoverflow with the larger mazes, there is also an iterative solution.

Recursive DFS traverses the maze by recursively looking at the first (psudo-random)
neighbor until a dead-end is found. When there are no neighbors to traverse, it 
backtracks to the last cell that has more non-traversed neighbors. When the exit is found,
each cell is placed in a traversal list as the program moves back up the call stack.

The Iterative DFS is similar to the recursive one, but it uses a stack to track the 
traversal. Each visited cell is placed in the stack, and when backtracing, the cells 
are then popped from the stack. When the exit is found the resulting stack is converted
to a list and returned to be rendered.

Breadth-First Search has only an iterative implementation. A queue is used to track which
cells to check next. The neighboring cells are all looked at first, and then added to the
queue. When each neighbor is checked, it is mapped to the current cell in a traversal map. 
This map is returned when the exit is found. To construct the traversal path, you
look at the cell associated with the exit, and then the cell associated with that cell.
This repeats back to the entrance.
