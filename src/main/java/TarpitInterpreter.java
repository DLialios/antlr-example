import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;

public class TarpitInterpreter {

    public static void main(String[] args) {

        //setup I/O
        CharStream iStream = null;
        FileWriter writer = null;
        try {
            iStream = CharStreams.fromFileName("input.txt");
            writer = new FileWriter("output.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //create derivation tree
        ParseTree tree = new TarpitParser(new CommonTokenStream(new TarpitLexer(iStream))).start();

        //walk tree via visitor pattern
        FileWriter finalWriter = writer;
        new TarpitBaseVisitor<Void>() {

            //30k tape cells
            short[] tape = new short[30000];
            //tape head position
            int pos = 0;

            @Override
            public Void visitStart(TarpitParser.StartContext ctx) {
                return this.visit(ctx.program());
            }

            @Override
            public Void visitProgram(TarpitParser.ProgramContext ctx) {
                if (ctx.getChildCount() == 0) {
                    return null;
                }
                this.visit(ctx.instruction());
                return this.visit(ctx.program());
            }

            @Override
            public Void visitInstruction(TarpitParser.InstructionContext ctx) {
                return this.visit(ctx.getChild(0));
            }

            @Override
            public Void visitLoop(TarpitParser.LoopContext ctx) {
                if (tape[pos] == 0) {
                    return null;
                }
                this.visit(ctx.program());
                return this.visit(ctx);
            }

            @Override
            public Void visitTerminal(TerminalNode node) {
                switch (node.getSymbol().getText()) {
                    case ">":
                        if (pos < 29999) {
                            pos++;
                        }
                        break;
                    case "<":
                        if (pos > 0) {
                            pos--;
                        }
                        break;
                    case "+":
                        tape[pos]++;
                        if (tape[pos] > 127) {
                            tape[pos] = 0;
                        }
                        break;
                    case "-":
                        tape[pos]--;
                        if (tape[pos] < 0) {
                            tape[pos] = 127;
                        }
                        break;
                    case ".":
                        try {
                            finalWriter.write(tape[pos]);
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.exit(1);
                        }
                        break;
                    case ",":
                        int input = -1;
                        try {
                            input = Integer.parseUnsignedInt(JOptionPane.showInputDialog("CELL #" + pos));
                            if (input > 127) {
                                throw new Exception("invalid cell value");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.exit(1);
                        }
                        tape[pos] = (short) input;
                        break;
                }
                return null;
            }

        }.visit(tree);

        //flush and close the writer
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

}
