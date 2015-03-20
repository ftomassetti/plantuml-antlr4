package me.tomassetti;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    class ErrorCollector implements ANTLRErrorListener {
        private List<String> errors = new ArrayList<>();
        
        public List<String> getErrors() {
            return errors;
        }
        
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
            errors.add(s);
        }
    
        @Override
        public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {
            errors.add("ambiguity");
        }
    
        @Override
        public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {
            errors.add("full context");
        }
    
        @Override
        public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {
            errors.add("context sensitivity");
        }
    }
    
    public void testParser() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("usecase1.plantuml").getFile());
        
        ErrorCollector lexErrors = new ErrorCollector();
        ErrorCollector syntaxErrors = new ErrorCollector();
        
        PlantUMLLexer lexer = new PlantUMLLexer(new ANTLRInputStream(new FileInputStream(file)));
        lexer.addErrorListener(lexErrors);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PlantUMLParser parser = new PlantUMLParser(tokens);
        parser.addErrorListener(syntaxErrors);
        PlantUMLParser.DiagramContext diagramCtx = parser.diagram();
        
        assertEquals(0, lexErrors.getErrors().size());
        assertEquals(0, syntaxErrors.getErrors().size());
    }

    public void testLexer() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("usecase1.plantuml").getFile());

        ErrorCollector lexErrors = new ErrorCollector();

        PlantUMLLexer lexer = new PlantUMLLexer(new ANTLRInputStream(new FileInputStream(file)));
        lexer.addErrorListener(lexErrors);
        for (Token token = lexer.nextToken();
             token.getType() != Token.EOF;
             token = lexer.nextToken())
        {
            System.out.println("* "+token);
        }
        assertEquals(0, lexErrors.getErrors().size());
    }
}
