Todo:
DONE 2021/02/22 03:49 - Change the drawing update to only update on tick - explicitly not to advance the balls on bounds resize.
Implement bounds checking on external bounds - if the window is enlarged, balls should only rebound on the enlarged bound.
Implement bounds checking on external bounds - if the window is shrunk, move the ball to remain within the window.
Implement collision detection on circles - detect collisions
Implement collision detection on circles - change velocity based on momentum
Implement concurrent calculation - calculate collisions in advance
Implement concurrent calculation - recalculate when the bounds change
Implement a GUI to allow the user to vary the time rate
Implement a GUI to allow the user to vary the balls (number, size, radius, RGB)
Implement a GUI to allow the user to save the current state
Implement a GUI to track performance metrics (calculation time displayed, calculation time discarded (bounds changed), known advance time)
