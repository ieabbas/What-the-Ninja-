package group_assignment;


import java.io.Serializable;
 
/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Group Project
 *
 * <>
 *
 * Ismail Abbas
 * 
 */
 
 
/**
 * @author jason
 *
 */
 
	public class Location implements Serializable {
		private final int first;
		private final int second;
 
    public Location(int first, int second) {
        this.first = first;
        this.second = second;
    }
 
    public int getFirst() {
        return first;
    }
 
    public int getSecond() {
        return second;
    }
}