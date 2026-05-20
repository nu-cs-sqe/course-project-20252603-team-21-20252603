# Instructor Code Review Feedback

**Contact**: Dr. Yiji Zhang (yiji.zhang@northwestern.edu)

**Purpose of This Document**:
The instructor will perform code review with respect to software design, error handling, format and style on the main branch every week starting Week 6 using the letter grade A standards.
The following chapters of the textbook are considered: Chapter 1, 2, 3, 4, 5, 6, 7, 9, and 10. The corresponding lectures are considered, too.

Please note that this feedback does not include evaluation of your progress, the proper use of linters, the quality of your test cases, or your compliance of TDD/BDD workflow.  
You can find the weekly feedback from your dedicated PM/TA for that.

## Week 7 Code Review
I have read every line of production code currently in the main branch. I've found no major violations except
for the use of magic numbers in Board, as I pointed out last time. Instead of using "8", you want to use
constant variables like

`private static final int TOTAL_NUM_RANKS = 8;`

Please approve and merge the PR once the team has read the feedback. Thanks!
## Week 6 Code Review
I have read every line of production code currently in the main branch.
2 issues:
1. The use of magic numbers in Board should be removed.
2. The 2D array in Board stores Object. Does this reflect your design? Piece[][] would make more sense.

Please approve and merge the PR once the team has read the feedback. Thanks!