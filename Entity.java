import java.util.List;


/**
  *The group of objects that fill rooms.
*/

public abstract class Entity {

  //Since all entities are placed onto rooms and the terminal, there needs to be distinct positions for each entity.
  private int x;
  private int y; //first thing is y
  private boolean alive;

  /**
  *Default constructor for entity at (1,1)
  *
  */
  public Entity(){
    x = 1;
    y = 1;
  }

  /**
    *Constructor for entities, has only coordinates.
    *
    *@param xcor X-coordinate
    *@param ycor Y-coordinate
  */
  public Entity(int xcor, int ycor){
    x = xcor;
    y = ycor;
  }
  /**
    *Function to acquire the x value of the Entity.
    *
    *@return the x-coordinate of the entity
  */
  public int getX(){
    return x;
  }

  /**
    *Function to acquire the y value of the Entity.
    *
    *@return the y-coordinate of the entity
  */
  public int getY(){
    return y;
  }

  /**
    *Function to set the x value of the Entity.
    *
    *@return nothing (mutator)
    *@param xcor a valid x coordinate positive integer
  */
  public void setX(int xcor){
    x = xcor;
  }

  /**
    *Function to set the y value of the Entity.
    *
    *@return nothing (mutator)
    *@param ycor a valid y coordinate positive integer
  */
  public void setY(int ycor){
    y = ycor;
  }
  public abstract int getHP();
  public abstract void setHP(int i);
  public abstract int getDEF();
  /**
    *Function for entities to be able to see other entities in the surrounding spaces (sideways and diagonals).
    *Full functionally will need to discussed, may place found entities in a list for future references.
    *
    *@return true if at least one entity is detected, else false if none are detected.
  */
  public boolean checkSurroundings(){
    //this.getX(), this.getY() -1 //down directions wrong but need to not confuse at moment
    //this.getX(), this.getY() +1 //up
    //this.getY(), this.getX() -1 //left
    //this.getY(), this.getX() +1 //right
    return false;
  }

  public boolean isAlive(){
    return alive;
  }

  public void setAliveStatus(boolean living){
    alive = living;
  }

  private void drawSelf(){

  }
}
