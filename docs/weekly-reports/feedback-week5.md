# Week 5 Project Feedback by PM/TA

**Dedicated PM/TA**: Jiahao Yu

## How to Read This Feedback
> [!NOTE]
> **Purpose.** This feedback focuses on your team's progress and collaboration. It is meant as guidance, not judgement.

> [!IMPORTANT]
> **Scope.** For the BVA and TDD items, the PM/TA evaluates only the `main` branch. Ongoing work in feature branches will be evaluated after it is merged. If you'd like early feedback on work in progress, please reach out to your PM/TA directly.

> [!TIP]
> **Mistakes are expected :).** As the instructor mentioned in class, early mistakes are part of the learning process. As long as your team addresses the issues after you get the feedback, your grade will not suffer from them.

## Checklist
Status:
- ✅: All done/Good job!
- ⚠️: Attention needed
- ❌: Significant issue found
- ➖: No basis to evaluate

### Past Feedback
| # | Item                                                                                                 | Status | Reviewer Notes | Source Instructions or Resources |
|---|------------------------------------------------------------------------------------------------------|:------:|----------------|----------------------------------|
| 0 | The team has closed and merged the past Feedback PR(s), indicating that they have read the feedback. |   ✅   | Week 4 feedback PR #7 has been merged into `main`. Thank you for closing the loop. | |

### Software Process Quality
| # | Item                                                                                                                                                         |  Status   | Reviewer Notes      | Source Instructions or Resources                                                  |
|---|--------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|-----------------------------------------------------------------------------------|
| 1 | Each active feature branch has an open draft PR against main.                                                                                                |     ✅     | The active `feature-piece` work has draft PR #13 open against `main`. Merged branches such as `feature-board` and `feature-setup-design` can be cleaned up when convenient. | Week 4 Wednesday Lecture (Lecture 08)                                             |
| 2 | The team has a "definition of done" (BVA) fully documented for the part of the system that is done. (needed for Letter Grade D)                              |     ✅     | `docs/bva/Board.md` documents the Board cases that are currently implemented on `main`, and it clearly marks later setup/isEmpty cases as not implemented yet. | Project grading rubrics                                                           |
| 3 | GitHub commit history demonstrates evidence of a TDD/BDD workflow for all the non-UI code. (needed for Letter Grade C)                                       |     ✅     | The Board history on `main` shows test-case-sized commits such as TC1/TC2, TC4-TC5, and TC6-TC9. I also ran the tests with Java 11 and they passed. | Project grading rubrics                                                           |

### Planning & Progress Evaluation
| # | Item                                                                                                                                                         |  Status   | Reviewer Notes      | Source Instructions or Resources                                                  |
|---|--------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|-----------------------------------------------------------------------------------|
| 4 | The team documents every week's planning and progress evaluation professionally. (needed for Letter Grade B)                                                 |     ✅     | Week 5 planning/progress is documented in `docs/weekly-reports/report.md` with links to PRs/issues. Nice improvement from last week. | Week 4 Wednesday Lecture (Lecture 08), Project grading rubrics                    |

### Progress & Collaboration
| # | Item                                                                                                                                                                                   |  Status   | Reviewer Notes      | Source Instructions or Resources                 |
|---|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|--------------------------------------------------|
| 5 | Overall development progress (recall the recommended order is: Game Setup Phase -> One turn of the game -> Multiple turns -> One win condition -> Other win conditions (if applicable) |     ⚠️     | Good progress: `main` now has Board implementation/tests and a setup-phase design document, and Piece work is underway in draft PR #13. The Game Setup Phase is still not complete on `main` yet, so please prioritize finishing/merging. | Canvas assignment Project: Week 4 and 5 Guidance |
| 6 | Collaboration: Quality of discussion in PR reviews and work item comments on the board.                                                                                                |     ⚠️     | PR approvals and assigned issues are present, but most PR reviews/issues currently have little or no written discussion. For future PRs, please leave short substantive review comments about BVA coverage, TDD evidence, design choices, or requested changes so the collaboration is visible. | |

### The following items are not checked by the reviewer as they were checked in the previous weeks
But if your team wants the reviewer to check any of these for any reasons, please contact them or the instructor via either email or tagging them in the feedback PR.

| #   | Item                                                                                                                                                         |  Status   | Reviewer Notes      | Source Instructions or Resources                                                  |
|-----|--------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|-----------------------------------------------------------------------------------|
| 1   | GitHub repository branch protection rules are fully set up so that people cannot push into main without a pull request approval. (needed for Letter Grade C) |     ➖     | Not rechecked this week. | Canvas assignment Project: Setup, Project grading rubrics                         |
| 2   | Continuous Integration (CI) is fully set up from the beginning. (needed for Letter Grade B)                                                                  |     ➖     | Not rechecked this week. | Canvas assignment Project: Setup, Project grading rubrics                         |
| 3   | The team uses the project management board steadily and frequently, and the description of each task is detailed. (needed for Letter Grade B)                | See below | See breakdown below | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.1 | Every functionality-related work item on the management board includes a user story, and optionally one or more use cases.                                   |     ➖     | Not rechecked in full this week. Issue #9 has a clear user story/use case; issue #14 is lighter and could use more detail if it becomes a larger work item. | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.2 | The design is documented somewhere, either in the work item description, or in a separate design document.                                                   |     ➖     | Not rechecked in full this week. `docs/design/setup_phase.md` now exists, which addresses last week's main design-documentation concern. | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.3 | Task assignments are documented clearly in the management board.                                                                                             |     ➖     | Not rechecked in full this week. The current open issues have assignees. | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |

## Additional Comments
Nice recovery from the Week 4 feedback: you merged the feedback PR, merged the weekly report, added setup-phase design documentation, and opened a draft PR for ongoing Piece work. The next best step is to turn that process improvement into visible implementation progress on `main`. Try to complete the core Game Setup Phase before spreading into later phases, and make PR reviews a little more conversational so teammates can see what was checked and why it was approved. Also, it would be good to have some comments/conversations in the PR reviews so that we can see you are collaborating with your teammates.

## Review Snapshot (Just used for tracking purposes, not for feedback)
- Reviewed latest `main` commit: `d399bfd`
- Commit summary: Merge pull request #15 from `nu-cs-sqe/weekly-reports`
- Review date: 2026-05-05
- Verification: `./gradlew test` passed with Java 11
