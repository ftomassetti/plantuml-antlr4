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
import java.util.BitSet;

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

    /**
     * Rigourous Test :-)
     */
    /*public void testApp() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("usecase1.plantuml").getFile());
        
        PlantUMLLexer lexer = new PlantUMLLexer(new ANTLRInputStream(new FileInputStream(file)));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PlantUMLParser parser = new PlantUMLParser(tokens);
        PlantUMLParser.DiagramContext diagramCtx = parser.diagram();
        assertTrue(true);
    }*/

    public void testLexer() throws IOException {
        System.out.println("TEST LEXER");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("usecase1.plantuml").getFile());

        PlantUMLLexer lexer = new PlantUMLLexer(new ANTLRInputStream(new FileInputStream(file)));
        lexer.addErrorListener(new ANTLRErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
                System.out.println("[ERROR] "+s);
            }

            @Override
            public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {
                System.out.println("B");
            }

            @Override
            public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {
                System.out.println("C");
            }

            @Override
            public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {
                System.out.println("D");
            }
        });
        for (Token token = lexer.nextToken();
             token.getType() != Token.EOF;
             token = lexer.nextToken())
        {
            System.out.println("* "+token);
        }
    }
}
