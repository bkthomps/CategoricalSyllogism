# CategoricalSyllogism
Categorical syllogisms are generated from a user-customisable word bank. The fallacies the syllogism has committed — if any — are displayed. For each syllogism, a Venn diagram is also created.

## Setup
1. The Java version for this project is Java 8.
2. Make sure you have the JDK or JRE installed. If you do not have either of those installed, you can install the JRE by going [here](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) and following the steps.
3. Download the CategoricalSyllogism.jar file to your computer. Optionally, you can also download Socrates.png to the same location on your computer so that the application has an icon.
4. Afterwards, you can run the application by running the jar file.

## Sentences
Syllogisms contain three sentences: a major premise, a minor premise, and a conclusion. Each sentence contains two words from the word bank. However, one word from the major premise and minor premise always repeats, and the conclusion repeats from the premises. Thus, there are three unique words per syllogism.

## Syllogism Code
Each syllogism can be described by a code (ie: AAA-1). The character at position one describes the major sentence. The character at position two describes the minor sentence. The character at position three describes the conclusion sentence. The number at position four describes the order of the repeated word in the syllogisms.

'A' = "All x are y"

'E' = "No x are y"

'I' = "Some x are y"

'O' = "Some x are not y"

'1' = First word of the major sentence and second word from the minor sentence repeat.

'2' = Second word of the major sentence and second word from the minor sentence repeat.

'3' = First word of the major sentence and first word from the minor sentence repeat.

'4' = Second word of the major sentence and first word from the minor sentence repeat.

## Screenshots
![InvalidOne](/Images/InvalidOne.png?raw=true "InvalidOne")
![ValidOne](/Images/ValidOne.png?raw=true "ValidOne")
![InvalidTwo](/Images/InvalidTwo.png?raw=true "InvalidTwo")
![InvalidThree](/Images/InvalidThree.png?raw=true "InvalidThree")
