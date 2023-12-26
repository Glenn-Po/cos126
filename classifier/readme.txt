Programming Assignment 6: Image Classifier


/******************************************************************************
 ***   Are you aware of the midsemester survey?  Please see Ed for          ***
 ***   instructions.                                                        ***
 ******************************************************************************/
Yes or no?

Yes

/******************************************************************************
 *  Approximately how many hours did it take you to complete this assignment?
 ******************************************************************************/

Number of hours: 3

/******************************************************************************
 *  Part 1. Some people (especially in Europe and Latin America) write a 7 with
 *  a line through the middle, while others (especially in Japan and Korea)
 *  make the top line crooked.
 *
 *  (a) Suppose that the training data consists solely of samples that do not
 *      use any of these conventions. How well do you think the algorithm will
 *      perform when you test it on different populations? What are the
 *      possible consequences?
 *
 *  (b) Now suppose that you are using a supervised learning algorithm to
 *      diagnose cancer. Suppose the training data consists of examples solely
 *      on individuals from population X but you use it on individuals from
 *      population Y. What are the possible consequences?
 ******************************************************************************/

ANSWER:

(a)
If the training data were to consist entirely of a certain convention,
the algorithm will perform relatively poorly depending on how different
the alternative convention is. For example, in the case of 7 above, it seems
that the Japanese/Korean 7 will be predicted much more accurately than the
European 7, but far less accurate than the convention it was trained on.

The consequences of such biased data is biased predictions from the learning
model: From these biased predictions, stakeholders can reach faulty conclusions
and take wrong decisions.

(b)
Consequences of using results based on biased data is that wrong diagnosis
could be reached which will sometimes be totally believable leading medics to
give wrong medication or treatment therapies.



/******************************************************************************
 *  Part 2
 *
 *  (a) Which digit is misclassified the most frequently?
 *
 *  (b) For this digit,  what are the top two digits that your MultiPerceptron
 *      incorrectly predicts?
 *
 *  (c) Examine some of these misclassified images. Provide an explanation
 *      of what might have caused these misclassifications.
 ******************************************************************************/

ANSWER:

(a) Most frequently misclassified digit:

9

Using the largest dataset of 60k inputs for training and testing on 10k inputs
to achieve the greatest accuracy, we counted the number of times each class
was passed to the model and how many times it was correctly predicted;
We then compared the error rates and 9 had the highest error rate closely by 5

(b) Top two digits misclassified are:

7 and 4

After ascertaining 9 as the most misclassfied digit, we went back to count how
many times each number was incorrectly predicted.
7 and 4 were by far the most incorrectly predicted in place of  9, with 7 being
misclassfied for, much more frequently.

(c) Explanation:
 A close examination (zoom in) of some images of 9 that were misclassfied
 revealed a pixel arrangement close to the average pixel arrangement of
 the numbers they were incorrectly predicted as






/******************************************************************************
 *  Did you encounter any serious problems? If so, please describe.
 ******************************************************************************/

Yes or no?
No


/******************************************************************************
 *  Write any other comments here.
 ******************************************************************************/


