# CategoricalSyllogism
Categorical syllogisms are generated from a user-customisable word bank. The fallacies the syllogism has committed — if any — are displayed. For each syllogism, a Venn diagram is also created.

# Sentences
There are three sentences. A major premise, a minor premise, and a conclusion. Each sentence contains two words from the word bank. However, one word from the major premise and minor premise always repeats, and the conclusion repeats from the premises. Thus, there are three unique words per syllogism.

# Syllogism Code
Each syllogism can be described by a code (ie: AAA-1). The character at position one describes the major sentence. The character at position two describes the minor sentence. The character at position three describes the conclusion sentence. The number at position four describes the order of the repeated word in the syllogisms.

'A' = "All x are y"
'E' = "No x are y"
'I' = "Some x are y"
'O' = "Some x are not y"

'1' = First word of the major sentence and second word from the minor sentence repeat.
'2' = Second word of the major sentence and second word from the minor sentence repeat.
'3' = First word of the major sentence and first word from the minor sentence repeat.
'4' = Second word of the major sentence and first word from the minor sentence repeat.

# Screenshots
![InvalidOne](/Images/InvalidOne.png?raw=true "InvalidOne")
![ValidOne](/Images/ValidOne.png?raw=true "ValidOne")
![InvalidTwo](/Images/InvalidTwo.png?raw=true "InvalidTwo")
