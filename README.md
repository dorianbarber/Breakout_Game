game
====

First project for CompSci 308 Fall 2017 
Authors: Dorian Barber

Date Started: January 16th
Date Finished: January 22nd

Estimated hours: ~10 hours

Resources used: Stackoverflow 

Files used to start the project: Breakout.java

Errors: The program prevents the user from inputing anything
into the program that would result in a crash. 

No resource or data files required by the project.

All information about using the program is present in the
instruction splash screen. The user is only given the ability to
move the paddle with the exception of the cheat codes. 

One of the biggest assumptions made were the colors of the objects 
and instances. All of the colors were picked more or less randomly
just with the condition that it did not conflict with the ball
or the background of the scene. Images of objects may have been a better
choice however, the assumption that colors could all be decided was made. 

Bugs
1. When the ball makes contact with the bottom corners of the paddle
the ball will being to move side to side within the paddle until the
paddle begins moving. The root of this error stems in the how the 
collision system works when both the paddle is also moving.
2. If the ball approaches the corner of the box from the inside it is
reflected back that way. The idea behind the code is that when the ball
hit the corner dead on it will bounce back. But the implementation makes it such that regardless of the way the ball is approaching the corner it
will bounce back.
3. If the ball is going to hit the wall on the same y plane that the 
paddle is on and the paddle makes contact with the ball toward the direction
of the wall it will push the ball into the wall. This create a collision 
detection problem where the ball with remain partially in the wall and 
simply move up and down along the wall. 

My overall impression of the assignment was that as a whole it was nicely designed. I think the main purpose of the assignment was to give people experience with starting a project from scratch and it did it beautiful. Coding a project can be a daunting task but this project does a good job of forcing people to do it. 

Accessible cheat codes:
+ R -- resets the level completely
+ 1 -- Sends the game back to level 1
+ 2 -- Sends the game to level 2
+ 3 -- Sends the game to level 3
+ U (toggle) -- on - prevents the player from losing a life