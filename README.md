# ANTLR4 Example

## Releases
See https://github.com/DLialios/antlr-example/releases

## Building from Source
After cloning, invoke the Gradle build task with: `gradlew build`

## Usage
Execute the appropriate startup script after saving a program to `antlr-example\bin\input.txt`  
Output will be written to file.

## Sample Program
```
>>>>>++++++
+++++++++++
++++[<<<<<-
-->--->--->
--->--->-]<
<<<<+++++++
.>++++.>>>>
++[<<<+++++
>+++++>>-]<
<<+.>+.>>++
+++++[<++>-
]<.
```

## Additional Info
|   |                                                                                                                                                                                   |
|---|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| > | increment the data pointer (to point to the next cell to the right).                                                                                                              |
| < | decrement the data pointer (to point to the next cell to the left).                                                                                                               |
| + | increment (increase by one) the byte at the data pointer.                                                                                                                         |
| - | decrement (decrease by one) the byte at the data pointer.                                                                                                                         |
| . | output the byte at the data pointer.                                                                                                                                              |
| , | accept one byte of input, storing its value in the byte at the data pointer.                                                                                                      |
| [ | if the byte at the data pointer is zero, then instead of moving the instruction pointer forward to the next command, jump it forward to the command after the matching ] command. |
| ] | if the byte at the data pointer is nonzero, then instead of moving the instruction pointer forward to the next command, jump it back to the command after the matching [ command. |
