Programming Assignment 1:  Conditionals and Loops

PLEASE REMEMBER NOT TO INCLUDE YOUR NAME ANYWHERE IN THIS SUBMISSION.


/******************************************************************************
 * Approximate number of hours to complete this assignment
 ******************************************************************************/

Number of hours: 2



/******************************************************************************
 *  What is the relationship between the number of steps n of the random walk
 *  and the mean squared distance? By "relationship," we mean something like:
 *
 *      mean squared distance = 126 n^9
 *
 *  Briefly justify your answer based on computational experiments.
 *  Describe the experiments and list several data points.
 ******************************************************************************/

mean squared distance = (1/20) n^2

Suppose we were trying to achieve the greatest squared distance, then the best
case scenario will be a straigt line in either directions (NESW) and that would
be n^2.So generally, it is expected that the relationship for a random walk,
if some would be of some a similar form. With that basis, an experiment can be
made with multiple values to approximate that relationship.


After several experimental runs with 1000 to 100,000 trials and the number of
steps being multiples of 10 from 10 to 6000, the trend indicated some form of
polynomial relationship. After checking the values of the
 `mean squared distance` divided by n to the power of several values,
it showed a relationship of the order a*n^2 from which I approximated `a`
to about 1/20 after further trials.


/******************************************************************************
 *  Did you encounter any serious problems? If so, please describe.
 ******************************************************************************/

Yes or no?
No



/******************************************************************************
 *  Write any other comments here.
 ******************************************************************************/

