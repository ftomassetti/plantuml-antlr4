package me.tomassetti;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    public void testApp() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("usecase1.plantuml").getFile());
        
        PlantUMLLexer lexer = new PlantUMLLexer(new ANTLRInputStream(new FileInputStream(file)));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PlantUMLParser parser = new PlantUMLParser(tokens);
        PlantUMLParser.DiagramContext diagramCtx = parser.diagram();
        assertTrue(true);
    }
}
