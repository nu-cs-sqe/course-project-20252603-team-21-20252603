# Week 3 (04/13/2026-04/19/2026)
**Planning and Progress Tracking**:
1. [done] Ryan Lei: Project Setup
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/1
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/2
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/3



# Week 4 (04/20/2026-04/26/2026) 
**Planning and Progress Tracking**:
1. [done] Ryan Lei: Set up BVA for Board class, working on implementation
2. [done] Ryan Lei: Created first github project with details and acceptance criteria
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/6

# Week 5 (04/27/26-05/03/2026)
**Planning and Progress Tracking**:
Finishing from Week 4: 
1. [done] Sid Javeri: Created Game Setup Phase design document and project board issue with user story, acceptance criteria, use case, and definition of done
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/8
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/issues/9

1. [done] Ryan Lei: Fixed some inconsistencies with the design document, finished currently implementable test cases for Board, merged into main
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/12
2. [50% done] Ryan Lei: Start working on Piece, did BVA basic, began working on test cases and set up enums, piece class, and test class
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/13

# Week 6 (05/04/26-05/10/2026)
**Planning and Progress Tracking**:
1. [done] Sid Javeri: Completed Piece implementation using TDD and BVA, implemented constructor validation, getters, and enums
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/13


# Week 7 (05/11/2026-05/17/2026)
**Planning and Progress Tracking**:
1. [done] Ryan Lei, Sid Javeri: Finished Piece Implementation with TDD, all cases for Piece BVA done
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/13
2. [done] Ryan Lei, Sid Javeri: Finished Game Initialization + BVA, all tests for BVA done
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/24
3. [done] Ryan Lei: Finished User story on Piece Movement logic, with all unique piece movements accounted for
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/25

# Week 8 (05/18/2026-05/24/2026)
**Planning and Progress Tracking**:
1. [done] Sid Javeri: Completed full castling implementation in `movePiece(...)` using TDD + BVA, including kingside and queenside castling for both white and black, comprehensive validation coverage for blocked paths, missing/opponent rooks, occupied destination squares, invalid castling-like king moves, and non-king moves incorrectly triggering castling, while also refactoring castling logic into helper methods to improve readability and keep `movePiece(...)` orchestration-focused
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/25
2. [done] Sid Javeri: Replaced Board magic numbers with named constants for improved readability and maintainability
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/29
3. [done] Sid Javeri: Added BVA documentation for Game move orchestration and castling validation scenarios
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/30
4. [done] Sid Javeri: Added and verified additional Checkstyle formatting rules and validated project with `./gradlew clean check`
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/31
5. [in progress] Sid Javeri: Designing BVA and TDD plan for king check validation and self-check move rejection

# Week 9 (05/25/2026-05/31/2026)
**Planning and Progress Tracking**:
1. [done] Sid Javeri: Completed comprehensive castling legality implementation in `movePiece(...)` using TDD + BVA, including prevention of castling while currently in check, prevention of castling through attacked squares, prevention of castling into attacked destination squares, and validation symmetry across white/black and kingside/queenside castling scenarios, while also refactoring legality validation into helper methods to improve readability and keep `movePiece(...)` orchestration-focused
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/25
2. [done] Sid Javeri: Added persistent castling-rights tracking for kings and rooks, including prevention of castling after prior king or rook movement even if the pieces later returned to their original starting squares
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/25
3. [done] Sid Javeri: Added and validated TC1–TC18 from `GameCastlingLegality.md` using strict TDD workflow (failing test → minimal implementation → passing test), including comprehensive validation coverage for attacked intermediate squares, attacked destination squares, blocked attack paths, and castling-right invalidation scenarios
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/25
4. [done] Sid Javeri: Expanded BVA documentation for castling legality edge cases and symmetric validation scenarios for both white and black castling behavior
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/30
5. [done] Sid Javeri: Revalidated project correctness and style compliance after castling legality integration using `./gradlew build` and `./gradlew check`
- https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/25

# Week 10 (06/01/2026-06/07/2026) TEMPLATE (You can change the format to whatever the team likes better)
**Planning and Progress Tracking**:
1. [done] Ryan Lei: added the locale requirements, for English and Spanish, finished completely and merged from main
   - https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/42
3. [done] Ryan Lei: added a fully functional UI, with support for Spanish and English locales, with all functionality and the fully tested backend
   - https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/43
5. [done] Ryan Lei: added integration testing on two key features, using Cucumber and Gherkin syntax along with step definitions.
   - https://github.com/nu-cs-sqe/course-project-20252603-team-21-20252603/pull/41
