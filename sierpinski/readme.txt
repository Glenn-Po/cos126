Programming Assignment 4:  Recursive Graphics


/******************************************************************************
 *  Approximate number of hours to complete this assignment
 ******************************************************************************/

Number of hours: 2



/******************************************************************************
 *  Describe your artistic creation and how you went about writing a program
 *  to produce it. If you found information about a fractal from somewhere
 *  (such as the course textbook, the booksite, or another offline or online
 *  source) cite it here.
 ******************************************************************************/
My artistic creation is a simple branching structure, a tree with n branches
on a lawn. Each branch has m sub-branches. The tree has fruit (growing at random
branch tips or fallen on the lawn) and the size of the fruits are proportional
to the size of the branch(so the branch doesn't break under its weight).

To write the program I sketched out the structure on a piece of paper to
the relationship between the number of branches, the fruit sizes and the number
of sub-branches. After coming to the conclusion, that each subranch was
a branch with n branches, I saw that I could use a loop to draw n branches
which each recursivly. I decided on fruiting probabilty of 20% per branch
which I could simulate with the random function.
To draw the left and right branches, I simply took a portion of the current
branch at a point, then rotated it about that point by 30 deg(30 deg is the
angle between a branch and the parent branch).


/******************************************************************************
 *  To get full credit on your Art.java, you need to have at least 3
 *  functions, including main(). Please list these functions below:
 ******************************************************************************/

 1. public static void main(String[] args)
 2. private static void rotateAround(double px, double py, double[] x,
                                        double[] y, double theta)
 3. private static void drawBranch(double x1, double y1, double x2, double y2,
                                int nBranches, int nSubBranches, double fruitSize)


/******************************************************************************
 *  To get full credit on your Art.java, you need to have used at least
 *  two of the criteria below. Please put an 'X' in the checkbox of the
 *   criteria that your submission fulfills, for instance:
 *     [ ] this is an unchecked criteria
 *     [X] this is a checked criteria
 ******************************************************************************/

    [X] call one or more Transform2D method

    [X] use different parameters than our examples: f(n, x, y, size)

    [X] use different StdDraw methods than in examples (e.g.,
        ellipses, arcs, text)

    [ ] number of recursive calls not constant per level (e.g.,
        conditional recursion)

    [ ] mutually recursive methods

    [ ] multiple recursive methods

    [ ] doesn't always recur from level n to level n-1

    [ ] draw between recursive calls, not just before/after all
        recursive calls

    [ ] use recursive level for secondary purpose (e.g., level
        dictates color)


/******************************************************************************
 *  Please check the following box if you do not want your Art to be shared
 *  (anonymously) with the rest of the class.
 ******************************************************************************/

    [ ] I do *NOT* want my art to be shared with the class.


/******************************************************************************
 *  Did you encounter any serious problems? If so, please describe.
 ******************************************************************************/

Yes or no?


/******************************************************************************
 *  Write any other comments here.
 ******************************************************************************/

