/**
 * @author : ajanasathian
 * @mailto : ajanacs@gmail.com
 * @created : 6/21/22, Tuesday
 *          To change this template use File | Settings | File and Code Templates
 * */

/*
Bipartite Graph
Medium Accuracy: 40.1% Submissions: 70747 Points: 4
Given an adjacency list of a graph adj  of V no. of vertices having 0 based index. Check whether the graph is bipartite or not.


Example 1:

Input:

Output: 1
Explanation: The given graph can be colored
in two colors so, it is a bipartite graph.
Example 2:

Input:

Output: 0
Explanation: The given graph cannot be colored
in two colors such that color of adjacent
vertices differs.


Your Task:
You don't need to read or print anything. Your task is to complete the function isBipartite() which takes V denoting
 no. of vertices and adj denoting adjacency list of the graph and returns a boolean value true if the graph is bipartite otherwise returns false.


Expected Time Complexity: O(V + E)
Expected Space Complexity: O(V)

Constraints:
1 ≤ V, E ≤ 105
 */

/**
 * Implementation
We will run a Breadth First Search(BFS). Assign RED color to the first uncolored vertex,
Color all the neighbors of the first vertex with BLUE color.
Color all neighbor’s neighbor with RED color. While assigning colors,
if we find a neighbor which is colored with same color as current vertex,
then the graph cannot be colored with 2 vertices (or graph is not Bipartite) .
Repeat this process until all the vertices are visited(graph may contain more than one connected component)

Complexity
Time Complexity O(V+E) -  We are traversing all the edges and nodes.
Space Complexity O(V) - We are storing the colors of V nodes.
 */
import scala.collection.mutable

class Solution
{
  def isBipartite(V: Int, adj: Array[Array[Int]]): Boolean =
  {
    val color = Array.fill[Int](V)(-1)
    val q = new mutable.Queue[Int]()

    var is_Bipratite = true

    (0 until V).foreach{i =>

      if(color(i) == -1)
      {
        q.enqueue(i)
        color(i) = 0

        while(q.nonEmpty)
        {
          val u = q.dequeue()
          for
            {
              v <- adj(u)
            }
            yield {
              if (color(v) == -1)
                {
                  color(v) = color(u) ^ 1
                  q.enqueue(v)
                }
              else
                is_Bipratite = is_Bipratite & (color(u) != color(v))
            }
        }
      }
    }
    is_Bipratite
  }
}

/*
Time Complexity : O(V*V) as ajacency matrix is used for graph but can be made O(V+E) by using adjacency list

Space Complexity : O(V) due to queue and color vector.
 */
object BiPartite extends App
{
  val solution = new Solution
  val G = Array(Array(0, 1, 0, 1),
          Array(1, 0, 1, 0),
          Array(0, 1, 0, 1),
          Array(1, 0, 1, 0))
  if (solution.isBipartite(0, G))
    println("yes")
  else
    println("No")
}

