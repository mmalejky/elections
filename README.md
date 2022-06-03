## Introduction
Election simulator from Object-oriented programming course.
Written in Java, as a Maven project.
Input-based tests are located in ```tests``` directory.
Package file ```elections.jar``` is in ```out/artifacts/elections_jar```.
Detailed description in Polish is in file ```description.pdf```.

Three methods for allocating seats in parliaments are tested:
- [D'Hondt method](https://en.wikipedia.org/wiki/D%27Hondt_method)
- [Webster/Sainte-Laguë method](https://en.wikipedia.org/wiki/Webster/Sainte-Lagu%C3%AB_method)
- [Hare–Niemeyer/Largest remainder method](https://en.wikipedia.org/wiki/Largest_remainder_method)

Four strategies of parties are possible:
- *R* - lavish strategy
- *S* - modest strategy
- *W* - my own strategy
- *Z* - greedy strategy

## Program input
- 1 line: "*n p d c*", where
    - *n* - number of electoral districts
    - *p* - number of political parties
    - *d* - number of possible actions
    - *c* - number of distinct characteristics of the candidate
- 2 line: "*n p1 p2 ... pn*", where
    - *k* - number of pairs of electoral districts to merge
    - *pi* in the form of *(j, j+1)* - ids of districts to merge
- 3 line: *p* names of parties
- 4 line: *p* party budgets
- 5 line: *p* party strategies from the collection *{R, S, W, Z}*
- 6 line: *n* number of voters in each district
- next lines: descriptions of candidates
- next lines: descriptions of voters
- next *d* lines: desriptions of possible actions

## Program output
- name of the method for allocating seats
- for each district:
    - voters and their votes, 
    - candidates and their votes,
    - parties and their seats from this district
- for each party: party name, number of seats
