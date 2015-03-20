grammar PlantUML;

diagram:
    Start NL+
    (title=Title NL?)?
    (usecases=usecase)*
    End NL?
    EOF;

usecase: (Usecase WS)? UsecaseLiteral (WS asClause)? NL;
asClause: As WS (id=ID|iduc=UsecaseLiteral);

Start: '@startuml';
End: '@enduml';

Title: 'title ' ('A'..'Z' | 'a'..'z' | '1..9' | ' ')+ '\n';
As: 'as';

UsecaseLiteral: '(' ('A'..'Z' | 'a'..'z' | '1..9' | ' ')+ ')';
Usecase: 'usecase';
ID: ('A'..'Z' | 'a'..'z' | '1..9')+;

WS: (' ' | '\t')+;
NL:  ('\r'? '\n')+;
