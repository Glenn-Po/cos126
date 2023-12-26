Final Programming Project: Atomic Nature of Matter

/**********************************************************************
 * Approximate number of hours to complete this assignment            *
 **********************************************************************/

Number of hours: 5


/**************************************************************************
 *  The input size n for BeadTracker is the product of the number of      *
 *  pixels per frame and the number of frames. What is the estimated      *
 *  running time (in seconds) of BeadTracker as a function of n?          *
 *  Justify your answer with empirical data and explain how you used it.  *
 *  Your answer should be of the form a*n^b where b is an integer.        *
 **************************************************************************

The estimated running time is 1.74e-6*n^1. We arrived at this answer by running
BeadTracker 6 times on the following inputs: 7680000, 15360000, 30720000, and
61440000(correspoding to number of frames 25, 50, 200 and 200).

We calculated the input size by mutliplying the pixels per
frame by the number of frames of each run, as that is how the input size is
defined for BeadTracker.

We then used the formula T(2N)/T(N)  for T(N) = a*N^b to calculate the
time complexity of the program, and found the time complexity to be O(n).

For example, the time it took to process 25 frames was about 13 seconds,
while ittook about 26 seconds to process double that amount of frames (50). This
indicates a linear time complexity, as the time taken doubles as input size
doubles. This can be seen again when comparing the time it took to process
200 frames with the time it took for 50 frames. 102 seconds is about 4 times 26,
which are the times taken to process 200 and 50 frames, respectively. This
indicates a linear time complexity, as the time taken quadruples as input size
quadruples.

We used a minimum mass of 25, a tau of 180.0, and a maximum distance of 25
to conduct our tests.

We also used the available StopWatch library to time our code and
disabled optimization for more accurate timing results.

| pixels per frame | number of frames |        n       | Time taken |
-------------------+------------------+----------------+------------|
          307200                25          7680000         13.25800
          307200                50         15360000         26.80600
          307200               100         30720000         50.97100
          307200               200         61440000        102.70000

/**********************************************************************
 *  Did you encounter any serious problems? If so, please describe.
 **********************************************************************/

Yes or no?

no

/**************************************************************************
 *  List any other comments here.                                         *
 **************************************************************************/
