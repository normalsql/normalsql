/*
    good: ( select a (( between b and c ) and d ))
    bad:  ( select a ( between b and ( c and d )))
 */

select a between b and c and d;