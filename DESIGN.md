GAME
=====

The high level design goals of my project was essentially to
develop a functioning version of a Breakout game that keeps the
the information that flows between classes to a minimum. In 
essence, behavioral functions should remain in the classes 
themselves and there should be minimum altercations of classes
as much as possible. The only inputs a class should be able to
receive from another class is just constructing the instance. 

One way to add a new feature which would work very easily is 
the addition of a new powerup. Simply creating a new class which 
extends the Powerup abstract class, then implementing the proper 
abstract method and super constructor. After adding the newly 
created powerup to the array of powerups in the block class
the game is ready to play with a new powerup! The block class 
takes care of the randomization of the powerups and is dependent
on the number of powerups in the array. 

One major design choice I choose to make was to keep all the
different blocks in one class. Essentially the randomization of
which blocks had a powerup and which ones were permanent were
at the root of this decision. At the creation of each block 
there is a random algorithm which decides whether the block is
permanent or has a powerup inside. Additionally, I did not choose
to split the blocks up based on the number of hits that they
are required to break. This is because in the implementation of
the code a 3-hit block once hit becomes a 2-hit block. Instead of
creating a new class, simply updating the hit counter and the
corresponding color was the easiest a simplest way to do it. 
That simple difference did not warrant abstraction because the 
blocks do not functionally differ in any substantial way. 

One major decision that needed to be made to simply the 
project functionality was the collision detection system that
the ball has with the blocks. Essentially the system divides 
collisions into three categories: top/bottom, sides, and corners. 
By drawing imaginary lines from the edges of the rectangle that
extend outward infinitely it divides the contact space into those
three categories. What this system does not take into account
in the corner case is the direction of the ball. If the ball is 
diagonally going to hit the top corner of the box from the right side
intuition states it will only change it's y velocity. However
the algorithm changes the direction of both velocities. It is somewhat 
difficult to seem in game, but can appear often enough to fool the player
and sometimes trick the player's intuition (I have actually lost this
game because of that simplification!). 