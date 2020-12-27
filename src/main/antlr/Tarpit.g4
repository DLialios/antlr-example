grammar Tarpit;

HEADRIGHT   :   '>';
HEADLEFT    :   '<';
INCREMENT   :   '+';
DECREMENT   :   '-';
OUTPUT      :   '.';
INPUT       :   ',';
LOOPSTART   :   '[';
LOOPEND     :   ']';
WHITESPACE  :   [ \t\r\n]+ -> skip;

start       :   program EOF;

program     :   instruction program | /*ε*/;

instruction :   HEADRIGHT|
                HEADLEFT|
                INCREMENT|
                DECREMENT|
                OUTPUT|
                INPUT|
                loop;

loop        :   LOOPSTART program LOOPEND;
