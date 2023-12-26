Programming Assignment 2:  N-Body Simulation


/******************************************************************************
 *  Approximate number of hours to complete this assignment
 ******************************************************************************/

Number of hours: 2



/******************************************************************************
 *  Did you encounter any serious problems? If so, please describe.
 ******************************************************************************/

Yes or no?
No



/******************************************************************************
 *  Write any other comments here.
 ******************************************************************************/


/******************************************************************************
 *  If you attempted Challenge #1, and submitted deluxe-universe.txt, explain
 *  your work and approach. In particular, describe how you determined
 *  the values to put into the file to get your desired result.
 ******************************************************************************/

Describe:



/******************************************************************************
 *  If you attempted Challenge #2, and submitted DeluxeNBody.java,
 *  register an account on https://www.loom.com, record a two-minute
 *  video of yourself showing your universe in action, and explain what
 *  you are doing.
 ******************************************************************************/
Describe: **Incomplete**

My attempt of this 2nd challenge was unsuccessly, and I coultn't find enough
time to debug but I would like to explain my thought process.

I tried to add a 3rd dimension to the simulation. Essentially, since the other
two were done, all that was left was to add in the arrays to hold position,
velocity and force data for the 3rd dimension.
In order to simulate a motion in the third dimension, I had to employ scaling as
follows. I know the radius of revolution would range from -R to R where R is the
radius of the universe. I could not find enough documentation for the StdDraw.
picture method, so I used tried our several values essentially settling at
R/10 scaling by 1 (when the object is at z=0).
I then tried several values to decide how tiny I want a body
when it z-index is -R and how big when it is R (Assuming the z-axis 'come out'
of the screen). These values, I estimated to a scale factor of R/40 and 7R/40
respectively. With these correspoing values between the z-axis and the scale
(0, R/10), (-R, R/40), (R, 7R/40)  I then determined the linear relationship
between the radius of revolution(z-index) and the corresponding scale value
(y = mx + c).

The model works perfectly when everything lies on the x-y plane but not otherwise.
This has got me wondering if the initial positions and velocities indefinitely
affect subsequent path of each body(I was expecting the system to 'auto-correct'
to their proper trajectories).

With that challenge and not enough time to see it through, I just wanted to
my thinking process and perhaps get a later feedback on it.


Link to Loom video: N/A

