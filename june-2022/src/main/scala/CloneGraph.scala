/**
 * @author : ajanasathian
 * @mailto : ajanacs@gmail.com
 * @created : 6/8/22, Wednesday
 *          To change this template use File | Settings | File and Code Templates
 * */

/*
Clone Graph
Medium
Given a reference of a node in a connected undirected graph. Return a clone of the graph.
Each node in the graph contains a value (Integer) and a list (List[Node]) of its neighbors.
For Example :

class Node {
    public int val;
    public List neighbors;
}
Example 1:

Input:
adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: 1
Explanation: The graph is cloned successfully.

Example 2:

Input:
adjList = [[2],[1]]
Output: 1
Explanation: The graph is cloned successfully.
Your Task:
You don't need to read input or print anything.
Your task is to complete the function cloneGraph( ) which takes a node will always be the first node of the graph as input and returns the copy of the given node as a reference to the cloned graph.

Note: After the user will returns the node of the cloned graph the system will automatically check if the output graph is perfectly cloned or not.
The output is 1 if the graph is cloned successfully. Otherwise output is 0. You can't return a reference to the original graph you have to make a deep copy of that.


Expected Time Complexity: O(N + M).
Expected Auxiliary Space: O(N).


Constraints:
1 <=Number of nodes<= 100
1 <=Node value<= 100
 */

/*
Cloning of a LinkedList and a Binary Tree with random pointers has already been discussed. The idea behind cloning a graph is pretty much similar.




The idea is to do a BFS traversal of the graph and while visiting a node make a clone node of it (a copy of original node).
If a node is encountered which is already visited then it already has a clone node.


How to keep track of the visited/cloned nodes? A HashMap/Map is required in order to maintain all the nodes which have already been created.
Key stores: Reference/Address of original Node
Value stores: Reference/Address of cloned Node

A copy of all the graph nodes has been made, how to connect clone nodes? While visiting the neighboring vertices of a node u get the corresponding cloned node for u ,
let’s call that cloneNodeU , now visit all the neighboring nodes for u and for each neighbor find the corresponding clone node(if not found create one) and then push into the neighboring vector of cloneNodeU node.

How to verify if the cloned graph is a correct? Do a BFS traversal before and after the cloning of graph. In BFS traversal display the value of a node along with its address/reference.
Compare the order in which nodes are displayed, if the values are same but the address/reference is different for both the traversals then the cloned graph is correct.

https://practice.geeksforgeeks.org/problems/clone-graph/1/?page=1&company[]=Google&sortBy=accuracy
 */
object CloneGraph
{
  def main(args: Array[String]): Unit =
  {
    Graph graph = new Graph();
    GraphNode source = graph.buildGraph();
    System.out.println("BFS traversal of a graph before cloning");
    graph.bfs(source);
    GraphNode newSource = graph.cloneGraph(source);
    System.out.println("BFS traversal of a graph after cloning");
    graph.bfs(newSource);
  }
}


// Scala program to Clone an Undirected Graph
import java.util._
import scala.collection.mutable

// GraphNode class represents each
// Node of the Graph
class GraphNode(val value: Int, var neighbours: mutable.ArrayBuffer[GraphNode] = null)


class Graph
{
  // A method which clones the graph and
  // returns the reference of new cloned source node
  def cloneGraph(source: GraphNode): Option[GraphNode] =
  {
    val q = mutable.Queue[GraphNode]()
    q.enqueue(source)

    // An HashMap to keep track of all the
    // nodes which have already been created
    val hm = mutable.HashMap[GraphNode, GraphNode]()

    //Put the node into the HashMap
    hm.put(source,new GraphNode(source.value))

    while (q.nonEmpty)
    {
      // Get the front node from the queue
      // and then visit all its neighbours
      val u = q.dequeue()

      // Get corresponding Cloned Graph Node
      val cloneNodeU = hm.get(u)
      if (u.neighbours != null)
      {
        val  v = u.neighbours
        for{
          grahNode <- v
        }yield {
          // Get the corresponding cloned node
          // If the node is not cloned then we will
          // simply get a null
          var cloneNodeG = hm.get(grahNode)
          // Check if this node has already been created
          if (cloneNodeG == null)
            {
              q.enqueue(grahNode)
              // If not then create a new Node and
              // put into the HashMap
              cloneNodeG= Option(new GraphNode(grahNode.value))
              hm.put(grahNode, cloneNodeG)
            }
          cloneNodeU.get.neighbours.append(cloneNodeG)
        }
      }
    }

    // Return the reference of cloned source Node
    hm.get(source);
  }

  // Build the desired graph
  def buildGraph(): GraphNode =
  {
    /*
        Note : All the edges are Undirected
        Given Graph:
        1--2
        |  |
        4--3
    */
    val node1 = new GraphNode(1)
    val node2 = new GraphNode(2)
    val node3 = new GraphNode(3)
    val node4 = new GraphNode(4)
    var v = mutable.ArrayBuffer[GraphNode]()
    v.append(node2)
    v.append(node4)
    node1.neighbours = v

    v.append(node1)
    v.append(node3)

    node2.neighbours = v
    node2.neighbours = v

    v.append(node2)
    v.append(node4)

    node3.neighbours = v

    v.append(node3)
    v.append(node1)
    node4.neighbours = v
   node1
  }

  // BFS traversal of a graph to
  // check if the cloned graph is correct
  def bfs(source: GraphNode): Unit =
  {
    val q = mutable.Queue[GraphNode]()
    q.enqueue(source)
    val visit = mutable.HashMap[GraphNode, Boolean]()
    visit.put(source, true)

    while (q.nonEmpty)
    {
      val u = q.dequeue()
      println("Value of Node " + u.value)
      println("Address of Node " + u)
      if (u.neighbours != null)
      {
        val v = u.neighbours
        for
          {
            g <- v
          }yield
          {
            if (!visit.contains(g))
              {
                q.enqueue(g)
                visit.put(g, true)
              }
          }
      }
    }
   println()
  }
}



